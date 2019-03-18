/**
 * 
 */
package project;

/**
 * @author Goodie Blake Dawson
 *
 */
import java.sql.*;

public class Database {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			//1. Creating Connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/galamseydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "Blakestake7*");
			
			//2. Creating Statement
			Statement stmnt = con.createStatement();
					
			//3. Execute Query
			ResultSet rs = stmnt.executeQuery("select * from galamsey");
			
			//4. Processing Result
			while (rs.next()) {
				System.out.println(rs.getString("galamseyid") + "," + rs.getString("vegcol"));
			}
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
