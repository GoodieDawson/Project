/**
 * A class called MonitoringIO with a main method that provides the user with a menu of features
 * for entering observatory data, entering galamsey data, providing monitoring statistics and exit the menu.
 * It takes user input and allows the user to input via the console, the details of the observatory and Galamsey. 
 */
package project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 * @author Group_18
 *
 */
public class MonitoringIO extends Monitoring{

	/**
	 * A method that prompts the user to input details for one observatory and
	 * creates an object of the Observatory class with those details.
	 */
	public static void enob () {
		System.out.println("Please enter the following details in this order about your Obsevatory event, separated by commas:"
				+ "\nName of the Obsevartory"
				+ "\nThe country in which the Obsevartory is based"
				+ "\nThe country in which the Obsevartory is based"
				+ "\nThe Year in which observations began"
				+ "\nThe area covered by the Observatory in square kilometers");
		
		Scanner input = new Scanner(System.in);

		String obdata = "";
		if (input.hasNextLine()) {
			obdata = input.nextLine();
		}
		
		String[] ob = obdata.split(",");
		Observatory a = new Observatory(ob[0], ob[1], Integer.parseInt(ob[2]), Double.parseDouble(ob[3]));
		try {
			//1. Creating Connection
			Connection con = Database.startCon();

			//2. Creating Statement
			Statement stmnt = con.createStatement();

			//3. Execute Query
			stmnt.executeUpdate("INSERT INTO observatory (obName, obcount, obYear, obarea) VALUES ('" +a.getObName()+ "', '" +a.getObCount()+"', "+a.getObYear()+ ", " +a.getObArea()+ ")");

			System.out.println("Observatory succesfully added");
			stmnt.close();
			con.close();

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		start();
	}
	
	/**
	 * A method that prompts the user to input details for one Galamsey event and
	 * creates an object of the Galamsey class with those details.
	 */
	public static void engal () {
		System.out.println("Please enter the following details in this order about your Galamsey event, separated by commas:"
				+ "\n\nVegetation colour of the area: Green, Yellow or Brown"
				+ "\nPosition of area: Longitude and latitude (sample format: 5.5862° N, 0.1752° W)"
				+ "\nYear of event");
		
		Scanner input = new Scanner(System.in);
		String obdata = "";
		if (input.hasNextLine()) {
			obdata = input.nextLine();
		}
		
		String[] obArray = obdata.split(",");
		Galamsey a = new Galamsey(obArray[0], Double.parseDouble(obArray[1]), Double.parseDouble(obArray[2]), Integer.parseInt(obArray[3]));
		try {
			//1. Creating Connection
			Connection con = Database.startCon();

			//2. Creating Statement
			Statement stmnt = con.createStatement();

			//3. Execute Query
			stmnt.executeUpdate("INSERT INTO galamsey (vegCol, colVal, longitude, latitude, year, obId) VALUES ('" +a.getVegCol()+ "', " +a.getColVal()+", "+a.getLon()+ ", " +a.getLat()+ ", " +a.getYear()+ ", " +a.getObId()+ ")");

			//add popup stuff here

			stmnt.close();
			con.close();

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		start();
	}
	
	public static void stats () {
		
		/**
		 * A method that prompts the user to input a word to get details on the observatory
		 * with the largest average Galamsey colour value 
		 */
		System.out.println("\nPlease find below a list of inputs for your stats actions:"
				+ "\nhiObAvg: To get the Observatory with the largest average Galamsey colour value"
				+ "\nmaxValEver: To get the largest Galamsey colour value ever recorded"
				+ "\ngivenList: To get all Galamsey with colour value greater than a given number");
		
		Scanner input = new Scanner(System.in);
		String task = "";
		if (input.hasNextLine()) {
			task = input.nextLine();
		}
		
		switch(task) {
		case "hiObAvg" : 
			System.out.println(hiObAvg().toString());
			start();
		case "maxValEver" : 
			System.out.println(maxValEver());
			start();
		case "givenList" : 
			System.out.println("Please input a number");
			int num  = input.nextInt();
			Galamsey x = null;
			try {
				//1. Creating Connection
				Connection con = Database.startCon();

				//2. Creating Statement
				Statement stmnt = con.createStatement();

				//3. Execute Query
				ResultSet rs = stmnt.executeQuery("select * from galamsey where colVal > " +num);

				//4. Processing Result
				while (rs.next()) {
					x = new Galamsey (Integer.parseInt(rs.getString("galamId")), rs.getString("vegCol"), Double.parseDouble(rs.getString("longitude")),Double.parseDouble(rs.getString("latitude")),Integer.parseInt(rs.getString("year")));
					System.out.println("\n" + x.toString());
				}

				rs.close();
				stmnt.close();
				con.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			start();
		}
		
	}
	
	public static void exit() {
		System.exit(0);
	}
	
	public static void start() {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("\nPlease find below a list of input words and their respective actions."
				+ "\nEnter one of the words to begin:"
				+ "\n\nenob: To input Obsevatory data"
				+ "\nengal: To input Galamsey data"
				+ "\nstats: To view certain monitoring statistics about the recorded data"
				+ "\nexit: To end the program");
					
			String task = input.nextLine();
			
			switch(task) {
			case "enob" : 
				enob();
				break;
			case "engal" : 
				engal();
				break;
			case "stats" : 
				stats();
				break;
			case "exit" : 
				exit();
				break;
			}

	}
		
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("Welcome to the Illegal Mining (Galamsey) Monitoring System."
				+ "\nThis program will help you keep track of Galamsey events and provide certain key statistics.");
		start();
		


	}
}
