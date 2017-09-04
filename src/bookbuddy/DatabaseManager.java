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
    Connection conn = null;
    
    public DatabaseManager(String dbConnString) {
        this.dbConnString = dbConnString;
        conn = this.getDBConnection();
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
            this.stmt = this.conn.createStatement();
            ResultSet bookResults = this.stmt.executeQuery("SELECT * FROM books;");
            return bookResults;
        } catch(Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return null;
        }
    }
    
    public void closeConnection() throws SQLException {
        this.stmt.close();
        this.conn.close();
    }
    
}
