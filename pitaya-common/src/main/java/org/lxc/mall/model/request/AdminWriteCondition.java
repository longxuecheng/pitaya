package org.lxc.mall.model.request;

import java.io.Serializable;

import org.lxc.mall.model.Admin;

public class AdminWriteCondition implements Serializable{
	
	private Long id;
	
	private String loginName;
	
	private String phoneNo;
	
	private String email;
	
	private String password;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Admin parseModel() {
		Admin a = new Admin();
		a.setPhoneNo(phoneNo);
		a.setEmail(email);
		a.setLoginName(loginName);
		a.setPassword(password);
		return a;
	}
}
