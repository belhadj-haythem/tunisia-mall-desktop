package tunisia.mall.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tunisia.mall.interfaces.VendorServiceLocal;
import tunisia.mall.interfaces.VendorServiceRemote;
import tunisia.mall.persistance.Shop;
import tunisia.mall.persistance.Vendor;

/**
 * Session Bean implementation class VendorService
 */
@Stateless
@LocalBean
public class VendorService implements VendorServiceRemote, VendorServiceLocal {

	@PersistenceContext(name="ejb-sample")
	EntityManager entitymanager;
	
    public VendorService() {
        // TODO Auto-generated constructor stub
    }

    @Override
	public boolean addVendor(Vendor vendor) {
		entitymanager.persist(vendor);
		return(true);
	}

	@Override
	public boolean removeVendor(Vendor vendor) {
		entitymanager.remove(entitymanager.merge(vendor));
		return true;
		
	}

	@Override
	public Vendor findVendorById(int id) {
		return entitymanager.find(Vendor.class, id);
	}

	@Override
	public List<Vendor> listVendor(int id) {
		String requete="select c from Vendor c where c.shop.id_account=:id";
	Query query	= entitymanager.createQuery(requete).setParameter("id", id);
	return query.getResultList();
	}

	@Override
	public boolean updateVendor(Vendor vendor) {
		entitymanager.merge(vendor);
		return true;
		
	}

	@Override
	public boolean JoinVendorToShop(Vendor vendor, Shop shop) {
		vendor.setShop(shop);
		entitymanager.merge(vendor);
		
	return true;
	}

	@Override
	public Shop findShopById(int id) {
		return entitymanager.find(Shop.class, id);
	}

	@Override
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

	@Override
	public List<Vendor> findVendorByFname(String fname,int idshop) {
		String requete="select c from Vendor c where c.first_name=:first_name and c.shop.id_account=:idshop";
		Query query	= entitymanager.createQuery(requete).setParameter("first_name", fname).setParameter("idshop", idshop);
		return query.getResultList();
		
	}

}
