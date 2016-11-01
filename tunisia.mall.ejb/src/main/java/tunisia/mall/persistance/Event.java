package tunisia.mall.persistance;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Event
 *
 */
@Entity

public class Event implements Serializable {

	   
	
	private int id;
	private String title;
	private Date dateDebut;
	private Date dateFin;
	private String subject;
	private String place;
	private String picture;
	private boolean statutEv ;
	private Shop shopOwner;
	private static final long serialVersionUID = 1L;
	private List<Item> items;
	

	public Event() {
		super();
	} 
	@Id
	@GeneratedValue
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public boolean isStatutEv() {
		return statutEv;
	}
	public void setStatutEv(boolean statutEv) {
		this.statutEv = statutEv;
	}
	
	@ManyToOne
	public Shop getShopOwner() {
		return shopOwner;
	}
	public void setShopOwner(Shop shopOwner) {
		this.shopOwner = shopOwner;
	}
	
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	} 
	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}   
	
	@Temporal(TemporalType.DATE)
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}@Temporal(TemporalType.DATE)
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	@OneToMany(mappedBy="event")
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	
	
   
}
