package edu.neumont.csc380.hello.service;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "video")
public class Video {
	
	private long id;
	
	private String fileName;
	private int fileSize;
	
	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public String getfileName()
	{
		return fileName;
	}
	
	public void setfileName(String fileName)
	{
		this.fileName = fileName;
	}

}
