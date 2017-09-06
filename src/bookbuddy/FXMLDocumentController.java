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
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 *
 * @author Tobi
 */
public class FXMLDocumentController implements Initializable {
    
    private WindowFactory windowBuilder;
    private DatabaseManager dbManager;
    private ObservableList<Book> books = FXCollections.observableArrayList(); 
    
    @FXML
    private TextField searchTextField;
    @FXML
    private Button startSearchBtn;
    @FXML
    private TableView bookListView;
    @FXML
    private TableColumn titleColumn;
    @FXML
    private TableColumn authorColumn;
    @FXML
    private TableColumn yearColumn;
    @FXML
    private TableColumn editionColumn;
    @FXML
    private TableColumn publisherColumn;
    @FXML
    private TableColumn descriptionColumn;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        searchTextField.setText("Hello World!");
        this.loadBookList();
        
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
        this.initColumnFactories();
        this.bookListView.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent evt){
                if(evt.getButton().equals(MouseButton.PRIMARY)){
                    if(evt.getClickCount() == 2){
                        System.out.println("Item clicked");
                    }
                }
            }
        });
        this.loadBookList();
    }    
    
    public void loadBookList(){
        this.books.clear();
        if(this.dbManager == null){
            this.dbManager = new DatabaseManager("jdbc:sqlite:data/books.db");
        }
        ResultSet allBooks = this.dbManager.getAllBooks();

        try {
            if(allBooks != null) {
                while(allBooks.next()){
                    String title = allBooks.getString("title");
                    String author = allBooks.getString("author");
                    int year = allBooks.getInt("year");
                    String edition = allBooks.getString("edition");
                    String publisher = allBooks.getString("publisher");
                    String description = allBooks.getString("description");
                    
                    Book currentBook = new Book(title, author, year, edition, publisher, description);
                    this.books.add(currentBook);
                }
            }
            this.bookListView.setItems(this.books);
            allBooks.close();
            this.dbManager.closeConnection();
        } catch(Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    
    public void initColumnFactories(){
        this.titleColumn.setCellValueFactory(
            new PropertyValueFactory<Book,String>("title")
        );
        this.authorColumn.setCellValueFactory(
            new PropertyValueFactory<Book,String>("author")
        );
        this.yearColumn.setCellValueFactory(
            new PropertyValueFactory<Book,String>("year")
        );
        this.editionColumn.setCellValueFactory(
            new PropertyValueFactory<Book,String>("title")
        );
        this.publisherColumn.setCellValueFactory(
            new PropertyValueFactory<Book,String>("publisher")
        );
        this.descriptionColumn.setCellValueFactory(
            new PropertyValueFactory<Book,String>("description")
        );
    }
    
}
