package br.com.perdeuachou.api.controller;

import br.com.perdeuachou.api.model.Usuario;
import br.com.perdeuachou.api.service.UsuarioService;
import br.com.perdeuachou.api.utils.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService service;

    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {
        return new ResponseEntity<>(service.save(usuario), HttpStatus.CREATED);
    }

    @GetMapping("{userId}")
    @JsonView(Views.Usuario.class)
    public ResponseEntity<Usuario> byId(@PathVariable Long userId) {
        Optional<Usuario> usuario = service.buscarPorId(userId);
        return usuario.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
}
