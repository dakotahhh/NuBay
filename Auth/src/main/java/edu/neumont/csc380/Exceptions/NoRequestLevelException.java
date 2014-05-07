package edu.neumont.csc380.Exceptions;

import java.io.Serializable;

public class NoRequestLevelException extends Exception implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public NoRequestLevelException() {
        super();
    }
    public NoRequestLevelException(String msg)   {
        super(msg);
    }
    public NoRequestLevelException(String msg, Exception e)  {
        super(msg, e);
    }
}
