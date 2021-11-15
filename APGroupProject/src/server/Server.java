package server;

import java.io.*;
import java.net.*;
import java.sql.*;
import client.*;
import javax.swing.JOptionPane;

public class Server {
	private ServerSocket serverSocket;
	private Socket connectionSocket;
	private ObjectOutputStream objOs;
	private ObjectInputStream objIs;
	private static Connection dBConn=null;
	private Statement stmt;
	private ResultSet result=null;
	
	public Server()
	{
		this.createConnection();
		this.waitForRequests();
	}
	
	private void createConnection()
	{
		try {
			serverSocket= new ServerSocket(8888);
		}
		catch(IOException i)
		{
			i.printStackTrace();
		}
	
	}
	
	private void configureStreams()
	{
		try {
			objOs= new ObjectOutputStream(connectionSocket.getOutputStream());
			objIs= new ObjectInputStream(connectionSocket.getInputStream());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	private static Connection getDatabaseConnection()
	{
		if(dBConn == null)
			
		{	
			try {
				String url="jdbc:mysql://localhost:3306/groupproject";
				dBConn= DriverManager.getConnection(url,"root","");
				
				JOptionPane.showMessageDialog(null, "Connection with database established","Connection Status",JOptionPane.INFORMATION_MESSAGE); 
		
			} catch (SQLException e) 
			{
				JOptionPane.showMessageDialog(null, "Connection with database could not "
						+ "established","Connection Status",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		return dBConn;
	}
	
	private void closeConnection()
	{
		try {
			objOs.close();
			objIs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void addStudentToFile(Student student)
	{
		String sql= "INSERT INTO dblab1.students(ID, FirstName, LastName, Email) VALUES ('"+ student.getId() +"','"+student.getFirstName()+"','"+student.getLastName()+"','"+student.getEmail()+"')";
		try {
			
			stmt= dBConn.createStatement();
			if (stmt.executeUpdate(sql)==1) {
				objOs.writeObject(true);
			}
			else {
				objOs.writeObject(false);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		catch (SQLException sqe) {
			sqe.printStackTrace();
		}
		
	}
	
	private Student findStudentById(String stuid)
	{
		Student stuObj= new Student();
		String query= "SELECT * FROM students WHERE ID="+stuid;
		try {
			stmt= dBConn.createStatement();
			result= stmt.executeQuery(query);
			if (result.next()) {
				stuObj.setId(result.getString(1));
				stuObj.setFirstName(result.getString(2));
				stuObj.setLastName(result.getString(3));
				stuObj.setEmail(result.getString(4));
				System.out.println("ID: "+stuObj.getId()+"\nFirst Name: "+stuObj.getFirstName()+"\nLast Name: "+stuObj.getLastName()+"\nEmail: "+stuObj.getEmail());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stuObj;
	}
	
	private void waitForRequests()
	{
		String action="";
		getDatabaseConnection();
		Student stuObj= null;
		try {
			while(true)
			{
				System.out.println("[SERVER]: Waiting for client connection");
				connectionSocket= serverSocket.accept();
				this.configureStreams();
				try {
					action= (String) objIs.readObject();
					if(action.equals("Add Student")) {
						stuObj= (Student) objIs.readObject();
						addStudentToFile(stuObj);
						objOs.writeObject(true);
					}
					else if (action.equals("Find Student")) {
						String stuID= (String) objIs.readObject();
						stuObj= findStudentById(stuID);
						objOs.writeObject(stuObj);
					}
				} catch (ClassNotFoundException cle) {
					cle.printStackTrace();
				}
				catch (ClassCastException cst) {
					cst.printStackTrace();
				}
				this.closeConnection();
			}
		} catch (EOFException eof) {
			eof.printStackTrace();
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}