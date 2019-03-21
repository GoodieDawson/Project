
package project;

/**
 * @author Group18
 *
 */
import java.sql.*;

/**
 * A class that creates a connection to a database
 */
public class Database {

	/**
	 *Connection Method
	 */
	public static Connection startCon() throws SQLException {

			//1. Creating Connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/galamseydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "Blakestake7*");

			return con;

	}

}
