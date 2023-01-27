package br.com.ada.crud.model.estado.dao.imp;

import br.com.ada.crud.controller.arquivo.EscritorArquivo;
import br.com.ada.crud.controller.arquivo.LeitorArquivo;
import br.com.ada.crud.model.estado.Estado;
import br.com.ada.crud.model.estado.dao.EstadoDAO;

import java.util.List;

public class EstadoArquivoDAO implements EstadoDAO {

    private EscritorArquivo<Estado> escritor;
    private LeitorArquivo<Estado> leitor;

    public EstadoArquivoDAO(
            EscritorArquivo<Estado> escritor,
            LeitorArquivo<Estado> leitor

    ) {
        this.escritor = escritor;
        this.leitor = leitor;
    }

    @Override
    public void cadastrar(Estado estado) {
        escritor.escrever(estado, estado.getId().toString());
    }

    @Override
    public List<Estado> listar() {
        return leitor.ler();
    }

    @Override
    public Estado buscar(Integer id) {
        Estado estado = leitor.ler(id.toString());
        return estado;
    }

    @Override
    public void atualizar(Integer id, Estado estado) {
        escritor.escrever(estado, id.toString());
    }

    @Override
    public Estado apagar(Integer id) {
        return escritor.apagar(id.toString());
    }
}
