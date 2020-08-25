package com.letsstartcoding.springbootrestapiexample.model;

public class Response {
 
	public String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Response [status=" + status + "]";
	}
}
