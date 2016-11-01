package tunisia.mall.persistance;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: ProductCategorie
 *
 */
@Entity

public class ProductCategorie implements Serializable {

	   
	
	private int id;
	private String name;
	private List<Shop> shops;
	private List<SubCategorie> subCategories;
	private static final long serialVersionUID = 1L;

	public ProductCategorie() {
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
	@OneToMany(mappedBy="categorie")
	public List<Shop> getShops() {
		return shops;
	}
	public void setShops(List<Shop> shops) {
		this.shops = shops;
	}
	@OneToMany(mappedBy="categorie")
	public List<SubCategorie> getSubCategories() {
		return subCategories;
	}
	public void setSubCategories(List<SubCategorie> subCategories) {
		this.subCategories = subCategories;
	}
   
}
