package com.api.coletafacil.controller;

import com.api.coletafacil.Dto.AgendamentoColetaDto;
import com.api.coletafacil.models.AgendamentoColetaModel;
import com.api.coletafacil.service.AgendamentoService;
import com.api.coletafacil.service.ColetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AgendamentosController {

    @Autowired
    private final AgendamentoService agendamentoService;

    public AgendamentosController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @GetMapping("/api/agendamentos")
    public List<AgendamentoColetaDto> getAgendamentos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return agendamentoService.findAllAsDTO(page, size);
    }

    @PostMapping("/api/criar-agendamento")
    public ResponseEntity<?> createAgendamento(@RequestBody AgendamentoColetaDto agendamentoDto) {
        try {
            AgendamentoColetaModel novoAgendamento = agendamentoService.createAgendamento(agendamentoDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoAgendamento);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar agendamento: " + e.getMessage());
        }
    }


}
