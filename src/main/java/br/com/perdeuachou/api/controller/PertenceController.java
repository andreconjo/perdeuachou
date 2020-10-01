package br.com.perdeuachou.api.controller;

import br.com.perdeuachou.api.model.pertence.Pertence;
import br.com.perdeuachou.api.service.PertenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/pertence")
public class PertenceController {

    @Autowired
    PertenceService service;

    @GetMapping
    public ResponseEntity<Page<Pertence>> getAll(Pageable pageable) {
        return new ResponseEntity<>(service.getAllPaginated(pageable), HttpStatus.OK);
    }

    @GetMapping("{userId}")
    public ResponseEntity<List<Pertence>> getAllBy(@PathVariable Long userId, Pageable pageable) {
        return new ResponseEntity<>(service.getMatches(userId, pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pertence> save(@RequestBody Pertence pertence) {
        return new ResponseEntity<>(service.save(pertence), HttpStatus.CREATED);
    }
}
