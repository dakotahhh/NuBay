package edu.neumont.csc380.hello.service;

import java.util.Arrays;

import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.provider.json.JSONProvider;

public class HelloWorldClient {
	private HelloWorldService service = JAXRSClientFactory.create("http://localhost:8080/hellorest-server/rest", HelloWorldService.class, Arrays.asList(new JSONProvider<Object>()));
	
//	public Greeting getGreeting(Long id) {
//		return service.getGreeting(id).readEntity(Greeting.class);
//	}
//
//	public Greeting updateGreeting(Long id, Greeting greeting) {
//		return service.updateGreeting(id, greeting).readEntity(Greeting.class);
//	}
//
//	public Greeting createGreeting(Greeting greeting) {
//		return service.createGreeting(greeting).readEntity(Greeting.class);
//	}
//
//	public void deleteGreeting(Long id) {
//		service.deleteGreeting(id);
//	}
//
//	public static void main(String[] args) {
//		HelloWorldClient c = new HelloWorldClient();
//		Greeting g = new Greeting();
//		g.setMessage("dave");
//		Greeting created = c.createGreeting(g);
//		System.out.println(c.getGreeting(created.getId()));
//	}
	
	public Image getImage(Long id)
	{
		return service.getImage(id).readEntity(Image.class);
	}
	
	public Video getVideo(Long id)
	{
		return service.getVideo(id).readEntity(Video.class);
	}
	
	public Image createImage(Image image)
	{
		return service.createImage(image).readEntity(Image.class);
	}
	
	public Video createVideo(Video video)
	{
		return service.createVideo(video).readEntity(Video.class);
	}
	
	public void deleteImage(Long id)
	{
		service.deleteImage(id);
	}
	
	public void deleteVideo(Long id)
	{
		service.deleteVideo(id);
	}
	
	public static void main(String[] args)
	{
		HelloWorldClient c = new HelloWorldClient();
		Image i = new Image();
		Image created = i.createImage(i);
		System.out.println(i.getImage(created.getId()));
	}
}
