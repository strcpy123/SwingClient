package registrationController;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.json.JSONException;
import org.json.JSONObject;

import api.Utility;
import loginController.LoginDemo;
import model.User;

/**
 * Class for Registration
 */
public class RegisDemo extends JFrame implements ActionListener {
	// Variable/Reference Declaration
	private static final long serialVersionUID = 1L;
	private Container container;
	private JLabel title;
	private JLabel name;
	private JTextField nameField;
	private JLabel email;
	private JTextField emailField;
	private JLabel password;
	private JPasswordField passwordField;
	private JLabel mobile;
	private JTextField mobileField;
	private JLabel gender;
	private JRadioButton maleButton;
	private JRadioButton femaleButton;
	private ButtonGroup gengp;
	private JLabel dob;
	private JComboBox date;
	private JComboBox month;
	private JComboBox year;
	private JLabel address;
	private JTextArea addressArea;
	private JButton sub;
	private JButton reset;
	private JLabel res;
	private User user;
	private JButton login;
	private LoginDemo loginDemo;

	private String dates[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
			"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
	private String months[] = { "Jan", "feb", "Mar", "Apr", "May", "Jun", "July", "Aug", "Sup", "Oct", "Nov", "Dec" };
	private String years[] = { "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005",
			"2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018",
			"2019", "2020" };
	private static final String EMAIL_PATTERN = "^(.+)@(\\S+)$";
	private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
	String uname;
	String uemail;
	String upassword;
	String umobile;
	String uradioButton;
	String uDate;
	String uMonth;
	String uYear;
	String uAddressArea;

	// Registration Page Frame
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public RegisDemo() {
		setTitle("Sign Up");
		setBounds(50, 50, 800, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		container = getContentPane();
		container.setLayout(null);

		title = new JLabel("Signup Here");
		title.setSize(94, 30);
		title.setLocation(395, 10);
		container.add(title);

		name = new JLabel("Enter Name");
		name.setSize(100, 30);
		name.setLocation(190, 58);
		container.add(name);

		nameField = new JTextField(5);
		nameField.setSize(300, 50);
		nameField.setLocation(300, 49);
		container.add(nameField);

		email = new JLabel("Enter Email");
		email.setSize(100, 30);
		email.setLocation(190, 116);
		container.add(email);

		emailField = new JTextField();
		emailField.setSize(300, 50);
		emailField.setLocation(300, 107);
		container.add(emailField);

		password = new JLabel("Enter Password");
		password.setSize(100, 30);
		password.setLocation(190, 181);
		container.add(password);

		passwordField = new JPasswordField();
		passwordField.setSize(300, 50);
		passwordField.setLocation(300, 172);
		container.add(passwordField);

		mobile = new JLabel("Enter Mobile");
		mobile.setSize(100, 30);
		mobile.setLocation(190, 254);
		container.add(mobile);

		mobileField = new JTextField();
		mobileField.setSize(300, 50);
		mobileField.setLocation(300, 245);
		container.add(mobileField);

		gender = new JLabel("Gender");
		gender.setSize(100, 30);
		gender.setLocation(190, 342);
		container.add(gender);

		maleButton = new JRadioButton("Male");
		maleButton.setActionCommand(maleButton.getText());
		maleButton.setSelected(true);
		maleButton.setSize(75, 20);
		maleButton.setLocation(300, 347);
		container.add(maleButton);

		femaleButton = new JRadioButton("Female");
		femaleButton.setActionCommand(femaleButton.getText());
		femaleButton.setSelected(false);
		femaleButton.setSize(80, 20);
		femaleButton.setLocation(395, 347);
		container.add(femaleButton);

		gengp = new ButtonGroup();
		gengp.add(maleButton);
		gengp.add(femaleButton);

		dob = new JLabel("DOB");
		dob.setSize(100, 20);
		dob.setLocation(190, 395);
		container.add(dob);

		date = new JComboBox(dates);
		date.setSize(50, 20);
		date.setLocation(300, 395);
		container.add(date);

		month = new JComboBox(months);
		month.setSize(60, 20);
		month.setLocation(420, 395);
		container.add(month);

		year = new JComboBox(years);
		year.setSize(60, 20);
		year.setLocation(540, 395);
		container.add(year);

		address = new JLabel("Address");
		address.setSize(100, 20);
		address.setLocation(190, 483);
		container.add(address);

		addressArea = new JTextArea();
		addressArea.setSize(300, 75);
		addressArea.setLocation(300, 456);
		addressArea.setLineWrap(true);
		container.add(addressArea);

		sub = new JButton("Sign Up");
		sub.setSize(100, 20);
		sub.setLocation(300, 573);
		sub.addActionListener(this);
		container.add(sub);

		reset = new JButton("Reset");
		reset.setSize(100, 20);
		reset.setLocation(395, 616);
		reset.addActionListener(this);
		container.add(reset);

		login = new JButton("Login");
		login.setSize(100, 20);
		login.setLocation(500, 573);
		login.addActionListener(this);
		container.add(login);

		res = new JLabel("");
		res.setSize(196, 25);
		res.setLocation(356, 663);
		container.add(res);

		setVisible(true);
	}

	public static void main(String[] args) {
		RegisDemo one = new RegisDemo();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// When a user go for signup
		if (e.getSource() == sub) {
			user = new User();
			if (nameField.getText().length() != 0 && emailField.getText().length() != 0
					&& passwordField.getText().length() != 0 && addressArea.getText().length() != 0
					&& mobileField.getText().length() != 0 && isValid(emailField.getText())
					&& mobileField.getText().matches("(0/91)?[7-9][0-9]{9}")) {
				user.setNameField(nameField.getText());
				user.setEmailField(emailField.getText());
				user.setPasswordField(passwordField.getText());
				user.setAddressArea(addressArea.getText());
				user.setMobileField(mobileField.getText());
				user.setDate((String) date.getItemAt(date.getSelectedIndex()));
				user.setMonth((String) month.getItemAt(month.getSelectedIndex()));
				user.setYear((String) year.getItemAt(year.getSelectedIndex()));
				user.setradioButton(gengp.getSelection().getActionCommand());
				uname = nameField.getText();
				uemail = emailField.getText();
				upassword = passwordField.getText();
				umobile = mobileField.getText();
				uradioButton = user.getradioButton();
				uDate = user.getDate();
				uMonth = user.getMonth();
				uYear = user.getYear();
				uAddressArea = user.getAddressArea();
				JSONObject reqObj = prepareReqJsonObj(uname, uemail, upassword, umobile, uradioButton, uDate, uMonth, uYear,
						uAddressArea);
				
//				JSONObject reqObj = checkMethod(user);
				String reqString = reqObj.toString();
				String APIUrl = "http://localhost:9090/userSignUp";

				String response = Utility.excutePost(APIUrl, reqString);

				System.out.println(" reqObj" + reqObj);
				System.out.println("reqString" + reqString);
				System.out.println("response" + response);
				res.setText("Registered Successfully!!!");
			}

			else {
				res.setText("Please Enter Valid Data");
			}
			// When reset button clicked
		} else if (e.getSource() == reset) {
			user = null;
			String def = "";
			nameField.setText(def);
			emailField.setText(def);
			passwordField.setText(def);
			addressArea.setText(def);
			mobileField.setText(def);
			res.setText(def);
			date.setSelectedIndex(0);
			month.setSelectedIndex(0);
			year.setSelectedIndex(0);
			// When login button clicked
		} else if (e.getActionCommand() == "Login") {
			loginDemo = new LoginDemo();
			dispose();
		}
//		System.out.println(user);
	}

	/**
	 * To check the email is valid or not
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isValid(final String email) {
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	/**
	 * Convert java object as a Json Object and return json Object
	 * @param user
	 * @return
	 */
	public JSONObject checkMethod(User user) {
		JSONObject jsonobj = new JSONObject();
		try {
			jsonobj.put("user", user);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonobj;
	}
	/**
	 * Convert String value in json Object and return json Object
	 * @param s1
	 * @param s2
	 * @param s3
	 * @param s4
	 * @param s5
	 * @param s6
	 * @param s7
	 * @param s8
	 * @param s9
	 * @return
	 */
	public JSONObject prepareReqJsonObj(String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9) {
		JSONObject jsonobj = new JSONObject();
		try {
			jsonobj.put("nameField", s1);
			jsonobj.put("emailField", s2);
			jsonobj.put("passwordField", s3);
			jsonobj.put("mobileField", s4);
			jsonobj.put("radioButton", s5);
			jsonobj.put("date", s6);
			jsonobj.put("month", s7);
			jsonobj.put("year", s8);
			jsonobj.put("addressArea", s9);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonobj;
	}

}
