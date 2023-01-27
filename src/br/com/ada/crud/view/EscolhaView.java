package br.com.ada.crud.view;

import br.com.ada.crud.controller.arquivo.cidade.CidadeController;
import br.com.ada.crud.controller.arquivo.cidade.CidadeControllerFactory;
import br.com.ada.crud.controller.arquivo.estado.EstadoController;
import br.com.ada.crud.controller.arquivo.estado.EstadoControllerFactory;
import br.com.ada.crud.controller.arquivo.pais.PaisController;
import br.com.ada.crud.controller.arquivo.pais.PaisControllerFactory;

import java.util.Scanner;

public class EscolhaView {

    public static void Opcoes() {
        EstadoController estadoControllerController = EstadoControllerFactory
                .getInstance().criar();
        CidadeController cidadeControllerController = CidadeControllerFactory
                .getInstance().criar();
        PaisController paisControllerController = PaisControllerFactory
                .getInstance().criar();

        EstadoView estadoview = new EstadoView(
                estadoControllerController,
                new Scanner(System.in)
        );

        CidadeView cidadeview = new CidadeView(
                cidadeControllerController,
                new Scanner(System.in)
        );

        PaisView paisview = new PaisView(
                paisControllerController,
                new Scanner(System.in)
        );

        Scanner scanner = new Scanner(System.in);

        System.out.println("Infome a opção desejada:");
        System.out.println("1 - Pais");
        System.out.println("2 - Estado");
        System.out.println("3 - Cidade");
        System.out.println("0 - Sair");
        Integer opcao = scanner.nextInt();
        scanner.nextLine();
        switch (opcao) {
            case 1:
                paisview.exibirOpcoes();
                break;
            case 2:
                estadoview.exibirOpcoes();
                break;
            case 3:
                cidadeview.exibirOpcoes();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Opção invalida!");
        }
    }
}
