package com.api.coletafacil.repositories;

import com.api.coletafacil.models.ColetaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColetaRepository extends JpaRepository<ColetaModel, Integer> {
    Page<ColetaModel> findAll(Pageable pageable);
}

