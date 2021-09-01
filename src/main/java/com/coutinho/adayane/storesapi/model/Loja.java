package com.coutinho.adayane.storesapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Loja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Código da Loja é Obrigatório!")
    @JsonProperty("codigo_loja")
    @Column(nullable = false)
    private String codigoLoja;

    @NotNull(message = "Faixa Início é Obrigatório!")
    @Min(1)
    @Max(99999999)
    @JsonProperty("faixa_inicio")
    @Column(nullable = false)
    private Integer faixaInicio;

    @NotNull(message = "Faixa Final é Obrigatório!")
    @Min(1)
    @Max(99999999)
    @JsonProperty("faixa_fim")
    @Column(nullable = false)
    private Integer faixaFim;


}
