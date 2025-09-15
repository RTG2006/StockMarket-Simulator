package org.example;

import java.util.*;

public class StockSimulator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Portfolio portfolio = new Portfolio();
        double balance = 10000.0;  // Starting cash

        // Market stocks (13 companies)
        Stock apple = new Stock("AAPL", "Apple Inc.", 150.0);
        Stock google = new Stock("GOOGL", "Alphabet Inc.", 2800.0);
        Stock amazon = new Stock("AMZN", "Amazon.com Inc.", 3400.0);
        Stock microsoft = new Stock("MSFT", "Microsoft Corp.", 310.0);
        Stock tesla = new Stock("TSLA", "Tesla Inc.", 700.0);
        Stock netflix = new Stock("NFLX", "Netflix Inc.", 600.0);
        Stock meta = new Stock("META", "Meta Platforms Inc.", 250.0);
        Stock nvidia = new Stock("NVDA", "NVIDIA Corp.", 450.0);
        Stock adobe = new Stock("ADBE", "Adobe Inc.", 500.0);
        Stock intel = new Stock("INTC", "Intel Corp.", 40.0);
        Stock ibm = new Stock("IBM", "IBM Corp.", 135.0);
        Stock disney = new Stock("DIS", "Walt Disney Co.", 100.0);
        Stock pepsi = new Stock("PEP", "PepsiCo Inc.", 180.0);

        ArrayList<Stock> market = new ArrayList<>(Arrays.asList(
                apple, google, amazon, microsoft, tesla, netflix, meta,
                nvidia, adobe, intel, ibm, disney, pepsi
        ));

        while (true) {
            System.out.println("\n--- Stock Market Simulator ---");
            System.out.println("Balance: $" + String.format("%.2f", balance));
            System.out.println("1. View Market Stocks");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Show Net Worth & Profit/Loss");
            System.out.println("6. Calculator");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.println("Market Stocks:");
                for (Stock stock : market) {
                    String priceStr = StockAPI.getStockPrice(stock.getSymbol());
                    try {
                        double price = Double.parseDouble(priceStr);
                        stock.setPrice(price);
                        System.out.println(stock.getSymbol() + " (" + stock.getCompanyName() + ") - $" + price);
                    } catch (Exception e) {
                        System.out.println(stock.getSymbol() + " price unavailable");
                    }
                }

            } else if (choice == 2) {
                System.out.print("Enter stock symbol to buy: ");
                String symbol = sc.next();
                Stock selected = null;
                for (Stock s : market) {
                    if (s.getSymbol().equalsIgnoreCase(symbol)) selected = s;
                }
                if (selected != null) {
                    System.out.print("Enter quantity: ");
                    int qty = sc.nextInt();
                    double cost = selected.getPrice() * qty;
                    if (balance >= cost) {
                        balance -= cost;
                        portfolio.buyStock(selected, qty);
                        System.out.println("Bought " + qty + " of " + selected.getSymbol());
                    } else {
                        System.out.println("Not enough balance!");
                    }
                } else {
                    System.out.println("Stock not found!");
                }

            } else if (choice == 3) {
                System.out.print("Enter stock symbol to sell: ");
                String symbol = sc.next();
                System.out.print("Enter quantity: ");
                int qty = sc.nextInt();
                double price = 0;
                for (Stock s : market) {
                    if (s.getSymbol().equalsIgnoreCase(symbol)) price = s.getPrice();
                }
                if (portfolio.sellStock(symbol, qty)) {
                    balance += price * qty;
                    System.out.println("Sold " + qty + " of " + symbol);
                } else {
                    System.out.println("You don't own that many shares!");
                }

            } else if (choice == 4) {
                portfolio.showPortfolio();

            } else if (choice == 5) {
                // Show Net Worth & Profit/Loss
                double portfolioValue = 0.0;
                for (Stock s : portfolio.getStocks()) {
                    String priceStr = StockAPI.getStockPrice(s.getSymbol());
                    try {
                        double currentPrice = Double.parseDouble(priceStr);
                        portfolioValue += currentPrice * s.getQuantity();
                    } catch (Exception e) {
                        portfolioValue += s.getPrice() * s.getQuantity();
                    }
                }
                double netWorth = balance + portfolioValue;
                double profitLoss = netWorth - 10000.0; // compared to starting balance
                System.out.println("Cash Balance: $" + String.format("%.2f", balance));
                System.out.println("Portfolio Value: $" + String.format("%.2f", portfolioValue));
                System.out.println("Net Worth: $" + String.format("%.2f", netWorth));
                System.out.println("Profit/Loss: $" + String.format("%.2f", profitLoss));

            } else if (choice == 6) {
                Calculator.startCalculator();

            } else if (choice == 7) {
                System.out.println("Exiting... Final balance: $" + balance);
                break;

            } else {
                System.out.println("Invalid choice!");
            }
        }

        sc.close();
    }
}