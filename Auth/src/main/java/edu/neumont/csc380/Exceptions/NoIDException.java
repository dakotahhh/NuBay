package edu.neumont.csc380.Exceptions;

import java.io.Serializable;

public class NoIDException extends Exception implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public NoIDException() {
        super();
    }
    public NoIDException(String msg)   {
        super(msg);
    }
    public NoIDException(String msg, Exception e)  {
        super(msg, e);
    }
}
