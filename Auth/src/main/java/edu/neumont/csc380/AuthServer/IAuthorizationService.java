package edu.neumont.csc380.AuthServer;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/authorize")
public interface IAuthorizationService {
	@POST
	@Consumes("application/vnd.neumont.auth.edu-v1+json")
	@Path("/checkAuth")
	@Produces("application/json")
	Response handleAuthorizationRequest(AuthPayload payload) throws Exception;
	
	@POST
	@Consumes("application/vnd.neumont.auth.edu-v1+json")
	@Path("/updateAuth")
	@Produces("application/json")
	Response handleUpdateRequest(AuthPayload payload) throws Exception;
	
	@POST
	@Consumes("application/vnd.neumont.auth.edu-v1+json")
	@Path("/deleteAuth")
	@Produces("application/json")
	Response handleDeletionRequest(AuthPayload payload) throws Exception;
}

//@Path("/Authorize")
//public interface AuthorizeService {
//	@POST
//	@Path("/{name}")
//	@Produces("text/html")
//	String sayHello(@PathParam("name") String name);
//}
