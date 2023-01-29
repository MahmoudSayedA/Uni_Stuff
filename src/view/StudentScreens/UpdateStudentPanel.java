package view.StudentScreens;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import controller.StudentController;
import model.Student;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class UpdateStudentPanel extends JPanel {
	private JTextField idField;
	private JTextField fName;
	private JTextField lName;
	private JTextField address;
	private JTextField department;
	private Student student;

	/**
	 * Create the panel.
	 */
	public UpdateStudentPanel() {
		setBackground(UIManager.getColor("inactiveCaption"));
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.background"));
		panel.setBounds(10, 11, 430, 54);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Student id");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setForeground(UIManager.getColor("TextField.foreground"));
		lblNewLabel.setBounds(10, 11, 82, 32);
		panel.add(lblNewLabel);

		idField = new JTextField();
		idField.setBounds(104, 18, 86, 20);
		panel.add(idField);
		idField.setColumns(10);

		JButton searchbtn = new JButton("Search");
		searchbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentController hand = new StudentController();
				// get student data
				student = hand.getStudentById(Integer.parseInt(idField.getText()));
				if (student == null) {
					JOptionPane.showMessageDialog(null, "wrong id", "update student", JOptionPane.WARNING_MESSAGE);
				} else {
					fName.setText(student.getfName());
					lName.setText(student.getlName());
					address.setText(student.getAddress());
					department.setText(student.getDepartment());
				}
			}
		});
		searchbtn.setFont(new Font("Arial", Font.PLAIN, 14));
		searchbtn.setBounds(281, 17, 112, 23);
		panel.add(searchbtn);

		JLabel lblNewLabel_1 = new JLabel("First name");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(31, 99, 83, 14);
		add(lblNewLabel_1);

		fName = new JTextField();
		fName.setBounds(154, 97, 86, 20);
		add(fName);
		fName.setColumns(10);

		lName = new JTextField();
		lName.setColumns(10);
		lName.setBounds(154, 144, 86, 20);
		add(lName);

		JLabel lblNewLabel_1_1 = new JLabel("Last name");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(31, 146, 83, 14);
		add(lblNewLabel_1_1);

		address = new JTextField();
		address.setColumns(10);
		address.setBounds(154, 189, 86, 20);
		add(address);

		JLabel lblNewLabel_1_2 = new JLabel("Address");
		lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(31, 191, 83, 14);
		add(lblNewLabel_1_2);

		department = new JTextField();
		department.setColumns(10);
		department.setBounds(154, 232, 86, 20);
		add(department);

		JLabel lblNewLabel_1_3 = new JLabel("Department");
		lblNewLabel_1_3.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_3.setBounds(31, 234, 83, 14);
		add(lblNewLabel_1_3);

		JButton save = new JButton("Save changes");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// check the changed fields
				if (student != null) {
					if (!fName.getText().equals(""))
						student.setfName(fName.getText());
					if (!lName.getText().equals(""))
						student.setlName(lName.getText());
					if (!address.getText().equals(""))
						student.setAddress(address.getText());
					if (!department.getText().equals(""))
						student.setDepartment(department.getText());
					StudentController hand = new StudentController();
					// update student information and show confirmation
					if (hand.updateStudent(student.getId(), student.getfName(), student.getlName(),
							student.getAddress(), student.getDepartment(), null).equals("ok"))
						JOptionPane.showMessageDialog(null, "info updated", "update student",
								JOptionPane.INFORMATION_MESSAGE);

				}

			}
		});
		save.setBounds(301, 225, 103, 35);
		add(save);

	}
}
