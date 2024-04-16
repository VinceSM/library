package com.example.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.library.models.AutorModel;

@Repository
public interface AutorRepository extends JpaRepository<AutorModel, Long> {
}
