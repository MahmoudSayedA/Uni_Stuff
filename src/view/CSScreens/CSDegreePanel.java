package view.CSScreens;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

import controller.DegreeController;
import controller.StudentController;

public class CSDegreePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField computerArch;
	private JTextField algo;
	private JTextField dataSt2;
	private JTextField software2;
	private JTextField operatingSystem;
	private DegreeController hand;
	private String SubmitteDSubjects = "";
	private int selectedRow;

	String[][] data;

	/**
	 * Create the panel.
	 */
	public CSDegreePanel() {
		setBackground(UIManager.getColor("activeCaption"));
		this.setLayout(null);
		StudentController studentController = new StudentController();
		data = studentController.getStudentsByDepartmentInfo("cs");
		String[] tableHeader = { "id", "fName", "lName" };
		table = new JTable(data, tableHeader);
		table.setBackground(Color.WHITE);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 260, 367);
		scrollPane.setViewportView(table);////////////
		add(scrollPane);

		JLabel addDegreelbl = new JLabel("Add degree");
		addDegreelbl.setFont(new Font("Arial", Font.PLAIN, 14));
		addDegreelbl.setBounds(372, 12, 85, 14);
		add(addDegreelbl);

		JLabel systemADlbl = new JLabel("computer architecture");
		systemADlbl.setFont(new Font("Arial", Font.PLAIN, 14));
		systemADlbl.setBounds(280, 56, 102, 14);
		add(systemADlbl);

		JLabel algolbl = new JLabel("Algorithms");
		algolbl.setFont(new Font("Arial", Font.PLAIN, 14));
		algolbl.setBounds(280, 98, 85, 14);
		add(algolbl);

		JLabel fileslbl = new JLabel("data structuer 2");
		fileslbl.setFont(new Font("Arial", Font.PLAIN, 14));
		fileslbl.setBounds(280, 145, 121, 14);
		add(fileslbl);

		JLabel SEAlbl = new JLabel("Software advanced");
		SEAlbl.setFont(new Font("Arial", Font.PLAIN, 14));
		SEAlbl.setBounds(280, 247, 121, 14);
		add(SEAlbl);

		JLabel oslbl = new JLabel("Operating systems");
		oslbl.setFont(new Font("Arial", Font.PLAIN, 14));
		oslbl.setBounds(280, 294, 121, 14);
		add(oslbl);

		computerArch = new JTextField();
		computerArch.setBounds(438, 54, 60, 20);
		add(computerArch);
		computerArch.setColumns(10);

		algo = new JTextField();
		algo.setColumns(10);
		algo.setBounds(438, 96, 60, 20);
		add(algo);

		dataSt2 = new JTextField();
		dataSt2.setColumns(10);
		dataSt2.setBounds(438, 143, 60, 20);
		add(dataSt2);

		software2 = new JTextField();
		software2.setColumns(10);
		software2.setBounds(438, 245, 60, 20);
		add(software2);

		operatingSystem = new JTextField();
		operatingSystem.setColumns(10);
		operatingSystem.setBounds(438, 292, 60, 20);
		add(operatingSystem);

		JButton savaDegree = new JButton("Sava");
		savaDegree.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// send to database
				hand = new DegreeController();
				int studentId = Integer.parseInt(data[selectedRow][0]);
				saveAction(algo, "csalgo", studentId);
				saveAction(operatingSystem, "csos", studentId);
				saveAction(computerArch, "csca", studentId);
				saveAction(dataSt2, "csds2", studentId);
				saveAction(software2, "csse2", studentId);
				if (!SubmitteDSubjects.equals("")) {
					String mess = SubmitteDSubjects + " degrees has been saved";
					JOptionPane.showMessageDialog(null, mess, "log", JOptionPane.INFORMATION_MESSAGE);
					SubmitteDSubjects = "";
				}

			}
		});

		savaDegree.setFont(new Font("Arial", Font.PLAIN, 14));
		savaDegree.setBounds(372, 342, 89, 23);
		add(savaDegree);
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectRowAcction(e);
			}

		});
	}

	public void saveAction(JTextField field, String code, int studentId) {
		String deg = field.getText();
		if (!deg.equals("")) {
			String mess = hand.setDegree(studentId, code, Integer.parseInt(deg));
			if (!mess.equals("ok"))
				JOptionPane.showMessageDialog(null, mess, "entary error", JOptionPane.WARNING_MESSAGE);
			else
				SubmitteDSubjects += " " + code + " ";

		}
	}

	private void selectRowAcction(MouseEvent event) {
		System.out.println(table.getSelectedRow());
		selectedRow = table.getSelectedRow();
	}
}
