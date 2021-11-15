package project.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdatepicker.*;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import project.authentication.Login;
import project.connectionFiles.DBConnectorFactory;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class RentDate extends JDialog {

	private static final long serialVersionUID = 1L;
	private static Connection connection=null;
	private Statement stmt=null;
	private ResultSet result=null;
	private final JPanel contentPanel = new JPanel();
	private JDatePanelImpl datePanel;
	private String date;
	private String id;
	private String eq_name;
	private String status;
	private String currentUser;
    private static final Logger LOGGER = LogManager.getLogger(RentDate.class);

	
	public static void main(String[] args) {
		try {
			RentDate dialog = new RentDate();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public RentDate( ) {
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Select Rent Date");
		setBounds(100, 100, 370, 243);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 0, 0);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		
		UtilDateModel model = new UtilDateModel();
		//model.setDate(20,04,2014);
		// Need this...
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		// Don't know about the formatter, but there it is...
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		datePicker.setBounds(0, 77, 356, 51);
		//model.setDate(new Date());
		
		JLabel Message = new JLabel("Enter the date you want to rent the equipment");
		Message.setBounds(44, 11, 284, 27);
		contentPanel.add(Message);
		
		JLabel monthLbl = new JLabel("Month");
		monthLbl.setBounds(44, 49, 49, 14);
		contentPanel.add(monthLbl);
		
		JLabel dateLbl = new JLabel("Date");
		dateLbl.setBounds(44, 86, 49, 14);
		contentPanel.add(dateLbl);
		
		
		
		JLabel yearVal = new JLabel("2021");
		yearVal.setBounds(94, 115, 85, 14);
		contentPanel.add(yearVal);
		
		
		JLabel yearLbl = new JLabel("Year");
		yearLbl.setBounds(44, 115, 49, 14);
		contentPanel.add(yearLbl);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 173, 356, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);//Adds button pane to JDialog window
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
							date=formatter.format(datePicker.getModel().getValue()).toString();//Gets the date value the user entered 
//							System.out.println(getId() + " " + getStatus() + " " + date);
							connection= DBConnectorFactory.getDatabaseConnection();
							String userIdQuery = "SELECT id FROM users where customer_id='"+getCurrentUser()+"'";//get id of currently signed in user
							Statement stmt1 = connection.createStatement();
							ResultSet uid = stmt1.executeQuery(userIdQuery);
							uid.next();
							
							stmt = connection.createStatement();
//							String insert = "INSERT INTO request VALUES (" + getId() + ","+ date + ","+"0)";
							String Query_String = "INSERT INTO transaction(equipment_id,date,customer_id) VALUES ('"+getId()+"','"+date+"','" + uid.getInt("id") + "')";
							stmt.executeLargeUpdate(Query_String);
							LOGGER.info("Equipment with ID: "+getId()+" was sucessfully requested in the database");
							dispose();
						} catch (SQLException e1) {
							LOGGER.error("Request for: "+getId()+" could not be added");
							e1.printStackTrace();
						}
					}
				});//Button listener for OK button. When pressed, it sends what the user selected to the database for a request 
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			
			
			 
			getContentPane().add(datePicker);
			
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}//Adds cancel button to JDialog window
		}
		
		JLabel lblNewLabel = new JLabel("Enter the date you want to rent");
		lblNewLabel.setBounds(95, 25, 196, 26);
		getContentPane().add(lblNewLabel);
		
	}
	
//	public void setVariables(String Id, String Name, String status) {
////		System.out.println(Id + " " + Name + " " + status);
//		
//		
//	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEq_name() {
		return eq_name;
	}

	public void setEq_name(String eq_name) {
		this.eq_name = eq_name;
	}

	public String getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}
}
