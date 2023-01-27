package br.com.ada.crud.controller.impl;
import br.com.ada.crud.model.cidade.Cidade;
import br.com.ada.crud.controller.arquivo.cidade.CidadeController;
import br.com.ada.crud.model.cidade.dao.CidadeDAO;


import java.util.List;


public class CidadeArmazenamentoDefinitivoController implements CidadeController {

    private CidadeDAO cidadeDAO;

    public CidadeArmazenamentoDefinitivoController(
            CidadeDAO cidadeDAO
    ) {
        this.cidadeDAO = cidadeDAO;
    }

    @Override
    public void cadastrar(Cidade cidade) {
        cidade.setId(Integer.valueOf(3));//revisar!!!!
        cidadeDAO.cadastrar(cidade);
    }

    @Override
    public Cidade ler(Integer id) {
        return cidadeDAO.buscar(id);
    }

    @Override
    public List<Cidade> listar() {
        return cidadeDAO.listar();
    }

    @Override
    public void update(Integer id, Cidade cidade) {
        cidadeDAO.atualizar(id, cidade);
    }

    @Override
    public Cidade delete(Integer id) {
        return cidadeDAO.apagar(id);
    }
}
