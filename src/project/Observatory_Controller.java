package project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Observatory_Controller {

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

    ObservableList<Observatory> list = FXCollections.observableArrayList();

    @FXML
    void initialize(){
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
                list.add(new Observatory (Integer.parseInt(rs.getString("obId")), rs.getString("obName"), rs.getString("obCount"), Integer.parseInt(rs.getString("obYear")), Double.parseDouble(rs.getString("obArea"))));
            }

            rs.close();
            stmnt.close();
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        obTable.setItems(list);

    }

    @FXML
    void loadMain_Menu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Main_Menu.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.hide();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void loadAddObservatory(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddObservatory.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.hide();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void removeOb(ActionEvent event) {
        Observatory selectedItem = obTable.getSelectionModel().getSelectedItem();
        obTable.getItems().remove(selectedItem);

        try {
            //1. Creating Connection
            Connection con = Database.startCon();

            //2. Creating Statement
            Statement stmnt = con.createStatement();

            //3. Execute Query
            Observatory obj = obTable.getSelectionModel().getSelectedItem();
            stmnt.executeUpdate("delete from observatory where (obId = " +obj.getObId()+ ")");

            stmnt.close();
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void loadUpdateObservatory(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateObservatory.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.hide();
        primaryStage.setScene(scene);
        primaryStage.show();

        UpdateObservatoryController controller = loader.<UpdateObservatoryController>getController();
        Observatory obj = obTable.getSelectionModel().getSelectedItem();
        controller.initialize(obj.getObId(), obj.getObName(), obj.getObCount(), obj.getObYear(), obj.getObArea());
    }

}
