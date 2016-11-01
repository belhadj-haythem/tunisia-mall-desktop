package tunisia.mall.security;

import java.security.PrivilegedAction;
import java.security.Permission;
import java.io.*;
  
public class myPermission extends Permission {

	public myPermission(String name) {
		super(name);
	}
	
	@Override
	public boolean implies(Permission permission) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getActions() {
		// TODO Auto-generated method stub
		return null;
	}
   
}