package com.example.library.controllers;

import com.example.library.dtos.requests.LibroRequest;
import com.example.library.dtos.responses.LibroResponse;
import com.example.library.models.AutorModel;
import com.example.library.services.AutorService;
import com.example.library.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/libros")
public class LibroController {

    @Autowired(required = true)
    private final LibroService libroService;

    @Autowired
    private AutorService autorService;

    @Autowired(required = true)
    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @PostMapping("/crear")
    public ResponseEntity<LibroResponse> crearLibro(@RequestBody LibroRequest libroRequest) {
        Optional<AutorModel> autorOptional = Optional.ofNullable(autorService.obtenerAutorPorId(libroRequest.getIdAutor()));
        if (autorOptional.isPresent()) {
            LibroResponse nuevoLibro = libroService.crearLibro(libroRequest, autorOptional.get());
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoLibro);
        } else {
            return null;
        }
    }

    /*@PostMapping("/crear")
    public ResponseEntity<LibroResponse> crearLibro(@RequestBody LibroRequest libroRequest) {
        for (AutorRequest autorRequest : libroRequest.getAutores()) {
            Optional<AutorModel> autorOptional = autorRepository.findById(autorModel.getId());
            if (!autorOptional.isPresent()) {
                AutorModel autor = autorMapper.mapToAutorModel(autorRequest);
                autorRepository.save(autor);
            }
        }

        LibroModel libro = libroMapper.mapToLibroModel(libroRequest);
        libroRepository.save(libro);
        LibroResponse libroResponse = libroMapper.mapToLibroResponse(libro);
        return ResponseEntity.status(HttpStatus.CREATED).body(libroResponse);
    }*/


    @GetMapping("/listar")
    public ResponseEntity<List<LibroResponse>> listarLibros() {
        List<LibroResponse> libros = libroService.listarLibros();
        return ResponseEntity.ok(libros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibroResponse> obtenerLibroPorId(@PathVariable Long id) {
        LibroResponse libro = libroService.obtenerLibroPorId(id);
        return ResponseEntity.ok(libro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarLibro(@PathVariable Long id) {
        libroService.eliminarLibro(id);
        return ResponseEntity.ok("Libro eliminado");
    }
}
