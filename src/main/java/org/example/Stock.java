package org.example;

public class Stock {
    private String symbol;
    private String companyName;
    private double price;
    private int quantity;  // ✅ new field

    // Default constructor
    public Stock() {
        this.symbol = "N/A";
        this.companyName = "N/A";
        this.price = 0.0;
        this.quantity = 0;
    }

    // Parameterized constructor
    public Stock(String symbol, String companyName, double price) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.price = price;
        this.quantity = 0;
    }

    // Copy constructor
    public Stock(Stock other) {
        this.symbol = other.symbol;
        this.companyName = other.companyName;
        this.price = other.price;
        this.quantity = other.quantity;
    }

    // Getters and Setters
    public String getSymbol() { return symbol; }
    public String getCompanyName() { return companyName; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    public void setPrice(double price) { this.price = price; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return symbol + " (" + companyName + ") - $" + price + " x " + quantity;
    }
}