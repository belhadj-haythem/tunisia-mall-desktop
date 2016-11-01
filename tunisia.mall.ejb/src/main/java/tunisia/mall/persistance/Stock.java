package tunisia.mall.persistance;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Stock
 *
 */
@Entity

public class Stock implements Serializable {

	
	private int id;
	private String size;
	private int quantity;
	private Item item;
	private static final long serialVersionUID = 1L;

	public Stock() {
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
	public String getSize() {
		return this.size;
	}

	public void setSize(String size) {
		this.size = size;
	}   
	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@ManyToOne
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
   
}
