/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.locacaomidia.locacaoMidia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.locacaomidia.locacaoMidia.entidades.Midia;

/**
 *
 * @author Samuel
 */
public interface MidiaRepository extends JpaRepository<Midia, Integer> {
    
}
