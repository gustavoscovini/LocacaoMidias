/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaomidias.entidades;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 *
 * @author gusta
 */
public class ItemLocacao {
    
    @NotNull
    private Locacao locacao;
    
    @NotNull
    private Exemplar exemplar;
    
    @NotNull
    @PositiveOrZero
    private BigDecimal valor;

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    
}
