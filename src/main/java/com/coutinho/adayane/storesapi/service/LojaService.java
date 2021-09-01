package com.coutinho.adayane.storesapi.service;
import com.coutinho.adayane.storesapi.dto.request.LojaDto;
import com.coutinho.adayane.storesapi.model.Loja;
import com.coutinho.adayane.storesapi.model.exception.CepNotFoundException;
import com.coutinho.adayane.storesapi.model.exception.FaixaValidationException;
import com.coutinho.adayane.storesapi.model.exception.LojaNotFoundException;
import com.coutinho.adayane.storesapi.repository.LojaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LojaService {

    private final LojaRepository lojaRepository;


    public Loja createCep(LojaDto dto) {
        Loja lojaToSave = dtoToModel(null, dto);
        validationFaixaContida(lojaToSave);

        return lojaRepository.save(lojaToSave);
    }

    private static Loja dtoToModel(final Long id, final LojaDto dto) {
        final Loja loja = new Loja();
        if (id != null) {
            loja.setId(id);
        }
        loja.setCodigoLoja(dto.getCodigoLoja());
        loja.setFaixaInicio(dto.getFaixaInicio());
        loja.setFaixaFim(dto.getFaixaFim());

        return loja;
    }

    public Optional<Loja> findById(long id) {
        Optional<Loja> optional = lojaRepository.findById(id);

        if (!optional.isPresent()) {
            log.info("Erro ao encontrar. Loja de id: " + id + " não cadastrada.");
            throw new LojaNotFoundException("Não existe Loja cadastrada com esse id: " + id);
        }

        return optional;
    }

    public Loja updateById(long id, LojaDto dto) {
        Loja newCep = dtoToModel(id, dto);
        validationFaixaContidaDiferenteId(newCep);
        Optional<Loja> optional = findById(newCep.getId());

        Loja oldcep = optional.get();
        oldcep.setFaixaFim(newCep.getFaixaFim());
        oldcep.setFaixaInicio(newCep.getFaixaInicio());
        oldcep.setCodigoLoja(newCep.getCodigoLoja());

        return lojaRepository.save(oldcep);
    }

    public void delete(long id) {
        validationDelete(id);
        lojaRepository.deleteById(id);
    }

    public String findByCep(int cep) {
        String retornaCep = lojaRepository.faixaRetornaLojafinal(cep);

        validationFindCep(retornaCep, cep);
        return retornaCep;

    }

    private void validationFaixaContida(Loja loja) {
        if (lojaRepository.faixaEstaContidaEmFaixaExiste(loja.getFaixaInicio(), loja.getFaixaFim()) == true) {
            log.info("Erro ao cadastrar Loja. Intervalo de Cep já existente. Inicio faixa:"
                    + loja.getFaixaInicio() + " Fim faixa: " + loja.getFaixaFim());
            throw new FaixaValidationException("Essa faixa já está cadastrada em outra loja..");
        }
    }

    private void validationFaixaContidaDiferenteId(Loja loja) {
        System.out.println(loja.getId());
        if (lojaRepository.atualizaFaixaVerificaEstaContida(loja.getId(), loja.getFaixaInicio(), loja.getFaixaFim()) == true) {
            log.info("Erro ao atualizars Loja. Intervalo de Cep já existente. Inicio faixa: "
                    + loja.getFaixaInicio() + " Fim faixa: " + loja.getFaixaFim());
            throw new FaixaValidationException("Essa faixa já está cadastrada em outra Loja.");
        }
    }

    private void validationDelete(long id) {
        lojaRepository
                .findById(id)
                .orElseThrow(() -> new CepNotFoundException("Não possui Loja cadastrada com esse id" + id + "."));
    }

    private void validationFindCep(String retornaCep, int cep) {
        if (retornaCep == null) {
            throw new FaixaValidationException("Não possui Loja cadastrada nessa faixa de CEP " + cep);
        }

    }

    public Page<Loja> listAll(Pageable pageable) {
        Page<Loja> allLoja = lojaRepository.findAll(pageable);

        return allLoja;
    }
}
