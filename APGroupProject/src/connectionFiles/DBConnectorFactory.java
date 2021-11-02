package connectionFiles;

import java.sql.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DBConnectorFactory 
{
	private static Connection connection=null;
	private static final Logger Logger = LogManager.getLogger(DBConnectorFactory.class);
	
	public static Connection getDatabaseConnection()
	{
		if(connection == null)
			
		{
			String url="jdbc:mysql://localhost:3306/groupproject";
			try {
				connection= DriverManager.getConnection(url,"root","");
				if(connection!=null)
				{
					Logger.info("Connection with database established");
				}
				
			} catch (Exception e) 
			{
				System.err.println("Exception"+e.getMessage());
				Logger.fatal("Connection to database could not be established");
			}
		}
		
		return connection;
	}
	
	
}
