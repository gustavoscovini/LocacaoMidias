/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaomidias.servicos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.dao.LocacaoDAO;
import locacaomidias.entidades.Locacao;

/**
 *
 * @author gusta
 */
public class LocacaoServices {
    
    public List<Locacao> getTodos() {

    List<Locacao> lista = new ArrayList<>();

    try ( LocacaoDAO dao = new LocacaoDAO() ) {
        lista = dao.listarTodos();
    } catch ( SQLException exc ) {
        exc.printStackTrace();
    }

    return lista;

    }
}
