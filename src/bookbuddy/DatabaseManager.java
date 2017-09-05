/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookbuddy;

import java.io.IOException;
import org.sqlite.JDBC;
import java.sql.*;

/**
 *
 * @author Tobi
 */
public class DatabaseManager {
    String dbConnString;
    Statement stmt;
    String newBookQuery = "INSERT INTO books(title, author, year, edition, publisher, description)"
            + " VALUES (?,?,?,?,?,?)";
    Connection conn = null;
    
    public DatabaseManager(String dbConnString) {
        this.dbConnString = dbConnString;
    }
    
    public Connection getDBConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(this.dbConnString);
            conn.setAutoCommit(false);
            return conn;
        } catch(Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }
    
    public boolean hasConnection() {
        return (conn != null);
    }
    
    public ResultSet getAllBooks() {
        try {
            this.conn = this.getDBConnection();
            this.stmt = this.conn.createStatement();
            ResultSet bookResults = this.stmt.executeQuery("SELECT * FROM books;");
            return bookResults;
        } catch(Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return null;
        }
    }
    
    public void saveBook(Book newBook){
        PreparedStatement insertNewBook;
        System.out.println("Saving book...");
        try {
          this.conn = this.getDBConnection();
          insertNewBook = this.conn.prepareStatement(this.newBookQuery);
          insertNewBook.setString(1, newBook.getTitle());
          insertNewBook.setString(2, newBook.getAuthor());
          insertNewBook.setInt(3, newBook.getYear());
          insertNewBook.setString(4, newBook.getEdition());
          insertNewBook.setString(5, newBook.getPublisher());
          insertNewBook.setString(6, newBook.getDescription());
          insertNewBook.executeUpdate();
          this.conn.commit();
          this.closeConnection();
        } catch(Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    
    public void closeConnection() throws SQLException {
        if(this.stmt != null){
            this.stmt.close();
        }
        if(this.conn != null){
            this.conn.close();        
            this.conn = null;
        }
    }
    
}
