package tunisia.mall.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tunisia.mall.interfaces.NewsServiceLocal;
import tunisia.mall.interfaces.NewsServiceRemote;
import tunisia.mall.persistance.News;

/**
 * Session Bean implementation class NewsService
 */
@Stateless
@LocalBean
public class NewsService implements NewsServiceRemote, NewsServiceLocal {

	/**
	 * Default constructor.
	 */
	@PersistenceContext(name = "ejb-sample")
	EntityManager em;

	public NewsService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addNews(News n) {
		em.persist(n);
		return true;
	}

	@Override
	public void updateNews(News n) {
		em.merge(n);
	}

	@Override
	public News findNews(int id) {
		return em.find(News.class, id);
	}

	@Override
	public void removeNews(News n) {
		em.remove(em.merge(n));
	}

	@Override
	public List<News> list() {
		return em.createQuery("select n from News n").getResultList();

	}

	public byte[] extractBytes(String ImageName) throws IOException {
		ByteArrayOutputStream baos = null;
		byte[] imageInByte = null;
		try {
			BufferedImage originalImage = ImageIO.read(new File(ImageName));
			baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, "jpg", baos);
			baos.flush();
			imageInByte = baos.toByteArray();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return imageInByte;

	}

	@Override
	public News searchNewsByName(String title) {
		// jpql
		String requete = "select i from News i where i.title=:r";
		News news = null;
		Query query = em.createQuery(requete).setParameter("r", title);
		news = (News) query.getSingleResult();
		return news;
	}

	@Override
	public List<News> searchNewsByDate(Date d) {
		// jpql
		String requete = "select i from News i where i.dateDebut=:d";
		List<News> maliste = new ArrayList<>();
		Query query = em.createQuery(requete).setParameter("d", d);
		maliste = query.getResultList();
		return maliste;
	}

	@Override
	public List<News> searchNewsByDateOrName(String title, Date date) {
		String requete = "select i from News i where i.title=:r OR i.dateDebut=:d";
		List<News> maliste = new ArrayList<>();
		Query query = em.createQuery(requete).setParameter("d", date).setParameter("r", title);
		maliste = query.getResultList();
		return maliste;
	}

}
