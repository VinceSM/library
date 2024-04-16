package com.example.library.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LibroResponse {
    private Long id;
    private String titulo;
    private Long idAutor;
    private String isbn;
}
