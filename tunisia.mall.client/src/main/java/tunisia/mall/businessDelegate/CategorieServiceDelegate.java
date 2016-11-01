package tunisia.mall.businessDelegate;

import java.util.List;

import tunisia.mall.interfaces.CategorieRemote;
import tunisia.mall.interfaces.ShopServiceRemote;
import tunisia.mall.persistance.ProductCategorie;
import tunisia.mall.persistance.Shop;
import tunisia.mall.serviceLocator.ServiceLocator;

public class CategorieServiceDelegate {
	
	private static String jndiName = "tunisia.mall.ejb/CategorieService!tunisia.mall.interfaces.CategorieRemote";

	public static CategorieRemote getProxy() {
		return (CategorieRemote) ServiceLocator.getInstance().getRemoteProxy(jndiName);
	}

	public static boolean addCategorie(ProductCategorie c){
		return getProxy().addCategorie(c);
	}
	public static boolean deleteCategorie(ProductCategorie c){
		return getProxy().deleteCategorie(c);
	}
	public static ProductCategorie findCategorie(int id){
		return getProxy().findCategorie(id);
		
	}
	public static List<ProductCategorie> listShop(){
		return getProxy().listCategorie();
		
	}
	public static ProductCategorie findCategorieByName(String nom){
		return getProxy().findCategorieByName(nom);
			
	}
}
