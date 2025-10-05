/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaodvds.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        
        PreparedStatement stmt = getConnection().prepareStatement(
                """
                DELETE FROM dvd
                WHERE id = ?;
                """ );

        stmt.setInt(1, obj.getId());
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public List<DVDs> listarTodos() throws SQLException {
        List<DVDs> lista = new ArrayList<>();
        PreparedStatement stmt = getConnection().prepareStatement(
                """
                SELECT
                    d.id idDvd,
                    d.titulo tituloDvd,
                    d.ano_lancamento anoLancamentoDvd,
                    d.data_lancamento dataLancamentoDvd,
                    d.duracao_minutos duracaoDvd,
                    a.id idAtorPrincipal,
                    a.nome nomeAtorPrincipal,
                    a.id idAtorCoadjuvante,
                    a.nome nomeAtorCoadjuvante,
                    ce.id idClassificacao,
                    ce.descricao descricaoClassificacao,
                    g.id idGenero,
                    g.descricao descricaoGenero
                
                FROM
                    dvd d,
                    ator a,
                    classificacao_etaria ce,
                    genero g
                
                WHERE
                    d.ator_principal_id = a.id AND
                    d.ator_coadjuvante_id = a.id AND
                    d.classificacao_etaria_id = ce.id AND
                    d.genero_id = g.id
                
                ORDER BY
                    d.titulo, g.descricao, ce.descricao;
                """ );

        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            DVDs dvd = new DVDs();
            Ator ator = new Ator();
            ClassificacaoEtaria classificacao = new ClassificacaoEtaria();
            Genero genero = new Genero();
            
            dvd.setId(resultSet.getInt("id"));
            dvd.setTitulo(resultSet.getString("titulo"));
            dvd.setAnoLancamento(resultSet.getInt("ano_lancamento"));
            
            
            ator.setId(resultSet.getInt("ator_principal_id"));
            dvd.setAtorPrincipal(ator);
            ator.setId(resultSet.getInt("ator_coadjuvante_id"));
            dvd.setAtorCoadjuvante(ator);
            
            dvd.setDataLancamento(resultSet.getString("data_lancamento"));
            dvd.setDuracaoMinutos(resultSet.getInt("duracao_minutos"));
            
            classificacao.setId(resultSet.getInt("classificacao_etaria_id"));
            dvd.setClassificacaoEtaria(classificacao);
            
            genero.setId(resultSet.getInt("genero_id"));
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
                    d.id idDvd,  
                    d.titulo tituloDvd,  
                    d.ano_lancamento anoLancamentoDvd,  
                    d.data_lancamento dataLancamentoDvd,  
                    d.duracao_minutos duracaoMinutosDvd,  
                
                    a.id idAtorPrincipal,  
                    a.nome nomeAtorPrincipal,  
                
                    a.id idAtorCoadjuvante,  
                    a.nome nomeAtorCoadjuvante,  
                
                    ce.id idClassificacaoEtaria,  
                    ce.descricao descricaoClassificacaoEtaria,  
                
                    g.id idGenero,  
                    g.descricao descricaoGenero  
                FROM  
                    dvd d,  
                    ator a,  
                    classificacao_etaria ce,  
                    genero g  
                
                WHERE  
                    d.id = ? AND  
                    d.ator_principal_id = a.id AND  
                    d.ator_coadjuvante_id = a.id AND  
                    d.classificacao_etaria_id = ce.id AND  
                    d.genero_id = g.id;
                """ );

        stmt.setInt(1, id);
        ResultSet resultSet = stmt.executeQuery();

        if (resultSet.next()) {
            dvd = new DVDs();
            Ator ator = new Ator();
            ClassificacaoEtaria classificacao = new ClassificacaoEtaria();
            Genero genero = new Genero();
            
            
            dvd.setId(resultSet.getInt("id"));
            dvd.setTitulo(resultSet.getString("titulo"));
            dvd.setAnoLancamento(resultSet.getInt("ano_lancamento"));
            
            
            ator.setId(resultSet.getInt("ator_principal_id"));
            dvd.setAtorPrincipal(ator);
            ator.setId(resultSet.getInt("ator_coadjuvante_id"));
            dvd.setAtorCoadjuvante(ator);
            
            dvd.setDataLancamento(resultSet.getString("data_lancamento"));
            dvd.setDuracaoMinutos(resultSet.getInt("duracao_minutos"));
            
            classificacao.setId(resultSet.getInt("classificacao_etaria_id"));
            dvd.setClassificacaoEtaria(classificacao);
            
            genero.setId(resultSet.getInt("genero_id"));
            dvd.setGenero(genero);
        }

        resultSet.close();
        stmt.close();

        return dvd;
    }
    
}
