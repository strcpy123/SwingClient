package loginController;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import api.Utility;
import database.DB;
import model.User;
import userProfileController.UserProfile;

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
	private DB db;
	private String rEmail;
	private String rPassoword;

	// Frame Created
	public LoginDemo() {
		setTitle("Login");
		setBounds(150, 150, 500, 520);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		container = getContentPane();
		container.setLayout(null);

		title = new JLabel("Login Here");
		title.setSize(67, 30);
		title.setLocation(211, 32);
		container.add(title);

		email = new JLabel("Enter Email");
		email.setSize(100, 30);
		email.setLocation(23, 109);
		container.add(email);

		emailField = new JTextField(5);
		emailField.setSize(257, 50);
		emailField.setLocation(133, 100);
		container.add(emailField);

		password = new JLabel("Enter Password");
		password.setSize(100, 30);
		password.setLocation(23, 179);
		container.add(password);

		passwordField = new JPasswordField(10);
		passwordField.setSize(257, 50);
		passwordField.setLocation(133, 170);
		container.add(passwordField);

		sub = new JButton("Login");
		sub.setSize(100, 20);
		sub.setLocation(143, 277);
		sub.addActionListener(this);
		container.add(sub);

		reset = new JButton("Reset");
		reset.setSize(100, 20);
		reset.setLocation(277, 277);
		reset.addActionListener(this);
		container.add(reset);

		res = new JLabel("");
		res.setSize(194, 25);
		res.setLocation(196, 352);
		container.add(res);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// When user click on login
		if (e.getSource() == sub) {
//			db = new DB ();
//			user = db.getUser(emailField.getText(), passwordField.getText());
			rEmail = emailField.getText();
			rPassoword = passwordField.getText();
			JSONObject reqObj = prepareReqJsonObj(rEmail, rPassoword);

//			JSONObject reqObj = checkMethod(user);
			String reqString = reqObj.toString();
			String APIUrl = "http://localhost:9090/login";

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
