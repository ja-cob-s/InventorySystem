/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.InhousePart;
import Model.Inventory;
import Model.Part;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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
public class AddInhousePartController implements Initializable {

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
    private Label machineIDLabel;
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
    private boolean validInput;
    private ScreenHelper helper;

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
        validInput = true;
        int partID = getPartID();
        String name = getName();
        double price = getPrice();
        int inStock = getInStock();
        int min = getMin();
        int max = getMax();
        int machineID = getMachineID();
        checkInvLevels(inStock, max, min);
                               
        if (validInput) {
            InhousePart part = new InhousePart(partID, name, price, inStock, min, max, machineID);
            inv.addPart(part);
            
            Stage stage = (Stage) saveButton.getScene().getWindow();
            helper.nextScreenHandler(stage, "MainScreen.fxml");
        }
    }

    @FXML
    private void inHouseRadioHandler(ActionEvent event) {
        //Inactive in this screen
    }

    @FXML
    private void outsourcedRadioHandler(ActionEvent event) throws IOException {
        //Switches to AddOutsourcedPart screen
        Stage stage = (Stage) outsourcedRadio.getScene().getWindow();
        helper.nextScreenHandler(stage, "AddOutsourcedPart.fxml");
    }
    
    public int getPartID() {
        int partID = 0;
        try { partID = Integer.parseInt(IDField.getText()); }
        catch(Exception e) { validInput = helper.IOExceptionHandler("Product ID field"); }
        return partID;
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
    
    public int getMachineID() {
        int machineID = 0;
        try { machineID = Integer.parseInt(machineIDField.getText()); }
        catch(Exception e) { validInput = helper.IOExceptionHandler("Machine ID field"); }
        return machineID;
    }
    
    public void checkInvLevels(int inStock, int max, int min) {
        boolean tmp;
        tmp = helper.invLevelsHandler(inStock, max, min);
        if (!tmp) { validInput = false; }
    }
}
