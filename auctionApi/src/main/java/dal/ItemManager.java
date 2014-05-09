package dal;

import model.Item;

public interface ItemManager {
	Item retrieveItem(long id);
	boolean createItem(Item item);
	boolean updateItem(Item item);
	Item deleteItem(long id);
}
