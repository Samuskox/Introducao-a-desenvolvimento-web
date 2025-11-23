/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locacaomidia.locacaoMidia.controllers;

import com.locacaomidia.locacaoMidia.entidades.Estado;
import com.locacaomidia.locacaoMidia.services.EstadoService;

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
@RequestMapping("/api/v1/estados")
public class EstadosController {

    private final EstadoService estadoService;
    
    public EstadosController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Estado create(@RequestBody Estado estado){
        return estadoService.create(estado);
    }
    
    @GetMapping
    List<Estado> list(){
        return estadoService.list();
    }
    
    @PutMapping("{id}")
    Estado update(@PathVariable("id") int id, @RequestBody Estado estado){
        estado.setId(id);
        return estadoService.update(estado);
    }
    
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable("id") int id){
        estadoService.delete(id);
    }
    
    
}
