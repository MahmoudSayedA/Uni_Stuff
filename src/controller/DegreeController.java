package controller;

import java.util.List;
import java.util.Map;

import dataaccess.DegreeDA;
import model.StudentInfo;

/*
 * controller class that link between data access 
 * class on degree and the view of degrees for the user
 */
/**
 * @author Mahmoud
 *
 */
public class DegreeController {
	// instance of degree data access class that can deal with database
	private DegreeDA dataAccess;

	public DegreeController() {
		this.dataAccess = new DegreeDA();
	}

	// add student degree for a specific subject
	public String setDegree(int studentId, String subjectCode, int degree) {
		// handel wrong entary from the user
		if (degree < 0 || degree > 100)
			return "invalid degree";
		if (subjectCode.equals(""))
			return "anvalid subject code";
		if (studentId < 1)
			return "anvalid student id";
		if (this.dataAccess.setStudentDegree(studentId, subjectCode, degree))
			return "ok";
		else
			return "access problem happend";
	}

	// Retrieve all students info -> id, first name, last name, department, and sum
	// of all his degrees
	public String[][] getStudentInfo() {
		List<StudentInfo> temp = this.dataAccess.getStudentsInfo();
		if (temp == null)
			return null;
		// to convert the list to 2d array of string to added to JTable
		String[][] data = new String[temp.size()][5];
		for (int i = 0; i < temp.size(); i++) {
			StudentInfo st = temp.get(i);
			data[i][0] = Integer.toString(st.getId());
			data[i][1] = st.getFname();
			data[i][2] = st.getLname();
			data[i][3] = st.getDep();
			data[i][4] = Integer.toString(st.getSumDeg());
		}
		return data;
	}

	// Retrieve a student degree using its id
	public Map<String, Integer> getStudentDegree(int studentId) {
		return this.dataAccess.getStudentDegree(studentId);
	}

	// update student degree in a specific subject
	public String updateStudentDegree(int studentId, String subjectCode, int degree) {
		// to handle wrong entry
		if (subjectCode.equals(""))
			return "subject code is requierd";
		if (degree < 0 || degree > 100)
			return "degree is not semantic";
		if (studentId < 1)
			return "student id is not valid";

		if (this.dataAccess.updateStudentDegree(studentId, subjectCode, degree))
			return "ok";
		return "access problem happend";
	}
}
