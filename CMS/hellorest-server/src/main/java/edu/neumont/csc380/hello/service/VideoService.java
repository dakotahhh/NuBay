package edu.neumont.csc380.hello.service;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.io.File;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;


@Path("/video")
@PermitAll
@Produces("application/vnd.neumont.edu.media-v1+json")
public interface VideoService {

	@GET
	@Path("/{id}")
	@Produces("multipart/mixed")
	Response getVideo(@PathParam("id") Long id);
	
	@PUT
	@Path("/{id}")
	@RolesAllowed("VideoOwner")
	@Consumes({"multipart/mixed"})
	Response updateVideo(@PathParam("id") Long id, @Multipart(value = "videoData") Video videoData, @Multipart(value = "videoFile") File videoFile);
	
	@POST
	@Consumes("multipart/mixed")
	Response createVideo(@Multipart(value = "videoData") Video videoData, @Multipart(value = "videoFile") File videoFile);
	
	@DELETE
	@Path("/{id}")
	@RolesAllowed("VideoOwner")
	Response deleteVideo(@PathParam("id") Long id);

}
