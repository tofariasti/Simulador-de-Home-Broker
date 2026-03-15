package com.example;

public class HomeBroker {
    public static void main(String[] args) {

        String[][] maketData = new String[5][3];
        int colStockSymbol = 0;
        int colCurrentPrice = 1;
        int colAvailableQuantity = 2;

        printMarketData(maketData);

        System.out.println("ASD! Simulador de Home Broker iniciado!");
        System.out.println("Use comandos de terminal para interagir.");
    }

    public static void printMarketData(String[][] marketData) {
        System.out.println("Mercado Atual:");
        System.out.println("Símbolo | Preço Atual | Quantidade Disponível");
        for (String[] stock : marketData) {
            System.out.printf("%s | %s | %s%n", stock[0], stock[1], stock[2]);
        }
    }
}
