/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.InhousePart;
import Model.Inventory;
import Model.OutsourcedPart;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
public class MainScreenController implements Initializable {

    @FXML
    private AnchorPane mainScreenAnchor;
    @FXML
    private Label inventoryManagementSystemLabel;
    @FXML
    private Button exitButton;
    @FXML
    private AnchorPane partsAnchor;
    @FXML
    private Label partsLabel;
    @FXML
    private Button partsSearchButton;
    @FXML
    private TextField partsSearchField;
    @FXML
    private TableView<Part> partsTable;
    @FXML
    private TableColumn<Part, Integer> partIDColumn;
    @FXML
    private TableColumn<Part, String> partNameColumn;
    @FXML
    private TableColumn<Part, Integer> partsInventoryLevelColumn;
    @FXML
    private TableColumn<Part, Double> partsPriceColumn;
    @FXML
    private Button partsDeleteButton;
    @FXML
    private Button partsModifyButton;
    @FXML
    private Button partsAddButton;
    @FXML
    private AnchorPane productsAnchor;
    @FXML
    private Label productsLabel;
    @FXML
    private Button productsSearchButton;
    @FXML
    private TextField productsSearchField;
    @FXML
    private TableView<Product> productsTable;
    @FXML
    private TableColumn<Product, Integer> productIDColumn;
    @FXML
    private TableColumn<Product, String> productNameColumn;
    @FXML
    private TableColumn<Product, Integer> productsInventoryLevelColumn;
    @FXML
    private TableColumn<Product, Double> productsPriceColumn;
    @FXML
    private Button productsDeleteButton;
    @FXML
    private Button productsModifyButton;
    @FXML
    private Button productsAddButton;
    
    static boolean entered;
    private Inventory inv;
    private ScreenHelper helper;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inv = new Inventory();
        helper = new ScreenHelper();
            
        if(!entered) {
            inv.addProduct(new Product());
            inv.addProduct(new Product());
            inv.addProduct(new Product());
            inv.addProduct(new Product());
            inv.addPart(new InhousePart());
            inv.addPart(new OutsourcedPart());
            entered = true;
        }
        
        this.populatePartsTable(inv.getAllParts());
        this.populateProductsTable(inv.getProducts());
    }    
    
    public void populatePartsTable(ObservableList<Part> list) {
        partIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        partsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        partsPriceColumn.setCellFactory(tc -> new TableCell<Part, Double>() {
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
        
        partsTable.setItems(list);
    }
    
    public void populateProductsTable(ObservableList<Product> list) {
        productIDColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productsInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        productsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        productsPriceColumn.setCellFactory(tc -> new TableCell<Product, Double>() {
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
        
        productsTable.setItems(list);
    }

    @FXML
    private void exitButtonHandler(ActionEvent event) {
        /*Terminates the application when the exit button is pressed
          Displays confirmation dialog first*/
        if (helper.showConfirmationDialog("Are you sure you want to exit?")){
            // ... user chose OK
            System.exit(0); 
        } 
    }

    @FXML
    private void partsSearchButtonHandler(ActionEvent event) {
        //Searches for user input within parts list when partsSearchButton pressed
        String searchItem = partsSearchField.getText();
        ObservableList<Part> filteredParts = FXCollections.observableArrayList();
        boolean found = false;
        try {
            //Searches by ID number
            int itemNum = Integer.parseInt(searchItem);
            for (Part p: inv.getAllParts()) {
                if (p.getPartID() == itemNum) {
                    found = true;
                    filteredParts.add(p);
                    this.populatePartsTable(filteredParts);
                }
            }
            if (found == false) {
                helper.showWarningDialog("Part not found!");
            }
        }
        catch(NumberFormatException e) {
            //Displays full list if no search string
            if (searchItem == null || searchItem.isEmpty()) {
                this.populatePartsTable(inv.getAllParts());
                found = true;
            }
            //Searches by Name
            for (Part p: inv.getAllParts()) {
                if (p.getName().equals(searchItem)) {
                    found = true;
                    filteredParts.add(p);
                    this.populatePartsTable(filteredParts);
                }
            }
            if (found == false) {
                helper.showWarningDialog("Part not found!");
            }            
        }
    }

    @FXML
    private void partsDeleteButtonHandler(ActionEvent event) {
        /*Deletes the selected part when partsDeleteButton pressed
          Only if a part is selected
          Displays confirmation dialog first*/
        if (partsTable.getSelectionModel().getSelectedItem() != null) {
            if (helper.showConfirmationDialog("Are you sure you want to delete this part?")){
                // ... user chose OK
                Part part = partsTable.getSelectionModel().getSelectedItem();
                inv.deletePart(part);
            }
        }
    }

    @FXML
    private void partsModifyButtonHandler(ActionEvent event) throws IOException {
        /*Switches to ModifyPart screen when partsModifyButton pressed
          Only if a part is selected
          Passes selected Part object to next screen*/
        if (partsTable.getSelectionModel().getSelectedItem() != null) {
            Object part = partsTable.getSelectionModel().getSelectedItem();
            
            Stage stage = (Stage) partsModifyButton.getScene().getWindow();
            ModifyPartController controller = 
                        (ModifyPartController)helper.nextScreenControllerHandler(stage, "ModifyPart.fxml");

            if (partsTable.getSelectionModel().getSelectedItem() instanceof InhousePart) {
                //Part is an Inhouse Part
                controller.setPart((InhousePart)part);
            }
            else {
                //Part is an Outsourced Part
                controller.setPart((OutsourcedPart)part);
            }
        }
    }

    @FXML
    private void partsAddButtonHandler(ActionEvent event) throws IOException {
        //Switches to AddPart screen when partsAddButton pressed
        Stage stage = (Stage) partsAddButton.getScene().getWindow(); 
        helper.nextScreenHandler(stage, "AddPart.fxml");
    }

    @FXML
    private void productsSearchButtonHandler(ActionEvent event) {
        //Searches for user input within products list when productsSearchButton pressed
        String searchItem = productsSearchField.getText();
        ObservableList<Product> filteredProducts = FXCollections.observableArrayList();
        boolean found = false;
        try {
            //Searches by ID number
            int itemNum = Integer.parseInt(searchItem);
            for (Product p: inv.getProducts()) {
                if (p.getProductID() == itemNum) {
                    found = true;
                    filteredProducts.add(p);
                    this.populateProductsTable(filteredProducts);
                }
            }
            if (found == false) {
                helper.showWarningDialog("Product not found!");
            }
        }
        catch(NumberFormatException e) {
            //Displays full list if no search string
            if (searchItem == null || searchItem.isEmpty()) {
                this.populateProductsTable(inv.getProducts());
                found = true;
            }
            //Searches by Name
            for (Product p: inv.getProducts()) {
                if (p.getName().equals(searchItem)) {
                    found = true;
                    filteredProducts.add(p);
                    this.populateProductsTable(filteredProducts);
                }
            }
            if (found == false) {
                helper.showWarningDialog("Product not found!");
            }            
        }
    }

    @FXML
    private void productsDeleteButtonHandler(ActionEvent event) {
        /*Deletes the selected product when productsDeleteButton pressed
          Only if a Product is selected
          Displays confirmation dialog first*/
        if (productsTable.getSelectionModel().getSelectedItem() != null) {
            if (helper.showConfirmationDialog("Are you sure you want to delete this product?")){
                // ... user chose OK
                Product product = productsTable.getSelectionModel().getSelectedItem();
                inv.removeProduct(inv.getProductIndex(product));
            } else {
                // ... user chose CANCEL or closed the dialog
            }    
        }
    }

    @FXML
    private void productsModifyButtonHandler(ActionEvent event) throws IOException {
        /*Switches to ModifyProduct screen when productsModifyButton pressed
          Only if a Product is selected
          Passes selected Product object to next screen*/
        if (productsTable.getSelectionModel().getSelectedItem() != null) {
            Stage stage = (Stage) productsModifyButton.getScene().getWindow();
            ModifyProductController controller = 
                    (ModifyProductController)helper.nextScreenControllerHandler(stage, "ModifyProduct.fxml");
            Product product = productsTable.getSelectionModel().getSelectedItem();
            controller.setProduct(product);
        }
    }

    @FXML
    private void productsAddButtonHandler(ActionEvent event) throws IOException {
        //Switches to AddProduct screen when productsAddButton pressed
        Stage stage = (Stage) productsAddButton.getScene().getWindow();
        helper.nextScreenHandler(stage, "AddProduct.fxml");
    }
    
}
