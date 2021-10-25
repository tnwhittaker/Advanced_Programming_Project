package inventory;

import java.sql.*;

import javax.persistence.*;
import javax.swing.JOptionPane;
import connectionFiles.DBConnectorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import accounts.User;
@Entity
@Table(name="inventory")

public class Inventory {
	@Id
	@Column(name="ItemID")
	private String itemID;
	
	
	@Column(name="ItemName")
	private String itemName;
	
	@Column(name="Category")
	private String category;
	
	@Column(name="Status")
	private String status;
	
	@Column(name="DateofRequirement")
	private String dateOfRequirement;
	
	@Column(name="DateofRequirement")
	private int ID;

	
	private static Connection connection=null;
	private Statement statement=null;
	private ResultSet rslt=null;
	private int rowsAffected=0;
	private static final Logger Logger = LogManager.getLogger(Inventory.class);
		
	public Inventory()
	{
		itemID="";
		itemName="";
		category="";
		status="";
		dateOfRequirement= "";
		ID=0;
		connection= DBConnectorFactory.getDatabaseConnection();
	}

	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDateOfRequirement() {
		return dateOfRequirement;
	}

	public void setDateOfRequirement(String dateOfRequirement) {
		this.dateOfRequirement = "";
	}
	
	public void create(String itemID, String itemName, String Category, String Status, String DOR, int ID)
	{
		String insertSql= "INSERT INTO groupproject.inventory(itemID, ItemName, Category, Status,DateofRequirement, ID) VALUES ('"+ itemID +"','"+itemName+"','"+Category+"','"+Status+"','"+DOR+"','"+ID+"')";
		try {
			statement= connection.createStatement();
			rowsAffected=statement.executeUpdate(insertSql);
			if(rowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "Inventory record created","Inventory Creation",
						JOptionPane.INFORMATION_MESSAGE);
				Logger.info("Record with ID "+itemID+" was successfully created in the database");
			}
		} catch (SQLException e) {
			System.err.println("Execption: "+e.getMessage());
			Logger.error("Record with ID "+itemID+" was not added to the database");
			Logger.trace(e.getMessage());
		}
	}
	
	public void readAll()
	{
		String selectSQL="SELECT * FROM inventory WHERE 1=1";
		
		try {
			statement=connection.createStatement();
			rslt= statement.executeQuery(selectSQL);
			while(rslt.next())
			{
				String itemID= rslt.getString("itemID");
				String ItemName= rslt.getString("ItemName");
				String Category= rslt.getString("Category");
				String Status= rslt.getString("Status");
				String DOR= rslt.getString("DateofRequirement");
				int ID= rslt.getInt("ID");
				System.out.println("ID#: "+itemID+"\nItem Name: "+ItemName+"\nCategory: "+Category+"\nStatus: "+Status+"\nDate of Requirement"+DOR+"\nID: "+ID);
			}
			
		} catch (SQLException e) {
			System.err.println("Error selecting all "+e.getMessage());
		}
	}
	
	public void updateItemName(String itemID, String newName)
	{
		String updateSQL="UPDATE groupproject.inventory SET ItemName='"+newName+"'WHERE ItemID = "+itemID;
		
		try {
			statement=connection.createStatement();
			rowsAffected= statement.executeUpdate(updateSQL);
			
			if(rowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "Inventory record updated","Inventory Creation",
						JOptionPane.INFORMATION_MESSAGE);
				Logger.info(newName+" was successfully updated in the database");
			}
			
		} catch (SQLException e) {
			System.err.println("Error updating "+e.getMessage());
			Logger.error(newName+" was not updated in the database");
			Logger.trace(e.getMessage());
		}
	}
	
	public void updateCategory(String itemID, String newCategory)
	{
		String updateSQL="UPDATE inventory SET ItemName='"+newCategory+"'WHERE ItemID = "+itemID;
		
		try {
			statement=connection.createStatement();
			rowsAffected= statement.executeUpdate(updateSQL);
			
			if(rowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "Inventory record updated","Inventory Creation",
						JOptionPane.INFORMATION_MESSAGE);
				Logger.info(itemID+" was successfully updated in the database");
			}
			
		} catch (SQLException e) {
			System.err.println("Error updating "+e.getMessage());
			Logger.error(newCategory+" was not updated in the database");
			Logger.trace(e.getMessage());
		}
	}
	
	public void updateStatus(String itemID, String newStatus)
	{
		String updateSQL="UPDATE groupproject.inventory SET ItemName='"+newStatus+"'WHERE ItemID = "+itemID;
		
		try {
			statement=connection.createStatement();
			rowsAffected= statement.executeUpdate(updateSQL);
			
			if(rowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "Inventory record updated","Inventory Creation",
						JOptionPane.INFORMATION_MESSAGE);
				Logger.info(itemID+" was successfully updated in the database");
			}
			
		} catch (SQLException e) {
			System.err.println("Error updating "+e.getMessage());
			Logger.error(newCategory+" was not updated in the database");
			Logger.trace(e.getMessage());
		}
	}
	
	public void updateDateOfRequirement(String itemID, String newDOF)
	{
		String updateSQL="UPDATE groupproject.inventory SET ItemName='"+newDOF+"'WHERE ItemID = "+itemID;
		
		try {
			statement=connection.createStatement();
			rowsAffected= statement.executeUpdate(updateSQL);
			
			if(rowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "Inventory record updated","Inventory Creation",
						JOptionPane.INFORMATION_MESSAGE);
				Logger.info(itemID+" was successfully updated in the database");
			}
			
		} catch (SQLException e) {
			System.err.println("Error updating "+e.getMessage());
			Logger.error(newCategory+" was not updated in the database");
			Logger.trace(e.getMessage());
		}
	}
	
	public void updateID(String itemID, int newID)
	{
		String updateSQL="UPDATE inventory SET newID='"+newName+"'WHERE ItemID = "+itemID;
		
		try {
			statement=connection.createStatement();
			rowsAffected= statement.executeUpdate(updateSQL);
			
			if(rowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "Inventory record updated","Inventory Creation",
						JOptionPane.INFORMATION_MESSAGE);
				Logger.info("Record"+itemID+" was successfully updated in the database");
			}
			
		} catch (SQLException e) {
			System.err.println("Error updating "+e.getMessage());
			Logger.error(newCategory+" was not updated in the database");
			Logger.trace(e.getMessage());
		}
	}
	
	public void delete(String ItemID)
	{
		String deleteSQL="DELETE FROM inventory WHERE ItemID="+ItemID;
		try {
			statement=connection.createStatement();
			rowsAffected= statement.executeUpdate(deleteSQL);
			if(rowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "Inventory record Deleted","Inventory Deletion",
						JOptionPane.INFORMATION_MESSAGE);
				Logger.info("Record with ID number "+id+" was successfully deleted in the database");
			}
					
			
		} catch (SQLException e) {
			System.err.println("Error deleting "+e.getMessage());
			Logger.error("Record with ID "+id+" was not deleted from the database");
			Logger.trace(e.getMessage());
		}
	}
	
}
