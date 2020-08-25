package com.letsstartcoding.springbootrestapiexample.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.letsstartcoding.springbootrestapiexample.model.Customers;
import com.letsstartcoding.springbootrestapiexample.model.Sales;
import com.letsstartcoding.springbootrestapiexample.repository.CustomerRepository;;;

@Service
public class CustomerDAO {

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	 private JavaMailSender javaMailSender;
	
	/*to save an employee*/
	
	public Customers save(Customers cus) {
		return customerRepository.save(cus);
	}
	
	/* search all employees*/
	
	public List<Customers> findAll(){
		return customerRepository.findAll();
	}
	
	/*get an employee by id*/
	public Customers findOne(Long id) {
		return customerRepository.findOne(id);
	}
	
	
	/*delete an employee*/
	
	public void delete(Customers cus) {
		customerRepository.delete(cus);
	}
	
   public Customers particularCustomer(Long id) {
	   System.out.println(id.toString());
	   return customerRepository.particularCustomer(id);
   }

   public Integer findBalanceByEmail(String email) {
	     return customerRepository.findBalanceByEmail(email);
   }
   
   public List<Customers> findCustomerByBalanceRange(Number minValue, Number maxValue){
	   return customerRepository.findCustomerByBalanceRange(minValue, maxValue);
   }

   public List<Sales> findCustomerByDateRange(Date minDate, Date maxDate) {
	// TODO Auto-generated method stub
	    System.out.println("HI here: "+ minDate +" "+ maxDate.getClass().getName());
	    return customerRepository.findCustomerByDateRange(minDate, maxDate);
}

   public String sendMail(String email, Integer balance){
		 SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setTo(email);

	        msg.setSubject("Your report related mail");
	        msg.setText("Hi "+email+" you need to pay "+balance +" rupees.");

	        javaMailSender.send(msg);
	        return "mailSent";
	}

public String notifieAllAtOnce() {
	// TODO Auto-generated method stub
	List<?> customers = customerRepository.getListDefaulterCustomers();
	Iterator it = customers.iterator();
	ArrayList customerToNotifie =  new ArrayList();
	while(it.hasNext()) {
		 Object[] obj = (Object[]) it.next();   
//			System.out.println(obj[0] instanceof java.lang.String);
		   if(obj[0] instanceof java.lang.String) {
			customerToNotifie.add(obj[0]);
		}
	}
	HashMap completeData = new HashMap();
	Iterator its = customerToNotifie.iterator();
	while(its.hasNext()) {
		String email = (String) its.next();
		int balance = customerRepository.findBalanceByEmail(email);
		completeData.put(email, balance);
	} 
	final Set st = completeData.entrySet();
//	Thread t = new Thread() {
//	    public void run() {
//	        System.out.println("blah");
//	        Iterator it = st.iterator();
//	        while(it.hasNext()) {
//	        	Map.Entry m1 = (Map.Entry)it.next();
//	        	System.out.println((String)m1.getKey()+ "--"+m1.getValue().getClass().getName());
//	        	sendMail((String)m1.getKey(), (Integer) m1.getValue());
//	        };
//	    }
//	};
//	t.start();
	   Iterator itr = st.iterator();
       while(itr.hasNext()) {
       	Map.Entry m1 = (Map.Entry)itr.next();
       	System.out.println((String)m1.getKey()+ "--"+m1.getValue().getClass().getName());
       	sendMail((String)m1.getKey(), (Integer) m1.getValue());
       };
	return "Mail sent Successfully";
} 
   
}





