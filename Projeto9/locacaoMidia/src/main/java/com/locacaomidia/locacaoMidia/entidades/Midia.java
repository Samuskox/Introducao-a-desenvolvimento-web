/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locacaomidia.locacaoMidia.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author Samuel
 */

@Entity
@Table(name = "midia")
public class Midia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;
    private String ano_lancamento;


    private String codigo_barras;

    @Column(name = "duracao_em_minutos")
    private int duracao_minutos;

    @ManyToOne
    @JoinColumn(name = "ator_principal")
    private Ator ator_principal;

    @ManyToOne
    @JoinColumn(name = "ator_coadjuvante")
    private Ator ator_coadjuvante;

    @ManyToOne
    @JoinColumn(name = "genero_id")
    private Genero genero;

    @ManyToOne
    @JoinColumn(name = "classificacao_etaria_id")
    private ClassificacaoEtaria classificacaoEtaria;

    @ManyToOne
    @JoinColumn(name = "tipo_id")
    private Tipo tipo;

    @ManyToOne
    @JoinColumn(name = "classificacao_interna_id")
    private ClassificacaoInterna classificacaoInterna;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAno_lancamento() {
        return ano_lancamento;
    }

    public void setAno_lancamento(String ano_lancamento) {
        this.ano_lancamento = ano_lancamento;
    }

    public String getCodigo_barras() {
        return codigo_barras;
    }

    public void setCodigo_barras(String codigo_barras) {
        this.codigo_barras = codigo_barras;
    }

    public int getDuracao_minutos() {
        return duracao_minutos;
    }

    public void setDuracao_minutos(int duracao_minutos) {
        this.duracao_minutos = duracao_minutos;
    }

    public Ator getAtor_principal() {
        return ator_principal;
    }

    public void setAtor_principal(Ator ator_principal) {
        this.ator_principal = ator_principal;
    }

    public Ator getAtor_coadjuvante() {
        return ator_coadjuvante;
    }

    public void setAtor_coadjuvante(Ator ator_coadjuvante) {
        this.ator_coadjuvante = ator_coadjuvante;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public ClassificacaoEtaria getClassificacaoEtaria() {
        return classificacaoEtaria;
    }

    public void setClassificacaoEtaria(ClassificacaoEtaria classificacaoEtaria) {
        this.classificacaoEtaria = classificacaoEtaria;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public ClassificacaoInterna getClassificacaoInterna() {
        return classificacaoInterna;
    }

    public void setClassificacaoInterna(ClassificacaoInterna classificacaoInterna) {
        this.classificacaoInterna = classificacaoInterna;
    }
}
