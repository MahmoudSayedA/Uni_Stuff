package view.CSScreens;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.DegreeController;
import controller.StudentController;

import javax.swing.UIManager;

@SuppressWarnings("serial")
public class CSUpdateDegreePanel extends JPanel {

	private JTextField computerArch;
	private JTextField algo;
	private JTextField dataSt2;
	private JTextField software2;
	private JTextField operatingSystem;
	private DegreeController hand;
	private JTextField studentID;
	private String SubmitteDSubjects;
	private String dep = "cs";

	/**
	 * Create the panel.
	 */
	public CSUpdateDegreePanel() {
		setLayout(null);
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("activeCaption"));
		panel.setBounds(10, 11, 451, 72);
		add(panel);
		panel.setLayout(null);

		JLabel idlbl = new JLabel("Student id");
		idlbl.setBounds(10, 42, 64, 17);
		idlbl.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(idlbl);

		studentID = new JTextField();
		studentID.setBounds(100, 41, 86, 20);
		studentID.setColumns(10);
		panel.add(studentID);

		JButton searchbtn = new JButton("search");
		searchbtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Integer studID = Integer.parseInt(studentID.getText());
				if (studID != null) {
					StudentController stHand = new StudentController();
					String studentDepart = stHand.getDepartment(studID);
					if (dep.equals(studentDepart)) {
						hand = new DegreeController();
						Map<String, Integer> degrees = hand.getStudentDegree(studID);
						computerArch.setText(Integer.toString(degrees.get("csca")));
						algo.setText(Integer.toString(degrees.get("csalgo")));
						operatingSystem.setText(Integer.toString(degrees.get("csos")));
						software2.setText(Integer.toString(degrees.get("csse2")));
						dataSt2.setText(Integer.toString(degrees.get("csds2")));
					} else
						JOptionPane.showMessageDialog(null, "this student is in " + studentDepart + " department",
								"System message", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		searchbtn.setFont(new Font("Arial", Font.PLAIN, 14));
		searchbtn.setBounds(267, 40, 123, 19);
		panel.add(searchbtn);

		JLabel addDegreelbl = new JLabel("Upbate student degree");
		addDegreelbl.setFont(new Font("Arial", Font.PLAIN, 14));
		addDegreelbl.setBounds(155, 11, 150, 20);
		panel.add(addDegreelbl);

		JLabel systemADlbl = new JLabel("computer architecture");
		systemADlbl.setFont(new Font("Arial", Font.PLAIN, 14));
		systemADlbl.setBounds(20, 96, 102, 14);
		add(systemADlbl);

		JLabel algolbl = new JLabel("Algorithms");
		algolbl.setFont(new Font("Arial", Font.PLAIN, 14));
		algolbl.setBounds(20, 130, 85, 14);
		add(algolbl);

		JLabel fileslbl = new JLabel("data structuer 2");
		fileslbl.setFont(new Font("Arial", Font.PLAIN, 14));
		fileslbl.setBounds(23, 173, 121, 14);
		add(fileslbl);

		JLabel SEAlbl = new JLabel("Software advanced");
		SEAlbl.setFont(new Font("Arial", Font.PLAIN, 14));
		SEAlbl.setBounds(23, 224, 121, 14);
		add(SEAlbl);

		JLabel oslbl = new JLabel("Operating systems");
		oslbl.setFont(new Font("Arial", Font.PLAIN, 14));
		oslbl.setBounds(23, 265, 121, 14);
		add(oslbl);

		computerArch = new JTextField();
		computerArch.setBounds(178, 94, 60, 20);
		add(computerArch);
		computerArch.setColumns(10);

		algo = new JTextField();
		algo.setColumns(10);
		algo.setBounds(178, 128, 60, 20);
		add(algo);

		dataSt2 = new JTextField();
		dataSt2.setColumns(10);
		dataSt2.setBounds(181, 171, 60, 20);
		add(dataSt2);

		software2 = new JTextField();
		software2.setColumns(10);
		software2.setBounds(181, 222, 60, 20);
		add(software2);

		operatingSystem = new JTextField();
		operatingSystem.setColumns(10);
		operatingSystem.setBounds(181, 263, 60, 20);
		add(operatingSystem);

		JButton savebtn = new JButton("Save changes");
		savebtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer studID = Integer.parseInt(studentID.getText());
				updateAction(dataSt2, "csds2", studID);
				updateAction(algo, "csalgo", studID);
				updateAction(operatingSystem, "csoc", studID);
				updateAction(computerArch, "csca", studID);
				updateAction(software2, "csse2", studID);
				if (!SubmitteDSubjects.equals("")) {
					String mess = SubmitteDSubjects + " degrees has been updated";
					JOptionPane.showMessageDialog(null, mess, "log", JOptionPane.INFORMATION_MESSAGE);
					SubmitteDSubjects = "";
				}

			}
		});
		savebtn.setFont(new Font("Arial", Font.PLAIN, 14));
		savebtn.setBounds(293, 267, 128, 36);
		add(savebtn);
	}

	public void updateAction(JTextField field, String code, int studentId) {
		String deg = field.getText();
		if (!deg.equals("")) {
			String mess = hand.updateStudentDegree(studentId, code, Integer.parseInt(deg));
			if (!mess.equals("ok"))
				JOptionPane.showMessageDialog(null, mess, "entary error", JOptionPane.WARNING_MESSAGE);
			else
				SubmitteDSubjects += " " + code + " ";

		}
	}
}
