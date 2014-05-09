package edu.neumont.csc380.hello.service;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Response;
import org.springframework.stereotype.Service;

@Service("helloWorldService")
public class HelloWorldServiceImpl implements ImageService{
	private Map<Long, Image> images = new HashMap<Long, Image>();
	private Long sequenceId = 1L;

//	
//	//needs to be owner of image
//	public Response updateImage(Long id, @Multipart(value = "imageData") Image imageData, @Multipart(value = "imageFile") File imageFile)
//	{
//		Image i = images.put(id, imageData);
//		return Response.ok(i).build();
//	}
//	
//	//needs to be owner of image
//	public Response updateVideo(Long id, @Multipart(value = "videoData") Video videoData, @Multipart(value = "videoFile") File videoFile)
//	{
//		Video v = videos.put(id,  videoData);
//		return Response.ok(v).build();
//	}
//	
//	//needs to be owner of image
//	public Response createImage(@Multipart(value = "imageData") Image imageData, @Multipart(value = "imageFile") File imageFile)
//	{
//		imageData.setId(sequenceId++);
//		images.put(imageData.getId(), imageData);
//		return Response.status(201).entity(imageData).build();
//	}
//	
//	//needs to be owner of image
//	public Response createVideo(@Multipart(value = "videoData") Video videoData, @Multipart(value = "videoFile") File videoFile)
//	{
//		videoData.setId(sequenceId++);
//		videos.put(videoData.getId(), videoData);
//		return Response.status(201).entity(videoData).build();
//	}

	public Response getImage(Long id, Image image)
	{
		Image i = images.get(id);
		if(i == null)
		{
			throw new ImageNotFoundException();
		}
		else
		{
			return Response.ok(i).entity(i).build();
		}
	}

	//needs to be owner of image
	public Response updateImage(Long id, Image imageData)
	{
		Image i = images.put(id, imageData);
		return Response.ok(i).entity(i).build();
	}

	//needs to be owner of image
	public Response createImage(Image imageData)
	{
		imageData.setId(sequenceId++);
		images.put(imageData.getId(), imageData);
		return Response.status(201).entity(imageData).build();
	}

	//needs to be owner of image
	public Response deleteImage(Long id)
	{
		images.remove(id);
		return Response.status(204).build();
	}
}