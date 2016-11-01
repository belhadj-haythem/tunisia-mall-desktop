package tunisia.mall.businessDelegate;

import java.util.List;

import tunisia.mall.interfaces.ShopServiceRemote;
import tunisia.mall.persistance.ProductCategorie;
import tunisia.mall.persistance.Shop;
import tunisia.mall.serviceLocator.ServiceLocator;

public class ShopServiceDelegate {
	
	private static String jndiName = "tunisia.mall.ejb/ShopService!tunisia.mall.interfaces.ShopServiceRemote";

	public static ShopServiceRemote getProxy() {
		return (ShopServiceRemote) ServiceLocator.getInstance().getRemoteProxy(jndiName);
	}
	
	public static boolean addShop(Shop shop){
		return getProxy().addShop(shop);
	}
	public static boolean deleteShop(Shop shop){
		return getProxy().deleteShop(shop);
	}
	public static Shop findShopById(int id){
		return getProxy().findShopById(id);
	}
    public static List<Shop> listShop(){
    	return getProxy().listShop();
    }
    public static boolean updateShop(Shop shop){
    	return getProxy().updateShop(shop);
    }
    public static Shop searchShopByName(String nameShop){
    	return getProxy().searchShopByName(nameShop);
    }
 

}
