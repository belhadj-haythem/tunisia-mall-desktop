package tunisia.mall.persistance;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * ID class for entity: Cart
 *
 */ 
@Embeddable
public class CartPK  implements Serializable {   
   
	         
	private int idCustomer;         
	private int idItems;
	private static final long serialVersionUID = 1L;

	public CartPK() {}

	

	public CartPK(int idCustomer, int idItems) {
		super();
		this.idCustomer = idCustomer;
		this.idItems = idItems;
	}



	public int getIdCustomer() {
		return this.idCustomer;
	}

	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}
	

	public int getIdItems() {
		return this.idItems;
	}

	public void setIdItems(int idItems) {
		this.idItems = idItems;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCustomer;
		result = prime * result + idItems;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartPK other = (CartPK) obj;
		if (idCustomer != other.idCustomer)
			return false;
		if (idItems != other.idItems)
			return false;
		return true;
	}
	
   
   
   
}
