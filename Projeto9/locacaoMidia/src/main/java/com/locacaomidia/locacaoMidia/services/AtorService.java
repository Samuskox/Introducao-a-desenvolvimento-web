/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locacaomidia.locacaoMidia.services;

import com.locacaomidia.locacaoMidia.entidades.Ator;
import com.locacaomidia.locacaoMidia.repository.AtorRepository;

import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Samuel
 */
@Service
public class AtorService {
    
    private AtorRepository atorRepository;
    
    public AtorService(AtorRepository atorRepository) {
        this.atorRepository = atorRepository;
    }
    
    public Ator create(Ator ator){
        return atorRepository.save(ator);
    }
    
    public List<Ator> list(){
        return atorRepository.findAll();
    }
    
    public Ator update(Ator ator){
        return atorRepository.save(ator);
    }
    
    public void delete(int id){
        atorRepository.deleteById(id);
    }
    
}
