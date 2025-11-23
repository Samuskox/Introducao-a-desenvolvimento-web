/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locacaomidia.locacaoMidia.controllers;

import com.locacaomidia.locacaoMidia.entidades.Ator;
import com.locacaomidia.locacaoMidia.services.AtorService;

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
@RequestMapping("/api/v1/atores")
public class AtorController {

    private AtorService atorService;

    public AtorController(AtorService atorService) {
        this.atorService = atorService;
    }

    @PostMapping
    Ator create(@RequestBody Ator ator) {
        return atorService.create(ator);
    }

    @GetMapping
    List<Ator> list() {
        return atorService.list();
    }

    @PutMapping("{id}")
    Ator update(@PathVariable int id, @RequestBody Ator ator) {
        ator.setId(id);
        return atorService.update(ator);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable("id") int id) {
        atorService.delete(id);
    }

}
