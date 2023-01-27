package br.com.ada.crud.controller.arquivo.imppais;

import br.com.ada.crud.controller.arquivo.AbstractXmlArquivo;
import br.com.ada.crud.controller.arquivo.EscritorArquivo;
import br.com.ada.crud.controller.arquivo.LeitorArquivo;
import br.com.ada.crud.model.pais.Pais;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PaisArquivoXml extends AbstractXmlArquivo implements EscritorArquivo<Pais>, LeitorArquivo<Pais> {

    public static final String EXTENSAO = ".xml";
    private String diretorio = "database/xml/estados";

    @Override
    public void escrever(Pais pais, String arquivo) {
        try {
            Document document = criarNovoDocumento();
            Element elementPais = document.createElement("estado");
            document.appendChild(elementPais);

            adicionarElemento(document, "id", pais.getId().toString(), elementPais);
            adicionarElemento(document, "nome", pais.getNome(), elementPais);
            adicionarElemento(document, "continente", pais.getContinente().toString(), elementPais);

            escreverArquivo(diretorio, arquivo + EXTENSAO, document);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Pais apagar(String arquivo) {
        Pais pais = ler(arquivo);
        apagarArquivo(diretorio, arquivo + EXTENSAO);
        return pais;
    }

    @Override
    public Pais ler(String arquivo) {
        File arquivoASerLido = new File(diretorio, arquivo + EXTENSAO);
        return ler(arquivoASerLido);
    }

    @Override
    public List<Pais> ler() {
        FilenameFilter filter = (dir, nomeDoArquivo) -> nomeDoArquivo.endsWith(EXTENSAO);

        List<Pais> estados = new ArrayList<>();
        File diretorioQueContemOsArquvios = new File(diretorio);
        for (File arquivoASerLido : diretorioQueContemOsArquvios.listFiles(filter)) {
            Pais pais = ler(arquivoASerLido);
            estados.add(pais);
        }
        return estados;
    }

    private Pais ler(File arquivo) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(arquivo);
            Element elementCidade = document.getDocumentElement();
            Node elementId = elementCidade.getElementsByTagName("id").item(0);
            Node elementNome = elementCidade.getElementsByTagName("nome").item(0);
            Node elementUf = elementCidade.getElementsByTagName("pais").item(0);


            Pais pais = new Pais();
            pais.setId(Integer.parseInt(elementId.getTextContent()));
            pais.setNome(elementNome.getTextContent());
            pais.setContinente(elementUf.getTextContent());
            return pais;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
