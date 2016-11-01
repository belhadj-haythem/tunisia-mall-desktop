package tunisia.mall.services;



import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import tunisia.mall.interfaces.CartServiceLocal;
import tunisia.mall.interfaces.CartServiceRemote;
import tunisia.mall.persistance.Cart;
import tunisia.mall.persistance.CartPK;
import tunisia.mall.persistance.Item;
import tunisia.mall.persistance.Order;

/**
 * Session Bean implementation class CartService
 */
@Stateless
@LocalBean
public class CartService implements CartServiceRemote, CartServiceLocal {

	@PersistenceContext(name="ejb-sample")
	EntityManager entitymanager;
	
    public CartService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public boolean addCart(Cart cart) {
		entitymanager.persist(cart);
		return true;
	}

	@Override
	public boolean addOrder(Order order) {
		entitymanager.persist(order);
		return false;
	}

	@Override
	public List<Order> listOrder() {
		return entitymanager.createQuery("select c from Order c").getResultList();
		
	}

	@Override
	public List<Order> findOrderByadress(String fadress) {
		String requete="select c from Order c where c.adress=:fadress";
		Query query	= entitymanager.createQuery(requete).setParameter("fadress", fadress);
		return query.getResultList();
	}

	@Override
	public boolean update(Order order) {
		entitymanager.merge(order);
		return false;
	}

	@Override
	public List<Cart> findpriceItem(int id,int idshop) {
		String requete= "select c from Cart c where c.customer.id_account=:id and c.items.shop.id_account=:idshop";
		
		Query query = entitymanager.createQuery(requete).setParameter("id", id).setParameter("idshop", idshop);
		 return query.getResultList();
		
	}

	@Override
	public Order findOrder(int id) {
		return entitymanager.find(Order.class, id);
	}

	@Override
	public List<Cart> listCartByIdShop(int idshop) {
String requete= "select c from Cart c where c.items.shop.id_account=:idshop";
		
		Query query = entitymanager.createQuery(requete).setParameter("idshop", idshop);
		 return query.getResultList();
		
	}

	@Override
	public long countItems(int idshop) {
		String requete = "select count(p) from Item p where p.shop.id_account=:idshop";
		return entitymanager.createQuery(requete,Long.class).setParameter("idshop", idshop).getSingleResult();
		
		
	}

	@Override
	public boolean updateCart(Cart cart) {
		entitymanager.merge(cart);
		return false;
	}

	
	



	

	

	
	
	

}
