package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Store {

    public static void main(String[] args) {
        // Initialize variables
        ArrayList<Product> inventory = new ArrayList<Product>();
        ArrayList<Product> cart = new ArrayList<Product>();
        double totalAmount = 0.0;

        // Load inventory from CSV file
        loadInventory("products.csv", inventory);

        // Create scanner to read user input
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        // Display menu and get user choice until they choose to exit
        while (choice != 3) {
            System.out.println("Welcome to the Online Store!");
            System.out.println("1. Show Products");
            System.out.println("2. Show Cart");
            System.out.println("3. Exit");

            choice = scanner.nextInt();
            scanner.nextLine();

            // Call the appropriate method based on user choice
            switch (choice) {
                case 1:
                    displayProducts(inventory, cart, scanner);
                    break;
                case 2:
                    displayCart(cart, scanner, totalAmount);
                    break;
                case 3:
                    System.out.println("Thank you for shopping with us!");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    public static void loadInventory(String fileName, ArrayList<Product> inventory) {
        String line;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String productID = parts[0];
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                Product newProduct = new Product(productID,name, price);
                inventory.add(newProduct);
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("Error loading file ");
        }
    }

    public static void displayProducts(ArrayList<Product> inventory, ArrayList<Product> cart, Scanner scanner) {
        for (Product product : inventory) {
            System.out.println(product.toString());
        }
        System.out.println("Enter productID: ");
        String productID = scanner.nextLine();
        System.out.println("Product added to cart!");

        for (Product product : inventory) {
            if (product.getProductID().equalsIgnoreCase(productID)){
                cart.add(product);
            }

        }

    }


    public static void displayCart(ArrayList<Product> cart, Scanner scanner, double totalAmount) {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty");
        }
        double total = 0.0;

        System.out.println("Your Cart: ");
        System.out.println("----------------------------");

        for (Product product : cart) {
            System.out.println(product);
            total += product.getPrice();
        }

        System.out.println("Total amount: " + total);
        System.out.println("----------------------------");
        System.out.println("\nWhat would you like to do next?");
        System.out.println("Type 1 to Check Out");
        System.out.println("Type 2 to Go Back to Home");
        System.out.println("Type 3 to remove item from cart by ID");

        int input = scanner.nextInt();
        scanner.nextLine();

        if (input == 3) {
            System.out.println("Enter product ID to remove: ");
            String removeProduct = scanner.nextLine();
            boolean removed = false;

            for (int i = 0; i < cart.size(); i++) {
                if (cart.get(i).getProductID().equalsIgnoreCase(removeProduct)) {
                    System.out.println(" success " + cart.get(i).getDescription() + " removed from cart.");
                    cart.remove(i);
                    removed = true;
                    break;
                }
            }
            if (!removed) {
                System.out.println("no item found with: " + removeProduct);
            }
            displayCart(cart, scanner, totalAmount);
        } else if (input == 1) {
            checkOut(cart, total);
        } else if (input == 2) {
            System.out.println("returning home!");
        } else {
            System.out.println("invalid choice, returning to cart!");
            displayCart(cart, scanner, totalAmount);
        }

    }


    public static void checkOut(ArrayList<Product> cart, double totalAmount) {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        System.out.printf("Your total amount is: $%.2f\n", totalAmount);
        System.out.print("Please enter payment amount: $");
        Scanner scanner = new Scanner(System.in);
        double payment = scanner.nextDouble();


        if (payment >= totalAmount) {
            double change = payment - totalAmount;
            System.out.printf("Thank you for your purchase! Your change is: "+ change);


            System.out.println("\nReceipt:");
            System.out.println("Date: " + date + "Time: " + time);
            for (Product product : cart) {
                System.out.println(product);
            }

            cart.clear();
            System.out.println("Your cart has been cleared.");
        } else {
            System.out.println("Insufficient payment. Please try again.");
        }
    }

    public static Product findProductById(String id, ArrayList<Product> inventory) {
        for (Product product : inventory) {
            if (product.getProductID().equalsIgnoreCase(id)){
              return product;
            }
        }
            System.out.println( id + " not found.");
            return null;

    }


    }



