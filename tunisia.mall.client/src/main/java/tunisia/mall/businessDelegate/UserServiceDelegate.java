package tunisia.mall.businessDelegate;

import java.util.List;

import tunisia.mall.interfaces.UserServicesRemote;
import tunisia.mall.persistance.Administrator;
import tunisia.mall.persistance.Customer;
import tunisia.mall.persistance.Item;
import tunisia.mall.persistance.Shop;
import tunisia.mall.persistance.User;
import tunisia.mall.serviceLocator.ServiceLocator;

public class UserServiceDelegate {

	private static String jndiName = "tunisia.mall.ejb/UserServices!tunisia.mall.interfaces.UserServicesRemote";

	public static UserServicesRemote getProxy() {
		return (UserServicesRemote) ServiceLocator.getInstance().getRemoteProxy(jndiName);
	}

	public static boolean addAdmin(Administrator admin) {
		return getProxy().addAdmin(admin);
	}

	public static boolean addCustomer(Customer customer) {
		return getProxy().addCustomer(customer);
	}



	public static boolean updateAdmin(Administrator admin) {
		return getProxy().updateAdmin(admin);
	}

	public static boolean updateCustomer(Customer customer) {
		return getProxy().updateCustomer(customer);
	}
	
	public static boolean updateUser(User user) {
		return getProxy().updateUser(user);
	}

	public static boolean deleteAdmin(Administrator admin) {
		return getProxy().deleteAdmin(admin);
	}

	public static boolean deleteCustomer(Customer customer) {
		return getProxy().deleteCustomer(customer);
	}

	public static Customer findCustomer(int id) {
		return getProxy().findCustomer(id);
	}

	public static Administrator findAdmin(int id) {
		return getProxy().findAdmin(id);
	}

	public static List<Customer> listCustomer() {
		return getProxy().listCustomer();
	}

	public static List<Administrator> listAdmin() {
		return getProxy().listAdmin();
	}
	
	public static User rechercherUserByUsername(String username){
		return getProxy().rechercherUserByUsername(username);
	}

	public static User rechercherCustomerByUsername(String username){
		return getProxy().rechercherCustomerByUsername(username);
	}
	
	public static boolean chercherUserByUsername(String username){
		return getProxy().chercherUserByUsername(username);
	}
	
	public static boolean chercherCustomerByUsername(String username){
		return getProxy().chercherCustomerByUsername(username);
	}
	
	public static User authentification(String username, String password){
		return getProxy().authentification(username, password);
	}
	
	public static List<Customer> findCustomerByName(String name){
		return getProxy().findCustomerByName(name);
	}
	public static List<Item> listItemByShop(int id){
		return getProxy().listItemByShop(id);
	}
	public static List<Shop> listShop(){
		return getProxy().listShop();
	}
	public static List<Item> listItem(){
		return getProxy().listItem();
	}
	public static User recuppererMotDePasse(String emailUsername){
		return getProxy().recuppererMotDePasse(emailUsername);
	}
	public static List<User> findByAll(String rec){
		return getProxy().findByAll(rec);
	}
	public static boolean deleteUser(User user){
		return getProxy().deleteUser(user);
	}
	
	public static List<User> listUser(){
		return getProxy().listUser(); 
	}

}
