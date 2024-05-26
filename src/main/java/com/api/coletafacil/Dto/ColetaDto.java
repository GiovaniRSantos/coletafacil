package com.api.coletafacil.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class ColetaDto {



    @NotBlank()
    private Integer id_coleta;

    @NotBlank()
    private LocalDateTime data_coleta;

    @NotBlank()
    private Integer id_local_coleta;

    @NotBlank()
    private Integer id_residuo;

    @NotBlank()
    private Integer id_base_descarte;

    @NotBlank()
    private Float quantidade_residuo;

    private String observacoes;




}
