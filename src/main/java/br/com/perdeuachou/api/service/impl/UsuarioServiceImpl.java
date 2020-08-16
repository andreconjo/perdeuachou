package br.com.perdeuachou.api.service.impl;


import br.com.perdeuachou.api.model.Usuario;
import br.com.perdeuachou.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements br.com.perdeuachou.api.service.UsuarioService {

    @Autowired
    UsuarioRepository repository;

    @Override
    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }
}
