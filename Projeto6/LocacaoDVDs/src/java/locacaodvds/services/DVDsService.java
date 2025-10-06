/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaodvds.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaodvds.dao.DVDsDAO;
import locacaodvds.entidades.DVDs;

/**
 *
 * @author Samuel
 */
public class DVDsService {
    public List<DVDs> getTodos() {

        List<DVDs> lista = new ArrayList<>();
        DVDsDAO dao = null;
        
        try {
            dao = new DVDsDAO();
            lista = dao.listarTodos();
            System.out.println("dvds -> "+ lista);
        } catch ( SQLException exc ) {
            exc.printStackTrace();
        } finally {
            if ( dao != null ) {
                try {
                    dao.closeConnection();
                } catch ( SQLException exc ) {
                    exc.printStackTrace();
                }
            }
        }

        return lista;

    }
}
