package userProfileController;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import api.Utility;
import model.Product;
import model.User;

public class Inventory extends JFrame implements ActionListener {

	/**
	 * Variable/Reference declaration
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JLabel lblNewLabel_3;
	private Product product;
	private Product cartProduct;
	private HashSet<Product> productSet;
	private JPanel panel;
	@SuppressWarnings("unused")
	private JComboBox<Object> comboBox;
	private JButton btnNewButton_2;
	private JButton btnNewButton_1;
	@SuppressWarnings("unused")
	private CartController cartcontroller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inventory frame = new Inventory();
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
	public Inventory() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 950, 950);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setForeground(Color.RED);
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(new Color(255, 0, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		productSet = new HashSet<>();

		JLabel lblNewLabel_1 = new JLabel("Product Id");
		lblNewLabel_1.setBounds(220, 66, 103, 28);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(556, 66, 132, 28);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Order Product");
		lblNewLabel.setBounds(339, 10, 194, 36);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblNewLabel);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Product Details", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setToolTipText("");
		panel.setBounds(53, 163, 835, 298);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Product Id");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(149, 21, 86, 17);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Product Name");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_1.setBounds(394, 19, 103, 21);
		panel.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_2 = new JLabel("Price");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_2.setBounds(632, 19, 48, 21);
		panel.add(lblNewLabel_2_2);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(149, 78, 96, 19);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(383, 78, 124, 19);
		panel.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(608, 78, 86, 19);
		panel.add(textField_2);

		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(231, 233, 572, 55);
		panel.add(lblNewLabel_3);

		btnNewButton_2 = new JButton("Add");
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.addActionListener(this);
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.setBounds(314, 163, 218, 49);
		panel.add(btnNewButton_2);

		btnNewButton_1 = new JButton("Go To Cart");
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(313, 506, 276, 36);
		contentPane.add(btnNewButton_1);

		textField_3 = new JTextField();
		textField_3.setBounds(382, 65, 132, 36);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// When a user search for a product
		if (e.getActionCommand() == "Search") {
			if (textField_3.getText().isEmpty() || textField_3.getText().isEmpty()
					|| textField_3.getText().length() == 0) {
				lblNewLabel_3.setText("Please Enter Product Id");
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				btnNewButton_2.setEnabled(false);
				btnNewButton_1.setEnabled(false);
			}

			if (textField_3.getText().length() != 0) {
				int num = this.IsInteger(textField_3.getText());
				if (num <= 0) {
					lblNewLabel_3.setText("Please Enter Some Valid Number");
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					btnNewButton_2.setEnabled(false);
					btnNewButton_1.setEnabled(false);
				} else {
					JSONObject reqObj = prepareReqJsonObj(String.valueOf(num));

//					JSONObject reqObj = checkMethod(user);
					String reqString = reqObj.toString();
//					String APIUrl = "http://localhost:9090/findProduct?id="+num;
					String APIUrl = "http://127.0.0.1:8080/BootSwing-0.0.1-SNAPSHOT/findProduct?id="+num;

					String response = Utility.excutePost(APIUrl, reqString);

					System.out.println(" reqObj" + reqObj);
					System.out.println("reqString" + reqString);
					System.out.println("response" + response);
					Gson gson = new Gson();
					product = gson.fromJson(response, Product.class);
					if (product == null) {
						lblNewLabel_3.setText("No Data Found in the Database");
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
						btnNewButton_2.setEnabled(false);
						btnNewButton_1.setEnabled(false);
					} else {
						textField.setText("" + product.getId());
						textField_1.setText(product.getName());
						textField_2.setText("" + product.getPrice());
						System.out.println(product);
						btnNewButton_2.setEnabled(true);
						lblNewLabel_3.setText("");
					}
				}

			}

		}
		// When a user wants to add product in the cart
		if (e.getActionCommand() == "Add") {

			cartProduct = new Product();
			cartProduct.setId(Integer.parseInt(textField.getText()));
			cartProduct.setName(textField_1.getText());
			cartProduct.setPrice(Float.parseFloat(textField_2.getText()));
			productSet.add(cartProduct);
			lblNewLabel_3.setText("Item Added to the Cart, Go for Next Product or Go to Cart");
			btnNewButton_1.setEnabled(true);
		}
		// Go to the cart after product selection
		if (e.getActionCommand() == "Go To Cart") {
			System.out.println("Cart Clicked");
			for (Product product : productSet) {
				System.out.println("Set Id:" + product.getId());
			}
			cartcontroller = new CartController(productSet);
			dispose();
		}
	}

	/**
	 * It will check that the number is Integer or not
	 * 
	 * @param number
	 * @return
	 */
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
