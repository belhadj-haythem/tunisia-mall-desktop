package tunisia.mall.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tunisia.mall.persistance.Item;
import tunisia.mall.persistance.Shop;
import tunisia.mall.persistance.Stock;

@Remote
public interface ItemServiceRemote {

	public Item recupererItem(int id);
	public List<String> returnColorItem(String reference, int id);
	public boolean addItem(Item item);
	public boolean removeItem(Item item);
	public Item findById(int id);
	public List<Item> listItems(int id);
	public void updateItem(Item item);
	public Shop findByIdShop(int id);
	public boolean addShop(Shop shop);
	public List<Item> searchItemByReference(String reference, int id);
	public boolean testExistingReference(String reference,int id);
	public boolean addStock(Stock stock);
	List<Stock> listStock(int id);
	List<Stock> listStockPerSize(int size);
	

	
	
}
