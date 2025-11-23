/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locacaomidia.locacaoMidia.controllers;

import com.locacaomidia.locacaoMidia.entidades.ItemLocacao;
import com.locacaomidia.locacaoMidia.entidades.ItemLocacaoId;
import com.locacaomidia.locacaoMidia.services.ExemplarService;
import com.locacaomidia.locacaoMidia.services.ItemLocacaoService;

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
@RequestMapping("/api/v1/item-locacoes")
public class ItemLocacaoController {

    private ItemLocacaoService itemLocacaoService;

    public ItemLocacaoController(ItemLocacaoService itemLocacaoService) {
        this.itemLocacaoService = itemLocacaoService;
    }

    @PostMapping
    ItemLocacao create(@RequestBody ItemLocacao item) {

        System.out.println("Chegou no controller: " + item.getId().getLocacaoId() + " - " + item.getId().getExemplarCodigoInterno());
        return itemLocacaoService.create(item);
    }

    @GetMapping
    List<ItemLocacao> list() {
        return itemLocacaoService.list();
    }

    @PutMapping("{locacaoId}/{exemplarCodigoInterno}")
    ItemLocacao update(
        @PathVariable Integer locacaoId,
        @PathVariable("exemplarCodigoInterno") Integer exemplarId, 
        @RequestBody ItemLocacao item ) {

        ItemLocacaoId id = new ItemLocacaoId();
        id.setLocacaoId(locacaoId);
        id.setExemplarCodigoInterno(exemplarId);

        item.setId(id);

        return itemLocacaoService.update(item);
    }

    @DeleteMapping("{locacaoId}/{exemplarCodigoInterno}")
    void delete(@PathVariable int locacaoId, @PathVariable int exemplarCodigoInterno) {
        ItemLocacaoId id = new ItemLocacaoId();
        id.setLocacaoId(locacaoId);
        id.setExemplarCodigoInterno(exemplarCodigoInterno);
        itemLocacaoService.delete(id);
    }

}
