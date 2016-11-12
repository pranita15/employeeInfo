package com.EmpInfo.Beans;

public class EmpMasterBean {

	
	private Integer empId;
	private String empName;
	private String dept;
	private String designation;
	private Integer salary;
	private String joiningDate;
	
	public EmpMasterBean(){}
	
	public EmpMasterBean(Integer empId,String empName,String dept,String designation,Integer salary,String joiningDate){
		this.empId=empId;
		this.empName=empName;
		this.dept=dept;
		this.designation=designation;
		this.salary= salary;
		this.joiningDate=joiningDate;
	}
	
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	public String getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}
	
	
	
}
