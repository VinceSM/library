package com.example.library.models;

import org.springframework.stereotype.Component;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "libros")
@Component
public class LibroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "isbn")
    private String isbn;

    private Long idAutor;

}
