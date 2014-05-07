package edu.neumont.csc380.AuthServer;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.model.wadl.ElementClass;

@Path("/authenticate")
public interface IAuthenticationService {
	
	@POST
	@Consumes("application/vnd.neumont.auth.edu-v1+json")
	@Path("/login")
	@Produces("application/json")
	@ElementClass(request = Credentials.class)
	Response handleLoginRequest(Credentials credentials)  throws Exception;

	@POST
	@Consumes("application/vnd.neumont.auth.edu-v1+json")
	@Path("/logout")
	Response handleLogoutRequest(AuthPayload payload)  throws Exception;
	
	@POST
	@Consumes("application/vnd.neumont.auth.edu-v1+json")
	@Path("/create")
	@Produces("application/json")
	Response handleCreationRequest(Credentials creds)  throws Exception;
	
	@POST
	@Consumes("application/vnd.neumont.auth.edu-v1+json")
	@Path("/verify")
	@Produces("application/json")
	Response verify(AuthPayload auth)  throws Exception;
}

//@Path("/Authorize")
//public interface AuthorizeService {
//	@POST
//	@Path("/{name}")
//	@Produces("text/html")
//	String sayHello(@PathParam("name") String name);
//}
