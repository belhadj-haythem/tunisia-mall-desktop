package tunisia.mall.persistance;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Order
 *
 */
@Entity
@Table(name="t_order")
public class Order implements Serializable {

	
	private int id;
	private float totalCost;
	private boolean delivery;
	private Date date;
	private List<Cart> carts;
	private static final long serialVersionUID = 1L;
	private String adress;

	public Order() {
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
	public float getTotalCost() {
		return this.totalCost;
	}

	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}   
	public boolean getDelivery() {
		return this.delivery;
	}

	public void setDelivery(boolean delivery) {
		this.delivery = delivery;
	}
	@Temporal(TemporalType.DATE)
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@OneToMany(mappedBy="order")
	public List<Cart> getCarts() {
		return carts;
	}
	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
   
}
