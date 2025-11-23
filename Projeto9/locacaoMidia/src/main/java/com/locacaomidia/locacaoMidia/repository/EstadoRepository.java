/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locacaomidia.locacaoMidia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.locacaomidia.locacaoMidia.entidades.Estado;

/**
 *
 * @author Samuel
 */
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{
    // O JpaRepository já fornece métodos como:
    // - save(Estado estado): salva ou atualiza um estado
    // - findById(Integer id): busca um estado pelo ID
    // - findAll(): busca todos os estados
    // - delete(Estado estado): deleta um estado
    
    //pode adicionar métodos personalizados aqui seguindo o padrão de nomenclatura do Spring Data
    //Estado findBySigla(String sigla); //achar estado pela sigla
    //Estado findByNome(String nome); // achar estado pelo nome
    

    //@Transactional // Necessário para operações de modificação
    //@Modifying
    //@Query("UPDATE Estado e SET e.nome = :novoNome WHERE e.id = :estadoId")
    //void updateNome(@Param("estadoId") int id, @Param("novoNome") String nome);
}
