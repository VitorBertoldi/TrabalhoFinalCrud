package br.com.ada.crud.controller.impl;

import br.com.ada.crud.controller.arquivo.estado.EstadoController;
import br.com.ada.crud.model.estado.Estado;
import br.com.ada.crud.model.estado.dao.EstadoDAO;

import java.util.List;

public class EstadoArmazenamentoDefinitivoController implements EstadoController {

    private EstadoDAO estadoDAO;

    public EstadoArmazenamentoDefinitivoController(
            EstadoDAO estadoDAO
    ) {
        this.estadoDAO = estadoDAO;
    }

    @Override
    public void cadastrar(Estado estado) {
        estado.setId(Integer.valueOf(3));//revisar!!!!
        estadoDAO.cadastrar(estado);
    }

    @Override
    public Estado ler(Integer id) {
        return estadoDAO.buscar(id);
    }

    @Override
    public List<Estado> listar() {
        return estadoDAO.listar();
    }

    @Override
    public void update(Integer id, Estado estado) {
        estadoDAO.atualizar(id, estado);
    }

    @Override
    public Estado delete(Integer id) {
        return estadoDAO.apagar(id);
    }
}
