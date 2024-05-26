package com.api.coletafacil.service;

import com.api.coletafacil.models.ColetaModel;
import com.api.coletafacil.repositories.ColetaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ColetaService {

    final ColetaRepository coletaRepository;

    public ColetaService(ColetaRepository coletaRepository) {
        this.coletaRepository = coletaRepository;
    }

    public Page<ColetaModel> findAll(Pageable pageable) {
        return coletaRepository.findAll(pageable);
    }
}

