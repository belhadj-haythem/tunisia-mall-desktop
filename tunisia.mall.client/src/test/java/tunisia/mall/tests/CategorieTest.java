package tunisia.mall.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import tunisia.mall.interfaces.CategorieRemote;
import tunisia.mall.interfaces.ShopServiceRemote;
import tunisia.mall.persistance.ProductCategorie;
import tunisia.mall.persistance.Shop;
import tunisia.mall.services.CategorieService;
import tunisia.mall.services.ShopService;

public class CategorieTest {

	
	CategorieRemote proxy;

	@Before
	public void setUp() {
		Context context;
		String jndiName = "tunisia.mall.ejb/CategorieService!tunisia.mall.interfaces.CategorieRemote";

		try {
			context = new InitialContext();
			proxy = (CategorieRemote) context.lookup(jndiName);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void testAddCategorie(){
		CategorieService c = new CategorieService();
		ProductCategorie cat = new ProductCategorie();
		cat.setName("Restauration");
		assertEquals(true, proxy.addCategorie(cat));
	}
	
	@Test
	public void testfindCategorie(){
		String name="Beaute et bien-etre";
		assertEquals(16, proxy.findCategorieByName(name).getId());
	}
	
	@Test
	public void testListCategorie(){
		List<ProductCategorie> cat = proxy.listCategorie();
		assertEquals(1,cat.size());
	}
}
