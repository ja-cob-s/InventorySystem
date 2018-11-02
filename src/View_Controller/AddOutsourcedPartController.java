/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Inventory;
import Model.OutsourcedPart;
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
public class AddOutsourcedPartController implements Initializable {

    @FXML
    private AnchorPane addOutsourcedPartScreenAnchor;
    @FXML
    private AnchorPane addOutsourcedPartAnchor;
    @FXML
    private Label addOutsourcedPartLabel;
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
    private Label companyNameLabel;
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
    private TextField companyNameField;
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
    private boolean validInput;
    private ScreenHelper helper;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        helper = new ScreenHelper();
        
        //Presets PartID
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
        String companyName = getCompanyName();
        checkInvLevels(inStock, max, min);
                               
        if (validInput) {
            OutsourcedPart part = new OutsourcedPart(partID, name, price, inStock, min, max, companyName);
            inv.addPart(part);
            
            Stage stage = (Stage) saveButton.getScene().getWindow();
            helper.nextScreenHandler(stage, "MainScreen.fxml");
        }
    }

    @FXML
    private void inHouseRadioHandler(ActionEvent event) throws IOException {
        //Switches to AddInhousePart screen
        Stage stage = (Stage) inHouseRadio.getScene().getWindow();
        helper.nextScreenHandler(stage, "AddInhousePart.fxml");
    }

    @FXML
    private void outsourcedRadioHandler(ActionEvent event) {
        //Inactive in this screen
    }
    
    public int getPartID() {
        int partID = 0;
        try { partID = Integer.parseInt(IDField.getText()); }
        catch(Exception e) { validInput = helper.IOExceptionHandler("Product ID field"); }
        return partID;
    }
    
    public String getName() {
        //Displays a warning dialog if field is empty
        validInput = helper.emptyStringHandler(nameField.getText(), "Name field");
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
    
    public String getCompanyName() {
        //Displays a warning dialog if field is empty
        validInput = helper.emptyStringHandler(nameField.getText(), "Company Name field");
        return companyNameField.getText();
    }    
    
    public void checkInvLevels(int inStock, int max, int min) {
        boolean tmp;
        tmp = helper.invLevelsHandler(inStock, max, min);
        if (!tmp) { validInput = false; }
    }
}
