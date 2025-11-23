/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locacaomidia.locacaoMidia.entidades;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

/**
 *
 * @author Samuel
 */

@Entity
@Table(name = "item_locacao")
public class ItemLocacao {
    
    @EmbeddedId
    private ItemLocacaoId id;

    public ItemLocacao() {
        this.id = new ItemLocacaoId();
    }

    
    
    @ManyToOne
    @MapsId("locacaoId")
    @JoinColumn(name = "locacao_id")
    private Locacao locacao;
    
    @ManyToOne
    @MapsId("exemplarCodigoInterno")
    @JoinColumn(name = "exemplar_codigo_interno")
    private Exemplar exemplar;
    private double valor;
    
    public ItemLocacaoId getId() {
        return id;
    }

    public void setId(ItemLocacaoId id) {
        this.id = id;
    }

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
