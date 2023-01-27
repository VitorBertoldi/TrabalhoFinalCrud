package br.com.ada.crud.model.cidade;

import java.io.Serializable;

public class Cidade implements Serializable {

    public static final long serialVersionUID = 1l;
    private Integer id;

    private String nome;

    private String uf;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
