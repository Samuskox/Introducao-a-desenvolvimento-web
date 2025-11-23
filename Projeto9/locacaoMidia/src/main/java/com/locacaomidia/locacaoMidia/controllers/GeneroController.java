/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locacaomidia.locacaoMidia.controllers;

import com.locacaomidia.locacaoMidia.entidades.Genero;
import com.locacaomidia.locacaoMidia.services.GeneroService;

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
@RequestMapping("/api/v1/generos")
public class GeneroController {
    
    private GeneroService generoService;

    public GeneroController(GeneroService generoService) {
        this.generoService = generoService;
    }

    @PostMapping
    Genero create(@RequestBody Genero genero) {
        return generoService.create(genero);
    }

    @GetMapping
    List<Genero> list() {
        return generoService.list();
    }

    @PutMapping("{id}")
    Genero update(@PathVariable int id, @RequestBody Genero genero) {
        genero.setId(id);
        return generoService.update(genero);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable("id") int id) {
        generoService.delete(id);
    }
}
