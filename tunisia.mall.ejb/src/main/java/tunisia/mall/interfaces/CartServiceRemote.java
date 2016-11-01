package tunisia.mall.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;


import tunisia.mall.persistance.Cart;
import tunisia.mall.persistance.Item;
import tunisia.mall.persistance.Order;
import tunisia.mall.persistance.Vendor;

@Remote
public interface CartServiceRemote {
	boolean addCart(Cart cart);
	boolean addOrder(Order order);
	boolean update(Order order);
	List<Order> listOrder();
	List<Order> findOrderByadress(String fadress);
	List<Cart> findpriceItem(int id,int idshop);
	Order findOrder(int id);
	List<Cart> listCartByIdShop(int idshop);
	long countItems(int idshop);
	boolean updateCart(Cart cart);

	
    

}
