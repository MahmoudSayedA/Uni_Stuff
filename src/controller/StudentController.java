package controller;

import java.util.List;

import dataaccess.StudentDA;
import model.Gender;
import model.Student;
import model.Studentdata;

/*
 * controller class that link between data access 
 * class on student and the view of Student frames for the user
 */
/**
 * @author Mahmoud
 *
 */
public class StudentController {
	// instance of student date access class that deal with database
	private StudentDA dataAccess;

	public StudentController() {
		this.dataAccess = StudentDA.getInstance();
	}

	// add new student
	public String addStudent(String fName, String lName, String address, String department, Gender gender) {
		// check of any wrong entry from the user
		if (gender == null)
			gender = Gender.UNDEFINED;
		if (fName.equals("") || lName.equals("") || address.equals("") || department.equals("") || gender.equals(null))
			return "enter all required fields";

		if (this.dataAccess.addStudent(new Student(fName, lName, address, department, gender)))
			return ("ok");

		return "Student not added access problem happend";

	}

	// retrieve a student using its id
	public Student getStudentById(int id) {
		return this.dataAccess.getStudentById(id);
	}

	// update student info
	public String updateStudent(int id, String fName, String lName, String address, String department, Gender gender) {
		// check if any wrong entry from user
		Student student = this.dataAccess.getStudentById(id);
		if (fName != null && !fName.equals(""))
			student.setfName(fName);
		if (lName != null && !lName.equals(""))
			student.setlName(lName);
		if (address != null && !address.equals(""))
			student.setAddress(address);
		if (department != null && !department.equals(""))
			student.setDepartment(department);
		if (gender != null)
			student.setGender(gender);
		if (gender == null)
			student.setGender(Gender.UNDEFINED);

		if (this.dataAccess.updateStudent(student))
			return "ok";

		else
			return "problem happend with access";

	}

	// delete Student using its id
	public String deleteStudent(int id) {
		if (this.dataAccess.deleteStudent(id))
			return "ok";
		return "problem happend with access";

	}

	// retrieve all student in a department
	public List<Student> getStudentsByDepartment(String department) {
		return this.dataAccess.getStudentsByDepartment(department);
	}

	// retrieve all student
	public List<Student> getStudents() {
		return this.getStudents();
	}

	// retrieve all student info -> id, first name, last name
	public String[][] getStudentsInfo() {
		List<Studentdata> temp = this.dataAccess.getStudents();
		if (temp == null)
			return null;
		// to convert it to 2d array
		String[][] data = new String[temp.size()][3];
		for (int i = 0; i < temp.size(); i++) {
			Studentdata studentdata = temp.get(i);
			data[i][0] = Integer.toString(studentdata.getId());
			data[i][1] = studentdata.getFname();
			data[i][2] = studentdata.getLname();
		}
		return data;

	}

	// retrieve all student in a department
	public String[][] getStudentsByDepartmentInfo(String department) {
		List<Student> temp = this.dataAccess.getStudentsByDepartment(department);
		if (temp == null)
			return null;
		// to convert it to 2d array
		String[][] data = new String[temp.size()][3];
		for (int i = 0; i < temp.size(); i++) {
			Student st = temp.get(i);
			Studentdata studentdata = new Studentdata(st.getId(), st.getfName(), st.getlName());
			data[i][0] = Integer.toString(studentdata.getId());
			data[i][1] = studentdata.getFname();
			data[i][2] = studentdata.getLname();
		}
		return data;
	}

	// get Student department code using its id
	public String getDepartment(int StudentId) {
		Student st = this.getStudentById(StudentId);
		if (st == null)
			return "Student not found";
		return st.getDepartment();
	}
}
