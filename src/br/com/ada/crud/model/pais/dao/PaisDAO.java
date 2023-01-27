package br.com.ada.crud.model.pais.dao;

import br.com.ada.crud.model.pais.Pais;

import java.util.List;

public interface PaisDAO {

    void cadastrar(Pais pais);

    List<Pais> listar();

    Pais buscar(Integer id);

    void atualizar(Integer id, Pais pais);

    Pais apagar(Integer id);
}
