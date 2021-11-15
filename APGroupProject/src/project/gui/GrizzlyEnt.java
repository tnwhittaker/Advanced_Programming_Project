package project.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GrizzlyEnt {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GrizzlyEnt window = new GrizzlyEnt();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GrizzlyEnt() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 871, 498);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel welcome = new JLabel("Welcome to Grizzly's Entertainment");
		welcome.setFont(new Font("Serif", Font.PLAIN, 33));
		welcome.setBounds(62, 11, 518, 44);
		frame.getContentPane().add(welcome);
		
		JLabel selection = new JLabel("Please select an application");
		selection.setFont(new Font("Serif", Font.PLAIN, 20));
		selection.setBounds(164, 87, 305, 44);
		frame.getContentPane().add(selection);
		
		JButton employeeButton = new JButton("Employee");
		employeeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				EmployeeLogin el= new EmployeeLogin();
				el.frame.setVisible(true);
			}
		});
		employeeButton.setBounds(157, 194, 89, 23);
		frame.getContentPane().add(employeeButton);
		
		JButton customerButton = new JButton("Customer");
		customerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Application app=new Application();
				
				
			}
		});
		customerButton.setBounds(313, 194, 98, 23);
		frame.getContentPane().add(customerButton);
	}
}
