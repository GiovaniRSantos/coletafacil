package com.api.coletafacil.service;

import com.api.coletafacil.Dto.AgendamentoColetaDto;
import com.api.coletafacil.config.ColetaJaExistenteException;
import com.api.coletafacil.models.*;
import com.api.coletafacil.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @Autowired
    private ColetaRepository coletaRepository;

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
        dto.setObservacoes(agendamentoColetaModel.getObservacoes());
        dto.setDataAgendamento(agendamentoColetaModel.getColeta().getDataColeta());
        dto.setStatus(agendamentoColetaModel.getStatus());
        return dto;
    }

    public AgendamentoColetaModel createAgendamento(AgendamentoColetaDto agendamentoDto) {

        AgendamentoColetaModel agendamentoModel = new AgendamentoColetaModel();
        if (agendamentoDto.getObservacoes() != null) {
            agendamentoModel.setObservacoes(agendamentoDto.getObservacoes());
        }


        Integer idLocalColeta = agendamentoDto.getIdLocalColeta();
        Integer idResiduo = agendamentoDto.getIdResiduo();
        Integer idBaseDescarte = agendamentoDto.getIdBaseDescarte();
        agendamentoModel.setStatus(StatusAgendamento.Ativo);

        agendamentoDto.setStatus(agendamentoDto.getStatus());

        LocalcoletaModel localColeta = localcoletaRepository.findById(idLocalColeta)
                .orElseThrow(() -> new EntityNotFoundException("Local de coleta não encontrado"));
        ResiduoModel residuo = residuoRepository.findById(idResiduo)
                .orElseThrow(() -> new EntityNotFoundException("Resíduo não encontrado"));
        BasedescarteModel baseDescarte = basedescarteRepository.findById(idBaseDescarte)
                .orElseThrow(() -> new EntityNotFoundException("Base de descarte não encontrada"));


        ColetaModel coletaModel = new ColetaModel();

        List<ColetaModel> coletasCriadas = coletaRepository.findAll();

        List<AgendamentoColetaModel> agendamentos = agendamentoRepository.findAll();


        coletasCriadas.forEach(coleta -> {
            agendamentos.forEach(agendamento -> {
                if (agendamento.getStatus() == StatusAgendamento.Ativo && agendamento.getColeta().getDataColeta().isEqual(agendamentoDto.getDataAgendamento())
                ) {
                    throw new ColetaJaExistenteException("Já existe um agendamento ativo para essa coleta");
                }
            });
        });


        coletaModel.setDataColeta(agendamentoDto.getDataAgendamento());

        coletaModel.setQuantidadeResiduo(Double.valueOf(agendamentoDto.getQuantidade()));
        if (agendamentoDto.getObservacoes() != null) {
            coletaModel.setObservacoes(agendamentoDto.getObservacoes());
        }
        coletaModel.setIdLocalColeta(localColeta);
        coletaModel.setIdResiduo(residuo);
        coletaModel.setIdBaseDescarte(baseDescarte);
        coletaModel = coletaRepository.save(coletaModel);

        agendamentoModel.setColeta(coletaModel);

        return agendamentoRepository.save(agendamentoModel);
    }


}