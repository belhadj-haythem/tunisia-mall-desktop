package tunisia.mall.security;

import java.io.Serializable;
import java.security.Principal;

public class TunisiaMallPrincipal implements Principal, Serializable {
	private static final long serialVersionUID = 1L;
	private final String name;

	
	public TunisiaMallPrincipal(String name) {
		this.name = name;
	}


	public String getName() {
		return name;
	}

	
	public boolean equals(Object obj) {
		boolean flag = false;
		if(obj instanceof TunisiaMallPrincipal) 
			flag = name.equals(((TunisiaMallPrincipal)obj).getName());
		return flag;
	}


	@Override
	public String toString() {
		return " [name= " + name + " ]";
	}
	
	
	

}
