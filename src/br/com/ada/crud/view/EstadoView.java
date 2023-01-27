package br.com.ada.crud.view;

import br.com.ada.crud.controller.arquivo.estado.EstadoController;
import br.com.ada.crud.controller.exception.NaoEncontrado;
import br.com.ada.crud.model.estado.Estado;

import java.util.List;
import java.util.Scanner;

public class EstadoView {
    private EstadoController controller;
    private Scanner scanner;

    public EstadoView(
            EstadoController controller,
            Scanner scanner
    ) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public void cadastrar() {
        Estado estado = new Estado();

        System.out.println("Informe o nome:");
        String nome = scanner.nextLine();
        estado.setNome(nome);

        //System.out.println("Informe a id: ");
        //Integer id = scanner.nextInt();
        //cidade.setId(id);

        System.out.println("Informe o pais:");
        String pais = scanner.nextLine();
        estado.setPais(pais);

        controller.cadastrar(estado);
    }

    public void listar(Integer id) {
        Estado estado = controller.ler(id);
        exibirEstado(estado);
    }

    public void atualizar() {
        listar();
        System.out.println("Informe o número do estado que deseja atualizar:");
        Integer numeroEstado = scanner.nextInt();
        scanner.nextLine();
        Estado estado = controller.listar().get(numeroEstado - 1);
        atualizar(estado);
    }

    public void atualizar(Estado estado) {
        exibirEstado(estado);

        System.out.println("Informe o novo nome:");
        String nome = scanner.nextLine();
        estado.setNome(nome);

        //System.out.println("Informe o novo id:");
        //Integer id = scanner.nextInt();
        //cidade.setId(id);

        System.out.println("Informe o pais:");
        String pais = scanner.nextLine();
        estado.setPais(pais);

        try {
            controller.update(estado.getId(), estado);
        } catch (NaoEncontrado ex) {
            System.out.println("Estado informado não existe na base. Tente novamente.");
        }
    }

    public void apagar() {
        listar();
        System.out.println("Informe o número do estado que deseja apagar:");
        Integer numero = scanner.nextInt();
        scanner.nextLine();
        Estado estado = controller.listar().get(numero - 1);
        apagar(estado.getId());
    }

    public void apagar(Integer id) {
        try {
            Estado estado = controller.delete(id);
            System.out.println("Estado apagado foi:");
            exibirEstado(estado);
        } catch (NaoEncontrado ex) {
            System.out.println("Estado não foi agapado pois não foi localizada. Tente novamente!");
        }
    }

    public void listar() {
        List<Estado> estados = controller.listar();
        System.out.println("| Número | Nome        | id       | Pais     |");
        for (int index = 0; index < estados.size(); index++) {
            System.out.print("| " + (index + 1) + "      ");
            exibirEstado(estados.get(index));
        }
    }

    public void exibirEstado(Estado estado) {
        System.out.print("| ");
        System.out.print(estado.getNome());
        System.out.print("    | ");
        System.out.print(estado.getId());
        System.out.print("    |  ");
        System.out.print(estado.getPais());
        System.out.println("    |");
    }

    public void exibirOpcoes() {
        System.out.println("Infome a opção desejada:");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Listar");
        System.out.println("3 - Atualizar");
        System.out.println("4 - Apagar");
        System.out.println("5 - Voltar ao inicio");
        System.out.println("0 - Sair");
        Integer opcao = scanner.nextInt();
        scanner.nextLine();
        switch (opcao) {
            case 1:
                cadastrar();
                break;
            case 2:
                listar();
                break;
            case 3:
                atualizar();
                break;
            case 4:
                apagar();
                break;
            case 5:
                EscolhaView.Opcoes();
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Opção invalida!");
        }
        exibirOpcoes();
    }
}
