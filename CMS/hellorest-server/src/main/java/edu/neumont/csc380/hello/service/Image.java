package edu.neumont.csc380.hello.service;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "image")
public class Image {
	
	private long id;
	
	private String fileName;
	private int width;
	private int height;
	private int fileSize;
	
	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public String getFileName()
	{
		return fileName;
	}
	
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public void setWidth(int width)
	{
		this.width = width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public void setHeight(int height)
	{
		this.height = height;
	}

}
