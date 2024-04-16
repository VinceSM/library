package com.example.library.controllers;

import com.example.library.dtos.requests.AutorRequest;
import com.example.library.dtos.responses.AutorResponse;
import com.example.library.services.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private final AutorService autorService;

    @Autowired
    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @PostMapping("/crear")
    public ResponseEntity<AutorResponse> crearAutor(@RequestBody AutorRequest autorRequest) {
        AutorResponse nuevoAutor = autorService.crearAutor(autorRequest).getBody();
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAutor);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<AutorResponse>> listarAutores() {
        List<AutorResponse> autores = autorService.listarAutores();
        return ResponseEntity.ok(autores);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> borrarAutor(@PathVariable("id") Long id) {
        autorService.eliminarAutor(id);
        return ResponseEntity.ok("Autor eliminado");
    }
}
