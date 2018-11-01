/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author jnsch
 */
public abstract class Part {
    private int partID;
    private String name;
    private double price;
    private int inStock;
    private int min;
    private int max;
    
    public Part() {
        this.setPartID(Inventory.partsCnt+1);
        this.setName("New Part");
        this.setPrice(0.00);
        this.setInStock(0);
        this.setMin(0);
        this.setMax(0);
    }
    
    public Part(int partID, String name, double price, int inStock, int min, int max) {
        this.setPartID(partID);
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
    
    public void setPartID(int partID) {
        this.partID = partID;
    }
    
    public int getPartID() {
        return partID;
    }
}
