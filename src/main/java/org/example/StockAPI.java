package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StockAPI {
    public static String getStockPrice(String symbol) {
        String price = "0.0";
        try {
            // ✅ Use the Python from your venv
            ProcessBuilder pb = new ProcessBuilder(
                    "./venv/bin/python3", "python/get_stock_price.py", symbol
            );
            pb.redirectErrorStream(true);
            Process process = pb.start();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );
            price = reader.readLine();

            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return price;
    }
}