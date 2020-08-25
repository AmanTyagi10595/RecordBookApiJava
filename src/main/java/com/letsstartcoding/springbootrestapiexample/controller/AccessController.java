package com.letsstartcoding.springbootrestapiexample.controller;

import java.io.IOException;
import java.io.FileOutputStream;
import java.io.File;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.validation.Valid;
import javax.xml.soap.SOAPPart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.letsstartcoding.springbootrestapiexample.dao.AceessDAO;
//import com.letsstartcoding.springbootrestapiexample.dao.File;
//import com.letsstartcoding.springbootrestapiexample.dao.FileOutputStream;
import com.letsstartcoding.springbootrestapiexample.model.Access;
import com.letsstartcoding.springbootrestapiexample.model.Customers;

import ch.qos.logback.core.net.SyslogOutputStream;

@RestController
@RequestMapping("/customer")
public class AccessController {
	@Autowired
	AceessDAO aceessDAO;
	Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	
	@PostMapping("/add")
	public Access createUser(@Valid @RequestBody Access data, @RequestParam("file") MultipartFile file) {
		return aceessDAO.save(data);	
	}
	
	@GetMapping("/check")
	public ResponseEntity<Access> checkAccess(@RequestBody Access data) {
		
		logger.info("data: ",data.getEmail());
		System.out.println("Starting Apiii"+data.getEmail() + data.getEmail().getClass().getName());
		Access a = aceessDAO.findParticular(data.getEmail(), "ajay");	
		
		if(a == null) {
			return ResponseEntity.notFound().build();
		}
		else {
			Boolean test = true;
			return ResponseEntity.ok().body(a);
		}
	}
//	@PostMapping("/updatePassword")
//	public String updatePassword(@Valid @RequestBody Access data) {
//		Access a = aceessDAO.findParticular(data.getEmail(), data.getPassword());
//		if(a == null) {
//			return "You are not register with us";
//		}
//		else {
//		Access b =	aceessDAO.save(data);
//		System.out.println(b);
//		return("Password Updated");
//		}
//				
//	}
	@GetMapping("/findAllUsers")
	public List<Access> findAllUsers() {
	List<Access> data = aceessDAO.findAll();
	return data;
	}
	//Delete the use
	@DeleteMapping("/deleteUser/{email}/")
	public String deleteUser(@PathVariable(value="email") String email){
		System.out.println("Starting Apiii "+email.getClass().getName());
		Access user=aceessDAO.findOne(email);
		System.out.println("get user from back end: "+user);
		if(user==null) {
//			return ResponseEntity.notFound().build();
			return "Data not found";
		}else {
		int data =	aceessDAO.deleteByEmail(email);
		if(data == 1) {
			return "Record Deleted";
		}else {
			return "Somthing Wrong";
		}
		}
	}
	@PostMapping("/sendEmail")
	public String sendEmail() throws AddressException, MessagingException, IOException{
		aceessDAO.sendMail();
		return "Email sent successfully";		
	}
	
	@PostMapping("/sendEmailAttachment")
	public String sendEmailAttachment() throws AddressException, MessagingException, IOException{
		aceessDAO.sendEmailWithAttachment();
		return "Email sent successfully";		
	}
	
	 @RequestMapping(value = "/upload", method = RequestMethod.POST,  
		      consumes = MediaType.MULTIPART_FORM_DATA_VALUE)	   
		   public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
		  System.out.println("Api started to upload file"+ file);
		  File convertFile = new File("C:/Users/Aman Tyagi/Desktop/files"+file.getOriginalFilename());
	      convertFile.createNewFile();
	      FileOutputStream fout = new FileOutputStream(convertFile);
	      fout.write(file.getBytes());	
	      fout.close();
	      System.out.println("upload success");
		      return "File is upload successfully";
		   }
}
	







