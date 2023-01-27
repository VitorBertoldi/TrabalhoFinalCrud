package br.com.ada.crud.controller.arquivo.estado;

import br.com.ada.crud.model.estado.Estado;

import java.util.List;

public interface EstadoController {

    void cadastrar(Estado estado);

    Estado ler(Integer id);

    List<Estado> listar();

    void update(Integer id, Estado estado);

    Estado delete(Integer id);
}
