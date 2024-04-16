package com.example.library.services;


import com.example.library.dtos.requests.LibroRequest;
import com.example.library.dtos.responses.LibroResponse;
import com.example.library.mappers.LibroMapper;
import com.example.library.models.AutorModel;
import com.example.library.models.LibroModel;
import com.example.library.repositories.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private LibroMapper libroMapper;

    @Transactional
    public LibroResponse crearLibro(LibroRequest libroRequest, AutorModel autor) {
        LibroModel libro = new LibroModel();
        libro.setIsbn(libroRequest.getIsbn());
        libro.setTitulo(libroRequest.getTitulo());
        libro.setIdAutor(libroRequest.getIdAutor());

        LibroModel libroGuardado = libroRepository.save(libro);

        return libroMapper.mapToLibroResponse(libroGuardado);
    }

    @Transactional(readOnly = true)
    public List<LibroResponse> listarLibros() {
        List<LibroModel> libros = libroRepository.findAll();
        return libros.stream()
                .map(libroMapper::mapToLibroResponse)
                .collect(Collectors.toList());
    }
    @Transactional
    public void eliminarLibro(Long id) {
        libroRepository.deleteById(id);
    }

    public LibroResponse obtenerLibroPorId(Long id) {
        LibroModel libro = libroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Libro no encontrado con id: " + id));

        return libroMapper.mapToLibroResponse(libro);
    }
}
