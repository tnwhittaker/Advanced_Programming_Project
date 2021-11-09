package accounts;
import java.sql.*;
import java.util.*;
import java.util.Date;
import javax.persistence.*;
import javax.swing.JOptionPane;
import connectionFiles.DBConnectorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Entity
@Table(name="category")

public class Category {	
		@Id
		@Column(name="id")
		private int ID;
		
		@Column(name="name")
		private String name;
		
		private static Connection connection=null;
		private Statement statement=null;
		private ResultSet rslt=null;
		private int rowsAffected=0;
		private static final Logger Logger = LogManager.getLogger(Category.class);

		@SuppressWarnings("deprecation")
		public Category()
		{
			ID=0;
			name="";
			connection= DBConnectorFactory.getDatabaseConnection();
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		
		public void create(int id,String name)
		{
			String insertSql= "INSERT category(id,name) VALUES ('"+id+"','"+name+"')";
			try {
				statement= connection.createStatement();
				rowsAffected=statement.executeUpdate(insertSql);
				if(rowsAffected==1)
				{
					JOptionPane.showMessageDialog(null, "Category record created","Creation",
							JOptionPane.INFORMATION_MESSAGE);
					Logger.info(name+" was successfully created in the database");
				}
			} catch (SQLException e) {
				System.err.println("Execption: "+e.getMessage());
				Logger.error(name+" was not added to the database");
				Logger.trace(e.getMessage());
			}
		}
		
		public void readAll()
		{
			String selectSQL="SELECT * FROM category WHERE 1=1";
			
			try {
				statement=connection.createStatement();
				rslt= statement.executeQuery(selectSQL);
				while(rslt.next())
				{
					int ID= rslt.getInt("id");
					String name= rslt.getString("name");
					
					System.out.println("ID#: "+ID+"\nCategory: "+name);
				}
				
			} catch (SQLException e) {
				System.err.println("Error selecting all "+e.getMessage());
			}
		}
		
		public void updateName(int ID, String newName)
		{
			String updateSQL="UPDATE category SET name='"+newName+"'WHERE id = "+ID;
			
			try {
				statement=connection.createStatement();
				rowsAffected= statement.executeUpdate(updateSQL);
				
				if(rowsAffected==1)
				{
					JOptionPane.showMessageDialog(null, "Record updated","Record Update",
							JOptionPane.INFORMATION_MESSAGE);
					Logger.info(name+" was successfully updated in the database");
				}
				
			} catch (SQLException e) {
				System.err.println("Error updating "+e.getMessage());
				Logger.error(name+" was not updated in the database");
				Logger.trace(e.getMessage());
			}
		}
		
		

		public void delete(int ID)
		{
			String deleteSQL="DELETE FROM category WHERE id="+ID;
			try {
				statement=connection.createStatement();
				rowsAffected= statement.executeUpdate(deleteSQL);
				if(rowsAffected==1)
				{
					JOptionPane.showMessageDialog(null, "Record Deleted","Record Deletion",
							JOptionPane.INFORMATION_MESSAGE);
					Logger.info("Record with ID number "+ID+" was successfully deleted in the database");
				}
						
				
			} catch (SQLException e) {
				System.err.println("Error deleting "+e.getMessage());
				Logger.error("Record with ID "+ID+" was not deleted from the database");
				Logger.trace(e.getMessage());
			}
		}

}
