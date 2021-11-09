package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import java.awt.GridBagConstraints;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingUtilities;

public class EmployeeGUI {

	private JFrame frame;
	private JTable Inventory;
	private JTable tableRequestedItems;

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
		frame = new JFrame();
		frame.setBounds(100, 100, 519, 526);
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
		gbl_panelInventory.rowHeights = new int[]{21, 58, 21, 294, 0};
		gbl_panelInventory.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelInventory.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelInventory.setLayout(gbl_panelInventory);
		String itemType[]= {"All","Lighting","Power","Sound","Staging"};
		
		JLabel lblCategory = new JLabel("Item Category :");
		GridBagConstraints gbc_lblCategory = new GridBagConstraints();
		gbc_lblCategory.anchor = GridBagConstraints.WEST;
		gbc_lblCategory.insets = new Insets(0, 0, 5, 5);
		gbc_lblCategory.gridx = 0;
		gbc_lblCategory.gridy = 0;
		panelInventory.add(lblCategory, gbc_lblCategory);
		
		JComboBox comboBoxItemCategory = new JComboBox(itemType);
		comboBoxItemCategory.setModel(new DefaultComboBoxModel(new String[] {"All", "Lighting", "Power", "Sound", "Staging"}));
		GridBagConstraints gbc_comboBoxItemCategory = new GridBagConstraints();
		gbc_comboBoxItemCategory.fill = GridBagConstraints.BOTH;
		gbc_comboBoxItemCategory.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxItemCategory.gridx = 1;
		gbc_comboBoxItemCategory.gridy = 0;
		panelInventory.add(comboBoxItemCategory, gbc_comboBoxItemCategory);
		comboBoxItemCategory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s=(String) comboBoxItemCategory.getSelectedItem();
				switch (s) {
				case "All":
				//add instructions
					break;
				case "Lighting":
					//add instructions
						break;
				case "Power":
					//add instructions
						break;
				case "Sound":
					//add instructions
						break;		
				case "Staging":
					//add instructions
						break;
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
			}
		});
		
		Inventory = new JTable();
		Inventory.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"ItemName", "ItemID", "Category", "Status", "DateofRequirement"
			}
			
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
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
		
		JButton btnDeleteItem = new JButton("Delete Item");
		GridBagConstraints gbc_btnDeleteItem = new GridBagConstraints();
		gbc_btnDeleteItem.anchor = GridBagConstraints.NORTH;
		gbc_btnDeleteItem.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDeleteItem.gridwidth = 2;
		gbc_btnDeleteItem.gridx = 3;
		gbc_btnDeleteItem.gridy = 3;
		panelInventory.add(btnDeleteItem, gbc_btnDeleteItem);
		
		JPanel panelRequestsHub = new JPanel();
		tabbedPane.addTab("Requests Hub", null, panelRequestsHub, null);
		GridBagLayout gbl_panelRequestsHub = new GridBagLayout();
		gbl_panelRequestsHub.columnWidths = new int[]{82, 131, 43, 93, 103, 0};
		gbl_panelRequestsHub.rowHeights = new int[]{21, 21, 13, 139, 21, 13, 77, 45, 0};
		gbl_panelRequestsHub.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelRequestsHub.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		tableRequestedItems.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null},
				},
				new String[] {
					"ItemName", "ItemID", "Category", "Status"
				}
				
			) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
				};
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
		
		//ButtonGroup "approval" moved once, it'll probably move again. Just check if it is above the radioButtons
				ButtonGroup approval=new ButtonGroup();
		
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
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});
	}
}
