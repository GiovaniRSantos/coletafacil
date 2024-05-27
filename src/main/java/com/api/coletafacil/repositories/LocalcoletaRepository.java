package com.api.coletafacil.repositories;

import com.api.coletafacil.models.LocalcoletaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalcoletaRepository extends JpaRepository<LocalcoletaModel, Integer> {
    // Métodos específicos, se necessário
}
