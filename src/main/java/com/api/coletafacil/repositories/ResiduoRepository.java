package com.api.coletafacil.repositories;

import com.api.coletafacil.models.ResiduoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResiduoRepository extends JpaRepository<ResiduoModel, Integer> {
    // Métodos específicos, se necessário
}
