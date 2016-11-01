package tunisia.mall.businessDelegate;

import java.util.List;

import tunisia.mall.interfaces.EventServicesRemote;
import tunisia.mall.persistance.Event;
import tunisia.mall.persistance.Item;
import tunisia.mall.persistance.Shop;
import tunisia.mall.persistance.User;
import tunisia.mall.serviceLocator.ServiceLocator;

public class EventServiceDelegate {

	private static String jndiName = "tunisia.mall.ejb/EventServices!tunisia.mall.interfaces.EventServicesRemote";

	public static EventServicesRemote getProxy() {
		return (EventServicesRemote) ServiceLocator.getInstance().getRemoteProxy(jndiName);
	}
	
	
	
	public static boolean addEvent(Event e){
		return getProxy().addEvent(e);
	}
	public static boolean updateEvent(Event e){
		return getProxy().updateEvent(e);
	}
	public static boolean deleteEvent(Event e){
		return getProxy().deleteEvent(e);
	}
	public static Event findEvent(int id){
		return getProxy().findEvent(id);
	}
	public static List<Event> ListEvent(){
		return getProxy().ListEvent();
	}
	public static List<Event> searchListEventById(int id){
		return getProxy().searchListEventById(id);
	}
	public static Shop searchUserByid(int id){
		return getProxy().searchUserByid(id);
	}
	public static List<Item> listItems(int id) {
		return getProxy().listItems(id);
	}
	public static boolean updateItem(Item e){
		return getProxy().updateItem(e);
	}
	public static int addEvent2(Event e){
		return getProxy().addEvent2(e);
	}
	public static List<Item> listItemsByIdEvent(Event event){
		return getProxy().listItemsByIdEvent(event);
	}
	public static Shop searchShopByName(String name){
		return getProxy().searchShopByName(name);
	}

}
