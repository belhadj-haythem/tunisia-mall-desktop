package tunisia.mall.persistance;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;
import tunisia.mall.persistance.User;

/**
 * Entity implementation class for Entity: Administrator
 *
 */
@Entity

public class Administrator extends User implements Serializable {

	
	private String name_admin;
	private static final long serialVersionUID = 1L;

	public Administrator() {
		super();
	}   
	public String getName_admin() {
		return this.name_admin;
	}

	public void setName_admin(String name_admin) {
		this.name_admin = name_admin;
	}
   
}
