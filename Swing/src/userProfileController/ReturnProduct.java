package userProfileController;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import api.Utility;
import loginController.LoginDemo;
import model.Product;
import model.Purchase_History;
import javax.swing.SwingConstants;

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
	private List<Purchase_History> ppl2;
	private JTextField textField_7;
	private JLabel lblNewLabel_6;
	private JPanel panel_1;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_4_2;

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
		setBounds(50, 50, 800, 800);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Return Order");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(314, 64, 106, 53);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Enter Billing Id:-");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(92, 125, 151, 31);
		contentPane.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(253, 127, 204, 31);
		contentPane.add(textField);
		textField.setColumns(10);

		btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(519, 128, 102, 28);
		contentPane.add(btnNewButton);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(361, 182, 415, 246);
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
		panel.setBounds(14, 182, 337, 349);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Product Id:-");
		lblNewLabel_2.setBounds(44, 40, 67, 28);
		panel.add(lblNewLabel_2);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(170, 45, 96, 19);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_2_1 = new JLabel("Product Cost:-");
		lblNewLabel_2_1.setBounds(44, 98, 90, 28);
		panel.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_2 = new JLabel("Quantity:-");
		lblNewLabel_2_2.setBounds(44, 138, 67, 28);
		panel.add(lblNewLabel_2_2);

		JLabel lblNewLabel_2_3 = new JLabel("Total Cost:-");
		lblNewLabel_2_3.setBounds(44, 176, 67, 28);
		panel.add(lblNewLabel_2_3);

		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(170, 103, 96, 19);
		panel.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(170, 143, 96, 19);
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
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(81, 300, 215, 25);
		panel.add(lblNewLabel_3);

		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setBounds(170, 74, 96, 19);
		panel.add(textField_7);
		textField_7.setColumns(10);

		lblNewLabel_6 = new JLabel("Product Name");
		lblNewLabel_6.setBounds(44, 75, 102, 13);
		panel.add(lblNewLabel_6);

		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(202, 566, 394, 31);
		contentPane.add(lblNewLabel_4);

		lblNewLabel_5 = new JLabel("Total :-");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(463, 449, 92, 31);
		contentPane.add(lblNewLabel_5);

		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setBounds(543, 443, 233, 47);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(0, 0, 786, 50);
		contentPane.add(panel_1);
		
		lblNewLabel_7 = new JLabel("Order");
		lblNewLabel_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Inventory();
				dispose();
			}
		});
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_7.setBounds(622, 10, 62, 30);
		panel_1.add(lblNewLabel_7);
		
		lblNewLabel_4_2 = new JLabel("Logout");
		lblNewLabel_4_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new LoginDemo();
				dispose();
			}
		});
		lblNewLabel_4_2.setForeground(Color.WHITE);
		lblNewLabel_4_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4_2.setBounds(694, 10, 62, 30);
		panel_1.add(lblNewLabel_4_2);

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
			textField_7.setText("");

			if (textField.getText().isEmpty() || textField.getText().isEmpty() || textField.getText().length() == 0) {
				lblNewLabel_4.setText("Please Enter the Billing Id");
			}
			if (textField.getText().length() > 0) {
				int num = this.IsInteger(textField.getText());
//				System.out.println(total);
				if (num <= 0) {
					lblNewLabel_4.setText("Please Enter Correct Billing Number");
				} else {
					JSONObject reqObj = prepareReqJsonObjSearch(num);
					String reqString = reqObj.toString();
//					String APIUrl = "http://localhost:9090/findpurchasedhistory?id=" + num;
					String APIUrl = "http://127.0.0.1:8080/BootSwing-0.0.1-SNAPSHOT/findpurchasedhistory?id=" + num;
					String response = Utility.excutePost(APIUrl, reqString);
					ObjectMapper mapper = new ObjectMapper();
					ppl2 = new ArrayList<Purchase_History>();
					try {
						ppl2 = Arrays.asList(mapper.readValue(response, Purchase_History[].class));
						billingPurchaseHistory = ppl2;
//						ppl2.stream().forEach(x -> System.out.println(x));
					} catch (IOException exx) {
						System.out.println("Message:" + exx.getMessage());
					}
					if (billingPurchaseHistory.size() == 0) {
						lblNewLabel_4.setText("No Records Found in the Database");
					} else {
						lblNewLabel_4.setText("");

						table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Product_Id",
								"ProductName", "Product Cost", "Quanity", "Total Cost", "Billing_Id" }));
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						billingId = billingPurchaseHistory.get(0).getBillingId();
						total = 0;
						for (Purchase_History result : billingPurchaseHistory) {
							rowData[0] = result.getProductId();
							rowData[1] = result.getProduct_Name();
							rowData[2] = result.getProduct_cost();
							rowData[3] = result.getQuantity();
							rowData[4] = result.getTotal_cost();
							rowData[5] = result.getBillingId();
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
								String productName = String.valueOf(model.getValueAt(selectedRowIndex, 1));
								String productCost = String.valueOf(model.getValueAt(selectedRowIndex, 2));
								String quantity = String.valueOf(model.getValueAt(selectedRowIndex, 3));
								String totalCost = "" + model.getValueAt(selectedRowIndex, 4);
								String billingId = String.valueOf(model.getValueAt(selectedRowIndex, 5));
								textField_1.setText(productId);
								textField_2.setText(productCost);
								textField_3.setText(quantity);
								textField_4.setText(totalCost);
								textField_5.setText(billingId);
								textField_7.setText(productName);

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
				total = 0;
				int quantity = this.IsInteger(textField_3.getText());
				float cost = Float.parseFloat(textField_2.getText()) * quantity;
				if (quantity <= 0) {
					lblNewLabel_3.setText("Please Enter Some Valid Number");
				} else {
					int id = 0;
					for (Purchase_History purchase_History : ppl2) {
						if (purchase_History.getBillingId() == Integer.parseInt(textField_5.getText())
								&& purchase_History.getProductId() == Integer.parseInt(textField_1.getText())) {
							id = purchase_History.getId();
						}
					}

					JSONObject reqObj = prepareReqJsonObj(id, Integer.parseInt(textField_5.getText()),
							Integer.parseInt(textField_1.getText()), textField_7.getText(),
							Float.parseFloat(textField_2.getText()), quantity, cost);
					String reqString = reqObj.toString();
//					String APIUrl = "http://localhost:9090/updatepurchasedhistory";
					String APIUrl = "http://127.0.0.1:8080/BootSwing-0.0.1-SNAPSHOT/updatepurchasedhistory";
					String response = Utility.excutePost(APIUrl, reqString);
//					System.out.println(" reqObj" + reqObj);
//					System.out.println("reqString" + reqString);
//					System.out.println("response" + response);
					Gson gson = new Gson();
					purchaseHistoryofOneItemFromDB = gson.fromJson(response, Purchase_History.class);
					int idToUpdate = 0;
					idToUpdate = purchaseHistoryofOneItemFromDB.getId();
					table.getModel().setValueAt(purchaseHistoryofOneItemFromDB.getQuantity(), selectedRowIndex, 3);
					table.getModel().setValueAt(purchaseHistoryofOneItemFromDB.getTotal_cost(), selectedRowIndex, 4);
					textField_4.setText(String.valueOf(cost));
					total = 0;
					for (int i = 0; i < ppl2.size(); i++) {
						if (ppl2.get(i).getId() == idToUpdate) {
							ppl2.set(i, purchaseHistoryofOneItemFromDB);
						}
					}
					for (int i = 0; i < ppl2.size(); i++) {
						total += ppl2.get(i).getTotal_cost();
					}
					if (idToUpdate > 0) {
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
				Purchase_History deleteObj = null;
				for (Purchase_History purchase_History : ppl2) {
					if ((purchase_History.getBillingId() == billingId)
							&& (purchase_History.getProductId() == productId)) {
						deleteObj = purchase_History;
					}
				}
				JSONObject reqObj = prepareReqJsonObjDelete(deleteObj);
				String reqString = reqObj.toString();
//				String APIUrl = "http://localhost:9090/deletepurchasedhistory";
				String APIUrl = "http://127.0.0.1:8080/BootSwing-0.0.1-SNAPSHOT/deletepurchasedhistory";

				String response = Utility.excutePost(APIUrl, reqString);
//				System.out.println(" reqObj" + reqObj);
//				System.out.println("reqString" + reqString);
//				System.out.println("response" + response);
				((DefaultTableModel) table.getModel()).removeRow(selectedRowIndex);

				total = 0;
				ObjectMapper mapper = new ObjectMapper();
				List<Purchase_History> updatedBillTotal = new ArrayList<Purchase_History>();
				try {
					updatedBillTotal = Arrays.asList(mapper.readValue(response, Purchase_History[].class));
//					ppl2.stream().forEach(x -> System.out.println(x));
				} catch (IOException exx) {
					System.out.println("Message:" + exx.getMessage());
				}
				for (int i = 0; i < updatedBillTotal.size(); i++) {
					total += updatedBillTotal.get(i).getTotal_cost();
				}
//				System.out.println("total"+total);
				if (ppl2.size() > updatedBillTotal.size()) {
					lblNewLabel_3.setText("Product Deleted");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
					textField_5.setText("");
					textField_7.setText("");

				}
				// For security, Here these buttons are disabled
				btnNewButton_1.setEnabled(false);
				textField_3.setEditable(false);
				btnNewButton_1_1.setEnabled(false);

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

	/**
	 * 
	 * @param id
	 * @param billingId
	 * @param productId
	 * @param product_Name
	 * @param product_cost
	 * @param quantity
	 * @param total_cost
	 * @return
	 */
	public JSONObject prepareReqJsonObj(int id, int billingId, int productId, String product_Name, float product_cost,
			int quantity, float total_cost) {
		JSONObject jsonobj = new JSONObject();
		try {
			jsonobj.put("id", id);
			jsonobj.put("billingId", billingId);
			jsonobj.put("productId", productId);
			jsonobj.put("product_Name", product_Name);
			jsonobj.put("product_cost", product_cost);
			jsonobj.put("quantity", quantity);
			jsonobj.put("total_cost", total_cost);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonobj;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public JSONObject prepareReqJsonObjSearch(int id) {
		JSONObject jsonobj = new JSONObject();
		try {
			jsonobj.put("billingId", id);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonobj;
	}

	/**
	 * 
	 * @param deleteObject
	 * @return
	 */
	public JSONObject prepareReqJsonObjDelete(Purchase_History deleteObject) {
		System.out.println("deleteObject" + deleteObject);
		JSONObject jsonobj = new JSONObject();
		try {
			jsonobj.put("id", deleteObject.getId());
			jsonobj.put("billingId", deleteObject.getBillingId());
			jsonobj.put("productId", deleteObject.getProductId());
			jsonobj.put("product_Name", deleteObject.getProduct_Name());
			jsonobj.put("product_cost", deleteObject.getProduct_cost());
			jsonobj.put("quantity", deleteObject.getQuantity());
			jsonobj.put("total_cost", deleteObject.getTotal_cost());

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonobj;
	}
}
