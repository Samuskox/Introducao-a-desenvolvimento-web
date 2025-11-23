/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locacaomidia.locacaoMidia.controllers;

import com.locacaomidia.locacaoMidia.entidades.ClassificacaoInterna;
import com.locacaomidia.locacaoMidia.services.ClassificacaoInternaService;

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
@RequestMapping("/api/v1/classificacao-interna")
public class ClassificacaoInternaController {

    private ClassificacaoInternaService classificacaoInternaService;

    public ClassificacaoInternaController(ClassificacaoInternaService classificacaoInternaService) {
        this.classificacaoInternaService = classificacaoInternaService;
    }

    @PostMapping
    ClassificacaoInterna create(@RequestBody ClassificacaoInterna ci) {
        return classificacaoInternaService.create(ci);
    }

    @GetMapping
    List<ClassificacaoInterna> list() {
        return classificacaoInternaService.list();
    }

    @PutMapping("{id}")
    ClassificacaoInterna update(@PathVariable int id, @RequestBody ClassificacaoInterna ci) {
        ci.setId(id);
        return classificacaoInternaService.update(ci);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable("id") int id) {
        classificacaoInternaService.delete(id);
    }

}