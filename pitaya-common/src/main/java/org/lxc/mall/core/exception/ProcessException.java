package org.lxc.mall.core.exception;

public class ProcessException extends RuntimeException {

	private static final long serialVersionUID = -1639555279143001230L;

	// 错误code
	public String errorCode;
	
	public String errorDesc;

	public ProcessException() {
	}

	public ProcessException(String message) {
		super(message);
	}
	
	public ProcessException(String errorCode, String errorDesc) {
		super(errorDesc);
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
	}
	
	public ProcessException(String errorCode, String errorDesc, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
	}

	public String getErrorCode() {
		return errorCode;
	}
	
	public String getErrorDesc(){
		return errorDesc;
	}
	
	public static ProcessException genException(String errorCode, String errorDesc){
		return new ProcessException(errorCode, errorDesc);
	}
	public static ProcessException genException(Throwable cause, String errorCode, String errorDesc){
		return new ProcessException(errorCode, errorDesc, cause);
	}
	
}
