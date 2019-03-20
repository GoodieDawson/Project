package project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Statistics_Controller {
    @FXML
    private ChoiceBox chbx;

    ObservableList<String> list = FXCollections.observableArrayList("stuff", "stuff2");

    @FXML
    private void initialize() {
        chbx.setItems(list);
    }

    @FXML
    void loadMenu_Menu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Main_Menu.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.hide();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void statnav(ActionEvent event) {
        switch(chbx.getValue().toString()) {

        }
    }
}
