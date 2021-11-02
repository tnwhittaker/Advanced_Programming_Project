package gui.test;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Insets;

public class guiTest {

	private JFrame frame;
	private JTextField txtEventDate;
	private JTextField textFieldEventName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guiTest window = new guiTest();
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
	public guiTest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 473, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		frame.getContentPane().add(tabbedPane, gbc_tabbedPane);
		
		JPanel panelHome = new JPanel();
		tabbedPane.addTab("Home", null, panelHome, null);
		GridBagLayout gbl_panelHome = new GridBagLayout();
		gbl_panelHome.columnWidths = new int[]{82, 129, 40, 82, 45, 50, 0};
		gbl_panelHome.rowHeights = new int[]{19, 18, 19, 186, 0};
		gbl_panelHome.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelHome.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelHome.setLayout(gbl_panelHome);
		
		JLabel lblNewLabel = new JLabel("Event Name :");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panelHome.add(lblNewLabel, gbc_lblNewLabel);
		
		textFieldEventName = new JTextField();
		GridBagConstraints gbc_textFieldEventName = new GridBagConstraints();
		gbc_textFieldEventName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldEventName.anchor = GridBagConstraints.NORTH;
		gbc_textFieldEventName.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldEventName.gridx = 1;
		gbc_textFieldEventName.gridy = 0;
		panelHome.add(textFieldEventName, gbc_textFieldEventName);
		textFieldEventName.setColumns(10);
		
		JLabel lblNewLabel_19 = new JLabel("Item Category :");
		GridBagConstraints gbc_lblNewLabel_19 = new GridBagConstraints();
		gbc_lblNewLabel_19.anchor = GridBagConstraints.SOUTH;
		gbc_lblNewLabel_19.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_19.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_19.gridx = 0;
		gbc_lblNewLabel_19.gridy = 1;
		panelHome.add(lblNewLabel_19, gbc_lblNewLabel_19);
		
		JComboBox comboBoxItemCategory = new JComboBox();
		GridBagConstraints gbc_comboBoxItemCategory = new GridBagConstraints();
		gbc_comboBoxItemCategory.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxItemCategory.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxItemCategory.gridheight = 2;
		gbc_comboBoxItemCategory.gridx = 1;
		gbc_comboBoxItemCategory.gridy = 1;
		panelHome.add(comboBoxItemCategory, gbc_comboBoxItemCategory);
		
		JLabel lblNewLabel_1 = new JLabel("Estimated Total : ");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 3;
		gbc_lblNewLabel_1.gridy = 1;
		panelHome.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblTotalCost = new JLabel("$0.00");
		GridBagConstraints gbc_lblTotalCost = new GridBagConstraints();
		gbc_lblTotalCost.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblTotalCost.insets = new Insets(0, 0, 5, 0);
		gbc_lblTotalCost.gridx = 5;
		gbc_lblTotalCost.gridy = 1;
		panelHome.add(lblTotalCost, gbc_lblTotalCost);
		
		JLabel lblNewLabel_3 = new JLabel("Event Date :");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.SOUTH;
		gbc_lblNewLabel_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 3;
		gbc_lblNewLabel_3.gridy = 2;
		panelHome.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		txtEventDate = new JTextField();
		txtEventDate.setText("dd/mm/yyyy");
		GridBagConstraints gbc_txtEventDate = new GridBagConstraints();
		gbc_txtEventDate.anchor = GridBagConstraints.NORTHEAST;
		gbc_txtEventDate.insets = new Insets(0, 0, 5, 0);
		gbc_txtEventDate.gridwidth = 3;
		gbc_txtEventDate.gridx = 3;
		gbc_txtEventDate.gridy = 2;
		panelHome.add(txtEventDate, gbc_txtEventDate);
		txtEventDate.setColumns(10);
		
		JList inventoryList = new JList();
		GridBagConstraints gbc_inventoryList = new GridBagConstraints();
		gbc_inventoryList.fill = GridBagConstraints.BOTH;
		gbc_inventoryList.insets = new Insets(0, 0, 0, 5);
		gbc_inventoryList.gridwidth = 2;
		gbc_inventoryList.gridx = 0;
		gbc_inventoryList.gridy = 3;
		panelHome.add(inventoryList, gbc_inventoryList);
		
		JButton btnCustomerRequest = new JButton("Request");
		GridBagConstraints gbc_btnCustomerRequest = new GridBagConstraints();
		gbc_btnCustomerRequest.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnCustomerRequest.gridwidth = 3;
		gbc_btnCustomerRequest.gridx = 3;
		gbc_btnCustomerRequest.gridy = 3;
		panelHome.add(btnCustomerRequest, gbc_btnCustomerRequest);
		
		JPanel panelTransactions = new JPanel();
		tabbedPane.addTab("Transactions", null, panelTransactions, null);
		GridBagLayout gbl_panelTransactions = new GridBagLayout();
		gbl_panelTransactions.columnWidths = new int[]{65, 88, 63, 208, 0};
		gbl_panelTransactions.rowHeights = new int[]{15, 21, 13, 13, 99, 0};
		gbl_panelTransactions.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelTransactions.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelTransactions.setLayout(gbl_panelTransactions);
		
		JLabel lblNewLabel_4 = new JLabel("Transactions History");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_4.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridwidth = 2;
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 0;
		panelTransactions.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Event Name : ");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 1;
		panelTransactions.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		JComboBox comboBoxEventName = new JComboBox();
		GridBagConstraints gbc_comboBoxEventName = new GridBagConstraints();
		gbc_comboBoxEventName.anchor = GridBagConstraints.NORTH;
		gbc_comboBoxEventName.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEventName.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxEventName.gridx = 1;
		gbc_comboBoxEventName.gridy = 1;
		panelTransactions.add(comboBoxEventName, gbc_comboBoxEventName);
		
		JList eventRecieptList = new JList();
		GridBagConstraints gbc_eventRecieptList = new GridBagConstraints();
		gbc_eventRecieptList.fill = GridBagConstraints.BOTH;
		gbc_eventRecieptList.gridheight = 4;
		gbc_eventRecieptList.gridx = 3;
		gbc_eventRecieptList.gridy = 1;
		panelTransactions.add(eventRecieptList, gbc_eventRecieptList);
		
		JLabel lblNewLabel_6 = new JLabel("Event Date  :");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 2;
		panelTransactions.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		JLabel lblEventDate = new JLabel("dd/mm/yyyy");
		GridBagConstraints gbc_lblEventDate = new GridBagConstraints();
		gbc_lblEventDate.anchor = GridBagConstraints.NORTH;
		gbc_lblEventDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEventDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblEventDate.gridx = 1;
		gbc_lblEventDate.gridy = 2;
		panelTransactions.add(lblEventDate, gbc_lblEventDate);
		
		JLabel lblNewLabel_8 = new JLabel("Total :");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 0;
		gbc_lblNewLabel_8.gridy = 3;
		panelTransactions.add(lblNewLabel_8, gbc_lblNewLabel_8);
		
		JLabel lblTransactionTotal = new JLabel("$0.00");
		GridBagConstraints gbc_lblTransactionTotal = new GridBagConstraints();
		gbc_lblTransactionTotal.anchor = GridBagConstraints.NORTH;
		gbc_lblTransactionTotal.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTransactionTotal.insets = new Insets(0, 0, 5, 5);
		gbc_lblTransactionTotal.gridx = 1;
		gbc_lblTransactionTotal.gridy = 3;
		panelTransactions.add(lblTransactionTotal, gbc_lblTransactionTotal);
		
		JLabel lblNewLabel_10 = new JLabel("Status :");
		GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
		gbc_lblNewLabel_10.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel_10.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_10.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_10.gridx = 0;
		gbc_lblNewLabel_10.gridy = 4;
		panelTransactions.add(lblNewLabel_10, gbc_lblNewLabel_10);
		
		JLabel lblTransactionStatus = new JLabel("pending");
		GridBagConstraints gbc_lblTransactionStatus = new GridBagConstraints();
		gbc_lblTransactionStatus.anchor = GridBagConstraints.NORTH;
		gbc_lblTransactionStatus.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTransactionStatus.insets = new Insets(0, 0, 0, 5);
		gbc_lblTransactionStatus.gridx = 1;
		gbc_lblTransactionStatus.gridy = 4;
		panelTransactions.add(lblTransactionStatus, gbc_lblTransactionStatus);
		
		JPanel panelAccountInfo = new JPanel();
		tabbedPane.addTab("Account", null, panelAccountInfo, null);
		GridBagLayout gbl_panelAccountInfo = new GridBagLayout();
		gbl_panelAccountInfo.columnWidths = new int[]{100, 45, 0};
		gbl_panelAccountInfo.rowHeights = new int[]{13, 13, 13, 13, 0};
		gbl_panelAccountInfo.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelAccountInfo.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelAccountInfo.setLayout(gbl_panelAccountInfo);
		
		JLabel lblNewLabel_18 = new JLabel("Account Information");
		GridBagConstraints gbc_lblNewLabel_18 = new GridBagConstraints();
		gbc_lblNewLabel_18.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel_18.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_18.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_18.gridx = 0;
		gbc_lblNewLabel_18.gridy = 0;
		panelAccountInfo.add(lblNewLabel_18, gbc_lblNewLabel_18);
		
		JLabel lblNewLabel_12 = new JLabel("Username :");
		GridBagConstraints gbc_lblNewLabel_12 = new GridBagConstraints();
		gbc_lblNewLabel_12.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel_12.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_12.gridx = 0;
		gbc_lblNewLabel_12.gridy = 1;
		panelAccountInfo.add(lblNewLabel_12, gbc_lblNewLabel_12);
		
		JLabel lblUserName = new JLabel("");
		GridBagConstraints gbc_lblUserName = new GridBagConstraints();
		gbc_lblUserName.fill = GridBagConstraints.BOTH;
		gbc_lblUserName.insets = new Insets(0, 0, 5, 0);
		gbc_lblUserName.gridx = 1;
		gbc_lblUserName.gridy = 1;
		panelAccountInfo.add(lblUserName, gbc_lblUserName);
		
		JLabel lblNewLabel_13 = new JLabel("Contact :");
		GridBagConstraints gbc_lblNewLabel_13 = new GridBagConstraints();
		gbc_lblNewLabel_13.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel_13.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_13.gridx = 0;
		gbc_lblNewLabel_13.gridy = 2;
		panelAccountInfo.add(lblNewLabel_13, gbc_lblNewLabel_13);
		
		JLabel lblUserContact = new JLabel("");
		GridBagConstraints gbc_lblUserContact = new GridBagConstraints();
		gbc_lblUserContact.fill = GridBagConstraints.BOTH;
		gbc_lblUserContact.insets = new Insets(0, 0, 5, 0);
		gbc_lblUserContact.gridx = 1;
		gbc_lblUserContact.gridy = 2;
		panelAccountInfo.add(lblUserContact, gbc_lblUserContact);
		
		JLabel lblNewLabel_14 = new JLabel("# of Transactions :");
		GridBagConstraints gbc_lblNewLabel_14 = new GridBagConstraints();
		gbc_lblNewLabel_14.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel_14.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_14.gridx = 0;
		gbc_lblNewLabel_14.gridy = 3;
		panelAccountInfo.add(lblNewLabel_14, gbc_lblNewLabel_14);
		
		JLabel lblTotalTransactionsMade = new JLabel("");
		GridBagConstraints gbc_lblTotalTransactionsMade = new GridBagConstraints();
		gbc_lblTotalTransactionsMade.fill = GridBagConstraints.BOTH;
		gbc_lblTotalTransactionsMade.gridx = 1;
		gbc_lblTotalTransactionsMade.gridy = 3;
		panelAccountInfo.add(lblTotalTransactionsMade, gbc_lblTotalTransactionsMade);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Hello World");
		menuBar.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Exit");
		menuBar.add(mntmNewMenuItem_1);
		mntmNewMenuItem_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});
	}
}
