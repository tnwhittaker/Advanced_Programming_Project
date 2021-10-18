package accounts;

import java.sql.*;

import javax.persistence.*;
import javax.swing.JOptionPane;

import connectionFiles.DBConnectorFactory;

@Entity
@Table(name="rental_status")
public class RentalStatus {
	@Id
	@Column(name="ID")
	private int ID;
	
	
	@Column(name="status")
	private String status;
	
	private static Connection connection=null;
	private Statement statement=null;
	private ResultSet rslt=null;
	private int rowsAffected=0;
	
	public RentalStatus()
	{
		ID=0;
		status="";
		connection= DBConnectorFactory.getDatabaseConnection();
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public void create(String status)
	{
		String insertSql= "INSERT INTO rental_status(status) VALUES ('"+status+"')";
		try {
			statement= connection.createStatement();
			rowsAffected=statement.executeUpdate(insertSql);
			if(rowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "Status record created","Status Creation",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException e) {
			System.err.println("Execption: "+e.getMessage());
		}
	}
	
	public void readAll()
	{
		String selectSQL="SELECT * FROM rental_status WHERE 1=1";
		
		try {
			statement=connection.createStatement();
			rslt= statement.executeQuery(selectSQL);
			while(rslt.next())
			{
				int ID= rslt.getInt("ID");
				String status= rslt.getString("status");
				
				System.out.println("ID#: "+ID+"\nStatus: "+status);
			}
			
		} catch (SQLException e) {
			System.err.println("Error selecting all "+e.getMessage());
		}
	}
	
	public void updateStatus(int ID, String newStatus)
	{
		String updateSQL="UPDATE rental_status SET status='"+newStatus+"'WHERE id = "+ID;
		
		try {
			statement=connection.createStatement();
			rowsAffected= statement.executeUpdate(updateSQL);
			
			if(rowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "Status record updated","Status Update",
						JOptionPane.INFORMATION_MESSAGE);
			}
			
		} catch (SQLException e) {
			System.err.println("Error updating "+e.getMessage());
		}
	}
	
	public void delete(int ID)
	{
		String deleteSQL="DELETE FROM status WHERE id="+ID;
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
