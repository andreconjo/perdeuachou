package br.com.perdeuachou.api.service;

import br.com.perdeuachou.api.model.Usuario;

import java.util.Optional;

public interface UsuarioService {
    Usuario save(Usuario usuario);

    Optional<Usuario> buscarPorEmail(String email);
}
