package locacaomidias.servicos;

import locacaomidias.dao.AtorDAO;
import locacaomidias.entidades.Ator;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AtorServices {

    public List<Ator> getTodos() {

        List<Ator> lista = new ArrayList<>();

        try ( AtorDAO dao = new AtorDAO() ) {
            lista = dao.listarTodos();
        } catch ( SQLException exc ) {
            exc.printStackTrace();
        }

        return lista;

    }

}


