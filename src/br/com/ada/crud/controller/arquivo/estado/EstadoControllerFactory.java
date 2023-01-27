package br.com.ada.crud.controller.arquivo.estado;

import br.com.ada.crud.Constantes;
import br.com.ada.crud.controller.arquivo.estado.EstadoArmazenamentoTipo;
import br.com.ada.crud.controller.arquivo.estado.EstadoController;
import br.com.ada.crud.controller.arquivo.estado.EstadoControllerFactory;
import br.com.ada.crud.controller.arquivo.cidade.CidadeDAOFactory;
import br.com.ada.crud.controller.impl.EstadoArmazenamentoDefinitivoController;
import br.com.ada.crud.controller.impl.EstadoArmazenamentoVolatilController;
import br.com.ada.crud.model.estado.dao.EstadoDAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EstadoControllerFactory {

    private static final EstadoControllerFactory INSTANCE = new EstadoControllerFactory();
    private static final String CONTROLLER_TIPO = "estado.controller.tipo";
    private EstadoArmazenamentoTipo tipo;

    private EstadoControllerFactory() {
    }

    public static EstadoControllerFactory getInstance() {
        return INSTANCE;
    }

    // Factory method
    public EstadoController criar() {
        if (tipo == null) {
            carregarTipoArmazenamento();
        }

        EstadoDAOFactory daoFactory = EstadoDAOFactory.getInstance();
        EstadoController controller = null;

        if (tipo == EstadoArmazenamentoTipo.VOLATIL) {
            controller = new EstadoArmazenamentoVolatilController();
        } else if (tipo == EstadoArmazenamentoTipo.DEFINITIVO) {
            EstadoDAO estadoDAO = daoFactory.create();
            controller = new EstadoArmazenamentoDefinitivoController(estadoDAO);
        } else {
            throw new RuntimeException("Nenhuma implementação disponível");
        }
        return controller;
    }

    private void carregarTipoArmazenamento() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(Constantes.ARQUIVO_PROPRIEDADES));

            String valorDoArquivo = properties.getProperty(CONTROLLER_TIPO);
            tipo = EstadoArmazenamentoTipo.valueOf(valorDoArquivo);
        }catch(IOException ex) {
            throw new RuntimeException("Falha no carregamento do arquivo de propriedaes.",ex);
        }
    }
}
