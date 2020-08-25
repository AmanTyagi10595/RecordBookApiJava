package com.letsstartcoding.springbootrestapiexample.controller;

import java.io.IOException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.letsstartcoding.springbootrestapiexample.dao.CustomerDAO;
import com.letsstartcoding.springbootrestapiexample.dao.EmployeeDAO;
import com.letsstartcoding.springbootrestapiexample.dao.Order;
//import com.letsstartcoding.springbootrestapiexample.dao.PayPalClient;
import com.letsstartcoding.springbootrestapiexample.dao.PaypalService;
import com.letsstartcoding.springbootrestapiexample.model.Customers;
import com.letsstartcoding.springbootrestapiexample.model.Employee;
import com.letsstartcoding.springbootrestapiexample.model.Response;
import com.letsstartcoding.springbootrestapiexample.model.Sales;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

//import json.parser.JSONParser;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	 Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	CustomerDAO customerDAO;
	PaypalService service;
	public static final String SUCCESS_URL = "pay/success";
	public static final String CANCEL_URL = "pay/cancel";
	
	
	/* to save an employee*/
	@PostMapping("/custom")
	public ResponseEntity<?> createEmployee(@RequestBody Customers cum  ) {
		System.out.println("Checkinh Api"+cum);
		logger.info("THis is for testing"+cum.toString());
		Customers data = customerDAO.save(cum);
		if(data != null) {
			return ResponseEntity.ok().body("uploadSave");
		}
		return ResponseEntity.notFound().build();
	}
	
	/*get all employees*/
	@GetMapping("/custom")
	public List<Customers> getAllEmployees(){
		List<Customers> a= customerDAO.findAll();
//		logger.info("THis is for testing"+a);
		return a;
	}
	
	/*get employee by empid*/
	@GetMapping("/custom/{id}")
	public ResponseEntity<Customers> getEmployeeById(@PathVariable(value="id") Long id){
		
		Customers cum=customerDAO.findOne(id);
		
		logger.info("emp: {}", cum);
		if(cum==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(cum);
		
	}
	
	
	/*update an employee by empid*/
	@PutMapping("/custom/{id}")
	public ResponseEntity<Customers> updateEmployee(@PathVariable(value="id") Long empid,@Valid @RequestBody Customers empDetails){
		
		Customers emp=customerDAO.findOne(empid); 
		
		if(emp==null) {
			return ResponseEntity.notFound().build();
		}
		System.out.println("Byy"+emp.toString());
		emp.setMo_num(empDetails.getMo_num());
		emp.setAddress(empDetails.getAddress());
		emp.setBalance(empDetails.getBalance());
		emp.setEmail(empDetails.getEmail()); 
		//emp.setImg_url(empDetails.getImg_url());
		emp.setName(empDetails.getName());
		emp.setId(empDetails.getId());
		System.out.println("HIiiii"+emp.toString());
		Customers updateEmployee=customerDAO.save(emp);
		return ResponseEntity.ok().body(empDetails);
		
		
		
	}
	
	/*Delete an employee*/
	@DeleteMapping("/custom/{id}")
	public ResponseEntity<Response> deleteEmployee(@PathVariable(value="id") Long empid){
		System.out.println("HIiiii  "+empid);
		Customers emp=customerDAO.findOne(empid);
		if(emp==null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		customerDAO.delete(emp);
		Response res = new Response();
		res.setStatus("success");
		return ResponseEntity.ok().body(res);		
	}
	
	@PostMapping("/particular")
	public Customers particularCustomer(@Valid @RequestBody Customers cum) {
		System.out.println("Checkinh Api");
	     Customers c =  customerDAO.particularCustomer(cum.getId());
	     System.out.println(c.toString());
//		return customerDAO.save(cum);
	     return c;
	}
	 @PostMapping("/uploads")
     public String upload(@RequestParam("file") MultipartFile file, 
                          @RequestParam("customer") Customers customer) {
         return customer + "\n" + file.getOriginalFilename() + "\n" + file.getSize();
     }
	 
	@GetMapping("/findBalanceByEmail/{email}/")
	public Integer findBalanceByEmail(@PathVariable(value="email") String email) {
		return customerDAO.findBalanceByEmail(email);
	}
	
	 @PostMapping("/findCustomerByBalanceRange")
	 public List<Customers> findCustomerByBalanceRange( @RequestParam String minValue, @RequestParam String maxValue) {
		 Integer min = Integer.parseInt(minValue);
		 Integer max = Integer.parseInt(maxValue);
			System.out.println("Checkinh Api " +"minValue: "+min+" "+"maxValue: "+ max);
		     List <Customers> cum =  customerDAO.findCustomerByBalanceRange(min, max);
		     System.out.println("here is the result"+cum);
		     return cum;
}
	 @PostMapping("/findCustomerByDateRange/{minDate}/{maxDate}")
	 public String findCustomerByDateRange( @PathVariable(value="minDate") Date minDate, @PathVariable(value = "maxDate") Date maxDate){
		 System.out.println("These dates are going in SQL "+minDate+ " "+ maxDate);
		 try {
			  List <Sales> cum =  customerDAO.findCustomerByDateRange(minDate, maxDate);
			     System.out.println("here is the result"+ cum);
			  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		   return "a";	   
}
	
	@PostMapping("/email")
	public ResponseEntity<?>  sendEmail(@RequestParam String email, @RequestParam Integer balance) {
		System.out.println("Api to send notification mail " );
	   String data= customerDAO.sendMail(email, balance);
		return new ResponseEntity(HttpStatus.OK);		
	}
	
	//Send mail to all users at-once with their respective sales (under promise date).
	@GetMapping("/notifieAllAtOnce")
	public ResponseEntity<?> notifieAllAtOnce(){
		String a= customerDAO.notifieAllAtOnce();
		return ResponseEntity.ok().body(a);
	}
	
	@PostMapping("/notifiedRanged")
	public ResponseEntity<Response>  notifiedRanged(@RequestBody String str) {
//		System.out.println("Api to send notification mail "+ str +":"+str.getClass().getName() );
		JSONArray jsonArray = new JSONArray(str);
		System.out.println("Api to send notification mail "+ jsonArray +":"+jsonArray.getClass().getName() );
		Iterator it = jsonArray.iterator();
		while(it.hasNext()) {
//			System.out.println("One by One: "+it.next());
			JSONObject jObj = (JSONObject) it.next();
			System.out.println("One by One: "+jObj.getString("email") + " "+jObj.getInt("balance") );
			String data= customerDAO.sendMail(jObj.getString("email"), jObj.getInt("balance"));
		}	   
		Response res = new Response();
		res.setStatus("success");
//		res.status= "success";
		return ResponseEntity.ok().body(res);
	}
	
	@PostMapping("/testing")
		public String testing(@RequestBody Map str) {
		System.out.println("Here it is: " + str+" "+str.getClass().getName());
		     HashMap map = new HashMap(str);
		     Set st = map.entrySet();
		     Iterator it = st.iterator();
		     while(it.hasNext()){
		    	 Map.Entry m1 = (Map.Entry)it.next();
		    	 System.out.println("Data here: "+ m1.getKey()+" : "+m1.getValue());
		     }
		     // If we receive data as String
//		     JSONObject jsonArray = new JSONObject(str);
//		     System.out.println("Here it is: " + jsonArray.getString("name")+" "+jsonArray.getInt("age")+" "+jsonArray.getClass().getName());
			return "testing";
		}
	@GetMapping("/")
	public String home() {
		return "home";
	}
	@PostMapping("/pay")
	public String payment(@ModelAttribute("order") Order order) {
		try {
			Payment payment = service.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(),
					order.getIntent(), order.getDescription(), "http://localhost:8080/" + CANCEL_URL,
					"http://localhost:9090/" + SUCCESS_URL);
			for(Links link:payment.getLinks()) {
				if(link.getRel().equals("approval_url")) {
					return "redirect:"+link.getHref();
				}
			}
			
		} catch (PayPalRESTException e) {
		
			e.printStackTrace();
		}
		return "redirect:/";
	}
	
	 @GetMapping(value = CANCEL_URL)
	    public String cancelPay() {
	        return "calcel";
	    }

	    @GetMapping(value = SUCCESS_URL)
	    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
	        try {
	            Payment payment = service.executePayment(paymentId, payerId);
	            System.out.println(payment.toJSON());
	            if (payment.getState().equals("approved")) {
	                return "success";
	            }
	        } catch (PayPalRESTException e) {
	         System.out.println(e.getMessage());
	        }
	        return "redirect:/";
	    }
}














