package edu.neumont.csc380.hello.service;

import java.util.Arrays;

import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.provider.json.JSONProvider;

public class HelloWorldClient {
	private ImageService image = JAXRSClientFactory.create("http://localhost:8080/hellorest-server/rest", ImageService.class, Arrays.asList(new JSONProvider<Object>()));
	private VideoService video = JAXRSClientFactory.create("http://localhost:8080/hellorest-server/rest", VideoService.class, Arrays.asList(new JSONProvider<Object>()));
	
	
	public Image getImage(Long id)
	{
		return image.getImage(id).readEntity(Image.class);
	}
	
	public Video getVideo(Long id)
	{
		return video.getVideo(id).readEntity(Video.class);
	}
	
	public Image createImage(Image image)
	{
		return image.createImage(image).readEntity(Image.class);
	}
	
	public Video createVideo(Video video)
	{
		return video.createVideo(video).readEntity(Video.class);
	}
	
	public void deleteImage(Long id)
	{
		image.deleteImage(id);
	}
	
	public void deleteVideo(Long id)
	{
		video.deleteVideo(id);
	}
	
	public static void main(String[] args)
	{
		HelloWorldClient c = new HelloWorldClient();
		Image i = new Image();
		Image created = i.createImage(i);
		System.out.println(i.getImage(created.getId()));
	}
}
