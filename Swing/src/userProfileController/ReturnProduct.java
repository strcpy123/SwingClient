package userProfileController;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import api.Utility;
import database.DB;
import model.Product;
import model.Purchase_History;

/**
 * Class for Return Product
 */
public class ReturnProduct extends JFrame implements ActionListener {

	// Variable/Reference declaration
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_1_1;
	private JScrollPane scrollPane;
	private Object rowData[];
	private DB db;
	private List<Purchase_History> billingPurchaseHistory;
	private Purchase_History purchaseHistoryofOneItemFromDB;
	private JLabel lblNewLabel_2_4;
	private JTextField textField_5;
	private int selectedRowIndex;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JTextField textField_6;
	private float total = 0;
	@SuppressWarnings("unused")
	private int billingId = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReturnProduct frame = new ReturnProduct();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ReturnProduct() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(30, 30, 910, 550);
		setLocationRelativeTo(null); 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// Database Object
		db = new DB();

		JLabel lblNewLabel = new JLabel("Return Order");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(345, 10, 106, 53);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Enter Billing Id:-");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(89, 60, 151, 31);
		contentPane.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(223, 60, 204, 31);
		contentPane.add(textField);
		textField.setColumns(10);

		btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(463, 63, 102, 28);
		contentPane.add(btnNewButton);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(400, 165, 450, 246);
		contentPane.add(scrollPane);
		// Object array is used for dynamic table row.
		rowData = new Object[10];

//		table = new JTable();
//		table.getModel().isCellEditable(selectedRowIndex, 0);
////		table.setModel(new DefaultTableModel(new Object[][] {},
////				new String[] { "Product_Id", "Product Cost", "Quanity", "Total Cost", "Billing_Id" }));
//		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.ORANGE));
		panel.setBounds(20, 131, 337, 349);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Product Id:-");
		lblNewLabel_2.setBounds(44, 22, 67, 28);
		panel.add(lblNewLabel_2);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(170, 27, 96, 19);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_2_1 = new JLabel("Product Cost:-");
		lblNewLabel_2_1.setBounds(44, 75, 90, 28);
		panel.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_2 = new JLabel("Quantity:-");
		lblNewLabel_2_2.setBounds(44, 124, 67, 28);
		panel.add(lblNewLabel_2_2);

		JLabel lblNewLabel_2_3 = new JLabel("Total Cost:-");
		lblNewLabel_2_3.setBounds(44, 176, 67, 28);
		panel.add(lblNewLabel_2_3);

		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(170, 80, 96, 19);
		panel.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(170, 129, 96, 19);
		panel.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(170, 181, 96, 19);
		panel.add(textField_4);

		btnNewButton_1 = new JButton("Update");
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setBounds(26, 268, 85, 21);
		panel.add(btnNewButton_1);

		btnNewButton_1_1 = new JButton("Delete");
		btnNewButton_1_1.setEnabled(false);
		btnNewButton_1_1.addActionListener(this);
		btnNewButton_1_1.setBounds(218, 268, 85, 21);
		panel.add(btnNewButton_1_1);

		lblNewLabel_2_4 = new JLabel("Billing ID:-");
		lblNewLabel_2_4.setBounds(44, 214, 67, 28);
		panel.add(lblNewLabel_2_4);

		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(170, 219, 96, 19);
		panel.add(textField_5);

		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(112, 314, 130, 25);
		panel.add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setBounds(223, 101, 580, 31);
		contentPane.add(lblNewLabel_4);

		lblNewLabel_5 = new JLabel("Total :-");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(550, 438, 92, 31);
		contentPane.add(lblNewLabel_5);

		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setBounds(619, 433, 233, 47);
		contentPane.add(textField_6);
		textField_6.setColumns(10);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Product search operation
		if (e.getActionCommand() == "Search") {
			table = new JTable();
			textField_1.setText("");
			textField_2.setText("");
			textField_3.setText("");
			textField_4.setText("");
			textField_5.setText("");

			if (textField.getText().isEmpty() || textField.getText().isEmpty() || textField.getText().length() == 0) {
				lblNewLabel_4.setText("Please Enter the Billing Id");
			}
			if (textField.getText().length() > 0) {
				int num = this.IsInteger(textField.getText());
//				System.out.println(total);
				if (num <= 0) {
					lblNewLabel_4.setText("Please Enter Correct Billing Number");
				} else {
					billingPurchaseHistory = db.getPurchaseDetails(num);
					JSONObject reqObj = prepareReqJsonObj(String.valueOf(num));

//					JSONObject reqObj = checkMethod(user);
					String reqString = reqObj.toString();
					String APIUrl = "http://localhost:9090/findProduct?id="+num;

					String response = Utility.excutePost(APIUrl, reqString);

					System.out.println(" reqObj" + reqObj);
					System.out.println("reqString" + reqString);
					System.out.println("response" + response);
//					Gson gson = new Gson();
//					product = gson.fromJson(response, Product.class);
					if (billingPurchaseHistory.size() == 0) {
						lblNewLabel_4.setText("No Records Found in the Database");
					} else {
						lblNewLabel_4.setText("");

						table.setModel(new DefaultTableModel(new Object[][] {},
								new String[] { "Product_Id", "Product Cost", "Quanity", "Total Cost", "Billing_Id" }));
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						billingId = billingPurchaseHistory.get(0).getId();
						total = 0;
						for (Purchase_History result : billingPurchaseHistory) {
							rowData[0] = result.getProduct_Id();
							rowData[1] = result.getProduct_cost();
							rowData[2] = result.getQuantity();
							rowData[3] = result.getTotal_cost();
							rowData[4] = result.getId();
							model.addRow(rowData);
							total += result.getTotal_cost();
						}
						table.addMouseListener(new MouseAdapter() {
							// When a product need to be alter, then selected product will be added to
							// Editable Panel
							@Override
							public void mouseClicked(MouseEvent e) {
								btnNewButton_1.setEnabled(true);
								btnNewButton_1_1.setEnabled(true);
								selectedRowIndex = table.getSelectedRow();
								TableModel model = table.getModel();
//								table.getModel().isCellEditable(selectedRowIndex, 0);
								String productId = String.valueOf(model.getValueAt(selectedRowIndex, 0));
								String productCost = String.valueOf(model.getValueAt(selectedRowIndex, 1));
								String quantity = String.valueOf(model.getValueAt(selectedRowIndex, 2));
								String totalCost = "" + model.getValueAt(selectedRowIndex, 3);
								String billingId = String.valueOf(model.getValueAt(selectedRowIndex, 4));
								textField_1.setText(productId);
								textField_2.setText(productCost);
								textField_3.setText(quantity);
								textField_4.setText(totalCost);
								textField_5.setText(billingId);
								textField_3.setEditable(true);
								lblNewLabel_3.setText("");
							}
						});
						scrollPane.setViewportView(table);
						textField_6.setText(String.valueOf(total));
						total = 0;
					}

				}
			}
		}
		// Here we are updating the product according to the user requirements
		if (e.getActionCommand() == "Update") {
			if (!(textField_1.getText().isEmpty()) && !(textField_5.getText().isEmpty())) {
				System.out.println("Update");
				total = 0;
				int quantity = this.IsInteger(textField_3.getText());
				float cost = Float.parseFloat(textField_2.getText()) * quantity;
				if (quantity <= 0) {
					lblNewLabel_3.setText("Please Enter Some Valid Number");
				} else {
					int status = db.updatePurchaseDetails(quantity, cost, Integer.parseInt(textField_1.getText()),
							Integer.parseInt(textField_5.getText()));
					purchaseHistoryofOneItemFromDB = db.getPurchaseHistoryOfOneItem(
							Integer.parseInt(textField_1.getText()), Integer.parseInt(textField_5.getText()));
					table.getModel().setValueAt(purchaseHistoryofOneItemFromDB.getQuantity(), selectedRowIndex, 2);
					System.out.println("Quantity:" + purchaseHistoryofOneItemFromDB.getQuantity());
					table.getModel().setValueAt(purchaseHistoryofOneItemFromDB.getTotal_cost(), selectedRowIndex, 3);
					System.out.println("Total Cost:" + purchaseHistoryofOneItemFromDB.getTotal_cost());
					textField_4.setText(String.valueOf(cost));
					total = 0;
					List<Purchase_History> updatedBillTotal = db
							.getPurchaseDetails(Integer.parseInt(textField_5.getText()));
					for (int i = 0; i < updatedBillTotal.size(); i++) {
						total += updatedBillTotal.get(i).getTotal_cost();
					}
					if (status > 0) {
						lblNewLabel_3.setText("Product Updated");
					}
					textField_6.setText(String.valueOf(total));
				}

			} else {
				lblNewLabel_3.setText("Please Choose Some Product");
			}

		}
		// Here we are deleting the product according to the user requirements
		if (e.getActionCommand() == "Delete") {
			if (!(textField_1.getText().isEmpty()) && !(textField_5.getText().isEmpty())) {
				int productId = Integer.parseInt(textField_1.getText());
				int billingId = Integer.parseInt(textField_5.getText());
				int deletedRow = db.deleteProductFromHistory(productId, billingId);
				((DefaultTableModel) table.getModel()).removeRow(selectedRowIndex);
				if (deletedRow > 0) {
					lblNewLabel_3.setText("Product Deleted");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
					textField_5.setText("");
				}
				// For security, Here these buttons are disabled
				btnNewButton_1.setEnabled(false);
				textField_3.setEditable(false);
				btnNewButton_1_1.setEnabled(false);
				total = 0;
				List<Purchase_History> updatedBillTotal = db
						.getPurchaseDetails(billingId);
				for (int i = 0; i < updatedBillTotal.size(); i++) {
					total += updatedBillTotal.get(i).getTotal_cost();
				}
				textField_6.setText(String.valueOf(total));
			} else {
				lblNewLabel_3.setText("Please Choose Some Product");
			}
		}
	}

	public int IsInteger(String number) {
		int num = 0;
		try {
			num = Integer.parseInt(number);
		} catch (NumberFormatException ex) {
			return 0;
		}
		return num;
	}
	
	public JSONObject prepareReqJsonObj(String s1) {
		JSONObject jsonobj = new JSONObject();
		try {
			jsonobj.put("productID", s1);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonobj;
	}
}
