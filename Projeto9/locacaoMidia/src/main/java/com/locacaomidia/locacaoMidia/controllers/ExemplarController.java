/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locacaomidia.locacaoMidia.controllers;

import com.locacaomidia.locacaoMidia.entidades.Exemplar;
import com.locacaomidia.locacaoMidia.services.ExemplarService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Samuel
 */
@RestController
@RequestMapping("/api/v1/exemplares")
public class ExemplarController {

    private ExemplarService exemplarService;

    public ExemplarController(ExemplarService exemplarService) {
        this.exemplarService = exemplarService;
    }

    @PostMapping
    Exemplar create(@RequestBody Exemplar exemplar) {
        return exemplarService.create(exemplar);
    }

    @GetMapping("{id}")
    Exemplar findById(@PathVariable("id") int id) {
    return exemplarService.findById(id);
}

    @GetMapping
    List<Exemplar> list() {
        return exemplarService.list();
    }

    @PutMapping("{id}")
    Exemplar update(@PathVariable("id") int id, @RequestBody Exemplar exemplar) {
        exemplar.setCodigo_interno(id);
        return exemplarService.update(exemplar);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable("id") int id) {
        exemplarService.delete(id);
    }

}
