/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locacaomidia.locacaoMidia.services;

import com.locacaomidia.locacaoMidia.entidades.ClassificacaoEtaria;
import com.locacaomidia.locacaoMidia.repository.ClassificacaoEtariaRepository;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 *
 * @author Samuel
 */
@Service
public class ClassificacaoEtariaService {
    private ClassificacaoEtariaRepository classificacaoEtariaRepository;

    public ClassificacaoEtariaService(ClassificacaoEtariaRepository classificacaoEtariaRepository) {
        this.classificacaoEtariaRepository = classificacaoEtariaRepository;
    }
    
    public ClassificacaoEtaria create(ClassificacaoEtaria classificacaoEtaria){
       return classificacaoEtariaRepository.save(classificacaoEtaria);
    }
    
    public List<ClassificacaoEtaria> list(){
        //Ordeno ou não??, tem como?????
         return classificacaoEtariaRepository.findAll();
    }
    
    public ClassificacaoEtaria update(ClassificacaoEtaria classificacaoEtaria){
       return classificacaoEtariaRepository.save(classificacaoEtaria);
    }
    
    public void delete(int id){
        classificacaoEtariaRepository.deleteById(id);
    }
}
