package org.example;

import java.util.Scanner;

public class Calculator {

    public static void startCalculator() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Calculator ---");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.println("5. Exit Calculator");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            if (choice == 5) {
                System.out.println("Exiting Calculator...");
                break;
            }

            System.out.print("Enter first number: ");
            double a = sc.nextDouble();
            System.out.print("Enter second number: ");
            double b = sc.nextDouble();

            switch (choice) {
                case 1:
                    System.out.println("Result: " + (a + b));
                    break;
                case 2:
                    System.out.println("Result: " + (a - b));
                    break;
                case 3:
                    System.out.println("Result: " + (a * b));
                    break;
                case 4:
                    if (b != 0)
                        System.out.println("Result: " + (a / b));
                    else
                        System.out.println("Error: Division by zero");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}