/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locacaomidia.locacaoMidia.services;

import com.locacaomidia.locacaoMidia.entidades.Tipo;
import com.locacaomidia.locacaoMidia.repository.TipoRepository;

import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Samuel
 */

@Service
public class TipoService {
    private TipoRepository tipoRepository;

    public TipoService(TipoRepository tipoRepository) {
        this.tipoRepository = tipoRepository;
    }

    public Tipo create(Tipo tipo){
       return tipoRepository.save(tipo);
    }

    public List<Tipo> list(){
         return tipoRepository.findAll();
    }

    public Tipo update(Tipo tipo){
       return tipoRepository.save(tipo);
    }

    public void delete(int id){
        tipoRepository.deleteById(id);
    }
}
