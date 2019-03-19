/**
 * 
 */
package project;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Goodie Blake Dawson
 *
 */
public class Main_Menu_Controller {

    @FXML
    void Exit(ActionEvent event) {

    }

    @FXML
    void opengalam(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("Galamsey.fxml"));
    	Scene scene = new Scene(root);
    	Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	primaryStage.hide();
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }


}
