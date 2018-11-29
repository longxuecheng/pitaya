package org.lxc.mall.model.request;

import org.lxc.mall.model.common.PageSerialization;

public class SaleQueryCondition extends PageSerialization{
	
	public String orderNo;
	
	public String phoneNo;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	
}
