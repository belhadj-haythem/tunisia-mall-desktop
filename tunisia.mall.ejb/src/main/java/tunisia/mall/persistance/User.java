package tunisia.mall.persistance;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class User implements Serializable {

	   
	
	
	private int id_account;
	private String email;
	private String username;
	private String password;
	private boolean enabled;
	
	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}   
	@Id	
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId_account() {
		return this.id_account;
	}

	public void setId_account(int id_account) {
		this.id_account = id_account;
	}   
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}   
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}   
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}   
	public boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	} 
}
