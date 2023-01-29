package view.authenticationScreens;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import controller.UserController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class SignUpForm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpForm frame = new SignUpForm();
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

	public SignUpForm() {
		// Initialise the frame
		setTitle("Sign up");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 448, 373);
		// initialise the panel
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		// add panel
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// add label
		JLabel userNameLabel = new JLabel("Username");
		userNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		userNameLabel.setBounds(150, 31, 115, 34);
		contentPane.add(userNameLabel);
		// add username field
		JTextArea usernameField = new JTextArea();
		usernameField.setBounds(150, 72, 145, 22);
		contentPane.add(usernameField);

		// add pass label
		JLabel passLable = new JLabel("Password");
		passLable.setFont(new Font("Arial", Font.PLAIN, 14));
		passLable.setBounds(150, 105, 115, 34);
		contentPane.add(passLable);
		// add pass field
		JPasswordField passField = new JPasswordField();
		passField.setBounds(150, 143, 145, 22);
		contentPane.add(passField);

		// add department label
		JLabel depLable = new JLabel("Department");
		depLable.setFont(new Font("Arial", Font.PLAIN, 14));
		depLable.setBounds(150, 176, 115, 34);
		contentPane.add(depLable);
		// add department field
		JTextArea depField = new JTextArea();
		depField.setBounds(150, 210, 145, 22);
		contentPane.add(depField);

		// add submit button
		JButton submit = new JButton("Register");
		submit.setFont(new Font("Arial", Font.PLAIN, 14));
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// set listener
				// register
				String uName = usernameField.getText();
				String uPass = passField.getText();
				String uDep = depField.getText();
				UserController uHand = new UserController();
				String mess = uHand.signUp(uName, uPass, uDep);
				if (mess.equals("ok")) {// info message
					JOptionPane.showMessageDialog(null, "user added! you can sign in now", "system message",
							JOptionPane.INFORMATION_MESSAGE);
					// switch to sign in form
					terminate();
					SignInForm s = new SignInForm();
					s.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, mess, "system message", JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		depLable.setFont(new Font("Arial", Font.PLAIN, 14));
		submit.setBounds(164, 259, 97, 34);
		contentPane.add(submit);

		// add back button
		JButton back = new JButton("back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				terminate();
				SignInForm s = new SignInForm();
				s.setVisible(true);
			}
		});
		back.setForeground(UIManager.getColor("ToolBar.dockingForeground"));
		back.setBackground(UIManager.getColor("ToolBar.dockingForeground"));
		back.setFont(new Font("Arial", Font.PLAIN, 12));
		back.setBounds(0, 0, 67, 34);

		contentPane.add(back);
	}

	// to turn frame off
	private void terminate() {
		this.dispose();
	}
}
