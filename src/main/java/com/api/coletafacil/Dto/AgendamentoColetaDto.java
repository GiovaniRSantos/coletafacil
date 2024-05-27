package com.api.coletafacil.Dto;

import com.api.coletafacil.models.BasedescarteModel;
import com.api.coletafacil.models.LocalcoletaModel;
import com.api.coletafacil.models.ResiduoModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
public class AgendamentoColetaDto {
    private Integer id;
    private Instant dataAgendamento;
    private LocalcoletaModel idLocalColeta;
    private ResiduoModel idResiduo;
    private Double quantidadeResiduo;
    private BasedescarteModel idBaseDescarte;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String observacoes;


}
