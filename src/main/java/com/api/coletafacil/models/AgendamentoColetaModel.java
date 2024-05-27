package com.api.coletafacil.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "agendamentocoletas", schema = "coletafacil")
public class AgendamentoColetaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAgendamento;

    @Column(name = "observacoes", length = Integer.MAX_VALUE)
    private String observacoes;

    @Getter
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_coleta", referencedColumnName = "id_coleta")
    private ColetaModel coleta;

}


