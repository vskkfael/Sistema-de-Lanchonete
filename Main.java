package com.example;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    static Scanner entrada = new Scanner(System.in);
    static int opcao, quantidade, quantidadePedidos = 0;
    static double total, valorPedido;
    static boolean continuar = true;
    static int tentativas = 0;
    static int[] pedidos = new int[100];
    static String metodoPagamento = ""; // Variável para armazenar o método de pagamento escolhido pelo usuário. 

    public static void Login(){
        // Login do usuário com 3 tentativas, caso o usuário erre as 3 tentativas o programa é encerrado.

        // Variáveis para armazenar o usuário e senha do administrador
        String usuarioAdmin = "admin";
        String senhaAdmin = "123";

            // Loop para solicitar o login do usuário
            do { 
                System.out.print("Usuário: ");
                String usuario = entrada.nextLine();

                System.out.print("Senha: ");
                String senha = entrada.nextLine();
                
                // Condicional para verificar se o usuário e senha estão corretos.
                if (usuario.equals(usuarioAdmin) && senha.equals(senhaAdmin)) {
                    System.out.println("Login bem-sucedido!");
                    continuar = false;
                    tentativas++;
                } else {
                    System.out.println("Usuário ou senha incorretos. Tente novamente.");
                    tentativas++;
                }

                // Verificar se o número máximo de tentativas foi atingido
                if (tentativas >= 3) {
                    System.out.println("Número máximo de tentativas atingidas. Encerrando o programa.");
                    System.exit(0);
                }


                // Verifica condição para continuar o loop de login.
            } while (continuar);
    }

    // Menu do painel de opções para o usuário escolher o que deseja fazer.
    public static void Menu() {
        System.out.println("==============================");
        System.out.println("************ MENU ************");
        System.out.println("==============================");
    }

    // Cardápio para pedido;
    public static void Cardapio() {

        do { // Loop para exibir o cardápio e solicitar a escolha do usuário, até que ele escolha encerrar a compra (opção 0).
            System.out.println("\n");
            System.out.println("=======Opções de cardápio=========");
            System.out.println("|1 - Hamburguer        |-| R$18.00");
            System.out.println("|2 - Pizza             |-| R$35.00");
            System.out.println("|3 - Refrigerante      |-| R$8.00 ");
            System.out.println("|4 - Batata Frita      |-| R$15.00");
            System.out.println("|0 - Encerrar compra   |-|        ");
            System.out.print("| - Escolha uma opção: ");
            opcao = Integer.parseInt(entrada.nextLine());
            pedidos[quantidadePedidos] = opcao;

            // Condicional para encerramento ou para solicitar a quantidade do pedido, caso o usuário escolha uma opção válida.
            if (opcao == 0) {
                break;
            } else {
                System.out.print("Quantidade: ");
                quantidade = Integer.parseInt(entrada.nextLine());
            }

            // Condicional de casos para cada opção do cardápio.
            switch (opcao) {
                case 1:
                    valorPedido = 18 * quantidade;
                    total += valorPedido;
                    quantidadePedidos++;
                    System.out.println("Hamburguer adicionado ao carrinho");
                    break;
                case 2:
                    valorPedido = 35 * quantidade;
                    total += valorPedido;
                    quantidadePedidos++;
                    System.out.println("Pizza adicionado ao carrinho");
                    break;
                case 3:
                    valorPedido = 8 * quantidade;
                    total += valorPedido;
                    quantidadePedidos++;
                    System.out.println("Refrigerante adicionado ao carrinho");
                    break;
                case 4:
                    valorPedido = 15 * quantidade;
                    total += valorPedido;
                    quantidadePedidos++; 
                    System.out.println("Batata frita adicionado ao carrinho");
                    break;
            }

            // Para exibir mensagem quando usuário escolher uma opção inválida.
            if (opcao < 1 || opcao > 4) {
                System.out.println("Opção inválida");
            }

            // Verifica condição para continuar o loop do cardápio.
        } while (opcao != 0);

    }

    // Para calcular e aplicar o desconto referente ao valor total da compra; (10% para compras acima de R$100,00 e 5% para compras abaixo de R$100,00).
    public static double Desconto(double preco){

        if(preco >= 100){
            double valorDesconto = (preco * 10) / 100;
            return preco - valorDesconto;
        }

        if(preco < 100){
            double valorDesconto = (preco * 5) / 100;
            return preco - valorDesconto;
        }

        return preco;
    }

    public static void DescontoAplicado(){
        if (total > 100) {
            System.out.println("Você recebeu um desconto de 10%!");
        } else {
            System.out.println("Você recebeu um desconto de 5%!");
        }
    }

    public static void confirmacaoPedido(){
        // Para exibir a confirmação do pedido, mostrando o valor total, a quantidade de pedidos e o valor com desconto aplicado.
        System.out.println("\n");
        System.out.println("***** CONFIRMAÇÃO DE PEDIDO *****");
        System.out.println("Valor Total: R$" + total);
        System.out.println("Quantidade de Pedidos: " + quantidadePedidos);
        DescontoAplicado();
        System.out.println("Valor com desconto: R$" + Desconto(total));

        System.out.print("Você deseja finalizar a compra? (S/N) ");
        String resposta = entrada.nextLine();

        // Condicional para verificar a resposta do usuário, caso ele queira finalizar a compra ou cancelar a compra.
        if(resposta.equalsIgnoreCase("S")){
            System.out.print("Finalizando compra...");
        } else {
            System.out.print("Compra cancelada. Volte sempre!");
            System.exit(0);
        }

        try {
            TimeUnit.SECONDS.sleep(2); // Simula o tempo de processamento da confirmação do pedido (2 segundos).
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    // Checkout para finalização de pedido.
    public static void Finalizacao() {
        System.out.println("\n");
        System.out.println("***** CHECKOUT *****");
        System.out.println("Valor Total: R$" + total);
        System.out.println("Quantidade de Pedidos: " + quantidadePedidos);
        DescontoAplicado(); // Para exibir o desconto aplicado no checkout.
        System.out.println("Valor com desconto: R$" + Desconto(total)); 
        System.out.println("Metodo de Pagamento: " + metodoPagamento);  
        System.out.println("Obrigado pela sua compra! Volte sempre!");
        System.out.println("=============================================================");

    }

    public static void Pagamento(){

        // Para exibir as opções de pagamento, e solicitar a escolha do usuário, e exibir a mensagem de pagamento aprovado ou recusado, utilizando um switch case para cada opção de pagamento.
    do {
        System.out.println("\n");
        System.out.println("***** OPÇÕES DE PAGAMENTO *****");
        System.out.println("|1 - Cartão de crédito |-| R$" + Desconto(total));
        System.out.println("|2 - Cartão de débito  |-| R$" + Desconto(total));
        System.out.println("|3 - Pix               |-| R$" + Desconto(total));
        System.out.print("| - Escolha uma opção: ");
        int opcaoPagamento = Integer.parseInt(entrada.nextLine());

        // Condicional para verificar a opção de pagamento escolhida pelo usuário, e exibir a mensagem de pagamento aprovado ou recusado, utilizando um switch case para cada opção de pagamento.
        switch (opcaoPagamento) {
            case 1:
                metodoPagamento = "Cartão de crédito";
                System.out.println("Pagamento aprovado com cartão de crédito!");
                System.out.print("Processando pagamento...");
                break;
            case 2:
                metodoPagamento = "Cartão de débito";
                System.out.println("Pagamento aprovado com cartão de débito!");
                System.out.print("Processando pagamento...");
                break;
            case 3:
                metodoPagamento = "Pix";
                System.out.println("Pagamento aprovado com Pix!");
                System.out.print("Processando pagamento...");
                break;
            default:
                System.out.println("Opção de pagamento inválida. Pagamento recusado.");
                System.out.print("Tente novamente!");
                break;
        }

        try {
            TimeUnit.SECONDS.sleep(2); // Simula o tempo de processamento da confirmação do pedido (2 segundos).
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    } while (metodoPagamento.isEmpty()); // Loop para continuar solicitando a opção de pagamento até que o usuário escolha uma opção válida.
    }

    public static void main(String[] args) {
    // Fluxo do programa: Login -> Menu -> Cardápio -> Finalização.

        // Tela de login;
        Login();

            // Menu do painel;
            Menu();

                // Cardápio para pedido;
                Cardapio();

                    // Confirmar pedido.
                    confirmacaoPedido();

                        // Pagamento
                        Pagamento();

                            // Checkout de finalização;
                            Finalizacao();
    }

}