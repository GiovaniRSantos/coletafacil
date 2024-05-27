package com.api.coletafacil.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "basedescarte", schema = "coletafacil")
public class BasedescarteModel {
    @Id
    @ColumnDefault("nextval('coletafacil.basedescarte_id_base_descarte_seq'::regclass)")
    @Column(name = "id_base_descarte", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "nome_base", nullable = false)
    private String nomeBase;

    @Size(max = 255)
    @NotNull
    @Column(name = "endereco", nullable = false)
    private String endereco;

    @Size(max = 255)
    @NotNull
    @Column(name = "cidade", nullable = false)
    private String cidade;

    @Size(max = 255)
    @NotNull
    @Column(name = "estado", nullable = false)
    private String estado;

    @Size(max = 255)
    @NotNull
    @Column(name = "cep", nullable = false)
    private String cep;

    @Size(max = 255)
    @NotNull
    @Column(name = "tipo_descarte", nullable = false)
    private String tipoDescarte;

    @NotNull
    @Column(name = "capacidade_tonelada", nullable = false)
    private Double capacidadeTonelada;

    @Size(max = 255)
    @NotNull
    @Column(name = "horario_funcionamento", nullable = false)
    private String horarioFuncionamento;

    @Size(max = 255)
    @Column(name = "responsavel")
    private String responsavel;

    @Size(max = 255)
    @Column(name = "telefone")
    private String telefone;

    @Size(max = 255)
    @Column(name = "observacoes")
    private String observacoes;

}