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
    private Inventory inv;
    
    public Product() {
        this.inv = new Inventory();
        this.associatedParts = new ArrayList<Part>();
        this.productID = inv.getProductsCnt() + 1;
        this.name = "New Product";
        this.price = 0.00;
        this.inStock = 0;
        this.min = 0;
        this.max = 0;
    }
    
    public Product(ArrayList<Part> associatedParts, int productID, String name, double price, int inStock, int min, int max) {
        this.inv = new Inventory();
        this.associatedParts = new ArrayList<Part>(associatedParts);
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.min = min;
        this.max = max;
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
    
    public boolean removeAssociatedPart(int partID) {
        for (int i = 0; i < associatedParts.size(); i++) {
            if (associatedParts.get(i).getPartID() == partID) {
                return associatedParts.remove(associatedParts.get(partID));
            }
        }
        return false;
    }
    
    public Part lookupAssociatedPart(int partID) {
        for (int i = 0; i < associatedParts.size(); i++) {
            if (associatedParts.get(i).getPartID() == partID) {
                return associatedParts.get(i);
            }
        }
        return null;
    }
    
    public void setProductID(int productID) {
        this.productID = productID;
    }
    
    public int getProductID() {
        return productID;
    }
    
    public ArrayList<Part> getAssociatedParts() {
        return associatedParts;
    }
    
    public void setAssociatedParts(ArrayList<Part> associatedParts) {
        this.associatedParts = associatedParts;
    }
    
}
