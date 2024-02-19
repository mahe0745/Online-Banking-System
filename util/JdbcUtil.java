package in.ineuron.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {
	
	private JdbcUtil() {}

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws IOException ,SQLException{
		String fileLocation="C:\\Servlet\\OnlineBankingSystem\\src\\main\\java\\in\\ineuron\\properties\\application.properties";
		FileInputStream fileInputStream= new FileInputStream(fileLocation);
		Properties property= new Properties();
		property.load(fileInputStream);
		Connection connection = DriverManager.getConnection(property.getProperty("jdburl"),property.getProperty("user"),
				property.getProperty("password"));
		return connection; 
	}
	
}
