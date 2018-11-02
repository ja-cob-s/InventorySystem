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
 *
 * @author jnsch
 */
public class ScreenHelper {
    
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
        showWarningDialog("Invalid input type in " + s);
        return false;
    }
    
    public void nextScreenHandler(Stage stage, String s) throws IOException {
        Parent root;   
        FXMLLoader loader = new FXMLLoader(getClass().getResource(s));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public Object nextScreenControllerHandler(Stage stage, String s) throws IOException {
        Parent root;   
        FXMLLoader loader = new FXMLLoader(getClass().getResource(s));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        return loader.getController();
    }
    
    public boolean emptyStringHandler (String s, String field) {
        String nullString = null;
        String empty = "";
        
        if (s.equals(nullString) || s.equals(empty)) {
            showWarningDialog(field + " cannot be empty!");
            return false;
        } else {
            return true;
        }
    }
    
    public boolean invLevelsHandler (int inStock, int max, int min) {
        boolean tmp = true;
        
        if (inStock > max || inStock < min) {
            showWarningDialog("In Stock level must be between Min and Max!");
            tmp = false;
        } 
        if (min < 0) {
            showWarningDialog("Min cannot be negative!");
            tmp = false;
        } 
        if (min > max) {
            showWarningDialog("Min must be less than Max!");
            tmp = false;
        }
        
        return tmp;
    }
}
