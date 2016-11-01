package tunisia.mall.security;

import java.io.IOException;
import java.security.PrivilegedAction;
import java.util.Iterator;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import tunisia.mall.businessDelegate.UserServiceDelegate;
import tunisia.mall.persistance.User;

public class TunisiaMallLoginModule implements LoginModule {
	private Subject subject = null;
	private CallbackHandler callbackHandler = null;
	private TunisiaMallPrincipal tmPrincipal = null;

	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
			Map<String, ?> options) {
		this.subject = subject;
		this.callbackHandler = callbackHandler;
		System.out.println("initialize");

	}

	public boolean login() throws LoginException {
		boolean flag = false;
		System.out.println("login");
		Callback[] callbacksArray = new Callback[2];
		callbacksArray[0] = new NameCallback("Username: ");
		callbacksArray[1] = new PasswordCallback("Password: ", false);
		try {
			callbackHandler.handle(callbacksArray);
			String name = ((NameCallback) callbacksArray[0]).getName();
			String password = new String(((PasswordCallback) callbacksArray[1]).getPassword());
			User u = new User();
			u = UserServiceDelegate.authentification(name, password);
			if(u.getPassword().equals(password) && u.getUsername().equals(name)){
				tmPrincipal = new TunisiaMallPrincipal(name);
				System.out.println("log succeess......");
				flag = true;
			}
			if (flag == false)
				throw new FailedLoginException("log fail............");
		} catch (IOException | UnsupportedCallbackException e) {

			e.printStackTrace();
		}
		return flag;
	}

	public boolean commit() throws LoginException {
		boolean flag = false;
		System.out.println("commit");
		if (subject != null && !subject.getPrincipals().contains(tmPrincipal)) {
			subject.getPrincipals().add(tmPrincipal);
//			Iterator it = subject.getPrincipals().iterator();
//			while (it.hasNext()) {
//				 System.out.println(it.next());
//				 System.out.println("------------");
//				}
			flag = true;
		}
		return flag;
	}

	public boolean abort() throws LoginException {
		if (subject != null && tmPrincipal != null && subject.getPrincipals().contains(tmPrincipal))
			subject.getPrincipals().remove(tmPrincipal);
		
		subject = null;
		tmPrincipal = null;
		System.out.println("abort");
		return true;
	}

	public boolean logout() throws LoginException {
		subject.getPrincipals().remove(tmPrincipal);
		subject = null;
		System.out.println("logout");
		return true;
	}

	public Subject getSubject() {
		return subject;
	}

	public CallbackHandler getCallbackHandler() {
		return callbackHandler;
	}

	public TunisiaMallPrincipal getTmPrincipal() {
		return tmPrincipal;
	}
	
	

}
