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
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
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
    
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
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
        this.populatePartTable1(inv.getAllParts()); //Table of all parts
        this.populatePartTable2(associatedParts); //Table of associated parts
    }    
    
    public void populatePartTable1(ObservableList<Part> list) {
        partIDColumn1.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameColumn1.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryLevelColumn1.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        priceColumn1.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        priceColumn1.setCellFactory(tc -> new TableCell<Part, Double>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(currencyFormat.format(price));
                }
            }
        });
        
        partTable1.setItems(list);
    }
    
    public void populatePartTable2(ObservableList<Part> list) {
        partIDColumn2.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameColumn2.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryLevelColumn2.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        priceColumn2.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        priceColumn2.setCellFactory(tc -> new TableCell<Part, Double>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(currencyFormat.format(price));
                }
            }
        });
        
        partTable2.setItems(list);
    }

    @FXML
    private void searchButtonHandler(ActionEvent event) {
        //Searches the table of all parts
        String searchItem = searchField.getText();
        ObservableList<Part> filteredParts = FXCollections.observableArrayList();
        boolean found = false;
        try {
            //Searches by ID number
            int itemNum = Integer.parseInt(searchItem);
            for (Part p: inv.getAllParts()) {
                if (p.getPartID() == itemNum) {
                    found = true;
                    filteredParts.add(p);
                    this.populatePartTable1(filteredParts);
                }
            }
            if (found == false) {
                helper.showWarningDialog("Part not found!");
            }
        }
        catch(NumberFormatException e) {
            //Displays full list if no search string
            if (searchItem == null || searchItem.isEmpty()) {
                this.populatePartTable1(inv.getAllParts());
                found = true;
            }
            //Searches by Name
            for (Part p: inv.getAllParts()) {
                if (p.getName().equals(searchItem)) {
                    found = true;
                    filteredParts.add(p);
                    this.populatePartTable1(filteredParts);
                }
            }
            if (found == false) {
                helper.showWarningDialog("Part not found!");
            }            
        }
    }

    @FXML
    private void addButtonHandler(ActionEvent event) {
        //Adds the selected part to the product
        if (partTable1.getSelectionModel().getSelectedItem() != null) {
            associatedParts.add(partTable1.getSelectionModel().getSelectedItem());
            this.populatePartTable2(associatedParts);
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
        if (helper.showConfirmationDialog("Are you sure you want to discard changes?")){
            // ... user chose OK
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            helper.nextScreenHandler(stage, "MainScreen.fxml");
        }
    }

    @FXML
    private void saveButtonHandler(ActionEvent event) throws IOException {
        //Saves changes to selected Product and returns to main screen
        helper.setValidInput(true);
        ArrayList<Part> associatedParts = new ArrayList<Part>(); //*****--->CHANGE THIS<---*****
        String name = helper.getString(nameField.getText(), "Name field");
        double price = helper.getDouble(priceField.getText(), "Price field");
        int inStock = helper.getInt(invField.getText(), "Inv field");
        int min = helper.getInt(minField.getText(), "Min field");
        int max = helper.getInt(maxField.getText(), "Max field");
        helper.invLevelsHandler(inStock, max, min);
        
        if (helper.getValidInput()) {
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
            associatedParts.add(product.lookupAssociatedPart(i));
        }
        IDField.setText(Integer.toString(product.getProductID()));
        nameField.setText(product.getName());
        invField.setText(Integer.toString(product.getInStock()));
        priceField.setText(Double.toString(product.getPrice()));
        maxField.setText(Integer.toString(product.getMax()));
        minField.setText(Integer.toString(product.getMin()));
    }
}
