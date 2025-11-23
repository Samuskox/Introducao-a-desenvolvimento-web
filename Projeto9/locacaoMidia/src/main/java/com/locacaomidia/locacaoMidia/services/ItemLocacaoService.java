/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locacaomidia.locacaoMidia.services;

import com.locacaomidia.locacaoMidia.entidades.Exemplar;
import com.locacaomidia.locacaoMidia.entidades.ItemLocacao;
import com.locacaomidia.locacaoMidia.entidades.ItemLocacaoId;
import com.locacaomidia.locacaoMidia.entidades.Locacao;
import com.locacaomidia.locacaoMidia.repository.ExemplarRepository;
import com.locacaomidia.locacaoMidia.repository.ItemLocacaoRepository;
import com.locacaomidia.locacaoMidia.repository.LocacaoRepository;

import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Samuel
 */

@Service
public class ItemLocacaoService {
    private final ItemLocacaoRepository itemLocacaoRepository;
    private final ExemplarRepository exemplarRepository;
    private final LocacaoRepository locacaoRepository;

    public ItemLocacaoService(  
        ItemLocacaoRepository itemLocacaoRepository,
        ExemplarRepository exemplarRepository,
        LocacaoRepository locacaoRepository) 
        {
        this.itemLocacaoRepository = itemLocacaoRepository;
        this.exemplarRepository = exemplarRepository;
        this.locacaoRepository = locacaoRepository;
    }

    public ItemLocacao create(ItemLocacao item){
        ItemLocacaoId id = item.getId();
        Locacao locacao = locacaoRepository.findById(id.getLocacaoId()).orElseThrow(() -> new RuntimeException("Locacao não encontrada"));
        Exemplar exemplar = exemplarRepository.findById(id.getExemplarCodigoInterno()).orElseThrow(() -> new RuntimeException("Exemplar não encontrado"));
        
        item.setLocacao(locacao);
        item.setExemplar(exemplar);
        
       return itemLocacaoRepository.save(item);
    }

    public List<ItemLocacao> list(){
         return itemLocacaoRepository.findAll();
    }

    public ItemLocacao update(ItemLocacao item){
        ItemLocacaoId id = item.getId();
        
        Locacao locacao = locacaoRepository.findById(id.getLocacaoId()).orElseThrow(() -> new RuntimeException("Locacao não encontrada"));
        Exemplar exemplar = exemplarRepository.findById(id.getExemplarCodigoInterno()).orElseThrow(() -> new RuntimeException("Exemplar não encontrado"));


         

        item.setLocacao(locacao);
        item.setExemplar(exemplar);

        return itemLocacaoRepository.save(item);
    }

    public void delete(ItemLocacaoId id){
        itemLocacaoRepository.deleteById(id);
    }
}
