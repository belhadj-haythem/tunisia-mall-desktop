package tunisia.mall.interfaces;

import java.util.List;

import javax.ejb.Remote;
import javax.persistence.Query;

import tunisia.mall.persistance.SubCategorie;

@Remote
public interface ScategorieServiceRemote {
	
	public boolean addSubCategorie(SubCategorie s);
	public boolean updateSubCategorie(SubCategorie s);
	public boolean removeSubCategorie(SubCategorie s);
	public List<SubCategorie> listSubCategorie();
	public SubCategorie findSubCategorieByName(String name);
	public List<SubCategorie> listSubCategorieByCategorie(String categorieName);
	public SubCategorie findSubCategorieById(int id);

}
