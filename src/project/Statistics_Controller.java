package project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.management.StringValueExp;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Statistics_Controller {

    @FXML
    private ChoiceBox<String> chbx1;

    @FXML
    private TableView<Observatory> obTable;

    @FXML
    private TableColumn<Observatory, String> obId;

    @FXML
    private TableColumn<Observatory, String> obName;

    @FXML
    private TableColumn<Observatory, String> obCount;

    @FXML
    private TableColumn<Observatory, String> obYear;

    @FXML
    private TableColumn<Observatory, String> obArea;

    @FXML
    private TableView<Galamsey> galtable;

    @FXML
    private TableColumn<Galamsey, String> galId;

    @FXML
    private TableColumn<Galamsey, String> vegCol;

    @FXML
    private TableColumn<Galamsey, String> colVal;

    @FXML
    private TableColumn<Galamsey, String> lon;

    @FXML
    private TableColumn<Galamsey, String> lat;

    @FXML
    private TableColumn<Galamsey, String> year;

    @FXML
    private ChoiceBox<String> chbx2;

    @FXML
    private Label result1;

    @FXML
    private Label result2;

    @FXML
    private TextField txtbx1;

    @FXML
    private TextField txtbx2;

    ObservableList<Observatory> list1 = FXCollections.observableArrayList();
    ObservableList<Galamsey> list2 = FXCollections.observableArrayList();
    ObservableList<String> list3 = FXCollections.observableArrayList("Largest Value", "Average Value", "Galamsey List", "Observatory: Largest Average");
    ObservableList<String> list4 = FXCollections.observableArrayList("Largest Value", "Above");

    @FXML
    private void initialize() {
        obId.setCellValueFactory(new PropertyValueFactory<>("obId"));
        obName.setCellValueFactory(new PropertyValueFactory<>("obName"));
        obCount.setCellValueFactory(new PropertyValueFactory<>("obCount"));
        obYear.setCellValueFactory(new PropertyValueFactory<>("obYear"));
        obArea.setCellValueFactory(new PropertyValueFactory<>("obArea"));

        try {
            //1. Creating Connection
            Connection con = Database.startCon();

            //2. Creating Statement
            Statement stmnt = con.createStatement();

            //3. Execute Query
            ResultSet rs = stmnt.executeQuery("select * from observatory");

            //4. Processing Result
            while (rs.next()) {
                list1.add(new Observatory (Integer.parseInt(rs.getString("obId")), rs.getString("obName"), rs.getString("obCount"), Integer.parseInt(rs.getString("obYear")), Double.parseDouble(rs.getString("obArea"))));
            }

            rs.close();
            stmnt.close();
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        chbx1.setItems(list3);
        txtbx1.setText(null);
        obTable.setItems(list1);

        galId.setCellValueFactory(new PropertyValueFactory<>("id"));
        vegCol.setCellValueFactory(new PropertyValueFactory<>("vegCol"));
        colVal.setCellValueFactory(new PropertyValueFactory<>("colVal"));
        lon.setCellValueFactory(new PropertyValueFactory<>("lon"));
        lat.setCellValueFactory(new PropertyValueFactory<>("lat"));
        year.setCellValueFactory(new PropertyValueFactory<>("year"));

        try {
            //1. Creating Connection
            Connection con = Database.startCon();

            //2. Creating Statement
            Statement stmnt = con.createStatement();

            //3. Execute Query
            ResultSet rs = stmnt.executeQuery("select * from galamsey");

            //4. Processing Result
            while (rs.next()) {
                list2.add(new Galamsey (Integer.parseInt(rs.getString("galamId")), rs.getString("vegCol"), Double.parseDouble(rs.getString("longitude")),Double.parseDouble(rs.getString("latitude")),Integer.parseInt(rs.getString("year"))));
            }

            rs.close();
            stmnt.close();
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        chbx2.setItems(list4);
        txtbx2.setText(null);
        galtable.setItems(list2);
    }

    @FXML
    void loadMainMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Main_Menu.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.hide();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void obLargestValue () {

        int max = 0;

        try {
            //1. Creating Connection
            Connection con = Database.startCon();

            //2. Creating Statement
            Statement stmnt = con.createStatement();

            //3. Execute Query
            Observatory obj = obTable.getSelectionModel().getSelectedItem();
            ResultSet rs = stmnt.executeQuery("select * from galamsey where obId = " +obj.getObId());

            while (rs.next()) {
                if (max < Integer.parseInt(rs.getString("colVal"))) {max = Integer.parseInt(rs.getString("colVal"));}
            }

            stmnt.close();
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        result1.setText(String.valueOf(max));
    }

    @FXML
    void averageValue() {
        int sum = 0;
        int count = 0;

        try {
            //1. Creating Connection
            Connection con = Database.startCon();

            //2. Creating Statement
            Statement stmnt = con.createStatement();

            //3. Execute Query
            Observatory obj = obTable.getSelectionModel().getSelectedItem();
            ResultSet rs = stmnt.executeQuery("select * from galamsey where obId = " +obj.getObId());

            while (rs.next()) {
                sum = sum  + Integer.parseInt(rs.getString("colVal"));
                count++;
            }


            stmnt.close();
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        double avg = sum / count;
        result1.setText(String.valueOf(avg));
    }

    @FXML
    void galList(int x) {
        galtable.getItems().clear();
        try {
            //1. Creating Connection
            Connection con = Database.startCon();

            //2. Creating Statement
            Statement stmnt = con.createStatement();

            //3. Execute Query
            Observatory obj = obTable.getSelectionModel().getSelectedItem();
            ResultSet rs = stmnt.executeQuery("select * from galamsey where obId = " +obj.getObId()+ " and colVal > " +x);
            while (rs.next()) {
                list2.add(new Galamsey (Integer.parseInt(rs.getString("galamId")), rs.getString("vegCol"), Double.parseDouble(rs.getString("longitude")),Double.parseDouble(rs.getString("latitude")),Integer.parseInt(rs.getString("year"))));
            }

            stmnt.close();
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        galtable.setItems(list2);
    }

    @FXML
    void obLargestAverage() {
        obTable.getItems().clear();
        int sum = 0;
        int count = 0;
        double avg = 0;
        int avgId = 0;

        try {
            //1. Creating Connection
            Connection con = Database.startCon();

            //2. Creating Statement
            Statement stmnt1 = con.createStatement();
            Statement stmnt2 = con.createStatement();

            //3. Execute Query
            ResultSet rs1 = stmnt1.executeQuery("select * from observatory");
            while (rs1.next()) {
                ResultSet rs2 = stmnt2.executeQuery("select * from galamsey where obId = " + rs1.getString("obId"));
                while (rs2.next()) {
                    sum = sum + Integer.parseInt(rs2.getString("colVal"));
                    count++;
                }

                if (avg < (sum / count)) {
                    avg = (sum / count);
                    avgId = Integer.parseInt(rs1.getString("obId"));
                }
            }

            ResultSet rs3 = stmnt1.executeQuery("select * from observatory where obId = " +avgId);
            while (rs3.next()) {
                list1.add(new Observatory (Integer.parseInt(rs3.getString("obId")), rs3.getString("obName"), rs3.getString("obCount"), Integer.parseInt(rs3.getString("obYear")), Double.parseDouble(rs3.getString("obArea"))));
            }

            result1.setText(String.valueOf(avg));
            stmnt1.close();
            con.close();
            obTable.setItems(list1);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void galLargestValue() {
        int max = 0;

        try {
            //1. Creating Connection
            Connection con = Database.startCon();

            //2. Creating Statement
            Statement stmnt = con.createStatement();

            //3. Execute Query
            ResultSet rs = stmnt.executeQuery("select * from galamsey");

            while (rs.next()) {
                if (max < Integer.parseInt(rs.getString("colVal"))) {max = Integer.parseInt(rs.getString("colVal"));}
            }

            stmnt.close();
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        result2.setText(String.valueOf(max));
    }

    @FXML
    void above(int x) {
        galtable.getItems().clear();
        try {
            //1. Creating Connection
            Connection con = Database.startCon();

            //2. Creating Statement
            Statement stmnt = con.createStatement();

            //3. Execute Query
            ResultSet rs = stmnt.executeQuery("select * from galamsey where colVal > " +x);
            while (rs.next()) {
                list2.add(new Galamsey (Integer.parseInt(rs.getString("galamId")), rs.getString("vegCol"), Double.parseDouble(rs.getString("longitude")),Double.parseDouble(rs.getString("latitude")),Integer.parseInt(rs.getString("year"))));
            }

            stmnt.close();
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        galtable.setItems(list2);
    }

    @FXML
    void obStatNav(ActionEvent event) {
        switch(chbx1.getValue()) {
            case "Largest Value" :
                obLargestValue();
                break;
            case "Average Value" :
                averageValue();
                break;
            case "Galamsey List" :
                if (txtbx1.getText() == null) {txtbx1.setText("0");}
                galList(Integer.parseInt(txtbx1.getText()));
                txtbx1.setText("");
                break;
            case "Observatory: Largest Average" :
                obLargestAverage();
                break;

        }
    }

    @FXML
    void galStatNav(ActionEvent event) {
        switch(chbx2.getValue().toString()) {
            case "Largest Value" :
                galLargestValue();
                break;
            case "Above" :
                if (txtbx2.getText() == null) {txtbx2.setText("0");}
                above(Integer.parseInt(txtbx2.getText()));
                txtbx2.setText("");
                break;


        }
    }
}
