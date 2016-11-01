package tunisia.mall.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tunisia.mall.interfaces.ScategorieServiceLocal;
import tunisia.mall.interfaces.ScategorieServiceRemote;
import tunisia.mall.persistance.SubCategorie;

/**
 * Session Bean implementation class ScategorieService
 */
@Stateless
@LocalBean
public class ScategorieService implements ScategorieServiceRemote, ScategorieServiceLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext(name="ejb-sample")
	EntityManager em;
    public ScategorieService() {
        // TODO Auto-generated constructor stub
    }
	@Override
	public boolean addSubCategorie(SubCategorie s) {
		em.persist(s);
		return true;
	}
	@Override
	public boolean updateSubCategorie(SubCategorie s) {
		em.merge(s);
		return true;
	}
	@Override
	public boolean removeSubCategorie(SubCategorie s) {
		em.remove(em.merge(s));
		return true;
	}

	@Override
	public List<SubCategorie> listSubCategorie() {
		return em.createQuery("select c from SubCategorie c").getResultList();
	}
	@Override
	public SubCategorie findSubCategorieByName(String name) {
		String requete="select i from SubCategorie i where i.name=:r";
		SubCategorie subcat=null;
		Query query=em.createQuery(requete).setParameter("r", name);
		subcat =(SubCategorie) query.getSingleResult();
		return subcat;
	}
	@Override
	public List<SubCategorie> listSubCategorieByCategorie(String categorieName) {
		String requete = "SELECT e FROM SubCategorie e JOIN e.categorie a WHERE a.name = :name";
		Query query = em.createQuery(requete).setParameter("name", categorieName);
		return query.getResultList();
	}
	@Override
	public SubCategorie findSubCategorieById(int id) {
		// TODO Auto-generated method stub
		return null;
	}


}
