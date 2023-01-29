package model;

public class StudentInfo {
	private int id, sumDeg;
	private String fname, lname, dep;

	public StudentInfo(int id, int sumDeg, String fname, String lname, String dep) {
		super();
		this.id = id;
		this.sumDeg = sumDeg;
		this.fname = fname;
		this.lname = lname;
		this.dep = dep;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSumDeg() {
		return sumDeg;
	}

	public void setSumDeg(int sumDeg) {
		this.sumDeg = sumDeg;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

}
