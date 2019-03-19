/**
 * 
 */
package project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * @author Goodie Blake Dawson
 *
 */
public class Galamsey_Controller {
	
    @FXML
    private Button addgalbtn;
    
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
    
    ObservableList<Galamsey> list = FXCollections.observableArrayList();
    
    @FXML
    void initialize() {
		galId.setCellValueFactory(new PropertyValueFactory<>("id"));
    	vegCol.setCellValueFactory(new PropertyValueFactory<>("vegCol"));
    	colVal.setCellValueFactory(new PropertyValueFactory<>("colVal"));
		lon.setCellValueFactory(new PropertyValueFactory<>("lon"));
		lat.setCellValueFactory(new PropertyValueFactory<>("lat"));
		year.setCellValueFactory(new PropertyValueFactory<>("year"));
    	
    	try {
			//1. Creating Connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/galamseydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "Blakestake7*");
			
			//2. Creating Statement
			Statement stmnt = con.createStatement();
					
			//3. Execute Query
			ResultSet rs = stmnt.executeQuery("select * from galamsey");
			
			//4. Processing Result
			while (rs.next()) {
				list.add(new Galamsey (Integer.parseInt(rs.getString("galamId")), rs.getString("vegCol"), Double.parseDouble(rs.getString("longitude")),Double.parseDouble(rs.getString("latitude")),Integer.parseInt(rs.getString("year"))));
			}
			
			rs.close();
			stmnt.close();
			con.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
    	
    	galtable.setItems(list);

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
    void loadAdd_Galamsey(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("Add_Galamsey.fxml"));
    	Scene scene = new Scene(root);
    	Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	primaryStage.hide();
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }
}
