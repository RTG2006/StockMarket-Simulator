import java.util.Random;

public class StockAPI {
    private static final Random random = new Random();

    public static String getStockPrice(String symbol) {
        // Generate a random stock price between 100.00 and 1000.00
        double price = 100 + (900 * random.nextDouble());
        // Round to 2 decimal places
        price = Math.round(price * 100.0) / 100.0;
        return String.valueOf(price);
    }
}
