package tunisia.mall.businessDelegate;

import java.util.List;

import tunisia.mall.interfaces.ItemServiceRemote;
import tunisia.mall.persistance.Item;
import tunisia.mall.persistance.Shop;
import tunisia.mall.persistance.Stock;
import tunisia.mall.serviceLocator.ServiceLocator;

public class ItemServiceDelegate {
	
	private static String jndiName = "tunisia.mall.ejb/ItemService!tunisia.mall.interfaces.ItemServiceRemote";

	public static ItemServiceRemote getProxy() {
		return (ItemServiceRemote) ServiceLocator.getInstance().getRemoteProxy(jndiName);
	}
	
	
	
	public static Item recupererItem(int id){
		return getProxy().recupererItem(id);
	}
	public static List<String> returnColorItem(String reference, int id){
		return getProxy().returnColorItem(reference, id);
	}
	public static boolean addItem(Item item){
		return getProxy().addItem(item);
	}
	public static boolean removeItem(Item item){
		return getProxy().removeItem(item);
	}
	public static Item findById(int id){
		return getProxy().findById(id);
	}
	public static List<Item> listItems(int id){
		return getProxy().listItems(id);
	}
	public static void updateItem(Item item){
		getProxy().updateItem(item);
	}
	public static Shop findByIdShop(int id){
		return getProxy().findByIdShop(id);
		}
	public static boolean addShop(Shop shop){
		return getProxy().addShop(shop);
	}
	public static List<Item> searchItemByReference(String reference, int id){
		return getProxy().searchItemByReference(reference, id);
	}
	public static boolean testExistingReference(String reference,int id){
		return getProxy().testExistingReference(reference, id);
	}
	public static boolean addStock(Stock stock){
		return getProxy().addStock(stock);
	}
	public static List<Stock> listStock(int id){
		return getProxy().listStock(id);
	}
	public static List<Stock> listStockPerSize(int size){
		return getProxy().listStockPerSize(size);
	}
}
