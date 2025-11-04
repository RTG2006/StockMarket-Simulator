import java.util.Scanner;
// No need for explicit StockSimulator imports if methods are standalone

public class Calculator {

    public static void startCalculator() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Financial Calculation Tools ---");
            System.out.println("1. Calculate Trade Profit/Loss (Gross)");
            System.out.println("2. Calculate Weighted Average Stock Price");
            System.out.println("3. Calculate Net Profit (with Brokerage/Fees)"); // <-- NEW FEATURE
            System.out.println("4. Return to Simulator Menu");
            System.out.print("Enter choice: ");

            int choice = 0;
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); // Consume the invalid input
                continue;
            }

            if (choice == 4) {
                System.out.println("Returning to Simulator Menu...");
                break;
            }

            switch (choice) {
                case 1:
                    calculateGrossProfitLoss(sc); // Renamed for clarity
                    break;
                case 2:
                    calculateWeightedAverage(sc);
                    break;
                case 3:
                    calculateNetProfit(sc); // <-- New function call
                    break;
                default:
                    System.out.println("Invalid choice! Please select an option from the menu.");
            }
        }
    }

    // --- 1. GROSS PROFIT/LOSS CALCULATION ---

    /**
     * Calculates the Gross Profit/Loss and Percentage Return for a single trade.
     */
    public static void calculateGrossProfitLoss(Scanner sc) {
        System.out.println("\n--- Gross Profit/Loss Calculator ---");
        System.out.print("Enter purchase price per share: $");
        double buyPrice = sc.nextDouble();
        System.out.print("Enter selling price per share: $");
        double sellPrice = sc.nextDouble();
        System.out.print("Enter number of shares: ");
        int shares = sc.nextInt();

        double grossProfitLoss = (sellPrice - buyPrice) * shares;
        double totalInvestment = buyPrice * shares;
        double percentageReturn = 0;

        if (totalInvestment != 0) {
            percentageReturn = (grossProfitLoss / totalInvestment) * 100;
        }

        System.out.println("\n--- Results ---");
        System.out.printf("Gross Profit/Loss (Before Fees): $%.2f%n", grossProfitLoss);
        System.out.printf("Percentage Return (ROI): %.2f%%%n", percentageReturn);

        if (grossProfitLoss > 0) {
            System.out.println("**Status: Profit!**");
        } else if (grossProfitLoss < 0) {
            System.out.println("**Status: Loss.**");
        } else {
            System.out.println("**Status: Break-even.**");
        }
    }

    // --- 2. WEIGHTED AVERAGE PRICE CALCULATION ---

    /**
     * Calculates the Weighted Average Price for a stock bought multiple times.
     */
    public static void calculateWeightedAverage(Scanner sc) {
        System.out.println("\n--- Weighted Average Price Calculator ---");
        System.out.print("Enter the number of different purchase transactions: ");
        int numTransactions = sc.nextInt();

        double totalCost = 0;
        int totalShares = 0;

        for (int i = 1; i <= numTransactions; i++) {
            System.out.println("\n--- Transaction " + i + " ---");
            System.out.print("Enter price for transaction " + i + ": $");
            double price = sc.nextDouble();
            System.out.print("Enter shares bought in transaction " + i + ": ");
            int shares = sc.nextInt();

            totalCost += (price * shares);
            totalShares += shares;
        }

        if (totalShares > 0) {
            double weightedAveragePrice = totalCost / totalShares;
            System.out.println("\n--- Result ---");
            System.out.printf("Total Shares Owned: %d%n", totalShares);
            System.out.printf("Total Cost of Investment: $%.2f%n", totalCost);
            System.out.printf("Your Weighted Average Price per Share (Break-even): **$%.2f**%n", weightedAveragePrice);
        } else {
            System.out.println("Error: Cannot calculate average with zero shares purchased.");
        }
    }

    // --- 3. NET PROFIT CALCULATION (NEW) ---

    /**
     * Calculates the Net Profit/Loss after deducting transaction fees.
     * Uses a simplified fixed percentage model for fees for simulation.
     */
    public static void calculateNetProfit(Scanner sc) {
        System.out.println("\n--- Net Profit/Brokerage Calculator ---");
        System.out.print("Enter purchase price per share: $");
        double buyPrice = sc.nextDouble();
        System.out.print("Enter selling price per share: $");
        double sellPrice = sc.nextDouble();
        System.out.print("Enter number of shares: ");
        int shares = sc.nextInt();

        // Simplified Fee Model for Simulation
        double brokerageRate = 0.005; // 0.5% of total transaction value (buy + sell)
        double taxRate = 0.0005;      // 0.05% for other taxes/fees

        double buyValue = buyPrice * shares;
        double sellValue = sellPrice * shares;
        double totalTurnover = buyValue + sellValue;

        // Calculate Fees
        double brokerageFee = totalTurnover * brokerageRate;
        double taxFee = totalTurnover * taxRate;
        double totalFees = brokerageFee + taxFee;

        // Calculate Profit
        double grossProfitLoss = sellValue - buyValue;
        double netProfitLoss = grossProfitLoss - totalFees;
        double totalInvestment = buyValue;
        double netPercentageReturn = 0;

        if (totalInvestment != 0) {
            netPercentageReturn = (netProfitLoss / totalInvestment) * 100;
        }

        System.out.println("\n--- Transaction Details ---");
        System.out.printf("Total Buy Value: $%.2f%n", buyValue);
        System.out.printf("Total Sell Value: $%.2f%n", sellValue);
        System.out.printf("Gross Profit (Before Fees): $%.2f%n", grossProfitLoss);

        System.out.println("\n--- Fee Breakdown (Simplified) ---");
        System.out.printf("Brokerage Fee (%.2f%%): $%.2f%n", brokerageRate * 100, brokerageFee);
        System.out.printf("Other Taxes/Fees (%.2f%%): $%.2f%n", taxRate * 100, taxFee);
        System.out.printf("Total Transaction Fees: $%.2f%n", totalFees);

        System.out.println("\n--- Final Result ---");
        System.out.printf("Net Profit/Loss: **$%.2f**%n", netProfitLoss);
        System.out.printf("Net Percentage Return: **%.2f%%**%n", netPercentageReturn);
    }
}
