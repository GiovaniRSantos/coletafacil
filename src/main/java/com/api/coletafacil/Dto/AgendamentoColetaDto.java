package com.api.coletafacil.Dto;

import com.api.coletafacil.models.BasedescarteModel;
import com.api.coletafacil.models.LocalcoletaModel;
import com.api.coletafacil.models.ResiduoModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Setter
@Getter
public class AgendamentoColetaDto {

    private LocalDateTime dataAgendamento;
    private Integer idLocalColeta;
    private Integer idResiduo;
    private BigDecimal quantidade;
    private Integer idBaseDescarte;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String observacoes;


}
