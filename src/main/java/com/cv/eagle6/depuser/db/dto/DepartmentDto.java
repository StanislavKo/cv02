package com.cv.eagle6.depuser.db.dto;

import java.util.Collection;

public class DepartmentDto {

	private Long id;
	private Long depId;
	private String title;
	private Collection<UserDto> users;

	public DepartmentDto() {
	}
	
	public DepartmentDto(Long id, Long depId, String title) {
		super();
		this.id = id;
		this.depId = depId;
		this.title = title;
	}

	public DepartmentDto(Long id, Long depId, String title, Collection<UserDto> users) {
		super();
		this.id = id;
		this.depId = depId;
		this.title = title;
		this.users = users;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDepId() {
		return depId;
	}

	public void setDepId(Long depId) {
		this.depId = depId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Collection<UserDto> getUsers() {
		return users;
	}

	public void setUsers(Collection<UserDto> users) {
		this.users = users;
	}

}
