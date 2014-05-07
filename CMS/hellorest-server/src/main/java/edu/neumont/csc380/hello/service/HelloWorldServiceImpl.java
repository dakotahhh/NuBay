package edu.neumont.csc380.hello.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.springframework.stereotype.Service;

@Service("helloWorldService")
public class HelloWorldServiceImpl implements ImageService, VideoService {
	private Map<Long, Image> images = new HashMap<Long, Image>();
	private Map<Long, Video> videos = new HashMap<Long, Video>();
	private Long sequenceId = 1L;
	
	public Response getImage(Long id)
	{
		Image i = images.get(id);
		if(i == null)
		{
			return Response.status(404).build();
		}
		else
		{
			return Response.ok(i).build();
		}
	}
	
	public Response getVideo(Long id)
	{
		Video v = videos.get(id);
		if(v == null)
		{
			return Response.status(404).build();
		}
		else
		{
			return Response.ok(v).build();
		}
	}
	
	public Response updateImage(Long id, @Multipart(value = "imageData") Image imageData, @Multipart(value = "imageFile") File imageFile)
	{
		Image i = images.put(id, imageData);
		return Response.ok(i).build();
	}
	
	public Response updateVideo(Long id, @Multipart(value = "videoData") Video videoData, @Multipart(value = "videoFile") File videoFile)
	{
		Video v = videos.put(id,  videoData);
		return Response.ok(v).build();
	}
	
	public Response createImage(@Multipart(value = "imageData") Image imageData, @Multipart(value = "imageFile") File imageFile)
	{
		imageData.setId(sequenceId++);
		images.put(imageData.getId(), imageData);
		return Response.status(201).entity(imageData).build();
	}
	
	public Response createVideo(@Multipart(value = "videoData") Video videoData, @Multipart(value = "videoFile") File videoFile) 
	{
		videoData.setId(sequenceId++);
		videos.put(videoData.getId(), videoData);
		return Response.status(201).entity(videoData).build();
	}
	
	//needs to be owner of image
	public Response deleteImage(Long id)
	{
		images.remove(id);
		return Response.status(204).build();
	}
	
	//needs to be owner of image
	public Response deleteVideo(Long id)
	{
		videos.remove(id);
		return Response.status(204).build();
	}
}
