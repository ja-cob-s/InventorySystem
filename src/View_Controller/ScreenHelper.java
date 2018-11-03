/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * The purpose of this class is to handle tasks common to all screen controllers
 * as to avoid writing redundant code for each screen controller
 * @author jnsch
 */
public class ScreenHelper {
    private boolean validInput; //Flag set to false if any field is invalid
    
    public ScreenHelper() {
        validInput = true;
    }
    
    public void setValidInput(boolean b) {
        validInput = b;
    }
    
    public boolean getValidInput() {
        return validInput;
    }
    
    public boolean showConfirmationDialog(String s) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText(null);
        alert.setContentText(s);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
            return true;
        } else {
            // ... user chose CANCEL or closed the dialog
            return false;
        }
    }
    
    public void showWarningDialog(String s) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
    }
    
    public boolean IOExceptionHandler(String s) {
        this.showWarningDialog("Invalid input type in " + s);
        return false;
    }
    
    public void nextScreenHandler(Stage stage, String s) throws IOException {
        //Loads the next screen
        Parent root;   
        FXMLLoader loader = new FXMLLoader(getClass().getResource(s));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public Object nextScreenControllerHandler(Stage stage, String s) throws IOException {
        //Loads the next screen but also passes an object to the next screen
        Parent root;   
        FXMLLoader loader = new FXMLLoader(getClass().getResource(s));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        return loader.getController();
    }
    
    public boolean emptyStringHandler (String s, String field) {
        //Checks if string is empty and displays error if so
        String nullString = null;
        String empty = "";
        
        if (s.equals(nullString) || s.equals(empty)) {
            this.showWarningDialog(field + " cannot be empty.");
            return false;
        } else {
            return true;
        }
    }
    
    public void invLevelsHandler (int inStock, int max, int min) {
        boolean tmp = true;
        
        if (inStock > max || inStock < min) {
            this.showWarningDialog("In Stock level must be between Min and Max.");
            tmp = false;
        } //Checked if inStock between min and max
        if (min < 0) {
            this.showWarningDialog("Min cannot be negative.");
            tmp = false;
        } //Checked if min is negative value
        if (min > max) {
            this.showWarningDialog("Min must be less than Max.");
            tmp = false;
        } //Checked if min < max, max > min
        
        if(!tmp) { this.setValidInput(tmp); }
    }
    
    public int getInt(String s, String IOExceptionText) {
        int i = 0;
        try { i = Integer.parseInt(s); }
        catch(Exception e) { this.setValidInput(IOExceptionHandler(IOExceptionText)); }
        return i;
    }
    
    public double getDouble(String s, String IOExceptionText) {
        double d = 0;
        try { d = Double.parseDouble(s); }
        catch(Exception e) { this.setValidInput(IOExceptionHandler(IOExceptionText)); }
        return d;
    }
    
    public String getString(String s, String IOExceptionText) {
        boolean tmp;
        tmp = emptyStringHandler(s, IOExceptionText);
        if (!tmp) { this.setValidInput(false); }
        return s;
    }
}
