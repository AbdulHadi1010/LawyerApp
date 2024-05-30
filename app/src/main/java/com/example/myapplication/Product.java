package com.example.myapplication;

public class Product {
    // Private variables
    int _id;
    String _name;
    String _description;
    double _price;

    // Empty constructor
    public Product() {

    }

    // Constructor
    public Product(int id, String name, String description, double price) {
        this._id = id;
        this._name = name;
        this._description = description;
        this._price = price;
    }

    // Constructor
    public Product(String name, String description, double price) {
        this._name = name;
        this._description = description;
        this._price = price;
    }

    // Getters and setters
    public int getID() {
        return this._id;
    }

    public void setID(int id) {
        this._id = id;
    }

    public String getName() {
        return this._name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getDescription() {
        return this._description;
    }

    public void setDescription(String description) {
        this._description = description;
    }

    public double getPrice() {
        return this._price;
    }

    public void setPrice(double price) {
        this._price = price;
    }
}
