package com.letsstartcoding.springbootrestapiexample.dao;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

//import com.letsstartcoding.springbootrestapiexample.controller.File;
//import com.letsstartcoding.springbootrestapiexample.controller.FileOutputStream;
import com.letsstartcoding.springbootrestapiexample.model.Access;
//import com.letsstartcoding.springbootrestapiexample.model.Customers;
import com.letsstartcoding.springbootrestapiexample.repository.AccessReposetory;

@Service
public class AceessDAO {
	
	@Autowired
	AccessReposetory accessReposetory;
	@Autowired
	 private JavaMailSender javaMailSender;
	
	public Access save(Access data) {
		return accessReposetory.save(data);
	}
	
	public Access findParticular(String email, String password) {

		return accessReposetory.particularCustomer(email, password);
	}
	
	public List<Access> findAll(){
	   return accessReposetory.findAll();
	}
	 
	public List<Access> manualFindAll(){
	return accessReposetory.manualFindAll();
	}

	public Access findOne(String email) {
		// TODO Auto-generated method stub
		System.out.println("email received : "+email);
		return accessReposetory.findByEmail(email);
	}

	public int deleteByEmail(String email) {
		// TODO Auto-generated method stub
		System.out.println("here is data: "+email);
//		System.out.println(accessReposetory.deleteByEmail(email));
		 return accessReposetory.deleteByEmail(email);
		
	}
	
//	public void sendmail() throws AddressException, MessagingException, IOException {
//		   Properties props = new Properties();
//		   props.put("mail.smtp.auth", "true");
//		   props.put("mail.smtp.starttls.enable", "true");
//		   props.put("mail.smtp.host", "smtp.gmail.com");
//		   props.put("mail.smtp.port", "587");
//		   
//		   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//		      protected PasswordAuthentication getPasswordAuthentication() {
//		         return new PasswordAuthentication("amantyagi10595js@gmail.com", "9761320827@Aman");
//		      }
//		   });
//		   Message msg = new MimeMessage(session);
//		   msg.setFrom(new InternetAddress("amantyagi10595js@gmail.com", false));
//
//		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("amantyagi10595js@gmail.com"));
//		   msg.setSubject("Tutorials point email");
//		   msg.setContent("Tutorials point email", "text/html");
//		   msg.setSentDate(new Date());
//
//		   MimeBodyPart messageBodyPart = new MimeBodyPart();
//		   messageBodyPart.setContent("Tutorials point email", "text/html");
//
//		   Multipart multipart = new MimeMultipart();
//		   multipart.addBodyPart(messageBodyPart);
//		   MimeBodyPart attachPart = new MimeBodyPart();
//
//		   attachPart.attachFile("./../../bmw.jpg");
//		   multipart.addBodyPart(attachPart);
//		   msg.setContent(multipart);
//		   Transport.send(msg);   
//		}
	 public void sendMail(){
		 SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setTo("amantyagi10595@gmail.com");

	        msg.setSubject("Testing from Spring Boot");
	        msg.setText("Hello World \n Spring Boot Email");

	        javaMailSender.send(msg);
	}
	 
	 public void sendEmailWithAttachment() throws MessagingException, IOException {

	        MimeMessage msg = javaMailSender.createMimeMessage();

	        // true = multipart message
	        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

	        helper.setTo("amantyagi10595@gmail.com");

	        helper.setSubject("Testing from Spring Boot");

	        // default = text/plain
	        //helper.setText("Check attachment for image!");

	        // true = text/html
	        helper.setText("<h1>Check attachment for image!</h1>", true);

			// hard coded a file path
//	        FileSystemResource file = new FileSystemResource(new File("path/android.png"));

//	        helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));

	        javaMailSender.send(msg);

	    }
     public void uploadFile(MultipartFile file){
//    	 File convertFile = new File("/var/tmp/"+file.getOriginalFilename());
//	      convertFile.createNewFile();
//	      FileOutputStream fout = new FileOutputStream(convertFile);
//	      fout.write(file.getBytes());
//	      fout.close();
     }
}
