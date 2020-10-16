package br.com.perdeuachou.api.controller;

import br.com.perdeuachou.api.service.PertenceService;
import br.com.perdeuachou.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bi/semana")
public class BiController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PertenceService pertenceService;

    @GetMapping("cadastro/usuarios/semana")
    public ResponseEntity<?> todosUsuariosCadastrados() {
        return ResponseEntity.ok().body(usuarioService.cadastros());
    }
    @GetMapping("cadastro/pertences/semana")
    public ResponseEntity<?> todosPertencesCadastrados() {
        return ResponseEntity.ok().body(pertenceService.cadastros());
    }

    @GetMapping("entregas/pertences/semana")
    public ResponseEntity<?> todosPertencesEntregues() {
        return ResponseEntity.ok().body(pertenceService.entregas());
    }

    @GetMapping("cadastro/usuarios/semana")
    public ResponseEntity<?> todosUsuariosCadastradosNaSemana() {
        return ResponseEntity.ok().body(usuarioService.cadastrosDaSemana());
    }
    @GetMapping("cadastro/pertences/semana")
    public ResponseEntity<?> todosPertencesCadastradosNaSemana() {
        return ResponseEntity.ok().body(pertenceService.cadastrosDaSemana());
    }

    @GetMapping("entregas/pertences/semana")
    public ResponseEntity<?> todosPertencesEntreguesNaSemana() {
        return ResponseEntity.ok().body(pertenceService.entregasDaSemana());
    }
}
