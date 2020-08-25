package com.letsstartcoding.springbootrestapiexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.letsstartcoding.springbootrestapiexample.model.Access;
//import com.letsstartcoding.springbootrestapiexample.model.Customers;

@Repository
public interface AccessReposetory extends JpaRepository<Access, Long>{


	@Query(value ="SELECT * FROM Access  WHERE email = :email and password = :password", nativeQuery = true) 
	   public Access particularCustomer(@Param("email")  String email, @Param("password")  String password); 
	
	@Query(value ="SELECT * FROM Access ", nativeQuery = true)
	public List<Access> manualFindAll();
		
	@Query(value ="SELECT * FROM Access WHERE email = :email", nativeQuery = true)
	public Access findByEmail(@Param("email")  String email);
	
	@Modifying
	@Transactional
	@Query(value ="DELETE FROM Access WHERE email=:email", nativeQuery = true)
	public int deleteByEmail(@Param("email")  String email);
}


