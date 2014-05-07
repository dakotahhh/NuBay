package edu.neumont.csc380.Exceptions;

import java.io.Serializable;

public class TokenRemovedException extends Exception implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public TokenRemovedException() {
        super();
    }
    public TokenRemovedException(String msg)   {
        super(msg);
    }
    public TokenRemovedException(String msg, Exception e)  {
        super(msg, e);
    }
}
