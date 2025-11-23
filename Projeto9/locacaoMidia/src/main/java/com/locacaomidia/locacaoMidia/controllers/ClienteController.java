/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locacaomidia.locacaoMidia.controllers;

import com.locacaomidia.locacaoMidia.entidades.Cliente;
import com.locacaomidia.locacaoMidia.services.ClienteService;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Samuel
 */

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {
    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    Cliente create(@RequestBody Cliente cliente) {
        return clienteService.create(cliente);
    }

    @GetMapping
    List<Cliente> list() {
        return clienteService.list();
    }

    @PutMapping("{id}")
    Cliente update(@PathVariable("id") int id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        return clienteService.update(cliente);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable("id") int id) {
        clienteService.delete(id);
    }
}
