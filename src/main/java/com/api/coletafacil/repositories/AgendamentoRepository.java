package com.api.coletafacil.repositories;

import com.api.coletafacil.models.AgendamentoColetaModel;
import com.api.coletafacil.models.ColetaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoRepository extends JpaRepository<AgendamentoColetaModel, Integer> {
    Page<AgendamentoColetaModel> findAll(Pageable pageable);

}
