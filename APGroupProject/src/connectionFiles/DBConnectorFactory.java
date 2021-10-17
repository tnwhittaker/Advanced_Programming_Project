package connectionFiles;

import java.sql.*;

import javax.swing.JOptionPane;

public class DBConnectorFactory 
{
	private static Connection connection=null;
	
	public static Connection getDatabaseConnection()
	{
		if(connection == null)
		{
			String url="jdbc:mysql://localhost:3306/dblab";
			try {
				connection= DriverManager.getConnection(url,"root","");
				if(connection!=null)
				{
					JOptionPane.showMessageDialog(null, "Connected to local server and database","JDBC Status",
							JOptionPane.INFORMATION_MESSAGE);
				}
				
			} catch (Exception e) 
			{
				System.err.println("Exception"+e.getMessage());
			}
		}
		
		return connection;
	}
	
	
}
