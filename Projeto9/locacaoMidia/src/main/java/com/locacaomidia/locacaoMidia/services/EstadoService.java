/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locacaomidia.locacaoMidia.services;

import com.locacaomidia.locacaoMidia.entidades.Estado;
import com.locacaomidia.locacaoMidia.repository.EstadoRepository;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Samuel
 */
@Service
public class EstadoService {
    private EstadoRepository estadoRepository;

    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public Estado findById(int id) {
    return estadoRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, 
                    "Estado não encontrado com o ID: " + id
            ));
    }
    
    public Estado create(Estado estado){
       return estadoRepository.save(estado);
    }
    
    public List<Estado> list(){
        //Ordeno ou não??, tem como?????
         return estadoRepository.findAll();
    }
    
    public Estado update(Estado estado){
        Estado estadoExistente = findById(estado.getId());
        estadoExistente.setId(estado.getId());
        estadoExistente.setNome(estado.getNome());
        estadoExistente.setSigla(estado.getSigla());
        estadoRepository.save(estadoExistente);

        // return estadoRepository.save(estadoExistente); seria pra retornar o estado atualizado mas não funciona o update
        return findById(estado.getId());
    }
    
    public void delete(int id){
        findById(id);
        estadoRepository.deleteById(id);
    }
    

}
