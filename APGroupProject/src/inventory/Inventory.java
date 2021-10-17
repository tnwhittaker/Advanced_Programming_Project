package inventory;

import java.sql.*;

import javax.persistence.*;
import javax.swing.JOptionPane;

import connectionFiles.DBConnectorFactory;
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
	
	private static Connection connection=null;
	private Statement statement=null;
	private ResultSet rslt=null;
	private int rowsAffected=0;

		
	public Inventory()
	{
		itemID="";
		itemName="";
		category="";
		status="";
		dateOfRequirement= "";
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
	
	public void create(String itemID, String itemName, String Category, String Status, String DOR)
	{
		String insertSql= "INSERT INTO groupproject.inventory(itemID, ItemName, Category, Status,DateofRequirement) VALUES ('"+ itemID +"','"+itemName+"','"+Category+"','"+Status+"','"+DOR+"')";
		try {
			statement= connection.createStatement();
			rowsAffected=statement.executeUpdate(insertSql);
			if(rowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "Inventory record created","Inventory Creation",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException e) {
			System.err.println("Execption: "+e.getMessage());
		}
	}
	
	public void readAll()
	{
		String selectSQL="SELECT * FROM groupproject.inventory WHERE 1=1";
		
		try {
			statement=connection.createStatement();
			rslt= statement.executeQuery(selectSQL);
			while(rslt.next())
			{
				String id= rslt.getString("id");
				String name= rslt.getString("name");
				
				System.out.println("ID#: "+id+"\tName: "+name);
			}
			
		} catch (SQLException e) {
			System.err.println("Error selecting all "+e.getMessage());
		}
	}
	
	public void update(String id, String newName)
	{
		String updateSQL="UPDATE groupproject.inventory SET ItemName='"+newName+"'WHERE ItemID = "+id;
		/*
		 * String updateSQL1="UPDATE groupproject.inventory SET Category='"
		 * +newName+"'WHERE ItemID = "+id; String
		 * updateSQL2="UPDATE groupproject.inventory SET Status='"
		 * +newName+"'WHERE ItemID = "+id; String
		 * updateSQL3="UPDATE groupproject.inventory SET DateofRequirement='"
		 * +newName+"'WHERE ItemID = "+id;
		 */
		
		try {
			statement=connection.createStatement();
			rowsAffected= statement.executeUpdate(updateSQL);
			
			if(rowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "Inventory record updated","Inventory Creation",
						JOptionPane.INFORMATION_MESSAGE);
			}
			
		} catch (SQLException e) {
			System.err.println("Error updating "+e.getMessage());
		}
	}
	
	public void delete(int id)
	{
		String deleteSQL="DELETE FROM groupproject.account WHERE ID="+id;
		try {
			statement=connection.createStatement();
			rowsAffected= statement.executeUpdate(deleteSQL);
			if(rowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "Inventory record Deleted","Inventory Deletion",
						JOptionPane.INFORMATION_MESSAGE);
			}
					
			
		} catch (SQLException e) {
			System.err.println("Error deleting "+e.getMessage());
		}
	}
	
}
