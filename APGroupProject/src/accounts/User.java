package accounts;
import connectionFiles.DBConnectorFactory;
import java.sql.*;

import javax.persistence.*;
import javax.swing.JOptionPane;

@Entity
@Table(name="inventory")
public class User {
	@Column(name="ID")
	private int IDnum;
	
	@Column(name="Password")
	private String password;
	
	private static Connection connection=null;
	private Statement stmt=null;
	private ResultSet result=null;
	private int numRowsAffected=0;
	
	public User()
	{
		IDnum=0;
		password="";
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
	
	public void create(int ID, String Password, String Name, String Acctype)
	{
		String insertSql= "INSERT INTO groupproject.account(ID, Password, Name, AccountType) VALUES ('"+ ID +"','"+Password+"','"+Name+"','"+Acctype+"')";
		try {
			stmt= connection.createStatement();
			numRowsAffected=stmt.executeUpdate(insertSql);
			if(numRowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "Account record created","Account Creation",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException e) {
			System.err.println("Execption: "+e.getMessage());
		}
	}
	
	public void readAll()
	{
		String selectSQL="SELECT * FROM groupproject.account WHERE 1=1";
		
		try {
			stmt=connection.createStatement();
			result= stmt.executeQuery(selectSQL);
			while(result.next())
			{
				String id= result.getString("ID");
				String name=result.getString("Name");
				String accountType=result.getString("AccountType");
				
				System.out.println("ID#: "+id+" Name: "+name+" Account Type:"+accountType);
			}
			
		} catch (SQLException e) {
			System.err.println("Error selecting all "+e.getMessage());
		}
	}
	
	public void update(String id, String newName)
	{
		String updateSQL="UPDATE groupproject.account SET name='"+newName+"'WHERE id = "+id;
		try {
			stmt=connection.createStatement();
			numRowsAffected= stmt.executeUpdate(updateSQL);
			
			if(numRowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "Student record updated","Student Creation",
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
			stmt=connection.createStatement();
			numRowsAffected= stmt.executeUpdate(deleteSQL);
			if(numRowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "Account record Deleted","Account Deletion",
						JOptionPane.INFORMATION_MESSAGE);
			}
					
			
		} catch (SQLException e) {
			System.err.println("Error deleting "+e.getMessage());
		}
	}

}


