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
	 *Connection Method
	 */
	public static Connection startCon() throws SQLException {

			//1. Creating Connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/galamseydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "13n_pa55");


			return con;

	}

}
