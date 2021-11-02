package accounts;
import connectionFiles.DBConnectorFactory;

import java.sql.*;

import javax.persistence.*;
import javax.swing.JOptionPane;

import org.springframework.security.crypto.bcrypt.BCrypt;
import java.util.UUID;  

@Entity
//@Table(name="inventory")
@Table(name="users")
public class User {
	@Column(name="ID")
	private int IDnum;
	
	@Column(name="Password")
	private String password;
	
	@Column(name="Email")
	private String email;
	
	
	private static Connection connection=null;
	private Statement stmt=null;
	private ResultSet result=null;
	private int numRowsAffected=0;
	
	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		User user = new User();
		
		Employee e2 = new Employee("test@test.com","123hello");//call instance to contact class
		Customer c1 = new Customer("sumn@sumnelse.com","qwertyuiop99");
		
		Employee e4 = new Employee("zzzkillashangzzz@gmail.com","barofsamples");
		
		
//		user.createEmployee(e2);
		
//		user.employeeLogin("58949b7d-c9e1-4b58-b214-649de4187174", "123hello");
//		user.employeeLogin(e4, "samplebar!");
		user.readAllUsers();
//		c1.readAll();//returns all records from database
//		String test = e1.getHashedPassword();
//		String testing = c1.getPassword();
//		System.out.print(e2.getHashedPassword());
//		System.out.print(e4.getStaffId());
//		
//		System.out.print(e2.getPassword());
//		System.out.print(e2.getHashedPassword());
//		System.out.print(BCrypt. checkpw("123hello", e2.getPassword()));
		
	}
	
	public User(String email, String password)
	{
		this.email = email;
//		this.password = password;
		this.password = BCrypt.hashpw(password, BCrypt.gensalt(10));//generate hashed and salted password...BCrypt.checkpw(password, hashedPassword)
		connection= DBConnectorFactory.getDatabaseConnection();
	}
	
	public User() {
		connection= DBConnectorFactory.getDatabaseConnection();
	}

	public int getIDnum() {
		return IDnum;
	}

	public void setIDnum(int iDnum) {
		IDnum = iDnum;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getHashedPassword() {
		String hash = BCrypt.hashpw(password, BCrypt.gensalt(10 ));
		return hash;
	}
	
	public void createCustomer(Customer customer)
	{
		customer.setCustomerId(UUID.randomUUID().toString());
		String insertSql= "INSERT INTO users(email, password,customer_id,type) VALUES ('"+ customer.getEmail() +"','"+customer.getHashedPassword()+"','"+customer.getCustomerId()+"','"+customer.getType()+"')";
		try {
			stmt= connection.createStatement();
			numRowsAffected=stmt.executeUpdate(insertSql);
			if(numRowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "User record created","User Creation",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException e) {
			System.err.println("Execption: "+e.getMessage());
		}
	}
	
	public void createEmployee(Employee employee) 
	{
//		employee.setStaffId(UUID.randomUUID().toString());
		String checkSql = "SELECT count(*) FROM users WHERE staff_id = '" + employee.getStaffId() +"'";
		
//		String insertSql= "INSERT INTO users(email, password,staff_id,type) VALUES ('"+ employee.getEmail() +"','"+ employee.getHashedPassword()+"','"+ employee.getStaffId()+"','"+ employee.getType()+"')";
		try {
			result= stmt.executeQuery(checkSql);
			while(result.next()) {
				
//				EXISTS (SELECT 1 FROM users WHERE users.staff_id = 'hello')
				
				if(result.getInt("count(*)") == 0) {
					employee.setStaffId(UUID.randomUUID().toString());
					String insertSql= "INSERT INTO users(email, password,staff_id,type) VALUES ('"+ employee.getEmail() +"','"+ employee.getHashedPassword()+"','"+ employee.getStaffId()+"','"+ employee.getType()+"')";
					
					stmt= connection.createStatement();
					numRowsAffected=stmt.executeUpdate(insertSql);
					if(numRowsAffected==1)
					{
						JOptionPane.showMessageDialog(null, "Employee record created","User Creation",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}else {
					System.out.print(result.getInt("count(*)"));
					System.out.println(employee.getStaffId());
				}
			}
			
		} catch (SQLException e) {
			System.err.println("Execption: "+e.getMessage());
		}
	}
	
	public void readAllUsers()
	{
		String selectSQL="SELECT * FROM users";
		
		try {
			stmt=connection.createStatement();
			result= stmt.executeQuery(selectSQL);
			while(result.next())
			{
				String id = result.getString("id");
				String email= result.getString("email");
				String fname=result.getString("first_name");
				String lname=result.getString("last_name");
				String staffId=result.getString("staff_id");
				String customerId=result.getString("customer_id");
				String accountType = result.getInt("type") == 1   ? "Employee": "Customer"; 
				
				System.out.println("ID#: "+id+" Name: "+(fname + " " + lname)+" Account Type:"+accountType);
			}
			
		} catch (SQLException e) {
			System.err.println("Error selecting all "+e.getMessage());
		}
	}
	
	public void update(String id, String newName)
	{
		String updateSQL="UPDATE users  SET name='"+newName+"'WHERE id = "+id;
		try {
			stmt=connection.createStatement();
			numRowsAffected= stmt.executeUpdate(updateSQL);
			
			if(numRowsAffected==1)
			{
				JOptionPane.showMessageDialog(null, "User record updated","User Creation",
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
				JOptionPane.showMessageDialog(null, "User record Deleted","User Deletion",
						JOptionPane.INFORMATION_MESSAGE);
			}
					
			
		} catch (SQLException e) {
			System.err.println("Error deleting "+e.getMessage());
		}
	}
	
//	public void employeeLogin(String staffId, String password) {
	public void employeeLogin(Employee employee, String password) {
		String selectSQL="SELECT password FROM users where staff_id = '" + employee.getStaffId() + "'";
		try {
			stmt=connection.createStatement();
			result= stmt.executeQuery(selectSQL);
			if(result.next())
			{
				if(BCrypt.checkpw(password, employee.getPassword())) {
					JOptionPane.showMessageDialog(null, "User Fetched","User Login Successful",
						JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Incorrect Credentials","User Login Failed",
							JOptionPane.INFORMATION_MESSAGE);
				}
				
			}else {
				System.out.print("Didnt find user: "+ employee.getEmail());
			}
					
			
		} catch (SQLException e) {
			System.err.println("Error deleting "+e.getMessage());
		}
	}

}


