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
import javax.swing.text.BadLocationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	private static final Logger Logger = LogManager.getLogger(Application.class);

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
		frmGrizzlysEntertainment.setTitle("Grizzly's Entertainment- Customer");
		frmGrizzlysEntertainment.setBounds(100, 100, 797, 442);
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

		
		JLabel lblNewLabel = new JLabel("Welcome to Grizzly's Entertainment");
		lblNewLabel.setFont(new Font("Sylfaen", Font.BOLD, 24));
		lblNewLabel.setBounds(166, 30, 429, 46);
		Welcome_1.add(lblNewLabel);
		
		JLabel lblPleaseSelectAn = new JLabel("Please select an option below");
		lblPleaseSelectAn.setFont(new Font("Sylfaen", Font.BOLD, 24));
		lblPleaseSelectAn.setBounds(207, 106, 327, 46);
		Welcome_1.add(lblPleaseSelectAn);
		
		JButton btnNewButton = new JButton("Sign Up");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Welcome_1.setVisible(false);
				SignUp.setVisible(true);
			}
		});
		btnNewButton.setBounds(53, 235, 115, 29);
		Welcome_1.add(btnNewButton);
		
		JButton btnLoginIn = new JButton("Login");
		btnLoginIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Welcome_1.setVisible(false);
				Login.setVisible(true);
			}
		});
		btnLoginIn.setBounds(480, 235, 115, 29);
		Welcome_1.add(btnLoginIn);
		
		
		
		JLabel Greeting = new JLabel("Welcome, enter your info below");
		Greeting.setFont(new Font("Sylfaen", Font.BOLD, 24));
		Greeting.setBounds(199, 52, 360, 33);
		Login.add(Greeting);
		
		JLabel Custlbl = new JLabel("Customer ID:");
		Custlbl.setBounds(76, 125, 108, 20);
		Login.add(Custlbl);
		
		JLabel Passlbl = new JLabel("Password:");
		Passlbl.setBounds(76, 187, 108, 20);
		Login.add(Passlbl);
		
		custidtxt = new JTextField();
		custidtxt.setBounds(199, 122, 146, 26);
		Login.add(custidtxt);
		custidtxt.setColumns(10);
		
		passtxt = new JPasswordField();
		passtxt.setBounds(199, 184, 146, 26);
		Login.add(passtxt);
		passtxt.setColumns(10);
		
		JButton submit = new JButton("Login");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User c= new User();
				c.authenticateCustomer(custidtxt.getText(), passtxt.getText().toString());
				Logger.info("Hello");
			}
		});
		submit.setBounds(307, 274, 115, 29);
		Login.add(submit);
		
				
		
	}
}
