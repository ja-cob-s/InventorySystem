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
    private ArrayList<Part> associatedParts = new ArrayList<Part>();
    private int productID;
    private String name;
    private double price;
    private int inStock;
    private int min;
    private int max;
    
    Product() {
        // ***TO-DO: complete constructor***
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
        associatedParts.remove(partIndex);
        // ***TO-DO: Not sure what to return here ***
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
