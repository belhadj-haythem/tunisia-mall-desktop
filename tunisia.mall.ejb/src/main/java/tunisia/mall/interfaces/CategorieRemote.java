package tunisia.mall.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tunisia.mall.persistance.ProductCategorie;
import tunisia.mall.persistance.ProductCategorie;;

@Remote
public interface CategorieRemote {
	
	public boolean addCategorie(ProductCategorie c);
	public boolean deleteCategorie(ProductCategorie c);
	public ProductCategorie findCategorie(int id);
	public List<ProductCategorie> listCategorie();
	public ProductCategorie findCategorieByName(String nom);

}
