package until;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBHelper {
	private static  String url = "jdbc:mysql://localhost:3306/crmdb";
	private static  String driver="com.mysql.jdbc.Driver" ;
	private static String user="root" ;
	private static  String password="123456" ;
	private static Connection connection;


  static {


	  try {
		  Class.forName(driver);
	  } catch (ClassNotFoundException e) {
		  e.printStackTrace();
	  }

  }

	public static Connection getConnection() throws SQLException {
       DBHelper helper=new DBHelper();
       connection = DriverManager.getConnection(url, user, password);
		return connection;
	}
}
