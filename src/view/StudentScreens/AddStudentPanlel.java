package view.StudentScreens;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import controller.StudentController;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class AddStudentPanlel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField fName;
	private JTextField lName;
	private JTextField address;
	private JTextField department;

	/**
	 * Create the panel.
	 */
	public AddStudentPanlel() {
		setLayout(null);

		JRadioButton genderMale = new JRadioButton("Male");
		genderMale.setFont(new Font("Arial", Font.PLAIN, 14));
		genderMale.setBounds(99, 256, 109, 23);
		// add(genderMale);

		JRadioButton genderFemale = new JRadioButton("Female");
		genderFemale.setFont(new Font("Arial", Font.PLAIN, 14));
		genderFemale.setBounds(291, 256, 109, 23);
		// add(genderFemale);

		JLabel fNameLable = new JLabel("First Name");
		fNameLable.setFont(new Font("Arial", Font.PLAIN, 14));
		fNameLable.setBounds(193, 21, 90, 14);
		add(fNameLable);

		JLabel lNameLable = new JLabel("Last Name");
		lNameLable.setFont(new Font("Arial", Font.PLAIN, 14));
		lNameLable.setBounds(193, 78, 90, 14);
		add(lNameLable);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Arial", Font.PLAIN, 14));
		lblAddress.setBounds(193, 133, 90, 14);
		add(lblAddress);

		JLabel lblDepatment = new JLabel("Depatment");
		lblDepatment.setFont(new Font("Arial", Font.PLAIN, 14));
		lblDepatment.setBounds(193, 189, 90, 14);
		add(lblDepatment);

		fName = new JTextField();
		fName.setToolTipText("enter first name");
		fName.setBounds(173, 47, 110, 20);
		add(fName);
		fName.setColumns(10);

		lName = new JTextField();
		lName.setToolTipText("enter last name");
		lName.setColumns(10);
		lName.setBounds(173, 102, 110, 20);
		add(lName);

		address = new JTextField();
		address.setToolTipText("enter address");
		address.setColumns(10);
		address.setBounds(173, 158, 110, 20);
		add(address);

		department = new JTextField();
		department.setToolTipText("enter department");
		department.setColumns(10);
		department.setBounds(173, 214, 110, 20);
		add(department);

		JButton addbtn = new JButton("add");
		addbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String first = fName.getText();
				String last = lName.getText();
				String addr = address.getText();
				String depart = department.getText();
				StudentController controller = new StudentController();
				String mess = controller.addStudent(first, last, addr, depart, null);////////
				if (mess == "ok") {
					JOptionPane.showMessageDialog(null, "Student " + first + " added!", "system message",
							JOptionPane.INFORMATION_MESSAGE);
					fName.setText("");
					lName.setText("");
					address.setText("");
					department.setText("");

				} else
					JOptionPane.showMessageDialog(null, mess, "system message", JOptionPane.WARNING_MESSAGE);
			}
		});
		addbtn.setToolTipText("send data");
		addbtn.setFont(new Font("Arial", Font.PLAIN, 14));
		addbtn.setBounds(193, 302, 89, 23);
		add(addbtn);

	}
}
