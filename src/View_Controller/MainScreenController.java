/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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
    private TableView<?> partsTable;
    @FXML
    private TableColumn<?, ?> partIDColumn;
    @FXML
    private TableColumn<?, ?> partNameColumn;
    @FXML
    private TableColumn<?, ?> partsInventoryLevelColumn;
    @FXML
    private TableColumn<?, ?> partsPriceColumn;
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
    private TableView<?> productsTable;
    @FXML
    private TableColumn<?, ?> productIDColumn;
    @FXML
    private TableColumn<?, ?> productNameColumn;
    @FXML
    private TableColumn<?, ?> productsInventoryLevelColumn;
    @FXML
    private TableColumn<?, ?> productsPriceColumn;
    @FXML
    private Button productsDeleteButton;
    @FXML
    private Button productsModifyButton;
    @FXML
    private Button productsAddButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void exitButtonHandler(ActionEvent event) {
    }

    @FXML
    private void partsSearchButtonHandler(ActionEvent event) {
    }

    @FXML
    private void partsDeleteButtonHandler(ActionEvent event) {
    }

    @FXML
    private void partsModifyButtonHandler(ActionEvent event) {
    }

    @FXML
    private void partsAddButtonHandler(ActionEvent event) {
    }

    @FXML
    private void productsSearchButtonHandler(ActionEvent event) {
    }

    @FXML
    private void productsDeleteButtonHandler(ActionEvent event) {
    }

    @FXML
    private void productsModifyButtonHandler(ActionEvent event) {
    }

    @FXML
    private void productsAddButtonHandler(ActionEvent event) {
    }
    
}
