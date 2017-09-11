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
    String updateBookQuery = "UPDATE books SET title = ?, author = ?, year = ?, edition = ?, "
            + "publisher = ?, description = ? WHERE id = ?;";
    String getByIdQuery = "SELECT * FROM books WHERE id = ? LIMIT 1;";
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
            //this.closeConnection();
            return bookResults;
        } catch(Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return null;
        }
    }
    
    public ResultSet getBookById(int id) {
        PreparedStatement findBook;
        try {
            this.conn = this.getDBConnection();
            findBook = this.conn.prepareStatement(this.getByIdQuery);
            findBook.setInt(1, id);
            ResultSet bookResult = findBook.executeQuery();
            this.conn.commit();
            //this.closeConnection();
            return bookResult;
        } catch(Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return null;
        }
    }
    
    public void saveBook(Book book){
        PreparedStatement saveStatement;
        String saveQuery =  book.getId() == -1 ? this.newBookQuery : this.updateBookQuery;
        System.out.println("Saving book...");
        try {
          this.conn = this.getDBConnection();
          saveStatement = this.conn.prepareStatement(saveQuery);
          saveStatement.setString(1, book.getTitle());
          saveStatement.setString(2, book.getAuthor());
          saveStatement.setInt(3, book.getYear());
          saveStatement.setString(4, book.getEdition());
          saveStatement.setString(5, book.getPublisher());
          saveStatement.setString(6, book.getDescription());
          if(book.getId() != -1){
              saveStatement.setInt(7, book.getId());
          }
          saveStatement.executeUpdate();
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
