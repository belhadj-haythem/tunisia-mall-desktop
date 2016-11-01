package tunisia.mall.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import tunisia.mall.interfaces.ShopServiceRemote;
import tunisia.mall.persistance.Shop;
import tunisia.mall.services.ShopService;


public class ShopTest {

	ShopServiceRemote proxy;

	@Before
	public void setUp() {
		Context context;
		String jndiName = "tunisia.mall.ejb/ShopService!tunisia.mall.interfaces.ShopServiceRemote";

		try {
			context = new InitialContext();
			proxy = (ShopServiceRemote) context.lookup(jndiName);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void testAddShop(){
		ShopService s = new ShopService();
		Shop shop = new Shop();
		shop.setPhone(54438602);
		shop.setWebsite("aahttp://www.google.com");
		shop.setNameShop("Orange");
		shop.setEmail("aaahaythem.belhadj@esprit.tn");
		shop.setUsername("haythem");
		shop.setPassword("ziz");
		shop.setEnabled(true);

		assertEquals(true, proxy.addShop(shop));
	}
	
	@Test
	public void testListShop(){
		List<Shop> shops = proxy.listShop();
		assertEquals(1,shops.size());
	}
	
	@Test
	public void testFindShop(){
		Shop shop = proxy.findShopById(2);
		//System.out.println(Shop);
		assertEquals(2, shop.getId_account());
	}
	
	@Test
	public void testRemoveShop(){
		Shop shop = proxy.findShopById(28);
		//System.out.println(Shop);
		assertEquals(true, proxy.deleteShop(shop));
	}
	
	@Test
	public void testUpdateShop(){
		Shop s = proxy.findShopById(9);
		s.setNameShop("zara");
		//System.out.println(Shop);
		assertEquals(true, proxy.updateShop(s));
	}
}
