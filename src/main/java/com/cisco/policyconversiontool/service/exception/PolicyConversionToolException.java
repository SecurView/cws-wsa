package com.cisco.policyconversiontool.service.exception;

public class PolicyConversionToolException extends Exception {
	
	private String message;
	private Throwable cause;

	public PolicyConversionToolException(){
		
	}
	
	public PolicyConversionToolException(String message){
		this.message = message;
	}
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Throwable getCause() {
		return cause;
	}

	public void setCause(Throwable cause) {
		this.cause = cause;
	}

}