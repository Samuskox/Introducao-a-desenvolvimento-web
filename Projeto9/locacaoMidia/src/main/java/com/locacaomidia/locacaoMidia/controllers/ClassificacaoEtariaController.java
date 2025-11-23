/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locacaomidia.locacaoMidia.controllers;

import com.locacaomidia.locacaoMidia.entidades.ClassificacaoEtaria;
import com.locacaomidia.locacaoMidia.services.ClassificacaoEtariaService;

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
@RequestMapping("/api/v1/classificacao-etaria")
public class ClassificacaoEtariaController {

    private ClassificacaoEtariaService classificacaoEtariaService;

    public ClassificacaoEtariaController(ClassificacaoEtariaService classificacaoEtariaService) {
        this.classificacaoEtariaService = classificacaoEtariaService;
    }

    @PostMapping
    ClassificacaoEtaria create(@RequestBody ClassificacaoEtaria ce) {
        return classificacaoEtariaService.create(ce);
    }

    @GetMapping
    List<ClassificacaoEtaria> list() {
        return classificacaoEtariaService.list();
    }

    @PutMapping("{id}")
    ClassificacaoEtaria update(@PathVariable int id, @RequestBody ClassificacaoEtaria ce) {
        ce.setId(id);
        return classificacaoEtariaService.update(ce);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable("id") int id) {
        classificacaoEtariaService.delete(id);
    }

}