package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Client {
	private Socket connectionSocket;
	private ObjectOutputStream objOs;
	private ObjectInputStream objIs;
	String action;
	public static void main(String[] args) {
		new Client();
	}
	
	
	public Client() {
		this.createConnection();
		this.configureStreams();
	}
	
	public void createConnection() {
		try {
			connectionSocket= new Socket("127.0.0.1", 8888);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	private void configureStreams()
	{
		try {
			objIs= new ObjectInputStream(connectionSocket.getInputStream());
			objOs= new ObjectOutputStream(connectionSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection()
	{
		try {
			objOs.close();
			objIs.close();
			connectionSocket.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void sendAction(String action){
		this.action=action;
		try {
			objOs.writeObject(action);
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}
	
	
	public void sendStudent(Student stuObj){
		try {
			objOs.writeObject(stuObj);
		} catch (IOException ie) {
			ie.printStackTrace();
		}
		
	}
	
	public void sendStudentID(String stuID){
		try {
			objOs.writeObject(stuID);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void receiveResponse() {
		try {
			if(action.equalsIgnoreCase("Add Student")) {
				Boolean flag= (Boolean) objIs.readObject();
				if(flag==true) {
					JOptionPane.showMessageDialog(null, "Record added successfully","Add record status",JOptionPane.INFORMATION_MESSAGE);
				}
			}
			if(action.equalsIgnoreCase("Find Student")) {
				Student student= new Student();
				student= (Student) objIs.readObject();
				if(student==null){
					JOptionPane.showMessageDialog(null, "Record could not be found","Record status error",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} catch (ClassCastException ce) {
			ce.printStackTrace();
		}
		catch(ClassNotFoundException cn) {
			cn.printStackTrace();
		}
		catch(IOException io) {
			io.printStackTrace();
		}
	}
}
