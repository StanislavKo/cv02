package com.cv.eagle6.depuser.db.dto;

import java.util.Collection;

public class UserDto {

	private Long id;
	private String firstName;
	private String lastName;
	private DepartmentDto department;

	public UserDto() {
	}

	public UserDto(Long id, String firstName, String lastName, DepartmentDto department) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public DepartmentDto getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentDto department) {
		this.department = department;
	}

}
