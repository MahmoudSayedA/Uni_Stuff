package view.DSScreens;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import controller.DegreeController;
import controller.StudentController;

import java.awt.event.ActionListener;
import java.util.Map;
import java.awt.event.ActionEvent;

public class DSUpdateDegreePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField studentID;
	private JTextField lProg;
	private JTextField algo;
	private JTextField lfdata;
	private JTextField anlytics;
	private JTextField dSupport;
	private JTextField operatingSystem;
	private DegreeController hand;
	private String SubmitteDSubjects;
	private String dep = "ds";

	/**
	 * Create the panel.
	 */
	public DSUpdateDegreePanel() {
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
						lProg.setText(Integer.toString(degrees.get("dslp")));
						algo.setText(Integer.toString(degrees.get("csalgo")));
						operatingSystem.setText(Integer.toString(degrees.get("csos")));
						lfdata.setText(Integer.toString(degrees.get("dslfd")));
						anlytics.setText(Integer.toString(degrees.get("dsda")));
						dSupport.setText(Integer.toString(degrees.get("dsds")));
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

		JLabel systemADlbl = new JLabel("Linear progr");
		systemADlbl.setFont(new Font("Arial", Font.PLAIN, 14));
		systemADlbl.setBounds(20, 96, 102, 14);
		add(systemADlbl);

		JLabel algolbl = new JLabel("Algorithms");
		algolbl.setFont(new Font("Arial", Font.PLAIN, 14));
		algolbl.setBounds(20, 138, 85, 14);
		add(algolbl);

		JLabel fileslbl = new JLabel("data learning");
		fileslbl.setFont(new Font("Arial", Font.PLAIN, 14));
		fileslbl.setBounds(20, 172, 121, 14);
		add(fileslbl);

		JLabel database2lbl = new JLabel("Data analytics");
		database2lbl.setFont(new Font("Arial", Font.PLAIN, 14));
		database2lbl.setBounds(20, 221, 85, 14);
		add(database2lbl);

		JLabel SEAlbl = new JLabel("Decision Support");
		SEAlbl.setFont(new Font("Arial", Font.PLAIN, 14));
		SEAlbl.setBounds(20, 274, 121, 14);
		add(SEAlbl);

		JLabel oslbl = new JLabel("Operating systems");
		oslbl.setFont(new Font("Arial", Font.PLAIN, 14));
		oslbl.setBounds(20, 321, 121, 14);
		add(oslbl);

		lProg = new JTextField();
		lProg.setBounds(178, 94, 60, 20);
		add(lProg);
		lProg.setColumns(10);

		algo = new JTextField();
		algo.setColumns(10);
		algo.setBounds(178, 136, 60, 20);
		add(algo);

		lfdata = new JTextField();
		lfdata.setColumns(10);
		lfdata.setBounds(178, 170, 60, 20);
		add(lfdata);

		anlytics = new JTextField();
		anlytics.setColumns(10);
		anlytics.setBounds(178, 219, 60, 20);
		add(anlytics);

		dSupport = new JTextField();
		dSupport.setColumns(10);
		dSupport.setBounds(178, 272, 60, 20);
		add(dSupport);

		operatingSystem = new JTextField();
		operatingSystem.setColumns(10);
		operatingSystem.setBounds(178, 319, 60, 20);
		add(operatingSystem);

		JButton savebtn = new JButton("Save changes");
		savebtn.setFont(new Font("Arial", Font.PLAIN, 14));
		savebtn.setBounds(293, 267, 128, 36);
		savebtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Integer studID = Integer.parseInt(studentID.getText());
				updateAction(lProg, "dslp", studID);
				updateAction(algo, "csalgo", studID);
				updateAction(operatingSystem, "csoc", studID);
				updateAction(lfdata, "dslfd", studID);
				updateAction(anlytics, "dsda", studID);
				updateAction(dSupport, "dsds", studID);
				if (!SubmitteDSubjects.equals("")) {
					String mess = SubmitteDSubjects + " degrees has been updated";
					JOptionPane.showMessageDialog(null, mess, "log", JOptionPane.INFORMATION_MESSAGE);
					SubmitteDSubjects = "";
				}

			}
		});
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
