package org.lxc.mall.model.request;

import java.io.Serializable;

import org.lxc.mall.model.UserAddress;

public class UserAddressWriteCondition implements Serializable{
	
	private Integer id;

    private Integer userId;

    private String address;

    private String name;

    private String phoneNo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	public UserAddress parseModel() {
		UserAddress ua = new UserAddress();
		ua.setId(id);
		ua.setUserId(userId);
		ua.setAddress(address);
		ua.setName(name);
		ua.setPhoneNo(phoneNo);
		return ua;
	}
}
