/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaomidias.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import locacaomidias.entidades.ItemLocacao;

/**
 *
 * @author gusta
 */
public class ItemLocacaoDAO extends DAO<ItemLocacao> {
    
    public ItemLocacaoDAO() throws SQLException {
    }

    @Override
    public void salvar(ItemLocacao obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(
            "INSERT INTO " + 
            "item_locacao( locacao_id, exemplar_codigo_interno, valor) " + 
            "VALUES( ?, ?, ? );" );
        
        stmt.setLong(1, obj.getLocacao().getId());
        stmt.setLong(2, obj.getExemplar().getCodigo_interno());
        stmt.setBigDecimal(3, obj.getValor());
        
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void atualizar(ItemLocacao obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void excluir(ItemLocacao obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ItemLocacao> listarTodos() throws SQLException {
        return null;
    }

    @Override
    public ItemLocacao obterPorId(Long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
