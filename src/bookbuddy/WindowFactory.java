/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookbuddy;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        return this.createWindow(resourceString, false);
    }
    
    public Stage createWindow(String resourceString, boolean resizable) throws IOException {
        try {
            
            Parent root = FXMLLoader.load(getClass().getResource(resourceString));
            Stage newBookWindow = new Stage();
            Scene newBookScene = new Scene(root);
            
            newBookWindow.setScene(newBookScene);
            newBookWindow.setResizable(resizable);
            
            return newBookWindow;
        
        } catch (IOException e) {
            throw e;
        }
    }
}
