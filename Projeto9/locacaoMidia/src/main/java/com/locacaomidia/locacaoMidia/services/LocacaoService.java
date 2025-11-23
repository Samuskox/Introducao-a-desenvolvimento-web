/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locacaomidia.locacaoMidia.services;

import com.locacaomidia.locacaoMidia.entidades.Cliente;
import com.locacaomidia.locacaoMidia.entidades.Locacao;
import com.locacaomidia.locacaoMidia.repository.ClienteRepository;
import com.locacaomidia.locacaoMidia.repository.LocacaoRepository;

import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Samuel
 */

@Service
public class LocacaoService {
    private final LocacaoRepository locacaoRepository;
    private final ClienteRepository clienteRepository;

    public LocacaoService(LocacaoRepository locacaoRepository, ClienteRepository clienteRepository) {
        this.locacaoRepository = locacaoRepository;
        this.clienteRepository = clienteRepository;
    }

    public Locacao create(Locacao locacao){
       return locacaoRepository.save(locacao);
    }

    public List<Locacao> list(){
         return locacaoRepository.findAll();
    }

    public Locacao update(Locacao locacao){
        Locacao existente = locacaoRepository.findById(locacao.getId()).orElseThrow();
        Cliente clienteGerenciado;
        if (locacao.getCliente() != null) {
            clienteGerenciado = clienteRepository.findById(locacao.getCliente().getId())
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + locacao.getCliente().getId()));

        } else {
            throw new RuntimeException("Cliente não pode ser nulo");
        }
        
        existente.setData_fim(locacao.getData_fim());
        existente.setData_inicio(locacao.getData_inicio());
        existente.setCliente(clienteGerenciado);


       return locacaoRepository.save(existente);
    }

    public void delete(int id){
        locacaoRepository.deleteById(id);
    }
}
