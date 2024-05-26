package com.api.coletafacil.controller;

import com.api.coletafacil.models.ColetaModel;
import com.api.coletafacil.service.ColetaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coletas")
public class ColetaController {

    final ColetaService coletaService;

    public ColetaController(ColetaService coletaService) {
        this.coletaService = coletaService;
    }

    @GetMapping()
    public ResponseEntity<Page<ColetaModel>> getAll(Pageable pageable) {
        Page<ColetaModel> coletas = coletaService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(coletas);
    }
}
