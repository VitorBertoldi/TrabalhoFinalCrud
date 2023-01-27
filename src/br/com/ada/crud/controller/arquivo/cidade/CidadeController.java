package br.com.ada.crud.controller.arquivo.cidade;

import br.com.ada.crud.model.cidade.Cidade;

import java.util.List;
import java.util.UUID;


public interface CidadeController {
    void cadastrar(Cidade cidade);

    Cidade ler(Integer id);

    List<Cidade> listar();

    void update(Integer id, Cidade cidade);

    Cidade delete(Integer id);
}
