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
    private TextField idField;
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
    
    private boolean isNewBook;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        this.isNewBook = true;
        
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
    
    public void setIsNewBook(boolean isNewBook){
        this.isNewBook = isNewBook;
    }
    
    public void saveBook(){
        int year = !this.yearField.getText().isEmpty() ? Integer.parseInt(this.yearField.getText()) : 0;
        int id = !this.idField.getText().isEmpty() ? Integer.parseInt(this.idField.getText()) : -1;
        Book currentBook = new Book(
            id,
            this.titleField.getText(),
            this.authorField.getText(),
            year,
            this.editionField.getText(),
            this.publisherField.getText(),
            this.descriptionField.getText()
        );
        this.dbManager.saveBook(currentBook);
        this.closeWindow();
        BookBuddy.getController().loadBookList();
    }
    
    public void loadBook(Book book){
        this.idField.setText(Integer.toString(book.getId()));
        this.titleField.setText(book.getTitle());
        this.authorField.setText(book.getAuthor());
        this.yearField.setText(Integer.toString(book.getYear()));
        this.editionField.setText(book.getEdition());
        this.publisherField.setText(book.getPublisher());
        this.descriptionField.setText(book.getDescription());
    }
        
}
