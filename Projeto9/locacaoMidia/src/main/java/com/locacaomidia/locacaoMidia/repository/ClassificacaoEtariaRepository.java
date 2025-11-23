/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.locacaomidia.locacaoMidia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.locacaomidia.locacaoMidia.entidades.ClassificacaoEtaria;

/**
 *
 * @author Samuel
 */

@Repository
public interface ClassificacaoEtariaRepository extends JpaRepository<ClassificacaoEtaria, Integer> {
    ClassificacaoEtaria findByDescricao(String descricao);
}
