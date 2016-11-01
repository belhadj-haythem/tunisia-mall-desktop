package tunisia.mall.persistance;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Vendor
 *
 */

@Entity

public class Vendor implements Serializable {

	
	private int id;
	private String first_name;
	private String last_name;
	private int idCard;
	private float salary;
	private Date dateEmbauche;
	private Shop shop;
	private static final long serialVersionUID = 1L;
  
	public Vendor(Date dateEmbauche, String first_name, String last_name, int idCard, float salary) {
		super();
		this.dateEmbauche= dateEmbauche;
		this.first_name = first_name;
		this.last_name = last_name;
		this.idCard = idCard;
		this.salary = salary;
	}
	public Vendor() {
	
	}

	public String getFirst_name() {
		return this.first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}   
	public String getLast_name() {
		return this.last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}   
	public int getIdCard() {
		return this.idCard;
	}

	public void setIdCard(int idCard) {
		this.idCard = idCard;
	}   
	public float getSalary() {
		return this.salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	} 
	@Temporal(TemporalType.DATE)
	public Date getDateEmbauche() {
		return this.dateEmbauche;
	}

	public void setDateEmbauche(Date dateEmbauche) {
		this.dateEmbauche = dateEmbauche;
	}   
	@Id
	@GeneratedValue
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@ManyToOne
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	
	
   
}
