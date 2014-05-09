package dal;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.HashMap;

import model.Item;
import model.ItemStatus;
import model.Money;
import model.MoneyType;
import model.Owner;

public class NonPersistentItemManager implements ItemManager{
	private static HashMap<Long, Item> itemMap;
	private static NonPersistentItemManager manager;
	
//	int id, String name, String description,
//	Date startDate, Date endDate, Money highestBid,
//	Owner highestBidOwner, ItemStatus status){
	
	private NonPersistentItemManager(){
		itemMap = new HashMap<Long, Item>();
		
		try {
			Item i1 = new Item(11034577, "Omelette Maker", "Never miss out on an opportunity to construct the perfect omelette!",
					Date.valueOf("2014-05-08"), Date.valueOf("2014-06-01"), new Money(3.75f, MoneyType.USD), new Owner(1, new URI("/details")),new Owner(1, new URI("/details")) , ItemStatus.COMPLETE);
			Item i2 = new Item(11034578, "Darth Vader Wristwatch", "Never miss an appointment again with this flashy piece of swag.",
					Date.valueOf("2014-05-04"), Date.valueOf("2014-07-11"), new Money(29.95f, MoneyType.USD), new Owner(2, new URI("/details")), new Owner(2, new URI("/details")), ItemStatus.READY);
			Item i3 = new Item(11034579, "YoloSwagHero Complete Collection", "Contains all 4 games from the hit PS3 series. Like-new condition, and collector's map is included.",
					Date.valueOf("2014-05-08"), Date.valueOf("2014-06-01"), new Money(60.00f, MoneyType.USD), new Owner(2, new URI("/details")), new Owner(2, new URI("/details")), ItemStatus.SAVED);
			
			itemMap.put(11034577L, i1);
			itemMap.put(11034578L, i2);
			itemMap.put(11034579L, i3);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static NonPersistentItemManager getInstance(){
		if (manager == null){
			manager = new NonPersistentItemManager();
		}
		
		return manager;
	}
	
	public Item retrieveItem(long id) {
		return itemMap.containsKey(id) ? itemMap.get(id) : null;
	}

	public boolean createItem(Item item) {
		boolean succeeded = (!itemMap.containsKey(item.getId()));

		if (succeeded){
			itemMap.put(item.getId(), item);
		}
		
		return succeeded;
	}

	public boolean updateItem(Item item) {
		boolean succeeded = (itemMap.containsKey(item.getId()));
		
		if (succeeded){
			itemMap.remove(item.getId());
			itemMap.put(item.getId(), item);
		}
		
		return succeeded;
	}

	public Item deleteItem(long id) {
		Item result = null;
		
		if (itemMap.containsKey(id)){
			result = itemMap.get(id);
			itemMap.remove(id);
		}
		
		return result;
	}

}
