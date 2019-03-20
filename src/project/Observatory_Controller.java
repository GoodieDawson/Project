package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.io.IOException;

public class Observatory_Controller {

    @FXML
    private TableColumn<?, ?> obName;

    @FXML
    private TableColumn<?, ?> obCount;

    @FXML
    private TableColumn<?, ?> obYear;

    @FXML
    private TableColumn<?, ?> obArea;

    @FXML
    void loadMain_Menu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Main_Menu.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.hide();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
