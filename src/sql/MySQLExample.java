package sql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import objects.DatabaseConnection;

public class MySQLExample {
    // JDBC URL, username, and password
	

    public static void main(String[] args) {
    	String url = "jdbc:mysql://localhost:3306/hospital";
    	try {
    		System.out.println("xello");
    	    Class.forName("com.mysql.cj.jdbc.Driver");
    	    Connection connection = DatabaseConnection.getConnection();
    	    Statement statement = connection.createStatement();
    	    
    	    ResultSet resultSet = statement.executeQuery("select * from doctor");
    	    
    	    while(resultSet.next()) {
    	    	System.out.println(resultSet.getString(2));
    	    }
    	    
    	    connection.close();
    		}
    		catch(Exception e) {
    			System.out.println(e);
    		}
    }

    
}
