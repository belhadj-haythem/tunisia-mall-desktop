package tunisia.mall.businessDelegate;


import tunisia.mall.serviceLocator.ServiceLocator;

import java.util.List;

import tunisia.mall.interfaces.VendorServiceRemote;
import tunisia.mall.persistance.Vendor;

public class VendorServiceDelegate {
	static String jndiName="/tunisia.mall.ejb/VendorService!tunisia.mall.interfaces.VendorServiceRemote";
	
	public static VendorServiceRemote getProxy(){
		return (VendorServiceRemote) ServiceLocator.getInstance().getRemoteProxy(jndiName);
	}
	public static boolean addVendor(Vendor vendor){
		return getProxy().addVendor(vendor);
	}
	
	public  static boolean isFloat(String s){
		return getProxy().isFloat(s);
	}
	
	public static boolean removeVendor(Vendor vendor){
		return getProxy().removeVendor(vendor);
	}
	
	public static boolean updateVendor(Vendor vendor){
		return getProxy().updateVendor(vendor);
	}
	
	public static List<Vendor> listVendor(int id){
		return getProxy().listVendor(id);
	}
	public static List<Vendor> findVendorByFname(String fname,int idshop){
		return getProxy().findVendorByFname(fname,idshop);
	}
}
