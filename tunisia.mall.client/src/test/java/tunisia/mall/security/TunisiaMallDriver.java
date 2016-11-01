package tunisia.mall.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.AccessController;
import java.security.Permission;
import java.security.PrivilegedAction;
import java.security.Principal;
import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class TunisiaMallDriver {
	public enum Action {
		action1, action2, logout
	};

	public static void main(String[] args) {
		System.setProperty("java.security.auth.login.config", "jaas.configFile");
		LoginContext loginContext = null;
		boolean flag = false;

		try {
			loginContext = new LoginContext("TMJaas", new TunisiaMallCallbackHandler("aa","aa"));
			loginContext.login();
			flag = true;
			Subject subject = loginContext.getSubject();
			for (Principal p : subject.getPrincipals()){
				System.out.println(p.toString());
			}
//
			PrivilegedAction action = new PrivilegedAction() {

				@Override
				public Object run() {
					Permission perm = new myPermission("test");
					AccessController.checkPermission(perm);
					System.out.println("granted !!!");
					return null;
				}
			};
			Subject.doAsPrivileged(subject, action, null);
//
			loginContext.logout();
			// while (flag)
			// flag = tmDriver.performedAction(loginContext);
		} catch (LoginException e) {
			// System.out.println("Log Fail.......");
			e.printStackTrace();
		}
	}

	boolean performedAction(LoginContext loginContext) throws IOException, LoginException {
		boolean flag = true;
		System.out.println("Choise .........???????");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			switch (Action.valueOf(br.readLine())) {
			case logout:
				loginContext.logout();
				System.out.println("logout success ......");
				flag = false;
				break;
			case action1:
				break;

			case action2:
				break;
			}

		} catch (IllegalArgumentException e) {
			System.out.println("rechoisir........");
		}
		return flag;
	}

}
