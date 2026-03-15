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
        System.out.println("1. Listar");
        System.out.println("2. Comprar");
        System.out.println("3. Vender");
        System.out.println("4. Ver Portfolio");
        System.out.println("5. Ver Balanço");
        System.out.println("0. Sair");

        Scanner scannerMenuItem = new Scanner(System.in);

        switch (scannerMenuItem.nextInt()) {
            case 1:
                System.out.println("Listar");
                printMarketData(marketData);
                break;
            case 2:
                System.out.println("Comprar");
                break;
            case 3:
                System.out.println("Vender");
                break;
            case 4:
                System.out.println("Ver Portfolio");
                printUserPortfolio(userPortfolio);
                break;
            case 5:
                System.out.println("Ver Balanço");
                break;
            case 0:
                System.out.println("Sair");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    /**
     * Imprime os dados de mercado, mostrando o símbolo, preço e quantidade disponível para cada ação.
     * @param marketData
     */
    public static void printMarketData(String[][] marketData) {
        System.out.println("Mercado Atual:");
        System.out.println("Símbolo | Preço | Quant. Disponível");
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

        System.out.println("Símbolo | Preço | Quantidade");
        for (String[] stock : userPortfolio) {
            if (stock == null || stock[0] == null) {
                continue;
            }
            System.out.printf("%s | %s | %s%n", stock[0], stock[1], stock[2]);
        }
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
}
