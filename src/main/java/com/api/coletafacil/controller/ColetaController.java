package com.api.coletafacil.controller;

import com.api.coletafacil.Dto.ColetaDto;
import com.api.coletafacil.service.ColetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ColetaController {

    @Autowired
    private ColetaService coletaService;

    @GetMapping("/api/coletas")
    public List<ColetaDto> getColetas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return coletaService.findAllAsDTO(page, size);
    }
}
