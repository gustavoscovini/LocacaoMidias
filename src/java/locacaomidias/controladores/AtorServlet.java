/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package locacaomidias.controladores;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import locacaomidias.dao.AtorDAO;
import locacaomidias.entidades.Ator;
import java.time.format.DateTimeFormatter;
import locacaomidias.utils.Utils;

/**
 *
 * @author Octavio
 */
@WebServlet(name = "AtorServlet", 
        urlPatterns = {"/processaAtor"})
public class AtorServlet extends HttpServlet {

    protected void processRequest( 
            HttpServletRequest request, 
            HttpServletResponse response )
            throws ServletException, IOException {
        
        String acao = request.getParameter( "acao" );
        RequestDispatcher disp = null;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try ( AtorDAO dao = new AtorDAO() ){

            if ( acao.equals( "inserir" ) ) {

                String nome = request.getParameter( "nome" );
                String sobrenome = request.getParameter( "sobrenome" );
                String dataEstreia = request.getParameter("dataEstreia");

                Ator a = new Ator();
                a.setNome( nome );
                a.setSobrenome(sobrenome);
                a.setDataEstreia(Date.valueOf(
                        LocalDate.parse(dataEstreia, dtf)));


                Utils.validar( a, "id" );
                dao.salvar( a );
                disp = request.getRequestDispatcher(
                        "/formularios/ator/listagem.jsp" );

            } else if ( acao.equals( "alterar" ) ) {

                Long id = Utils.getLong( request, "id" );
                String nome = request.getParameter( "nome" );
                String sobrenome = request.getParameter( "sobrenome" );
                String dataEstreia = request.getParameter("dataEstreia");

                Ator a = dao.obterPorId( id );
                a.setNome( nome );
                a.setSobrenome(sobrenome);
                a.setDataEstreia(Date.valueOf(
                        LocalDate.parse(dataEstreia, dtf)));

                Utils.validar( a );
                dao.atualizar( a );
                disp = request.getRequestDispatcher(
                        "/formularios/ator/listagem.jsp" );

            } else if ( acao.equals( "excluir" ) ) {

                Long id = Utils.getLong( request, "id" );
                Ator a = dao.obterPorId( id );

                dao.excluir( a );
                disp = request.getRequestDispatcher(
                        "/formularios/ator/listagem.jsp" );

            } else {
                
                Long id = Utils.getLong( request, "id" );
                Ator a = dao.obterPorId( id );
                request.setAttribute( "ator", a );
                
                if ( acao.equals( "prepararAlteracao" ) ) {
                    disp = request.getRequestDispatcher( 
                            "/formularios/ator/alterar.jsp" );
                } else if ( acao.equals( "prepararExclusao" ) ) {
                    disp = request.getRequestDispatcher( 
                            "/formularios/ator/excluir.jsp" );
                }
                
            }

        } catch ( SQLException exc ) {
            disp = Utils.prepararDespachoErro( request, exc.getMessage() );
        }

        if ( disp != null ) {
            disp.forward( request, response );
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
