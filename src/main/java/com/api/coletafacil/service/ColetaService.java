package com.api.coletafacil.service;

import com.api.coletafacil.Dto.ColetaDto;
import com.api.coletafacil.models.ColetaModel;
import com.api.coletafacil.repositories.ColetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColetaService {

    @Autowired
    private ColetaRepository coletaRepository;

    public Page<ColetaModel> findAll(int page, int size) {
        return coletaRepository.findAll(PageRequest.of(page, size));
    }

    public List<ColetaDto> findAllAsDTO(int page, int size) {
        Page<ColetaModel> pageResult = findAll(page, size);
        return pageResult.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private ColetaDto convertToDTO(ColetaModel coletaModel) {
        ColetaDto dto = new ColetaDto();
        dto.setId(coletaModel.getId());
        dto.setDataColeta(coletaModel.getDataColeta());
        dto.setQuantidadeResiduo(coletaModel.getQuantidadeResiduo());
        dto.setObservacoes(coletaModel.getObservacoes());
        dto.setIdLocalColeta(coletaModel.getIdLocalColeta());
        dto.setIdResiduo(coletaModel.getIdResiduo());
        dto.setIdBaseDescarte(coletaModel.getIdBaseDescarte());
        return dto;
    }
}
