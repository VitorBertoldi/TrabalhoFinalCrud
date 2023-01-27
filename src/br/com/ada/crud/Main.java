package br.com.ada.crud;

import br.com.ada.crud.controller.arquivo.cidade.CidadeController;
import br.com.ada.crud.controller.arquivo.cidade.CidadeControllerFactory;
import br.com.ada.crud.controller.arquivo.estado.EstadoController;
import br.com.ada.crud.controller.arquivo.estado.EstadoControllerFactory;
import br.com.ada.crud.controller.arquivo.pais.PaisController;
import br.com.ada.crud.controller.arquivo.pais.PaisControllerFactory;
import br.com.ada.crud.model.cidade.Cidade;
import br.com.ada.crud.model.estado.Estado;
import br.com.ada.crud.view.CidadeView;
import br.com.ada.crud.view.EscolhaView;
import br.com.ada.crud.view.EstadoView;
import br.com.ada.crud.view.PaisView;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
    EscolhaView.Opcoes();
    }
}
