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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Add_Galamsey_Controller {
	
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

	@FXML
	void initialize () {
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
    void AddGalamsey(ActionEvent event) {
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
			
			stmnt.executeUpdate("INSERT INTO galamsey (vegCol, colVal, longitude, latitude, year, obId) VALUES ('" +obj.getVegCol()+ "', " +obj.getColVal()+", "+obj.getLon()+ ", " +obj.getLat()+ ", " +obj.getYear()+ ", " +obj.getObId()+ ")");
			
			vegcoltxtbx.clear();
			lontxtbx.clear();
			lattxtbx.clear();
			yrtxtbx.clear();
			chcbx.setValue(null);

			//add popup stuff here
			
			stmnt.close();
			con.close();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
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
