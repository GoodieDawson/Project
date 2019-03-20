package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

public class AddObservatoryController {

    @FXML
    private TextField nmetxtbx;

    @FXML
    private TextField yrtxtbx;

    @FXML
    private TextField countxtbx;

    @FXML
    private TextField areatxtbx;

    @FXML
    void addObservatory(ActionEvent event) {
        try {
            //1. Creating Connection
            Connection con = Database.startCon();

            //2. Creating Statement
            Statement stmnt = con.createStatement();

            //3. Execute Query
            Observatory obj = new Observatory(nmetxtbx.getText(),
                    countxtbx.getText(),
                    Integer.parseInt(yrtxtbx.getText()),
                    Double.parseDouble(areatxtbx.getText()));

            stmnt.executeUpdate("INSERT INTO observatory (obName, obcount, obYear, obarea) VALUES ('" +obj.getObName()+ "', '" +obj.getObCount()+"', "+obj.getObYear()+ ", " +obj.getObArea()+ ")");

            nmetxtbx.clear();
            countxtbx.clear();
            yrtxtbx.clear();
            areatxtbx.clear();

            //add popup stuff here

            stmnt.close();
            con.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void loadObservatory(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Observatory.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.hide();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
