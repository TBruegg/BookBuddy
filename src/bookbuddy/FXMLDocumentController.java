/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookbuddy;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.stage.Stage;


/**
 *
 * @author Tobi
 */
public class FXMLDocumentController implements Initializable {
    
    private WindowFactory windowBuilder;
    
    @FXML
    private TextField searchTextField;
    @FXML
    private Button startSearchBtn;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        searchTextField.setText("Hello World!");
        startSearchBtn.setText("Clicked!");
    }
    
    @FXML
    private void handleAddButtonAction(ActionEvent event) throws IOException {
        System.out.println("Add button was clicked!");
        windowBuilder = new WindowFactory();
        String fxmlResource = "AddBookDialog.fxml";
        
        Stage addBookWindow = windowBuilder.createWindow(fxmlResource);
        addBookWindow.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
}
