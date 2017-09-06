/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookbuddy;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Tobi
 */
public class Book {
    
    private final SimpleStringProperty title;
    private final SimpleStringProperty author;
    private final SimpleIntegerProperty year;
    private final SimpleStringProperty edition;
    private final SimpleStringProperty publisher;
    private final SimpleStringProperty description;
    
//    public Book() {
//        
//    }
    
    public Book(String title, String author, int year, String edition, String publisher, String description) {
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.year = new SimpleIntegerProperty(year);
        this.edition = new SimpleStringProperty(edition);
        this.publisher = new SimpleStringProperty(publisher);
        this.description = new SimpleStringProperty(description);
    }
    
    // Setters
    public void setTitle(String title){
        this.title.set(title);
    }
    public void setAuthor(String author){
        this.author.set(author);
    }
    public void setYear(int year){
        this.year.set(year);
    }
    public void setEdition(String edition){
        this.edition.set(edition);
    }
    public void setPublisher(String publisher){
        this.publisher.set(publisher);
    }
    public void setDescription(String description){
        this.description.set(description);
    }
    
    // Getters
    public String getTitle() {
        return this.title.get();
    }
    public String getAuthor() {
        return this.author.get();
    }
    public int getYear() {
        return this.year.get();
    }
    public String getEdition() {
        return this.edition.get();
    }
    public String getPublisher() {
        return this.publisher.get();
    }
    public String getDescription() {
        return this.description.get();
    }
    
}
