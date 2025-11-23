/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.locacaomidia.locacaoMidia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.locacaomidia.locacaoMidia.entidades.Genero;

/**
 *
 * @author Samuel
 */
public interface GeneroRepository extends JpaRepository<Genero, Integer> {
    Genero findByDescricao(String descricao);
}
