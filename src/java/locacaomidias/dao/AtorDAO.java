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
import locacaomidias.utils.Utils;
/**
 *
 * @author gusta
 */
public class AtorDAO extends DAO<Ator>{
    
    public AtorDAO() throws SQLException {
    }

    @Override
    public void salvar(Ator obj) throws SQLException {
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "INSERT INTO " + 
                "ator_atriz(nome, sobrenome,dataEstreia) " + 
                "VALUES( ?, ?, ? );",
                new String[]{ "insert_id" } );

        stmt.setString( 1, obj.getNome() );
        stmt.setString( 2, obj.getSobrenome() );
        stmt.setDate(3, obj.getDataEstreia());

        stmt.executeUpdate();
        obj.setId( Utils.getChavePrimariaAposInsercao( stmt, "insert_id" ) );
        stmt.close();
    }

    @Override
    public void atualizar(Ator obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(
                "UPDATE ator_atriz " + 
                "SET" + 
                "    nome = ?," + 
                "    sobrenome = ?, " + 
                "    dataEstreia = ? " +
                "WHERE" + 
                "    id = ?;" );

        stmt.setString( 1, obj.getNome() );
        stmt.setString( 2, obj.getSobrenome() );
        stmt.setDate( 3, obj.getDataEstreia() );
        stmt.setLong( 4,obj.getId() );

        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void excluir(Ator obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(
                "DELETE FROM ator_atriz " + 
                "WHERE" + 
                "    id = ?;" );

        stmt.setLong( 1, obj.getId() );

        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public List<Ator> listarTodos() throws SQLException {
                
        List<Ator> lista = new ArrayList<>();

        PreparedStatement stmt = getConnection().prepareStatement(
            "SELECT * FROM ator_atriz " +
            "ORDER BY nome, sobrenome,dataEstreia;" );

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
