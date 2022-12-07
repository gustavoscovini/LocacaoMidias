/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaomidias.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.entidades.Cidade;
import locacaomidias.entidades.Cliente;
import locacaomidias.entidades.Estado;
import locacaomidias.entidades.Locacao;
import locacaomidias.utils.Utils;

/**
 *
 * @author gusta
 */
public class LocacaoDAO extends DAO<Locacao>{
    
    public LocacaoDAO() throws SQLException {
    }

    @Override
    public void salvar(Locacao obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(
                "INSERT INTO " + 
                "locacao(" + 
                "    dataInicio, " + 
                "    dataFim, " + 
                "    cancelada," +
                "    cliente_id ) " + 
                "VALUES( ?, ?, ?, ?);",
                new String[]{ "insert_id" } );
        
        
        stmt.setDate( 1, obj.getDataInicio() );
        stmt.setDate( 2, obj.getDataFim() );
        stmt.setBoolean( 3, obj.getCancelada() );
        stmt.setLong( 4, obj.getCliente().getId() );

        stmt.executeUpdate();
        obj.setId( Utils.getChavePrimariaAposInsercao( stmt, "insert_id" ) );
        stmt.close();
    }

    @Override
    public void atualizar(Locacao obj) throws SQLException {
                   
        PreparedStatement stmt = getConnection().prepareStatement(
        "UPDATE locacao " + 
        "SET" + 
        "    dataFim = ?, " + 
        "    cancelada = ?, " + 
        "    cliente_id = ? " + 
        "WHERE" + 
        "    id = ?;" );

        stmt.setDate( 1, obj.getDataFim());
        stmt.setBoolean( 2, obj.getCancelada() );
        stmt.setLong( 3, obj.getCliente().getId() );
        stmt.setLong( 4, obj.getId() );

        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void excluir(Locacao obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(
                "DELETE FROM locacao " + 
                "WHERE" + 
                "    id = ?;" );

        stmt.setLong( 1, obj.getId() );

        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public List<Locacao> listarTodos() throws SQLException {
        
        List<Locacao> lista = new ArrayList<>();

        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT" + 
                "    l.id idLocacao, " + 
                "    l.dataInicio dataIni, " +
                "    l.dataFim dataFim, " +
                "    l.cancelada locacaoCancelada, " +
                "    c.id idCliente, " + 
                "    c.nome nomeCliente, " + 
                "    c.sobreNome sobrenomeCliente, " + 
                "    c.dataNascimento dataNascimentoCliente, " + 
                "    c.cpf cpfCliente, " + 
                "    c.email emailCliente, " + 
                "    c.logradouro logradouroCliente, " + 
                "    c.numero numeroCliente, " + 
                "    c.bairro bairroCliente, " + 
                "    c.cep cepCliente, " +
                "    ci.id idCidade, " + 
                "    ci.nome nomeCidade, " + 
                "    e.id idEstado, " + 
                "    e.nome nomeEstado, " + 
                "    e.sigla siglaEstado " + 
                "FROM" + 
                "    locacao l, " + 
                "    cliente c, " +
                "    cidade ci, " + 
                "    estado e " + 
                "WHERE" + 
                "    l.cliente_id = c.id AND " + 
                "    c.cidade_id = ci.id AND " + 
                "    ci.estado_id = e.id " +
                "ORDER BY l.data DESC, c.nome;" );

        ResultSet rs = stmt.executeQuery();
        
        while ( rs.next() ) {

            Locacao l = new Locacao();
            Cliente c = new Cliente();
            Cidade ci = new Cidade();
            Estado e = new Estado();

            l.setId( rs.getLong( "idLocacao" ) );
            l.setDataInicio(rs.getDate( "dataIni" ) );
            l.setDataFim ( rs.getDate( "dataFim" ) );
            l.setCancelada( rs.getBoolean( "locacaoCancelada" ) );
            l.setCliente( c );

            c.setId( rs.getLong( "idCliente" ) );
            c.setNome( rs.getString( "nomeCliente" ) );
            c.setSobrenome( rs.getString( "sobrenomeCliente" ) );
            c.setDataNascimento( rs.getDate( "dataNascimentoCliente" ) );
            c.setCpf( rs.getString( "cpfCliente" ) );
            c.setEmail( rs.getString( "emailCliente" ) );
            c.setLogradouro( rs.getString( "logradouroCliente" ) );
            c.setNumero( rs.getString( "numeroCliente" ) );
            c.setBairro( rs.getString( "bairroCliente" ) );
            c.setCep( rs.getString( "cepCliente" ) );
            c.setCidade( ci );
            
            ci.setId( rs.getLong( "idCidade" ) );
            ci.setNome( rs.getString( "nomeCidade" ) );
            ci.setEstado( e );

            e.setId( rs.getLong( "idEstado" ) );
            e.setNome( rs.getString( "nomeEstado" ) );
            e.setSigla( rs.getString( "siglaEstado" ) );

            lista.add( l );

        }

        rs.close();
        stmt.close();

        return lista;

    }

    @Override
    public Locacao obterPorId(Long id) throws SQLException {
        
        Locacao locacao = null;

        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT" + 
                "    l.id idLocacao, " + 
                "    l.dataInicio dataIni, " +
                "    l.dataFim dataFim, " +
                "    l.cancelada locacaoCancelada, " +
                "    c.id idCliente, " + 
                "    c.nome nomeCliente, " + 
                "    c.sobreNome sobrenomeCliente, " + 
                "    c.dataNascimento dataNascimentoCliente, " + 
                "    c.cpf cpfCliente, " + 
                "    c.email emailCliente, " + 
                "    c.logradouro logradouroCliente, " + 
                "    c.numero numeroCliente, " + 
                "    c.bairro bairroCliente, " + 
                "    c.cep cepCliente, " +
                "    ci.id idCidade, " + 
                "    ci.nome nomeCidade, " + 
                "    e.id idEstado, " + 
                "    e.nome nomeEstado, " + 
                "    e.sigla siglaEstado " + 
                "FROM" + 
                "    locacao l, " + 
                "    cliente c, " +
                "    cidade ci, " + 
                "    estado e " + 
                "WHERE" + 
                "    l.id = ? AND " + 
                "    l.cliente_id = c.id AND " + 
                "    c.cidade_id = ci.id AND " + 
                "    ci.estado_id = e.id;" );

        stmt.setLong( 1, id );

        ResultSet rs = stmt.executeQuery();

        if ( rs.next() ) {

            locacao = new Locacao();
            Cliente c = new Cliente();
            Cidade ci = new Cidade();
            Estado e = new Estado();

            locacao.setId( rs.getLong( "idLocacao" ) );
            locacao.setDataInicio(rs.getDate( "dataIni" ) );
            locacao.setDataFim(rs.getDate( "dataFim" ) );
            locacao.setCancelada( rs.getBoolean( "vendaCancelada" ) );
            locacao.setCliente( c );

            c.setId( rs.getLong( "idCliente" ) );
            c.setNome( rs.getString( "nomeCliente" ) );
            c.setSobrenome( rs.getString( "sobrenomeCliente" ) );
            c.setDataNascimento( rs.getDate( "dataNascimentoCliente" ) );
            c.setCpf( rs.getString( "cpfCliente" ) );
            c.setEmail( rs.getString( "emailCliente" ) );
            c.setLogradouro( rs.getString( "logradouroCliente" ) );
            c.setNumero( rs.getString( "numeroCliente" ) );
            c.setBairro( rs.getString( "bairroCliente" ) );
            c.setCep( rs.getString( "cepCliente" ) );
            c.setCidade( ci );
            
            ci.setId( rs.getLong( "idCidade" ) );
            ci.setNome( rs.getString( "nomeCidade" ) );
            ci.setEstado( e );

            e.setId( rs.getLong( "idEstado" ) );
            e.setNome( rs.getString( "nomeEstado" ) );
            e.setSigla( rs.getString( "siglaEstado" ) );

        }

        rs.close();
        stmt.close();

        return locacao;

    }
   
}
