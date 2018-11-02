/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

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
public class ModifyOutsourcedPartController implements Initializable {

    @FXML
    private AnchorPane modifyOutsourcedPartScreenAnchor;
    @FXML
    private AnchorPane modifyOutsourcedPartAnchor;
    @FXML
    private Label modifyOutsourcedPartLabel;
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
    
    private boolean validInput;
    private OutsourcedPart part;
    private ScreenHelper helper;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        helper = new ScreenHelper();
        
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
        //Saves changes to selected Part and returns to main screen
        validInput = true;
        String name = getName();
        int inStock = getInStock();
        double price = getPrice();
        int max = getMax();
        int min = getMin();
        String companyName = getCompanyName();
        checkInvLevels(inStock, max, min);
        
        if (validInput) {
            part.setName(name);
            part.setInStock(inStock);
            part.setPrice(price);
            part.setMax(max);
            part.setMin(min);
            part.setCompanyName(companyName);
            
            Stage stage = (Stage) saveButton.getScene().getWindow();
            helper.nextScreenHandler(stage, "MainScreen.fxml");
        }
    }

    @FXML
    private void inHouseRadioHandler(ActionEvent event) throws IOException {
        //Switches to modifyInhousePart screen
        Stage stage = (Stage) inHouseRadio.getScene().getWindow();
        helper.nextScreenHandler(stage, "ModifyInhousePart.fxml");
    }

    @FXML
    private void outsourcedRadioHandler(ActionEvent event) {
        //Inactive in this screen        
    }
    
    public void setPart(OutsourcedPart part) {
        this.part = part;
        
        IDField.setText(Integer.toString(part.getPartID()));
        nameField.setText(part.getName());
        invField.setText(Integer.toString(part.getInStock()));
        priceField.setText(Double.toString(part.getPrice()));
        maxField.setText(Integer.toString(part.getMax()));
        minField.setText(Integer.toString(part.getMin()));
        companyNameField.setText(part.getCompanyName());
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
        validInput = helper.emptyStringHandler(nameField.getText(), "Name field");
        return companyNameField.getText();
    }    
    
    public void checkInvLevels(int inStock, int max, int min) {
        boolean tmp;
        tmp = helper.invLevelsHandler(inStock, max, min);
        if (!tmp) { validInput = false; }
    }    
}
