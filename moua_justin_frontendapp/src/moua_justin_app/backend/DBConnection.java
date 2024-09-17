package moua_justin_app.backend;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DriverManager;

//This file does nothing it is meant for testing purposes.

public class DBConnection {
	
    // JDBC variables for opening and managing connection
    Connection connection = null;
    
    
	public void EstablishConn() {
	    //============DB CONN STARTS HERE============DB CONN STARTS HERE============DB CONN STARTS HERE============
	    // JDBC URL, username, and password of MySQL server
	    String url = "jdbc:mysql://localhost:3306/dbMywatchList"; //GRADER MUST CHANGE ACCORDINGLY IF NEEDED
	    String user = "root"; ////GRADER MUST CHANGE ACCORDINGLY IF NEEDED
	    String password = "1234"; ////GRADER MUST CHANGE ACCORDINGLY IF NEEDED

	
	    try {
	        // Open a connection
	    	//connection = DriverManager.getConnection(url, user, password);
	    	connection=(Connection)DriverManager.getConnection(url, user, password);
	        
	        if (connection != null) {
	        	System.out.println("database is connected");
	        }
	
	        // Perform database operations here...
	
	    } catch (Exception e) {
	        System.out.println("Connection failed.");
	    }
	    //============DB CONN ENDS HERE===============DB CONN ENDS HERE===============DB CONN ENDS HERE==============
	}
	
	public String selectAll(String strTable) {
	    EstablishConn();
	    StringBuilder result = new StringBuilder();
	    try {
	        String query = "SELECT * FROM " + strTable;

	        PreparedStatement statement = connection.prepareStatement(query);
	        // Execute the query and get the results
	        ResultSet resultSet = statement.executeQuery();

	        // Get metadata about the ResultSet
	        ResultSetMetaData metaData = resultSet.getMetaData();
	        int columnCount = metaData.getColumnCount();

	        // Iterate over the results and construct the string representation
	        while (resultSet.next()) {
	            // Loop through each column and append its value to the result string
	            for (int i = 1; i <= columnCount; i++) {
	                result.append(resultSet.getString(i));
	                // Add a pipe separator after each value except the last one
	                if (i < columnCount) {
	                    result.append(" | ");
	                }
	            }
	            // Add a newline after each row
	            result.append("\n");
	        }
	    } catch (Exception exception) {
	        exception.printStackTrace();
	    }
	    return result.toString();
	}

}
