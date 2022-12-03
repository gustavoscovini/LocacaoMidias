/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaomidias.entidades;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author gusta
 */
public class Cidade {
    
    @NotNull
    private Long id;
    
    @NotNull
    @Size( min = 1, max = 30 )
    private String nome;
    
    @NotNull
    private Estado estado;

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome( String nome ) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado( Estado estado ) {
        this.estado = estado;
    }

}
