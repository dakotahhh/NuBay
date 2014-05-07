package edu.neumont.csc380.Exceptions;

import java.io.Serializable;

public class InvalidPasswordOrUserNameException extends Exception implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public InvalidPasswordOrUserNameException() {
        super();
    }
    public InvalidPasswordOrUserNameException(String msg)   {
        super(msg);
    }
    public InvalidPasswordOrUserNameException(String msg, Exception e)  {
        super(msg, e);
    }
}
