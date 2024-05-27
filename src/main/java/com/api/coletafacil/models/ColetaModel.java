package com.api.coletafacil.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "coleta", schema = "coletafacil")
public class ColetaModel implements Serializable {
    @Id
    @Column(name = "id_coleta", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "data_coleta", nullable = false)
    private LocalDateTime dataColeta;

    @NotNull
    @Column(name = "quantidade_residuo", nullable = false)
    private Double quantidadeResiduo;

    @Size(max = 255)
    @Column(name = "observacoes")
    private String observacoes;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_local_coleta", nullable = false)
    private LocalcoletaModel idLocalColeta;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_residuo", nullable = false)
    private ResiduoModel idResiduo;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_base_descarte")
    private BasedescarteModel idBaseDescarte;

}