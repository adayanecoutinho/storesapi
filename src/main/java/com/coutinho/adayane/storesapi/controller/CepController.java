package com.coutinho.adayane.storesapi.controller;

import com.coutinho.adayane.storesapi.model.Cep;
import com.coutinho.adayane.storesapi.model.exception.FaixaValidateException;
import com.coutinho.adayane.storesapi.service.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cep")
public class CepController {

    @Autowired
    private CepService cepService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCep(@RequestBody @Valid Cep newCep){
        try {
            cepService.createCep(newCep);
        }catch (FaixaValidateException e){
            throw new FaixaValidateException(e.getMessage());
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
     public List<Cep> listAll(){
        return cepService.listAll();
     }

     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public ResponseEntity<Cep> findById(@PathVariable Long id){
         Optional<Cep> optional = cepService.findById(id);

         if(optional.isEmpty()){
             return ResponseEntity.notFound().build();
         }

         return ResponseEntity.ok().body(optional.get());
     }

     @GetMapping("/lojas/{cep}")
     @ResponseStatus(HttpStatus.OK)
     public String findByCep(@PathVariable Integer cep){
        return cepService.findByCep(cep);
     }

     @PutMapping("/{id}")
     public void updateById(@PathVariable Long id, @RequestBody @Valid Cep newCep){
         try {
             cepService.updateById(id,newCep);
         }catch (FaixaValidateException e){
             throw new FaixaValidateException(e.getMessage());
         }
     }

     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public void deleteById(@PathVariable Long id){
        cepService.delete(id);
     }

}
