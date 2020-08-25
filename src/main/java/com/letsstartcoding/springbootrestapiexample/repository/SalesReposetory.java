package com.letsstartcoding.springbootrestapiexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.letsstartcoding.springbootrestapiexample.model.Customers;
import com.letsstartcoding.springbootrestapiexample.model.Sales;

@Repository
public interface SalesReposetory extends JpaRepository<Sales, Long> {

	@Query(value ="SELECT * FROM Sales WHERE email = :email", nativeQuery = true)
   public List<Sales> saleOfOneUser(@Param("email")  String email); 
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE world.customer SET balance= balance + :newBalance where email =:email", nativeQuery = true)
	public Integer updateBalance(@Param("email") String email, @Param("newBalance") Integer newBalance);
}
