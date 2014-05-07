package edu.neumont.csc380.Exceptions;

import java.io.Serializable;

public class TokenMissingException extends Exception implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public TokenMissingException() {
        super();
    }
    public TokenMissingException(String msg)   {
        super(msg);
    }
    public TokenMissingException(String msg, Exception e)  {
        super(msg, e);
    }
}
