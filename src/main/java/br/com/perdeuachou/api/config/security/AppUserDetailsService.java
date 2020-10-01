package br.com.perdeuachou.api.config.security;

import br.com.perdeuachou.api.model.Usuario;
import br.com.perdeuachou.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service("userDetailsService")
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    UsuarioService usuarioService;

    @Override
    public UserSystem loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            Optional<Usuario> userOptional = Optional.empty();
            Cookie refreshToken = null;
//            if(request.getCookies() != null)
//                refreshToken = Arrays.asList(request.getCookies()).stream().filter(c -> c.getName().equalsIgnoreCase("refreshToken")).collect(Collectors.toList()).get(0);

//            if (refreshToken != null && request.getParameter("username") == null) {
            userOptional = usuarioService.buscarPorEmail(email);

            Usuario user = userOptional.orElseThrow(() -> new UsernameNotFoundException("Invalid User or Password"));
            return new UserSystem(user, getPermissions(user));
        }catch (Exception ex){
            throw new UsernameNotFoundException("Invalid User or Password");
        }

    }

    private Collection<? extends GrantedAuthority> getPermissions(Usuario user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        if(user.getRoles().size() > 0)
            user.getRoles().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescription().toUpperCase())));

        return authorities;
    }
}
