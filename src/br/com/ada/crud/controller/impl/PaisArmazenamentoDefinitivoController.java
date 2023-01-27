package br.com.ada.crud.controller.impl;

import br.com.ada.crud.controller.arquivo.pais.PaisController;
import br.com.ada.crud.model.pais.Pais;
import br.com.ada.crud.model.pais.dao.PaisDAO;

import java.util.List;

public class PaisArmazenamentoDefinitivoController implements PaisController {

    private PaisDAO paisDAO;

    public PaisArmazenamentoDefinitivoController(
            PaisDAO paisDAO
    ) {
        this.paisDAO = paisDAO;
    }

    @Override
    public void cadastrar(Pais pais) {
        pais.setId(Integer.valueOf(3));//revisar!!!!
        paisDAO.cadastrar(pais);
    }

    @Override
    public Pais ler(Integer id) {
        return paisDAO.buscar(id);
    }

    @Override
    public List<Pais> listar() {
        return paisDAO.listar();
    }

    @Override
    public void update(Integer id, Pais pais) {
        paisDAO.atualizar(id, pais);
    }

    @Override
    public Pais delete(Integer id) {
        return paisDAO.apagar(id);
    }
}
