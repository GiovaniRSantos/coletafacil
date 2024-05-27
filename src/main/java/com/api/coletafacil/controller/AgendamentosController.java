package com.api.coletafacil.controller;

import com.api.coletafacil.Dto.AgendamentoColetaDto;
import com.api.coletafacil.service.AgendamentoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AgendamentosController {

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
}
