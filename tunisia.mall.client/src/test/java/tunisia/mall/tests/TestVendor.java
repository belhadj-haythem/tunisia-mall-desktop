package tunisia.mall.tests;

import static org.junit.Assert.*;

import java.util.Date;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import tunisia.mall.businessDelegate.VendorServiceDelegate;
import tunisia.mall.interfaces.VendorServiceRemote;
import tunisia.mall.persistance.Shop;
import tunisia.mall.persistance.Vendor;

public class TestVendor {
	
	Context context;
	VendorServiceRemote proxy;

	
@Before
	public void setup(){
	
	
	
		
		String jndiname="/tunisia.mall.ejb/VendorService!tunisia.mall.interfaces.VendorServiceRemote";
		try {
			context = new InitialContext();
			proxy = (VendorServiceRemote)context.lookup(jndiname);
			
					
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	@Test
	public void testaddVendor() {
		Vendor vendor = new Vendor();
		 
		 
		java.util.Date myDate = new java.util.Date("10/10/2009");
		java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
		 
		vendor.setDateEmbauche(sqlDate);
		 
		vendor.setFirst_name("Hadhemi");
		vendor.setLast_name("Mejri");
		vendor.setIdCard(33);
		vendor.setSalary(200f);
		assertEquals(false, proxy.addVendor(vendor));
	}
	@Test
	public void testfindVendor(){
		Vendor vendor = proxy.findVendorById(1);
		System.out.println(vendor);
		assertEquals(33, vendor.getId());
		
	}
	@Test
	public void testfindShop(){
		Shop shop = proxy.findShopById(1);
		assertEquals(4, shop.getId_account());
	}
	@Test
	public void testremoveVendor(){
		Vendor vendor = new Vendor();
		vendor= proxy.findVendorById(1);
		proxy.removeVendor(vendor);
	}
	
	@Test
	public void testlistVendor(){
		List<Vendor> vendors = proxy.listVendor(500);
		assertEquals(1, vendors.size());
		System.out.println(vendors+"aaaaaaaaa\n");
	}
	@Test
	public void testmatchVendorToShop(){
		Vendor vendor = proxy.findVendorById(4);
		Shop shop = proxy.findShopById(1);
		assertEquals(false, proxy.JoinVendorToShop(vendor, shop));
	}
	public  boolean isFloat(String s) {
		 boolean v = false;
	        try {
	            Float.parseFloat(s);
	            v = true;
	        } catch (Exception e) {
	            v = false;
	        }
	        return v;
	}
	@Test
	public void testlistVendorByName(){
		List<Vendor> vendors = proxy.findVendorByFname("sirine",2001);
		assertEquals(1, vendors.size());
		System.out.println(vendors.size());
		
	}
	
	
	
	
}
