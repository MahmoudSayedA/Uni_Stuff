package view.authenticationScreens;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import controller.UserController;
import dataaccess.ISubject;
import dataaccess.StudentDA;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class SignInForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userName;
	private JPasswordField passowrd;

	// show form
	public static void main(String args[]) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignInForm frame = new SignInForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SignInForm() {

		// main frame properties////////////should be at the end
		setTitle("Sing in");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		// add label
		JLabel userNameLabel = new JLabel("Username");
		userNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		userNameLabel.setBounds(10, 41, 121, 30);
		contentPane.add(userNameLabel);
		// add username field
		userName = new JTextField();
		userName.setBounds(10, 82, 141, 20);
		contentPane.add(userName);
		userName.setColumns(10);

		// add label
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 14));
		lblPassword.setBounds(10, 113, 121, 30);
		contentPane.add(lblPassword);
		// add pass field
		passowrd = new JPasswordField();
		passowrd.setColumns(10);
		passowrd.setBounds(10, 154, 141, 20);
		contentPane.add(passowrd);
		// Configuration label
		JLabel logLable = new JLabel("");
		logLable.setForeground(Color.RED);
		logLable.setFont(new Font("Arial", Font.PLAIN, 14));
		logLable.setBounds(213, 82, 151, 61);
		contentPane.add(logLable);
		// sign in
		JButton Submit = new JButton("Sign in");

		Submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// check sign in
				String name = userName.getText();
				String pass = passowrd.getText();
				UserController uHand = new UserController();
				String mess = uHand.signIn(name, pass.toString());
				if (!mess.equals("ok"))
					// confirmation message
					JOptionPane.showMessageDialog(null, mess, "error message", JOptionPane.WARNING_MESSAGE);
				else {
					String depart = uHand.getDepartment(name);
					view.MainFrame frame = new view.MainFrame(depart);
					frame.setVisible(true);
					terminate();

				}
//				SignUpForm s=new SignUpForm();
//				s.setVisible(true);
//				System.out.println("clicked");
			}
		});
		Submit.setFont(new Font("Arial", Font.PLAIN, 14));
		Submit.setBounds(70, 197, 90, 35);
		contentPane.add(Submit);

		JButton signUp = new JButton("Sign Up");
		signUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// create sign in from and terminates
				SignUpForm form = new SignUpForm();
				form.setVisible(true);
				terminate();
			}
		});
		signUp.setFont(new Font("Arial", Font.PLAIN, 14));
		signUp.setBounds(266, 197, 121, 35);
		contentPane.add(signUp);

	}

	void terminate() {
		this.dispose();
	}
}
