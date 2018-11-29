package org.lxc.mall.core.exception;

import java.io.Serializable;

public class SimpleException implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SimpleException(String errorCode, String errorDesc) {
		super();
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
	}

	public String errorCode;
	
	public String errorDesc;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	
}
