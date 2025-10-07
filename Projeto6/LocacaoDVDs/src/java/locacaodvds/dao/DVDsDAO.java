/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaodvds.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import locacaodvds.entidades.DVDs;
import java.sql.ResultSet;
import java.util.ArrayList;
import locacaodvds.entidades.Ator;
import locacaodvds.entidades.ClassificacaoEtaria;
import locacaodvds.entidades.Genero;

/**
 *
 * @author Samuel
 */
public class DVDsDAO extends DAO<DVDs>{

    public DVDsDAO()throws SQLException{}
    @Override
    public void salvar(DVDs obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(
                """
                INSERT INTO dvd (
                    titulo,
                    ano_lancamento,
                    ator_principal_id,
                    ator_coadjuvante_id,
                    data_lancamento,
                    duracao_minutos,
                    classificacao_etaria_id,
                    genero_id
                ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ? );
                """ );

        stmt.setString(1, obj.getTitulo());
        stmt.setInt(2, obj.getAnoLancamento());
        stmt.setInt(3, obj.getAtorPrincipal().getId());
        stmt.setInt(4, obj.getAtorCoadjuvante().getId());
        
        System.out.println("Ator Principal ID: " + obj.getAtorPrincipal().getId());
        System.out.println("Ator Coadjuvante ID: " + obj.getAtorCoadjuvante().getId());
        
        stmt.setString(5, obj.getDataLancamento());
        stmt.setInt(6, obj.getDuracaoMinutos());
        stmt.setInt(7, obj.getClassificacaoEtaria().getId());
        stmt.setInt(8, obj.getGenero().getId());

        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void atualizar(DVDs obj) throws SQLException {
         PreparedStatement stmt = getConnection().prepareStatement(
                """
                UPDATE dvd
                SET
                    titulo = ?,
                    ano_lancamento = ?,
                    ator_principal_id = ?,
                    ator_coadjuvante_id = ?,
                    data_lancamento = ?,
                    duracao_minutos = ?,
                    classificacao_etaria_id = ?,
                    genero_id = ?
                WHERE
                    id = ?;
                """ );

        stmt.setString(1, obj.getTitulo());
        stmt.setInt(2, obj.getAnoLancamento());
        stmt.setInt(3, obj.getAtorPrincipal().getId());
        stmt.setInt(4, obj.getAtorCoadjuvante().getId());
        stmt.setString(5, obj.getDataLancamento());
        stmt.setInt(6, obj.getDuracaoMinutos());
        stmt.setInt(7, obj.getClassificacaoEtaria().getId());
        stmt.setInt(8, obj.getGenero().getId());
        stmt.setInt(9, obj.getId());

        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void excluir(DVDs obj) throws SQLException {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(
                """
                DELETE FROM dvd
                WHERE id = ?;
                """ );

        stmt.setInt(1, obj.getId());
        stmt.executeUpdate();
        stmt.close();
        } catch (SQLIntegrityConstraintViolationException e) {
           e.printStackTrace();
        }
        
    }

    @Override
    public List<DVDs> listarTodos() throws SQLException {
        List<DVDs> lista = new ArrayList<>();
        PreparedStatement stmt = getConnection().prepareStatement(
                """
                SELECT
                    d.id id,
                    d.titulo titulo,
                    d.ano_lancamento anoLancamento,
                    d.data_lancamento dataLancamento,
                    d.duracao_minutos duracao,
                    ap.id idAtorPrincipal,
                    ap.nome nomeAtorPrincipal,
                    ac.id idAtorCoadjuvante,
                    ac.nome nomeAtorCoadjuvante,
                    ce.id idClassificacao,
                    ce.descricao descricaoClassificacao,
                    g.id idGenero,
                    g.descricao descricaoGenero
                
                FROM
                    dvd d,
                    ator ap,
                    ator ac,
                    classificacao_etaria ce,
                    genero g
                
                WHERE
                    d.ator_principal_id = ap.id AND
                    d.ator_coadjuvante_id = ac.id AND
                    d.classificacao_etaria_id = ce.id AND
                    d.genero_id = g.id
                
                ORDER BY
                    d.titulo, g.descricao, ce.descricao;
                """ );

        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            DVDs dvd = new DVDs();
            Ator atorPrincipal = new Ator();
            Ator atorCoadjuvante = new Ator();
            ClassificacaoEtaria classificacao = new ClassificacaoEtaria();
            Genero genero = new Genero();
            
            dvd.setId(resultSet.getInt("id"));
            dvd.setTitulo(resultSet.getString("titulo"));
            dvd.setAnoLancamento(resultSet.getInt("anoLancamento"));
            
            
            atorPrincipal.setId(resultSet.getInt("idAtorPrincipal"));
            atorPrincipal.setNome( resultSet.getString("nomeAtorPrincipal"));
            dvd.setAtorPrincipal(atorPrincipal);
            
            atorCoadjuvante.setId(resultSet.getInt("idAtorCoadjuvante"));
            atorCoadjuvante.setNome( resultSet.getString("nomeAtorCoadjuvante"));
            dvd.setAtorCoadjuvante(atorCoadjuvante);
            
            dvd.setDataLancamento(resultSet.getString("dataLancamento"));
            dvd.setDuracaoMinutos(resultSet.getInt("duracao"));
            
            classificacao.setId(resultSet.getInt("idClassificacao"));
            classificacao.setDescricao( resultSet.getString("descricaoClassificacao"));
            dvd.setClassificacaoEtaria(classificacao);
            
            genero.setId(resultSet.getInt("idGenero"));
            genero.setDescricao( resultSet.getString("descricaoGenero"));
            dvd.setGenero(genero);

            lista.add(dvd);
        }

        resultSet.close();
        stmt.close();

        return lista;
    }

    @Override
    public DVDs obterPorId(int id) throws SQLException {
        
        DVDs dvd = null;

        PreparedStatement stmt = getConnection().prepareStatement(
                """
                SELECT
                    d.id id,  
                    d.titulo titulo,  
                    d.ano_lancamento anoLancamento,  
                    d.data_lancamento dataLancamento,  
                    d.duracao_minutos duracaoMinutos,  
                
                    ap.id idAtorPrincipal,  
                    ap.nome nomeAtorPrincipal,  
                
                    ac.id idAtorCoadjuvante,  
                    ac.nome nomeAtorCoadjuvante,  
                
                    ce.id idClassificacaoEtaria,  
                    ce.descricao descricaoClassificacaoEtaria,  
                
                    g.id idGenero,  
                    g.descricao descricaoGenero  
                FROM  
                    dvd d,  
                    ator ap, 
                    ator ac,
                    classificacao_etaria ce,  
                    genero g  
                
                WHERE  
                    d.id = ? AND  
                    d.ator_principal_id = ap.id AND  
                    d.ator_coadjuvante_id = ac.id AND  
                    d.classificacao_etaria_id = ce.id AND  
                    d.genero_id = g.id;
                """ );

        stmt.setInt(1, id);
        ResultSet resultSet = stmt.executeQuery();

        if (resultSet.next()) {
            dvd = new DVDs();
            Ator atorPrincipal = new Ator();
            Ator atorCoadjuvante = new Ator();
            ClassificacaoEtaria classificacao = new ClassificacaoEtaria();
            Genero genero = new Genero();
            
            
            dvd.setId(resultSet.getInt("id"));
            dvd.setTitulo(resultSet.getString("titulo"));
            dvd.setAnoLancamento(resultSet.getInt("anoLancamento"));
            
            
            atorPrincipal.setId(resultSet.getInt("idAtorPrincipal"));
            atorPrincipal.setNome( resultSet.getString("nomeAtorPrincipal"));
            dvd.setAtorPrincipal(atorPrincipal);
            
            atorCoadjuvante.setId(resultSet.getInt("idAtorCoadjuvante"));
            atorCoadjuvante.setNome( resultSet.getString("nomeAtorCoadjuvante"));
            dvd.setAtorCoadjuvante(atorCoadjuvante);
            
            dvd.setDataLancamento(resultSet.getString("dataLancamento"));
            dvd.setDuracaoMinutos(resultSet.getInt("duracaoMinutos"));
            
            classificacao.setId(resultSet.getInt("idClassificacaoEtaria"));
            classificacao.setDescricao(resultSet.getString("descricaoClassificacaoEtaria"));
            dvd.setClassificacaoEtaria(classificacao);
            
            genero.setId(resultSet.getInt("idGenero"));
            genero.setDescricao(resultSet.getString("descricaoGenero"));
            dvd.setGenero(genero);
        }

        resultSet.close();
        stmt.close();

        return dvd;
    }
    
}
