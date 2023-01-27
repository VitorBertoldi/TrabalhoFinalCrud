package br.com.ada.crud.model.estado.dao;

import br.com.ada.crud.model.estado.Estado;

import java.util.List;

public interface EstadoDAO {

    void cadastrar(Estado estado);

    List<Estado> listar();

    Estado buscar(Integer id);

    void atualizar(Integer id, Estado estado);

    Estado apagar(Integer id);
}
