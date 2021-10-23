package accounts;
import connectionFiles.DBConnectorFactory;
import java.sql.*;

import javax.persistence.*;
import javax.swing.JOptionPane;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int IDnum;
	
	@Column(name="password")
	private String password;
	
	@Column(name="first_name")
	private String fname;
	
	@Column(name="last_name")
	private String lname;
	
	@Column(name="email")
	private String email;

	@Column(name="customer_id")
	private String customerID;
	
	@Column(name="staff_id")
	private String staffID;

	@Column(name="type")
	private short type;

	private static Connection connection=null;
	private Statement stmt=null;
	private ResultSet result=null;
	private int numRowsAffected=0;
	
	public User()
	{
		IDnum=0;
		password="";
		fname="";
		lname="";
		email="";
		customerID="";
		staffID="";
		type=0;
		connection= DBConnectorFactory.getDatabaseConnection();
	}

	public int getIDnum() {
		return IDnum;
	}

	public void setIDnum(int iDnum) {
		IDnum = iDnum;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getStaffID() {
		return staffID;
	}

	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	public void createCustomer(String custID, String email, String Fname, String Lname, String Password)
	{
		int type=1;
String insertSql= "INSERT INTO users(first_name, last_name, email, customer_id, password,type) VALUES ('"+ Fname +"','"+Lname+"','"+email+"','"+custID+"','"+Password+"','"+type+"')";   
		try {
			stmt= connection.createStatement();
			numRowsAffected=stmt.executeUpdate(insertSql);
			if(numRowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "Customer record created","Customer Creation",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException e) {
			System.err.println("Execption: "+e.getMessage());
		}
	}
	
	public void createEmployee(String staffID, String email, String Fname, String Lname, String Password,short type)
	{
String insertSql= "INSERT INTO users(first_name, last_name, email, staff_id, password,type) VALUES ('"+ Fname +"','"+Lname+"','"+email+"','"+staffID+"','"+Password+"','"+type+"')";   
		try {
			stmt= connection.createStatement();
			numRowsAffected=stmt.executeUpdate(insertSql);
			if(numRowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "Employee record created","Employee Creation",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException e) {
			System.err.println("Execption: "+e.getMessage());
		}
	}
	
	public void readAll()
	{
		String selectSQL="SELECT * FROM users WHERE 1=1";
		
		try {
			stmt=connection.createStatement();
			result= stmt.executeQuery(selectSQL);
			while(result.next())
			{
				String id= result.getString("id");
				String fname=result.getString("first_name");
				String lname=result.getString("last_name");
				String email=result.getString("email");
				String password=result.getString("password");
				String custID=result.getString("customer_id");
				String staffID=result.getString("staff_id");
				short type= result.getShort("type");
				System.out.println("ID#: "+id+"\nFirst Name: "+fname+"\nLast Name:"+lname+"\nEmail: "+email+"\n: Password"+password+"\nCustomer ID: "+custID+"\nStaff ID"+staffID+"\nType: "+type);    
			}
			
		} catch (SQLException e) {
			System.err.println("Error selecting all "+e.getMessage());
		}
	}
	
	public void authenticateCustomer(String id, String pass)
	{
		String validateSQL="SELECT * FROM users WHERE customer_id='"+id+"'and password='"+pass+"'";
		
		try {
			stmt=connection.createStatement();
			result= stmt.executeQuery(validateSQL);
			if(result.next())
			{
				JOptionPane.showMessageDialog(null, id+ "has logged in sucessfully");
			
			}
			else
			{
				JOptionPane.showMessageDialog(null, id+ " entered something wrong");
			}
			
		} catch (SQLException e) {
			System.err.println("Error selecting all "+e.getMessage());
		}
	}
	
	public void updateFNAME(String id, String newFName)
	{
		String updateSQL="UPDATE users SET fname='"+newFName+"'WHERE id = "+id;
		try {
			stmt=connection.createStatement();
			numRowsAffected= stmt.executeUpdate(updateSQL);
			
			if(numRowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "First name has been updated","Record Update",
						JOptionPane.INFORMATION_MESSAGE);
			}
			
		} catch (SQLException e) {
			System.err.println("Error updating "+e.getMessage());
		}
	}
	
	public void updateLNAME(String id, String newLName)
	{
		String updateSQL="UPDATE users SET lname='"+newLName+"'WHERE id = "+id;
		try {
			stmt=connection.createStatement();
			numRowsAffected= stmt.executeUpdate(updateSQL);
			
			if(numRowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "Last name has been updated","Record Update",
						JOptionPane.INFORMATION_MESSAGE);
			}
			
		} catch (SQLException e) {
			System.err.println("Error updating "+e.getMessage());
		}
	}
	
	public void updateEmail(String id, String newEmail)
	{
		String updateSQL="UPDATE users SET email='"+newEmail+"'WHERE id = "+id;
		try {
			stmt=connection.createStatement();
			numRowsAffected= stmt.executeUpdate(updateSQL);
			
			if(numRowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "Email has been updated","Record Update",
						JOptionPane.INFORMATION_MESSAGE);
			}
			
		} catch (SQLException e) {
			System.err.println("Error updating "+e.getMessage());
		}
	}
	
	public void updatePassword(String id, String newPassword)
	{
		String updateSQL="UPDATE users SET password='"+newPassword+"'WHERE id = "+id;
		try {
			stmt=connection.createStatement();
			numRowsAffected= stmt.executeUpdate(updateSQL);
			
			if(numRowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "Password has been updated","Record Update",
						JOptionPane.INFORMATION_MESSAGE);
			}
			
		} catch (SQLException e) {
			System.err.println("Error updating "+e.getMessage());
		}
	}
	
	public void updateCUSTID(String id, String newCUSTID)
	{
		String updateSQL="UPDATE users SET customer_id='"+newCUSTID+"'WHERE id = "+id;
		try {
			stmt=connection.createStatement();
			numRowsAffected= stmt.executeUpdate(updateSQL);
			
			if(numRowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "Customer ID has been updated","Record Update",
						JOptionPane.INFORMATION_MESSAGE);
			}
			
		} catch (SQLException e) {
			System.err.println("Error updating "+e.getMessage());
		}
	}
	
	public void updateSTAFFID(String id, String newSTAFFID)
	{
		String updateSQL="UPDATE users SET staff_id='"+newSTAFFID+"'WHERE id = "+id;
		try {
			stmt=connection.createStatement();
			numRowsAffected= stmt.executeUpdate(updateSQL);
			
			if(numRowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "Employee ID has been updated","Record Update",
						JOptionPane.INFORMATION_MESSAGE);
			}
			
		} catch (SQLException e) {
			System.err.println("Error updating "+e.getMessage());
		}
	}
	
	public void updateTYPE(String id, short newTYPE)
	{
		String updateSQL="UPDATE users SET type='"+newTYPE+"'WHERE id = "+id;
		try {
			stmt=connection.createStatement();
			numRowsAffected= stmt.executeUpdate(updateSQL);
			
			if(numRowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "User type has been updated","Record Update",
						JOptionPane.INFORMATION_MESSAGE);
			}
			
		} catch (SQLException e) {
			System.err.println("Error updating "+e.getMessage());
		}
	}
	
	public void delete(int id)
	{
		String deleteSQL="DELETE FROM users WHERE ID="+id;
		try {
			stmt=connection.createStatement();
			numRowsAffected= stmt.executeUpdate(deleteSQL);
			if(numRowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "User record Deleted","Record Deletion",
						JOptionPane.INFORMATION_MESSAGE);
			}
					
			
		} catch (SQLException e) {
			System.err.println("Error deleting "+e.getMessage());
		}
	}

}


