package com.letsstartcoding.springbootrestapiexample.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.letsstartcoding.springbootrestapiexample.model.Access;
import com.letsstartcoding.springbootrestapiexample.model.Customers;
import com.letsstartcoding.springbootrestapiexample.model.Sales;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Long> {

		@Query("SELECT u FROM Customers u WHERE u.id = :id") 
	   public Customers particularCustomer(@Param("id")  Long id); 
		
		@Query(value ="SELECT balance FROM world.customer where email = :email", nativeQuery = true)
		public Integer findBalanceByEmail(@Param("email")  String email);
		
		@Query(value ="SELECT * FROM world.customer WHERE balance <= :maxValue AND balance >= :minValue", nativeQuery = true)
		public List<Customers> findCustomerByBalanceRange(@Param("minValue")  Number minValue, @Param("maxValue") Number maxValue);
		
//		@Query(value ="SELECT * FROM world.sales WHERE promis_date >= :minDate AND promis_date <= :maxDate", nativeQuery = true)
//		public List<Sales> findCustomerByDateRange(@Param("minDate")  Date minDate, @Param("maxDate") Date maxDate);
		
		@Query("SELECT s FROM Sales as s WHERE s.promis_date >= :minDate AND s.promis_date <= :maxDate")
		public List<Sales> findCustomerByDateRange(@Param("minDate")  Date minDate, @Param("maxDate") Date maxDate);
		
	
//		@Query("select distinct s.email from Sales as s, Customers as c where s.email=c.email") 
//		public List<Customers> getListDefaulterCustomers();

		@Query(value ="SELECT distinct email, total from(SELECT count(*) as Total , email from (SELECT c.email, s.amount,s.balance, s.promis_date FROM world.customer as c LEFT join world.sales as s ON c.email = s.email)AS T  group by email)as Y",  nativeQuery = true)
		public List<?> getListDefaulterCustomers();
		
//		@Query(value = "UPDATE world.customer SET balance= 5 where email =:email", nativeQuery = true)
//		public Customers updateBalance(@Param("email") String email);
		
		
}