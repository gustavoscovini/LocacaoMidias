package locacaomidias.servicos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.dao.MidiaDAO;
import locacaomidias.entidades.Midia;


/**
 * Classe de serviços para a entidade Produto.
 *
 * @author Prof. Dr. David Buzatto
 */
public class MidiaServices {

    /**
     * Usa o ProdutoDAO para obter todos os Produtos.
     *
     * @return Paginacao de Produtos.
     * 
     * 
     */
    
    public List<Midia> getTodos() {

        List<Midia> lista = new ArrayList<>();

        try ( MidiaDAO dao = new MidiaDAO() ) {
            lista = dao.listarTodos();
        } catch ( SQLException exc ) {
            exc.printStackTrace();
        }

        return lista;

    }
   

}
