/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locacaomidia.locacaoMidia.controllers;

import com.locacaomidia.locacaoMidia.entidades.Locacao;
import com.locacaomidia.locacaoMidia.services.LocacaoService;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Samuel
 */
@RestController
@RequestMapping("/api/v1/locacoes")
public class LocacaoController {

    private LocacaoService locacaoService;

    public LocacaoController(LocacaoService locacaoService) {
        this.locacaoService = locacaoService;
    }

    @PostMapping
    Locacao create(@RequestBody Locacao locacao) {
        return locacaoService.create(locacao);
    }

    @GetMapping
    List<Locacao> list() {
        return locacaoService.list();
    }

    @PutMapping("{id}")
    Locacao update(@PathVariable int id, @RequestBody Locacao locacao) {
        locacao.setId(id);
        return locacaoService.update(locacao);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable("id") int id) {
        locacaoService.delete(id);
    }

}
