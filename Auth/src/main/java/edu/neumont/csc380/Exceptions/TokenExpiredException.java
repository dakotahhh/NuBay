package edu.neumont.csc380.Exceptions;

import java.io.Serializable;

public class TokenExpiredException extends Exception implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public TokenExpiredException() {
        super();
    }
    public TokenExpiredException(String msg)   {
        super(msg);
    }
    public TokenExpiredException(String msg, Exception e)  {
        super(msg, e);
    }
}
