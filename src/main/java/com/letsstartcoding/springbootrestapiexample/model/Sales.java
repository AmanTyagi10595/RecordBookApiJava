package com.letsstartcoding.springbootrestapiexample.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="Sales")
@EntityListeners(AuditingEntityListener.class)
public class Sales {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column
	private Integer amount;
	
	@Column
	private Integer payedAmout; 
	
	@Column
	private Date sale_date;
	
	@Column
	private Date promis_date;
	
	@Column
	private String email;
	
	@Column
	private Integer balance;
	
	@Column
	private String image_url;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getPayedAmout() {
		return payedAmout;
	}

	public void setPayedAmout(Integer payedAmout) {
		this.payedAmout = payedAmout;
	}

	public Date getSale_date() {
		return sale_date;
	}

	public void setSale_date(Date sale_date) {
		this.sale_date = sale_date;
	}

	public Date getPromis_date() {
		return promis_date;
	}

	public void setPromis_date(Date promis_date) {
		this.promis_date = promis_date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	@Override
	public String toString() {
		return "Sales [id=" + id + ", amount=" + amount + ", payedAmout=" + payedAmout + ", sale_date=" + sale_date
				+ ", promis_date=" + promis_date + ", email=" + email + ", balance=" + balance + ", image_url="
				+ image_url + "]";
	}
	
}
