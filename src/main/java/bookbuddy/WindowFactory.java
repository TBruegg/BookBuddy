/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookbuddy;

import bookbuddy.AddBookDialogController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
/**
 *
 * @author Tobi
 */
public class WindowFactory {
    
    public WindowFactory () {
        // Constructor
    }
    
    public Stage createWindow(String resourceString) throws IOException {
        return this.createWindow(resourceString, false, null);
    }
    
    public Stage createWindow(String resourceString, boolean resizable) throws IOException {
        return this.createWindow(resourceString, resizable, null);
    }
    
    public Stage createWindow(String resourceString, boolean resizable, Book book) throws IOException {
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resourceString));
            Parent root = (Parent)loader.load();
            Stage newBookWindow = new Stage();
            Scene newBookScene = new Scene(root);
            AddBookDialogController controller = (AddBookDialogController) loader.getController();
            
            if(book != null){                
                controller.loadBook(book);
            }
            
            newBookWindow.setScene(newBookScene);
            newBookWindow.setResizable(resizable);
            
            return newBookWindow;
        
        } catch (IOException e) {
            throw e;
        }
    }
}
