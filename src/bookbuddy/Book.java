/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookbuddy;

/**
 *
 * @author Tobi
 */
public class Book {
    
    private String title;
    private String author;
    private int year;
    private String edition;
    private String publisher;
    private String description;
    
    public Book() {
        
    }
    
    public Book(String title, String author, int year, String edition, String publisher, String description) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.edition = edition;
        this.publisher = publisher;
        this.description = description;
    }
    
    // Setters
    public void setTitle(String title){
        this.title = title;
    }
    public void setAuthor(String author){
        this.author = author;
    }
    public void setYear(int year){
        this.year = year;
    }
    public void setEdition(String edition){
        this.edition = edition;
    }
    public void setPublisher(String publisher){
        this.publisher = publisher;
    }
    public void setDescription(String description){
        this.description = description;
    }
    
    // Getters
    public String getTitle() {
        return this.title;
    }
    public String getAuthor() {
        return this.author;
    }
    public int getYear() {
        return this.year;
    }
    public String getEdition() {
        return this.edition;
    }
    public String getPublisher() {
        return this.publisher;
    }
    public String getDescription() {
        return this.description;
    }
    
}
