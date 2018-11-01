/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author jnsch
 */
public class Product {
    private ArrayList<Part> associatedParts;
    private int productID;
    private String name;
    private double price;
    private int inStock;
    private int min;
    private int max;
    
    public Product() {
        associatedParts = new ArrayList<Part>();
        this.setProductID(Inventory.productsCnt+1);
        this.setName("New Product");
        this.setPrice(0.00);
        this.setInStock(0);
        this.setMin(0);
        this.setMax(0);
    }
    
    public Product(ArrayList<Part> associatedParts, int productID, String name, double price, int inStock, int min, int max) {
        this.associatedParts = new ArrayList<Part>(associatedParts);
        this.setProductID(productID);
        this.setName(name);
        this.setPrice(price);
        this.setInStock(inStock);
        this.setMin(min);
        this.setMax(max);
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setInStock(int inStock) {
        this.inStock = inStock;
    }
    
    public int getInStock() {
        return inStock;
    }
    
    public void setMin(int min) {
        this.min = min;
    }
    
    public int getMin() {
        return min;
    }
    
    public void setMax(int max) {
        this.max = max;
    }
    
    public int getMax() {
        return max;
    }
    
    public void addAssociatedPart(Part selectedPart) {
        associatedParts.add(selectedPart);
    }
    
    public boolean removeAssociatedPart(int partIndex) {
        return associatedParts.remove(associatedParts.get(partIndex));
    }
    
    public Part lookupAssociatedPart(int partIndex) {
        return associatedParts.get(partIndex);
    }
    
    public void setProductID(int productID) {
        this.productID = productID;
    }
    
    public int getProductID() {
        return productID;
    }
}
