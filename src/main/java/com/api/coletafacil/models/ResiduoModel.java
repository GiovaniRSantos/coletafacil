package com.api.coletafacil.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "residuo", schema = "coletafacil")
public class ResiduoModel {
    @Id
    @ColumnDefault("nextval('coletafacil.residuo_id_residuo_seq'::regclass)")
    @Column(name = "id_residuo", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "tipo_residuo", nullable = false)
    private String tipoResiduo;

    @Size(max = 255)
    @Column(name = "descricao_residuo")
    private String descricaoResiduo;

    @Size(max = 255)
    @NotNull
    @Column(name = "cor_coleta", nullable = false)
    private String corColeta;

}