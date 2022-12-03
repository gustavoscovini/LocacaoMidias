/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaomidias.entidades;

import javax.validation.constraints.NotNull;

/**
 *
 * @author gusta
 */
public class Exemplar {
    
    @NotNull
    private Long codigo_interno;
    
    @NotNull
    private Boolean disponivel;
    
    @NotNull
    private Midia midia;

    public Long getCodigo_interno() {
        return codigo_interno;
    }

    public void setCodigo_interno(Long codigo_interno) {
        this.codigo_interno = codigo_interno;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Midia getMidia() {
        return midia;
    }

    public void setMidia(Midia midia) {
        this.midia = midia;
    }
    
}
