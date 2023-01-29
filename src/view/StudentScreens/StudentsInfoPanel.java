package view.StudentScreens;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import controller.DegreeController;

import javax.swing.JTable;

@SuppressWarnings("serial")
public class StudentsInfoPanel extends JPanel {
	private JTable table;
	private String data[][];
	private JPanel mine;

	/**
	 * Create the panel.
	 */
	public StudentsInfoPanel() {
		setBackground(UIManager.getColor("activeCaption"));
		setLayout(null);
		mine=this;
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 11, 452, 340);
		add(scrollPane);
		DegreeController hand = new DegreeController();
		data = hand.getStudentInfo();
		String[] header = { "id", "fName", "lName", "department", "sum degree" };
		// fill table with student information
		table = new JTable(data, header);
		scrollPane.setViewportView(table);
		JButton printbtn = new JButton("print student info");
		printbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// create header and footer for the PDF
				MessageFormat head = new MessageFormat("Student information");
				MessageFormat foot = new MessageFormat("page {1}");
				try {
					// confirm and print
					JOptionPane.showMessageDialog(null, "Do you want to print", "system message",
							JOptionPane.QUESTION_MESSAGE);
					table.print(JTable.PrintMode.NORMAL, head, foot);
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}

			}
		});
		printbtn.setToolTipText("press to print it");
		printbtn.setFont(new Font("Arial", Font.PLAIN, 14));
		printbtn.setBounds(197, 350, 150, 40);
		add(printbtn);
		
		JButton refresh = new JButton("refresh");
		refresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) { // problem with it -> don't make a refresh
				// fill table with student information
				scrollPane.remove(table);
				mine.remove(scrollPane);
				data = hand.getStudentInfo();
				table = new JTable(data, header);
				
			}
		});
		refresh.setToolTipText("refresh");
		refresh.setFont(new Font("Arial", Font.PLAIN, 14));
		refresh.setBounds(50, 350, 80, 25);
		add(refresh);

	}
}
