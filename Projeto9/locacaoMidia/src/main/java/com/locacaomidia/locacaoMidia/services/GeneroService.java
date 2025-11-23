/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locacaomidia.locacaoMidia.services;

import com.locacaomidia.locacaoMidia.entidades.Genero;
import com.locacaomidia.locacaoMidia.repository.GeneroRepository;

import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Samuel
 */
@Service
public class GeneroService {
    private GeneroRepository generoRepository;

    public GeneroService(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    public Genero create(Genero genero){
       return generoRepository.save(genero);
    }

    public List<Genero> list(){
         return generoRepository.findAll();
    }

    public Genero update(Genero genero){
       return generoRepository.save(genero);
    }

    public void delete(int id){
        generoRepository.deleteById(id);
    }

    public Genero findByDescricao(String descricao){
        return generoRepository.findByDescricao(descricao);
    }
}
