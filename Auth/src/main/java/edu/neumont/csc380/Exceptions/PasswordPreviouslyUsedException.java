package edu.neumont.csc380.Exceptions;

import java.io.Serializable;

public class PasswordPreviouslyUsedException extends Exception implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public PasswordPreviouslyUsedException() {
        super();
    }
    public PasswordPreviouslyUsedException(String msg)   {
        super(msg);
    }
    public PasswordPreviouslyUsedException(String msg, Exception e)  {
        super(msg, e);
    }
}
