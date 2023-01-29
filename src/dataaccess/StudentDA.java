package dataaccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Gender;
import model.Student;
import model.Studentdata;
import view.IObserver;

/*
 * data access class for dealing with stored data on database
 * for classes provide Student data
 */
/**
 * @author Mahmoud
 *
 */

public class StudentDA implements ISubject {
	private List<IObserver> myObservers; // to store observer views
	private String dbName = "Uni_Stuff";/// database name
	// instance from Result set class to hold the coming data from database
	private ResultSet result;
	// instance from Statement class to execute queries
	private Statement stat;
	// instance from DatabaseConnector to establish the connection
	private DatabaseConnector con;
	// to apply singleton
	private static StudentDA single;

	private StudentDA() {
		this.myObservers = new ArrayList<IObserver>();
		this.con = new DatabaseConnector(dbName);
	}

	// to get instance from Student data access class
	public static synchronized StudentDA getInstance() {
		if (single == null) {
			single = new StudentDA();
		}
		return single;

	}

	// add new Student
	public boolean addStudent(Student student) {
		// query to be executed
		String query = "INSERT INTO Student(fname,lname,address,department,gender) VALUES(" + "'"
				+ student.getfName().toLowerCase() + "','" + student.getlName().toLowerCase() + "','"
				+ student.getAddress().toLowerCase() + "', '" + student.getDepartment().toLowerCase() + "','"
				+ student.getGender().toString().toLowerCase() + "')";
		try {
			// create the statement and execute the query
			stat = this.con.connect().createStatement();
			stat.execute(query);
			this.con.disConnect();
			// to notify the views with the new changes
			notifyObservers();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			this.con.disConnect();
			return false;
		}
	}

	// check if there is a student with this id or not
	public boolean isFound(int id) {
		if (getStudentById(id) != null)
			return true;
		return false;
	}

	// delete student
	public boolean deleteStudent(int id) {
		// query to be executed
		String Query = "DELETE FROM Student WHERE id=" + id;
		try {
			// create the statement and execute the query
			stat = this.con.connect().createStatement();
			stat.execute(Query);
			this.con.disConnect();
			// to notify the views with the new changes
			notifyObservers();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			this.con.disConnect();
			return false;
		}

	}

	public boolean updateStudent(Student student) {
		// query to be executed
		String query = "UPDATE Student SET fname='" + student.getfName().toLowerCase() + "', lname='"
				+ student.getlName().toLowerCase() + "', address='" + student.getAddress().toLowerCase()
				+ "', department='" + student.getDepartment().toLowerCase() + "',gender='"
				+ student.getGender().toString().toLowerCase() + "' WHERE id =" + student.getId();

		try {
			// create the statement and execute the query
			stat = this.con.connect().createStatement();
			stat.execute(query);
			this.con.disConnect();
			// to notify the views with the new changes
			notifyObservers();
			return true;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			this.con.disConnect();
			return false;
		}

	}

	public List<Student> getStudentsByDepartment(String department) {
		// query to be executed
		String query = "SELECT * FROM Student WHERE department='" + department.toLowerCase() + "' ORDER BY id";
		try {
			// create the statement and execute the query
			stat = this.con.connect().createStatement();
			result = stat.executeQuery(query);
			// store the result in a list
			List<Student> match = new ArrayList<>();
			while (result.next()) {
				int id = result.getInt("id");
				String fName = result.getString("fname");
				String lName = result.getString("lname");
				String address = result.getString("address");
				String depart = result.getString("department");
				Gender gender = Gender.getType(result.getString("gender"));
				match.add(new Student(id, fName, lName, address, depart, gender));
			}
			this.con.disConnect();
			return match;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			this.con.disConnect();
			return null;
		}

	}

	public Student getStudentById(int id) {
		// query to be executed
		String query = "SELECT * FROM Student WHERE id=" + id;
		try {
			// create the statement and execute the query
			stat = this.con.connect().createStatement();
			result = stat.executeQuery(query);
			if (result.next()) {
				int ID = result.getInt("id");
				String fName = result.getString("fname");
				String lName = result.getString("lname");
				String address = result.getString("address");
				String department = result.getString("department");
				Gender gender = Gender.getType(result.getString("gender"));
				this.con.disConnect();
				return new Student(ID, fName, lName, address, department, gender);//////////
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		this.con.disConnect();
		return null;
	}

	public List<Studentdata> getStudents() {
		// query to be executed
		String query = "SELECT id,fname,lname FROM Student ORDER BY id";
		try {
			// create the statement and execute the query
			stat = this.con.connect().createStatement();
			result = stat.executeQuery(query);
			// store the result in a list
			List<Studentdata> match = new ArrayList<>();
			while (result.next()) {
				int id = result.getInt("id");
				String fName = result.getString("fname");
				String lName = result.getString("lname");
				match.add(new Studentdata(id, fName, lName));
			}
			this.con.disConnect();
			return match;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			this.con.disConnect();
			return null;
		}

	}

	// register to be observer and be notified with new changes
	@Override
	public boolean register(IObserver obs) {

		return this.myObservers.add(obs);
	}

	// unregister
	@Override
	public boolean unRegister(IObserver obs) {

		return this.myObservers.remove(obs);
	}

	// notify the observer with new changes
	@Override
	public void notifyObservers() {
		// myObservers.forEach(obs->obs.update());
	}

}
