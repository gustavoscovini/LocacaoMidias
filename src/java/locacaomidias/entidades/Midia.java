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
public class Midia {
    
    @NotNull
    private Long id;
    
    @NotNull
    @Size( min = 1, max = 100 )
    private String titulo;
    
    @NotNull
    @Size( min = 1, max = 100 )
    private String anoLancamento;
    
    @NotNull
    @Size( min = 1, max = 13 )
    private String codigoBarras;
    
    @NotNull
    private Long duracaoEmMinutos;
    
    @NotNull
    private Ator atorp;

    @NotNull
    private Ator atorc;

    @NotNull
    private Genero genero;

    @NotNull
    private ClassificacaoEtaria classificaoEtaria;

    @NotNull
    private Tipo tipo;
    
    @NotNull
    private ClassificacaoInterna classificaoInterna;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(String anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Long getDuracaoEmMinutos() {
        return duracaoEmMinutos;
    }

    public void setDuracaoEmMinutos(Long duracaoEmMinutos) {
        this.duracaoEmMinutos = duracaoEmMinutos;
    }

    public Ator getAtorp() {
        return atorp;
    }

    public void setAtorp(Ator atorp) {
        this.atorp = atorp;
    }

    public Ator getAtorc() {
        return atorc;
    }

    public void setAtorc(Ator atorc) {
        this.atorc = atorc;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public ClassificacaoEtaria getClassificaoEtaria() {
        return classificaoEtaria;
    }

    public void setClassificaoEtaria(ClassificacaoEtaria classificaoEtaria) {
        this.classificaoEtaria = classificaoEtaria;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public ClassificacaoInterna getClassificaoInterna() {
        return classificaoInterna;
    }

    public void setClassificaoInterna(ClassificacaoInterna classificaoInterna) {
        this.classificaoInterna = classificaoInterna;
    }
}
