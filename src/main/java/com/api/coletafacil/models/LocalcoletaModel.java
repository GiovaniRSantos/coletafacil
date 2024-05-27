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
@Table(name = "localcoleta", schema = "coletafacil")
public class LocalcoletaModel {
    @Id
    @ColumnDefault("nextval('coletafacil.localcoleta_id_local_coleta_seq'::regclass)")
    @Column(name = "id_local_coleta", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "endereco", nullable = false)
    private String endereco;

    @Size(max = 255)
    @NotNull
    @Column(name = "bairro", nullable = false)
    private String bairro;

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
    @Column(name = "tipo_local", nullable = false)
    private String tipoLocal;

}