package accounts;

import java.sql.*;

import javax.persistence.*;
import javax.swing.JOptionPane;

import connectionFiles.DBConnectorFactory;

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
	
	

}
