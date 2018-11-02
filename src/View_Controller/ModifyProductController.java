/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
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
public class ModifyProductController implements Initializable {

    @FXML
    private AnchorPane modifyProductScreenAnchor;
    @FXML
    private AnchorPane modifyProductAnchor;
    @FXML
    private Label modifyProductLabel;
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
    
    private ObservableList<Part> tmpAssociatedParts = FXCollections.observableArrayList();
    private boolean validInput;
    private Product product;
    private Inventory inv;
    private ScreenHelper helper;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inv = new Inventory();
        helper = new ScreenHelper();
        
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
        partTable2.setItems(tmpAssociatedParts);
    }

    @FXML
    private void searchButtonHandler(ActionEvent event) {
        //Searches the table of all parts
    }

    @FXML
    private void addButtonHandler(ActionEvent event) {
        //Adds the selected part to the product
        if (partTable1.getSelectionModel().getSelectedItem() != null) {
            tmpAssociatedParts.add(partTable1.getSelectionModel().getSelectedItem());
            populatePartTable2();
        }
    }

    @FXML
    private void deleteButtonHandler(ActionEvent event) {
        /*Removes selected part
          Displays confirmation dialog first*/
        if (helper.showConfirmationDialog("Are you sure you want to delete this part?")){
            // ... user chose OK
            tmpAssociatedParts.remove(partTable2.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void cancelButtonHandler(ActionEvent event) throws IOException {
        //Switches to main screen and discards changes when cancelButton pressed
        //Displays confirmation dialog first
        if (helper.showConfirmationDialog("Are you sure you want to discard changes?")){
            // ... user chose OK
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            helper.nextScreenHandler(stage, "MainScreen.fxml");
        }
    }

    @FXML
    private void saveButtonHandler(ActionEvent event) throws IOException {
        //Saves changes to selected Product and returns to main screen
        validInput = true;
        String name = getName();
        int inStock = getInStock();
        double price = getPrice();
        int max = getMax();
        int min = getMin();
        checkInvLevels(inStock, max, min);
        
        if (validInput) {
            /*for (int i = 0; i < tmpAssociatedParts.size(); i++) {
                boolean found= false;
                for (int j = 0; j < product.getNumAssociatedParts(); j++) {
                    if (tmpAssociatedParts.get(i).equals(product.lookupAssociatedPart(i))) {
                        found = true;
                    }
                }
                if (!found) { product.addAssociatedPart(tmpAssociatedParts.get(i)); }
            }*/
            
            product.setName(name);
            product.setInStock(inStock);
            product.setPrice(price);
            product.setMax(max);
            product.setMin(min);
            
            Stage stage = (Stage) saveButton.getScene().getWindow();
            helper.nextScreenHandler(stage, "MainScreen.fxml");
        }
    }
    
    public void setProduct(Product product) {
        this.product = product;
    
        for (int i = 0; i < product.getNumAssociatedParts(); i++) {
            tmpAssociatedParts.add(product.lookupAssociatedPart(i));
        }
        IDField.setText(Integer.toString(product.getProductID()));
        nameField.setText(product.getName());
        invField.setText(Integer.toString(product.getInStock()));
        priceField.setText(Double.toString(product.getPrice()));
        maxField.setText(Integer.toString(product.getMax()));
        minField.setText(Integer.toString(product.getMin()));
    }
    
    public String getName() {
        //Displays a warning dialog if field is empty
        boolean tmp;
        tmp = helper.emptyStringHandler(nameField.getText(), "Name field");
        if (!tmp) { validInput = false; }
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
