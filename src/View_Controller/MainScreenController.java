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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inv = new Inventory();
            
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
        
        partsTable.setItems(inv.allParts);
        
        productIDColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productsInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        productsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        productsTable.setItems(inv.products);
        
    }    

    @FXML
    private void exitButtonHandler(ActionEvent event) {
        System.exit(0); // Terminates the application when the exit button is pressed
    }

    @FXML
    private void partsSearchButtonHandler(ActionEvent event) {
        //Searches for user input within parts list when partsSearchButton pressed
    }

    @FXML
    private void partsDeleteButtonHandler(ActionEvent event) {
        /*Deletes the selected part when partsDeleteButton pressed
          Only if a part is selected*/
        //Part part = partsTable.getSelectionModel().getSelectedItem();
        //inv.deletePart(part.getPartID());
    }

    @FXML
    private void partsModifyButtonHandler(ActionEvent event) throws IOException {
        /*Switches to ModifyPart screen when partsModifyButton pressed
          Only if a part is selected
          Passes selected Part object to next screen*/
        if (partsTable.getSelectionModel().getSelectedItem() != null) {
            Stage stage; 
            Parent root;       
            stage=(Stage) partsModifyButton.getScene().getWindow();
            
            Object part = partsTable.getSelectionModel().getSelectedItem();
            //load up OTHER FXML document
            FXMLLoader inhouseLoader = new FXMLLoader(getClass().getResource(
                   "ModifyInhousePart.fxml"));
            FXMLLoader outsourcedLoader = new FXMLLoader(getClass().getResource(
                   "ModifyOutsourcedPart.fxml"));
            if (partsTable.getSelectionModel().getSelectedItem() instanceof InhousePart) {
                root = inhouseLoader.load();
                ModifyInhousePartController controller = inhouseLoader.getController();
                controller.setPart((InhousePart)part);
            }
            else {
                root = outsourcedLoader.load();
                ModifyOutsourcedPartController controller = outsourcedLoader.getController();
                controller.setPart((OutsourcedPart)part);
            }
            
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private void partsAddButtonHandler(ActionEvent event) throws IOException {
        //Switches to AddPart screen when partsAddButton pressed
        Stage stage; 
        Parent root;       
        stage=(Stage) partsAddButton.getScene().getWindow();
        //load up OTHER FXML document
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
               "AddInhousePart.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void productsSearchButtonHandler(ActionEvent event) {
        //Searches for user input within products list when productssSearchButton pressed
    }

    @FXML
    private void productsDeleteButtonHandler(ActionEvent event) {
        /*Deletes the selected product when productsDeleteButton pressed
          Only if a Product is selected*/
        if (productsTable.getSelectionModel().getSelectedItem() != null) {
            Product product = productsTable.getSelectionModel().getSelectedItem();
            inv.removeProduct(product.getProductID() - 1);
        }
    }

    @FXML
    private void productsModifyButtonHandler(ActionEvent event) throws IOException {
        /*Switches to ModifyProduct screen when productsModifyButton pressed
          Only if a Product is selected
          Passes selected Product object to next screen*/
        if (productsTable.getSelectionModel().getSelectedItem() != null) {
            Stage stage; 
            Parent root;       
            stage=(Stage) productsModifyButton.getScene().getWindow();
            //load up OTHER FXML document
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                   "ModifyProduct.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            ModifyProductController controller = loader.getController();
            Product product = productsTable.getSelectionModel().getSelectedItem();
            controller.setProduct(product);
        }
    }

    @FXML
    private void productsAddButtonHandler(ActionEvent event) throws IOException {
        //Switches to AddProduct screen when productsAddButton pressed
        Stage stage; 
        Parent root;       
        stage=(Stage) productsAddButton.getScene().getWindow();
        //load up OTHER FXML document
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
               "AddProduct.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
