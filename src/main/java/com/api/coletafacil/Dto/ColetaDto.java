package com.api.coletafacil.Dto;

import com.api.coletafacil.models.BasedescarteModel;
import com.api.coletafacil.models.LocalcoletaModel;
import com.api.coletafacil.models.ResiduoModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Setter
@Getter
public class ColetaDto {
    private Integer id;
    private LocalDateTime dataColeta;
    private Double quantidadeResiduo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String observacoes;

    private LocalcoletaModel idLocalColeta;
    private ResiduoModel idResiduo;
    private BasedescarteModel idBaseDescarte;

}
