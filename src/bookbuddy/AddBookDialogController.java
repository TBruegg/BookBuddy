/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookbuddy;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tobi
 */
public class AddBookDialogController implements Initializable {

    @FXML
    private Button saveBookBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField titleField;
    @FXML
    private TextField authorField;
    @FXML
    private TextField yearField;
    @FXML
    private TextField editionField;
    @FXML
    private TextField publisherField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private ImageView bookImage;
    
    private DatabaseManager dbManager;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        this.saveBookBtn.setOnAction( (e) -> {
            this.saveBook();
        });
        
        this.cancelBtn.setOnAction((e) -> {
           this.closeWindow();
        });
        
        this.dbManager = new DatabaseManager("jdbc:sqlite:data/books.db");
        
    }   
    
    public void closeWindow(){
         Stage stage = (Stage) cancelBtn.getScene().getWindow();
            stage.close();
    }
    
    public void saveBook(){
        int year = !this.yearField.getText().isEmpty() ? Integer.parseInt(this.yearField.getText()) : 0;
        Book newBook = new Book(
            this.titleField.getText(),
            this.authorField.getText(),
            year,
            this.editionField.getText(),
            this.publisherField.getText(),
            this.descriptionField.getText()
        );
        this.dbManager.saveBook(newBook);
        this.closeWindow();
    }
        
}
