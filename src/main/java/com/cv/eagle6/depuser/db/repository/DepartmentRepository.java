package com.cv.eagle6.depuser.db.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cv.eagle6.depuser.db.entity.Department;

@Repository
@Transactional
public interface DepartmentRepository extends CrudRepository<Department, Long> {

	@Query("from Department r where r.depId = :depId")
	public Department findByDepId(@Param("depId") Long e1Id);

}
