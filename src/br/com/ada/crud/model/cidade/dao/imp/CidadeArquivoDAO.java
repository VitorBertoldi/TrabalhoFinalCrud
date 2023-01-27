package br.com.ada.crud.model.cidade.dao.imp;

import br.com.ada.crud.controller.arquivo.EscritorArquivo;
import br.com.ada.crud.controller.arquivo.LeitorArquivo;
import br.com.ada.crud.model.cidade.dao.CidadeDAO;
import br.com.ada.crud.model.cidade.Cidade;

import java.util.List;

public class CidadeArquivoDAO implements CidadeDAO {

    private EscritorArquivo<Cidade> escritor;
    private LeitorArquivo<Cidade> leitor;

    public CidadeArquivoDAO(
            EscritorArquivo<Cidade> escritor,
            LeitorArquivo<Cidade> leitor

    ) {
        this.escritor = escritor;
        this.leitor = leitor;
    }

    @Override
    public void cadastrar(Cidade cidade) {
        escritor.escrever(cidade, cidade.getId().toString());
    }

    @Override
    public List<Cidade> listar() {
        return leitor.ler();
    }

    @Override
    public Cidade buscar(Integer id) {
        Cidade cidade = leitor.ler(id.toString());
        return cidade;
    }

    @Override
    public void atualizar(Integer id, Cidade cidade) {
        escritor.escrever(cidade, id.toString());
    }

    @Override
    public Cidade apagar(Integer id) {
        return escritor.apagar(id.toString());
    }
}
