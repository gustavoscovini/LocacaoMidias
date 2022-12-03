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
public class Estado {
    
    @NotNull
    private Long id;
    
    @NotNull
    @Size( min = 1, max = 30 )
    private String nome;
    
    @NotNull
    @Size( min = 1, max = 2 )
    private String sigla;

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

    public String getSigla() {
        return sigla;
    }

    public void setSigla( String sigla ) {
        this.sigla = sigla;
    }

}
