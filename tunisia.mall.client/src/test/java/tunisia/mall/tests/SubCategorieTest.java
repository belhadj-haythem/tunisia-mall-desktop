package tunisia.mall.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import tunisia.mall.businessDelegate.CategorieServiceDelegate;
import tunisia.mall.businessDelegate.ItemServiceDelegate;
import tunisia.mall.businessDelegate.ScategorieServiceDelegate;
import tunisia.mall.persistance.Item;
import tunisia.mall.persistance.ProductCategorie;
import tunisia.mall.persistance.Stock;
import tunisia.mall.persistance.SubCategorie;

public class SubCategorieTest {

	
	@Test
	public void testAdd(){
		/*SubCategorie s = new SubCategorie();
		s.setName("gloves");
		ProductCategorie a = CategorieServiceDelegate.findCategorieByName("Accessoires de mode");
		s.setCategorie(a);
		assertEquals(true,ScategorieServiceDelegate.addSubCategorie(s));*/
		
		
		Stock c = new Stock();
		Item s = ItemServiceDelegate.findById(5);
		System.out.println(s);
		c.setItem(s);
		c.setQuantity(20);
		c.setSize("aa");
		assertEquals(true,ItemServiceDelegate.addStock(c));
	}
	
	@Test
	public void testListSubCategorieByCategorie(){
		List<SubCategorie> maliste = ScategorieServiceDelegate.listSubCategorieByCategorie("Accessoires de mode");
		System.out.println(maliste.size());
		assertEquals(1,maliste.size());

	}
}
