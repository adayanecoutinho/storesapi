package com.coutinho.adayane.storesapi.controller;

import com.coutinho.adayane.storesapi.entity.Cep;
import com.coutinho.adayane.storesapi.service.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cep")
public class CepController {

    @Autowired
    private CepService cepService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCep(@RequestBody @Valid Cep newCep){
        cepService.createCep(newCep);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
     public List<Cep> listAll(){
        return cepService.listAll();
     }

     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public ResponseEntity findById(@PathVariable Long id){
        return cepService.findById(id);
     }

     @GetMapping("/lojas/{cep}")
     @ResponseStatus(HttpStatus.OK)
     public String findByCep(@PathVariable Integer cep){
        return cepService.findByCep(cep);
     }

     @PutMapping("/{id}")
     public ResponseEntity<Cep> updateById(@PathVariable Long id, @RequestBody @Valid Cep newCep){
        return cepService.updateById(id,newCep);
     }

     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public void deleteById(@PathVariable Long id){
        cepService.delete(id);
     }

}
