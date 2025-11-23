/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locacaomidia.locacaoMidia.controllers;

import com.locacaomidia.locacaoMidia.entidades.Cidade;
import com.locacaomidia.locacaoMidia.services.CidadeService;

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
@RequestMapping("/api/v1/cidades")
public class CidadeController {

    private CidadeService cidadeService;

    public CidadeController(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Cidade create(@RequestBody Cidade cidade) {
        return cidadeService.create(cidade);
    }

    @GetMapping("{id}")
    Cidade findById(@PathVariable("id") int id) {
        return cidadeService.findById(id); 
    }

    @GetMapping
    List<Cidade> list() {
        return cidadeService.list();
    }

    @PutMapping("{id}")
    Cidade update(@PathVariable("id") int id, @RequestBody Cidade cidade) {
        cidade.setId(id);
        return cidadeService.update(cidade);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable("id") int id) {
        cidadeService.delete(id);
    }

}
