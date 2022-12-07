package locacaomidias.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.entidades.Ator;
import locacaomidias.entidades.Cidade;
import locacaomidias.entidades.ClassificacaoEtaria;
import locacaomidias.entidades.ClassificacaoInterna;
import locacaomidias.entidades.Estado;
import locacaomidias.entidades.Exemplar;
import locacaomidias.entidades.Genero;
import locacaomidias.entidades.Midia;
import locacaomidias.entidades.Tipo;
import locacaomidias.utils.Utils;

/**
 * DAO para a entidade Produto.
 *
 * @author Prof. Dr. David Buzatto
 */
public class MidiaDAO extends DAO<Midia> {

    public MidiaDAO() throws SQLException {
    }

    @Override
    public void salvar(Midia obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void atualizar(Midia obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void excluir(Midia obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Midia> listarTodos() throws SQLException {
    List<Midia> lista = new ArrayList<>();

        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT" + 
                "    m.id idMidia, " + 
                "    m.titulo tituloMidia, " + 
                "    m.anoLancamento anoLancamentoMidia, " + 
                "    m.codigoBarras codigoBarrasMidia, " +
                "    m.duracaoEmMinutos duracaoEmMinutosMidia, " + 
                "    ap.id idAtorPrincipal, " +
                "    ap.nome nomeAtorPrincipal, " + 
                "    ap.sobrenome sobrenomeAtorPrincipal, " +
                "    ap.dataEstreia dataEstreiaAtorPrincipal, " +
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
                "    midia m, " + 
                "    ator_atriz ap, " + 
                "    ator_atriz ac, " + 
                "    genero g, " + 
                "    classificacao_etaria c, " + 
                "    tipo t, " +
                "    classificacao_interna ci " +
                "WHERE" + 
                "    m.ator_atriz_principal = ap.id AND " +
                "    m.ator_atriz_coadjuvante = ac.id AND " +
                "    m.genero_id = g.id AND " +
                "    m.classificacao_etaria_id = c.id AND " +
                "    m.tipo_id = t.id AND " +
                "    m.classificacao_interna_id = ci.id " +      
                "ORDER BY m.titulo, m.anoLancamento,m.codigoBarras,m.duracaoEmMinutos,"
                        + "ap.nome,ap.sobrenome,ap.dataEstreia,ac.nome,ac.sobrenome,ac.dataEstreia,"
                        + "g.descricao,c.descricao,t.descricao,ci.descricao,ci.valorAluguel;" );
        
        
        ResultSet rs = stmt.executeQuery();

        while ( rs.next() ) {

            Midia m = new Midia();
            Ator ap = new Ator();
            Ator ac = new Ator();
            Genero g = new Genero();
            ClassificacaoEtaria c = new ClassificacaoEtaria();
            Tipo t = new Tipo();
            ClassificacaoInterna ci = new ClassificacaoInterna();
            
            
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
            
            

            lista.add( m );

        }

        rs.close();
        stmt.close();

        return lista;}

    @Override
    public Midia obterPorId(Long id) throws SQLException {
        Midia m = null;

        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT" + 
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
                "    midia m, " + 
                "    atorPrincipal ap, " + 
                "    atorCoadjuvante ac, " + 
                "    genero g, " + 
                "    classificacaoEtaria c, " + 
                "    tipo t, " +
                "    classificacaoInterna ci " +
                "WHERE" + 
                "    m.midia_id = ? AND " +
                "    ap.midia_id = m.id AND " +
                "    ac.midia_id = m.id AND " +
                "    g.midia_id = m.id AND " +
                "    c.midia_id = m.id AND " +
                "    t.midia_id = m.id AND " +
                "    ci.midia_id = m.id " +      
                "ORDER BY m.titulo, m.anoLancamento,m.codigoBarras,m.duracaoMinutos,"
                        + "ap.nome,ap.sobrenome,ap.dataEstreia,ac.nome,ac.sobrenome,ac.dataEstreia,"
                        + "g.descricao,c.descricao,t.descricao,ci.descricao,ci.valorAluguel;" );
        
        stmt.setLong(1,id);

        ResultSet rs = stmt.executeQuery();
        
        if ( rs.next() ) {

            
            m = new Midia();
            Ator ap = new Ator();
            Ator ac = new Ator();
            Genero g = new Genero();
            ClassificacaoEtaria c = new ClassificacaoEtaria();
            Tipo t = new Tipo();
            ClassificacaoInterna ci = new ClassificacaoInterna();
            
            
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

        return m;
    }


}
