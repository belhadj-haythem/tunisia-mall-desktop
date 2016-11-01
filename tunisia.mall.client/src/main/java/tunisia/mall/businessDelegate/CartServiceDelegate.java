package tunisia.mall.businessDelegate;


import java.util.List;
import tunisia.mall.interfaces.CartServiceRemote;
import tunisia.mall.persistance.Cart;
import tunisia.mall.persistance.Order;
import tunisia.mall.serviceLocator.ServiceLocator;

public class CartServiceDelegate {
	
	static String jndiname="/tunisia.mall.ejb/CartService!tunisia.mall.interfaces.CartServiceRemote";
	
	public static CartServiceRemote getProxy(){
		return (CartServiceRemote) ServiceLocator.getInstance().getRemoteProxy(jndiname);
	}
	
	public static boolean addCart(Cart cart){
		return getProxy().addCart(cart);
		
	}
	public static boolean addOrder(Order order){
		return getProxy().addOrder(order);
	}
	public static List<Order> listOrder(){
		return getProxy().listOrder();
	}
	public static List<Order> findOrderByadress(String fadress){
		return getProxy().findOrderByadress(fadress);
	}
	
	public static boolean update(Order order){
		return getProxy().update(order);
		}
	public static List<Cart> findpriceItem(int id,int idshop){
		return getProxy().findpriceItem(id,idshop);
	}
	public static Order findOrder(int id){
		return getProxy().findOrder(id);
	}
	public static List<Cart> listCartByIdShop(int idshop){
		return getProxy().listCartByIdShop(idshop);
	}
	public static long countItems(int idshop){
		return getProxy().countItems(idshop);
	}
	public static boolean updateCart(Cart cart){
		return getProxy().updateCart(cart);
	}
}
