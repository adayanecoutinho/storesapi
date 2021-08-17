package com.coutinho.adayane.storesapi.service;

import com.coutinho.adayane.storesapi.entity.Cep;
import com.coutinho.adayane.storesapi.repository.CepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CepService {

    @Autowired
    private CepRepository cepRepository;

    public void createCep(Cep newCep) {
        if(cepRepository.faixaEstaContidaEmFaixaExiste(newCep.getFaixaInicio(), newCep.getFaixaFim()) == false){
            cepRepository.save(newCep);
        }
    }

    public List<Cep> listAll() {
        List<Cep> allCep = cepRepository.findAll();

        return allCep;
    }

    public ResponseEntity<Cep> findById(Long id) {
        Optional<Cep> optional = cepRepository.findById(id);

        if(optional.isPresent()){
            return ResponseEntity.ok().body(optional.get());
        }else {
            return ResponseEntity.notFound().build();
        }

    }

    public ResponseEntity<Cep> updateById(Long id, Cep newCep) {
        Optional<Cep> optional = cepRepository.findById(id);

        if(optional.isPresent()){
            Cep oldcep = optional.get();
            oldcep.setFaixaFim(newCep.getFaixaFim());
            oldcep.setFaixaInicio(newCep.getFaixaInicio());
            oldcep.setCodigoLoja(newCep.getCodigoLoja());
            cepRepository.save(oldcep);
            return ResponseEntity.ok().body(optional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    public void delete(Long id) {
        cepRepository.deleteById(id);
    }

    public String findByCep(Integer cep) {
        return cepRepository.faixaRetornaLojafinal(cep);
    }
}
