/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locacaomidia.locacaoMidia.controllers;

import com.locacaomidia.locacaoMidia.entidades.Midia;
import com.locacaomidia.locacaoMidia.services.MidiaService;

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
@RequestMapping("/api/v1/midias")
public class MidiaController {

    private MidiaService midiaService;

    public MidiaController(MidiaService midiaService) {
        this.midiaService = midiaService;
    }

    @PostMapping
    Midia create(@RequestBody Midia midia) {
        return midiaService.create(midia);
    }

    @GetMapping
    List<Midia> list() {
        return midiaService.list();
    }

    @PutMapping("{id}")
    Midia update(@PathVariable int id, @RequestBody Midia midia) {
        midia.setId(id);
        return midiaService.update(midia);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable("id") int id) {
        midiaService.delete(id);
    }

}
