package gui;

import java.awt.EventQueue;
import java.util.Random;   
import javax.swing.*;
import java.awt.*;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import accounts.Equipment;
import connectionFiles.DBConnectorFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JDesktopPane;
import java.awt.Panel;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Button;
import javax.swing.JSplitPane;
import java.awt.Label;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import accounts.*;

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

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the application.
	 */
	public Application() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGrizzlysEntertainment = new JFrame();
		frmGrizzlysEntertainment.setResizable(false);
		frmGrizzlysEntertainment.setTitle("Grizzly's Entertainment- Customer");
		frmGrizzlysEntertainment.setBounds(100, 100, 871, 498);
		frmGrizzlysEntertainment.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGrizzlysEntertainment.getContentPane().setLayout(new CardLayout(0, 0));
		
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
		lblNewLabel.setBounds(199, 30, 429, 46);
		Welcome_1.add(lblNewLabel);
		
		JLabel lblPleaseSelectAn = new JLabel("Please select an option below");
		lblPleaseSelectAn.setFont(new Font("Sylfaen", Font.BOLD, 24));
		lblPleaseSelectAn.setBounds(234, 107, 327, 46);
		Welcome_1.add(lblPleaseSelectAn);
		
		JButton btnNewButton = new JButton("Sign Up");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Welcome_1.setVisible(false);
				SignUp.setVisible(true);
			}
		});
		btnNewButton.setBounds(248, 235, 115, 29);
		Welcome_1.add(btnNewButton);
		
		JButton btnLoginIn = new JButton("Login");
		btnLoginIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Welcome_1.setVisible(false);
				Login.setVisible(true);
			}
		});
		btnLoginIn.setBounds(411, 235, 115, 29);
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
		
		JButton submit = new JButton("Login");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User c= new User();
				if(c.authenticateCustomer(custidtxt.getText(), passtxt.getText().toString())) {
					Dashboard.setVisible(true);
					Login.setVisible(false);
				}
				
				Logger.info("Hello");
			}
		});
		submit.setBounds(384, 273, 115, 29);
		Login.add(submit);
		
		
		
		JLabel Description = new JLabel("What would you like to do?");
		Description.setBounds(303, 16, 194, 20);
		Dashboard.add(Description);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(235, 52, 612, 398);
		Dashboard.add(layeredPane);
		layeredPane.setLayout(null);
		
		JInternalFrame Request = new JInternalFrame("Request Equipment");
		Request.setBounds(0, 0, 612, 398);
		layeredPane.setLayer(Request, 1);
		
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
		table.setColumnSelectionAllowed(true);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String check=(String)table.getModel().getValueAt(table.getSelectedRow(), 1);
				if (check.contains("2")) {
					JOptionPane.showMessageDialog(null, "Sorry, this device has already been rented","Error",JOptionPane.INFORMATION_MESSAGE);
				}
				else if(check.contains("1")) {
					RentDate rent=new RentDate();
					rent.displayWindow();
					
				}
				
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Availability"
			}
		));
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		
		
		
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
					table.setModel(new DefaultTableModel(null,new String[] {"Name","Availability"}));
					connection= DBConnectorFactory.getDatabaseConnection();
					stmt= connection.createStatement();
					char quote='"';
					String cat= quote+(String)comboBox.getSelectedItem()+quote;
					String searchSQL= "SELECT * FROM equipment WHERE category="+cat;
					result= stmt.executeQuery(searchSQL);
					while(result.next()) {
						String Name= result.getString("name");
						String Availability= result.getString("availability");
						String jtbledata[]= {Name,Availability};
						DefaultTableModel tblModel= (DefaultTableModel)table.getModel();
						tblModel.addRow(jtbledata);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
			}
		});
		search.setBounds(47, 162, 83, 23);
		Request.getContentPane().add(search);
		
		JInternalFrame ViewAll = new JInternalFrame("View All Transactions");
		ViewAll.setBounds(0, 0, 612, 398);
		layeredPane.setLayer(ViewAll, 0);
		ViewAll.setVisible(false);
		layeredPane.add(ViewAll);
		
		JInternalFrame View = new JInternalFrame("View a Transaction");
		View.setBounds(0, 0, 612, 398);
		layeredPane.setLayer(View, 0);
		View.setVisible(false);
		layeredPane.add(View);
		
		JButton rentButton = new JButton("Rent Equipment");
		rentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Request.setMaximum(true);
					ViewAll.moveToBack();
					View.moveToBack();
				} catch (PropertyVetoException e) {
					e.printStackTrace();
				}
			}
		});
		rentButton.setBounds(26, 88, 133, 23);
		Dashboard.add(rentButton);
		
		JButton viewAll = new JButton("View All Transaction");
		viewAll.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			try {
				ViewAll.moveToFront();
				ViewAll.setMaximum(true);
				Request.moveToBack();
				View.moveToBack();
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}
	});
		viewAll.setBounds(26, 164, 133, 23);
		Dashboard.add(viewAll);
		
		JButton viewButton = new JButton("View A Transaction");
		viewButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					View.moveToFront();
					View.setMaximum(true);
					ViewAll.moveToBack();
					Request.moveToBack();
				} catch (PropertyVetoException e) {
					e.printStackTrace();
				}
			}
		});
		viewButton.setBounds(26, 230, 133, 23);
		Dashboard.add(viewButton);
		
		JButton signOut = new JButton("Sign Out");
		signOut.setBounds(26, 297, 133, 23);
		Dashboard.add(signOut);
		View.setVisible(true);
		ViewAll.setVisible(true);
		Request.setVisible(true);
		
				
		
	}
}
