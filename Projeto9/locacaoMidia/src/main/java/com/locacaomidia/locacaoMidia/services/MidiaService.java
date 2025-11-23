/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locacaomidia.locacaoMidia.services;

import com.locacaomidia.locacaoMidia.entidades.Midia;
import com.locacaomidia.locacaoMidia.repository.MidiaRepository;

import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Samuel
 */

@Service
public class MidiaService {
    private MidiaRepository midiaRepository;

    public MidiaService(MidiaRepository midiaRepository) {
        this.midiaRepository = midiaRepository;
    }

    public Midia create(Midia midia){
       return midiaRepository.save(midia);
    }

    public List<Midia> list(){
         return midiaRepository.findAll();
    }

    public Midia update(Midia midia){
       return midiaRepository.save(midia);
    }

    public void delete(int id){
        midiaRepository.deleteById(id);
    }
}
