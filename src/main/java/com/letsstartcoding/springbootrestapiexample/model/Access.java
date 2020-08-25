package com.letsstartcoding.springbootrestapiexample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "access")
@EntityListeners(AuditingEntityListener.class)
public class Access {	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Number id;
	
	@Column
	private String name ;

	@Column
	private String address ;
	
	@Column
	private Number mo_num ;
	
	@Column
	private String email ;
	
	@Column
	private Number balance ;
	
	@Column
	private String img_url ;
	
	public Number getId() {
		return id;
	}

	public void setId(Number id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Number getMo_num() {
		return mo_num;
	}

	public void setMo_num(Number mo_num) {
		this.mo_num = mo_num;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Number getBalance() {
		return balance;
	}

	public void setBalance(Number balance) {
		this.balance = balance;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	@Override
	public String toString() {
		return "Access [id=" + id + ", name=" + name + ", address=" + address + ", mo_num=" + mo_num + ", email="
				+ email + ", balance=" + balance + ", img_url=" + img_url + "]";
	}

}
