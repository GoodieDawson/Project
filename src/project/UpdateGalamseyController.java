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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdateGalamseyController {

    @FXML
    private TextField vegcoltxtbx;

    @FXML
    private TextField lattxtbx;

    @FXML
    private TextField lontxtbx;

    @FXML
    private TextField yrtxtbx;

    @FXML
    private ChoiceBox<String> chcbx;

    ObservableList<String> list = FXCollections.observableArrayList();

    private int id;
    @FXML
    void initialize(int galamId, String vegCol, double lat,double lon, int year) {
        id = galamId;
        vegcoltxtbx.setText(vegCol);
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

        chcbx.setItems(list);

    }
    @FXML
    void UpdateGalamsey(ActionEvent event) {
        try {
            //1. Creating Connection
            Connection con = Database.startCon();

            //2. Creating Statement
            Statement stmnt = con.createStatement();

            //3. Execute Query
            if (chcbx.getValue() == null) {chcbx.setValue("0");}
            Galamsey obj = new Galamsey (vegcoltxtbx.getText(),
                    Double.parseDouble(lontxtbx.getText()),
                    Double.parseDouble(lattxtbx.getText()),
                    Integer.parseInt(yrtxtbx.getText()),
                    Integer.parseInt(chcbx.getValue()));
            stmnt.executeUpdate("UPDATE galamsey SET vegCol = '" +obj.getVegCol()+"' WHERE galamId = " +id );

            stmnt.close();
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        vegcoltxtbx.clear();
        lontxtbx.clear();
        lattxtbx.clear();
        yrtxtbx.clear();
        chcbx.setValue(null);
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
