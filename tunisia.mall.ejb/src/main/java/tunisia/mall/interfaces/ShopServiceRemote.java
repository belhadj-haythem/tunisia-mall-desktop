package tunisia.mall.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tunisia.mall.persistance.Shop;

@Remote
public interface ShopServiceRemote {
	
	public boolean addShop(Shop shop);
	public boolean deleteShop(Shop shop);
	public Shop findShopById(int id);
    public List<Shop> listShop();
    public boolean updateShop(Shop shop);
    public Shop searchShopByName(String nameShop);
    public List<Shop> searchShopByCategorie(int idCat);
}
