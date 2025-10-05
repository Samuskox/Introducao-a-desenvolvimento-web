/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaodvds.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaodvds.entidades.ClassificacaoEtaria;
import locacaodvds.entidades.Genero;

/**
 *
 * @author Samuel
 */
public class ClassificacaoDAO extends DAO<ClassificacaoEtaria> {

    public ClassificacaoDAO() throws SQLException {}
    
    @Override
    public void salvar(ClassificacaoEtaria obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(
                """
                INSERT INTO  
                classificao_etaria( descricao )  
                VALUES( ? );
                """ );
        
        stmt.setString( 1, obj.getDescricao());


        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void atualizar(ClassificacaoEtaria obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(
                """
                UPDATE classificacao_etaria 
                SET 
                    descricao = ?
                WHERE 
                    id = ?;
                """ );
        
        stmt.setString( 1, obj.getDescricao() );
        stmt.setInt( 2, obj.getId() );

        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void excluir(ClassificacaoEtaria obj) throws SQLException {
         PreparedStatement stmt = getConnection().prepareStatement(
                """
                DELETE FROM classificacao_etaria
                WHERE 
                    id = ?;
                """ );
        
        stmt.setInt( 1, obj.getId() );

        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public List<ClassificacaoEtaria> listarTodos() throws SQLException {
        List<ClassificacaoEtaria> lista = new ArrayList<>();
        PreparedStatement stmt = getConnection().prepareStatement(
                """
                SELECT 
                    * 
                FROM
                    classificacao_etaria
                """ );
        
        ResultSet resultSet = stmt.executeQuery();
        
        while ( resultSet.next() ) {



            ClassificacaoEtaria classificacao = new ClassificacaoEtaria();

            classificacao.setId( resultSet.getInt("id"));
            classificacao.setDescricao( resultSet.getString("descricao"));
            

            lista.add( classificacao );

        }
        resultSet.close();
        stmt.close();

        return lista;
    }

    @Override
    public ClassificacaoEtaria obterPorId(int id) throws SQLException {
        ClassificacaoEtaria classificacao = null;
        
         PreparedStatement stmt = getConnection().prepareStatement(
                """
                SELECT 
                    * 
                FROM
                    classificacao_etaria
                WHERE 
                    id = ?
                """ );

        stmt.setInt( 1, id );

        ResultSet resultSet = stmt.executeQuery();

        if ( resultSet.next() ) {
            
            classificacao = new ClassificacaoEtaria();

            classificacao.setId( resultSet.getInt("id"));
            classificacao.setDescricao( resultSet.getString("descricao"));
        }

        resultSet.close();
        stmt.close();

        return classificacao;
        }
    }
    
