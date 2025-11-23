/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locacaomidia.locacaoMidia.services;

import com.locacaomidia.locacaoMidia.entidades.ClassificacaoInterna;
import com.locacaomidia.locacaoMidia.repository.ClassificacaoInternaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Samuel
 */
@Service
public class ClassificacaoInternaService {
    private ClassificacaoInternaRepository classificacaoInternaRepository;

    public ClassificacaoInternaService(ClassificacaoInternaRepository classificacaoInternaRepository) {
        this.classificacaoInternaRepository = classificacaoInternaRepository;
    }
    
    public ClassificacaoInterna create(ClassificacaoInterna classificacaoInterna){
       return classificacaoInternaRepository.save(classificacaoInterna);
    }
    
    public List<ClassificacaoInterna> list(){
        //Ordeno ou não??, tem como?????
         return classificacaoInternaRepository.findAll();
    }
    
    public ClassificacaoInterna update(ClassificacaoInterna classificacaoInterna){
       return classificacaoInternaRepository.save(classificacaoInterna);
    }
    
    public void delete(int id){
        classificacaoInternaRepository.deleteById(id);
    }
}
