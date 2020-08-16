package br.com.perdeuachou.api.controller;

import br.com.perdeuachou.api.model.Usuario;
import br.com.perdeuachou.api.model.pertence.Image;
import br.com.perdeuachou.api.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    ImageService service;

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
