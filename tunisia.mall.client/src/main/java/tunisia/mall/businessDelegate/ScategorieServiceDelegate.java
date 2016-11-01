package tunisia.mall.businessDelegate;

import java.util.List;

import tunisia.mall.interfaces.CategorieRemote;
import tunisia.mall.interfaces.ScategorieServiceRemote;
import tunisia.mall.persistance.SubCategorie;
import tunisia.mall.serviceLocator.ServiceLocator;

public class ScategorieServiceDelegate {
	
	private static String jndiName = "tunisia.mall.ejb/ScategorieService!tunisia.mall.interfaces.ScategorieServiceRemote";

	public static ScategorieServiceRemote getProxy() {
		return (ScategorieServiceRemote) ServiceLocator.getInstance().getRemoteProxy(jndiName);
	}

	public static boolean addSubCategorie(SubCategorie s){
		return getProxy().addSubCategorie(s);
	}
	public static boolean updateSubCategorie(SubCategorie s){
		return getProxy().updateSubCategorie(s);
	}
	public static boolean removeSubCategorie(SubCategorie s){
		return getProxy().removeSubCategorie(s);
	}
	public static List<SubCategorie> listSubCategorie(){
		return getProxy().listSubCategorie();
	}
	public static SubCategorie findSubCategorieByName(String name){
		return getProxy().findSubCategorieByName(name);
	}
	public static List<SubCategorie> listSubCategorieByCategorie(String categorieName){
		return getProxy().listSubCategorieByCategorie(categorieName);
	}
	public static SubCategorie findSubCategorieById(int id){
		return getProxy().findSubCategorieById(id);
	}
}
