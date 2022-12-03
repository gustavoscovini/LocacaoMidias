/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaomidias.dao;

import locacaomidias.entidades.Ator;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author gusta
 */
public class AtorDAO extends DAO<Ator>{
    
    public AtorDAO() throws SQLException {
    }

    @Override
    public void salvar(Ator obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void atualizar(Ator obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void excluir(Ator obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Ator> listarTodos() throws SQLException {
                
        List<Ator> lista = new ArrayList<>();

        PreparedStatement stmt = getConnection().prepareStatement(
            "SELECT * FROM ator_atriz " );

        ResultSet rs = stmt.executeQuery();

        while ( rs.next() ) {

            Ator a = new Ator();

            a.setId( rs.getLong("id" ) );
            a.setNome( rs.getString( "nome" ) );
            a.setSobrenome(rs.getString( "sobrenome") );
            a.setDataEstreia(rs.getDate("dataEstreia"));

            lista.add( a );

        }

        rs.close();
        stmt.close();

        return lista;   
    }

    @Override
    public Ator obterPorId(Long id) throws SQLException {
                        
        Ator a = null;
        
        PreparedStatement stmt = getConnection().prepareStatement(
        
                "SELECT * FROM ator_atriz " +
                " WHERE id = ?;"   
        );
        
        stmt.setLong(1, id);
        
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            
            a = new Ator();
            
            a.setId( rs.getLong("id") );
            a.setNome( rs.getString( "nome" ) );
            a.setSobrenome( rs.getString( "sobrenome") );
            a.setDataEstreia( rs.getDate("dataEstreia") );
            
        }
        
        rs.close();
        stmt.close();
        
        return a;
    }

}
