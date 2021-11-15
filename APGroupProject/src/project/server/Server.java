package project.server;

import java.io.*;
import java.net.*;
import java.sql.*;

import project.accounts.Equipment;
import project.client.*;

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
	}//Default constructor
	
	private void createConnection()
	{
		try {
			serverSocket= new ServerSocket(8888);
		}
		catch(IOException i)
		{
			i.printStackTrace();
		}
	
	}//Sets which port the server should listen to for a connection
	
	private void configureStreams()
	{
		try {
			objOs= new ObjectOutputStream(connectionSocket.getOutputStream());
			objIs= new ObjectInputStream(connectionSocket.getInputStream());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}//Initializes the I/O Streams
	
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
	}//Makes a connection to database
	
	private void closeConnection()
	{
		try {
			objOs.close();
			objIs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//Closes server connection
	
	private void addEquipmentToFile(Equipment equipment)
	{
		String sql= "INSERT INTO equipment(name, category_id, availability, cost) VALUES ('"+ equipment.getName() +"','"+equipment.getCategoryID()+"','"+equipment.getAvailability()+"','"+equipment.getCost()+"')";
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
		
	}//Writes to equipment table in database
	
	
	
	private void waitForRequests()
	{
		String action="";
		getDatabaseConnection();
		Equipment stuObj= null;
		try {
			while(true)
			{
				System.out.println("[SERVER]: Waiting for client connection");
				connectionSocket= serverSocket.accept();
				this.configureStreams();
				try {
					action= (String) objIs.readObject();
					if(action.equals("Add Equipment")) {
						stuObj= (Equipment) objIs.readObject();
						addEquipmentToFile(stuObj);
						objOs.writeObject(true);
					}
					else if (action.equals("Retrieve Equipment")) {
						String stuID= (String) objIs.readObject();
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
	}//Allows the server to listen for requests continuously
}