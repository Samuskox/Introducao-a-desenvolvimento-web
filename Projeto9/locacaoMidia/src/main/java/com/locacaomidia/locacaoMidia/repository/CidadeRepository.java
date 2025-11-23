/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locacaomidia.locacaoMidia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.locacaomidia.locacaoMidia.entidades.Cidade;

/**
 *
 * @author Samuel
 */

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
    
    Cidade findByNome(String nome);
}
