package edu.neumont.csc380.hello.service;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "image")
public class Image {

	@XmlAttribute(name="id")
	private long id;
	@XmlAttribute(name="fileName")
	private String fileName;
	@XmlAttribute(name="width")
	private int width;
	@XmlAttribute(name="height")
	private int height;
	@XmlAttribute(name="fileSize")
	private int fileSize;

	@XmlTransient
	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	@XmlTransient
	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	@XmlTransient
	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	@XmlTransient
	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	@XmlTransient
	public int getFileSize()
	{
		return fileSize;
	}

	public void setFileSize(int fileSize)
	{
		this.fileSize = fileSize;
	}

	public void print()
	{
		String printed = "Image_ID=" + id + "_FileName=" + fileName + "_FileSize=" + fileSize + "_Width=" + width + "_Height=" + height;
		System.out.println(printed);
	}

	public String toMessageString() {
		return ("id:" + id + ";fileName:" + fileName + ";fileSize:" + fileSize + ";width:" + width + ";height:" + height);
	}

}