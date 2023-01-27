package br.com.ada.crud.controller.impl;

import br.com.ada.crud.controller.arquivo.estado.EstadoController;
import br.com.ada.crud.controller.exception.NaoEncontrado;
import br.com.ada.crud.model.estado.Estado;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EstadoArmazenamentoVolatilController implements EstadoController {

    private Map<Integer, Estado> estados = new HashMap<>();

    @Override
    public void cadastrar(Estado estado) {
        estado.setId(Integer.valueOf(3));
        estados.put(estado.getId(), estado);
    }

    @Override
    public Estado ler(Integer id) {
        Estado encontrada = estados.get(id);
        if (encontrada == null) {
            throw new NaoEncontrado();
        }
        return encontrada;
    }

    @Override
    public List<Estado> listar() {
        return new ArrayList<>(estados.values());
    }

    @Override
    public void update(Integer id, Estado estado) {
        if (estados.containsKey(id)) {
            estados.put(id, estado);
        } else {
            throw new NaoEncontrado();
        }
    }

    @Override
    public Estado delete(Integer id) {
        Estado apagada = estados.remove(id);
        if (apagada == null) {
            throw new NaoEncontrado();
        }
        return apagada;
    }
}
