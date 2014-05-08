package edu.neumont.csc380.hello.service;

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

import edu.neumont.csc380.hello.service.Image;

@Path("/image")
//@PermitAll
public interface ImageService {

	@GET
	@Path("/{id")
	@Consumes("application/json")
	@Produces("application/json")
//	@Produces("multipart/mixed")
	Response getImage(@PathParam("id") Long id, Image image);

	@PUT
	@Path("/{id}")
//	@RolesAllowed("ImageOwner")
	@Consumes("application/json")
//	@Consumes("multipart/mixed")
	@Produces("application/json")
	Response updateImage(@PathParam("id") Long id, Image imageData);
//	Response updateImage(@PathParam("id") Long id, @Multipart(value = "imageData") Image imageData, @Multipart(value = "imageFile") File imageFile);

	@POST
	@Consumes("application/json")
//	@Consumes("multipart/mixed")
	@Produces("application/json")
	Response createImage(Image imageData);
//	Response createImage(@Multipart(value = "imageData") Image imageData, @Multipart(value = "imageFile") File imageFile);

	@DELETE
	@Path("/{id}")
//	@RolesAllowed("ImageOwner")
	Response deleteImage(@PathParam("id") Long id);

}