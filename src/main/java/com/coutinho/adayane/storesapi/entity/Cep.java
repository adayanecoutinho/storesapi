package com.coutinho.adayane.storesapi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonProperty("codigo_loja")
    @Column(nullable = false)
    private String codigoLoja;

    @JsonProperty("faixa_inicio")
    @Column(nullable = false)
    private Integer faixaInicio;

    @JsonProperty("faixa_fim")
    @Column(nullable = false)
    private Integer faixaFim;
}
