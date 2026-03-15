package com.example;

public class HomeBroker {
    public static void main(String[] args) {

        String[][] marketData = new String[5][3];
        String[][] userPortfolio = new String[5][3];

        buildMarketData(marketData);
        printMarketData(marketData);
    }

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
