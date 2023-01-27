package br.com.ada.crud.view;

import br.com.ada.crud.controller.arquivo.cidade.CidadeController;
import br.com.ada.crud.model.cidade.Cidade;
import br.com.ada.crud.controller.exception.NaoEncontrado;

import java.util.List;
import java.util.Scanner;

public class CidadeView {
    private CidadeController controller;
    private Scanner scanner;

    public CidadeView(
            CidadeController controller,
            Scanner scanner
    ) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public void cadastrar() {
        Cidade cidade = new Cidade();

        System.out.println("Informe o nome:");
        String nome = scanner.nextLine();
        cidade.setNome(nome);

        //System.out.println("Informe a id: ");
        //Integer id = scanner.nextInt();
        //cidade.setId(id);

        System.out.println("Informe o UF:");
        String uf = scanner.nextLine();
        cidade.setUf(uf);

        controller.cadastrar(cidade);
    }

    public void listar(Integer id) {
        Cidade cidade = controller.ler(id);
        exibirCidade(cidade);
    }

    public void atualizar() {
        listar();
        System.out.println("Informe o número da cidade que deseja atualizar:");
        Integer numeroCidade = scanner.nextInt();
        scanner.nextLine();
        Cidade cidade = controller.listar().get(numeroCidade - 1);
        atualizar(cidade);
    }

    public void atualizar(Cidade cidade) {
        exibirCidade(cidade);

        System.out.println("Informe o novo nome:");
        String nome = scanner.nextLine();
        cidade.setNome(nome);

        System.out.println("Informe o novo id:");
        Integer id = scanner.nextInt();
        cidade.setId(id);

        System.out.println("Informe o UF:");
        String uf = scanner.nextLine();
        cidade.setUf(uf);

        try {
            controller.update(cidade.getId(), cidade);
        } catch (NaoEncontrado ex) {
            System.out.println("Cidade informada não existe na base. Tente novamente.");
        }
    }

    public void apagar() {
        listar();
        System.out.println("Informe o número da cidade que deseja apagar:");
        Integer numero = scanner.nextInt();
        scanner.nextLine();
        Cidade cidade = controller.listar().get(numero - 1);
        apagar(cidade.getId());
    }

    public void apagar(Integer id) {
        try {
            Cidade cidade = controller.delete(id);
            System.out.println("Cidade apagada foi:");
            exibirCidade(cidade);
        } catch (NaoEncontrado ex) {
            System.out.println("Cidade não foi agapada pois não foi localizada. Tente novamente!");
        }
    }

    public void listar() {
        List<Cidade> cidades = controller.listar();
        System.out.println("| Número | Nome        | id       | UF     |");
        for (int index = 0; index < cidades.size(); index++) {
            System.out.print("| " + (index + 1) + "      ");
            exibirCidade(cidades.get(index));
        }
    }

    public void exibirCidade(Cidade cidade) {
        System.out.print("| ");
        System.out.print(cidade.getNome());
        System.out.print("    | ");
        System.out.print(cidade.getId());
        System.out.print("    |  ");
        System.out.print(cidade.getUf());
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
