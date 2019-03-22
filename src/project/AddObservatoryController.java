package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

public class AddObservatoryController {

    @FXML
    private Label lbl1;

    @FXML
    private TextField nmetxtbx;

    @FXML
    private TextField yrtxtbx;

    @FXML
    private TextField countxtbx;

    @FXML
    private TextField areatxtbx;

    boolean validateInput(String year, String area){
        try {
            int yr = Integer.parseInt(year);
            double lat = Double.parseDouble(area);
            return true;
        }
        catch (NumberFormatException e){
            lbl1.setText("Addition Failed: Please check your input");
            lbl1.setTextFill(Paint.valueOf("#d64541"));
            return false;
        }
    }

    @FXML
    void addObservatory(ActionEvent event) {
        if (validateInput(yrtxtbx.getText(), areatxtbx.getText()) == true) {
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

                lbl1.setText("Addition Successful");
                lbl1.setTextFill(Paint.valueOf("#80B4B4"));

                stmnt.close();
                con.close();

            }
            catch (Exception e) {
                e.printStackTrace();
            }
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
