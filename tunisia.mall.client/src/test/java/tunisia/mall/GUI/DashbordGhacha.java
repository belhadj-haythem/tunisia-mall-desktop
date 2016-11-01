package tunisia.mall.GUI;

import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

import org.rapidpm.demo.javafx.dialog.confirm.Dialog;

import javafx.application.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

@SuppressWarnings("restriction")
public class DashbordGhacha extends Application {
	public static String rechercheChams;
	Stage window;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Label aaa = new Label("Shop list :");
		
		// aaa.setVisible(false);
		window = primaryStage;
		window.initStyle(StageStyle.TRANSPARENT);
		BorderPane p = new BorderPane();
		System.out.println(Notification.aaa);
		if(Notification.aaa==1){
			
			AdminCheckListRequests rm = new AdminCheckListRequests();
			//addEv.setVisible(true);
			//addnews.setVisible(false);
			p.setCenter(rm.start());
			p.getCenter().setTranslateY(-50);
			p.getCenter().setTranslateX(30);
		}
		BackgroundSize bgS = new BackgroundSize(1355, 660, false, false, false, false);
		Background bg = new Background(
				new BackgroundImage(new Image("file:///C:/workspace/tunisia.mall.client/Images/admin.png"), NO_REPEAT,
						NO_REPEAT, null, bgS));
		p.setBackground(bg);
		Button dec = new Button("L");
		Button addnews = new Button("+");
		Button addEv = new Button("+");
		addEv.setVisible(false);
		// addnews.setVisible(false);

		Button mrequest = new Button("Manage Events Requests");
		Button muser = new Button("Manage Customers");
		Button mshop = new Button("Manage Shops");
		Button mcat = new Button("Manage Categories");
		Button mevent = new Button("Manage Events");
		Button mnews = new Button("Manage News");
		Button mshopr = new Button("Manage shop request");
		mevent.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				AdminManageEvents rm = new AdminManageEvents();

				try {
					addEv.setVisible(true);
					addnews.setVisible(false);
					p.setCenter(rm.start());
					p.getCenter().setTranslateY(-50);
					p.getCenter().setTranslateX(30);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		mrequest.setStyle(
				"-fx-background-color: #3A3A3A,radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));-fx-background-radius: 5,4,3,5;-fx-background-insets: 0,1,2,0;-fx-text-fill: blod;-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );-fx-font-family: Times New Roman;-fx-text-fill: linear-gradient(white, #d0d0d0);-fx-font-size: 12px;-fx-padding: 10 20 10 20;");
		addEv.setStyle(
				"-fx-text-fill: white; -fx-font-weight: bold;-fx-background-color: linear-gradient(#990012,#990012); -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 )");
		addnews.setStyle(
				"-fx-text-fill: white; -fx-font-weight: bold;-fx-background-color: linear-gradient(#990012,#990012); -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 )");
		muser.setStyle(
				"-fx-background-color: #3A3A3A,radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));-fx-background-radius: 5,4,3,5;-fx-background-insets: 0,1,2,0;-fx-text-fill: blod;-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );-fx-font-family: Times New Roman;-fx-text-fill: linear-gradient(white, #d0d0d0);-fx-font-size: 12px;-fx-padding: 10 20 10 20;");
		mcat.setStyle(
				"-fx-background-color: #3A3A3A,radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));-fx-background-radius: 5,4,3,5;-fx-background-insets: 0,1,2,0;-fx-text-fill: blod;-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );-fx-font-family: Times New Roman;-fx-text-fill: linear-gradient(white, #d0d0d0);-fx-font-size: 12px;-fx-padding: 10 20 10 20;");
		mshop.setStyle(
				"-fx-background-color: #3A3A3A,radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));-fx-background-radius: 5,4,3,5;-fx-background-insets: 0,1,2,0;-fx-text-fill: blod;-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );-fx-font-family: Times New Roman;-fx-text-fill: linear-gradient(white, #d0d0d0);-fx-font-size: 12px;-fx-padding: 10 20 10 20;");
		mevent.setStyle(
				"-fx-background-color: #3A3A3A,radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));-fx-background-radius: 5,4,3,5;-fx-background-insets: 0,1,2,0;-fx-text-fill: blod;-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );-fx-font-family: Times New Roman;-fx-text-fill: linear-gradient(white, #d0d0d0);-fx-font-size: 12px;-fx-padding: 10 20 10 20;");
		mnews.setStyle(
				"-fx-background-color: #3A3A3A,radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));-fx-background-radius: 5,4,3,5;-fx-background-insets: 0,1,2,0;-fx-text-fill: blod;-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );-fx-font-family: Times New Roman;-fx-text-fill: linear-gradient(white, #d0d0d0);-fx-font-size: 12px;-fx-padding: 10 20 10 20;");
		mshopr.setStyle(
				"-fx-background-color: #3A3A3A,radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));-fx-background-radius: 5,4,3,5;-fx-background-insets: 0,1,2,0;-fx-text-fill: blod;-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );-fx-font-family: Times New Roman;-fx-text-fill: linear-gradient(white, #d0d0d0);-fx-font-size: 12px;-fx-padding: 10 20 10 20;");

		addEv.setTranslateX(240);
		addEv.setTranslateY(-240);
		// addnews.setVisible(false);

		addnews.setTranslateX(240);
		addnews.setTranslateY(-240);
		addnews.setVisible(false);

		
		
		mrequest.setPrefSize(180, 30);
		mrequest.setTranslateX(10);
		mrequest.setTranslateY(-37);
		muser.setPrefSize(180, 30);
		muser.setTranslateX(10);
		muser.setTranslateY(-30);
		mshop.setPrefSize(180, 30);
		mshop.setTranslateX(10);
		mshop.setTranslateY(-22);
		mcat.setPrefSize(180, 30);
		mcat.setTranslateX(10);
		mcat.setTranslateY(-15);
		mevent.setPrefSize(180, 30);
		mevent.setTranslateX(10);
		mevent.setTranslateY(-8);
		mnews.setPrefSize(180, 30);
		mnews.setTranslateX(10);
		mnews.setTranslateY(0);
		mshopr.setPrefSize(180, 30);
		mshopr.setTranslateX(10);
		mshopr.setTranslateY(7);

		/*
		 * 
		 * ImageView imageCaptcha = new ImageView(new
		 * Image("file:///C:/charityland/captcha_refresh.jpg")); Button
		 * generateCaptcha = new Button("Voir Captcha", imageCaptcha);
		 */

		mnews.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				RemoveNews rm = new RemoveNews();
				try {
					addEv.setVisible(false);
					addnews.setVisible(true);
					p.setCenter(rm.start());
					p.getCenter().setTranslateY(-50);
					p.getCenter().setTranslateX(30);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		addEv.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				AdminAddEvent rm = new AdminAddEvent();
				addnews.setVisible(false);
				try {
					p.setCenter(rm.start());
					p.getCenter().setTranslateY(-50);
					p.getCenter().setTranslateX(30);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		addnews.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				addnews.setVisible(false);
				NewsAjout rm = new NewsAjout();
				p.setCenter(rm.start());
				p.getCenter().setTranslateY(-50);
				p.getCenter().setTranslateX(30);

			}
		});

		muser.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				addnews.setVisible(false);
				// MapTunisiaMall m = new MapTunisiaMall();
				AfficherCustomer af = new AfficherCustomer();
				try {
					p.setCenter(af.start());
					p.getCenter().setTranslateY(40);
					p.getCenter().setTranslateX(130);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		mshop.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				addnews.setVisible(false);
				AfficherShop a = new AfficherShop();
				try {

					// p.getChildren().add(sh);

					// aaa.setStyle("-fx-font-size: 20px");
					// aaa.setTranslateX(400);
					// aaa.setTranslateY(-150);
					// aaa.setVisible(true);
					p.setCenter(a.start1());
					p.getCenter().setTranslateY(150);
					p.getCenter().setTranslateX(200);
					// p.getCenter()
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		mshopr.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				addnews.setVisible(false);
				AddShop a = new AddShop();
				try {

					p.setCenter(a.start11());
					p.getCenter().setTranslateY(45);
					p.getCenter().setTranslateX(30);
					// p.getCenter()
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		mrequest.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				addnews.setVisible(false);
				addEv.setVisible(false);
				AdminCheckListRequests a = new AdminCheckListRequests();
				try {

					p.setCenter(a.start());
					p.getCenter().setTranslateY(-50);
					p.getCenter().setTranslateX(30);
					// p.getCenter()
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		
		
		// TextField a = new TextField("aaa");
		dec.setStyle(
				"-fx-background-color: #090a0c,linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),linear-gradient(#20262b, #191d22),radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));-fx-background-radius: 5,4,3,5;-fx-background-insets: 0,1,2,0;-fx-text-fill: white;-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );-fx-font-family: Arial;-fx-text-fill: linear-gradient(white, #d0d0d0);-fx-font-size: 12px;-fx-padding: 10 20 10 20;");
		dec.setTranslateX(1292);
		dec.setTranslateY(-53);
		dec.setPrefSize(45, 45);

		dec.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				Connexion c = new Connexion();
				try {
					c.start(new Stage());
					window.hide();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		VBox x = new VBox();
		x.getChildren().addAll(dec, muser, mshop, mcat, mevent, mnews, mshopr, addnews, addEv,mrequest);
		p.setLeft(x);
		Main mm = new Main();
		//p.setTop(mm.start());
		//p.getTop().setTranslateX(830);
		// p.getTop().setTranslateY(0);
		// p.setCenter(mm.start());
		Scene scene = new Scene(p, 1360, 665);
		window.setScene(scene);
		window.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
