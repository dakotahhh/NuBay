package edu.neumont.csc380.hello.service;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Response;
import org.springframework.stereotype.Service;

@Service("videoService")
public class VideoServiceImpl implements VideoService {
	private Map<Long, Video> videos = new HashMap<Long, Video>();
	private Long sequenceId = 1L;

	public Response getVideo(Long id, Video video)
	{
		Video v = videos.get(id);
		if(v == null)
		{
			throw new VideoNotFoundException();
		}
		else
		{
			return Response.ok(v).entity(v).build();
		}
	}

	//needs to be owner of image
	public Response updateVideo(Long id, Video videoData)
	{
		Video v = videos.put(id,  videoData);
		return Response.ok(v).entity(videoData).build();
	}

	//needs to be owner of image
	public Response createVideo(Video videoData)
	{
		videoData.setId(sequenceId++);
		videos.put(videoData.getId(), videoData);
		return Response.status(201).entity(videoData).build();
	}

	//needs to be owner of image
	public Response deleteVideo(Long id)
	{
		videos.remove(id);
		return Response.status(204).build();
	}
}