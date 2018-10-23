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
    private ArrayList<Product> products = new ArrayList<Product>();
    private ArrayList<Part> allParts = new ArrayList<Part>();
    
    Inventory() {
        // ***TO-DO: Complete constructor***
    }
    
    public void addProduct(Product selectedProduct) {
        products.add(selectedProduct);
    }
    
    public boolean removeProduct(int productIndex) {
        products.remove(productIndex);
        // ***TO-DO: Not sure what to return here***
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
