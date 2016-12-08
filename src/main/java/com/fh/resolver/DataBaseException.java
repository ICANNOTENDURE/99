package com.fh.resolver;

import org.springframework.dao.DataAccessException;

public class DataBaseException extends DataAccessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Exception linkedException;
	
	public DataBaseException(String msg,Exception ex){
		
		super(msg);
		linkedException = ex;
	}
	
	public DataBaseException(String msg){
		
		super(msg);
	}
	

	public Exception getLinkedException() {
		return linkedException;
	}

	public void setLinkedException(Exception linkedException) {
		this.linkedException = linkedException;
	}
}
