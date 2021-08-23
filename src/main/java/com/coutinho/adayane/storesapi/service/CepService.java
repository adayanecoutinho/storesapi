package com.coutinho.adayane.storesapi.service;

import com.coutinho.adayane.storesapi.model.Cep;
import com.coutinho.adayane.storesapi.model.exception.FaixaValidateException;
import com.coutinho.adayane.storesapi.model.exception.LojaValidateException;
import com.coutinho.adayane.storesapi.repository.CepRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CepService {

    @Autowired
    private CepRepository cepRepository;

    public void createCep(Cep newCep) {
        if(cepRepository.faixaEstaContidaEmFaixaExiste(newCep.getFaixaInicio(), newCep.getFaixaFim()) == false){
            log.info("Criando cadastro da Loja " + newCep.getCodigoLoja() + " com o intervalo de Faixas:" + newCep.getFaixaInicio() + " / " + newCep.getFaixaFim());
            cepRepository.save(newCep);
        }else{
            log.info("Erro ao cadastrar Loja. Intervalo de Cep já existente. Inicio faixa:" + newCep.getFaixaInicio() + " Fim faixa: " + newCep.getFaixaFim());
            throw new FaixaValidateException("Essa faixa já está cadastrada em outra loja..");
        }
    }

    public List<Cep> listAll() {
        log.info("Listando todas as Lojas cadastradas.");
        List<Cep> allCep = cepRepository.findAll();

        return allCep;
    }

    public Optional<Cep> findById(Long id) {
        log.info("Buscando Loja com id: " + id);
        Optional<Cep> optional = cepRepository.findById(id);

        return optional;
    }

    public void updateById(Long id, Cep newCep) {
        Optional<Cep> optional = cepRepository.findById(id);

        if(optional.isPresent()){
            if(cepRepository.atualizaFaixaVerificaEstaContida(id,newCep.getFaixaInicio(), newCep.getFaixaFim()) == false) {
                log.info("Atualizando Loja com id: " + id);
                Cep oldcep = optional.get();
                oldcep.setFaixaFim(newCep.getFaixaFim());
                oldcep.setFaixaInicio(newCep.getFaixaInicio());
                oldcep.setCodigoLoja(newCep.getCodigoLoja());
                cepRepository.save(oldcep);
            }else{
                log.info("Erro ao atualizars Loja. Intervalo de Cep já existente. Inicio faixa: " + newCep.getFaixaInicio() +  " Fim faixa: " + newCep.getFaixaFim());
                throw new FaixaValidateException("Essa faixa já está cadastrada em outra Loja.");
            }
        }
    }

    public void delete(Long id) {

        if (findById(id).isEmpty()){
            log.info("Erro ao deletar. Loja de id: " + id + " não cadastrada.");
            throw new LojaValidateException("Não existe Loja cadastrada com esse id: " +id);
        }

        log.info("Deletando registro Loja de id: " + id);
        cepRepository.deleteById(id);
    }

    public String findByCep(Integer cep) {
        if(cepRepository.faixaRetornaLojafinal(cep) != null){
            log.info("Buscando Loja que se encontra na Faixa de Cep: " + cep);
            return cepRepository.faixaRetornaLojafinal(cep);
        }else{
            log.info("Loja com a Faixa de Cep: " + cep + " não encontrada.");
            throw new FaixaValidateException("Não possui Loja cadastrada nessa faixa de CEP.");
        }

    }
}
