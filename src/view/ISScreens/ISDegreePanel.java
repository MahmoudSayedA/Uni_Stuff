package view.ISScreens;

import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.DegreeController;
import controller.StudentController;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Color;

public class ISDegreePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField systemAD;
	private JTextField algo;
	private JTextField files;
	private JTextField database2;
	private JTextField software2;
	private JTextField operatingSystem;
	String[][] data;
	private DegreeController hand;
	private String SubmitteDSubjects = "";
	private int selectedRow;

	/**
	 * Create the panel.
	 */
	public ISDegreePanel() {
		setBackground(UIManager.getColor("activeCaption"));
		this.setLayout(null);
		StudentController studentController = new StudentController();
		data = studentController.getStudentsByDepartmentInfo("is");
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

		JLabel systemADlbl = new JLabel("A & D system ");
		systemADlbl.setFont(new Font("Arial", Font.PLAIN, 14));
		systemADlbl.setBounds(280, 56, 102, 14);
		add(systemADlbl);

		JLabel algolbl = new JLabel("Algorithms");
		algolbl.setFont(new Font("Arial", Font.PLAIN, 14));
		algolbl.setBounds(280, 98, 85, 14);
		add(algolbl);

		JLabel fileslbl = new JLabel("File management");
		fileslbl.setFont(new Font("Arial", Font.PLAIN, 14));
		fileslbl.setBounds(280, 145, 121, 14);
		add(fileslbl);

		JLabel database2lbl = new JLabel("Database 2");
		database2lbl.setFont(new Font("Arial", Font.PLAIN, 14));
		database2lbl.setBounds(280, 194, 85, 14);
		add(database2lbl);

		JLabel SEAlbl = new JLabel("Software advanced");
		SEAlbl.setFont(new Font("Arial", Font.PLAIN, 14));
		SEAlbl.setBounds(280, 247, 121, 14);
		add(SEAlbl);

		JLabel oslbl = new JLabel("Operating systems");
		oslbl.setFont(new Font("Arial", Font.PLAIN, 14));
		oslbl.setBounds(280, 294, 121, 14);
		add(oslbl);

		systemAD = new JTextField();
		systemAD.setBounds(438, 54, 60, 20);
		add(systemAD);
		systemAD.setColumns(10);

		algo = new JTextField();
		algo.setColumns(10);
		algo.setBounds(438, 96, 60, 20);
		add(algo);

		files = new JTextField();
		files.setColumns(10);
		files.setBounds(438, 143, 60, 20);
		add(files);

		database2 = new JTextField();
		database2.setColumns(10);
		database2.setBounds(438, 192, 60, 20);
		add(database2);

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
				hand = new DegreeController();
				int studentId = Integer.parseInt(data[selectedRow][0]);
				saveAction(algo, "csalgo", studentId);
				saveAction(operatingSystem, "csos", studentId);
				saveAction(files, "isfiles", studentId);
				saveAction(systemAD, "issa&d", studentId);
				saveAction(software2, "csse2", studentId);
				saveAction(database2, "isdb2", studentId);
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

	private void selectRowAcction(MouseEvent event) {
		System.out.println(table.getSelectedRow());
		selectedRow = table.getSelectedRow();

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
}
