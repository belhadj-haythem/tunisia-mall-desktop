package tunisia.mall.interfaces;



import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import tunisia.mall.persistance.Event;
import tunisia.mall.persistance.News;

@Remote
public interface NewsServiceRemote {
	public boolean addNews(News n );
	public void updateNews(News n );
	public News findNews(int id );
	public void removeNews(News n );
	public List<News> list();
	public List<News> searchNewsByDate(Date d);
	public News searchNewsByName(String title);
	public List<News> searchNewsByDateOrName(String title,Date date);
}
