package com.example.demo.bean;

import org.springframework.stereotype.Component;

@Component
public class CustumResponse {
private int status;
private String message;
private Object data;

public Object getData() {
	return data;
}
public void setData(Object data) {
	this.data = data;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
}

