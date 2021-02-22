package loginController;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import api.Utility;
import model.User;
import registrationController.RegisDemo;
import userProfileController.UserProfile;
import javax.swing.SwingConstants;

/**
 * Class For Login
 */
public class LoginDemo extends JFrame implements ActionListener {

	// Variable/Reference declaration
	private static final long serialVersionUID = 1L;
	private Container container;
	private JLabel title;
	private JLabel email;
	private JTextField emailField;
	private JLabel password;
	private JPasswordField passwordField;
	private JButton sub;
	private JButton reset;
	private JLabel res;
	private User user;
	private UserProfile userProfile;
	private JLabel lblNewLabel;
	private String rEmail;
	private String rPassoword;

	// Frame Created
	public LoginDemo() {
		setTitle("Login");
		setBounds(50, 50, 800, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		container = getContentPane();
		container.setLayout(null);

		title = new JLabel("Login Here");
		title.setFont(new Font("Tahoma", Font.BOLD, 14));
		title.setSize(109, 30);
		title.setLocation(359, 142);
		container.add(title);

		email = new JLabel("Enter Email");
		email.setSize(100, 30);
		email.setLocation(147, 232);
		container.add(email);

		emailField = new JTextField(5);
		emailField.setSize(257, 50);
		emailField.setLocation(285, 223);
		container.add(emailField);

		password = new JLabel("Enter Password");
		password.setSize(100, 30);
		password.setLocation(147, 336);
		container.add(password);

		passwordField = new JPasswordField(10);
		passwordField.setSize(257, 50);
		passwordField.setLocation(285, 327);
		container.add(passwordField);

		sub = new JButton("Login");
		sub.setSize(100, 20);
		sub.setLocation(285, 420);
		sub.addActionListener(this);
		container.add(sub);

		reset = new JButton("Reset");
		reset.setSize(100, 20);
		reset.setLocation(442, 420);
		reset.addActionListener(this);
		container.add(reset);

		res = new JLabel("");
		res.setHorizontalAlignment(SwingConstants.CENTER);
		res.setForeground(Color.RED);
		res.setSize(194, 25);
		res.setLocation(318, 462);
		container.add(res);
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 796, 50);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("Sign Up");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegisDemo obj = new RegisDemo();
				dispose();
			}
		});
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(709, 10, 77, 30);
		panel.add(lblNewLabel);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// When user click on login
		if (e.getSource() == sub) {
			rEmail = emailField.getText();
			rPassoword = passwordField.getText();
			JSONObject reqObj = prepareReqJsonObj(rEmail, rPassoword);

//			JSONObject reqObj = checkMethod(user);
			String reqString = reqObj.toString();
//			String APIUrl = "http://localhost:9090/login";
			String APIUrl = "http://127.0.0.1:8080/BootSwing-0.0.1-SNAPSHOT/login";
			String response = Utility.excutePost(APIUrl, reqString);

//			System.out.println(" reqObj" + reqObj);
//			System.out.println("reqString" + reqString);
//			System.out.println("response" + response);
			Gson gson = new Gson();
			user = gson.fromJson(response, User.class);
//			System.out.println(user);
		}
		// When reset button clicked
		else if (e.getSource() == reset) {
			user = null;
			String def = "";
			emailField.setText(def);
			passwordField.setText(def);
			res.setText(def);
		}
		// If user is not available
		if (user == null) {
			res.setText("No User!!!");
		}
		// If user exists
		if (user != null) {
			userProfile = new UserProfile(user);
			res.setText("User Found");
			dispose();
		}
//		System.out.println(user);
	}

	// LoginDemo object creation
	public static void main(String[] args) {
		LoginDemo demo = new LoginDemo();
	}

	public JSONObject prepareReqJsonObj(String s1, String s2) {
		JSONObject jsonobj = new JSONObject();
		try {
			jsonobj.put("emailField", s1);
			jsonobj.put("passwordField", s2);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonobj;
	}
}
