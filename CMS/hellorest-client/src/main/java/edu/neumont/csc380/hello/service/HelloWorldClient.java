package edu.neumont.csc380.hello.service;

import java.util.Arrays;

import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.provider.json.JSONProvider;

public class HelloWorldClient {
	private ImageService imageService = JAXRSClientFactory.create("http://localhost:8080/CMS-server/rest", ImageService.class, Arrays.asList(new JSONProvider<Object>()));
	private VideoService videoService = JAXRSClientFactory.create("http://localhost:8080/CMS-server/rest", VideoService.class, Arrays.asList(new JSONProvider<Object>()));

	public Image getImage(Long id, Image image)
	{
		Image i = imageService.getImage(id, image).readEntity(Image.class);
		return i;
	}

	public Video getVideo(Long id, Video video)
	{
		return videoService.getVideo(id, video).readEntity(Video.class);
	}

	public Image createImage(Image image)
	{
		return imageService.createImage(image).readEntity(Image.class);
	}

	public Video createVideo(Video video)
	{
		return videoService.createVideo(video).readEntity(Video.class);
	}

	public Image updateImage(Long id, Image image)
	{
		return imageService.updateImage(id, image).readEntity(Image.class);
	}

	public Video updateVideo(Long id, Video video)
	{
		return videoService.updateVideo(id, video).readEntity(Video.class);
	}

	public void deleteImage(Long id)
	{
		imageService.deleteImage(id);
	}

	public void deleteVideo(Long id)
	{
		videoService.deleteVideo(id);
	}

	public static void main(String[] args)
	{
		HelloWorldClient c = new HelloWorldClient();

		Image i = new Image();
		i.setFileName("testImage.png");
		i.setHeight(300);
		i.setWidth(300);
		i.setFileSize(11);
		i.print();
		System.out.println();

		i = c.createImage(i);
		System.out.println("Create Image");
		i.print();
		System.out.println();

		Long idImage = i.getId();

		i.setFileName("updateTest.jpg");
		i = c.updateImage(idImage, i);
		System.out.println("Update Image");
		i.print();
		System.out.println();

		try {
			i = c.getImage(idImage, new Image());
		} catch (Exception e) {
			System.out.println("Failed");
			e.printStackTrace();
		}
		System.out.println("Get Image " + idImage);
		i.print();
		System.out.println();

		System.out.println("Deleted Image");
		c.deleteImage(idImage);

		try {
			i = c.getImage(idImage, new Image());
		} catch (Exception e) {
			System.out.println("Failed");
			e.printStackTrace();
		}
		System.out.println("Get Image " + idImage);
		i.print();
		System.out.println();


		Video v = new Video();
		v.setFileName("testVideo.png");
		v.setFileSize(9);
		v.print();
		System.out.println();

		v = c.createVideo(v);
		System.out.println("Create Video");
		v.print();
		System.out.println();

		Long idVideo = v.getId();

		v.setFileName("updateTest.jpg");
		v = c.updateVideo(idVideo, v);
		System.out.println("Update Video");
		v.print();
		System.out.println();

		v = c.getVideo(idVideo, new Video());
		System.out.println("Get Video " + idVideo);
		v.print();
		System.out.println();

		System.out.println("Deleted Video " + idVideo);
		c.deleteVideo(idVideo);

		v = c.getVideo(idVideo, new Video());
		System.out.println("Get Video " + idVideo);
		v.print();
		System.out.println();
	}
}