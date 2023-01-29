package model;

public class Student {
	private int id;
	private String fName, lName, address, department;
	private Gender gender;

	public Student(int id, String fName, String lName, String address, String department, Gender gender) {
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.address = address;
		this.department = department;
	}

	public Student(String fName, String lName, String address, String department, Gender gender) {
		this.fName = fName;
		this.lName = lName;
		this.address = address;
		this.department = department;
		this.gender = gender;
	}

	public Student() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", fName=" + fName + ", lName=" + lName + ", address=" + address + ", department="
				+ department + "]";
	}

	public boolean equals(Student st) {
		if (!(this.id == st.getId()))
			return false;
		if (!this.fName.equals(st.getfName()))
			return false;
		if (!this.lName.equals(st.getlName()))
			return false;
		if (!this.address.equals(st.getAddress()))
			return false;
		if (!this.department.equals(st.getDepartment()))
			return false;
		return true;

	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

}