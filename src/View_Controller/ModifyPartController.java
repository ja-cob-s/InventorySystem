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
public class ModifyPartController implements Initializable {

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

        String name = helper.getString(nameField.getText(), "Name field");
        double price = helper.getDouble(priceField.getText(), "Price field");
        int inStock = helper.getInt(invField.getText(), "Inv field");
        int min = helper.getInt(minField.getText(), "Min field");
        int max = helper.getInt(maxField.getText(), "Max field");
        
        if (part instanceof InhousePart) {
            if (outsourcedRadio.isSelected()) {
                helper.showWarningDialog("Cannot convert Inhouse Part to Outsourced Part!");
                helper.setValidInput(false);
            }
            int machineID = helper.getInt(variableField.getText(), "Machine ID field");
            helper.invLevelsHandler(inStock, max, min);
            if (helper.getValidInput()) {
                part.setName(name);
                part.setPrice(price);
                part.setInStock(inStock);
                part.setMin(min);
                part.setMax(max);
                ((InhousePart)part).setMachineID(machineID);
                
                Stage stage = (Stage) saveButton.getScene().getWindow();
                helper.nextScreenHandler(stage, "MainScreen.fxml");
            }
        } else if (part instanceof OutsourcedPart) {
            if (inHouseRadio.isSelected()) {
                helper.showWarningDialog("Cannot convert Outsourced Part to Inhouse Part!");
                helper.setValidInput(false);
            }
            String companyName = helper.getString(variableField.getText(), "Company Name field");
            helper.invLevelsHandler(inStock, max, min);
            if (helper.getValidInput()) {
                part.setName(name);
                part.setPrice(price);
                part.setInStock(inStock);
                part.setMin(min);
                part.setMax(max);
                ((OutsourcedPart)part).setCompanyName(companyName);
                
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
        //Blanks out the field if part is an Outsourced Part
        if (part instanceof InhousePart) {
            variableField.setText(Integer.toString(((InhousePart) part).getMachineID()));
        } else if (part instanceof OutsourcedPart) {
            variableField.setText("");
        }
    }

    @FXML
    private void outsourcedRadioHandler(ActionEvent event) throws IOException {
        //Changes the last field for Company Name input
        variableLabel.setText("Company Name");
        variableField.setPromptText("Company Name");
        //Blanks out the field if part is an Inhouse Part
        if (part instanceof InhousePart) {
            variableField.setText("");
        } else if (part instanceof OutsourcedPart) {
            variableField.setText(((OutsourcedPart) part).getCompanyName());
        }
    }
    
    public void setPart(Part part) {
        this.part = part;
        
        IDField.setText(Integer.toString(part.getPartID()));
        nameField.setText(part.getName());
        invField.setText(Integer.toString(part.getInStock()));
        priceField.setText(Double.toString(part.getPrice()));
        maxField.setText(Integer.toString(part.getMax()));
        minField.setText(Integer.toString(part.getMin()));
        /*Sets either Machine ID or Company Name if part is an 
          Inhouse Part or Outsourced Part, respectively*/
        if (part instanceof InhousePart) {
            inHouseRadio.setSelected(true);
            variableLabel.setText("Machine ID");
            variableField.setPromptText("Machine ID");
            variableField.setText(Integer.toString(((InhousePart) part).getMachineID()));
        }
        else if (part instanceof OutsourcedPart) {
            outsourcedRadio.setSelected(true);
            variableLabel.setText("Company Name");
            variableField.setPromptText("Company Name");
            variableField.setText(((OutsourcedPart) part).getCompanyName());
        }
    }
}
