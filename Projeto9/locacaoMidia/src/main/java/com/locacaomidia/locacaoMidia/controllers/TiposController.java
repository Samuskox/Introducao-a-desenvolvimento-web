/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locacaomidia.locacaoMidia.controllers;

import com.locacaomidia.locacaoMidia.entidades.Tipo;
import com.locacaomidia.locacaoMidia.services.TipoService;

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
@RequestMapping("/api/v1/tipos")
public class TiposController {

    private TipoService tipoService;

    public TiposController(TipoService tipoService) {
        this.tipoService = tipoService;
    }

    @PostMapping
    Tipo create(@RequestBody Tipo tipo) {
        return tipoService.create(tipo);
    }

    @GetMapping
    List<Tipo> list() {
        return tipoService.list();
    }

    @PutMapping("{id}")
    Tipo update(@PathVariable int id, @RequestBody Tipo tipo) {
        tipo.setId(id);
        return tipoService.update(tipo);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable("id") int id) {
        tipoService.delete(id);
    }

}
