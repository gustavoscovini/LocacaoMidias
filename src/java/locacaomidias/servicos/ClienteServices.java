/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaomidias.servicos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.dao.ClienteDAO;
import locacaomidias.entidades.Cliente;

/**
 *
 * @author gusta
 */
public class ClienteServices {
    
    public List<Cliente> getTodos() {

    List<Cliente> lista = new ArrayList<>();

    try ( ClienteDAO dao = new ClienteDAO() ) {
        lista = dao.listarTodos();
    } catch ( SQLException exc ) {
        exc.printStackTrace();
    }

    return lista;

    }
}
