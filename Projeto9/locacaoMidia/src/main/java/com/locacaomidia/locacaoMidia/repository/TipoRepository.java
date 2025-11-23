/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.locacaomidia.locacaoMidia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.locacaomidia.locacaoMidia.entidades.Tipo;

/**
 *
 * @author Samuel
 */
public interface TipoRepository extends JpaRepository<Tipo, Integer> {
    
}
