package userProfileController;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.User;
import registrationController.RegisDemo;

/**
 * Class for UserProfile
 */
public class UserProfile extends JFrame implements ActionListener {

	// Variable/Reference declaration
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private User user;
	private RegisDemo demo;
	private JButton btnNewButton;
	private JButton btnProductPanel;
	private JButton btnReturnProduct;
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UserProfile frame = new UserProfile();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public UserProfile(User user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 950);
		setLocationRelativeTo(null); 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("User Home");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(405, 10, 99, 50);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Name:-");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(90, 71, 64, 35);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_3 = new JLabel("Email:-");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(90, 116, 64, 35);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_3_1 = new JLabel("Password:-");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_1.setBounds(90, 180, 82, 35);
		contentPane.add(lblNewLabel_3_1);

		JLabel lblNewLabel_3_2 = new JLabel("Mobile:-");
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_2.setBounds(90, 242, 64, 35);
		contentPane.add(lblNewLabel_3_2);

		JLabel lblNewLabel_3_3 = new JLabel("Gender:-");
		lblNewLabel_3_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_3.setBounds(90, 306, 82, 35);
		contentPane.add(lblNewLabel_3_3);

		JLabel lblNewLabel_3_4 = new JLabel("DOB:-");
		lblNewLabel_3_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_4.setBounds(90, 378, 64, 35);
		contentPane.add(lblNewLabel_3_4);

		JLabel lblNewLabel_3_5 = new JLabel("Address:-");
		lblNewLabel_3_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_5.setBounds(90, 430, 82, 35);
		contentPane.add(lblNewLabel_3_5);

		JLabel lblNewLabel_2 = new JLabel(user.getNameField());
		lblNewLabel_2.setBounds(218, 79, 286, 22);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel(user.getEmailField());
		lblNewLabel_2_1.setBounds(218, 124, 286, 22);
		contentPane.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_2 = new JLabel(user.getPasswordField());
		lblNewLabel_2_2.setBounds(218, 188, 286, 22);
		contentPane.add(lblNewLabel_2_2);

		JLabel lblNewLabel_2_3 = new JLabel(user.getMobileField());
		lblNewLabel_2_3.setBounds(218, 250, 286, 22);
		contentPane.add(lblNewLabel_2_3);

		JLabel lblNewLabel_2_4 = new JLabel(user.getradioButton());
		lblNewLabel_2_4.setBounds(218, 314, 286, 22);
		contentPane.add(lblNewLabel_2_4);

		JLabel lblNewLabel_2_5 = new JLabel(user.getDate() + "/" + user.getMonth() + "/" + user.getYear());
		lblNewLabel_2_5.setBounds(218, 386, 286, 22);
		contentPane.add(lblNewLabel_2_5);

		JLabel lblNewLabel_2_6 = new JLabel(user.getAddressArea());
		lblNewLabel_2_6.setBounds(218, 438, 286, 22);
		contentPane.add(lblNewLabel_2_6);

		btnNewButton = new JButton("Logout");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(90, 536, 141, 43);
		contentPane.add(btnNewButton);

		btnProductPanel = new JButton("Order Product");
		btnProductPanel.addActionListener(this);
		btnProductPanel.setBounds(335, 536, 141, 43);
		contentPane.add(btnProductPanel);

		btnReturnProduct = new JButton("Return Product");
		btnReturnProduct.addActionListener(this);
		btnReturnProduct.setBounds(577, 536, 141, 43);
		contentPane.add(btnReturnProduct);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// When user click on logout button, then object becomes null and user
		// redirected to Registration Page
		if (e.getActionCommand() == "Logout") {
			System.out.println("Logout");
			user = null;
			dispose();
			demo = new RegisDemo();

		}
		// If a user want to visit Product Page
		if (e.getSource() == btnProductPanel) {
			Inventory inventory = new Inventory();
		}
		// If a user want to return a product
		if (e.getActionCommand() == "Return Product") {
			ReturnProduct product = new ReturnProduct();
		}
	}
}
