/**
 * A class called Monitoring that extends the Observatory class.
 * It contains information about all the observatories, as well as methods to retrieve
 * some key statistics about the data.
 */
package project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author Group_18
 *
 */
public class Monitoring extends Observatory{

	private static ArrayList<Observatory> ObList = new ArrayList<Observatory>();
	
	/**
	 * A method that returns the observatory with the largest average Galamsey colour value.
	 * @return
	 */
	public static Observatory hiObAvg () {
		int sum = 0;
		int count = 0;
		double avg = 0;
		int avgId = 0;
		Observatory x = null;

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
				x = new Observatory (Integer.parseInt(rs3.getString("obId")), rs3.getString("obName"), rs3.getString("obCount"), Integer.parseInt(rs3.getString("obYear")), Double.parseDouble(rs3.getString("obArea")));

			}
			return x;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	/**
	 * A method that returns the largest Galamsey colour value ever recorded in all the observatories.
	 * @return
	 */
	public static int maxValEver() {
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
			return max;
		}
		catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}
	
	/**
	 * A method that takes in a given number and returns a list of all Galamsey events
	 * recorded in all observatories with a colour value greater than a given number.
	 *
	 * @param num
	 * @return
	 */
	public static ArrayList<Galamsey> givenList(int num) {
		ArrayList<Galamsey> givList = new ArrayList<Galamsey>();
		if (ObList.isEmpty()) {
			System.out.println("No list can be created as there are no observatories");
		}
		else {
			for (Observatory obj : ObList) {
				givList.addAll(obj.aboveList(num));
			}
		}
		return givList;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

	}

}
