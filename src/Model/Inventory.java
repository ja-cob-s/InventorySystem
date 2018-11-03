/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author jnsch
 */
public class Inventory {
    
    public static ObservableList<Product> products = FXCollections.observableArrayList();
    public static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static int productsCnt;
    private static int partsCnt;
    
    public Inventory() {

    }
    
    public void addProduct(Product selectedProduct) {
        products.add(selectedProduct);
        this.incrementProductsCnt();
    }
    
    public boolean removeProduct(int productIndex) {
        return products.remove(products.get(productIndex));
    }
    
    public Product lookupProduct(int productIndex) {
        return products.get(productIndex);
    }
    
    public void updateProduct(int productID) {
        //Method not utilized
    }
    
    public void addPart(Part selectedPart) {
        allParts.add(selectedPart);
        this.incrementPartsCnt();
    }
    
    public boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }
    
    public Part lookupPart(int partIndex) {
        return allParts.get(partIndex);
    }
    
    public void updatePart(int partID) {
        //Method not utilized
    }
    
    public int getPartsCnt() {
        return partsCnt;
    }
    
    public void incrementPartsCnt() {
        partsCnt++;
    }
    
    public int getProductsCnt() {
        return productsCnt;
    }
    
    public void incrementProductsCnt() {
        productsCnt++;
    }
    
    public int getPartIndex(Part part) {
        return allParts.indexOf(part);
    }
    
    public int getProductIndex(Product product) {
        return products.indexOf(product);
    }
    
    public ObservableList<Part> getAllParts() {
        return allParts;
    }
    
    public ObservableList<Product> getProducts() {
        return products;
    }
}
