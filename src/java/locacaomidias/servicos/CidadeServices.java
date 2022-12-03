/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaomidias.servicos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.dao.CidadeDAO;
import locacaomidias.entidades.Cidade;

/**
 *
 * @author gusta
 */
public class CidadeServices {
    
    public List<Cidade> getTodos() {

    List<Cidade> lista = new ArrayList<>();

    try ( CidadeDAO dao = new CidadeDAO() ) {
        lista = dao.listarTodos();
    } catch ( SQLException exc ) {
        exc.printStackTrace();
    }

    return lista;

    }
}
