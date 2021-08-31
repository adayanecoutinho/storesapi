package com.coutinho.adayane.storesapi.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LojaDto {

    private Long id;
    @JsonProperty("codigo_loja")
    private String codigoLoja;
    @JsonProperty("faixa_inicio")
    private Integer faixaInicio;
    @JsonProperty("faixa_fim")
    private Integer faixaFim;
}
