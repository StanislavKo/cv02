package com.cv.eagle6.depuser.db.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cv.eagle6.depuser.db.entity.User;

@Repository
@Transactional
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	@Query("from User u where u.department.id = :depId AND (u.firstName LIKE :likeName OR u.lastName LIKE :likeName)")
	public Iterable<User> findFilter(@Param("depId") Long depId, @Param("likeName") String likeName);
	
}
