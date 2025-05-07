package com.pluralsight;

public class Product {
private String productID;
private String name;
private double price;

    public Product(String productID, String description, double price) {
        this.productID = productID;
        this.name = description;
        this.price = price;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getDescription() {
        return name;
    }

    public void setDescription(String description) {
        this.name = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return productID + "|" +  name + "|" + price + "|";
    }
}
