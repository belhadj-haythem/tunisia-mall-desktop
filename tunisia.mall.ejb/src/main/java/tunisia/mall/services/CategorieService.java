package tunisia.mall.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tunisia.mall.interfaces.CategorieLocal;
import tunisia.mall.interfaces.CategorieRemote;
import tunisia.mall.persistance.ProductCategorie;
import tunisia.mall.persistance.Shop;

/**
 * Session Bean implementation class CategorieService
 */
@Stateless
@LocalBean
public class CategorieService implements CategorieRemote, CategorieLocal {

    
	@PersistenceContext(name="ejb-sample")
    EntityManager entitymanager;
	
    public CategorieService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public boolean addCategorie(ProductCategorie c) {
		entitymanager.persist(c);
		return true;
	}

	@Override
	public boolean deleteCategorie(ProductCategorie c) {
		entitymanager.remove(entitymanager.merge(c));
		return true;
	}

	@Override
	public ProductCategorie findCategorie(int id) {
		return entitymanager.find(ProductCategorie.class, id);
	}


	@Override
	public ProductCategorie findCategorieByName(String nom) {
		String requete="select i from ProductCategorie i where i.name=:r";
		ProductCategorie pc=null;
		Query query=entitymanager.createQuery(requete).setParameter("r", nom);
		pc=(ProductCategorie) query.getSingleResult();
		return pc;
		
	}

	@Override
	public List<ProductCategorie> listCategorie() {
		return entitymanager.createQuery("select s from ProductCategorie s").getResultList();
	}

}
