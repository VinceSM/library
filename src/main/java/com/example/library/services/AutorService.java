package com.example.library.services;

import com.example.library.dtos.requests.AutorRequest;
import com.example.library.dtos.responses.AutorResponse;
import com.example.library.mappers.AutorMapper;
import com.example.library.models.AutorModel;
import com.example.library.repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private AutorMapper autorMapper;

    public ResponseEntity<AutorResponse> crearAutor(@RequestBody AutorRequest autorRequest) {
        AutorModel autor = autorMapper.mapToAutorModel(autorRequest);
        AutorModel nuevoAutor = autorRepository.save(autor);
        AutorResponse autorResponse = autorMapper.mapToAutorResponse(nuevoAutor);
        return ResponseEntity.ok(autorResponse);
    }

    public List<AutorResponse> listarAutores() {
        List<AutorModel> autores = autorRepository.findAll();
        return autores.stream()
                .map(autorMapper::mapToAutorResponse)
                .collect(Collectors.toList());
    }

    public ResponseEntity<String> eliminarAutor(Long id) {
        Optional<AutorModel> autorOptional = autorRepository.findById(id);
        if (autorOptional.isPresent()) {
            autorRepository.deleteById(id);
            return ResponseEntity.ok("Autor eliminado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public AutorModel obtenerAutorPorId(Long idAutor) {
        Optional<AutorModel> autorOptional = autorRepository.findById(idAutor);
        if (autorOptional.isPresent()) {
            return autorOptional.get();
        } else {
            throw new IllegalArgumentException("El autor con el ID proporcionado no existe");
        }
    }


}
