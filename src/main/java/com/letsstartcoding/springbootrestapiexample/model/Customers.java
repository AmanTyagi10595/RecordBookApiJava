package com.letsstartcoding.springbootrestapiexample.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="Customer") 
@EntityListeners(AuditingEntityListener.class)
public class Customers {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column
	private Long mo_num;
	
	@Column
	private String address;
	
	@Column
	private String email;
	
	@Column
	private String name;
	
	@Column
	private String img_url;
	
	@Column
	private Integer balance;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMo_num() {
		return mo_num;
	}

	public void setMo_num(Long mo_num) {
		this.mo_num = mo_num;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Customers [id=" + id + ", mo_num=" + mo_num + ", address=" + address + ", email=" + email + ", name="
				+ name + ", img_url=" + img_url + ", balance=" + balance + "]";
	}


}
