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
    
    private Part part;
    private boolean validInput;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Presets PartID
        IDField.setText(Integer.toString(Inventory.partsCnt + 1));

        // Sets togglegroup for radio buttons
        ToggleGroup toggleGroup = new ToggleGroup();
        inHouseRadio.setToggleGroup(toggleGroup);
        outsourcedRadio.setToggleGroup(toggleGroup);
    }    

    @FXML
    private void cancelButtonHandler(ActionEvent event) throws IOException {
        //Switches to main screen and discards changes when cancelButton pressed
        //Displays confirmation dialog first
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to discard changes?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user chose OK
            Stage stage; 
            Parent root;       
            stage=(Stage) cancelButton.getScene().getWindow();
            //load up OTHER FXML document
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                   "MainScreen.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
           // ... user chose CANCEL or closed the dialog
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
                               
        if (validInput) {
            InhousePart part = new InhousePart(partID, name, price, inStock, min, max, machineID);
            Inventory.allParts.add(part);
            
            Stage stage; 
            Parent root;       
            stage=(Stage) cancelButton.getScene().getWindow();
            //load up OTHER FXML document
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                   "MainScreen.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private void inHouseRadioHandler(ActionEvent event) {
        //Inactive in this screen
    }

    @FXML
    private void outsourcedRadioHandler(ActionEvent event) throws IOException {
        //Switches to AddOutsourcedPart screen
        Stage stage; 
        Parent root;       
        stage=(Stage) outsourcedRadio.getScene().getWindow();
        //load up OTHER FXML document
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
               "AddOutsourcedPart.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void IOExceptionHandler(String s) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Invalid input type in " + s);
        alert.showAndWait();

        System.out.println("Invalid input type in " + s); 
        validInput = false;
    }
    
    public int getPartID() {
        int partID = 0;
        try { partID = Integer.parseInt(IDField.getText()); }
        catch(Exception e) { IOExceptionHandler("Product ID field"); }
        return partID;
    }
    
    public String getName() {
        return nameField.getText();
    }    
    
    public int getInStock() {
        int inStock = 0;
        try { inStock = Integer.parseInt(invField.getText()); }
        catch(Exception e) { IOExceptionHandler("Inv field"); }
        return inStock;
    }
    
    public double getPrice() {
        double price = 0.0;
        try { price = Double.parseDouble(priceField.getText()); }
        catch(Exception e) { IOExceptionHandler("Price field"); }
        return price;
    }
    
    public int getMax() {
        int max = 0;
        try { max = Integer.parseInt(maxField.getText()); }
        catch(Exception e) { IOExceptionHandler("Max field"); }
        return max;
    }
    
    public int getMin() {
        int min = 0;
        try { min = Integer.parseInt(minField.getText()); }
        catch(Exception e) { IOExceptionHandler("Min field"); }
        return min;
    }
    
    public int getMachineID() {
        int machineID = 0;
        try { machineID = Integer.parseInt(machineIDField.getText()); }
        catch(Exception e) { IOExceptionHandler("Machine ID field"); }
        return machineID;
    }
}
