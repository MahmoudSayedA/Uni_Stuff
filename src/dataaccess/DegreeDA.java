package dataaccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.StudentInfo;

/*
 * data access class for dealing with stored data on database
 * for classes provide degrees
 */
/**
 * @author Mahmoud
 *
 */
public class DegreeDA {
	private String databaseName = "Uni_Stuff";
	// instance from DatabaseConnector to establish the connection
	private DatabaseConnector conn;
	// instance from Statement class to execute queries
	private Statement stat;
	// instance from Result set class to hold the coming data from database
	private ResultSet result;

	public DegreeDA() {
		this.conn = new DatabaseConnector(databaseName);
	}

	// insert student specific degree
	public boolean setStudentDegree(int studentId, String subjectCode, int degree) {
		// query to be executed
		String query = "INSERT INTO [dbo].[StudentSubject](studentId,code,degree) VALUES(" + studentId + ",'"
				+ subjectCode.toLowerCase() + "'," + degree + ")";

		try {
			// create the statement and execute the query
			stat = this.conn.connect().createStatement();
			stat.execute(query);
			this.conn.disConnect();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			this.conn.disConnect();
		}
		return false;
	}

	// retrieve all student info -> id, name, department, sum of student degree
	public List<StudentInfo> getStudentsInfo() {
		// query to be executed
		String query = "select s.id,s.fname,s.lname, s.department, deg.sumDegree " + "from Student s, "
				+ "(select ss.studentId ,sum(ss.degree) as 'sumDegree' " + "from Student s,StudentSubject ss "
				+ "where s.id=ss.studentId " + "group by studentId) deg " + "where s.id=deg.studentId";

		try {
			// create the statement and execute the query
			stat = this.conn.connect().createStatement();
			result = stat.executeQuery(query);
			// store the result in a list
			List<StudentInfo> match = new ArrayList<>();
			while (result.next()) {
				int id = result.getInt("id");
				int sumDeg = result.getInt("sumDegree");
				String fname = result.getString("fname");
				String lname = result.getString("lname");
				String deprt = result.getString("department");
				match.add(new StudentInfo(id, sumDeg, fname, lname, deprt));
			}
			this.conn.disConnect();
			return match;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			this.conn.disConnect();
		}
		return null;
	}

	// update student degree in a specific subject
	public boolean updateStudentDegree(int studentId, String subjectCode, int degree) {
		// query to be executed
		String query = "update StudentSubject set degree = " + degree + "where studentId = " + studentId
				+ " and code ='" + subjectCode + "'";
		try {
			// create the statement and execute the query
			stat = this.conn.connect().createStatement();
			stat.execute(query);
			this.conn.disConnect();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			this.conn.disConnect();
		}
		return false;
	}

	// Retrieve a student degree using its id
	public Map<String, Integer> getStudentDegree(int studentId) {
		// query to be executed
		String query = "select ss.degree,ss.code from StudentSubject as ss " + "where studentId = " + studentId;
		try {
			// create the statement and execute the query
			stat = this.conn.connect().createStatement();
			result = stat.executeQuery(query);
			// store the result in a map
			Map<String, Integer> map = new HashMap<>();
			while (result.next()) {
				int degree = result.getInt("degree");
				String code = result.getString("code");
				map.put(code, degree);
			}
			this.conn.disConnect();
			return map;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			this.conn.disConnect();
		}
		return null;
	}

}
