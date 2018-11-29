package org.lxc.mall.model.common;

import java.io.Serializable;


public class RespDTO<T> implements Serializable{
	public static final String SUCCESS = "success";
	public static final String FAIL = "fail";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String status = SUCCESS;
	private String errDesc;
	private T data;
	
	public void errorResult() {
		this.status = FAIL;
	}
	public void successResult() {
		this.status = SUCCESS;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrDesc() {
		return errDesc;
	}
	public void setErrDesc(String errDesc) {
		this.errDesc = errDesc;
	}
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	/**
	 * Check if the service is ok
	 * @return
	 */
	public boolean isSuccess() {
		return SUCCESS.equals(this.status);
	}
}
