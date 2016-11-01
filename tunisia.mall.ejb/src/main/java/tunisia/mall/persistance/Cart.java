package tunisia.mall.persistance;

import java.io.Serializable;


import javax.persistence.*;

/**
 * Entity implementation class for Entity: Cart
 *
 */
@Entity

//@IdClass(CartPK.class)

public class Cart implements Serializable {

	private CartPK cartPk;
	private Order order;
	   
	private static final long serialVersionUID = 1L;
	
	private Customer customer;
	private Item items;
	private int quantity;

	public Cart() {
		super();
	}   
	
	@EmbeddedId
	public CartPK getCartPk() {
		return cartPk;
	}


	public void setCartPk(CartPK cartPk) {
		this.cartPk = cartPk;
	}


	@ManyToOne
	@JoinColumn(name="idCustomer",referencedColumnName="id_account",insertable=false,updatable=false)
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@ManyToOne
	@JoinColumn(name="idItems",referencedColumnName="id",updatable=false,insertable=false)
	public Item getItems() {
		return items;
	}
	public void setItems(Item items) {
		this.items = items;
	}
	@ManyToOne(cascade=CascadeType.ALL)
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
   
}
