/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookbuddy;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;


/**
 *
 * @author Tobi
 */
public class FXMLDocumentController implements Initializable {
    
    private WindowFactory windowBuilder;
    private DatabaseManager dbManager;
    private ObservableList<String> books = FXCollections.observableArrayList(); 
    
    @FXML
    private TextField searchTextField;
    @FXML
    private Button startSearchBtn;
    @FXML
    private ListView bookListView;
    
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
        this.dbManager = new DatabaseManager("jdbc:sqlite:data/books.db");
        ResultSet allBooks = this.dbManager.getAllBooks();

        try {
            if(allBooks != null) {
                while(allBooks.next()){
                    String title = allBooks.getString("title");
                    this.books.add(title);
                }
            }
            this.bookListView.setItems(this.books);
            allBooks.close();
            this.dbManager.closeConnection();
        } catch(Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }    
    
}
