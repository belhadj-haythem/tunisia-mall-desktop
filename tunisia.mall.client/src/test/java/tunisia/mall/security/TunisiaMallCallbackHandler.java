package tunisia.mall.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

import tunisia.mall.GUI.Connexion;

public class TunisiaMallCallbackHandler implements CallbackHandler {

	private String username = null;
	private String password = null;
	
	
	
	public TunisiaMallCallbackHandler(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}



	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		
		NameCallback nameCallback = (NameCallback) callbacks[0];
		//System.out.println(nameCallback.getPrompt());
		nameCallback.setName(username);
		PasswordCallback passwordCallback = (PasswordCallback) callbacks[1];
		//System.out.println(passwordCallback.getPrompt());
		passwordCallback.setPassword(password.toCharArray());
	}

}
