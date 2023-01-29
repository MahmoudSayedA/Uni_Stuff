package view.ISScreens;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import controller.DegreeController;
import controller.StudentController;

@SuppressWarnings("serial")
public class ISUpdateDegreePanel extends JPanel {
	private JTextField systemAD;
	private JTextField algo;
	private JTextField files;
	private JTextField database2;
	private JTextField software2;
	private JTextField operatingSystem;
	private JTextField studentID;
	private String SubmitteDSubjects;
	private DegreeController hand;
	private String dep = "is";

	/**
	 * Create the panel.
	 */
	public ISUpdateDegreePanel() {
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
						files.setText(Integer.toString(degrees.get("isfiles")));
						algo.setText(Integer.toString(degrees.get("csalgo")));
						operatingSystem.setText(Integer.toString(degrees.get("csos")));
						software2.setText(Integer.toString(degrees.get("csse2")));
						systemAD.setText(Integer.toString(degrees.get("issa&d")));
						database2.setText(Integer.toString(degrees.get("isdb2")));
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

		JLabel systemADlbl = new JLabel("A & D system ");
		systemADlbl.setFont(new Font("Arial", Font.PLAIN, 14));
		systemADlbl.setBounds(20, 88, 102, 14);
		add(systemADlbl);

		JLabel algolbl = new JLabel("Algorithms");
		algolbl.setFont(new Font("Arial", Font.PLAIN, 14));
		algolbl.setBounds(20, 130, 85, 14);
		add(algolbl);

		JLabel fileslbl = new JLabel("File management");
		fileslbl.setFont(new Font("Arial", Font.PLAIN, 14));
		fileslbl.setBounds(20, 177, 121, 14);
		add(fileslbl);

		JLabel database2lbl = new JLabel("Database 2");
		database2lbl.setFont(new Font("Arial", Font.PLAIN, 14));
		database2lbl.setBounds(20, 226, 85, 14);
		add(database2lbl);

		JLabel SEAlbl = new JLabel("Software advanced");
		SEAlbl.setFont(new Font("Arial", Font.PLAIN, 14));
		SEAlbl.setBounds(20, 279, 121, 14);
		add(SEAlbl);

		JLabel oslbl = new JLabel("Operating systems");
		oslbl.setFont(new Font("Arial", Font.PLAIN, 14));
		oslbl.setBounds(20, 326, 121, 14);
		add(oslbl);

		systemAD = new JTextField();
		systemAD.setBounds(178, 86, 60, 20);
		add(systemAD);
		systemAD.setColumns(10);

		algo = new JTextField();
		algo.setColumns(10);
		algo.setBounds(178, 128, 60, 20);
		add(algo);

		files = new JTextField();
		files.setColumns(10);
		files.setBounds(178, 175, 60, 20);
		add(files);

		database2 = new JTextField();
		database2.setColumns(10);
		database2.setBounds(178, 224, 60, 20);
		add(database2);

		software2 = new JTextField();
		software2.setColumns(10);
		software2.setBounds(178, 277, 60, 20);
		add(software2);

		operatingSystem = new JTextField();
		operatingSystem.setColumns(10);
		operatingSystem.setBounds(178, 324, 60, 20);
		add(operatingSystem);

		JButton savebtn = new JButton("Save changes");

		savebtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Integer studID = Integer.parseInt(studentID.getText());
				updateAction(files, "isfiles", studID);
				updateAction(algo, "csalgo", studID);
				updateAction(operatingSystem, "csos", studID);
				updateAction(systemAD, "issa&d", studID);
				updateAction(database2, "isdb2", studID);
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
