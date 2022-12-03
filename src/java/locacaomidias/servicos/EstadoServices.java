/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaomidias.servicos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.dao.EstadoDAO;
import locacaomidias.entidades.Estado;

/**
 *
 * @author gusta
 */
public class EstadoServices {
    
    public List<Estado> getTodos() {

    List<Estado> lista = new ArrayList<>();

    try ( EstadoDAO dao = new EstadoDAO() ) {
        lista = dao.listarTodos();
    } catch ( SQLException exc ) {
        exc.printStackTrace();
    }

    return lista;

    }
}
