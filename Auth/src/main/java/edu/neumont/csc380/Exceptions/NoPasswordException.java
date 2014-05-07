package edu.neumont.csc380.Exceptions;

import java.io.Serializable;

public class NoPasswordException extends Exception implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public NoPasswordException() {
        super();
    }
    public NoPasswordException(String msg)   {
        super(msg);
    }
    public NoPasswordException(String msg, Exception e)  {
        super(msg, e);
    }
}
