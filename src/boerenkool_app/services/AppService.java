package boerenkool_app.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import boerenkool_app.ApplicationDefaults;

public class AppService {
	
	public static AppService instance = new AppService();
	
	public Connection connection;
	
	
	public AppService() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(ApplicationDefaults.getLink(), ApplicationDefaults.USERNAME, ApplicationDefaults.PASSWORD);
		} catch (SQLException e) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					JOptionPane.showMessageDialog(null, "Unable to connect to database, please check your internet connection.", "Connection failed", JOptionPane.ERROR_MESSAGE);
				}
			});
		} catch (ClassNotFoundException e) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					JOptionPane.showMessageDialog(null, "jdbc Driver was not found", "Missing driver", JOptionPane.ERROR_MESSAGE);
				}
			});
		}  
	}

	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	public void setData(String name, int score) throws SQLException {
//		Statement stmt = con.createStatement();  
//		stmt.executeUpdate("INSERT INTO Scoreboard VALUES (NULL, " + score + ", '" + name + "', NULL)");
//	}
	

}
