package tunisia.mall.GUI;

import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

import javafx.application.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;

@SuppressWarnings("restriction")
public class DashbordShopOwner extends Application {
	Stage window;
	static BorderPane p = new BorderPane();

	@Override
	public void start(Stage primaryStage) throws Exception {
		Label aaa = new Label("Shop list :");
		aaa.setVisible(false);
		window = primaryStage;
		window.initStyle(StageStyle.TRANSPARENT);

		BackgroundSize bgS = new BackgroundSize(1355, 660, false, false, false, false);
		Background bg = new Background(
				new BackgroundImage(new Image("file:///C:/workspace/tunisia.mall.client/Images/admin.png"), NO_REPEAT,
						NO_REPEAT, null, bgS));
		p.setBackground(bg);
		
		Button mEv = new Button("Manage Events");
		Button dec = new Button("L");
		Button mcatal = new Button("Manage Order");
		Button mvendor = new Button("Manage Vendors");
		Button mmag = new Button("View Mall Map");
		Button mprod = new Button("Manage Products");
		Button msub = new Button("Manage Sub-categories");
		// Button mshopr = new Button("Manage shop request");

		mEv.setStyle(
				"-fx-background-color: #3A3A3A,radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));-fx-background-radius: 5,4,3,5;-fx-background-insets: 0,1,2,0;-fx-text-fill: blod;-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );-fx-font-family: Times New Roman;-fx-text-fill: linear-gradient(white, #d0d0d0);-fx-font-size: 12px;-fx-padding: 10 20 10 20;");
		
		mcatal.setStyle(
				"-fx-background-color: #3A3A3A,radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));-fx-background-radius: 5,4,3,5;-fx-background-insets: 0,1,2,0;-fx-text-fill: blod;-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );-fx-font-family: Times New Roman;-fx-text-fill: linear-gradient(white, #d0d0d0);-fx-font-size: 12px;-fx-padding: 10 20 10 20;");
		mmag.setStyle(
				"-fx-background-color: #3A3A3A,radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));-fx-background-radius: 5,4,3,5;-fx-background-insets: 0,1,2,0;-fx-text-fill: blod;-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );-fx-font-family: Times New Roman;-fx-text-fill: linear-gradient(white, #d0d0d0);-fx-font-size: 12px;-fx-padding: 10 20 10 20;");
		mvendor.setStyle(
				"-fx-background-color: #3A3A3A,radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));-fx-background-radius: 5,4,3,5;-fx-background-insets: 0,1,2,0;-fx-text-fill: blod;-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );-fx-font-family: Times New Roman;-fx-text-fill: linear-gradient(white, #d0d0d0);-fx-font-size: 12px;-fx-padding: 10 20 10 20;");
		mprod.setStyle(
				"-fx-background-color: #3A3A3A,radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));-fx-background-radius: 5,4,3,5;-fx-background-insets: 0,1,2,0;-fx-text-fill: blod;-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );-fx-font-family: Times New Roman;-fx-text-fill: linear-gradient(white, #d0d0d0);-fx-font-size: 12px;-fx-padding: 10 20 10 20;");
		msub.setStyle(
				"-fx-background-color: #3A3A3A,radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));-fx-background-radius: 5,4,3,5;-fx-background-insets: 0,1,2,0;-fx-text-fill: blod;-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );-fx-font-family: Times New Roman;-fx-text-fill: linear-gradient(white, #d0d0d0);-fx-font-size: 12px;-fx-padding: 10 20 10 20;");
		// mshopr.setStyle("-fx-background-color: #3A3A3A,radial-gradient(center
		// 50% 0%, radius 100%, rgba(114,131,148,0.9),
		// rgba(255,255,255,0));-fx-background-radius:
		// 5,4,3,5;-fx-background-insets: 0,1,2,0;-fx-text-fill:
		// blod;-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5,
		// 0.0 , 0 , 1 );-fx-font-family: Times New Roman;-fx-text-fill:
		// linear-gradient(white, #d0d0d0);-fx-font-size: 12px;-fx-padding: 10
		// 20 10 20;");

		Button mail = new Button("Contact Admin");
		mail.setStyle(
				"-fx-background-color: #3A3A3A,radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));-fx-background-radius: 5,4,3,5;-fx-background-insets: 0,1,2,0;-fx-text-fill: blod;-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );-fx-font-family: Times New Roman;-fx-text-fill: linear-gradient(white, #d0d0d0);-fx-font-size: 12px;-fx-padding: 10 20 10 20;");
		mail.setPrefSize(180, 30);
		mail.setTranslateX(10);
		mail.setTranslateY(50);

		Button addEv = new Button("+");
		addEv.setVisible(false);
		addEv.setTranslateX(240);
		addEv.setTranslateY(-240);
		addEv.setStyle(
				"-fx-text-fill: white; -fx-font-weight: bold;-fx-background-color: linear-gradient(#990012,#990012); -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 )");
		
		mEv.setPrefSize(180, 30);
		mEv.setTranslateX(10);
		mEv.setTranslateY(56);
		mcatal.setPrefSize(180, 30);
		mcatal.setTranslateX(10);
		mcatal.setTranslateY(30);
		mvendor.setPrefSize(180, 30);
		mvendor.setTranslateX(10);
		mvendor.setTranslateY(38);
		mmag.setPrefSize(180, 30);
		mmag.setTranslateX(10);
		mmag.setTranslateY(45);
		mprod.setPrefSize(180, 30);
		mprod.setTranslateX(10);
		mprod.setTranslateY(52);
		msub.setPrefSize(180, 30);
		msub.setTranslateX(10);
		msub.setTranslateY(60);

		// TextField a = new TextField("aaa");
		dec.setStyle(
				"-fx-background-color: #090a0c,linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),linear-gradient(#20262b, #191d22),radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));-fx-background-radius: 5,4,3,5;-fx-background-insets: 0,1,2,0;-fx-text-fill: white;-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );-fx-font-family: Arial;-fx-text-fill: linear-gradient(white, #d0d0d0);-fx-font-size: 12px;-fx-padding: 10 20 10 20;");
		dec.setTranslateX(1292);
		dec.setTranslateY(8);
		dec.setPrefSize(45, 45);
		mprod.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if (Connexion.shopKriaa.getCategorie().getName().equals("Clothes")) {
					CItemMenu cim = new CItemMenu();
					try {
						p.setCenter(cim.start());
						p.getCenter().setTranslateY(-30);
						p.getCenter().setTranslateX(30);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					ItemMenu im = new ItemMenu();
					try {
						p.setCenter(im.start());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		mmag.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				MapTunisiaMall a = new MapTunisiaMall();	
				try {
					p.setCenter(a.start());
					p.getCenter().setTranslateY(-30);
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
				ShopOwnerAddEvent rm = new ShopOwnerAddEvent();
				//addnews.setVisible(false);
				try {
					p.setCenter(rm.start());
					p.getCenter().setTranslateY(-30);
					p.getCenter().setTranslateX(30);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		

		
		mvendor.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				MenuVendor rm = new MenuVendor();
				//addnews.setVisible(false);
				try {
					p.setCenter(rm.start());
					p.getCenter().setTranslateY(-30);
					p.getCenter().setTranslateX(30);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		mail.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				MailAdmin af = new MailAdmin();
				try {
					af.start(new Stage());

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

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
				//System.exit(0);

			}
		});
		
		mcatal.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				ListOrder a = new ListOrder();
				addEv.setVisible(false);
				try {
					p.setCenter(a.start());
					p.getCenter().setTranslateY(140);
					p.getCenter().setTranslateX(180);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});

		mEv.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				ShopOwnerManageEvents a = new ShopOwnerManageEvents();
				try {
					addEv.setVisible(true);
					p.setCenter(a.start());
					p.getCenter().setTranslateY(-30);
					p.getCenter().setTranslateX(30);
					// p.getCenter()
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
		VBox x = new VBox();
		x.getChildren().addAll(dec, mcatal, mvendor, mmag, mprod, msub, aaa, mail,mEv,addEv);
		p.setLeft(x);
		Scene scene = new Scene(p, 1360, 665);
		window.setScene(scene);
		window.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
