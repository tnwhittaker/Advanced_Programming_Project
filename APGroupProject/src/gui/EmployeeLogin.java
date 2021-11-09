package employee.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class EmployeeLogin {

	JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeLogin window = new EmployeeLogin();
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
	public EmployeeLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Employee Login");
		frame.setBounds(100, 100, 328, 270);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{61, 185, 0};
		gridBagLayout.rowHeights = new int[]{45, 19, 19, 19, 19, 43, 21, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblLoginId = new JLabel("Username : ");
		GridBagConstraints gbc_lblLoginId = new GridBagConstraints();
		gbc_lblLoginId.anchor = GridBagConstraints.WEST;
		gbc_lblLoginId.fill = GridBagConstraints.VERTICAL;
		gbc_lblLoginId.insets = new Insets(0, 0, 5, 0);
		gbc_lblLoginId.gridx = 1;
		gbc_lblLoginId.gridy = 1;
		frame.getContentPane().add(lblLoginId, gbc_lblLoginId);
		
		usernameField = new JTextField();
		GridBagConstraints gbc_usernameField = new GridBagConstraints();
		gbc_usernameField.anchor = GridBagConstraints.NORTH;
		gbc_usernameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_usernameField.insets = new Insets(0, 0, 5, 0);
		gbc_usernameField.gridx = 1;
		gbc_usernameField.gridy = 2;
		frame.getContentPane().add(usernameField, gbc_usernameField);
		usernameField.setColumns(10);
		
		JLabel lblLoginPassword = new JLabel("Password :");
		GridBagConstraints gbc_lblLoginPassword = new GridBagConstraints();
		gbc_lblLoginPassword.anchor = GridBagConstraints.WEST;
		gbc_lblLoginPassword.fill = GridBagConstraints.VERTICAL;
		gbc_lblLoginPassword.insets = new Insets(0, 0, 5, 0);
		gbc_lblLoginPassword.gridx = 1;
		gbc_lblLoginPassword.gridy = 3;
		frame.getContentPane().add(lblLoginPassword, gbc_lblLoginPassword);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.anchor = GridBagConstraints.NORTH;
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 4;
		frame.getContentPane().add(passwordField, gbc_passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uName= usernameField.getText();
				String pass= passwordField.getText();
				
				if(uName.equals("admin")&& pass.equals("password")) {
					EmployeeGUI eGUI= new EmployeeGUI();
					eGUI.setVisible(true);
					frame.dispose();
					
				}else {
					JOptionPane.showMessageDialog(null,"Incorrect username or password","LOGIN FAILED",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.anchor = GridBagConstraints.NORTH;
		gbc_btnLogin.gridx = 1;
		gbc_btnLogin.gridy = 6;
		frame.getContentPane().add(btnLogin, gbc_btnLogin);
		//set btnLogin to activate on pressing 'Enter'
		frame.getRootPane().setDefaultButton(btnLogin);
	}
}
