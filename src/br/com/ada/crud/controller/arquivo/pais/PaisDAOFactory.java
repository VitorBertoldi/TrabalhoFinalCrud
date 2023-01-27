package br.com.ada.crud.controller.arquivo.pais;

import br.com.ada.crud.Constantes;
import br.com.ada.crud.controller.arquivo.EscritorArquivo;
import br.com.ada.crud.controller.arquivo.LeitorArquivo;
import br.com.ada.crud.controller.arquivo.pais.PaisDAOFactory;
import br.com.ada.crud.controller.arquivo.imppais.PaisArquivoBinario;
import br.com.ada.crud.controller.arquivo.imppais.PaisArquivoXml;
import br.com.ada.crud.model.cidade.dao.PersistenciaTipo;
import br.com.ada.crud.model.pais.Pais;
import br.com.ada.crud.model.pais.dao.PaisDAO;
import br.com.ada.crud.model.pais.dao.imp.PaisArquivoDAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PaisDAOFactory {

    private static final PaisDAOFactory INSTANCE = new PaisDAOFactory();
    private static final String PERSISTENCIA_TIPO = "pais.persistencia.tipo";

    private PersistenciaTipo tipo;

    private PaisDAOFactory() {
    }

    public static PaisDAOFactory getInstance() {
        return INSTANCE;
    }

    public PaisDAO create() {
        if (tipo == null) {
            carregarTipoPersistencia();
        }
        EscritorArquivo<Pais> escritor = null;
        LeitorArquivo<Pais> leitor = null;

        if (tipo == PersistenciaTipo.BINARIA) {
            escritor = new PaisArquivoBinario();
            leitor = new PaisArquivoBinario();
        } else if (tipo == PersistenciaTipo.XML) {
            escritor = new PaisArquivoXml();
            leitor = new PaisArquivoXml();
        }
        return new PaisArquivoDAO(escritor, leitor);
    }

    private void carregarTipoPersistencia() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(Constantes.ARQUIVO_PROPRIEDADES));

            String valorNoArquivo = properties.getProperty(PERSISTENCIA_TIPO);
            tipo = PersistenciaTipo.valueOf(valorNoArquivo);
        } catch (IOException ex) {
            throw new RuntimeException("Não foi possível ler o arquivo de configurações", ex);
        }
    }

}
