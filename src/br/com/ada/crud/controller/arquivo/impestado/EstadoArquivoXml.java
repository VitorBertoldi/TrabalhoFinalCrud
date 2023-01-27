package br.com.ada.crud.controller.arquivo.impestado;

import br.com.ada.crud.controller.arquivo.AbstractXmlArquivo;
import br.com.ada.crud.controller.arquivo.EscritorArquivo;
import br.com.ada.crud.controller.arquivo.LeitorArquivo;
import br.com.ada.crud.model.estado.Estado;
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

public class EstadoArquivoXml extends AbstractXmlArquivo implements EscritorArquivo<Estado>, LeitorArquivo<Estado> {

    public static final String EXTENSAO = ".xml";
    private String diretorio = "database/xml/estados";

    @Override
    public void escrever(Estado estado, String arquivo) {
        try {
            Document document = criarNovoDocumento();
            Element elementEstado = document.createElement("estado");
            document.appendChild(elementEstado);

            adicionarElemento(document, "id", estado.getId().toString(), elementEstado);
            adicionarElemento(document, "nome", estado.getNome(), elementEstado);
            adicionarElemento(document, "pais", estado.getPais().toString(), elementEstado);

            escreverArquivo(diretorio, arquivo + EXTENSAO, document);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Estado apagar(String arquivo) {
        Estado estado = ler(arquivo);
        apagarArquivo(diretorio, arquivo + EXTENSAO);
        return estado;
    }

    @Override
    public Estado ler(String arquivo) {
        File arquivoASerLido = new File(diretorio, arquivo + EXTENSAO);
        return ler(arquivoASerLido);
    }

    @Override
    public List<Estado> ler() {
        FilenameFilter filter = (dir, nomeDoArquivo) -> nomeDoArquivo.endsWith(EXTENSAO);

        List<Estado> estados = new ArrayList<>();
        File diretorioQueContemOsArquvios = new File(diretorio);
        for (File arquivoASerLido : diretorioQueContemOsArquvios.listFiles(filter)) {
            Estado estado = ler(arquivoASerLido);
            estados.add(estado);
        }
        return estados;
    }

    private Estado ler(File arquivo) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(arquivo);
            Element elementCidade = document.getDocumentElement();
            Node elementId = elementCidade.getElementsByTagName("id").item(0);
            Node elementNome = elementCidade.getElementsByTagName("nome").item(0);
            Node elementUf = elementCidade.getElementsByTagName("pais").item(0);


            Estado estado = new Estado();
            estado.setId(Integer.parseInt(elementId.getTextContent()));
            estado.setNome(elementNome.getTextContent());
            estado.setPais(elementUf.getTextContent());
            return estado;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
