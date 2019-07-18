package org.lxc.mall.model.request;

import java.io.Serializable;

public class SmsgRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String[] phoneNos;
	
	private int templateId;
	
	private String[] params;

	public String[] getPhoneNos() {
		return phoneNos;
	}

	public void setPhoneNos(String[] phoneNos) {
		this.phoneNos = phoneNos;
	}

	public int getTemplateId() {
		return templateId;
	}

	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}
	
	
}
