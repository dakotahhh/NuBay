package edu.neumont.csc380.Exceptions;

import java.io.Serializable;

public class NoUserFoundException extends Exception implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public NoUserFoundException() {
        super();
    }
    public NoUserFoundException(String msg)   {
        super(msg);
    }
    public NoUserFoundException(String msg, Exception e)  {
        super(msg, e);
    }
}
