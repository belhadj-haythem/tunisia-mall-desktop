package tunisia.mall.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import tunisia.mall.interfaces.UserServicesLocal;
import tunisia.mall.interfaces.UserServicesRemote;
import tunisia.mall.persistance.Administrator;
import tunisia.mall.persistance.Customer;
import tunisia.mall.persistance.Item;
import tunisia.mall.persistance.Shop;
import tunisia.mall.persistance.User;

/**
 * Session Bean implementation class UserServices
 */
@Stateless
@LocalBean
public class UserServices implements UserServicesRemote, UserServicesLocal {

	@PersistenceContext(name = "ejb-sample")
	EntityManager entityManager;

	public UserServices() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addAdmin(Administrator admin) {
		entityManager.persist(admin);
		return true;
	}

	@Override
	public boolean addCustomer(Customer customer) {
		entityManager.persist(customer);
		return true;
	}

	@Override
	public boolean updateAdmin(Administrator admin) {
		entityManager.merge(admin);
		return true;
	}

	@Override
	public boolean updateCustomer(Customer customer) {
		entityManager.merge(customer);
		return true;
	}

	@Override
	public boolean deleteAdmin(Administrator admin) {
		entityManager.remove(entityManager.merge(admin));
		return true;
	}

	@Override
	public boolean deleteCustomer(Customer customer) {
		entityManager.remove(entityManager.merge(customer));
		return true;
	}

	@Override
	public Customer findCustomer(int id) {
		return entityManager.find(Customer.class, id);
	}

	@Override
	public Administrator findAdmin(int id) {
		return entityManager.find(Administrator.class, id);
	}

	@Override
	public List<Customer> listCustomer() {

		return entityManager.createQuery("select c from Customer c").getResultList();
	}

	@Override
	public List<Administrator> listAdmin() {
		return entityManager.createQuery("select a from Administrator a").getResultList();
	}

	@Override
	public User rechercherUserByUsername(String username) {
		String requete="select a from User a where a.username=:u";
		return (User)entityManager.createQuery(requete,User.class).setParameter("u", username).getSingleResult();
	}

	@Override
	public Customer rechercherCustomerByUsername(String username) {
		String requete="select a from Customer a where a.username=:u";
		return (Customer)entityManager.createQuery(requete,User.class).setParameter("u", username).getSingleResult();
	}

	@Override
	public boolean chercherUserByUsername(String username) {
		String requete="select a from User a where a.username=:u";
		
		try {
			entityManager.createQuery(requete,User.class).setParameter("u", username).getSingleResult();
				return true;
			
		} catch (NoResultException e) {
			return false;
		}
	}

	@Override
	public boolean chercherCustomerByUsername(String username) {

		String requete="select a from Customer a where a.username=:u";
		
		try {
			entityManager.createQuery(requete,Customer.class).setParameter("u", username).getSingleResult();
				return true;
			
		} catch (NoResultException e) {
			return false;
		}

	}

	@Override
	public User authentification(String username, String password) {

		String requete="select a from User a where a.username=:u and a.password=:p"; 
		try {
				return entityManager.createQuery(requete,User.class).setParameter("u", username).setParameter("p", password).getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<Customer> findCustomerByName(String name) {
		return entityManager.createQuery("select a from Customer a where a.fisrt_name=:name").setParameter("name", name).getResultList();
	}

	@Override
	public List<Item> listItemByShop(int id) {
		
		String requete="select i from Item i join i.shop s where s.id_account=:id";
		
		return entityManager.createQuery(requete).setParameter("id", id).getResultList();
	}

	@Override
	public List<Shop> listShop() {
		String requete="select s from Shop s";
		return entityManager.createQuery(requete,Shop.class).getResultList();
	}

	@Override
	public List<Item> listItem() {
		return entityManager.createQuery("select i from Item i").getResultList();
	}

	@Override
	public User recuppererMotDePasse(String emailUsername) {
		String requete= "select u from User u where u.email=:email or u.username=:username";
		try{
			return entityManager.createQuery(requete,User.class)
					.setParameter("email", emailUsername)
					.setParameter("username", emailUsername)
					.getSingleResult();

		}catch(Exception e){
			return null;
		}
		
	}

	@Override
	public List<User> findByAll(String rec) {
		
		String requete= "select u from User u where u.email=:email or u.username=:username or u.fisrt_name=:fisrt_name "
				+ "or u.last_name=:last_name or u.nameShop=:nameShop";

		try{
			return entityManager.createQuery(requete,User.class)
					.setParameter("email", rec)
					.setParameter("username", rec)
					.setParameter("fisrt_name", rec)
					.setParameter("last_name", rec)
					.setParameter("nameShop", rec)
					.getResultList();

		}catch(Exception e){
			return null;
		}
	}

	@Override
	public boolean deleteUser(User user) {
		entityManager.remove(entityManager.merge(user));
		return true;
	}

	@Override
	public boolean updateUser(User user) {
		entityManager.merge(user);
		return true;
	}

	@Override
	public List<User> listUser() {
		return entityManager.createQuery("select a from User a").getResultList();
	}
	

}
