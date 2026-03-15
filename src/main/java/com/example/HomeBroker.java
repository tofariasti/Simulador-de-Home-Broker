package com.example;

import java.util.Scanner;

public class HomeBroker {
    static String[][] marketData = new String[5][3];
    static String[][] userPortfolio = new String[5][3];

    public static void main(String[] args) {
        System.out.println("Bem-vindo ao Home Broker!");

        buildMarketData(marketData);
        //printMarketData(marketData);
        buildMenu();
    }

    private static void buildMenu() {

        Scanner scannerMenuItem = new Scanner(System.in);
        int option;

        do {
            System.out.println("1. Listar");
            System.out.println("2. Comprar");
            System.out.println("3. Vender");
            System.out.println("4. Ver Portfolio");
            System.out.println("5. Ver Balanço");
            System.out.println("0. Sair");

            System.out.print("Escolha uma opção: ");
            option = scannerMenuItem.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Listar");
                    printMarketData(marketData);
                    break;
                case 2:
                    System.out.println("Comprar");
                    buyStock(marketData, userPortfolio, scannerMenuItem);
                    break;
                case 3:
                    System.out.println("Vender");
                    sellStock(marketData, userPortfolio, scannerMenuItem);
                    break;
                case 4:
                    System.out.println("Ver Portfolio");
                    printUserPortfolio(userPortfolio);
                    break;
                case 5:
                    System.out.println("Ver Balanço");
                    printBalance(userPortfolio);
                    break;
                case 0:
                    System.out.println("Sair");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (option != 0);
    }

    /**
     * Imprime os dados de mercado, mostrando o símbolo, preço e quantidade disponível para cada ação.
     * @param marketData
     */
    public static void printMarketData(String[][] marketData) {
        System.out.println("Mercado Atual:");
        System.out.println("Símbolo | Preço (R$) | Quant. Disponível");
        for (String[] stock : marketData) {
            if (stock == null || stock[0] == null) {
                continue;
            }
            System.out.printf("%s | %s | %s%n", stock[0], stock[1], stock[2]);
        }
    }

    /**
     * Imprime os dados do portfólio do usuário, mostrando o símbolo, preço e quantidade disponível para cada ação.
     * @param userPortfolio
     */
    public static void printUserPortfolio(String[][] userPortfolio) {
        System.out.println("Seu Portfólio:");

        System.out.println("Símbolo | Preço (R$) | Quantidade | Total (R$)");
        for (String[] stock : userPortfolio) {
            if (stock == null || stock[0] == null) {
                continue;
            }
            double preco;
            int quantidade;
            try {
                preco = Double.parseDouble(stock[1]);
                quantidade = Integer.parseInt(stock[2]);
            } catch (NumberFormatException e) {
                System.out.printf("%s | %s | %s | %s%n", stock[0], stock[1], stock[2], "N/A");
                continue;
            }
            double total = preco * quantidade;
            System.out.printf("%s | %s | %s | %.2f%n", stock[0], stock[1], stock[2], total);
        }
    }

    public static void printBalance(String[][] userPortfolio) {
        System.out.println("Seu Balanço:");

        double totalBalance = 0.0;
        boolean hasStocks = false;

        System.out.println("Símbolo | Preço (R$) | Quantidade | Total (R$)");
        for (String[] stock : userPortfolio) {
            if (stock == null || stock[0] == null) {
                continue;
            }
            hasStocks = true;
            double preco;
            int quantidade;
            try {
                preco = Double.parseDouble(stock[1]);
                quantidade = Integer.parseInt(stock[2]);
            } catch (NumberFormatException e) {
                System.out.printf("%s | %s | %s | %s%n", stock[0], stock[1], stock[2], "N/A");
                continue;
            }
            double total = preco * quantidade;
            totalBalance += total;
            System.out.printf("%s | %s | %s | %.2f%n", stock[0], stock[1], stock[2], total);
        }

        if (!hasStocks) {
            System.out.println("Nenhuma ação no portfólio.");
        }

        System.out.printf("Total do Portfólio: R$ %.2f%n", totalBalance);
    }

    /**
     * Constrói os dados de mercado com ações, preços e quantidades disponíveis.
     * @param marketData
     */
    public static void buildMarketData(String[][] marketData) {
        
        marketData[0][0] = "PETR4";
        marketData[0][1] = "32.50";
        marketData[0][2] = "1000";

        marketData[1][0] = "VALE3";
        marketData[1][1] = "68.90";
        marketData[1][2] = "1000";

        marketData[2][0] = "ITUB4";
        marketData[2][1] = "29.10";
        marketData[2][2] = "1000";

        marketData[3][0] = "BBDC4";
        marketData[3][1] = "15.20";
        marketData[3][2] = "500";

        marketData[4][0] = "ABEV3";
        marketData[4][1] = "12.40";
        marketData[4][2] = "750";
    }

    /**
     * Compra uma ação do mercado e adiciona ao portfólio do usuário.
     * @param marketData dados de mercado
     * @param userPortfolio portfólio do usuário
     * @param scanner objeto Scanner para leitura do stdin
     */
    private static void buyStock(String[][] marketData, String[][] userPortfolio, Scanner scanner) {
        System.out.print("Informe o símbolo da ação: ");
        String symbol = scanner.next();
        System.out.print("Informe a quantidade a comprar: ");
        int quantidade;
        try {
            quantidade = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Quantidade inválida. Operação cancelada.");
            return;
        }

        int idxMarket = -1;
        for (int i = 0; i < marketData.length; i++) {
            if (marketData[i] != null && marketData[i][0] != null && marketData[i][0].equalsIgnoreCase(symbol)) {
                idxMarket = i;
                break;
            }
        }

        if (idxMarket == -1) {
            System.out.println("Ação não encontrada no mercado.");
            return;
        }

        int disponivel;
        try {
            disponivel = Integer.parseInt(marketData[idxMarket][2]);
        } catch (NumberFormatException e) {
            System.out.println("Quantidade no mercado inválida. Não é possível comprar.");
            return;
        }

        if (quantidade <= 0 || quantidade > disponivel) {
            System.out.println("Quantidade inválida ou maior que o disponível no mercado.");
            return;
        }

        // Ajusta o mercado
        marketData[idxMarket][2] = String.valueOf(disponivel - quantidade);

        // Atualiza portfólio do usuário
        int idxPortfolio = -1;
        for (int i = 0; i < userPortfolio.length; i++) {
            if (userPortfolio[i] != null && userPortfolio[i][0] != null && userPortfolio[i][0].equalsIgnoreCase(symbol)) {
                idxPortfolio = i;
                break;
            }
        }

        if (idxPortfolio >= 0) {
            int atual = Integer.parseInt(userPortfolio[idxPortfolio][2]);
            userPortfolio[idxPortfolio][2] = String.valueOf(atual + quantidade);
        } else {
            for (int i = 0; i < userPortfolio.length; i++) {
                if (userPortfolio[i] == null) {
                    userPortfolio[i] = new String[3];
                }
                if (userPortfolio[i][0] == null) {
                    userPortfolio[i][0] = symbol.toUpperCase();
                    userPortfolio[i][1] = marketData[idxMarket][1];
                    userPortfolio[i][2] = String.valueOf(quantidade);
                    idxPortfolio = i;
                    break;
                }
            }
        }

        System.out.printf("Compra realizada: %s %d ações a R$ %s.\n", symbol.toUpperCase(), quantidade, marketData[idxMarket][1]);
    }

    /**
     * Vende uma ação do portfólio e devolve ao mercado.
     * @param marketData dados de mercado
     * @param userPortfolio portfólio do usuário
     * @param scanner objeto Scanner para leitura do stdin
     */
    private static void sellStock(String[][] marketData, String[][] userPortfolio, Scanner scanner) {
        System.out.print("Informe o símbolo da ação: ");
        String symbol = scanner.next();
        System.out.print("Informe a quantidade a vender: ");
        int quantidade;
        try {
            quantidade = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Quantidade inválida. Operação cancelada.");
            return;
        }

        int idxPortfolio = -1;
        for (int i = 0; i < userPortfolio.length; i++) {
            if (userPortfolio[i] != null && userPortfolio[i][0] != null && userPortfolio[i][0].equalsIgnoreCase(symbol)) {
                idxPortfolio = i;
                break;
            }
        }

        if (idxPortfolio == -1) {
            System.out.println("Ação não encontrada no portfólio.");
            return;
        }

        int disponivelUsuario;
        try {
            disponivelUsuario = Integer.parseInt(userPortfolio[idxPortfolio][2]);
        } catch (NumberFormatException e) {
            System.out.println("Quantidade no portfólio inválida. Não é possível vender.");
            return;
        }

        if (quantidade <= 0 || quantidade > disponivelUsuario) {
            System.out.println("Quantidade inválida ou maior que o disponível no portfólio.");
            return;
        }

        double precoUnitario;
        try {
            precoUnitario = Double.parseDouble(userPortfolio[idxPortfolio][1]);
        } catch (NumberFormatException e) {
            System.out.println("Preço inválido no portfólio. Não é possível vender.");
            return;
        }

        userPortfolio[idxPortfolio][2] = String.valueOf(disponivelUsuario - quantidade);
        if (Integer.parseInt(userPortfolio[idxPortfolio][2]) == 0) {
            userPortfolio[idxPortfolio][0] = null;
            userPortfolio[idxPortfolio][1] = null;
            userPortfolio[idxPortfolio][2] = null;
        }

        int idxMarket = -1;
        for (int i = 0; i < marketData.length; i++) {
            if (marketData[i] != null && marketData[i][0] != null && marketData[i][0].equalsIgnoreCase(symbol)) {
                idxMarket = i;
                break;
            }
        }

        if (idxMarket != -1) {
            int disponivelMercado;
            try {
                disponivelMercado = Integer.parseInt(marketData[idxMarket][2]);
            } catch (NumberFormatException e) {
                disponivelMercado = 0;
            }
            marketData[idxMarket][2] = String.valueOf(disponivelMercado + quantidade);
        }

        double totalRecebido = precoUnitario * quantidade;
        System.out.printf("Venda realizada: %s %d ações a R$ %.2f (total R$ %.2f).\n", symbol.toUpperCase(), quantidade, precoUnitario, totalRecebido);
    }
}
