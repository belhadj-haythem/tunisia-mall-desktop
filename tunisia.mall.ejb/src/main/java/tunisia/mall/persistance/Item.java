package tunisia.mall.persistance;

import java.io.Serializable;
import java.lang.String;
import java.util.Arrays;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Items
 *
 */
@Entity

public class Item implements Serializable {

	   
	
	private int id;
	private String name;
	private float price;
	private List<Stock> stock;
	private String Color;
	private boolean disponibility;
	private String description;
	private String reference;
	private byte[] photo;
	private SubCategorie subCategorie;
	private Shop shop;
	private static final long serialVersionUID = 1L;
	private List<Cart> carts;
	private float discount;
	private Event event;

	public Item() {
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
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}  

	public String getColor() {
		return Color;
	}
	public void setColor(String color) {
		Color = color;
	}
	@OneToMany(mappedBy="item")
	public List<Stock> getStock() {
		return this.stock;
	}

	public void setStock(List<Stock> stock) {
		this.stock = stock;
	}   
	public boolean getDisponibility() {
		return this.disponibility;
	}

	public void setDisponibility(boolean disponibility) {
		this.disponibility = disponibility;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}   
	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}   
	@Lob
	@Basic(fetch=FetchType.LAZY)
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	  
	@Basic(fetch=FetchType.LAZY)
	@OneToMany(mappedBy="items")
	public List<Cart> getCarts() {
		return carts;
	}
	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}
	@ManyToOne
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	@ManyToOne
	public SubCategorie getSubCategorie() {
		return subCategorie;
	}
	public void setSubCategorie(SubCategorie subCategorie) {
		this.subCategorie = subCategorie;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", price=" + price +  ", disponibility="
				+ disponibility + ", description=" + description + ", reference=" + reference 
				 + ", subCategorie=" + subCategorie  ;
				
	}
	
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	@ManyToOne
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	
   
}
