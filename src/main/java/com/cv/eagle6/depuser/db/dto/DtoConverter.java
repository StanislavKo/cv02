package com.cv.eagle6.depuser.db.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.cv.eagle6.depuser.db.entity.Department;
import com.cv.eagle6.depuser.db.entity.User;

public class DtoConverter {

	public static List<DepartmentDto> getDepartmentDtos(List<Department> deps, boolean withUsers) {
		return deps.stream().map(d -> new DepartmentDto(d.getId(), d.getDepId(), d.getTitle(), withUsers ? getUserDtos(d.getUsers(), false) : null))
				.collect(Collectors.toList());
	}

	public static DepartmentDto getDepartmentDto(Department dep, boolean withUsers) {
		return Optional.ofNullable(dep).map(d -> new DepartmentDto(d.getId(), d.getDepId(), d.getTitle(), withUsers ? getUserDtos(d.getUsers(), false) : null))
				.orElse(null);
	}

	public static Department getDepartment(DepartmentDto depDto) {
		return Optional.ofNullable(depDto).map(d -> new Department(d.getDepId(), d.getTitle())).orElse(null);
	}

	public static List<UserDto> getUserDtos(List<User> users, boolean withDep) {
		if (users == null) {
			return null;
		}
		return users.stream().map(u -> new UserDto(u.getId(), u.getFirstName(), u.getLastName(), withDep ? getDepartmentDto(u.getDepartment(), false) : null))
				.collect(Collectors.toList());
	}

	public static UserDto getUserDto(User user, boolean withDep) {
		return Optional.ofNullable(user)
				.map(u -> new UserDto(u.getId(), u.getFirstName(), u.getLastName(), withDep ? getDepartmentDto(u.getDepartment(), false) : null)).orElse(null);
	}

}
