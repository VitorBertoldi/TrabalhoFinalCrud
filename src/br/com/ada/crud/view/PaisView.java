package br.com.ada.crud.view;

import br.com.ada.crud.controller.arquivo.pais.PaisController;
import br.com.ada.crud.controller.exception.NaoEncontrado;
import br.com.ada.crud.model.pais.Pais;

import java.util.List;
import java.util.Scanner;

public class PaisView {

    private PaisController controller;
    private Scanner scanner;

    public PaisView(
            PaisController controller,
            Scanner scanner
    ) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public void cadastrar() {
        Pais pais = new Pais();

        System.out.println("Informe o nome:");
        String nome = scanner.nextLine();
        pais.setNome(nome);

        //System.out.println("Informe a id: ");
        //Integer id = scanner.nextInt();
        //cidade.setId(id);

        System.out.println("Informe o continente:");
        String continente = scanner.nextLine();
        pais.setContinente(continente);

        controller.cadastrar(pais);
    }

    public void listar(Integer id) {
        Pais pais = controller.ler(id);
        exibirPais(pais);
    }

    public void atualizar() {
        listar();
        System.out.println("Informe o número do pais que deseja atualizar:");
        Integer numeroEstado = scanner.nextInt();
        scanner.nextLine();
        Pais pais = controller.listar().get(numeroEstado - 1);
        atualizar(pais);
    }

    public void atualizar(Pais pais) {
        exibirPais(pais);

        System.out.println("Informe o novo nome:");
        String nome = scanner.nextLine();
        pais.setNome(nome);

        //System.out.println("Informe o novo id:");
        //Integer id = scanner.nextInt();
        //cidade.setId(id);

        System.out.println("Informe o continente:");
        String continente = scanner.nextLine();
        pais.setContinente(continente);

        try {
            controller.update(pais.getId(), pais);
        } catch (NaoEncontrado ex) {
            System.out.println("Pais informado não existe na base. Tente novamente.");
        }
    }

    public void apagar() {
        listar();
        System.out.println("Informe o número do pais que deseja apagar:");
        Integer numero = scanner.nextInt();
        scanner.nextLine();
        Pais pais = controller.listar().get(numero - 1);
        apagar(pais.getId());
    }

    public void apagar(Integer id) {
        try {
            Pais pais = controller.delete(id);
            System.out.println("Pais apagado foi:");
            exibirPais(pais);
        } catch (NaoEncontrado ex) {
            System.out.println("Pais não foi agapado pois não foi localizada. Tente novamente!");
        }
    }

    public void listar() {
        List<Pais> paises = controller.listar();
        System.out.println("| Número | Nome        | id       | Continente     |");
        for (int index = 0; index < paises.size(); index++) {
            System.out.print("| " + (index + 1) + "      ");
            exibirPais(paises.get(index));
        }
    }

    public void exibirPais(Pais pais) {
        System.out.print("| ");
        System.out.print(pais.getNome());
        System.out.print("    | ");
        System.out.print(pais.getId());
        System.out.print("    |  ");
        System.out.print(pais.getContinente());
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
