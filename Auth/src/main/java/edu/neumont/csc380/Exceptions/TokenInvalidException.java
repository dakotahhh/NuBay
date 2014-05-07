package edu.neumont.csc380.Exceptions;

import java.io.Serializable;

public class TokenInvalidException extends Exception implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public TokenInvalidException() {
        super();
    }
    public TokenInvalidException(String msg)   {
        super(msg);
    }
    public TokenInvalidException(String msg, Exception e)  {
        super(msg, e);
    }
}
