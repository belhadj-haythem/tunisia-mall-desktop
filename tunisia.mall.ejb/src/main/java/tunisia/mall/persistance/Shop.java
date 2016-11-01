package tunisia.mall.persistance;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;
import tunisia.mall.persistance.User;

/**
 * Entity implementation class for Entity: Shop
 *
 */
@Entity

public class Shop extends User implements Serializable {

	private String description;
	private long phone;
	private String website;
	private String nameShop;
	private ProductCategorie categorie;
	private byte[] picture;
	private List<Vendor> vendors;
	private List<Item> items;
	private static final long serialVersionUID = 1L;

	public Shop() {
		super();
	}

	public long getPhone() {
		return this.phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getNameShop() {
		return this.nameShop;
	}

	public void setNameShop(String nameShop) {
		this.nameShop = nameShop;
	}

	public byte[] getPicture() {
		return this.picture;
	}

	@Lob
	@Basic(fetch = FetchType.LAZY)
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	@OneToMany(mappedBy = "shop")
	public List<Vendor> getVendors() {
		return vendors;
	}

	public void setVendors(List<Vendor> vendors) {
		this.vendors = vendors;
	}

	@ManyToOne
	public ProductCategorie getCategorie() {
		return categorie;
	}

	public void setCategorie(ProductCategorie categorie) {
		this.categorie = categorie;
	}

	@OneToMany(mappedBy = "shop")
	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
