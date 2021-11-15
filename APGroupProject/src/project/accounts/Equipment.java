package project.accounts;
import java.sql.*;
import javax.persistence.*;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import project.connectionFiles.DBConnectorFactory;
@Entity
@Table(name="equipment")
public class Equipment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int IDnum;
	
	@Column(name="name")
	private String name;
	
	@Column(name="category_id")
	private int categoryID;
	
	@Column(name="availability")
	private short availability;
	
	@Column(name="cost")
	private int cost;

	
	private static Connection connection=null;
	private Statement stmt=null;
	private ResultSet result=null;
	private int numRowsAffected=0;
	private static final Logger Logger = LogManager.getLogger(Equipment.class);
	
	public Equipment()
	{
		IDnum=0;
		name="";
		categoryID=0;
		availability=1;
		cost=0;
		connection= DBConnectorFactory.getDatabaseConnection();
	}

	public int getIDnum() {
		return IDnum;
	}

	public void setIDnum(int iDnum) {
		IDnum = iDnum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name= name;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost= cost;
	}

	
	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public short getAvailability() {
		return availability;
	}

	public void setAvailability(short availability) {
		this.availability = availability;
	}
	
	public void createEquipment(String name, int catID, short availability,int cost)
	{
String insertSql= "INSERT INTO groupproject.equipment(name, category_id, availability,cost) VALUES ('"+ name +"','"+catID+"','"+availability+"','"+cost+"')";   
		try {
			stmt= connection.createStatement();
			numRowsAffected=stmt.executeUpdate(insertSql);
			if(numRowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "Equipment record created","Equipment Creation",
						JOptionPane.INFORMATION_MESSAGE);
				Logger.info(name+" was successfully created in the database");
			}
		} catch (SQLException e) {
			System.err.println("Execption: "+e.getMessage());
			Logger.error(name+" was not added to the database");
			Logger.trace(e.getMessage());
		}
	}//Creates an equipment entry in the database
	
	public void readAll()
	{
		String selectSQL="SELECT * FROM equipment WHERE 1=1";
		
		try {
			stmt=connection.createStatement();
			result= stmt.executeQuery(selectSQL);
			while(result.next())
			{
				String id= result.getString("id");
				String name=result.getString("name");
				String catID=result.getString("category_id");
				short availability=result.getShort("availability");
				int cost= result.getInt("cost");
				System.out.println("ID#: "+id+"\nName: "+name+"\nCategory ID:"+catID+"\navailability: "+availability+"\nEquipment ID:"+cost);    
			}
			
		} catch (SQLException e) {
			System.err.println("Error selecting all "+e.getMessage());
		}
	}//Unused but this would read all from the database and display it in the console
	
	public void updateNAME(int id, String newName)
	{
		String updateSQL="UPDATE equipment SET name='"+newName+"'WHERE id = "+id;
		try {
			stmt=connection.createStatement();
			numRowsAffected= stmt.executeUpdate(updateSQL);
			
			if(numRowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "Equipement name has been updated","Record Update",
						JOptionPane.INFORMATION_MESSAGE);
				Logger.info(newName+" was successfully updated in the database");
			}
			
		} catch (SQLException e) {
			System.err.println("Error updating "+e.getMessage());
			Logger.error(newName+" was not updated to the database");
			Logger.trace(e.getMessage());
			
		}
	}//Updates the first name of a particular record 
	
	
	public void updateCost(int id, int newCost)
	{
		String updateSQL="UPDATE equipment SET cost='"+newCost+"'WHERE id = "+id;
		try {
			stmt=connection.createStatement();
			numRowsAffected= stmt.executeUpdate(updateSQL);
			
			if(numRowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "Equipement ID has been updated","Record Update",
						JOptionPane.INFORMATION_MESSAGE);
				Logger.info(newEQ+" was successfully updated in the database");
			}
			
		} catch (SQLException e) {
			System.err.println("Error updating "+e.getMessage());
			Logger.error(newEQ+" was not updated to the database");
			Logger.trace(e.getMessage());
			
		}
	}//Updates the cost of a particular record
	
	public void updateCATEGORY(int id, int newCATID)
	{
		String updateSQL="UPDATE users SET category_id'"+newCATID+"'WHERE id = "+id;
		try {
			stmt=connection.createStatement();
			numRowsAffected= stmt.executeUpdate(updateSQL);
			
			if(numRowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "Category ID has been updated","Record Update",
						JOptionPane.INFORMATION_MESSAGE);
				Logger.info(newCATID+" was successfully updated in the database");
			}
			
		} catch (SQLException e) {
			System.err.println("Error updating "+e.getMessage());
			Logger.error(newCATID+" was not updated to the database");
			Logger.trace(e.getMessage());
		}
	}//Updates the category ID of a particular record
	
	public void updateAvail(int id, short availability)
	{
		String updateSQL="UPDATE equipment SET availability='"+availability+"'WHERE id = "+id;
		try {
			stmt=connection.createStatement();
			numRowsAffected= stmt.executeUpdate(updateSQL);
			if(numRowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "Equpiment availability has been updated","Record Update",
						JOptionPane.INFORMATION_MESSAGE);
				Logger.info(availability+" was successfully updated in the database");
			}
			
		} catch (SQLException e) {
			System.err.println("Error updating "+e.getMessage());
			Logger.error(availability+" was not updated in the database");
			Logger.trace(e.getMessage());
		}
	}//Updates the availability of a particular record
	
	public void delete(int id)
	{
		String deleteSQL="DELETE FROM equipment WHERE ID="+id;
		try {
			stmt=connection.createStatement();
			numRowsAffected= stmt.executeUpdate(deleteSQL);
			if(numRowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "User record Deleted","Record Deletion",
						JOptionPane.INFORMATION_MESSAGE);
				Logger.info("Record with ID number "+id+" was successfully deleted in the database");
			}
					
			
		} catch (SQLException e) {
			System.err.println("Error deleting "+e.getMessage());
			Logger.error("Record with ID "+id+" was not deleted from the database");
			Logger.trace(e.getMessage());
		}
	}//Deletes a record from the database
	
	public void getAllAvailable() {
		String selectSQL="SELECT * FROM equipment WHERE availability=1";
		
		try {
			stmt=connection.createStatement();
			result= stmt.executeQuery(selectSQL);
			while(result.next())
			{
				String id= result.getString("id");
				String name=result.getString("name");
				String catID=result.getString("category_id");
				short availability=result.getShort("availability");
				String eqID= result.getString("eqID");
				System.out.println("ID#: "+id+"\nName: "+name+"\nCategory ID:"+catID+"\navailability: "+availability+"\nEquipment ID:"+eqID);   
				System.out.println("\n\n");
			}
			
		} catch (SQLException e) {
			System.err.println("Error selecting all "+e.getMessage());
		}
	}//Gets all records that are available in the database
	
	public void getAllUnavailable() {
		String selectSQL="SELECT * FROM equipment WHERE availability=2";
		
		try {
			stmt=connection.createStatement();
			result= stmt.executeQuery(selectSQL);
			while(result.next())
			{
				String id= result.getString("id");
				String name=result.getString("name");
				String catID=result.getString("category_id");
				short availability=result.getShort("availability");
				String eqID= result.getString("eqID");
				System.out.println("ID#: "+id+"\nName: "+name+"\nCategory ID:"+catID+"\navailability: "+availability+"\nEquipment ID:"+eqID); 
				System.out.println("\n\n");
			}
			
		} catch (SQLException e) {
			System.err.println("Error selecting all "+e.getMessage());
		}
	}//Gets all records that are unavailable in the database

	
}
