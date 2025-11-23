/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locacaomidia.locacaoMidia.services;

import com.locacaomidia.locacaoMidia.entidades.Exemplar;
import com.locacaomidia.locacaoMidia.repository.ExemplarRepository;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 *
 * @author Samuel
 */
@Service
public class ExemplarService {

    private ExemplarRepository exemplarRepository;
    
    public ExemplarService(ExemplarRepository exemplarRepository) {
        this.exemplarRepository = exemplarRepository;
    }
    
    public Exemplar create(Exemplar exemplar){
        return exemplarRepository.save(exemplar);
    }
    
    public List<Exemplar> list(){
        return exemplarRepository.findAll();
    }
    
    public Exemplar update(Exemplar exemplar){
        return exemplarRepository.save(exemplar);
    }
    
    public void delete(int id){
        exemplarRepository.deleteById(id);
    }

    public Exemplar findById(int id){
        return exemplarRepository.findById(id).orElse(null);
    }
}
