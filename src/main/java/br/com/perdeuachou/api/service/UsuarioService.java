package br.com.perdeuachou.api.service;

import br.com.perdeuachou.api.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario save(Usuario usuario);

    Optional<Usuario> buscarPorEmail(String email);

    List<Long> cadastrosDaSemana();

    Optional<Usuario> buscarPorId(Long usuario);

    List<Usuario> buscarTodos();
}
