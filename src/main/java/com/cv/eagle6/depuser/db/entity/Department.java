package com.cv.eagle6.depuser.db.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "deparment_id_seq", sequenceName = "deparment_id_seq", allocationSize = 1)
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deparment_id_seq")
	@Column(name = "id")
	private Long id;

	@Column(name = "department_id", unique = true)
	private Long depId;

	@Column(name = "title")
	private String title;

	@OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
	private List<User> users;

	public Department() {
	}

	public Department(Long depId, String title) {
		this.depId = depId;
		this.title = title;
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return String.format("Department[id=%d, depId='%s', name='%s']", id, depId, title);
	}

}