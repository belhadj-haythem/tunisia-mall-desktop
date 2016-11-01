package tunisia.mall.GUI;

import java.util.Date;

import static org.junit.Assert.*;

import java.lang.reflect.Proxy;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Test;

import tunisia.mall.businessDelegate.NewsServiceDelegate;
import tunisia.mall.interfaces.NewsServiceRemote;
import tunisia.mall.persistance.News;

public class NewsTest {

	public static void main(String[] args) throws NamingException {

		News n = new News();

		n.setTitle("add new shop");

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String dateInString = "7/12/2013";

		try {

			Date date = formatter.parse(dateInString);
			n.setDateDebut(date);

		} catch (ParseException ex) {
			ex.printStackTrace();
		}

		n.setSubject("fffffffyhfyhyfyhfff");
		NewsServiceDelegate.addNews(n);
		// n=proxy.findNews(1);
		// System.out.println(n);
		// proxy.removeNews(n);

		System.out.println(NewsServiceDelegate.list());
	}
	
	@Test
	public void testSearchTitle(){
		String title="celio";
		assertEquals(67,NewsServiceDelegate.searchNewsByName(title).getId());
	}
	

	@Test
	public void testSearchDate(){
	Date dated=new Date();
		assertEquals(2,NewsServiceDelegate.searchNewsByDate(dated).size());
	}
	
	
	

}
