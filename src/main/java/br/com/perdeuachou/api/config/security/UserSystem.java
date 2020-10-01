package br.com.perdeuachou.api.config.security;


import br.com.perdeuachou.api.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserSystem extends User {

    private Usuario user = null;

    public UserSystem(Usuario user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getId().toString(), user.getSenha(), authorities);
        this.user = user;
    }

    public Usuario getUsuario() {
        return this.user;
    }
}
