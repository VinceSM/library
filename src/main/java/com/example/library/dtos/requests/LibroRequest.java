package com.example.library.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LibroRequest {
    private String titulo;
    private String isbn;
    private List<AutorRequest> autores;
}
