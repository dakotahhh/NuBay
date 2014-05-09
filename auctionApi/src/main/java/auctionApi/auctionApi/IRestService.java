package auctionApi.auctionApi;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import model.Item;

@Path("/item")
public interface IRestService {
	@GET
	@Path("/{itemId}")
	@Produces("application/vnd.neumont.edu.auction-v1+json")
	Response retrieveItem(@PathParam("itemId") long itemId, @HeaderParam("Authorization") String authorizationHeader);
	
	@POST
	@Path("/")
	@Produces("application/vnd.neumont.edu.auction-v1+json")
	Response createItem(Item item) throws Exception;
}
