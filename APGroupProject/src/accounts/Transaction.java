package accounts;

import java.sql.*;

import javax.persistence.*;
import javax.swing.JOptionPane;
import connectionFiles.DBConnectorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Entity
@Table(name="transaction")

public class Transaction {
	@Id
	@Column(name="ID")
	private int ID;
	
	
	@Column(name="equipment_id")
	private String eID;
	
	@Column(name="date")
	private Date date;
	
	
	@Column(name="cost")
	private int cost;
	
	private static Connection connection=null;
	private Statement statement=null;
	private ResultSet rslt=null;
	private int rowsAffected=0;
	private static final Logger Logger = LogManager.getLogger(Transaction.class);
	
	public Transaction()
	{
		ID=0;
		eID="";
		cost=0;
		connection= DBConnectorFactory.getDatabaseConnection();
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String geteID() {
		return eID;
	}

	public void seteID(String eID) {
		this.eID = eID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public void create(int eqID, Date date, int cost)
	{
		String insertSql= "INSERT INTO transaction(eqID, date,cost) VALUES ('"+eqID+"','"+date+"','"+cost+"')";
		try {
			statement= connection.createStatement();
			rowsAffected=statement.executeUpdate(insertSql);
			if(rowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "Transaction record created","Transaction Creation",
						JOptionPane.INFORMATION_MESSAGE);
				Logger.info("Record with ID "+eqID+" was successfully created in the database");
			}
		} catch (SQLException e) {
			System.err.println("Execption: "+e.getMessage());
			Logger.error("Record with "+eqID+" was not added to the database");
			Logger.trace(e.getMessage());
		}
	}
	
	public void readAll()
	{
		String selectSQL="SELECT * FROM transaction WHERE 1=1";
		
		try {
			statement=connection.createStatement();
			rslt= statement.executeQuery(selectSQL);
			while(rslt.next())
			{
				int ID=rslt.getInt("ID");
				int eqID= rslt.getInt("ID");
				Date date= rslt.getDate("date");
				int cost=rslt.getInt("cost");
				
				System.out.println("ID#: "+ID+"\nEquipment ID: "+eqID+"\nDate: "+date+"\nCost: "+cost);
			}
			
		} catch (SQLException e) {
			System.err.println("Error selecting all "+e.getMessage());
		}
	}
	
	public void updateEquimentID(int ID, String newEqID)
	{
		String updateSQL="UPDATE transaction SET equipment_id='"+newEqID+"'WHERE id = "+ID;
		
		try {
			statement=connection.createStatement();
			rowsAffected= statement.executeUpdate(updateSQL);
			
			if(rowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "Transaction record updated","Transaction Update",
						JOptionPane.INFORMATION_MESSAGE);
				Logger.info(newEqID+" was successfully updated in the database");
			}
			
		} catch (SQLException e) {
			System.err.println("Error updating "+e.getMessage());
			Logger.error(newEqID+" was not updated in the database");
			Logger.trace(e.getMessage());
		}
	}
	
	public void updateDate(int ID, Date newDate)
	{
		String updateSQL="UPDATE transaction SET date='"+newDate+"'WHERE id = "+ID;
		
		try {
			statement=connection.createStatement();
			rowsAffected= statement.executeUpdate(updateSQL);
			
			if(rowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "Transaction record updated","Transaction Update",
						JOptionPane.INFORMATION_MESSAGE);
				Logger.info(newDate+" was successfully updated in the database");
			}
			
		} catch (SQLException e) {
			System.err.println("Error updating "+e.getMessage());
			Logger.error(newDate+" was not updated in the database");
			Logger.trace(e.getMessage());
		}
	}
	
	public void updateCost(int ID, int newCost)
	{
		String updateSQL="UPDATE transaction SET cost='"+newCost+"'WHERE id = "+ID;
		
		try {
			statement=connection.createStatement();
			rowsAffected= statement.executeUpdate(updateSQL);
			
			if(rowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "Transaction record updated","Transaction Update",
						JOptionPane.INFORMATION_MESSAGE);
				Logger.info(newCost+" was successfully updated in the database");
			}
			
		} catch (SQLException e) {
			System.err.println("Error updating "+e.getMessage());
			Logger.error(newCost+" was not updated in the database");
			Logger.trace(e.getMessage());
		}
	}
	
	public void delete(int ID)
	{
		String deleteSQL="DELETE FROM transaction WHERE id="+ID;
		try {
			statement=connection.createStatement();
			rowsAffected= statement.executeUpdate(deleteSQL);
			if(rowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "Transaction record Deleted","Transaction Deletion",
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
