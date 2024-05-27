package com.api.coletafacil.service;

import com.api.coletafacil.Dto.AgendamentoColetaDto;
import com.api.coletafacil.models.AgendamentoColetaModel;
import com.api.coletafacil.repositories.AgendamentoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    public Page<AgendamentoColetaModel> findAll(int page, int size) {
        return agendamentoRepository.findAll(PageRequest.of(page, size));
    }

    public List<AgendamentoColetaDto> findAllAsDTO(int page, int size) {
        Page<AgendamentoColetaModel> pageResult = findAll(page, size);
        return pageResult.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private AgendamentoColetaDto convertToDTO(AgendamentoColetaModel agendamentoColetaModel) {
        AgendamentoColetaDto dto = new AgendamentoColetaDto();
        dto.setDataAgendamento(agendamentoColetaModel.getDataAgendamento());
        dto.setIdLocalColeta(agendamentoColetaModel.getIdLocalColeta());
        dto.setIdResiduo(agendamentoColetaModel.getIdResiduo());
        dto.setIdBaseDescarte(agendamentoColetaModel.getIdBaseDescarte());
        dto.setObservacoes(agendamentoColetaModel.getObservacoes());
        return dto;
    }

}
