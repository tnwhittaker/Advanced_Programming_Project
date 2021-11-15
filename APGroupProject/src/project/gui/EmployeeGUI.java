package project.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import java.awt.GridBagConstraints;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Random;

import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JInternalFrame;

import inventory.Inventory;
import project.connectionFiles.DBConnectorFactory;

public class EmployeeGUI {

	JFrame frame;
	private JTable tableRequestedItems;
	private static Connection connection=null;
	private Statement stmt=null;
	private ResultSet result=null;
	private JTable table;
	
	private String getComboBoxAction;
	private String getCategoryOptSlctd;
	private String Status1 = "";
	private JTable Inventory;
	private Inventory invent = new Inventory();
    private static final Logger LOGGER = LogManager.getLogger(EmployeeGUI.class);

	
	
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

	
	public EmployeeGUI() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame("Grizzly's Entertainment - Employees Dashboard");
		frame.setBounds(100, 200, 871, 520);
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
		JPanel panelInventory = new JPanel();
		tabbedPane.addTab("Inventory", null, panelInventory, null);
		GridBagLayout gbl_panelInventory = new GridBagLayout();
		gbl_panelInventory.columnWidths = new int[]{72, 106, 136, 103, 28, 0};
		gbl_panelInventory.rowHeights = new int[]{21, 58, 21, 294, 0, 0};
		gbl_panelInventory.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelInventory.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panelInventory.setLayout(gbl_panelInventory);
		String itemType[]= {"All","Lighting","Power","Sound","Staging"};
		JLabel lblCategory = new JLabel("Item Category :");
		GridBagConstraints gbc_lblCategory = new GridBagConstraints();
		gbc_lblCategory.anchor = GridBagConstraints.WEST;
		gbc_lblCategory.insets = new Insets(0, 0, 5, 5);
		gbc_lblCategory.gridx = 0;
		gbc_lblCategory.gridy = 0;
		panelInventory.add(lblCategory, gbc_lblCategory);
		
		
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
		
		
		JButton btnNewItem = new JButton("New Item");
		GridBagConstraints gbc_btnNewItem = new GridBagConstraints();
		gbc_btnNewItem.anchor = GridBagConstraints.SOUTH;
		gbc_btnNewItem.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewItem.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewItem.gridwidth = 2;
		gbc_btnNewItem.gridx  = 3;
		gbc_btnNewItem.gridy = 1;
		panelInventory.add(btnNewItem, gbc_btnNewItem);
		
		btnNewItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddNewItem();
			}
		});
		
		GridBagConstraints gbc_btnUpdateItem = new GridBagConstraints();
		gbc_btnUpdateItem.anchor = GridBagConstraints.NORTH;
		gbc_btnUpdateItem.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnUpdateItem.insets = new Insets(0, 0, 5, 0);
		gbc_btnUpdateItem.gridwidth = 2;
		gbc_btnUpdateItem.gridx = 3;
		gbc_btnUpdateItem.gridy = 2;
		panelInventory.add(btnUpdateItem, gbc_btnUpdateItem);
		
		
		
		JButton btnDeleteItem = new JButton("Delete Item");
		GridBagConstraints gbc_btnDeleteItem = new GridBagConstraints();
		gbc_btnDeleteItem.insets = new Insets(0, 0, 5, 0);
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
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.gridheight = 5;
		gbc_scrollPane.anchor = GridBagConstraints.NORTH;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		panelInventory.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		table.setModel(new DefaultTableModel(
				new Object[][] {
					
				},
				new String[] {
					"ID","Name", "Category", "Status"
				}
			));

		
		
			
		JComboBox comboBoxItemCategory = new JComboBox(itemType);
		comboBoxItemCategory.setModel(new DefaultComboBoxModel(new String[] {"All", "Lighting", "Power", "Sound", "Staging"}));
		GridBagConstraints gbc_comboBoxItemCategory = new GridBagConstraints();
		gbc_comboBoxItemCategory.fill = GridBagConstraints.BOTH;
		gbc_comboBoxItemCategory.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxItemCategory.gridx = 1;
		gbc_comboBoxItemCategory.gridy = 0;
		panelInventory.add(comboBoxItemCategory, gbc_comboBoxItemCategory);
		table.setModel(new DefaultTableModel(null,new String[] {"ID","Name", "Category", "Status", "Cost"}));
		
		getAllEquipment(table);
		comboBoxItemCategory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s=(String) comboBoxItemCategory.getSelectedItem();
				switch (s) {
				case "All":
					table.setModel(new DefaultTableModel(null,new String[] {"ID","Name", "Category", "Status","Cost"}));
					getAllEquipment(table);
					break;
				case "Lighting":
					table.setModel(new DefaultTableModel(null,new String[] {"ID","Name", "Status","Cost"}));
					getEquipmentByCategory(table, s);
						break;
				case "Power":
					table.setModel(new DefaultTableModel(null,new String[] {"ID","Name", "Status","Cost"}));
					getEquipmentByCategory(table, s);
						break;
				case "Sound":
					table.setModel(new DefaultTableModel(null,new String[] {"ID","Name", "Status","Cost"}));
					getEquipmentByCategory(table, s);
						break;		
				case "Staging":
					table.setModel(new DefaultTableModel(null,new String[] {"ID","Name", "Status","Cost"}));
					getEquipmentByCategory(table, s);
						break;
				}
			}
		});	

		btnUpdateItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update(table);
			}
		});
		
		JPanel panelRequestsHub = new JPanel();
		tabbedPane.addTab("Requests Hub", null, panelRequestsHub, null);
		GridBagLayout gbl_panelRequestsHub = new GridBagLayout();
		gbl_panelRequestsHub.columnWidths = new int[]{82, 131, 43, 93, 103, 0};
		gbl_panelRequestsHub.rowHeights = new int[]{21, 21, 13, 139, 21, 13, 77, 45, 0};
		gbl_panelRequestsHub.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelRequestsHub.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelRequestsHub.setLayout(gbl_panelRequestsHub);

		JScrollPane requestScrollPane = new JScrollPane();
		GridBagConstraints gbc_sequestScrollPane = new GridBagConstraints();
		gbc_sequestScrollPane.gridwidth = 3;
		gbc_sequestScrollPane.gridheight = 4;
		gbc_sequestScrollPane.anchor = GridBagConstraints.NORTH;
		gbc_sequestScrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_sequestScrollPane.fill = GridBagConstraints.BOTH;
		gbc_sequestScrollPane.gridx = 0;
		gbc_sequestScrollPane.gridy = 1;
		panelInventory.add(requestScrollPane, gbc_sequestScrollPane);
		
		JTable requestTable = new JTable();
		requestTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		requestScrollPane.setViewportView(requestTable);
		
		requestTable.setModel(new DefaultTableModel(
				new Object[][] {
					
				},
				new String[] {
					"Equipment Name", "Category", "Date", "Cost", "Customer", "Status"
				}
			));

		requestTable.setModel(new DefaultTableModel(null,new String[] {"Equipment Name", "Category", "Date", "Cost", "Customer", "Status"}));
		getAllRequests(requestTable);
		
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
		
		GridBagConstraints gbc_tableRequestedItems = new GridBagConstraints();
		gbc_tableRequestedItems.gridheight = 7;
		gbc_tableRequestedItems.gridwidth = 7;
		gbc_tableRequestedItems.insets = new Insets(0, 0, 5, 5);
		gbc_tableRequestedItems.fill = GridBagConstraints.BOTH;
		gbc_tableRequestedItems.gridx = 0;
		gbc_tableRequestedItems.gridy = 1;
		panelRequestsHub.add(requestScrollPane, gbc_tableRequestedItems);	
		

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		menuBar.add(exitMenuItem);
		
		JMenuItem mntmLogOut = new JMenuItem("Log Out");
		menuBar.add(mntmLogOut);
		exitMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		EmployeeGUI window = new EmployeeGUI();
		window.frame.setVisible(b);
	}
	
	public void getEquipmentByCategory(JTable table, String category) {
		try {  
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			connection= DBConnectorFactory.getDatabaseConnection();
			stmt= connection.createStatement();
			String quote="'";
			String cat= quote+category+quote;
			String catID= "SELECT id FROM category WHERE name="+cat;
			ResultSet catIdResult = stmt.executeQuery(catID);
			catIdResult.next();
			String searchSQL= "SELECT * FROM equipment WHERE category_id="+catIdResult.getInt("id")+"";
			
			result= stmt.executeQuery(searchSQL);
			while(result.next()) {
				String ID= result.getString("id");
				String Name= result.getString("name"); 
				String Cost = result.getString("cost");
				if(result.getInt("availability") == 1) {
					String Availability = "Available";
					String jtbledata[]= {ID, Name,Availability,Cost};
					DefaultTableModel tblModel= (DefaultTableModel)table.getModel();
					tblModel.addRow(jtbledata);
				}else {
					String Availability = "Unavailable";
					String jtbledata[]= {ID, Name,Availability,Cost};
					DefaultTableModel tblModel= (DefaultTableModel)table.getModel();
					tblModel.addRow(jtbledata);
				}	
				
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	public void getAllEquipment(JTable table) {
		try {  
			
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			connection= DBConnectorFactory.getDatabaseConnection();
			stmt= connection.createStatement();
			String searchSQL= "SELECT e.id, e.name, c.name, e.availability, e.cost FROM category as c INNER JOIN equipment as e ON e.category_id = c.id";
			
			result= stmt.executeQuery(searchSQL);
			while(result.next()) {
				String ID= result.getString("id");
				String Name= result.getString("e.name"); 
				String Category = result.getString("c.name");
				String Cost = result.getString("cost");
				if(result.getInt("availability") == 1) {
					String Availability = "Available";
					String jtbledata[]= {ID, Name,Category,Availability,Cost};
					DefaultTableModel tblModel= (DefaultTableModel)table.getModel();
					tblModel.addRow(jtbledata);
				}else {
					String Availability = "Unavailable";
					String jtbledata[]= {ID, Name,Category,Availability,Cost};
					DefaultTableModel tblModel= (DefaultTableModel)table.getModel();
					tblModel.addRow(jtbledata);
				}	
				
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	public void getAllRequests(JTable table){
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//connects to the database
		connection = DBConnectorFactory.getDatabaseConnection();
				
		//Three statements to effectively carry out our queries need to display readable information to the use
		try {
			stmt = connection.createStatement();
			Statement transtmt = connection.createStatement();
			Statement cat_stmt = connection.createStatement();
			Statement user_stmt = connection.createStatement();

			String allTrans = "SELECT * FROM transaction";//Query to get transactions of currently signed in use
			result = stmt.executeQuery(allTrans);//execte query with first statement

			while(result.next()) {//continue operation if we didnt get a null response from query
				String equipMoreInfo = "Select name,category_id,cost from equipment where id="+result.getInt("equipment_id");//query to retrieve equipment name and category id for further querying
				ResultSet equipMoreResult = transtmt.executeQuery(equipMoreInfo);//execute above query to retrieve equipment info and category Id for next query
				while(equipMoreResult.next()) {//continue operation
					String categoryInfo = "Select name from category where id="+equipMoreResult.getInt("category_id");//uses category id to retrieve category name
					ResultSet moreCategoryInfo = cat_stmt.executeQuery(categoryInfo);//execute query with third and final statement variable declared
					moreCategoryInfo.next();//continue operation

					String userInfo = "Select customer_id from users where id=" + result.getInt("customer_id");
					ResultSet user = user_stmt.executeQuery(userInfo);
					user.next();

					SimpleDateFormat simpDate = new SimpleDateFormat("MMM dd, yyyy");//this lines helps to format date shown
					Object[] tableData = {
							equipMoreResult.getString("name"),
							moreCategoryInfo.getString("name"),
							simpDate.format(result.getDate("date")),
							equipMoreResult.getFloat("cost"),
							user.getString("customer_id"),
							result.getInt("approve") == 0 ? "Unapproved" : "Approved",
							
					};//displays and formats rows that will be inserted in the table
					DefaultTableModel tblModel= (DefaultTableModel)table.getModel();
					tblModel.addRow(tableData);//add row wid information to table
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
public void AddNewItem() {
		
		JFrame addNewPanel = new JFrame("Grizzly's Entertainment - Employees/Add new Item");
		addNewPanel.setBounds(100, 100, 519, 526);
		addNewPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel itemIDLbl = new JPanel();
		addNewPanel.getContentPane().add(itemIDLbl, BorderLayout.NORTH);
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

		ButtonGroup status = new ButtonGroup();

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
				new DefaultComboBoxModel(new String[] {"Lighting", "Power", "Sound", "Staging" }));
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

		JRadioButton rdbtnDecline = new JRadioButton("Available");
		rdbtnDecline.setActionCommand("Available");
		GridBagConstraints gbc_rdbtnDecline = new GridBagConstraints();
		gbc_rdbtnDecline.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnDecline.gridx = 2;
		gbc_rdbtnDecline.gridy = 9;
		itemIDLbl.add(rdbtnDecline, gbc_rdbtnDecline);
		status.add(rdbtnDecline);
		if (rdbtnDecline.isSelected()) {
			Status1 = "Available";
		}

		JRadioButton rdbtnApproved = new JRadioButton("Unavailable");
		rdbtnApproved.setActionCommand("Unavailable");
		GridBagConstraints gbc_rdbtnApproved = new GridBagConstraints();
		gbc_rdbtnApproved.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnApproved.gridx = 3;
		gbc_rdbtnApproved.gridy = 9;
		itemIDLbl.add(rdbtnApproved, gbc_rdbtnApproved);
		status.add(rdbtnApproved);
		if (rdbtnApproved.isSelected()) {
			Status1 = "Unavailable";
		}
		
		JButton clearBtn = new JButton("CLEAR");
		GridBagConstraints gbc_clearBtn = new GridBagConstraints();
		gbc_clearBtn.insets = new Insets(0, 0, 5, 0);
		gbc_clearBtn.gridx = 4;
		gbc_clearBtn.gridy = 10;
		itemIDLbl.add(clearBtn, gbc_clearBtn);

		JLabel DORLbl = new JLabel("Equipment Cost:");
		DORLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_DORLbl = new GridBagConstraints();
		gbc_DORLbl.insets = new Insets(0, 0, 5, 5);
		gbc_DORLbl.gridx = 1;
		gbc_DORLbl.gridy = 11;
		itemIDLbl.add(DORLbl, gbc_DORLbl);

		
		SpinnerModel value =  new SpinnerNumberModel((double) 0.0,null,null,(double) 0.1);//minimum value  
		JSpinner spinner = new JSpinner( value);  
                
		
		GridBagConstraints gbc_DOR = new GridBagConstraints();
		gbc_DOR.gridwidth = 2;
		gbc_DOR.insets = new Insets(0, 0, 5, 5);
		gbc_DOR.fill = GridBagConstraints.HORIZONTAL;
		gbc_DOR.gridx = 2;
		gbc_DOR.gridy = 11;
		itemIDLbl.add(spinner, gbc_DOR);
		
		
		JButton exitBtn = new JButton("EXIT");
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addNewPanel.setVisible(false);
				frame.setVisible(true);
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
					connection= DBConnectorFactory.getDatabaseConnection();
					String categoryIdQuery = "SELECT id FROM category where name='"+(String) categoryComboBox.getSelectedItem()+"'";//get id of currently signed in user
					Statement stmt1 = connection.createStatement();
					ResultSet cid = stmt1.executeQuery(categoryIdQuery);
					cid.next();

					String statusQuery = "SELECT id FROM rental_status where status='"+status.getSelection().getActionCommand()+"'";//get id of currently signed in user
					Statement stmt2 = connection.createStatement();
					ResultSet sid = stmt2.executeQuery(statusQuery);
					sid.next();

					String Query_String = "INSERT INTO equipment(name,category_id,availability,cost) VALUES ('"+itemNameTxt.getText()+"','"+cid.getInt("id")+"','" + sid.getInt("id")+"','"+ (double) spinner.getValue()+ "')";
					stmt.executeLargeUpdate(Query_String);

					JOptionPane.showMessageDialog(addNewPanel, "Equipment record has been stored");
					
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		addNewPanel.setVisible(true);
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void update(JTable table) {

		JPanel update;
		JFrame updatePanel = new JFrame("Grizzly's Entertainment - Employees/Update");
		updatePanel.setBounds(100, 100, 519, 526);
		updatePanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		update = new JPanel();
		updatePanel.getContentPane().add(update, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 65, 131, 63, 96, 37, 0 };
		gbl_panel.rowHeights = new int[] { 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		update.setLayout(gbl_panel);

		JLabel itemName = new JLabel("Item Name:");
		itemName.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_itemName = new GridBagConstraints();
		gbc_itemName.gridy = 5;
		gbc_itemName.insets = new Insets(0, 0, 5, 5);
		gbc_itemName.gridx = 1;
		update.add(itemName, gbc_itemName);

		JTextField itemNameTxt = new JTextField();
		itemNameTxt.setToolTipText("Add the name of the item");
		itemNameTxt.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_itemNameTxt = new GridBagConstraints();
		gbc_itemNameTxt.gridy = 5;
		gbc_itemNameTxt.gridwidth = 2;
		gbc_itemNameTxt.fill = GridBagConstraints.BOTH;
		gbc_itemNameTxt.insets = new Insets(0, 0, 5, 5);
		gbc_itemNameTxt.gridx = 2;
		itemNameTxt.setText((String) table.getValueAt(table.getSelectedRow() , 1));//set equipment name to what it was in the table
		update.add(itemNameTxt, gbc_itemNameTxt);

		JLabel categorylbl = new JLabel("Category:");
		categorylbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_categorylbl = new GridBagConstraints();
		gbc_categorylbl.insets = new Insets(0, 0, 5, 5);
		gbc_categorylbl.gridx = 1;
		gbc_categorylbl.gridy = 7;
		update.add(categorylbl, gbc_categorylbl);

		JComboBox categoryComboBox = new JComboBox();
		categoryComboBox.setModel(
				new DefaultComboBoxModel(new String[] { "Lighting", "Power", "Sound", "Staging" }));
		categoryComboBox.setSelectedItem((String) table.getValueAt(table.getSelectedRow() , 2));//set already selected category to what it was in the table
		GridBagConstraints gbc_categoryComboBox = new GridBagConstraints();
		gbc_categoryComboBox.gridwidth = 2;
		gbc_categoryComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_categoryComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_categoryComboBox.gridx = 2;
		gbc_categoryComboBox.gridy = 7;
		update.add(categoryComboBox, gbc_categoryComboBox);
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

		ButtonGroup status = new ButtonGroup();

		JLabel statusLbl = new JLabel("Status");
		statusLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_statusLbl = new GridBagConstraints();
		gbc_statusLbl.insets = new Insets(0, 0, 5, 5);
		gbc_statusLbl.gridx = 1;
		gbc_statusLbl.gridy = 9;
		update.add(statusLbl, gbc_statusLbl);

		JRadioButton rdbtnDecline = new JRadioButton("Available");
		rdbtnDecline.setActionCommand("Available");
		GridBagConstraints gbc_rdbtnDecline = new GridBagConstraints();
		gbc_rdbtnDecline.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnDecline.gridx = 2;
		gbc_rdbtnDecline.gridy = 9;
		update.add(rdbtnDecline, gbc_rdbtnDecline);
		status.add(rdbtnDecline);
		if (rdbtnDecline.isSelected()) {
			Status1 = "Available";
		}

		JRadioButton rdbtnApproved = new JRadioButton("Unavailable");
		rdbtnApproved.setActionCommand("Unavailable");
		GridBagConstraints gbc_rdbtnApproved = new GridBagConstraints();
		gbc_rdbtnApproved.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnApproved.gridx = 3;
		gbc_rdbtnApproved.gridy = 9;
		update.add(rdbtnApproved, gbc_rdbtnApproved);
		status.add(rdbtnApproved);
		if (rdbtnApproved.isSelected()) {
			Status1 = "Unavailable";
		}

		if(table.getValueAt(table.getSelectedRow() , 3) == "Available"){
			rdbtnDecline.setSelected(true);
		}else{
			rdbtnApproved.setSelected(true);
		}
		
		JLabel DORLbl = new JLabel("Equipment Cost:");
		DORLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_DORLbl = new GridBagConstraints();
		gbc_DORLbl.insets = new Insets(0, 0, 5, 5);
		gbc_DORLbl.gridx = 1;
		gbc_DORLbl.gridy = 90;
		update.add(DORLbl, gbc_DORLbl);

		
		SpinnerModel value =  new SpinnerNumberModel((double) 0.0,null,null,(double) 0.1);//minimum value  
		JSpinner spinner = new JSpinner( value);  
         
		//set Values to Values from the Row selected in the previous frame
		if((String) table.getValueAt(table.getSelectedRow() , 4) == null){
			value.setValue(0.0);
		}else{
			value.setValue(Double.parseDouble((String) table.getValueAt(table.getSelectedRow() , 4)));
		}
		

		GridBagConstraints gbc_DOR = new GridBagConstraints();
		gbc_DOR.gridwidth = 2;
		gbc_DOR.insets = new Insets(0, 0, 5, 5);
		gbc_DOR.fill = GridBagConstraints.HORIZONTAL;
		gbc_DOR.gridx = 2;
		gbc_DOR.gridy = 90;
		update.add(spinner, gbc_DOR);

		JButton btnCancel = new JButton("CANCEL");
		btnCancel.setToolTipText("Cancle");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCancel.setBackground(new Color(34, 139, 34));
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.gridheight = 2;
		gbc_btnCancel.fill = GridBagConstraints.BOTH;
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 2;
		gbc_btnCancel.gridy = 150;
		update.add(btnCancel, gbc_btnCancel);
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				updatePanel.setVisible(false);
				frame.setVisible(true);
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
		gbc_btnUpdate.gridy = 150;
		update.add(btnUpdate, gbc_btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					connection= DBConnectorFactory.getDatabaseConnection();
					String categoryIdQuery = "SELECT id FROM category where name='"+(String) categoryComboBox.getSelectedItem()+"'";//get id of currently signed in user
					Statement stmt1 = connection.createStatement();
					ResultSet cid = stmt1.executeQuery(categoryIdQuery);
					cid.next();

					String statusQuery = "SELECT id FROM rental_status where status='"+status.getSelection().getActionCommand()+"'";//get id of currently signed in user
					Statement stmt2 = connection.createStatement();
					ResultSet sid = stmt2.executeQuery(statusQuery);
					sid.next();

					//String Query_String = "UPDATE equipment SET(name,category_id,availability,cost) WHERE ('"+itemNameTxt.getText()+"','"+cid.getInt("id")+"','" + sid.getInt("id")+"','"+ (double) spinner.getValue()+ "')";
					String Update_Query = "UPDATE equipment SET name = '" + itemNameTxt.getText() + "', category_id='"+cid.getInt("id")+"',availability='" + sid.getInt("id")+"',cost='"+(double) spinner.getValue()+ "' Where id='"+(String) table.getValueAt(table.getSelectedRow() , 0)+"'";
					stmt.executeLargeUpdate(Update_Query);

					JOptionPane.showMessageDialog(update, "Equipment record has been updated");
					
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		updatePanel.setVisible(true);
	}

	public void delete() {
		JTextField textField;
		JPanel deletePanel;
		JFrame deleteFrame = new JFrame("Grizzly's Entertainment - Employees-Delete");
		deleteFrame.setBounds(100, 100, 519, 526);
		deleteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		deletePanel = new JPanel();
		deleteFrame.getContentPane().add(deletePanel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 65, 100, 63, 100, 0, 0 };
		gbl_panel.rowHeights = new int[] { 14, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		deletePanel.setLayout(gbl_panel);

		JLabel lblNewLabel = new JLabel("Enter Item ID:");
		lblNewLabel.setToolTipText("Delete from database");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		deletePanel.add(lblNewLabel, gbc_lblNewLabel);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		deletePanel.add(textField, gbc_textField);
		textField.setColumns(10);

		JButton btnNewButton_1 = new JButton("CLEAR");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 4;
		gbc_btnNewButton_1.gridy = 1;
		deletePanel.add(btnNewButton_1, gbc_btnNewButton_1);
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
		deletePanel.add(btnCancel, gbc_btnCancel);
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				deletePanel.setVisible(false);
				frame.setVisible(true);
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
		deletePanel.add(btnDelete, gbc_btnDelete);
		deleteFrame.setVisible(true);
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				deletePanel.setVisible(false);
				frame.setVisible(true);
			}
		});
	}
}

