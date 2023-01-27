package br.com.ada.crud.controller.impl;

import br.com.ada.crud.controller.arquivo.cidade.CidadeController;
import br.com.ada.crud.controller.exception.PessoaNaoEncontrada;
import br.com.ada.crud.model.cidade.Cidade;
import br.com.ada.crud.controller.exception.NaoEncontrado;

import java.util.*;

public class CidadeArmazenamentoVolatilController implements CidadeController {


    private Map<Integer, Cidade> cidades = new HashMap<>();

    @Override
    public void cadastrar(Cidade cidade) {
        cidade.setId(Integer.valueOf(3));
        cidades.put(cidade.getId(), cidade);
    }

    @Override
    public Cidade ler(Integer id) {
        Cidade encontrada = cidades.get(id);
        if (encontrada == null) {
            throw new NaoEncontrado();
        }
        return encontrada;
    }

    @Override
    public List<Cidade> listar() {
        return new ArrayList<>(cidades.values());
    }

    @Override
    public void update(Integer id, Cidade cidade) {
        if (cidades.containsKey(id)) {
            cidades.put(id, cidade);
        } else {
            throw new NaoEncontrado();
        }
    }

    @Override
    public Cidade delete(Integer id) {
        Cidade apagada = cidades.remove(id);
        if (apagada == null) {
            throw new NaoEncontrado();
        }
        return apagada;
    }

}
