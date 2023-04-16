package myStudy.com;

import java.sql.Date;

public class myStudyVO {
	
	private int employee_id;          //HR에 EMPLOYEES 테이블 attribute
	private String first_name;
	private String last_name;
	private String job_id;
	private Date hire_date;
	private int salary;
	
	public int getEmployee_id() {  //게터세터
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getJob_id() {
		return job_id;
	}
	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}
	public Date getHire_date() {
		return hire_date;
	}
	public void setHire_date(Date hire_date) {
		this.hire_date = hire_date;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	@Override
	public String toString() { //to string 오버라이드
		return "myStudyVO [employee_id=" + employee_id + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", job_id=" + job_id + ", hire_date=" + hire_date + ", salary=" + salary + "]";
	}

}
