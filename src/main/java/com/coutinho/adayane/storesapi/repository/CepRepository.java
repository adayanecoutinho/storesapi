package com.coutinho.adayane.storesapi.repository;

import com.coutinho.adayane.storesapi.model.Cep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CepRepository extends JpaRepository<Cep, Long> {

    @Query(value = "SELECT COUNT(1) > 0 FROM Cep C WHERE (C.faixaInicio BETWEEN :faixaIni AND :faixaFim) " +
            "OR (C.faixaFim BETWEEN :faixaIni AND :faixaFim)")
    Boolean faixaEstaContidaEmFaixaExiste(final Integer faixaIni, final Integer faixaFim);

    @Query(value = "SELECT COUNT(1) > 0 FROM Cep C WHERE C.id != :id AND ((C.faixaInicio BETWEEN :faixaIni AND :faixaFim) " +
            "OR (C.faixaFim BETWEEN :faixaIni AND :faixaFim))")
    Boolean atualizaFaixaVerificaEstaContida(final long id,final Integer faixaIni, final Integer faixaFim);

    @Query(value = "SELECT C.codigoLoja FROM Cep C WHERE C.faixaInicio <= :cep AND C.faixaFim >= :cep")
    String faixaRetornaLojafinal(final Integer cep);
}
