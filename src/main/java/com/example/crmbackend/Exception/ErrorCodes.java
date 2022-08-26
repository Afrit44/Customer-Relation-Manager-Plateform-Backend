package com.example.crmbackend.Exception;

public enum ErrorCodes {
	
	//TODO Fix the error codes once you're done !!
	  REQUEST_NOT_FOUND(1000),
	  REQUEST_NOT_VALID(1001),
	  REQUEST_ALREADY_IN_USE(1002),
	
	  USER_NOT_FOUND(2000),
	  USER_NOT_VALID(2001),
	  USER_ALREADY_IN_USE(2002),
	
	  FEEDBACK_NOT_FOUND(3000),
	  FEEDBACK_NOT_VALID(3001),
	  FEEDBACK_ALREADY_IN_USE(3002),
	
	  BAD_CREDENTIALS(12003),
	
	  //List of technical exceptions
	  UPDATE_PHOTO_EXCEPTION(14000),
	  UNKNOWN_CONTEXT(14001)
	  ;
	
	  private int code;
	
	  ErrorCodes(int code) {
	    this.code = code;
	  }
	
	  public int getCode() {
	    return code;
	  }
}
