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
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jnsch
 */
public class AddPartController implements Initializable {

    @FXML
    private AnchorPane addInhousePartScreenAnchor;
    @FXML
    private AnchorPane addInhousePartAnchor;
    @FXML
    private Label addInhousePartLabel;
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
    private TextField machineIDField;
    @FXML
    private TextField maxField;
    @FXML
    private TextField minField;
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;
    @FXML
    private RadioButton inHouseRadio;
    @FXML
    private RadioButton outsourcedRadio;
    
    private Inventory inv;
    private Part part;
    private ScreenHelper helper;
    @FXML
    private Label variableLabel;
    @FXML
    private TextField variableField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Presets PartID
        inv = new Inventory();
        helper = new ScreenHelper();
        IDField.setText(Integer.toString(inv.getPartsCnt() + 1));

        // Sets togglegroup for radio buttons
        ToggleGroup toggleGroup = new ToggleGroup();
        inHouseRadio.setToggleGroup(toggleGroup);
        outsourcedRadio.setToggleGroup(toggleGroup);
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
        //Adds the part to the inventory and returns to the main screen
        helper.setValidInput(true);
        int partID = helper.getInt(IDField.getText(), "Part ID field");
        String name = helper.getString(nameField.getText(), "Name field");
        double price = helper.getDouble(priceField.getText(), "Price field");
        int inStock = helper.getInt(invField.getText(), "Inv field");
        int min = helper.getInt(minField.getText(), "Min field");
        int max = helper.getInt(maxField.getText(), "Max field");
        
        if (inHouseRadio.isSelected()) {
            //Creates a new Inhouse Part
            int machineID = helper.getInt(variableField.getText(), "Machine ID field");
            helper.invLevelsHandler(inStock, max, min);
            if (helper.getValidInput()) {
                InhousePart part = new InhousePart(partID, name, price, inStock, min, max, machineID);
                inv.addPart(part);
                Stage stage = (Stage) saveButton.getScene().getWindow();
                helper.nextScreenHandler(stage, "MainScreen.fxml");
            }
        } else if (outsourcedRadio.isSelected()) {
            //Creates a new Outsourced Part
            String companyName = helper.getString(variableField.getText(), "Company Name field");
            helper.invLevelsHandler(inStock, max, min);
            if (helper.getValidInput()) {
                OutsourcedPart part = new OutsourcedPart(partID, name, price, inStock, min, max, companyName);
                inv.addPart(part);
                Stage stage = (Stage) saveButton.getScene().getWindow();
                helper.nextScreenHandler(stage, "MainScreen.fxml");
            }
        }
    }

    @FXML
    private void inHouseRadioHandler(ActionEvent event) {
        //Changes the last field for Machine ID input
        variableLabel.setText("Machine ID");
        variableField.setPromptText("Machine ID");
    }

    @FXML
    private void outsourcedRadioHandler(ActionEvent event) throws IOException {
        //Changes the last field for Company Name input
        variableLabel.setText("Company Name");
        variableField.setPromptText("Company Name");
    }
}
