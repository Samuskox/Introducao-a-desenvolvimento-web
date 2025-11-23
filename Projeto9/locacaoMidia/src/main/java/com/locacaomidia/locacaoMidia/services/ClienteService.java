/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locacaomidia.locacaoMidia.services;

import org.springframework.stereotype.Service;

import com.locacaomidia.locacaoMidia.entidades.Cliente;
import com.locacaomidia.locacaoMidia.repository.ClienteRepository;

import java.util.List;

/**
 *
 * @author Samuel
 */
@Service
public class ClienteService {
    public ClienteRepository clienteRepository;
    
     public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente create(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> list() {
        return clienteRepository.findAll();
    }

    public Cliente update(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void delete(int id) {
        clienteRepository.deleteById(id);
    }
}
