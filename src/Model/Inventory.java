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
public class Inventory {
    private ArrayList<Product> products;
    private ArrayList<Part> allParts;
    
    Inventory() {
        products = new ArrayList<Product>();
        allParts = new ArrayList<Part>();
    }
    
    public void addProduct(Product selectedProduct) {
        products.add(selectedProduct);
    }
    
    public boolean removeProduct(int productIndex) {
        return products.remove(products.get(productIndex));
    }
    
    public Product lookupProduct(int productIndex) {
        return products.get(productIndex);
    }
    
    public void updateProduct(int productIndex) {
        // ***TO-DO: Complete method***
    }
    
    public void addPart(Part selectedPart) {
        allParts.add(selectedPart);
    }
    
    public boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }
    
    public Part lookupPart(int partIndex) {
        return allParts.get(partIndex);
    }
    
    public void updatePart(int partIndex) {
        // ***TO-DO: Complete method***
    }
}
