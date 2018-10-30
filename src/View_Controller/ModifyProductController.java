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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void searchButtonHandler(ActionEvent event) {
    }

    @FXML
    private void addButtonHandler(ActionEvent event) {
    }

    @FXML
    private void deleteButtonHandler(ActionEvent event) {
    }

    @FXML
    private void cancelButtonHandler(ActionEvent event) {
    }

    @FXML
    private void saveButtonHandler(ActionEvent event) {
    }
    
}
