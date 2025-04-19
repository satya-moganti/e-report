package com.re.ereport;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Employee {
	private Long id;
	private String firstname;
	private String lastname;
	private Long salary;
	private Long managerId;
	
	private Integer hierachyIndex;
	
	public Integer getHierachyIdex() {
		return hierachyIndex;
	}
	public void setHierachyIdex(Integer hierachyIdex) {
		this.hierachyIndex = hierachyIdex;
	}
	private List<Employee> subordinates = new ArrayList<>();
	
	
	
	public List<Employee> getSubordinates() {
		return subordinates;
	}
	public void setSubordinates(List<Employee> subordinates) {
		this.subordinates = subordinates;
	}
	public Employee() {

	}
	public Employee(Long id, String firstname, String lastname, Long salary, Long managerId) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.salary = salary;
		this.managerId = managerId;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Long getSalary() {
		return salary;
	}
	public void setSalary(Long salary) {
		this.salary = salary;
	}



	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", salary=" + salary
				+ ", managerId=" + managerId + ", hierachyIndex="+ hierachyIndex +  " ]";
	}
	public Long getManagerId() {
		return managerId;
	}
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}
	@Override
	public int hashCode() {
		return Objects.hash(firstname, id, lastname, managerId, salary);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(firstname, other.firstname) && Objects.equals(id, other.id)
				&& Objects.equals(lastname, other.lastname) && Objects.equals(managerId, other.managerId)
				&& Objects.equals(salary, other.salary);
	}






}
