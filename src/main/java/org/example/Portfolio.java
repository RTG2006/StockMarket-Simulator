package org.example;

import java.util.ArrayList;

public class Portfolio {
    private ArrayList<Stock> stocks;

    public Portfolio() {
        stocks = new ArrayList<>();
    }

    public void buyStock(Stock stock, int qty) {
        for (Stock s : stocks) {
            if (s.getSymbol().equalsIgnoreCase(stock.getSymbol())) {
                s.setQuantity(s.getQuantity() + qty);
                return;
            }
        }
        Stock newStock = new Stock(stock);
        newStock.setQuantity(qty);
        stocks.add(newStock);
    }

    public boolean sellStock(String symbol, int qty) {
        for (Stock s : stocks) {
            if (s.getSymbol().equalsIgnoreCase(symbol)) {
                if (s.getQuantity() >= qty) {
                    s.setQuantity(s.getQuantity() - qty);
                    if (s.getQuantity() == 0) stocks.remove(s);
                    return true;
                }
            }
        }
        return false;
    }

    public void showPortfolio() {
        if (stocks.isEmpty()) {
            System.out.println("Your portfolio is empty.");
        } else {
            System.out.println("Your Portfolio:");
            for (Stock stock : stocks) {
                System.out.println(stock);
            }
        }
    }

    public ArrayList<Stock> getStocks() {
        return stocks;
    }
}