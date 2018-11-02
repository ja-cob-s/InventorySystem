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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableView<Part> partTable1; //Lists all parts
    @FXML
    private TableColumn<Part, Integer> partIDColumn1;
    @FXML
    private TableColumn<Part, String> partNameColumn1;
    @FXML
    private TableColumn<Part, Integer> inventoryLevelColumn1;
    @FXML
    private TableColumn<Part, Double> priceColumn1;
    @FXML
    private TableView<Part> partTable2; //Lists only parts associated with selected product
    @FXML
    private TableColumn<Part, Integer> partIDColumn2;
    @FXML
    private TableColumn<Part, String> partNameColumn2;
    @FXML
    private TableColumn<Part, Integer> inventoryLevelColumn2;
    @FXML
    private TableColumn<Part, Double> priceColumn2;
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
        
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private boolean validInput;
    private Inventory inv;
    private ScreenHelper helper;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inv = new Inventory();
        helper = new ScreenHelper();
        
        //Presets ProductID
        IDField.setText(Integer.toString(inv.getProductsCnt() + 1));
        
        //Populates parts tables
        populatePartTable1(); //Table of all parts
        populatePartTable2(); //Table of associated parts
    }    
    
    public void populatePartTable1() {
        partIDColumn1.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameColumn1.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryLevelColumn1.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        priceColumn1.setCellValueFactory(new PropertyValueFactory<>("price"));
        partTable1.setItems(inv.getAllParts());
    }
    
    public void populatePartTable2() {
        partIDColumn2.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameColumn2.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryLevelColumn2.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        priceColumn2.setCellValueFactory(new PropertyValueFactory<>("price"));
        partTable2.setItems(associatedParts);
    }

    @FXML
    private void searchButtonHandler(ActionEvent event) {
        //Searches the table of all parts
    }

    @FXML
    private void addButtonHandler(ActionEvent event) {
        //Adds the selected part to the product
        if (partTable1.getSelectionModel().getSelectedItem() != null) {
            associatedParts.add(partTable1.getSelectionModel().getSelectedItem());
            populatePartTable2();
        }
    }

    @FXML
    private void deleteButtonHandler(ActionEvent event) {
        /*Removes selected part
          Displays confirmation dialog first*/
        if (helper.showConfirmationDialog("Are you sure you want to delete this part?")){
            // ... user chose OK
            associatedParts.remove(partTable2.getSelectionModel().getSelectedItem());
        } 
    }

    @FXML
    private void cancelButtonHandler(ActionEvent event) throws IOException {
        //Switches to main screen and discards changes when cancelButton pressed
        //Displays confirmation dialog first
        if (helper.showConfirmationDialog("Are you sure you want to discard changes?")) {
            // ... user chose OK
            Stage stage = (Stage) cancelButton.getScene().getWindow(); 
            helper.nextScreenHandler(stage, "MainScreen.fxml");
        }  
    }

    @FXML
    private void saveButtonHandler(ActionEvent event) throws IOException {
        //Adds the product to the inventory and returns to the main screen
        validInput = true;
        ArrayList<Part> associatedParts = new ArrayList<Part>(); //*****CHANGE THIS*****
        String name = getName();
        int productID = getProductID();
        double price = getPrice();
        int inStock = getInStock();
        int min = getMin();
        int max = getMax();
        checkInvLevels(inStock, max, min);
                                               
        if (validInput) {
            Product product = new Product(associatedParts, productID, name, price, inStock, min, max);
            inv.addProduct(product);
            
            Stage stage = (Stage) saveButton.getScene().getWindow();
            helper.nextScreenHandler(stage, "MainScreen.fxml");
        }
    }
    
    public int getProductID() {
        int productID = 0;
        try { productID = Integer.parseInt(IDField.getText()); }
        catch(Exception e) { validInput = helper.IOExceptionHandler("Product ID field"); }
        return productID;
    }
    
    public String getName() {
        //Displays a warning dialog if field is empty
        validInput = helper.emptyStringHandler(nameField.getText(), "Name field");
        return nameField.getText();
    }    
    
    public int getInStock() {
        int inStock = 0;
        try { inStock = Integer.parseInt(invField.getText()); }
        catch(Exception e) { validInput = helper.IOExceptionHandler("Inv field"); }
        return inStock;
    }
    
    public double getPrice() {
        double price = 0.0;
        try { price = Double.parseDouble(priceField.getText()); }
        catch(Exception e) { validInput = helper.IOExceptionHandler("Price field"); }
        return price;
    }
    
    public int getMax() {
        int max = 0;
        try { max = Integer.parseInt(maxField.getText()); }
        catch(Exception e) { validInput = helper.IOExceptionHandler("Max field"); }
        return max;
    }
    
    public int getMin() {
        int min = 0;
        try { min = Integer.parseInt(minField.getText()); }
        catch(Exception e) { validInput = helper.IOExceptionHandler("Min field"); }
        return min;
    }
    
    public void checkInvLevels(int inStock, int max, int min) {
        boolean tmp;
        tmp = helper.invLevelsHandler(inStock, max, min);
        if (!tmp) { validInput = false; }
    }
}
