package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DbAccesser {

	MysqlDataSource ds = null;
	Connection connect = null;
	Statement statement = null;

	public String getAllMovies(){
		String result="<table style=\"width:50%\" align=\"centre\" border=\"1\" cellpadding=\"5\"> <tr> <th> Movie Name</th> <th>Run Time</th></tr>";
		try {
			// Create a new DataSource (MySQL specifically)
			// and provide the relevant information to be used by Tomcat.
			ds = new MysqlDataSource();
			ds.setUrl("jdbc:mysql://localhost:3306/Demo");
			ds.setUser("root");
			ds.setPassword("prak2310");

			connect = ds.getConnection();

			// Create the statement to be used to get the results.
			statement = connect.createStatement();
			String query = "SELECT * FROM MOVIE";

			// Execute the query and get the result set.
			
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				String movieName = resultSet.getString("Movie_Name");
				int runTime = resultSet.getInt("Run_Time");
				result+= "<tr> <td>" + movieName +"</td><td>" + runTime + "</td></tr>";
			}
			result+= "</table>";
		} catch (SQLException e) { 
		} finally {
			// Close the connection and release the resources used.
			try { statement.close(); } catch (SQLException e){  }
			try { connect.close(); } catch (SQLException e) {  }
		}
		return result;
	}
}
