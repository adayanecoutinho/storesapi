package com.coutinho.adayane.storesapi.repository;

import com.coutinho.adayane.storesapi.entity.Cep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CepRepository extends JpaRepository<Cep, Long> {

    @Query(value = "SELECT COUNT(1) > 0 FROM Cep C WHERE C.faixaInicio >= :faixaIni AND C.faixaInicio <= :faixaFim " +
            "OR C.faixaFim >= :faixaFim AND C.faixaFim <= :faixaFim")
    Boolean faixaEstaContidaEmFaixaExiste(final Integer faixaIni, final Integer faixaFim);

    @Query(value = "SELECT C.codigoLoja FROM Cep C WHERE C.faixaInicio <= :cep AND C.faixaFim >= :cep")
    String faixaRetornaLojafinal(final Integer cep);
}
