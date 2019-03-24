package org.lxc.mall.model.request;

import java.io.Serializable;
import java.util.List;

/**
 * SupplierWriteCondition 作为供应商写操作的API请求对象
 * @author lxc
 *
 */
public class SupplierWriteConditon implements Serializable{
	
	private Long id;
	
	private String name;
	
    private String address;

    private String phoneNo;

    private String email;

    private String license;

    private String tel;

    private String contactName;

    private String remark;
	
	private String status;
	
	private List<WarehouseWriteCondition> warehouses;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<WarehouseWriteCondition> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<WarehouseWriteCondition> warehouses) {
		this.warehouses = warehouses;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
