package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.*;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class RentDate extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JDatePanelImpl datePanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RentDate dialog = new RentDate();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the dialog.
	 */
	public RentDate() {
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Select Rent Date");
		setBounds(100, 100, 370, 243);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
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
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
						System.out.println(formatter.format(datePicker.getModel().getValue())); 
						
					}
				});
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
			}
		}
		
	}
	
	public void displayWindow() {
		try {
			RentDate dialog = new RentDate();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
