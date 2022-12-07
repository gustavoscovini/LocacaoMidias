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
import locacaomidias.entidades.Ator;
import locacaomidias.entidades.ClassificacaoEtaria;
import locacaomidias.entidades.ClassificacaoInterna;
import locacaomidias.entidades.Exemplar;
import locacaomidias.entidades.Genero;
import locacaomidias.entidades.Midia;
import locacaomidias.entidades.Tipo;
import locacaomidias.utils.Utils;

/**
 *
 * @author gusta
 */
public class ExemplarDAO extends DAO<Exemplar> {

    public ExemplarDAO() throws SQLException {
    }
    
    @Override
    public void salvar(Exemplar obj) throws SQLException {
         PreparedStatement stmt = getConnection().prepareStatement(
                "INSERT INTO " + 
                "exemplar( disponivel, midia_id ) " + 
                "VALUES( ?, ? );",
                new String[]{ "insert_id" } );//duvida aqui

        stmt.setBoolean( 1, obj.getDisponivel() );
        stmt.setLong( 2, obj.getMidia().getId() );

        stmt.executeUpdate();
        obj.setCodigo_interno(Utils.getChavePrimariaAposInsercao( stmt, "insert_id" ) );
        stmt.close();
    }

    @Override
    public void atualizar(Exemplar obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(
                "UPDATE exemplar " + 
                "SET" + 
                "    disponivel = ?," + 
                "    midia_id = ? " + 
                "WHERE" + 
                "    codigo_interno = ?;" );

        stmt.setBoolean( 1, obj.getDisponivel() );
        stmt.setLong( 2, obj.getMidia().getId() );
        stmt.setLong( 3, obj.getCodigo_interno() );

        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void excluir(Exemplar obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(
                "DELETE FROM exemplar " + 
                "WHERE" + 
                "    codigo_interno = ?;" );

        stmt.setLong( 1, obj.getCodigo_interno() );

        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public List<Exemplar> listarTodos() throws SQLException {
       List<Exemplar> lista = new ArrayList<>();

        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT" + 
                "    e.codigo_interno codigoInternoExemplar, " + 
                "    e.disponivel disponivelExemplar, " + 
                "    m.id idMidia, " + 
                "    m.titulo tituloMidia, " + 
                "    m.anoLancamento anoLancamentoMidia, " + 
                "    m.codigoBarras codigoBarrasMidia, " +
                "    m.duracaoEmMinutos duracaoEmMinutosMidia, " + 
                "    ap.id idAtorPrincipal, " +
                "    ap.nome nomeAtorPrincipal, " + 
                "    ap.sobrenome sobrenomeAtorPrincipal, " +
                "    ap.dataEstreia dataEstreiaAtorPrincipal " +
                "    ac.id idAtorCoadjuvante, " +
                "    ac.nome nomeAtorCoadjuvante, " + 
                "    ac.sobrenome sobrenomeAtorCoadjuvante, " +
                "    ac.dataEstreia dataEstreiaAtorCoadjuvante, " +
                "    g.id idGenero, " +
                "    g.descricao descricaoGenero, " + 
                "    c.id idClassificacaoEtaria, " +
                "    c.descricao descricaoClassificacaoEtaria, " + 
                "    t.id idTipo, " +
                "    t.descricao descricaoTipo, " + 
                "    ci.id idClassificacaoInterna, " +
                "    ci.descricao descricaoClassificacaoInterna, " +
                "    ci.valorAluguel valorAluguelClassificacaoInterna " + 
                "FROM" + 
                "    exemplar e, " + 
                "    midia m, " + 
                "    atorPrincipal ap, " + 
                "    atorCoadjuvante ac, " + 
                "    genero g, " + 
                "    classificacaoEtaria c, " + 
                "    tipo t, " +
                "    classificacaoInterna ci " +
                "WHERE" + 
                "    e.midia_id = m.id AND " +
                "    ap.midia_id = m.id AND " +
                "    ac.midia_id = m.id AND " +
                "    g.midia_id = m.id AND " +
                "    c.midia_id = m.id AND " +
                "    t.midia_id = m.id AND " +
                "    ci.midia_id = m.id " +      
                "ORDER BY e.disponivel, m.titulo, m.anoLancamento,m.codigoBarras,m.duracaoMinutos,"
                        + "ap.nome,ap.sobrenome,ap.dataEstreia,ac.nome,ac.sobrenome,ac.dataEstreia,"
                        + "g.descricao,c.descricao,t.descricao,ci.descricao,ci.valorAluguel;" );

        ResultSet rs = stmt.executeQuery();

        while ( rs.next() ) {

            Exemplar e = new Exemplar();
            Midia m = new Midia();
            Ator ap = new Ator();
            Ator ac = new Ator();
            Genero g = new Genero();
            ClassificacaoEtaria c = new ClassificacaoEtaria();
            Tipo t = new Tipo();
            ClassificacaoInterna ci = new ClassificacaoInterna();
            
            e.setCodigo_interno(rs.getLong( "codigoInternoExemplar" ));
            e.setDisponivel(rs.getBoolean("disponivelExemplar"));
            e.setMidia(m);
            
            m.setId(rs.getLong("idMidia"));
            m.setTitulo(rs.getString("tituloMidia"));
            m.setAnoLancamento(rs.getString("anoLancamentoMidia"));
            m.setCodigoBarras(rs.getString("codigoBarrasMidia"));
            m.setDuracaoEmMinutos(rs.getLong("duracaoEmMinutosMidia"));
            m.setAtorp(ap);
            m.setAtorc(ac);
            m.setClassificaoEtaria(c);
            m.setClassificaoInterna(ci);
            m.setTipo(t);
            m.setGenero(g);
            
            ap.setId(rs.getLong("idAtorPrincipal"));
            ap.setNome(rs.getString("nomeAtorPrincipal"));
            ap.setSobrenome(rs.getString("sobrenomeAtorPrincipal"));
            ap.setDataEstreia(rs.getDate("dataEstreiaAtorPrincipal"));
            
            ac.setId(rs.getLong("idAtorCoadjuvante"));
            ac.setNome(rs.getString("nomeAtorCoadjuvante"));
            ac.setSobrenome(rs.getString("sobrenomeAtorCoadjuvante"));
            ac.setDataEstreia(rs.getDate("dataEstreiaAtorCoadjuvante"));
            
            g.setId(rs.getLong("idGenero"));
            g.setDescricao(rs.getString("descricaoGenero"));
            
            c.setId(rs.getLong("idClassificacaoEtaria"));
            c.setDescricao(rs.getString("descricaoClassificacaoEtaria"));
            
            t.setId(rs.getLong("idTipo"));
            t.setDescricao(rs.getString("descricaoTipo"));
            
            ci.setId(rs.getLong("idClassificacaoInterna"));
            ci.setDescricao(rs.getString("descricaoClassificacaoInterna"));
            ci.setValorAluguel(rs.getBigDecimal("valorAluguelClassificacaoInterna"));

            lista.add( e );

        }

        rs.close();
        stmt.close();

        return lista;
    }

    @Override
    public Exemplar obterPorId(Long id) throws SQLException {
       
         Exemplar e = null;

        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT" + 
                "    e.codigo_interno codigoInternoExemplar, " + 
                "    e.disponivel disponivelExemplar, " + 
                "    m.id idMidia, " + 
                "    m.titulo tituloMidia, " + 
                "    m.anoLancamento anoLancamentoMidia, " + 
                "    m.codigoBarras codigoBarrasMidia, " +
                "    m.duracaoEmMinutos duracaoEmMinutosMidia, " + 
                "    ap.id idAtorPrincipal, " +
                "    ap.nome nomeAtorPrincipal, " + 
                "    ap.sobrenome sobrenomeAtorPrincipal, " +
                "    ap.dataEstreia dataEstreiaAtorPrincipal " +
                "    ac.id idAtorCoadjuvante, " +
                "    ac.nome nomeAtorCoadjuvante, " + 
                "    ac.sobrenome sobrenomeAtorCoadjuvante, " +
                "    ac.dataEstreia dataEstreiaAtorCoadjuvante, " +
                "    g.id idGenero, " +
                "    g.descricao descricaoGenero, " + 
                "    c.id idClassificacaoEtaria, " +
                "    c.descricao descricaoClassificacaoEtaria, " + 
                "    t.id idTipo, " +
                "    t.descricao descricaoTipo, " + 
                "    ci.id idClassificacaoInterna, " +
                "    ci.descricao descricaoClassificacaoInterna, " +
                "    ci.valorAluguel valorAluguelClassificacaoInterna " + 
                "FROM" + 
                "    exemplar e, " + 
                "    midia m, " + 
                "    atorPrincipal ap, " + 
                "    atorCoadjuvante ac, " + 
                "    genero g, " + 
                "    classificacaoEtaria c, " + 
                "    tipo t, " +
                "    classificacaoInterna ci " +
                "WHERE" + 
                "    e.codigo_interno = ? AND" +
                "    e.midia_id = m.id AND " +
                "    ap.midia_id = m.id AND " +
                "    ac.midia_id = m.id AND " +
                "    g.midia_id = m.id AND " +
                "    c.midia_id = m.id AND " +
                "    t.midia_id = m.id AND " +
                "    ci.midia_id = m.id " +      
                "ORDER BY e.disponivel, m.titulo, m.anoLancamento,m.codigoBarras,m.duracaoMinutos,"
                        + "ap.nome,ap.sobrenome,ap.dataEstreia,ac.nome,ac.sobrenome,ac.dataEstreia,"
                        + "g.descricao,c.descricao,t.descricao,ci.descricao,ci.valorAluguel;" );
        
        stmt.setLong(1,id);

        ResultSet rs = stmt.executeQuery();
        
        if ( rs.next() ) {

            e = new Exemplar();
            Midia m = new Midia();
            Ator ap = new Ator();
            Ator ac = new Ator();
            Genero g = new Genero();
            ClassificacaoEtaria c = new ClassificacaoEtaria();
            Tipo t = new Tipo();
            ClassificacaoInterna ci = new ClassificacaoInterna();
            
            e.setCodigo_interno(rs.getLong( "codigoInternoExemplar" ));
            e.setDisponivel(rs.getBoolean("disponivelExemplar"));
            e.setMidia(m);
            
            m.setId(rs.getLong("idMidia"));
            m.setTitulo(rs.getString("tituloMidia"));
            m.setAnoLancamento(rs.getString("anoLancamentoMidia"));
            m.setCodigoBarras(rs.getString("codigoBarrasMidia"));
            m.setDuracaoEmMinutos(rs.getLong("duracaoEmMinutosMidia"));
            m.setAtorp(ap);
            m.setAtorc(ac);
            m.setClassificaoEtaria(c);
            m.setClassificaoInterna(ci);
            m.setTipo(t);
            m.setGenero(g);
            
            ap.setId(rs.getLong("idAtorPrincipal"));
            ap.setNome(rs.getString("nomeAtorPrincipal"));
            ap.setSobrenome(rs.getString("sobrenomeAtorPrincipal"));
            ap.setDataEstreia(rs.getDate("dataEstreiaAtorPrincipal"));
            
            ac.setId(rs.getLong("idAtorCoadjuvante"));
            ac.setNome(rs.getString("nomeAtorCoadjuvante"));
            ac.setSobrenome(rs.getString("sobrenomeAtorCoadjuvante"));
            ac.setDataEstreia(rs.getDate("dataEstreiaAtorCoadjuvante"));
            
            g.setId(rs.getLong("idGenero"));
            g.setDescricao(rs.getString("descricaoGenero"));
            
            c.setId(rs.getLong("idClassificacaoEtaria"));
            c.setDescricao(rs.getString("descricaoClassificacaoEtaria"));
            
            t.setId(rs.getLong("idTipo"));
            t.setDescricao(rs.getString("descricaoTipo"));
            
            ci.setId(rs.getLong("idClassificacaoInterna"));
            ci.setDescricao(rs.getString("descricaoClassificacaoInterna"));
            ci.setValorAluguel(rs.getBigDecimal("valorAluguelClassificacaoInterna"));

            

        }

        rs.close();
        stmt.close();

        return e;
        
        
    }
    
}
