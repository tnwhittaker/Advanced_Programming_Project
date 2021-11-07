package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class RentDate extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

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
		
		JLabel Message = new JLabel("Enter the date you want to rent the equipment");
		Message.setBounds(44, 11, 284, 27);
		contentPanel.add(Message);
		
		JLabel monthLbl = new JLabel("Month");
		monthLbl.setBounds(44, 49, 49, 14);
		contentPanel.add(monthLbl);
		
		JLabel dateLbl = new JLabel("Date");
		dateLbl.setBounds(44, 86, 49, 14);
		contentPanel.add(dateLbl);
		
		JComboBox monthBox = new JComboBox();
		monthBox.setModel(new DefaultComboBoxModel(new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
		monthBox.setBounds(94, 49, 85, 22);
		contentPanel.add(monthBox);
		
		JComboBox dateBox = new JComboBox();
		dateBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		dateBox.setBounds(94, 82, 85, 22);
		contentPanel.add(dateBox);
		
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
						
								int date= Integer.valueOf((String)dateBox.getSelectedItem());
								if (monthBox.getSelectedItem()=="February"&& date>29) 
								{
									JOptionPane.showMessageDialog(okButton, "Invalid Date!");
								}
								if(monthBox.getSelectedItem()=="April") {
									JOptionPane.showMessageDialog(okButton, "Invalid Date!");
								}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
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
