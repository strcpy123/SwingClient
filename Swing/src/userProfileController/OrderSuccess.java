package userProfileController;

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

import model.Purchase_History;

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
		setBounds(100, 100, 750, 750);
		setLocationRelativeTo(null); 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Invoice");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(307, 10, 64, 32);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(91, 120, 547, 277);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Product Id", "Product Name", "Product Cost", "Quantity", "Product Total Cost"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("Total :- ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(367, 404, 57, 42);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(443, 407, 195, 42);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Billing Id:-");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(91, 57, 116, 32);
		contentPane.add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(217, 52, 221, 47);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		model = (DefaultTableModel) table.getModel();
		purchaseList = purchase;
		textField_1.setText(String.valueOf(purchaseList.get(0).getBillingId()));
		float total = 0;
		for (int i = 0; i < purchaseList.size(); i++) {
			rowData[0] = purchaseList.get(i).getProduct_Id();
			rowData[1] = purchaseList.get(i).getProduct_Name();
			rowData[2] = purchaseList.get(i).getProduct_cost();
			rowData[3] = purchaseList.get(i).getQuantity();
			rowData[4] = purchaseList.get(i).getTotal_cost();
			model.addRow(rowData);
			total += purchaseList.get(i).getTotal_cost();
		}
		textField.setText(String.valueOf(total));
		setVisible(true);
	}
}
