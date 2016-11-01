package tunisia.mall.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tunisia.mall.persistance.Administrator;
import tunisia.mall.persistance.Customer;
import tunisia.mall.persistance.Item;
import tunisia.mall.persistance.Shop;
import tunisia.mall.persistance.User;

@Remote
public interface UserServicesRemote {

	public boolean addAdmin(Administrator admin);

	public boolean addCustomer(Customer customer);

	public boolean updateAdmin(Administrator admin);

	public boolean updateCustomer(Customer customer);
	
	public boolean updateUser(User user);

	public boolean deleteAdmin(Administrator admin);

	public boolean deleteCustomer(Customer customer);
	
	public boolean deleteUser(User user);

	public Customer findCustomer(int id);

	public Administrator findAdmin(int id);

	public List<Customer> listCustomer();
	
	public List<User> listUser();

	public List<Administrator> listAdmin();

	public List<Shop> listShop();

	public User rechercherUserByUsername(String username);

	public Customer rechercherCustomerByUsername(String username);

	public List<Customer> findCustomerByName(String name);

	public List<Item> listItemByShop(int id);

	public List<Item> listItem();

	public boolean chercherUserByUsername(String username);

	public boolean chercherCustomerByUsername(String username);

	public User authentification(String username, String password);
	
	public User recuppererMotDePasse(String emailUsername);
	public List<User> findByAll(String rec);

}
