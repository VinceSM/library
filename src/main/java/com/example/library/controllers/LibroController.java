package com.example.library.controllers;

import com.example.library.dtos.requests.AutorRequest;
import com.example.library.dtos.requests.LibroRequest;
import com.example.library.dtos.responses.LibroResponse;
import com.example.library.mappers.AutorMapper;
import com.example.library.mappers.LibroMapper;
import com.example.library.models.AutorModel;
import com.example.library.models.LibroModel;
import com.example.library.repositories.AutorRepository;
import com.example.library.repositories.LibroRepository;
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

    @Autowired(required = true)
    private AutorRepository autorRepository;

    @Autowired(required = true)
    private AutorModel autorModel;

    @Autowired(required = true)
    private AutorMapper autorMapper;

    @Autowired(required = true)
    private LibroMapper libroMapper;

    @Autowired(required = true)
    private LibroRepository libroRepository;

    @Autowired(required = true)
    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearLibro(@RequestBody LibroRequest libroRequest) {
        try {
            LibroResponse nuevoLibro = libroService.crearLibro(libroRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoLibro);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el libro: " + e.getMessage());
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
