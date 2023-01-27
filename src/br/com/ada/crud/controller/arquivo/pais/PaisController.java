package br.com.ada.crud.controller.arquivo.pais;

import br.com.ada.crud.model.pais.Pais;

import java.util.List;

public interface PaisController {

    void cadastrar(Pais pais);

    Pais ler(Integer id);

    List<Pais> listar();

    void update(Integer id, Pais pais);

    Pais delete(Integer id);
}
