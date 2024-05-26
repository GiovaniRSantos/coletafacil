package com.api.coletafacil.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@Table(name = "coleta")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ColetaModel implements Serializable {

    @Id
    @Column(nullable = false, unique = true)
    private Integer id_coleta;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime data_coleta;

    @Column(nullable = false)
    private Integer id_local_coleta;

    @Column(nullable = false)
    private Integer id_residuo;

    @Column(nullable = false)
    private Integer id_base_descarte;

    @Column(nullable = false)
    private Float quantidade_residuo;

    private String observacoes;
    @Getter
    @jakarta.persistence.Id
    private Long id;


}
