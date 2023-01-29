package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JTabbedPane;

import view.CSScreens.CSDegreePanel;
import view.CSScreens.CSUpdateDegreePanel;
import view.DSScreens.DSDegreePanel;
import view.DSScreens.DSUpdateDegreePanel;
import view.ISScreens.ISDegreePanel;
import view.ISScreens.ISUpdateDegreePanel;
import view.ITScreens.ITDegreePanel;
import view.ITScreens.ITUpdateDegreePanel;
import view.StudentScreens.AddStudentPanlel;
import view.StudentScreens.StudentsInfoPanel;
import view.StudentScreens.UpdateStudentPanel;

/**
 * the main view class for dealing with user interaction
 */
/**
 * @author Mahmoud
 *
 */
public class MainFrame extends JFrame implements IObserver {

	private static final long serialVersionUID = 1L;
	private JTabbedPane tab;
	// panel that appears in the main frame tab
	private JPanel addPanel, degreePanel, studentInfoPanel, updateStudentPanel, updateDegreePanel;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainFrame frame = new MainFrame(department);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public MainFrame(String department) {
		setTitle("Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 568, 440);
		update(department);
	}

	@Override
	public void update(String department) {

		this.tab = new JTabbedPane();
		addPanel = new AddStudentPanlel();
		studentInfoPanel = new StudentsInfoPanel();
		updateStudentPanel = new UpdateStudentPanel();
		// create add degree Panel and update degree panel based on user'doctor'
		// department
		if (department.equals("is")) {
			degreePanel = new ISDegreePanel();
			updateDegreePanel = new ISUpdateDegreePanel();
		} else if (department.equals("ds")) {
			degreePanel = new DSDegreePanel();
			updateDegreePanel = new DSUpdateDegreePanel();
		} else if (department.equals("it")) {
			degreePanel = new ITDegreePanel();
			updateDegreePanel = new ITUpdateDegreePanel();
		} else if (department.equals("cs")) {
			degreePanel = new CSDegreePanel();
			updateDegreePanel = new CSUpdateDegreePanel();
		} else {
			degreePanel = null;
			updateDegreePanel = null;
		}

		tab.addTab("add student", addPanel);
		tab.addTab("update student", updateStudentPanel);
		tab.addTab("degree", degreePanel);
		tab.add("udpate Degree", updateDegreePanel);
		tab.addTab("student info", studentInfoPanel);
		getContentPane().add(tab);
	}
}
