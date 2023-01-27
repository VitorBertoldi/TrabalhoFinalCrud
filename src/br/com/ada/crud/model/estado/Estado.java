package br.com.ada.crud.model.estado;

import java.io.Serializable;

public class Estado implements Serializable {
    public static final long serialVersionUID = 1l;
    private Integer id;

    private String nome;

    private String pais;


    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
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
