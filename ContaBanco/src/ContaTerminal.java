import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContaTerminal {

    private static List<String> listaAgencias = new ArrayList<>();
    private static List<Integer> listaContas = new ArrayList<>();
    private static double saldo = 0.0;
    private static String nomeCompleto = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Laço de repetição para que depois que o usuário cadastrar ele volte para a página inicial e efetue o login
        while (true) {
            int opcao = iniciarPaginaInicial(scanner);

            if (opcao == 1) {
                cadastrarConta(scanner);
                continue; // Volta para o início do loop para permitir o login após o cadastro
            }
            if (opcao == 2) {
                if (efetuarLogin(scanner)) {
                    break; // Sai do loop de página inicial após login bem-sucedido
                } else {
                    System.out.println("Agência ou conta inválida. Tente novamente.\n");
                }
            }
            if (opcao != 1 && opcao != 2) {
                System.out.println("Opção inválida, tente novamente!\n");
            }
        }

        // Laço de repetição para permitir que depois do usuário realizar a operação ele possa escolher outra
        while (true) {
            exibirMenuOperacoes();
            int opcao2 = scanner.nextInt();
            System.out.println();

            switch (opcao2) {
                case 1:
                    depositar(scanner);
                    break;
                case 2:
                    sacar(scanner);
                    break;
                case 3:
                    consultarSaldo();
                    break;
                case 4:
                    System.out.println("Programa finalizado com sucesso!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida, tente novamente!");
                    break;
            }
        }
    }

    // Método Página Inicial
    private static int iniciarPaginaInicial(Scanner scanner) {
        System.out.println("Seja bem-vindo ao Banco DIO!");
        System.out.println("==============================\n");

        System.out.println("""
            1 - Cadastrar
            2 - Entrar
        """);

        System.out.println("Por favor digite o número da opção escolhida:");
        int opcao = scanner.nextInt();
        System.out.println();
        return opcao;
    }

    // Método Cadastrar Conta
    private static void cadastrarConta(Scanner scanner) {
        System.out.println("Digite seu nome:");
        String nome = scanner.next();
        System.out.println();

        System.out.println("Digite seu sobrenome:");
        String sobrenome = scanner.next();
        System.out.println();

        nomeCompleto = nome + " " + sobrenome;
        
        String agencia = "067-8";
        int conta = 1021 + listaContas.size(); // Gera um número de conta sequencial
        listaAgencias.add(agencia);
        listaContas.add(conta);

        System.out.println("Olá " + nomeCompleto + ", obrigado por criar uma conta em nosso banco. Sua agência é: " 
        + agencia + " e sua conta é: " + conta + "\n");
    }

    // Método Efetuar Login
    private static boolean efetuarLogin(Scanner scanner) {
        System.out.println("Digite sua agência:");
        String agencia = scanner.next();
        System.out.println();

        System.out.println("Digite o número da Conta:");
        int conta = scanner.nextInt();
        System.out.println();

        if (listaAgencias.contains(agencia) && listaContas.contains(conta)) {
            System.out.println("Login efetuado com sucesso, seja bem-vindo!");
            return true;
        } else {
            return false;
        }
    }

    // Método Depositar e creditar ao saldo
    private static void depositar(Scanner scanner) {
        System.out.println("Por favor, digite o valor do depósito:");
        double deposito = scanner.nextDouble();
        saldo += deposito;
        System.out.println("Depósito realizado com sucesso! Seu saldo é: " + saldo + "\n");
    }
    
    // Método para sacar e debitar do saldo
    private static void sacar(Scanner scanner) {
        System.out.println("Por favor, digite o valor do saque:");
        double saque = scanner.nextDouble();
        if (saque <= saldo) {
            saldo -= saque;
            System.out.println("Saque realizado com sucesso! Seu saldo é: " + saldo + "\n");
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    // Método para visualizar o saldo disponível
    private static void consultarSaldo() {
        System.out.println("Atualmente, seu saldo é: " + saldo + "\n");
    }

    // Método para exibir o menu de operações após login
    private static void exibirMenuOperacoes() {
        System.out.println("""
            1 - Depositar
            2 - Sacar
            3 - Saldo
            4 - Sair
        """);

        System.out.println("Por favor, digite o número da opção escolhida:");
    }
}
