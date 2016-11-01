package tunisia.mall.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tunisia.mall.interfaces.EventServicesLocal;
import tunisia.mall.interfaces.EventServicesRemote;
import tunisia.mall.persistance.Event;
import tunisia.mall.persistance.Item;
import tunisia.mall.persistance.Shop;
import tunisia.mall.persistance.User;

/**
 * Session Bean implementation class EventServices
 */
@Stateless
@LocalBean
public class EventServices implements EventServicesRemote {

	@PersistenceContext(name = "ejb-sample")
	EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public EventServices() {

	}

	@Override
	public boolean addEvent(Event e) {
		entityManager.persist(e);
		return true;
	}

	@Override
	public int addEvent2(Event e) {
		entityManager.persist(e);
		String requete = "select e from Event e where e.title=:titre and e.dateDebut=:dateDebut and e.dateFin=:dateFin and e.subject=:subject";
		int a = entityManager.createQuery(requete, Event.class).setParameter("titre", e.getTitle())
				.setParameter("dateDebut", e.getDateDebut()).setParameter("dateFin", e.getDateFin())
				.setParameter("subject", e.getSubject()).getSingleResult().getId();
		return a;
	}

	@Override
	public boolean updateEvent(Event e) {
		entityManager.merge(e);
		return true;
	}

	@Override
	public boolean deleteEvent(Event e) {
		entityManager.remove(entityManager.merge(e));
		return true;
	}

	@Override
	public Event findEvent(int id) {

		return entityManager.find(Event.class, id);
	}

	@Override
	public List<Event> ListEvent() {
		return entityManager.createQuery("select e from Event e").getResultList();
	}

	@Override
	public List<Event> searchListEventById(int id) {
		String requete = "select e from Event e join e.shopOwner s where s.id_account=:id";
		List<Event> ev = null;
		Query query = entityManager.createQuery(requete).setParameter("id", id);
		ev = query.getResultList();
		return ev;
	}

	@Override
	public Shop searchUserByid(int id) {
		String requete = "select e from Shop e where e.id_account=:id";
		User ev = null;
		Query query = entityManager.createQuery(requete, Shop.class).setParameter("id", id);
		try {
			ev = (Shop) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}

		return (Shop) ev;
	}

	@Override
	public List<Item> listItems(int id) {
		String requete = "select i from Item i where i.shop.id_account=:id";

		Query query = entityManager.createQuery(requete).setParameter("id", id);

		return query.getResultList();
	}

	@Override
	public boolean updateItem(Item e) {
		entityManager.merge(e);
		return true;
	}

	@Override
	public List<Item> listItemsByIdEvent(Event event) {
		String requete = "select i from Item i where i.event=:event";

		Query query = entityManager.createQuery(requete).setParameter("event", event);

		return query.getResultList();
	}

	@Override
	public Shop searchShopByName(String name) {
		
		String requete = "select e from Shop e where e.nameShop=:name";
		User ev = null;
		Query query = entityManager.createQuery(requete, Shop.class).setParameter("name", name);
		try {
			ev = (Shop) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
		return (Shop) ev;	
	}
	

}
