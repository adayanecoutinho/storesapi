package com.coutinho.adayane.storesapi.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class LojaResponseDto {

    private Long id;
    private String codigoLoja;
    private Integer faixaInicio;
    private Integer faixaFim;

}
