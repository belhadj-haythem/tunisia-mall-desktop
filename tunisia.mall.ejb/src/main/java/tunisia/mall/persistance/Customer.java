package tunisia.mall.persistance;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Entity implementation class for Entity: Customer
 *
 */
@Entity

public class Customer extends User implements Serializable {

	private String fisrt_name;
	private String last_name;
	private long phone;
	private String adress;
	private long card_number;
	private int id_card;
	private static final long serialVersionUID = 1L;
	private List<Cart> carts;

	public Customer() {
		super();
	}

	public String getFisrt_name() {
		return this.fisrt_name;
	}

	public void setFisrt_name(String fisrt_name) {
		this.fisrt_name = fisrt_name;
	}

	public String getLast_name() {
		return this.last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public long getPhone() {
		return this.phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getAdress() {
		return this.adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public long getCard_number() {
		return this.card_number;
	}

	public void setCard_number(long card_number) {
		this.card_number = card_number;
	}

	public int getId_card() {
		return this.id_card;
	}

	public void setId_card(int id_card) {
		this.id_card = id_card;
	}

	@OneToMany(mappedBy="customer")
	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

}
