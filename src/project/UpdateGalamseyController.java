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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdateGalamseyController {

    @FXML
    private Label lbl1;

    @FXML
    private ChoiceBox<String> vegcolchcbx;

    @FXML
    private TextField lattxtbx;

    @FXML
    private TextField lontxtbx;

    @FXML
    private TextField yrtxtbx;

    @FXML
    private ChoiceBox<String> chcbx;

    ObservableList<String> list = FXCollections.observableArrayList();
    ObservableList<String> list2 = FXCollections.observableArrayList("Green", "Yellow", "Brown");

    private int id;

    boolean validateInput(String longitude, String latitude, String year){
        try {
            double lon = Double.parseDouble(longitude);
            double lat = Double.parseDouble(latitude);
            int yr = Integer.parseInt(year);
            return true;
        }
        catch (NumberFormatException e){
            lbl1.setText("Update Failed: Please check your input");
            lbl1.setTextFill(Paint.valueOf("#d64541"));
            return false;
        }
    }

    @FXML
    void initialize(int galamId, String vegCol, double lat,double lon, int year) {
        id = galamId;
        lattxtbx.setText(String.valueOf(lat));
        lontxtbx.setText(String.valueOf(lon));
        yrtxtbx.setText(String.valueOf(year));

        try {
            //1. Creating Connection
            Connection con = Database.startCon();

            //2. Creating Statement
            Statement stmnt = con.createStatement();

            //3. Execute Query
            ResultSet rs = stmnt.executeQuery("select * from observatory");

            //4. Processing Result
            while (rs.next()) {
                list.add(rs.getString("obId"));
            }

            rs.close();
            stmnt.close();
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        vegcolchcbx.setItems(list2);
        chcbx.setItems(list);


    }
    @FXML
    void UpdateGalamsey(ActionEvent event) {
        if (validateInput(lontxtbx.getText(), lattxtbx.getText(), yrtxtbx.getText()) == true) {
            try {
                //1. Creating Connection
                Connection con = Database.startCon();

                //2. Creating Statement
                Statement stmnt = con.createStatement();

                //3. Execute Query
                if (chcbx.getValue() == null) {chcbx.setValue("0");}
                Galamsey obj = new Galamsey (vegcolchcbx.getValue(),
                        Double.parseDouble(lontxtbx.getText()),
                        Double.parseDouble(lattxtbx.getText()),
                        Integer.parseInt(yrtxtbx.getText()),
                        Integer.parseInt(chcbx.getValue()));
                stmnt.executeUpdate("UPDATE galamsey SET vegCol = '" +obj.getVegCol()+ "', colVal = "+obj.getColVal()+", longitude = "+obj.getLon()+", latitude = "+obj.getLat()+", year = "+obj.getYear()+", obId = "+obj.getObId()+" WHERE galamId = " +id );

                lbl1.setText("Update Successful");
                lbl1.setTextFill(Paint.valueOf("#80B4B4"));

                stmnt.close();
                con.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            vegcolchcbx.setValue(null);
            lontxtbx.clear();
            lattxtbx.clear();
            yrtxtbx.clear();
            chcbx.setValue(null);
        }


    }


    @FXML
    void loadGalamsey(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Galamsey.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.hide();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
