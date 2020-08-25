package com.letsstartcoding.springbootrestapiexample.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.letsstartcoding.springbootrestapiexample.dao.CustomerDAO;
import com.letsstartcoding.springbootrestapiexample.dao.SalesDAO;
import com.letsstartcoding.springbootrestapiexample.model.Customers;
import com.letsstartcoding.springbootrestapiexample.model.Response;
import com.letsstartcoding.springbootrestapiexample.model.Sales;

import ch.qos.logback.core.net.SyslogOutputStream;

@CrossOrigin(origins = "http://localhost:4200") 
@RestController
@RequestMapping("/sales")
public class SalesController {

	Logger logger = LoggerFactory.getLogger(SalesController.class);
	@Autowired
	SalesDAO salesDAO;
//	CustomerDAO customerDAO;
	
	/* to save an employee*/
	@PostMapping("/add")
	public ResponseEntity<?> createSale(@RequestBody Sales sales  ) {
		System.out.println("Checkinh Api"+sales.toString());
		Integer balance = sales.getAmount() - sales.getPayedAmout();
		sales.setBalance(balance);
		logger.info("THis is for testing"+sales.toString());
		Sales data = salesDAO.save(sales);
		if(data != null) {
			Integer result = salesDAO.updateBalance(sales.getEmail(), balance);
			System.out.println("Otput after update: "+result);
			return new ResponseEntity(data,HttpStatus.OK);
		}
		return ResponseEntity.notFound().build();
	}
	@GetMapping("/getAllSale")
	public List<Sales> getAllEmployees(){
		List<Sales> a= salesDAO.findAll();
//		logger.info("THis is for testing"+a);
		return a;
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Response> deleteEmployee(@PathVariable(value="id") Long empid){
		System.out.println("HIiiii  "+empid);
		Sales sale=salesDAO.findOne(empid);
		if(sale==null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		salesDAO.delete(sale);
		Response res = new Response();
		res.setStatus("success");
		return ResponseEntity.ok().body(res);		
	}
	@GetMapping("/saleOfOneUser/{email}/")
	public ResponseEntity<?> getEmployeeById(@PathVariable(value="email") String email){
		
		List<Sales> sale = salesDAO.saleOfOneUser(email);
			
		logger.info("emp: {}", sale);
		if(sale==null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity(sale,HttpStatus.OK);
		
	}
}
