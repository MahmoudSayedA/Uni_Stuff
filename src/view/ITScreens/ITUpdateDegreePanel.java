package view.ITScreens;

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

public class ITUpdateDegreePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField studentID;
	private JTextField signals;
	private JTextField algo;
	private JTextField dataArch;
	private JTextField dataComp;
	private JTextField network2;
	private JTextField operatingSystem;
	private String dep = "cs";

	/**
	 * Create the panel.
	 */
	private String SubmitteDSubjects;
	private DegreeController hand;

	public ITUpdateDegreePanel() {
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
						signals.setText(Integer.toString(degrees.get("its")));
						algo.setText(Integer.toString(degrees.get("csalgo")));
						operatingSystem.setText(Integer.toString(degrees.get("csos")));
						dataComp.setText(Integer.toString(degrees.get("itdc")));
						network2.setText(Integer.toString(degrees.get("itn2")));
						dataArch.setText(Integer.toString(degrees.get("csca")));
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

		JLabel systemADlbl = new JLabel("Signals");
		systemADlbl.setFont(new Font("Arial", Font.PLAIN, 14));
		systemADlbl.setBounds(20, 92, 102, 14);
		add(systemADlbl);

		JLabel algolbl = new JLabel("Algorithms");
		algolbl.setFont(new Font("Arial", Font.PLAIN, 14));
		algolbl.setBounds(20, 134, 85, 14);
		add(algolbl);

		JLabel fileslbl = new JLabel("computer arch");
		fileslbl.setFont(new Font("Arial", Font.PLAIN, 14));
		fileslbl.setBounds(20, 181, 121, 14);
		add(fileslbl);

		JLabel database2lbl = new JLabel("Data compr");
		database2lbl.setFont(new Font("Arial", Font.PLAIN, 14));
		database2lbl.setBounds(20, 230, 85, 14);
		add(database2lbl);

		JLabel SEAlbl = new JLabel("netowrk advanced");
		SEAlbl.setFont(new Font("Arial", Font.PLAIN, 14));
		SEAlbl.setBounds(20, 283, 121, 14);
		add(SEAlbl);

		JLabel oslbl = new JLabel("Operating systems");
		oslbl.setFont(new Font("Arial", Font.PLAIN, 14));
		oslbl.setBounds(20, 330, 121, 14);
		add(oslbl);

		signals = new JTextField();
		signals.setBounds(178, 90, 60, 20);
		add(signals);
		signals.setColumns(10);

		algo = new JTextField();
		algo.setColumns(10);
		algo.setBounds(178, 132, 60, 20);
		add(algo);

		dataArch = new JTextField();
		dataArch.setColumns(10);
		dataArch.setBounds(178, 179, 60, 20);
		add(dataArch);

		dataComp = new JTextField();
		dataComp.setColumns(10);
		dataComp.setBounds(178, 228, 60, 20);
		add(dataComp);

		network2 = new JTextField();
		network2.setColumns(10);
		network2.setBounds(178, 281, 60, 20);
		add(network2);

		operatingSystem = new JTextField();
		operatingSystem.setColumns(10);
		operatingSystem.setBounds(178, 328, 60, 20);
		add(operatingSystem);

		JButton savebtn = new JButton("Save changes");
		savebtn.setFont(new Font("Arial", Font.PLAIN, 14));
		savebtn.setBounds(293, 267, 128, 36);
		savebtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Integer studID = Integer.parseInt(studentID.getText());
				updateAction(dataComp, "itdc", studID);
				updateAction(algo, "csalgo", studID);
				updateAction(operatingSystem, "csoc", studID);
				updateAction(dataArch, "csca", studID);
				updateAction(network2, "itn2", studID);
				updateAction(signals, "its", studID);
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
