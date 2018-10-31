/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Inventory;
import Model.Product;
import Model.Part;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jnsch
 */
public class AddProductController implements Initializable {

    @FXML
    private AnchorPane addProductScreenAnchor;
    @FXML
    private AnchorPane addProductAnchor;
    @FXML
    private Label addProductLabel;
    @FXML
    private Label IDLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label invLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Label maxLabel;
    @FXML
    private Label minLabel;
    @FXML
    private TextField IDField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField invField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField maxField;
    @FXML
    private TextField minField;
    @FXML
    private TableView<?> partTable1;
    @FXML
    private TableColumn<?, ?> partIDColumn1;
    @FXML
    private TableColumn<?, ?> partNameColumn1;
    @FXML
    private TableColumn<?, ?> inventoryLevelColumn1;
    @FXML
    private TableColumn<?, ?> priceColumn1;
    @FXML
    private TableView<?> partTable2;
    @FXML
    private TableColumn<?, ?> partIDColumn2;
    @FXML
    private TableColumn<?, ?> partNameColumn2;
    @FXML
    private TableColumn<?, ?> inventoryLevelColumn2;
    @FXML
    private TableColumn<?, ?> priceColumn2;
    @FXML
    private Button searchButton;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;
    @FXML
    private TextField searchField;
    private boolean validInput;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IDField.setText(Integer.toString(Inventory.products.size() + 1));
    }    

    @FXML
    private void searchButtonHandler(ActionEvent event) {
        //Searches for part based on user input
    }

    @FXML
    private void addButtonHandler(ActionEvent event) {
        //Adds selected part
    }

    @FXML
    private void deleteButtonHandler(ActionEvent event) {
        //Removes selected part
    }

    @FXML
    private void cancelButtonHandler(ActionEvent event) throws IOException {
        //Switches to main screen and discards changes when cancelButton pressed
        Stage stage; 
        Parent root;       
        stage=(Stage) cancelButton.getScene().getWindow();
        //load up OTHER FXML document
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
               "MainScreen.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void saveButtonHandler(ActionEvent event) throws IOException {
        //Adds the product to the inventory and returns to the main screen
        validInput = true;
        ArrayList<Part> associatedParts = new ArrayList<Part>(); //*****CHANGE THIS*****
        Product product = new Product(associatedParts, getName(), getPrice(), getInStock(), getMin(), getMax());
                               
        if (validInput) {
            Inventory.products.add(product);
            
            Stage stage; 
            Parent root;       
            stage=(Stage) cancelButton.getScene().getWindow();
            //load up OTHER FXML document
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                   "MainScreen.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    
    public boolean IOExceptionHandler() {
        System.out.println("Invalid input type"); 
        return false;
    }
    
    public String getName() {
        return nameField.getText();
    }    
    
    public int getInStock() {
        int inStock = 0;
        try { inStock = Integer.parseInt(invField.getText()); }
        catch(Exception e) { validInput = IOExceptionHandler(); }
        return inStock;
    }
    
    public double getPrice() {
        double price = 0.0;
        try { price = Double.parseDouble(priceField.getText()); }
        catch(Exception e) { validInput = IOExceptionHandler(); }
        return price;
    }
    
    public int getMax() {
        int max = 0;
        try { max = Integer.parseInt(maxField.getText()); }
        catch(Exception e) { validInput = IOExceptionHandler(); }
        return max;
    }
    
    public int getMin() {
        int min = 0;
        try { min = Integer.parseInt(maxField.getText()); }
        catch(Exception e) { validInput = IOExceptionHandler(); }
        return min;
    }
}
