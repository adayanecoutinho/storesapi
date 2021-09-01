package com.coutinho.adayane.storesapi.controller;

import com.coutinho.adayane.storesapi.dto.request.LojaDto;
import com.coutinho.adayane.storesapi.model.Loja;
import com.coutinho.adayane.storesapi.model.exception.CepNotFoundException;
import com.coutinho.adayane.storesapi.model.exception.FaixaValidationException;
import com.coutinho.adayane.storesapi.model.exception.LojaNotFoundException;
import com.coutinho.adayane.storesapi.service.LojaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cep")
@Slf4j
@RequiredArgsConstructor
public class LojaController {

    private final LojaService lojaService;

    @PostMapping
    public Loja createCep(@RequestBody @Valid LojaDto dto) {
        try {
            return lojaService.createCep(dto);
        } catch (FaixaValidationException e) {
            throw new ResponseStatusException(HttpStatus.CREATED, e.getMessage(), e);
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Loja> listAll(Pageable pageable) {
        return lojaService.listAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loja> findById(@PathVariable long id) {
        Optional<Loja> optional = lojaService.findById(id);

        try {
            return ResponseEntity.ok().body(optional.get());
        } catch (CepNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }

    }

    @GetMapping("/lojas/{cep}")
    @ResponseStatus(HttpStatus.OK)
    public String findByCep(@PathVariable int cep) {
        try {
            return lojaService.findByCep(cep);
        } catch (LojaNotFoundException e) {
            throw new LojaNotFoundException(e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public Loja updateById(@PathVariable Long id, @RequestBody @Valid LojaDto newCep) {
        try {
            return lojaService.updateById(id, newCep);
        } catch (FaixaValidationException e) {
            throw new FaixaValidationException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        try {
            lojaService.delete(id);
        } catch (CepNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

}
