package tunisia.mall.persistance;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: SubCategorie
 *
 */
@Entity

public class SubCategorie implements Serializable {

	
	private int id;
	private String name;
	private ProductCategorie categorie;
	private Characteristics characteristics;
	private static final long serialVersionUID = 1L;

	public SubCategorie() {
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

	@ManyToOne
	public ProductCategorie getCategorie() {
		return categorie;
	}
	public void setCategorie(ProductCategorie categorie) {
		this.categorie = categorie;
	}
	@ManyToOne
	public Characteristics getCharacteristics() {
		return characteristics;
	}
	public void setCharacteristics(Characteristics characteristics) {
		this.characteristics = characteristics;
	}
   
}
