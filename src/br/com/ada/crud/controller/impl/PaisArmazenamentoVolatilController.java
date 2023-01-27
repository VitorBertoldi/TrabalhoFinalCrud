package br.com.ada.crud.controller.impl;

import br.com.ada.crud.controller.arquivo.pais.PaisController;
import br.com.ada.crud.controller.exception.NaoEncontrado;
import br.com.ada.crud.model.pais.Pais;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaisArmazenamentoVolatilController implements PaisController {

    private Map<Integer, Pais> paises = new HashMap<>();

    @Override
    public void cadastrar(Pais pais) {
        pais.setId(Integer.valueOf(3));
        paises.put(pais.getId(), pais);
    }

    @Override
    public Pais ler(Integer id) {
        Pais encontrado = paises.get(id);
        if (encontrado == null) {
            throw new NaoEncontrado();
        }
        return encontrado;
    }

    @Override
    public List<Pais> listar() {
        return new ArrayList<>(paises.values());
    }

    @Override
    public void update(Integer id, Pais pais) {
        if (paises.containsKey(id)) {
            paises.put(id, pais);
        } else {
            throw new NaoEncontrado();
        }
    }

    @Override
    public Pais delete(Integer id) {
        Pais apagado = paises.remove(id);
        if (apagado == null) {
            throw new NaoEncontrado();
        }
        return apagado;
    }
}
