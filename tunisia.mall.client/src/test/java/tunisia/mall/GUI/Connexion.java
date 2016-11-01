package tunisia.mall.GUI;

import javafx.application.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.event.*;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.AccessController;
import java.security.Permission;
import java.security.Principal;
import java.security.PrivilegedAction;
import java.util.Iterator;
import java.util.List;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.controlsfx.dialog.*;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import tunisia.mall.businessDelegate.EventServiceDelegate;
import tunisia.mall.businessDelegate.ShopServiceDelegate;
import tunisia.mall.businessDelegate.UserServiceDelegate;
import tunisia.mall.persistance.Administrator;
import tunisia.mall.persistance.Customer;
import tunisia.mall.persistance.Event;
import tunisia.mall.persistance.Shop;
import tunisia.mall.persistance.User;
import tunisia.mall.security.TunisiaMallCallbackHandler;
import tunisia.mall.security.TunisiaMallDriver;
import tunisia.mall.security.TunisiaMallPrincipal;
import tunisia.mall.security.myPermission;
import tunisia.mall.security.TunisiaMallDriver.Action;
import javafx.stage.FileChooser;

@SuppressWarnings("restriction")
public class Connexion extends Application {
	public Scene scene;
	public boolean test = false;
	public static DashbordGhacha aaa = new DashbordGhacha();
	public static User userCourant;
	public static Shop shopKriaa;
	// public static String log="admin";
	// public static String pass="admin";
	public static TextField login;
	public static PasswordField password;
	Stage window;

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		GridPane p = new GridPane();
		window.initStyle(StageStyle.TRANSPARENT);
		p.setVgap(10); // écart entre les lignes
		p.setHgap(20);

		Hyperlink hl = new Hyperlink("Forgot your password ? ");
		hl.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				RecupererPwd rp = new RecupererPwd();
				try {
					rp.start(new Stage());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		login = new TextField();
		password = new PasswordField();
		login.setPromptText("Enter your username");
		password.setPromptText("Enter your password");

		Button cnx = new Button("Connexion");
		Button exit = new Button("x");
		login.setMaxSize(200, 100);

		exit.setStyle("-fx-background-color: linear-gradient(#ffd65b, #e68400)," + "linear-gradient(#ffef84, #f2ba44),"
				+ "linear-gradient(#ffea6a, #efaa22),linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),"
				+ "linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9)," + " rgba(255,255,255,0));"
				+ "-fx-background-radius: 30;"
				// + "-fx-background-insets: 0,1,2,3,0;"
				+ "-fx-text-fill: #307D7E;-fx-font-weight: bold;" + "-fx-font-size: 10px;-fx-padding: 5 10 5 10;");

		cnx.setStyle("-fx-background-color: linear-gradient(#ffd65b, #e68400)," + "linear-gradient(#ffef84, #f2ba44),"
				+ "linear-gradient(#ffea6a, #efaa22),linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),"
				+ "linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9)," + " rgba(255,255,255,0));"
				+ "-fx-background-radius: 30;" + "-fx-background-insets: 0,1,2,3,0;"
				+ "-fx-text-fill: #307D7E;-fx-font-weight: bold;" + "-fx-font-size: 14px;-fx-padding: 10 20 10 20;");

		GridPane.setConstraints(login, 10, 17);
		login.setTranslateX(5);
		GridPane.setConstraints(password, 10, 18);
		password.setTranslateX(5);
		GridPane.setConstraints(hl, 10, 19);
		hl.setTranslateX(5);
		hl.setStyle("-fx-text-fill : #FFFFFF");
		GridPane.setConstraints(cnx, 10, 20);
		cnx.setTranslateX(45);
		GridPane.setConstraints(exit, 24, 0);
		exit.setTranslateX(-10);
		exit.setTranslateY(-30);

		cnx.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				// Alerte2.alerte("aaaaaaaaaaaaaaaaaaa");
				//DashbordGhacha a = new DashbordGhacha();
				Dashbord a = new Dashbord();
				DashbordShopOwner aa = new DashbordShopOwner();
				try {

					User user = driver();

					if (user instanceof Administrator) {
						userCourant = UserServiceDelegate.rechercherUserByUsername(login.getText());
						Confirm.Confirmer(login.getText() + " is Authenticated, Welcome !");
						notification();
						a.start(new Stage());
						//aaa = a;

						window.hide();
					} else if (user instanceof Customer) {

						userCourant = UserServiceDelegate.rechercherUserByUsername(login.getText());

					} else if (user instanceof Shop) {
						if (test) {
							userCourant = UserServiceDelegate.rechercherUserByUsername(login.getText());
							Confirm.Confirmer(login.getText() + " is Authenticated, Welcome !");
							shopKriaa = ShopServiceDelegate.findShopById(userCourant.getId_account());

							aa.start(new Stage());
							window.hide();
						}

					} else if (user == null) {
						Alerte.alerte("Please check your username or password");
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		exit.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				System.exit(0);

			}
		});

		// -------------------Sphere animation----------------------

		PerspectiveCamera camera = new PerspectiveCamera(true);
		camera.setTranslateZ(-1000);
		camera.setNearClip(0.1);
		camera.setFarClip(2000.0);
		camera.setFieldOfView(40);

		Sphere sphere = new Sphere(60);
		sphere.setTranslateX(350);
		sphere.setTranslateY(59);
		sphere.setTranslateZ(100);

		Image earthImage = new Image("file:///C:/workspace/tunisia.mall.client/Images/aaaaa.jpg");
		PhongMaterial earthPhong = new PhongMaterial();
		earthPhong.setDiffuseMap(earthImage);
		sphere.setMaterial(earthPhong);

		RotateTransition rt4 = new RotateTransition();
		rt4.setNode(sphere);
		rt4.setDuration(Duration.millis(10000));
		rt4.setAxis(Rotate.Y_AXIS);
		rt4.setByAngle(360);
		rt4.setCycleCount(Animation.INDEFINITE);
		rt4.setInterpolator(Interpolator.LINEAR);
		rt4.play();

		// -----------------------------------------
		login.setStyle("-fx-background-color: rgba(53,89,119,0.4);-fx-text-fill : #FFFFFF;");
		login.setMinWidth(190);
		password.setStyle("-fx-background-color: rgba(53,89,119,0.4);-fx-text-fill : #FFFFFF;");
		BackgroundSize bgS = new BackgroundSize(820, 618, false, false, false, false);
		Background bg = new Background(
				new BackgroundImage(new Image("file:///C:/workspace/tunisia.mall.client/Images/image.jpg"), NO_REPEAT,
						NO_REPEAT, null, bgS));
		p.setBackground(bg);
		p.getChildren().addAll(login, password, cnx, exit, sphere, hl);
		scene = new Scene(p, 820, 620);
		// scene.setCamera(camera);
		window.setScene(scene);
		window.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void notification() {
		Notification a = new Notification();
		Platform.setImplicitExit(false);

		List<Event> listev = EventServiceDelegate.ListEvent();

		for (Event ev : listev) {
			if (!ev.isStatutEv()) {

				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						try {
							a.start(new Stage());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			}
		}
	}

	public User driver() {
		TunisiaMallDriver tmDriver = new TunisiaMallDriver();
		System.setProperty("java.security.auth.login.config", "jaas.configFile"); // ou bien dans VM args
																			// -Djava.security.auth.login.config==jaas.configFile
																					// deja fait :
																			// -Djava.security.policy==jaas.policy

		LoginContext loginContext = null;
		boolean flag = false;
		try {
			loginContext = new LoginContext("TMJaas",
					new TunisiaMallCallbackHandler(login.getText(), password.getText()));
			loginContext.login();
			Subject subject = loginContext.getSubject();
			Iterator it = subject.getPrincipals().iterator();
			Principal p = (Principal) it.next();
			String ch = p.getName();
			System.out.println(ch);
			User us = UserServiceDelegate.rechercherUserByUsername(ch);

			test = true;
			/*
			 * PrivilegedAction action = new PrivilegedAction() {
			 * 
			 * @Override public Object run() { Permission perm = new
			 * myPermission("test"); try {
			 * AccessController.checkPermission(perm); System.out.println(
			 * "granted !!!"); test = true;
			 * 
			 * return null; } catch (Exception e) { System.out.println(
			 * "not granted !!!"); Alerte.alerte("You are not authorized !"); }
			 * 
			 * return null; } }; Subject.doAsPrivileged(subject, action, null);
			 */
			// try {
			// flag = performedAction(loginContext);
			// System.out.println("waiting performed action");
			// } catch (IOException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			flag = true;
			return us;

		} catch (LoginException e) {
			System.out.println("Log Fail.......");
			// e.printStackTrace();
		}
		return null;
	}

	boolean performedAction(LoginContext loginContext) throws IOException, LoginException {
		boolean flag = true;
		// System.out.println("Choise .........???????");
		// BufferedReader br = new BufferedReader(new
		// InputStreamReader(System.in));

		try {
			switch (Dashbord.valueExit) {
			case "logout":
				loginContext.logout();
				System.out.println("logout success ......");
				flag = false;
				break;
			case "a":
				break;

			case "b":
				break;
			}

		} catch (IllegalArgumentException e) {
			System.out.println("rechoisir........");
		}
		return flag;
	}

}
