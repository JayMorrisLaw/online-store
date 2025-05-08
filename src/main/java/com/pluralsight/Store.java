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
        if (cart.isEmpty()){
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

        int input = scanner.nextInt();
        scanner.nextLine();

        if (input == 1) {
            checkOut(cart, total);
        }

        // This method should display the items in the cart ArrayList, along
        // with the total cost of all items in the cart. The method should
        // prompt the user to remove items from their cart by entering the ID
        // of the product they want to remove. The method should update the cart ArrayList and totalAmount
        // variable accordingly.
    }

    public static void checkOut(ArrayList<Product> cart, double totalAmount) {
        // This method should calculate the total cost of all items in the cart,
        // and display a summary of the purchase to the user. The method should
        // prompt the user to confirm the purchase, and calculate change and clear the cart
        // if they confirm.
    }

    public static Product findProductById(String id, ArrayList<Product> inventory) {
        boolean found = false;
        for (Product product : inventory) {
            if (product.getProductID().equalsIgnoreCase(id)){
                System.out.println(product);
                found = true;
            }
        }
        if (!found){
            System.out.println( id + " found.");

    }

        return null;
    }
    }


 