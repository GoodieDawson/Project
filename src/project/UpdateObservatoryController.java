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

public class UpdateObservatoryController {

    @FXML
    private TextField nmetxtbx;

    @FXML
    private TextField yrtxtbx;

    @FXML
    private TextField countxtbx;

    @FXML
    private TextField areatxtbx;

    private int id;

    @FXML
    private Label lbl1;

    boolean validateInput(String year, String area){
        try {
            int yr = Integer.parseInt(year);
            double lat = Double.parseDouble(area);
            return true;
        }
        catch (NumberFormatException e){
            lbl1.setText("Update Failed: Please check your input");
            lbl1.setTextFill(Paint.valueOf("#d64541"));
            return false;
        }
    }

    @FXML
    void initialize(int obId, String obName, String count, int year, double area) {
        id = obId;
        nmetxtbx.setText(obName);
        countxtbx.setText(count);
        yrtxtbx.setText(String.valueOf(year));
        areatxtbx.setText(String.valueOf(area));
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

    @FXML
    void updateObservatory(ActionEvent event) {
        if (validateInput(yrtxtbx.getText(), areatxtbx.getText()) == true) {
            try {
                //1. Creating Connection
                Connection con = Database.startCon();

                //2. Creating Statement
                Statement stmnt = con.createStatement();

                //3. Execute Query
                Observatory obj = new Observatory (nmetxtbx.getText(),
                        countxtbx.getText(),
                        Integer.parseInt(yrtxtbx.getText()),
                        Double.parseDouble(areatxtbx.getText()));
                stmnt.executeUpdate("UPDATE observatory SET obName = '" +obj.getObName()+"', obcount = '"+obj.getObCount()+"', obYear = "+obj.getObYear()+", obarea = "+obj.getObArea()+" WHERE obId = " +id );

                lbl1.setText("Update Successful");
                lbl1.setTextFill(Paint.valueOf("#80B4B4"));

                stmnt.close();
                con.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            nmetxtbx.clear();
            countxtbx.clear();
            yrtxtbx.clear();
            areatxtbx.clear();
        }
    }

}
