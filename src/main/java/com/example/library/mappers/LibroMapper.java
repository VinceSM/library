package com.example.library.mappers;

import com.example.library.dtos.requests.LibroRequest;
import com.example.library.dtos.responses.LibroResponse;
import com.example.library.models.LibroModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroMapper {
    @Autowired
    private AutorMapper autorMapper;

    public LibroModel mapToLibroModel(LibroRequest libroRequest) {
        LibroModel libro = new LibroModel();
        libro.setTitulo(libroRequest.getTitulo());
        libro.setAutores(autorMapper.mapToAutorModelList(libroRequest.getAutores()));
        libro.setIsbn(libroRequest.getIsbn());
        return libro;
    }

    public LibroResponse mapToLibroResponse(LibroModel libro) {
        LibroResponse libroResponse = new LibroResponse();
        libroResponse.setId(libro.getId());
        libroResponse.setTitulo(libro.getTitulo());
        libroResponse.setAutores(autorMapper.mapToAutorResponseList(libro.getAutores()));
        libroResponse.setIsbn(libro.getIsbn());
        return libroResponse;
    }
}
