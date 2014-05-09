package auctionApi.auctionApi;

import javax.ws.rs.core.Response;

import model.Item;
import dal.NonPersistentItemManager;

public class RestService implements IRestService {
	private static final float MIN_BID_AMT = 0.01f;
	private static final float MAX_DESCRIPTION_LENGTH = 255;

	public Response retrieveItem(long itemId, String authorizationHeader) {
		Item item = NonPersistentItemManager.getInstance().retrieveItem(itemId);
		boolean authorized = true; // if authorization level is that of owner...
		
		System.out.println("Auth Header: " + authorizationHeader);
		
		if (item != null && authorized){
			item.setPlaceBidUri(null);
		}
		else{
			// set "edit", "delete", and "history" to null
		}
		
		return Response.status(200).entity(item).build();
	}

	public Response createItem(Item item) throws Exception {
		boolean succeeded = NonPersistentItemManager.getInstance().createItem(item);
		Exception error = succeeded ? checkForErrors(item) : new Exception("An item with that ID already exists.");
		
		if (error != null){
			throw error;
		}
		
		return Response.status(200).entity(item).build();
	}

	private Exception checkForErrors(Item item) {
		Exception error = null;
		
		if (isNullOrWhitespace(item.getName())){
			error = new Exception("Item name is missing");
		}
		else if (item.getOwner() == null){
			error = new Exception("Item owner is missing");
		}
		else if (item.getEndDate() != null && item.getEndDate().before(item.getStartDate())){
			error = new Exception("The end date is before the start date");
		}
		else if (item.getHighestBid().getAmount() < MIN_BID_AMT){
			error = new Exception("The bid is too low (minimum is $.01)");
		}
//		else if (isImageUrlInvalid){
//			error = new Exception("The image link is not a valid URL");
//		}
//		else if (isImageUnsupported)
//			error = new Exception("The image specified is too big or is otherwise unsupportable");
//		}
		else if (item.getDescription() == null || item.getDescription().length() > MAX_DESCRIPTION_LENGTH){
			error = new Exception("Description is too long");
		}
//		else if (isDescriptionUnsafe){
//			error = new Exception("Description contains scripting or other unsafe HTML");
//		}
		
		return error;
	}

	private boolean isNullOrWhitespace(String value){
		return (value == null || value.trim().isEmpty());
	}
}
