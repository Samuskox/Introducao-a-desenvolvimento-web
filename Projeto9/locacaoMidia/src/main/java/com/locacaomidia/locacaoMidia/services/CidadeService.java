/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locacaomidia.locacaoMidia.services;

import com.locacaomidia.locacaoMidia.entidades.Cidade;
import com.locacaomidia.locacaoMidia.repository.CidadeRepository;
import com.locacaomidia.locacaoMidia.repository.EstadoRepository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Samuel
 */
@Service
public class CidadeService {

    private CidadeRepository cidadeRepository;
    private EstadoRepository estadoRepository;

    public CidadeService(CidadeRepository cidadeRepository, EstadoRepository estadoRepository) {
        this.cidadeRepository = cidadeRepository;
        this.estadoRepository = estadoRepository;
    }

    public Cidade create(Cidade cidade) {
        
        return cidadeRepository.save(cidade);

    }

    public Cidade findById(int id) {
        return cidadeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "Cidade não encontrada"));
    }

    public List<Cidade> list() {
        return cidadeRepository.findAll(Sort.by("Nome").ascending());
    }

    public Cidade update(Cidade cidade) {
        findById(cidade.getId());
        return cidadeRepository.save(cidade);
    }

    public void delete(int id) {
        findById(id);
        cidadeRepository.deleteById(id);
    }

}
