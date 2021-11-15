package project.gui;

import java.awt.EventQueue;
import java.util.Random;   
import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;

import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;

import project.accounts.*;
import project.connectionFiles.DBConnectorFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.awt.Label;
import java.awt.CardLayout;

import javax.swing.border.LineBorder;

public class Application {

	private JFrame frmGrizzlysEntertainment;
	private JTextField FNametxt;
	private JTextField LNametxt;
	private JTextField Emailtxt;
	private JPasswordField PWordtxt;
	private JTextField custidtxt;
	private JTextField passtxt;
	private JTextField custIDtxt;
	private DefaultTableModel model;
	private static final Logger Logger = LogManager.getLogger(Application.class);
	private JTable table;
	private static Connection connection=null;
	private Statement stmt=null;
	private ResultSet result=null;
	private JTable transactionTable;
	private JTable viewTable;
	RentDate rent= new RentDate();
	JComboBox transBox = new JComboBox();

	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Application window = new Application();
					window.frmGrizzlysEntertainment.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Application() {
		initialize();
	}

	private void initialize() {
		connection= DBConnectorFactory.getDatabaseConnection();
		frmGrizzlysEntertainment = new JFrame();
		frmGrizzlysEntertainment.setResizable(false);
		frmGrizzlysEntertainment.setTitle("Grizzly's Entertainment- Customer");
		frmGrizzlysEntertainment.setBounds(100, 100, 871, 498);
		frmGrizzlysEntertainment.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGrizzlysEntertainment.getContentPane().setLayout(new CardLayout(0, 0));
		frmGrizzlysEntertainment.setVisible(true);
		JPanel Welcome_1 = new JPanel();
		frmGrizzlysEntertainment.getContentPane().add(Welcome_1, "name_331322988084100");
		Welcome_1.setLayout(null);
		
		JPanel SignUp = new JPanel();
		frmGrizzlysEntertainment.getContentPane().add(SignUp, "name_332539168727000");
		SignUp.setLayout(null);
		
		JPanel Login = new JPanel();
		frmGrizzlysEntertainment.getContentPane().add(Login, "name_353004344875400");
		Login.setLayout(null);
		
		JPanel Dashboard = new JPanel();
		frmGrizzlysEntertainment.getContentPane().add(Dashboard, "name_74116013573800");
		Dashboard.setLayout(null);
		
		JLabel Welcome = new JLabel("Welcome new user!");
		Welcome.setFont(new Font("Sylfaen", Font.BOLD, 24));
		Welcome.setBounds(15, 27, 220, 20);
		SignUp.add(Welcome);
		
		JLabel Subwelcome = new JLabel("Please enter your info below to create an account");
		Subwelcome.setFont(new Font("Sylfaen", Font.BOLD, 24));
		Subwelcome.setBounds(15, 78, 548, 20);
		SignUp.add(Subwelcome);
		
		JLabel Fname = new JLabel("First Name:");
		Fname.setFont(new Font("Tahoma", Font.BOLD, 16));
		Fname.setBounds(15, 145, 104, 20);
		SignUp.add(Fname);
		
		JLabel Lname = new JLabel("Last Name:");
		Lname.setFont(new Font("Tahoma", Font.BOLD, 16));
		Lname.setBounds(15, 181, 104, 20);
		SignUp.add(Lname);
		
		JLabel Email = new JLabel("Email:");
		Email.setFont(new Font("Tahoma", Font.BOLD, 16));
		Email.setBounds(15, 217, 104, 20);
		SignUp.add(Email);
		
		JLabel PWord = new JLabel("Password:");
		PWord.setFont(new Font("Tahoma", Font.BOLD, 16));
		PWord.setBounds(15, 253, 104, 20);
		SignUp.add(PWord);
		
		JButton Create = new JButton("Create Account");
		Create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Random custnum= new Random();
					int ending= custnum.nextInt(99);
					String custID= FNametxt.getText(0, 3);
					custID=custID.concat(custID.valueOf(ending));
					Customer c= new Customer();
					c.createCustomer(custID, Emailtxt.getText(), FNametxt.getText(), LNametxt.getText(), PWordtxt.getText());
					custIDtxt.setText(custID);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
					Logger.error("Account could not be created");
				} 
				
			}
		});
		Create.setBounds(303, 290, 156, 29);
		SignUp.add(Create);
		
		FNametxt = new JTextField();
		FNametxt.setBounds(119, 142, 194, 26);
		SignUp.add(FNametxt);
		FNametxt.setColumns(10);
		
		LNametxt = new JTextField();
		LNametxt.setColumns(10);
		LNametxt.setBounds(119, 178, 194, 26);
		SignUp.add(LNametxt);
		
		Emailtxt = new JTextField();
		Emailtxt.setColumns(10);
		Emailtxt.setBounds(119, 214, 194, 26);
		SignUp.add(Emailtxt);
		
		PWordtxt = new JPasswordField();
		PWordtxt.setBounds(119, 250, 194, 26);
		SignUp.add(PWordtxt);
		
		JLabel custIDlbl = new JLabel("Customer ID:");
		custIDlbl.setBounds(392, 145, 110, 20);
		SignUp.add(custIDlbl);
		
		custIDtxt = new JTextField();
		custIDtxt.setEditable(false);
		custIDtxt.setBounds(503, 142, 146, 26);
		SignUp.add(custIDtxt);
		custIDtxt.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SignUp.setVisible(false);
				Welcome_1.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(303, 333, 156, 29);
		SignUp.add(btnNewButton_1);

		
		JLabel lblNewLabel = new JLabel("Welcome to Grizzly's Entertainment");
		lblNewLabel.setFont(new Font("Sylfaen", Font.BOLD, 24));
		lblNewLabel.setBounds(207, 35, 429, 46);
		Welcome_1.add(lblNewLabel);
		
		JLabel lblPleaseSelectAn = new JLabel("Please select an option below");
		lblPleaseSelectAn.setFont(new Font("Sylfaen", Font.BOLD, 24));
		lblPleaseSelectAn.setBounds(260, 108, 327, 46);
		Welcome_1.add(lblPleaseSelectAn);
		
		JButton btnNewButton = new JButton("Sign Up");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Welcome_1.setVisible(false);
				SignUp.setVisible(true);
			}
		});
		btnNewButton.setBounds(280, 235, 115, 29);
		Welcome_1.add(btnNewButton);
		
		JButton btnLoginIn = new JButton("Login");
		btnLoginIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Welcome_1.setVisible(false);
				Login.setVisible(true);
			}
		});//Listener for Login button. Shows the login screen when clicked
		btnLoginIn.setBounds(451, 235, 115, 29);
		Welcome_1.add(btnLoginIn);
		
		
		
		JLabel Greeting = new JLabel("Welcome, enter your info below");
		Greeting.setFont(new Font("Sylfaen", Font.BOLD, 24));
		Greeting.setBounds(199, 52, 360, 33);
		Login.add(Greeting);
		
		JLabel Custlbl = new JLabel("Customer ID:");
		Custlbl.setBounds(248, 125, 108, 20);
		Login.add(Custlbl);
		
		JLabel Passlbl = new JLabel("Password:");
		Passlbl.setBounds(248, 176, 108, 20);
		Login.add(Passlbl);
		
		custidtxt = new JTextField();
		custidtxt.setBounds(384, 122, 146, 26);
		Login.add(custidtxt);
		custidtxt.setColumns(10);
		
		passtxt = new JPasswordField();
		passtxt.setBounds(384, 173, 146, 26);
		Login.add(passtxt);
		passtxt.setColumns(10);
		
		JButton submit = new JButton("Login");//Login Button to access customer dashboard
		
		//Login action that is executed when pressed to authenticate user
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User c= new User();
				if(c.authenticateCustomer(custidtxt.getText(), passtxt.getText().toString())) {
					Logger.info("Login Successful. Correct credentials were entered");
					Dashboard.setVisible(true);
					Login.setVisible(false);
				}else {
					Dashboard.setVisible(false);
					Login.setVisible(true );
					Logger.info("Login Failed due to incorrect credentials");
				}
				
			}
		});
		
		submit.setBounds(384, 273, 115, 29);//position submit/login button on frame
		Login.add(submit);//add login button to frame 
		
		//Back button to allow Easy and convenient user experience moving to and from pages
		JButton backButton = new JButton("Back");
		backButton.setBounds(241, 273, 115, 29);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Welcome_1.setVisible(true);
				Login.setVisible(false);
			}
		});
		
		//add back button to login frame
		Login.add(backButton);
		
		
		
		JLabel Description = new JLabel("What would you like to do?");
		Description.setBounds(303, 16, 194, 20);
		Dashboard.add(Description);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(235, 52, 612, 398);
		Dashboard.add(layeredPane);
		layeredPane.setLayout(null);
		
		JInternalFrame Request = new JInternalFrame("Request Equipment");
		Request.setBounds(0, 0, 612, 398);
		layeredPane.setLayer(Request, 3);
		
		layeredPane.add(Request);
		Request.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Welcome, choose a device below to rent");
		lblNewLabel_1.setBounds(220, 11, 200, 14);
		Request.getContentPane().add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(165, 81, 402, 206);
		Request.getContentPane().add(scrollPane);
		
		
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(true);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID","Name", "Availability"
			}
		));//Adds scrollability to the table, should the values be larger than the window
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		
		
		
		JLabel choice = new JLabel("Select Category");
		choice.setBounds(47, 81, 100, 14);
		Request.getContentPane().add(choice);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Staging", "Lighting", "Power", "Sound"}));
		comboBox.setBounds(47, 105, 83, 22);
		Request.getContentPane().add(comboBox);
		
		JButton search = new JButton("Search");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {  
					table.setModel(new DefaultTableModel(null,new String[] {"ID","Name","Availability"}));
					connection= DBConnectorFactory.getDatabaseConnection();
					stmt= connection.createStatement();
					String quote="'";
					String cat= quote+(String)comboBox.getSelectedItem()+quote;
					String catID= "SELECT id FROM category WHERE name="+cat;
					ResultSet catIdResult = stmt.executeQuery(catID);
					catIdResult.next();
					String searchSQL= "SELECT * FROM equipment WHERE category_id="+catIdResult.getInt("id")+" AND availability=1";
					
					result= stmt.executeQuery(searchSQL);
					while(result.next()) {
						String ID= result.getString("id");
						String Name= result.getString("name"); 
						if(result.getInt("availability") == 1) {
							String Availability = "Available";
							String jtbledata[]= {ID, Name,Availability};
							DefaultTableModel tblModel= (DefaultTableModel)table.getModel();
							tblModel.addRow(jtbledata);
						}else {
							String Availability = "Unavailable";
							String jtbledata[]= {ID, Name,Availability};
							DefaultTableModel tblModel= (DefaultTableModel)table.getModel();
							tblModel.addRow(jtbledata);
						}	
						Logger.info("Equipment items were successfully loaded from database");
					}//Searches and loads all available equipment from the database into the table

					
				} catch (SQLException e) {
					Logger.error("Items could not be retrieved from database");
					Logger.trace("Check query pertaining to equipment table");
					e.printStackTrace();
				}
				
				
			}
		});//Listener for the search button. Gets a list of all available equipment in the database, based on category when search button is pressed
		search.setBounds(47, 162, 83, 23);
		Request.getContentPane().add(search);
		
		JButton selectButton = new JButton("Rent");
		
			selectButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) throws ArrayIndexOutOfBoundsException {
					// if()
					System.out.println(table.getValueAt(table.getSelectedRow() , 0));
					String id = (String) table.getValueAt(table.getSelectedRow() , 0);
					String name = (String) table.getValueAt(table.getSelectedRow() , 1);
					String status = (String) table.getValueAt(table.getSelectedRow() , 2);
					rent.setId(id);
					rent.setName(name);
					rent.setStatus(status);
					rent.setCurrentUser(custidtxt.getText());
					rent.setVisible(true);
					
					
				}
			});
		
		
		selectButton.setBounds(478, 310, 89, 23);
		Request.getContentPane().add(selectButton);
		
		
		
		JInternalFrame ViewAll = new JInternalFrame("View All Transactions");
		ViewAll.setBounds(0, 0, 612, 398);
		layeredPane.setLayer(ViewAll, 2);
		ViewAll.setVisible(false);
		layeredPane.add(ViewAll);
		
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(165, 81, 402, 206);
		ViewAll.getContentPane().add(scrollPane1);
		
		transactionTable = new JTable();
		
		transactionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		transactionTable.setColumnSelectionAllowed(true);
		
		scrollPane1.setViewportView(transactionTable);
		
		
		transactionTable.setModel(new DefaultTableModel(
			new Object[][] {
			
			},
			new String[] {
				"Equipment Name", "Category", "Date", "Cost"
			}
		));
		transactionTable.getColumnModel().getColumn(0).setResizable(false);
		transactionTable.getColumnModel().getColumn(1).setResizable(false);
		transactionTable.getColumnModel().getColumn(2).setResizable(false);
		transactionTable.getColumnModel().getColumn(3).setResizable(false);

		ViewAll.getContentPane().add(scrollPane1);
		
		JInternalFrame View = new JInternalFrame("View a Transaction");
		View.setBounds(0, 0, 612, 398);
		layeredPane.setLayer(View, 1);
		View.setVisible(false);
		layeredPane.add(View);
		
		JButton rentButton = new JButton("Rent Equipment");
		rentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Request.setMaximum(true);
					Request.setLayer(3);
					ViewAll.setLayer(2);
					View.setLayer(1);
					ViewAll.moveToBack();
					View.moveToBack();
				} catch (PropertyVetoException e) {
					e.printStackTrace();
				} 
			}
		});
		rentButton.setBounds(26, 88, 133, 23);
		Dashboard.add(rentButton);
		
		//Button to view All transactions of the currently signed in user
		JButton viewAll = new JButton("View All Transaction");
		
		//Action executed when View All transactions is pressed
		viewAll.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			try {
				
				//Pushes View All transactions layer to the front
				Request.moveToBack();
				View.moveToBack();
				ViewAll.setMaximum(true);
				ViewAll.setLayer(3);
				Request.setLayer(2);
				View.setLayer(1);
				
				//adds transaction model with initial format of cells and rows and their values
				transactionTable.setModel(new DefaultTableModel(null,new String[] {"Equipment Name", "Category", "Date", "Cost"}));
				
				//connects to the database
				connection = DBConnectorFactory.getDatabaseConnection();
				
				//Three statements to effectively carry out our queries need to display readable information to the use
				stmt = connection.createStatement();
				Statement transtmt = connection.createStatement();
				Statement cat_stmt = connection.createStatement();
				
				
				String userIdQuery = "SELECT id FROM users where customer_id='"+custidtxt.getText()+"'";//get id of currently signed in user
				ResultSet userExe = stmt.executeQuery(userIdQuery);//execute that query with the first statement
				userExe.next();//continue operation
				String allTrans = "SELECT * FROM transaction where customer_id=" + userExe.getInt("id");//Query to get transactions of currently signed in use
				result = stmt.executeQuery(allTrans);//execte query with first statement
				while(result.next()) {//continue operation if we didnt get a null response from query
					String equipMoreInfo = "Select name,category_id,cost from equipment where id="+result.getInt("equipment_id");//query to retrieve equipment name and category id for further querying
					ResultSet equipMoreResult = transtmt.executeQuery(equipMoreInfo);//execute above query to retrieve equipment info and category Id for next query
					while(equipMoreResult.next()) {//continue operation
						String categoryInfo = "Select name from category where id="+equipMoreResult.getInt("category_id");//uses category id to retrieve category name
						ResultSet moreCategoryInfo = cat_stmt.executeQuery(categoryInfo);//execute query with third and final statement variable declared
						moreCategoryInfo.next();//continue operation
						SimpleDateFormat simpDate = new SimpleDateFormat("MMM dd, yyyy");//this lines helps to format date shown
						Object[] tableData = {
								equipMoreResult.getString("name"),
								moreCategoryInfo.getString("name"),
								simpDate.format(result.getDate("date")),
								equipMoreResult.getFloat("cost")
						};//displays and formats rows that will be inserted in the table
						DefaultTableModel tblModel= (DefaultTableModel)transactionTable.getModel();
						tblModel.addRow(tableData);//add row wid information to table
					}
				}
				
			} catch ( PropertyVetoException |SQLException e ) {
				e.printStackTrace();
			} 
		}
	});
		viewAll.setBounds(26, 164, 133, 23);
		Dashboard.add(viewAll);
		View.getContentPane().setLayout(null);
		
		
		
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(0, 0, 602, 291);
		View.getContentPane().add(scrollPane2);
		
		viewTable = new JTable();
		
		viewTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		viewTable.setColumnSelectionAllowed(true);
		
		scrollPane2.setViewportView(viewTable);
		
		
		viewTable.setModel(new DefaultTableModel(
			new Object[][] {
			
			},
			new String[] {
				"Equipment Name", "Category", "Date", "Cost"
			}
		));
		viewTable.getColumnModel().getColumn(0).setResizable(false);
		viewTable.getColumnModel().getColumn(1).setResizable(false);
		viewTable.getColumnModel().getColumn(2).setResizable(false);
		viewTable.getColumnModel().getColumn(3).setResizable(false);

		View.getContentPane().add(scrollPane2);
		
		JButton selectbttn = new JButton("Select");
		selectbttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showDetails();
			}
		});
		selectbttn.setBounds(252, 335, 89, 23);
		View.getContentPane().add(selectbttn);
		
		
		transBox.setBounds(243, 302, 108, 23);
		View.getContentPane().add(transBox);
		
		JButton viewButton = new JButton("View A Transaction");
		viewButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					View.setLayer(3);
					ViewAll.setLayer(2);
					Request.setLayer(1);
					View.moveToFront();
					View.setMaximum(true);
					ViewAll.moveToBack();
					Request.moveToBack();
					getItems();
				} catch (PropertyVetoException e) {
					e.printStackTrace();
				}
			}
		});
		viewButton.setBounds(26, 230, 133, 23);
		Dashboard.add(viewButton);
		
		JButton signOut = new JButton("Sign Out");
		signOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					connection.close();
					JOptionPane.showMessageDialog(null, "You have logged out of your account! Bye"+custidtxt.getText(),"Sign Out",
							JOptionPane.INFORMATION_MESSAGE);
					Login.setVisible(true);
					Dashboard.setVisible(false);
					Logger.info("Customer: "+custidtxt.getText()+" has signed out");
				} catch (SQLException e) {
					e.printStackTrace();
					Logger.info("Error signing out customer: "+custidtxt.getText());
				}
				
			}
		});
		signOut.setBounds(26, 297, 133, 23);
		Dashboard.add(signOut);
		View.setVisible(true);
		ViewAll.setVisible(true);
		Request.setVisible(true);

		signOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
				
			}
		});

	}
	
	
public void showDetails() {
		
		try {
		//adds transaction model with initial format of cells and rows and their values
		viewTable.setModel(new DefaultTableModel(null,new String[] {"Equipment Name", "Category", "Date", "Cost"}));
		
		//connects to the database
		connection = DBConnectorFactory.getDatabaseConnection();
		String userIdQuery = "SELECT id FROM users where customer_id='"+custidtxt.getText()+"'";//get id of currently signed in user
		Statement stmt1 = connection.createStatement();
		ResultSet uid = stmt1.executeQuery(userIdQuery);
		uid.next();
		
		//Three statements to effectively carry out our queries need to display readable information to the use
		stmt = connection.createStatement();
			
		
		String joinQuery="SELECT e.name,e.category_id,t.date,e.cost FROM equipment AS e inner JOIN transaction as t on e.id=t.equipment_id WHERE e.name= '"+transBox.getSelectedItem()+"'"+"AND t.customer_id='"+uid.getInt("id")+"'";                   
			
		
		
		ResultSet joinResult = stmt.executeQuery(joinQuery);//execute that query with the first statement
		
			while(joinResult.next()) {//continue operation
				
				//SimpleDateFormat simpDate = new SimpleDateFormat("MMM dd, yyyy");//this lines helps to format date shown
				Object[] tableData = {
						joinResult.getString("name"),
						joinResult.getInt("category_id"),
						joinResult.getString("date"),
						joinResult.getFloat("cost")
						
						};//Loads the values retrieved from database into table column object 
				DefaultTableModel tblModel= (DefaultTableModel)viewTable.getModel();
				tblModel.addRow(tableData);//add row with information to table
					
		}
	
	} catch (SQLException e) {
		e.printStackTrace();
	}}

	
	
	
	public void getItems() {
		try {
			transBox.removeAllItems();
			String userIdQuery = "SELECT id FROM users where customer_id='"+custidtxt.getText()+"'";//get id of currently signed in user
			
			connection= DBConnectorFactory.getDatabaseConnection();
			stmt= connection.createStatement();
			Statement stmt1 = connection.createStatement();
			ResultSet uid = stmt1.executeQuery(userIdQuery);
			uid.next();
			String selectQuery= "SELECT name FROM equipment inner JOIN transaction on equipment.id=transaction.equipment_id WHERE transaction.customer_id='"+uid.getInt("id")+"'";
			result= stmt.executeQuery(selectQuery);
			while(result.next()) {
				String eqname= result.getString("name");
				transBox.addItem(eqname);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//Gets the name of the items that the logged in user has rented 
	
	
}
