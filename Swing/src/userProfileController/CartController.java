package userProfileController;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import api.Utility;
import model.Product;
import model.Purchase_History;

/**
 * Class for ProductCart
 */
public class CartController extends JFrame implements ActionListener {
	// Variable/Reference Declaration
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private String quantity[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
	private JComboBox comboBox_1;
	private DefaultCellEditor cellEditor;
	private HashSet<Product> cartSet;
	private Object rowData[] = new Object[10];
	private DefaultTableModel model;
	private JTextField textField;
	private int rowCount;
	private float totalSum;
	private JButton btnNewButton_1;
	private JButton btnNewButton;
	private Purchase_History purchase_history;
	private List<Purchase_History> purchaseList;
	private int code = 0;

	/**
	 * Create the frame.
	 */
	public CartController(HashSet<Product> productSet) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 750);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Cart");
		lblNewLabel.setBounds(320, 0, 53, 52);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 44, 647, 192);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Product ID", "Product Name", "Product Cost", "Quantity", "Total_Cost" }));
		scrollPane.setViewportView(table);

		comboBox_1 = new JComboBox(quantity);
		comboBox_1.addActionListener(this);
		comboBox_1.setForeground(Color.ORANGE);
		cellEditor = new DefaultCellEditor(comboBox_1);
		table.getColumnModel().getColumn(3).setCellEditor(cellEditor);
		scrollPane.setColumnHeaderView(comboBox_1);

		btnNewButton = new JButton("Place Order");
		btnNewButton.setEnabled(false);
		btnNewButton.addActionListener(this);
		btnNewButton.setForeground(new Color(0, 128, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(248, 277, 227, 29);
		contentPane.add(btnNewButton);

		btnNewButton_1 = new JButton("Total");
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setBounds(370, 246, 85, 21);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel_1 = new JLabel("Total Cost :");
		lblNewLabel_1.setBounds(465, 243, 67, 24);
		contentPane.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(542, 247, 136, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		cartSet = productSet;
		model = (DefaultTableModel) table.getModel();
		for (Product product : cartSet) {
			rowData[0] = product.getId();
			rowData[1] = product.getName();
			rowData[2] = product.getPrice();
			model.addRow(rowData);
		}
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// When Product Quantity is selected in the Cart
		if (e.getSource() == comboBox_1) {
			for (int i = 0; i < table.getRowCount(); i++) {
				if (table.getModel().getValueAt(i, 3) != null) {
					btnNewButton_1.setEnabled(true);
				} else {
					btnNewButton_1.setEnabled(false);
					break;
				}
			}
		}
		// After the product quantity selection total button clicked
		if (e.getActionCommand() == "Total") {
			rowCount = table.getRowCount();
			totalSum = 0;
			float product_cost = 1;
			int product_quantity = 1;
			for (int i = 0; i < rowCount; i++) {
//				System.out.println(table.getModel().getValueAt(0, 2));
//				System.out.println(table.getModel().getValueAt(0, 3));
				product_cost = (float) table.getModel().getValueAt(i, 2);
				product_quantity = Integer.parseInt((String) table.getModel().getValueAt(i, 3));
				table.getModel().setValueAt("" + product_cost * product_quantity, i, 4);
				totalSum += product_cost * product_quantity;
			}
			textField.setText("" + totalSum);
			purchaseList = new ArrayList<Purchase_History>();
			btnNewButton.setEnabled(true);
		}
		// When place order button clicked
		if (e.getActionCommand() == "Place Order") {
			for (int i = 0; i < table.getRowCount(); i++) {
				purchase_history = new Purchase_History();
				purchase_history.setProductId((int) (table.getModel().getValueAt(i, 0)));
				purchase_history.setProduct_Name((String) table.getModel().getValueAt(i, 1));
				purchase_history.setProduct_cost((Float) table.getModel().getValueAt(i, 2));
//				System.out.println((table.getModel().getValueAt(i, 3));
				purchase_history.setQuantity(Integer.parseInt((String) (table.getModel().getValueAt(i, 3))));
				purchase_history.setTotal_cost(Float.parseFloat((String) table.getModel().getValueAt(i, 4)));
				purchaseList.add(purchase_history);
			}
			code = (int) (Math.random() * 100000);
			JSONArray reqObj = prepareReqJsonObj(purchaseList);
			String reqString = reqObj.toString();
			String APIUrl = "http://localhost:9090/storeProduct";

			String response = Utility.excutePost(APIUrl, reqString);

//			System.out.println(" reqObj" + reqObj);
//			System.out.println("reqString" + reqString);
//			System.out.println("response" + response);
			ObjectMapper mapper = new ObjectMapper();
			List<Purchase_History> ppl2 = new ArrayList<Purchase_History>();
			try {
				// 2. convert JSON array to List of objects
				ppl2 = Arrays.asList(mapper.readValue(response, Purchase_History[].class));
//				System.out.println("\nJSON array to List of objects");
//				ppl2.stream().forEach(x -> System.out.println(x));
			} catch (IOException exx) {
				System.out.println("Message:" + exx.getMessage());
			}
			OrderSuccess orderSuccess = new OrderSuccess(ppl2);
			dispose();
		}
	}

	public JSONArray prepareReqJsonObj(List<Purchase_History> list) {
		System.out.println("list" + list);
		JSONObject jsonobj = null;
		JSONArray arr = new JSONArray();
		try {
			for (Purchase_History purchase_History : list) {
				jsonobj = new JSONObject();
				jsonobj.put("billingId", code);
				jsonobj.put("productId", purchase_History.getProductId());
				jsonobj.put("product_Name", purchase_History.getProduct_Name());
				jsonobj.put("product_cost", purchase_History.getProduct_cost());
				jsonobj.put("quantity", purchase_History.getQuantity());
				jsonobj.put("total_cost", purchase_History.getTotal_cost());
				arr.put(jsonobj);
			}
//			jsonobj.put(list);
//			arr.put(list);
////			jsonobj.put("code", s2);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("arr"+arr);
		return arr;
	}
}
