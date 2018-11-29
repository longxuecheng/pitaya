package org.lxc.mall.model.request;

import java.io.Serializable;

import org.lxc.mall.model.Admin;

public class LoginRequest implements Serializable{
	
	private String user;
	
	private String password;
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Admin parseModel() {
		Admin q = new Admin();
		q.setLoginName(user);
		q.setEmail(user);
		q.setPhoneNo(user);
		q.setPassword(password);
		return q;
	}
	
}
