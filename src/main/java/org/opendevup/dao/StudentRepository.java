package org.opendevup.dao;

import java.util.Date;
import java.util.List;

import org.opendevup.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface StudentRepository extends JpaRepository <Student,Long>{
	public List<Student> findByName(String n);
	public Page<Student> findByName(String n, Pageable pageable);
	
	@Query("select e from Student e where e.name like:x")
	public Page<Student> loadStudents(@Param("x")String mc, Pageable pageable);
	
	@Query("select e from Student e where e.dateOfBirth>:x and e.dateOfBirth<:y ")
	public List<Student> loadStudents(@Param("x")Date d1,@Param("y") Date d2);
	
 
}
