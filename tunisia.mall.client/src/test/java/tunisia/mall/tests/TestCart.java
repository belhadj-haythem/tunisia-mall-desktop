package tunisia.mall.tests;


import static org.junit.Assert.*;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;

import tunisia.mall.businessDelegate.CartServiceDelegate;
import tunisia.mall.interfaces.CartServiceRemote;
import tunisia.mall.persistance.Cart;
import tunisia.mall.persistance.CartPK;
import tunisia.mall.persistance.Order;
import tunisia.mall.persistance.Vendor;

public class TestCart {

	Context context;
	CartServiceRemote proxy;
	CartServiceDelegate cartservicedelegate;

	@Before
	public void setup() {

		String jndiname = "tunisia.mall.ejb/CartService!tunisia.mall.interfaces.CartServiceRemote";
		try {
			context = new InitialContext();
			proxy = (CartServiceRemote) context.lookup(jndiname);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testaddCart() {
		Cart cart = new Cart();
		CartPK pk = new CartPK(20, 1);
		//List<Cart> carts = proxy.findpriceItem(13, 2000);

		cart.setCartPk(pk);

		cart.setQuantity(2);

		assertEquals(true, proxy.addCart(cart));

	}

	@Test
	public void testaddOrder() {
		Order order = new Order();
		order.setAdress("bizert");

		java.util.Date myDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
		List<Cart> carts = proxy.findpriceItem(12, 6);
		int j = proxy.findpriceItem(12, 6).size();
		float totalCost = 0;

		for (int i = 0; i < j; i++) {
			float p = carts.get(i).getItems().getPrice();
			int t = carts.get(i).getQuantity();
			totalCost = totalCost + p * t;

		}
		order.setDate(sqlDate);

		order.setTotalCost(totalCost); // a faire

		order.setDelivery(false);
		order.setCarts(carts);

		// proxy.addOrder(order);

		for (Cart c : carts) {
			System.out.println(c.getItems().getPrice());
			c.setOrder(order);
			assertEquals(false, proxy.updateCart(c));

		}

	}

	@Test
	public void testfindOrderByadress() {

		List<Order> orders = proxy.findOrderByadress("bizert");
		assertEquals(1, orders.size());

	}

	@Test
	public void testlistOrder() {
		List<Order> order = proxy.listOrder();
		assertEquals(2, order.size());
	}

	@Test
	public void testpriceitemfromcart() {

		List<Cart> carts = proxy.findpriceItem(2000, 2001);
		int j = proxy.findpriceItem(2000, 2001).size();
		System.out.println(j);
		// float totalCost= 0;
		//
		// for(int i=0;i<j;i++){
		// float p= carts.get(i).getItems().getPrice();
		// int t= carts.get(i).getQuantity();
		// totalCost=totalCost+p*t;
		//
		//
		// }
		// System.out.println("bbbb");
		// System.out.println(totalCost);

	}

	@Test
	public void testcartByShop() {
		List<Cart> carts = proxy.listCartByIdShop(2001);
		int j = proxy.listCartByIdShop(2001).size();
		System.out.println(j);

	}

	@Test
	public void testCountItem() {
		Long i = proxy.countItems(2001);

		System.out.println(i);
	}

}
