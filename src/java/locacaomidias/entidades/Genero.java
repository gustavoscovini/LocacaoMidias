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
public class Genero {
    
    @NotNull
    private Long id;
    
    @NotNull
    @Size( min = 1, max = 45 )
    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
