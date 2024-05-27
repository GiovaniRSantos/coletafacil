package com.api.coletafacil.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "agendamentocoletas", schema = "coletafacil")
public class AgendamentoColetaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "agendamentocoletas_id_gen")
    @SequenceGenerator(name = "agendamentocoletas_id_gen", sequenceName = "agendamentocoletas_id_agendamento_seq", allocationSize = 1)
    @Column(name = "id_agendamento", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "data_agendamento", nullable = false)
    private Instant dataAgendamento;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_local_coleta", nullable = false)
    private LocalcoletaModel idLocalColeta;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_residuo", nullable = false)
    private ResiduoModel idResiduo;

    @NotNull
    @Column(name = "quantidade", nullable = false, precision = 10, scale = 2)
    private BigDecimal quantidade;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_base_descarte", nullable = false)
    private BasedescarteModel idBaseDescarte;

    @Column(name = "observacoes", length = Integer.MAX_VALUE)
    private String observacoes;

}