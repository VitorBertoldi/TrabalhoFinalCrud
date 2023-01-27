package br.com.ada.crud.model.pais;

import java.io.Serializable;

public class Pais implements Serializable {

    public static final long serialVersionUID = 1l;
    private Integer id;

    private String nome;

    private String continente;

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
