package guiDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.table.DefaultTableModel;
import inventory.Inventory;

public class EmployeeGUI {
	private String getComboBoxAction;
	private String getCategoryOptSlctd;
	private String Status1 = "";
	private JFrame frame;
	private JTable Inventory;
	private JTable tableRequestedItems;
	private Inventory invent = new Inventory();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeGUI window = new EmployeeGUI();
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
	public EmployeeGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Grizzly's Entertainment - Employees");
		frame.setBounds(100, 100, 519, 526);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		frame.getContentPane().add(tabbedPane, gbc_tabbedPane);

		JPanel panelInventory = new JPanel();
		tabbedPane.addTab("Inventory", null, panelInventory, null);
		GridBagLayout gbl_panelInventory = new GridBagLayout();
		gbl_panelInventory.columnWidths = new int[] { 72, 106, 136, 103, 28, 0 };
		gbl_panelInventory.rowHeights = new int[] { 21, 58, 21, 294, 0 };
		gbl_panelInventory.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelInventory.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelInventory.setLayout(gbl_panelInventory);
		String itemType[] = { "All", "Lighting", "Power", "Sound", "Staging" };

		JLabel lblCategory = new JLabel("Item Category :");
		GridBagConstraints gbc_lblCategory = new GridBagConstraints();
		gbc_lblCategory.anchor = GridBagConstraints.WEST;
		gbc_lblCategory.insets = new Insets(0, 0, 5, 5);
		gbc_lblCategory.gridx = 0;
		gbc_lblCategory.gridy = 0;
		panelInventory.add(lblCategory, gbc_lblCategory);

		JComboBox comboBoxItemCategory = new JComboBox(itemType);
		comboBoxItemCategory.setModel(
				new DefaultComboBoxModel(new String[] { " ", "All", "Lighting", "Power", "Sound", "Staging" }));
		GridBagConstraints gbc_comboBoxItemCategory = new GridBagConstraints();
		gbc_comboBoxItemCategory.fill = GridBagConstraints.BOTH;
		gbc_comboBoxItemCategory.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxItemCategory.gridx = 1;
		gbc_comboBoxItemCategory.gridy = 0;
		panelInventory.add(comboBoxItemCategory, gbc_comboBoxItemCategory);
		comboBoxItemCategory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = (String) comboBoxItemCategory.getSelectedItem();
				switch (s) {
				case "All":
					invent.readAll();
					break;

				case "Lighting":
					invent.readAll("lighting");
					break;

				case "Power":
					invent.readAll("power");
					break;

				case "Sound":
					invent.readAll("Sound");
					break;

				case "Staging":
					invent.readAll("Staging");

					break;
					/*
					 * 
					 * 
					 * 
					 * 
					 * 
					 * 
					 * 
					 * 
					 * 
					 * 
					 * 
					 * 
					 * 
					 * 
					 */
				}
			}
		});

		JLabel lblEdit = new JLabel("Edit Inventory");
		GridBagConstraints gbc_lblEdit = new GridBagConstraints();
		gbc_lblEdit.anchor = GridBagConstraints.EAST;
		gbc_lblEdit.insets = new Insets(0, 0, 5, 5);
		gbc_lblEdit.gridx = 3;
		gbc_lblEdit.gridy = 0;
		panelInventory.add(lblEdit, gbc_lblEdit);

		JCheckBox chckbxEditMode = new JCheckBox("");
		GridBagConstraints gbc_chckbxEditMode = new GridBagConstraints();
		gbc_chckbxEditMode.anchor = GridBagConstraints.NORTH;
		gbc_chckbxEditMode.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxEditMode.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxEditMode.gridx = 4;
		gbc_chckbxEditMode.gridy = 0;

		panelInventory.add(chckbxEditMode, gbc_chckbxEditMode);

		JButton btnUpdateItem = new JButton("Update Item");
		btnUpdateItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
				
			}
		});

		Inventory = new JTable();
		Inventory.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null }, },
				new String[] { "ItemName", "ItemID", "Category", "Status", "DateofRequirement" }

		) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		GridBagConstraints gbc_Inventory = new GridBagConstraints();
		gbc_Inventory.fill = GridBagConstraints.BOTH;
		gbc_Inventory.insets = new Insets(0, 0, 0, 5);
		gbc_Inventory.gridheight = 3;
		gbc_Inventory.gridwidth = 2;
		gbc_Inventory.gridx = 0;
		gbc_Inventory.gridy = 1;
		panelInventory.add(Inventory, gbc_Inventory);

		JButton btnNewItem = new JButton("New Item");
		GridBagConstraints gbc_btnNewItem = new GridBagConstraints();
		gbc_btnNewItem.anchor = GridBagConstraints.SOUTH;
		gbc_btnNewItem.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewItem.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewItem.gridwidth = 2;
		gbc_btnNewItem.gridx = 3;
		gbc_btnNewItem.gridy = 1;
		panelInventory.add(btnNewItem, gbc_btnNewItem);
		GridBagConstraints gbc_btnUpdateItem = new GridBagConstraints();
		gbc_btnUpdateItem.anchor = GridBagConstraints.NORTH;
		gbc_btnUpdateItem.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnUpdateItem.insets = new Insets(0, 0, 5, 0);
		gbc_btnUpdateItem.gridwidth = 2;
		gbc_btnUpdateItem.gridx = 3;
		gbc_btnUpdateItem.gridy = 2;
		panelInventory.add(btnUpdateItem, gbc_btnUpdateItem);
		btnNewItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddNewItem();
			}
		});

		JButton btnDeleteItem = new JButton("Delete Item");
		GridBagConstraints gbc_btnDeleteItem = new GridBagConstraints();
		gbc_btnDeleteItem.anchor = GridBagConstraints.NORTH;
		gbc_btnDeleteItem.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDeleteItem.gridwidth = 2;
		gbc_btnDeleteItem.gridx = 3;
		gbc_btnDeleteItem.gridy = 3;
		panelInventory.add(btnDeleteItem, gbc_btnDeleteItem);
		btnDeleteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				delete();
			}
		});

		JPanel panelRequestsHub = new JPanel();
		tabbedPane.addTab("Requests Hub", null, panelRequestsHub, null);
		GridBagLayout gbl_panelRequestsHub = new GridBagLayout();
		gbl_panelRequestsHub.columnWidths = new int[] { 82, 131, 43, 93, 103, 0 };
		gbl_panelRequestsHub.rowHeights = new int[] { 21, 21, 13, 139, 21, 13, 77, 45, 0 };
		gbl_panelRequestsHub.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelRequestsHub.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelRequestsHub.setLayout(gbl_panelRequestsHub);

		JLabel lblActiveRequests = new JLabel("Active Requests : ");
		GridBagConstraints gbc_lblActiveRequests = new GridBagConstraints();
		gbc_lblActiveRequests.anchor = GridBagConstraints.WEST;
		gbc_lblActiveRequests.insets = new Insets(0, 0, 5, 5);
		gbc_lblActiveRequests.gridx = 0;
		gbc_lblActiveRequests.gridy = 0;
		panelRequestsHub.add(lblActiveRequests, gbc_lblActiveRequests);

		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.anchor = GridBagConstraints.NORTH;
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		panelRequestsHub.add(comboBox, gbc_comboBox);

		tableRequestedItems = new JTable();
		tableRequestedItems.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null }, },
				new String[] { "ItemName", "ItemID", "Category", "Status" }

		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		GridBagConstraints gbc_tableRequestedItems = new GridBagConstraints();
		gbc_tableRequestedItems.gridheight = 7;
		gbc_tableRequestedItems.gridwidth = 2;
		gbc_tableRequestedItems.insets = new Insets(0, 0, 5, 5);
		gbc_tableRequestedItems.fill = GridBagConstraints.BOTH;
		gbc_tableRequestedItems.gridx = 0;
		gbc_tableRequestedItems.gridy = 1;
		panelRequestsHub.add(tableRequestedItems, gbc_tableRequestedItems);

		JLabel lblRequestDate = new JLabel("Request Date :");
		GridBagConstraints gbc_lblRequestDate = new GridBagConstraints();
		gbc_lblRequestDate.anchor = GridBagConstraints.SOUTH;
		gbc_lblRequestDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRequestDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblRequestDate.gridx = 3;
		gbc_lblRequestDate.gridy = 1;
		panelRequestsHub.add(lblRequestDate, gbc_lblRequestDate);

		JLabel lblDate = new JLabel("dd/mm/yyyy");
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblDate.insets = new Insets(0, 0, 5, 0);
		gbc_lblDate.gridx = 4;
		gbc_lblDate.gridy = 1;
		panelRequestsHub.add(lblDate, gbc_lblDate);

		JLabel lblRequestCost = new JLabel("Total Cost :");
		GridBagConstraints gbc_lblRequestCost = new GridBagConstraints();
		gbc_lblRequestCost.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblRequestCost.insets = new Insets(0, 0, 5, 5);
		gbc_lblRequestCost.gridx = 3;
		gbc_lblRequestCost.gridy = 2;
		panelRequestsHub.add(lblRequestCost, gbc_lblRequestCost);

		JLabel lblCost = new JLabel("$ 0.00");
		GridBagConstraints gbc_lblCost = new GridBagConstraints();
		gbc_lblCost.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblCost.insets = new Insets(0, 0, 5, 0);
		gbc_lblCost.gridx = 4;
		gbc_lblCost.gridy = 2;
		panelRequestsHub.add(lblCost, gbc_lblCost);

		// ButtonGroup "approval" moved once, it'll probably move again. Just check if
		// it is above the radioButtons
		ButtonGroup approval = new ButtonGroup();

		JRadioButton rdbtnApproval = new JRadioButton("Approved");
		GridBagConstraints gbc_rdbtnApproval = new GridBagConstraints();
		gbc_rdbtnApproval.anchor = GridBagConstraints.NORTH;
		gbc_rdbtnApproval.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnApproval.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnApproval.gridx = 3;
		gbc_rdbtnApproval.gridy = 4;
		panelRequestsHub.add(rdbtnApproval, gbc_rdbtnApproval);
		approval.add(rdbtnApproval);

		JRadioButton rdbtnDenial = new JRadioButton("Denied");
		GridBagConstraints gbc_rdbtnDenial = new GridBagConstraints();
		gbc_rdbtnDenial.anchor = GridBagConstraints.NORTH;
		gbc_rdbtnDenial.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnDenial.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnDenial.gridx = 4;
		gbc_rdbtnDenial.gridy = 4;
		panelRequestsHub.add(rdbtnDenial, gbc_rdbtnDenial);
		approval.add(rdbtnDenial);

		JLabel lblNotes = new JLabel("Additional Notes");
		GridBagConstraints gbc_lblNotes = new GridBagConstraints();
		gbc_lblNotes.anchor = GridBagConstraints.NORTH;
		gbc_lblNotes.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNotes.insets = new Insets(0, 0, 5, 5);
		gbc_lblNotes.gridx = 3;
		gbc_lblNotes.gridy = 5;
		panelRequestsHub.add(lblNotes, gbc_lblNotes);

		JTextArea textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.insets = new Insets(0, 0, 5, 0);
		gbc_textArea.gridwidth = 2;
		gbc_textArea.gridx = 3;
		gbc_textArea.gridy = 6;
		panelRequestsHub.add(textArea, gbc_textArea);

		JButton btnRequestResponse = new JButton("Respond");
		GridBagConstraints gbc_btnRequestResponse = new GridBagConstraints();
		gbc_btnRequestResponse.anchor = GridBagConstraints.NORTH;
		gbc_btnRequestResponse.insets = new Insets(0, 0, 0, 5);
		gbc_btnRequestResponse.gridx = 3;
		gbc_btnRequestResponse.gridy = 7;
		panelRequestsHub.add(btnRequestResponse, gbc_btnRequestResponse);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenuItem exitMenuItem = new JMenuItem("Exit");
		menuBar.add(exitMenuItem);

		JMenuItem mntmLogOut = new JMenuItem("Log Out");
		menuBar.add(mntmLogOut);
		exitMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}

		});
		mntmLogOut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				EmployeeLogin window = new EmployeeLogin();
				window.frame.setVisible(true);
			}

		});
	}

	public void setVisible(boolean b) {
		EmployeeGUI window = new EmployeeGUI();
		window.frame.setVisible(b);
	}
	
	
	
	
	
	
	
	

	
//	this method will add a new item in the database
	public void AddNewItem() {
		
		frame = new JFrame("Grizzly's Entertainment - Employees/Add new Item");
		frame.setBounds(100, 100, 519, 526);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel itemIDLbl = new JPanel();
		frame.getContentPane().add(itemIDLbl, BorderLayout.NORTH);
		GridBagLayout gbl_itemIDLbl = new GridBagLayout();
		gbl_itemIDLbl.columnWidths = new int[] { 65, 131, 63, 96, 37, 0 };
		gbl_itemIDLbl.rowHeights = new int[] { 30, 30, 30, 0, 30, 0, 30, 0, 30, 30, 30, 30, 30, 0, 0, 80 };
		gbl_itemIDLbl.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_itemIDLbl.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		itemIDLbl.setLayout(gbl_itemIDLbl);

		JLabel lblNewLabel = new JLabel("Add New Item");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 4;
		gbc_lblNewLabel.gridheight = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		itemIDLbl.add(lblNewLabel, gbc_lblNewLabel);

		JLabel itemID = new JLabel("Item ID:");
		itemID.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_itemID = new GridBagConstraints();
		gbc_itemID.insets = new Insets(0, 0, 5, 5);
		gbc_itemID.gridx = 1;
		gbc_itemID.gridy = 3;
		itemIDLbl.add(itemID, gbc_itemID);

		JTextField itemIDTxt = new JTextField();
		itemIDTxt.setToolTipText("Enter Item ID:");
		GridBagConstraints gbc_itemIDTxt = new GridBagConstraints();
		gbc_itemIDTxt.gridwidth = 2;
		gbc_itemIDTxt.insets = new Insets(0, 0, 5, 5);
		gbc_itemIDTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_itemIDTxt.gridx = 2;
		gbc_itemIDTxt.gridy = 3;
		itemIDLbl.add(itemIDTxt, gbc_itemIDTxt);
		ButtonGroup status = new ButtonGroup();
		
		
		
		
		
		
		
		/*
		 * 
		 * JLabel ID = new JLabel("Item ID:"); ID.setBounds(392, 145, 110, 20);
		 * addNewItem.add(ID);
		 */

		JLabel itemName = new JLabel("Item Name:");
		itemName.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_itemName = new GridBagConstraints();
		gbc_itemName.gridy = 5;
		gbc_itemName.insets = new Insets(0, 0, 5, 5);
		gbc_itemName.gridx = 1;
		itemIDLbl.add(itemName, gbc_itemName);

		JTextField itemNameTxt = new JTextField();
		itemNameTxt.setToolTipText("Add the name of the item");
		itemNameTxt.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_itemNameTxt = new GridBagConstraints();
		gbc_itemNameTxt.gridy = 5;
		gbc_itemNameTxt.gridwidth = 2;
		gbc_itemNameTxt.fill = GridBagConstraints.BOTH;
		gbc_itemNameTxt.insets = new Insets(0, 0, 5, 5);
		gbc_itemNameTxt.gridx = 2;
		itemIDLbl.add(itemNameTxt, gbc_itemNameTxt);

		JLabel categorylbl = new JLabel("Category:");
		categorylbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_categorylbl = new GridBagConstraints();
		gbc_categorylbl.insets = new Insets(0, 0, 5, 5);
		gbc_categorylbl.gridx = 1;
		gbc_categorylbl.gridy = 7;
		itemIDLbl.add(categorylbl, gbc_categorylbl);

		JComboBox categoryComboBox = new JComboBox();
		categoryComboBox.setModel(
				new DefaultComboBoxModel(new String[] { "", "Lighting", "Power", "Sound", "Staging" }));
		GridBagConstraints gbc_categoryComboBox = new GridBagConstraints();
		gbc_categoryComboBox.gridwidth = 2;
		gbc_categoryComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_categoryComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_categoryComboBox.gridx = 2;
		gbc_categoryComboBox.gridy = 7;
		itemIDLbl.add(categoryComboBox, gbc_categoryComboBox);
		categoryComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = (String) categoryComboBox.getSelectedItem();
				switch (s) {
				case "Lighting":
					getCategoryOptSlctd = "lighting";
					break;

				case "Power":
					getCategoryOptSlctd = "power";
					break;

				case "Sound":
					getCategoryOptSlctd = "Sound";
					break;

				case "Staging":
					getCategoryOptSlctd = "Staging";

					break;

				}
			}
		});

		JLabel statusLbl = new JLabel("Status");
		statusLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_statusLbl = new GridBagConstraints();
		gbc_statusLbl.insets = new Insets(0, 0, 5, 5);
		gbc_statusLbl.gridx = 1;
		gbc_statusLbl.gridy = 9;
		itemIDLbl.add(statusLbl, gbc_statusLbl);

		JRadioButton rdbtnDecline = new JRadioButton("Decline");
		GridBagConstraints gbc_rdbtnDecline = new GridBagConstraints();
		gbc_rdbtnDecline.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnDecline.gridx = 2;
		gbc_rdbtnDecline.gridy = 9;
		itemIDLbl.add(rdbtnDecline, gbc_rdbtnDecline);
		status.add(rdbtnDecline);
		if (rdbtnDecline.isSelected()) {
			Status1 = "Decline";
		}

		JRadioButton rdbtnApproved = new JRadioButton("Approve");
		GridBagConstraints gbc_rdbtnApproved = new GridBagConstraints();
		gbc_rdbtnApproved.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnApproved.gridx = 3;
		gbc_rdbtnApproved.gridy = 9;
		itemIDLbl.add(rdbtnApproved, gbc_rdbtnApproved);
		status.add(rdbtnApproved);
		if (rdbtnApproved.isSelected()) {
			Status1 = "Approved";
		}
		
		JButton clearBtn = new JButton("CLEAR");
		GridBagConstraints gbc_clearBtn = new GridBagConstraints();
		gbc_clearBtn.insets = new Insets(0, 0, 5, 0);
		gbc_clearBtn.gridx = 4;
		gbc_clearBtn.gridy = 10;
		itemIDLbl.add(clearBtn, gbc_clearBtn);

		JLabel DORLbl = new JLabel("Registration Date:");
		DORLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_DORLbl = new GridBagConstraints();
		gbc_DORLbl.insets = new Insets(0, 0, 5, 5);
		gbc_DORLbl.gridx = 1;
		gbc_DORLbl.gridy = 11;
		itemIDLbl.add(DORLbl, gbc_DORLbl);

		JTextField DOR = new JTextField();
		DOR.setToolTipText("DD/MM/YYYY");
		GridBagConstraints gbc_DOR = new GridBagConstraints();
		gbc_DOR.gridwidth = 2;
		gbc_DOR.insets = new Insets(0, 0, 5, 5);
		gbc_DOR.fill = GridBagConstraints.HORIZONTAL;
		gbc_DOR.gridx = 2;
		gbc_DOR.gridy = 11;
		itemIDLbl.add(DOR, gbc_DOR);
		DOR.setColumns(10);
		
		JButton exitBtn = new JButton("EXIT");
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GridBagConstraints gbc_exitBtn = new GridBagConstraints();
		gbc_exitBtn.insets = new Insets(0, 0, 5, 0);
		gbc_exitBtn.gridx = 4;
		gbc_exitBtn.gridy = 12;
		itemIDLbl.add(exitBtn, gbc_exitBtn);

		JButton submit = new JButton("SUBMIT");
		submit.setBackground(new Color(34, 139, 34));
		submit.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_submit = new GridBagConstraints();
		gbc_submit.gridwidth = 2;
		gbc_submit.gridheight = 2;
		gbc_submit.fill = GridBagConstraints.BOTH;
		gbc_submit.insets = new Insets(0, 0, 0, 5);
		gbc_submit.gridx = 2;
		gbc_submit.gridy = 13;
		itemIDLbl.add(submit, gbc_submit);
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Random ID = new Random();
					invent.create(itemID.getText(), itemNameTxt.getText(), getCategoryOptSlctd, Status1, DOR.getText(),
							ID.nextInt());
					
					itemIDTxt.setText("");
					itemNameTxt.setText("");
					DOR.setText("");
					categoryComboBox.setSelectedIndex(-1);
					rdbtnApproved.setSelected(false);
					rdbtnDecline.setSelected(false);
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		frame.setVisible(true);
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void update() {

		JPanel panel;
		JTextField updateToTxtField;
		frame = new JFrame("Grizzly's Entertainment - Employees/Update");
		frame.setBounds(100, 100, 519, 526);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 65, 131, 63, 96, 37, 0 };
		gbl_panel.rowHeights = new int[] { 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);


		JLabel UpdateComboBoxLbl = new JLabel("Choose what to Update:");
		UpdateComboBoxLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_UpdateComboBoxLbl = new GridBagConstraints();
		gbc_UpdateComboBoxLbl.fill = GridBagConstraints.HORIZONTAL;
		gbc_UpdateComboBoxLbl.insets = new Insets(0, 0, 5, 5);
		gbc_UpdateComboBoxLbl.gridx = 1;
		gbc_UpdateComboBoxLbl.gridy = 1;
		panel.add(UpdateComboBoxLbl, gbc_UpdateComboBoxLbl);

	

		JComboBox updateComboBox = new JComboBox();
		updateComboBox.setModel(new DefaultComboBoxModel
			(new String[] { "", "itemName", "category", "status", "dateOfRegistration" }));
		GridBagConstraints gbc_updateComboBox = new GridBagConstraints();
		gbc_updateComboBox.gridwidth = 2;
		gbc_updateComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_updateComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_updateComboBox.gridx = 2;
		gbc_updateComboBox.gridy = 1;
		panel.add(updateComboBox, gbc_updateComboBox);
		updateComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = (String) updateComboBox.getSelectedItem();
				switch (s) {
				case "All":
					getComboBoxAction = "All";
					break;

				case "itemName":
					getComboBoxAction = "itemName";
					break;

				case "category":
					getComboBoxAction = "category";
					break;

				case "status":
					getComboBoxAction = "status";
					break;

				case "dateOfRegistration":
					getComboBoxAction = "DOR";
					break;
				}
			}
		});

		JLabel lblNewLabel = new JLabel("Enter Item ID:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setToolTipText("Delete from database");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 2;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		JTextField itemIDTxtField = new JTextField();
		GridBagConstraints gbc_itemIDTxtField = new GridBagConstraints();
		gbc_itemIDTxtField.gridwidth = 2;
		gbc_itemIDTxtField.insets = new Insets(0, 0, 5, 5);
		gbc_itemIDTxtField.fill = GridBagConstraints.HORIZONTAL;
		gbc_itemIDTxtField.gridx = 2;
		gbc_itemIDTxtField.gridy = 2;
		panel.add(itemIDTxtField, gbc_itemIDTxtField);
		itemIDTxtField.setColumns(10);

		JLabel updateToLbl = new JLabel("Update to:");
		updateToLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_updateToLbl = new GridBagConstraints();
		gbc_updateToLbl.insets = new Insets(0, 0, 5, 5);
		gbc_updateToLbl.gridx = 1;
		gbc_updateToLbl.gridy = 3;
		panel.add(updateToLbl, gbc_updateToLbl);

		updateToTxtField = new JTextField();
		GridBagConstraints gbc_updateToTxtField = new GridBagConstraints();
		gbc_updateToTxtField.gridwidth = 2;
		gbc_updateToTxtField.insets = new Insets(0, 0, 5, 5);
		gbc_updateToTxtField.fill = GridBagConstraints.HORIZONTAL;
		gbc_updateToTxtField.gridx = 2;
		gbc_updateToTxtField.gridy = 3;
		panel.add(updateToTxtField, gbc_updateToTxtField);
		updateToTxtField.setColumns(10);

		JButton clearBtn = new JButton("CLEAR");
		GridBagConstraints gbc_clearBtn = new GridBagConstraints();
		gbc_clearBtn.insets = new Insets(0, 0, 5, 5);
		gbc_clearBtn.gridx = 2;
		gbc_clearBtn.gridy = 4;
		panel.add(clearBtn, gbc_clearBtn);
		clearBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				itemIDTxtField.setText("");
				updateToTxtField.setText("");
			}

		});
		
		JButton exit = new JButton("EXIT UPDATE");
		GridBagConstraints gbc_exit = new GridBagConstraints();
		gbc_exit.anchor = GridBagConstraints.NORTHEAST;
		gbc_exit.insets = new Insets(0, 0, 5, 5);
		gbc_exit.gridx = 3;
		gbc_exit.gridy = 4;
		panel.add(exit, gbc_exit);
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				/*
				 * 
				 *  
				 *  
				 *  
				 *  EmployeeLogin window = new EmployeeLogin();
				window.frame.setVisible(true);
				 *  
				 *  
				 *  
				 *  
				 *  
				 *  
				 *  
				 *  
				 *  
				 *  
				 *  
				 *
				 */
			}

		});

		JButton btnCancel = new JButton("CANCEL");
		btnCancel.setToolTipText("Cancle");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCancel.setBackground(new Color(34, 139, 34));
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.gridheight = 2;
		gbc_btnCancel.fill = GridBagConstraints.BOTH;
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 2;
		gbc_btnCancel.gridy = 8;
		panel.add(btnCancel, gbc_btnCancel);
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				
			}
		});
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUpdate.setToolTipText("Update an Item in the database");
		btnUpdate.setBackground(Color.RED);
		btnUpdate.setMnemonic('u');
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.insets = new Insets(0, 0, 0, 5);
		gbc_btnUpdate.gridheight = 2;
		gbc_btnUpdate.fill = GridBagConstraints.BOTH;
		gbc_btnUpdate.gridx = 3;
		gbc_btnUpdate.gridy = 8;
		panel.add(btnUpdate, gbc_btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				switch (getComboBoxAction) {
				case "All":
					System.out.print("God, I Love you!!!!!!");
					break;

				case "itemName":
					invent.updateItemName(itemIDTxtField.getText(), updateToTxtField.getText());
					itemIDTxtField.setText("");
					updateToTxtField.setText("");
					break;

				case "category":
					invent.updateCategory(itemIDTxtField.getText(), updateToTxtField.getText());
					itemIDTxtField.setText("");
					updateToTxtField.setText("");
					break;

				case "status":
					invent.updateStatus(itemIDTxtField.getText(), updateToTxtField.getText());
					itemIDTxtField.setText("");
					updateToTxtField.setText("");
					break;

				case "dateOfRegistration":
					invent.updateDateOfRequirement(itemIDTxtField.getText(), updateToTxtField.getText());
					itemIDTxtField.setText("");
					updateToTxtField.setText("");
					break;
				}
			}
		});
		frame.setVisible(true);
	}

	public void delete() {
		JTextField textField;
		Inventory invent = new Inventory();
		JPanel panel;
		frame = new JFrame("Grizzly's Entertainment - Employees-Delete");
		frame.setBounds(100, 100, 519, 526);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 65, 100, 63, 100, 0, 0 };
		gbl_panel.rowHeights = new int[] { 14, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblNewLabel = new JLabel("Enter Item ID:");
		lblNewLabel.setToolTipText("Delete from database");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);

		JButton btnNewButton_1 = new JButton("CLEAR");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 4;
		gbc_btnNewButton_1.gridy = 1;
		panel.add(btnNewButton_1, gbc_btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
			}

		});

		JButton btnCancel = new JButton("CANCEL");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCancel.setBackground(new Color(34, 139, 34));
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.gridheight = 2;
		gbc_btnCancel.fill = GridBagConstraints.BOTH;
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 5;
		panel.add(btnCancel, gbc_btnCancel);
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				
			}
		});
		JButton btnDelete = new JButton("DELETE");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDelete.setToolTipText("Delete an Item");
		btnDelete.setBackground(Color.RED);
		btnDelete.setMnemonic('d');
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.gridheight = 2;
		gbc_btnDelete.fill = GridBagConstraints.BOTH;
		gbc_btnDelete.insets = new Insets(0, 0, 0, 5);
		gbc_btnDelete.gridx = 3;
		gbc_btnDelete.gridy = 5;
		panel.add(btnDelete, gbc_btnDelete);
		frame.setVisible(true);
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				invent.delete(textField.getText());
				textField.setText("");
			}
		});
	}

}