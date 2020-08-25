package com.letsstartcoding.springbootrestapiexample.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letsstartcoding.springbootrestapiexample.model.Customers;
import com.letsstartcoding.springbootrestapiexample.model.Sales;
import com.letsstartcoding.springbootrestapiexample.repository.SalesReposetory;

@Service
public class SalesDAO {

	@Autowired
	SalesReposetory salesReposetory;
	
	/*to save an sale*/
	
	public Sales save(Sales sale) {
		return salesReposetory.save(sale);
	}
	
	/* search all sales*/
	
	public List<Sales> findAll(){
		return salesReposetory.findAll();
	}
	
	/*get an sale by id*/
	public Sales findOne(Long id) {
		return salesReposetory.findOne(id);
	}
	
	
	/*delete an sale*/
	
	public void delete(Sales sale) {
		salesReposetory.delete(sale);
	}
	
	/*sale of one user*/
	 public List<Sales> saleOfOneUser(String email) {
		   System.out.println(email);
		   return salesReposetory.saleOfOneUser(email);
	   }
	 
	 public Integer updateBalance(String email, Integer newBalance) {
		    System.out.println(email);
	    	return salesReposetory.updateBalance(email, newBalance);
	    }
}
