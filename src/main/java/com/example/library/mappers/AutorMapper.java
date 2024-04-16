package com.example.library.mappers;

import com.example.library.dtos.requests.AutorRequest;
import com.example.library.dtos.responses.AutorResponse;
import com.example.library.models.AutorModel;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AutorMapper {
    public AutorModel mapToAutorModel(AutorRequest autorRequest) {
        AutorModel autor = new AutorModel();
        autor.setNombre(autorRequest.getNombre());
        autor.setApellido(autorRequest.getApellido());
        return autor;
    }

    public AutorResponse mapToAutorResponse(AutorModel nuevoAutor) {
        AutorResponse autorResponse = new AutorResponse();
        autorResponse.setId(nuevoAutor.getId());
        autorResponse.setNombre(nuevoAutor.getNombre());
        autorResponse.setApellido(nuevoAutor.getApellido());
        return autorResponse;
    }

}

