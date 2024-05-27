package com.api.coletafacil.service;

import com.api.coletafacil.Dto.AgendamentoColetaDto;
import com.api.coletafacil.models.AgendamentoColetaModel;
import com.api.coletafacil.models.BasedescarteModel;
import com.api.coletafacil.models.LocalcoletaModel;
import com.api.coletafacil.models.ResiduoModel;
import com.api.coletafacil.repositories.AgendamentoRepository;
import com.api.coletafacil.repositories.BasedescarteRepository;
import com.api.coletafacil.repositories.LocalcoletaRepository;
import com.api.coletafacil.repositories.ResiduoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;


    @Autowired
    private LocalcoletaRepository localcoletaRepository;

    @Autowired
    private ResiduoRepository residuoRepository;

    @Autowired
    private BasedescarteRepository basedescarteRepository;

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
        dto.setIdLocalColeta(agendamentoColetaModel.getIdLocalColeta().getId());
        dto.setIdResiduo(agendamentoColetaModel.getIdResiduo().getId());
        dto.setIdBaseDescarte(agendamentoColetaModel.getIdBaseDescarte().getId());
        dto.setObservacoes(agendamentoColetaModel.getObservacoes());
        return dto;
    }

    public AgendamentoColetaModel createAgendamento(AgendamentoColetaDto agendamentoDto) {
        AgendamentoColetaModel agendamentoModel = new AgendamentoColetaModel();
        agendamentoModel.setDataAgendamento(agendamentoDto.getDataAgendamento());

        // Resolver entidades pelos identificadores
        LocalcoletaModel localColeta = localcoletaRepository.findById(agendamentoDto.getIdLocalColeta())
                .orElseThrow(() -> new EntityNotFoundException("Local de coleta não encontrado"));
        agendamentoModel.setIdLocalColeta(localColeta);

        ResiduoModel residuo = residuoRepository.findById(agendamentoDto.getIdResiduo())
                .orElseThrow(() -> new EntityNotFoundException("Resíduo não encontrado"));
        agendamentoModel.setIdResiduo(residuo);

        BasedescarteModel baseDescarte = basedescarteRepository.findById(agendamentoDto.getIdBaseDescarte())
                .orElseThrow(() -> new EntityNotFoundException("Base de descarte não encontrada"));
        agendamentoModel.setIdBaseDescarte(baseDescarte);

        agendamentoModel.setQuantidade(agendamentoDto.getQuantidade());
        agendamentoModel.setObservacoes(agendamentoDto.getObservacoes());

        return agendamentoRepository.save(agendamentoModel);
    }

}
