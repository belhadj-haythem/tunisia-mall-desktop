package tunisia.mall.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tunisia.mall.interfaces.ItemServiceLocal;
import tunisia.mall.interfaces.ItemServiceRemote;
import tunisia.mall.persistance.Item;
import tunisia.mall.persistance.Shop;
import tunisia.mall.persistance.Stock;

/**
 * Session Bean implementation class ItemService
 */
@Stateless
@LocalBean
public class ItemService implements ItemServiceRemote, ItemServiceLocal {

	@PersistenceContext
	EntityManager entityManager;

	public ItemService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Item recupererItem(int id) {
		return entityManager.find(Item.class, id);
	}

	@Override
	public boolean addItem(Item item) {
		entityManager.persist(item);
		return true;
	}

	@Override
	public boolean removeItem(Item item) {
		entityManager.remove(entityManager.merge(item));
		return true;
	}

	@Override
	public Item findById(int id) {

		return entityManager.find(Item.class, id);
	}

	@Override
	public List<Item> listItems(int id) {
		String requete = "select i from Item i where i.shop.id_account=:id";

		Query query = entityManager.createQuery(requete).setParameter("id", id);

		return query.getResultList();
	}

	@Override
	public void updateItem(Item item) {
		entityManager.merge(item);
	}

	@Override
	public Shop findByIdShop(int id) {

		return entityManager.find(Shop.class, id);
	}

	public boolean addShop(Shop shop) {
		entityManager.persist(shop);
		return true;

	}
	/*
	 * public boolean matchShoptoItem(Item item, Shop shop){ item.setShop(shop);
	 * entityManager.merge(item); return true;
	 * 
	 * }
	 */

	@Override
	public List<Item> searchItemByReference(String reference, int id) {
		String requete = "select i from Item i where i.reference=:r and i.shop.id_account=:id";

		Query query = entityManager.createQuery(requete).setParameter("r", reference).setParameter("id", id);

		return query.getResultList();
	}

	@Override
	public boolean testExistingReference(String reference, int id) {
		String requete = "select i from Item i where i.reference=:r and i.shop.id_account=:id";

		List<Item> l = new ArrayList<>();
		Query query = entityManager.createQuery(requete).setParameter("r", reference).setParameter("id", id);

		for (int i = 0; i < query.getResultList().size(); i++) {
			l.add((Item) query.getResultList().get(i));
		}

		if (l.size() == 0) {
			return false;
		}
		return true;
	}

	@Override
	public List<String> returnColorItem(String reference, int id) {
		String requete = "select i.color from Item i where i.reference=:r and i.shop.id_account=:id";
		Query query = entityManager.createQuery(requete).setParameter("r", reference).setParameter("id", id);

		return query.getResultList();
	}

	@Override
	public boolean addStock(Stock stock) {
		entityManager.persist(stock);
		return true;
	}
	@Override
	public List<Stock> listStock(int id) {
		String requete="select s from Stock s where s.item.id=:id";
		Query query=entityManager.createQuery(requete).setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public List<Stock> listStockPerSize(int size) {
		String requete="select s from Stock s where s.size=:s";
		Query query=entityManager.createQuery(requete).setParameter("s", size);
		return query.getResultList();
		
	}
	
}
