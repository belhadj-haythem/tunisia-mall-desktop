package tunisia.mall.businessDelegate;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import tunisia.mall.interfaces.NewsServiceRemote;
import tunisia.mall.persistance.News;
import tunisia.mall.persistance.ProductCategorie;
import tunisia.mall.persistance.Shop;
import tunisia.mall.serviceLocator.ServiceLocator;

public class NewsServiceDelegate {
	
	private static String jndiName = "tunisia.mall.ejb/NewsService!tunisia.mall.interfaces.NewsServiceRemote";

	public static NewsServiceRemote getProxy() {
		return (NewsServiceRemote) ServiceLocator.getInstance().getRemoteProxy(jndiName);
	}
	
	public static  boolean addNews(News n ){
		return getProxy().addNews(n);
	}
	public static void updateNews(News n ){
		getProxy().updateNews(n);
	}
	public static News findNews(int id ){
		return getProxy().findNews(id);
		
	}
	public static void removeNews(News n ){
		getProxy().removeNews(n);
		
	}
	public static List<News> list(){
		return getProxy().list();
	}
	
	public static List<News> searchNewsByDate(Date d){
		return getProxy().searchNewsByDate(d);
	}
	public static News searchNewsByName(String title){
		return getProxy().searchNewsByName(title);
	}
	public static List<News> searchNewsByDateOrName(String title, Date date){
		return getProxy().searchNewsByDateOrName(title, date);
	}
	
 

}
