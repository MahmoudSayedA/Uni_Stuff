
create database Uni_Stuff
use Uni_Stuff;

CREATE TABLE Department
(
  departmentCode VARCHAR(50) NOT NULL,
  departmentName VARCHAR(200) NOT NULL,
  PRIMARY KEY (departmentCode),
  UNIQUE (departmentName)
);

CREATE TABLE Subject
(
  name VARCHAR(200) NOT NULL,
  code VARCHAR(50) NOT NULL,
  PRIMARY KEY (code),
  UNIQUE (name)
);

CREATE TABLE Users
(
  userName VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  departmentName VARCHAR(50),
  PRIMARY KEY (userName),
  FOREIGN KEY (departmentName) REFERENCES 
  Department(departmentCode) on delete set null on update cascade
);

CREATE TABLE DepartmentSubject
(
  departmentCode VARCHAR(50) NOT NULL,
  subjectCode VARCHAR(50) NOT NULL,
  PRIMARY KEY (departmentCode, subjectCode),
  FOREIGN KEY (departmentCode) REFERENCES Department(departmentCode),
  FOREIGN KEY (subjectCode) REFERENCES Subject(code) on delete cascade on update cascade
);

CREATE TABLE Student
(
  id INT NOT NULL identity(1,1),
  address VARCHAR(200) NOT NULL,
  fname VARCHAR(200) NOT NULL,
  lname VARCHAR(200) NOT NULL,
  gender VARCHAR(200) NOT NULL,
  department VARCHAR(50) ,
  PRIMARY KEY (id),
  FOREIGN KEY (department) REFERENCES Department(departmentCode)
  on delete set null on update cascade
);

CREATE TABLE StudentSubject
(
  degree INT NOT NULL,
  studentId INT NOT NULL,
  code VARCHAR(50) NOT NULL,
  PRIMARY KEY (studentId, code),
  FOREIGN KEY (studentId) REFERENCES Student(id),
  FOREIGN KEY (code) REFERENCES Subject(code)
  on delete cascade on update cascade
);
