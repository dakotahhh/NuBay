package edu.neumont.csc380.hello.service;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Service;

@Provider
@Service
public class ImageExceptionMapper implements
		ExceptionMapper<ImageNotFoundException> {

	public Response toResponse(ImageNotFoundException exception) {
		return Response.status(404).build();
	}

}
