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
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
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
        
        partIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        partsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        partsTable.setItems(inv.getAllParts());
        
        productIDColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productsInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        productsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        productsTable.setItems(inv.getProducts());
        
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

            if (partsTable.getSelectionModel().getSelectedItem() instanceof InhousePart) {
                //Part is an Inhouse Part
                ModifyInhousePartController controller = 
                        (ModifyInhousePartController)helper.nextScreenControllerHandler(stage, "ModifyInhousePart.fxml");
                controller.setPart((InhousePart)part);
            }
            else {
                //Part is an Outsourced Part
                ModifyOutsourcedPartController controller = 
                        (ModifyOutsourcedPartController)helper.nextScreenControllerHandler(stage, "ModifyOutsourcedPart.fxml");
                controller.setPart((OutsourcedPart)part);
            }
        }
    }

    @FXML
    private void partsAddButtonHandler(ActionEvent event) throws IOException {
        //Switches to AddPart screen when partsAddButton pressed
        Stage stage = (Stage) partsAddButton.getScene().getWindow(); 
        helper.nextScreenHandler(stage, "AddInhousePart.fxml");
    }

    @FXML
    private void productsSearchButtonHandler(ActionEvent event) {
        //Searches for user input within products list when productssSearchButton pressed
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
