package DbConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnect {
	
	public static Connection connection() {
		Connection con = null;
		try {

			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection("");


		} catch (SQLException exp) {
			System.out.println("Exception  is :" + exp);
		}
		return con;
	}


}
	
	