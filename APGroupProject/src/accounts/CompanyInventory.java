package accounts;

import java.sql.*;
import java.util.*;
import java.util.Date;

import javax.persistence.*;
import javax.swing.JOptionPane;
import connectionFiles.DBConnectorFactory;
@Entity
@Table(name="company_inventory")

public class CompanyInventory {
	@Id
	@Column(name="id")
	private int ID;
	
	@Column(name="equipment_id")
	private int eID;
	
	@Column(name="date")
	private Date date;
	
	private static Connection connection=null;
	private Statement statement=null;
	private ResultSet rslt=null;
	private int rowsAffected=0;

	@SuppressWarnings("deprecation")
	public CompanyInventory()
	{
		ID=0;
		eID=0;
		date= new Date(221, 10, 18);
		connection= DBConnectorFactory.getDatabaseConnection();
	}

	public int geteID() {
		return eID;
	}

	public void seteID(int eID) {
		this.eID = eID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public void create(int eqID, Date date)
	{
		String insertSql= "INSERT INTO company_inventory(equipment_id, date) VALUES ('"+eqID+"','"+date+"')";
		try {
			statement= connection.createStatement();
			rowsAffected=statement.executeUpdate(insertSql);
			if(rowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "Company Inventory record created","Creation",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException e) {
			System.err.println("Execption: "+e.getMessage());
		}
	}
	
	public void readAll()
	{
		String selectSQL="SELECT * FROM company_inventory WHERE 1=1";
		
		try {
			statement=connection.createStatement();
			rslt= statement.executeQuery(selectSQL);
			while(rslt.next())
			{
				int ID=rslt.getInt("ID");
				int eqID= rslt.getInt("ID");
				Date date= rslt.getDate("date");
				
				System.out.println("ID#: "+ID+"\nEquipment ID: "+eqID+"\nDate: "+date);
			}
			
		} catch (SQLException e) {
			System.err.println("Error selecting all "+e.getMessage());
		}
	}
	
	public void updateEquimentID(int ID, String newEqID)
	{
		String updateSQL="UPDATE company_inventory SET equipment_id='"+newEqID+"'WHERE id = "+ID;
		
		try {
			statement=connection.createStatement();
			rowsAffected= statement.executeUpdate(updateSQL);
			
			if(rowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "Record updated","Record Update",
						JOptionPane.INFORMATION_MESSAGE);
			}
			
		} catch (SQLException e) {
			System.err.println("Error updating "+e.getMessage());
		}
	}
	
	public void updateDate(int ID, Date newDate)
	{
		String updateSQL="UPDATE company_inventory SET date='"+newDate+"'WHERE id = "+ID;
		
		try {
			statement=connection.createStatement();
			rowsAffected= statement.executeUpdate(updateSQL);
			
			if(rowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "Record updated","Record Update",
						JOptionPane.INFORMATION_MESSAGE);
			}
			
		} catch (SQLException e) {
			System.err.println("Error updating "+e.getMessage());
		}
	}
	

	public void delete(int ID)
	{
		String deleteSQL="DELETE FROM company_inventory WHERE id="+ID;
		try {
			statement=connection.createStatement();
			rowsAffected= statement.executeUpdate(deleteSQL);
			if(rowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "Record Deleted","Record Deletion",
						JOptionPane.INFORMATION_MESSAGE);
			}
					
			
		} catch (SQLException e) {
			System.err.println("Error deleting "+e.getMessage());
		}
	}
	

}
