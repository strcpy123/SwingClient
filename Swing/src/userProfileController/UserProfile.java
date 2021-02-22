package userProfileController;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import loginController.LoginDemo;
import model.User;
import registrationController.RegisDemo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Class for UserProfile
 */
public class UserProfile extends JFrame implements ActionListener {

	// Variable/Reference declaration
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private User user;
	private RegisDemo demo;
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
		setBounds(50, 50, 800, 800);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("User Home");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(339, 60, 99, 50);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Name:-");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(90, 148, 64, 35);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_3 = new JLabel("Email:-");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(90, 210, 64, 35);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_3_1 = new JLabel("Password:-");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_1.setBounds(90, 272, 82, 35);
		contentPane.add(lblNewLabel_3_1);

		JLabel lblNewLabel_3_2 = new JLabel("Mobile:-");
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_2.setBounds(90, 333, 64, 35);
		contentPane.add(lblNewLabel_3_2);

		JLabel lblNewLabel_3_3 = new JLabel("Gender:-");
		lblNewLabel_3_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_3.setBounds(90, 391, 82, 35);
		contentPane.add(lblNewLabel_3_3);

		JLabel lblNewLabel_3_4 = new JLabel("DOB:-");
		lblNewLabel_3_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_4.setBounds(90, 451, 64, 35);
		contentPane.add(lblNewLabel_3_4);

		JLabel lblNewLabel_3_5 = new JLabel("Address:-");
		lblNewLabel_3_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_5.setBounds(90, 496, 82, 35);
		contentPane.add(lblNewLabel_3_5);

		JLabel lblNewLabel_2 = new JLabel(user.getNameField());
		lblNewLabel_2.setBounds(218, 156, 286, 22);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel(user.getEmailField());
		lblNewLabel_2_1.setBounds(218, 218, 286, 22);
		contentPane.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_2 = new JLabel(user.getPasswordField());
		lblNewLabel_2_2.setBounds(218, 280, 286, 22);
		contentPane.add(lblNewLabel_2_2);

		JLabel lblNewLabel_2_3 = new JLabel(user.getMobileField());
		lblNewLabel_2_3.setBounds(218, 341, 286, 22);
		contentPane.add(lblNewLabel_2_3);

		JLabel lblNewLabel_2_4 = new JLabel(user.getradioButton());
		lblNewLabel_2_4.setBounds(218, 399, 286, 22);
		contentPane.add(lblNewLabel_2_4);

		JLabel lblNewLabel_2_5 = new JLabel(user.getDate() + "/" + user.getMonth() + "/" + user.getYear());
		lblNewLabel_2_5.setBounds(218, 453, 286, 22);
		contentPane.add(lblNewLabel_2_5);

		JLabel lblNewLabel_2_6 = new JLabel(user.getAddressArea());
		lblNewLabel_2_6.setBounds(218, 504, 286, 22);
		contentPane.add(lblNewLabel_2_6);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 786, 50);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Order");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Inventory();
				dispose();
			}
		});
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(489, 10, 62, 30);
		panel.add(lblNewLabel_4);
		
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
		// If a user want to return a product
		if (e.getActionCommand() == "Return Product") {
			ReturnProduct product = new ReturnProduct();
		}
	}
}
