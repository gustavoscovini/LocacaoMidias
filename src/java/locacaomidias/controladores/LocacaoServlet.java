package locacaomidias.controladores;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import locacaomidias.dao.ClienteDAO;
import locacaomidias.dao.ExemplarDAO;
import locacaomidias.dao.ItemLocacaoDAO;
import locacaomidias.dao.LocacaoDAO;
import locacaomidias.entidades.Cliente;
import locacaomidias.entidades.Exemplar;
import locacaomidias.entidades.ItemLocacao;
import locacaomidias.entidades.Locacao;
import locacaomidias.utils.Utils;


@WebServlet( name = "LocacoesServlet", 
             urlPatterns = { "/processaLocacao" } )
public class LocacaoServlet extends HttpServlet {

    protected void processRequest( 
            HttpServletRequest request, 
            HttpServletResponse response )
            throws ServletException, IOException {
        
        String acao = request.getParameter( "acao" );
        RequestDispatcher disp = null;
        
        try ( LocacaoDAO daoLocacao = new LocacaoDAO();
              ClienteDAO daoCliente = new ClienteDAO();
              ItemLocacaoDAO daoItemLocacao = new ItemLocacaoDAO();
              ExemplarDAO daoExemplar = new ExemplarDAO() ) {

            if ( acao.equals( "inserir" ) ) {

                Long idCliente = Utils.getLong( request, "idCliente" );
                String itensLocacao = request.getParameter( "itensLocacao" );
                // cria um leitor de json para processar os
                // itens da venda
                JsonReader jsr = Json.createReader( 
                        new StringReader( itensLocacao ) );
                // faz a leitura/parse
                JsonArray jsaItensVenda = jsr.readArray();
                Cliente c = daoCliente.obterPorId( idCliente );
                
                Locacao l = new Locacao();
                l.setDataInicio( Date.valueOf( LocalDate.now() ) );
                l.setDataFim( Date.valueOf( LocalDate.now() ) );
                l.setCancelada( false );
                l.setCliente( c );
                
                Utils.validar( l, "id" );
                daoLocacao.salvar( l );
                // itera pelos itens da venda genéricos
                for ( JsonValue jsv : jsaItensVenda ) {
                    
                    // sabemos que cada item é um objeto
                    JsonObject jso = jsv.asJsonObject();
                    
                    // extraímos os atributos 
                    Long idExemplar = Utils.getLong( 
                            jso.getString( "codigo_interno" ) );
                    Long disponivel = Utils.getLong(
                            jso.getString( "disponivel" ) );
                    
                    // obtém o produto e atualiza o estoque
                    Exemplar e = daoExemplar.obterPorId( idExemplar );
                    if(disponivel==1){
                        e.setDisponivel(true);
                    }else{
                        e.setDisponivel(false);
                    }
                    
                    
                    // cria um item da venda
                    ItemLocacao il = new ItemLocacao();
                    il.setExemplar(e);
                    il.setLocacao(l);
                    il.setValor(BigDecimal.ZERO);
                    
                    
                    // não validaremos o produto, pois
                    // permitiremos estoque negativo na venda
                    daoExemplar.atualizar( e );
                    daoItemLocacao.salvar( il );
                    
                }
                System.out.println("oi4");
                disp = request.getRequestDispatcher(
                        "/formularios/vendas/listagem.jsp" );

            } else if ( acao.equals( "cancelar" ) ) {

                

            }

        } catch ( SQLException exc ) {
            disp = Utils.prepararDespachoErro( request, exc.getMessage() );
        }

        if ( disp != null ) {
            disp.forward( request, response );
        }
        
    }

    @Override
    protected void doGet( 
            HttpServletRequest request, 
            HttpServletResponse response )
            throws ServletException, IOException {
        processRequest( request, response );
    }

    @Override
    protected void doPost( 
            HttpServletRequest request, 
            HttpServletResponse response )
            throws ServletException, IOException {
        processRequest( request, response );
    }

    @Override
    public String getServletInfo() {
        return "VendasServlet";
    }

}
