package userProfileController;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import loginController.LoginDemo;
import model.Purchase_History;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OrderSuccess extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private Object rowData[] = new Object[10];
	private DefaultTableModel model;
	private List<Purchase_History> purchaseList;

	/**
	 * Create the frame. When order placed successfully
	 */
	public OrderSuccess(List<Purchase_History> purchase) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(50, 50, 800, 800);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Invoice");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(356, 75, 64, 32);
		contentPane.add(lblNewLabel);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(55, 190, 671, 334);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Product Id", "Product Name", "Product Cost", "Quantity", "Product Total Cost" }));
		scrollPane.setViewportView(table);

		JLabel lblNewLabel_1 = new JLabel("Total :- ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(448, 534, 57, 42);
		contentPane.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(531, 537, 195, 42);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Billing Id:-");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(129, 138, 116, 32);
		contentPane.add(lblNewLabel_3);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(255, 133, 221, 47);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		model = (DefaultTableModel) table.getModel();
		purchaseList = purchase;
		textField_1.setText(String.valueOf(purchaseList.get(0).getBillingId()));
		float total = 0;
		for (int i = 0; i < purchaseList.size(); i++) {
			rowData[0] = purchaseList.get(i).getProductId();
			rowData[1] = purchaseList.get(i).getProduct_Name();
			rowData[2] = purchaseList.get(i).getProduct_cost();
			rowData[3] = purchaseList.get(i).getQuantity();
			rowData[4] = purchaseList.get(i).getTotal_cost();
			model.addRow(rowData);
			total += purchaseList.get(i).getTotal_cost();
		}
		textField.setText(String.valueOf(total));
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 786, 50);
		contentPane.add(panel);
		
		JLabel lblNewLabel_4_1 = new JLabel("Return");
		lblNewLabel_4_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new ReturnProduct();
				dispose();
			}
		});
		lblNewLabel_4_1.setForeground(Color.WHITE);
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4_1.setBounds(592, 10, 62, 30);
		panel.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_2 = new JLabel("Logout");
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
		panel.add(lblNewLabel_4_2);
		setVisible(true);
	}
}
