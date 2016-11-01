package tunisia.mall.GUI;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tunisia.mall.businessDelegate.EventServiceDelegate;
import tunisia.mall.persistance.Event;
import tunisia.mall.persistance.Item;
import tunisia.mall.utils.JavaDropbox;
import javafx.embed.swing.SwingFXUtils;

/**
 *
 * @author YOSRA
 */

@SuppressWarnings("restriction")
public class AdminShowDetailsEvent extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	JavaDropbox dropBox = new JavaDropbox();
	File filedropBox;

	@Override
	public void start(final Stage primaryStage) throws Exception {

		Pane p = new Pane();
		p.setStyle("-fx-background-color: #ffffff");

		Event ev = EventServiceDelegate.findEvent(AdminCheckListRequests.idEvCourantAdmin);

		Text shop = new Text("Shop/Admin: " + ev.getShopOwner().getNameShop());
		shop.setFont(Font.font("Arial", 20));
		shop.setTranslateX(200);
		shop.setTranslateY(50);

		// byte[] b = ev.getPicture();
		// ByteArrayInputStream in = new ByteArrayInputStream(b);
		File file = null;
		try {
			dropBox.authDropbox(JavaDropbox.DROP_BOX_APP_KEY, JavaDropbox.DROP_BOX_APP_SECRET);
			 file= dropBox.tempFileFromDropbox(ev.getPicture(), "admin", "");
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			file.deleteOnExit();
		}
		BufferedImage read = ImageIO.read(file);

		Image image = SwingFXUtils.toFXImage(read, null);
		ImageView imageV = new ImageView(image);
		imageV.setFitHeight(200);
		imageV.setFitWidth(400);
		imageV.setTranslateX(200);
		imageV.setTranslateY(65);
		imageV.setEffect(new DropShadow(20, Color.BLACK));

		Text date1 = new Text("Start date: " + ev.getDateDebut().toString());
		date1.setFont(Font.font("Arial", 20));
		date1.setTranslateX(40);
		date1.setTranslateY(310);

		Text date2 = new Text("End date: " + ev.getDateFin().toString());
		date2.setFont(Font.font("Arial", 20));
		date2.setTranslateX(40);
		date2.setTranslateY(340);

		Text title = new Text("Title: " + ev.getTitle());
		title.setFont(Font.font("Arial", 20));
		title.setTranslateX(40);
		title.setTranslateY(370);

		Text subject = new Text("Subject: " + "\n" + ev.getSubject());
		subject.setFont(Font.font("Arial", 20));
		subject.setTranslateX(40);
		subject.setTranslateY(410);

		/***********************************
		 * button activate
		 ********************************************/

		Button activate = new Button("Click to avtivate this event");
		activate.setTranslateY(300);
		activate.setTranslateX(500);
		activate.setStyle("-fx-font-weight : bold;" + "-fx-font-family : Lato;" + "-fx-background-color: \n"
				+ "        #c3c4c4,\n" + "        linear-gradient(#d6d6d6 50%, white 100%),\n"
				+ "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);\n"
				+ "    -fx-background-radius: 30;\n" + "    -fx-background-insets: 0,1,1;\n"
				+ "    -fx-text-fill: #463E3F;\n");
		activate.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ev.setStatutEv(true);
				EventServiceDelegate.updateEvent(ev);
				primaryStage.hide();
				AdminCheckListRequests sle = new AdminCheckListRequests();
				try {
					// sle.start(new Stage());
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

		/***********************************
		 * button refuse
		 *******************************************/
		Button refuse = new Button("Click to refuse this event");
		refuse.setTranslateY(350);
		refuse.setTranslateX(500);
		refuse.setStyle("-fx-font-weight : bold;" + "-fx-font-family : Lato;" + "-fx-background-color: \n"
				+ "        #c3c4c4,\n" + "        linear-gradient(#d6d6d6 50%, white 100%),\n"
				+ "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);\n"
				+ "    -fx-background-radius: 30;\n" + "    -fx-background-insets: 0,1,1;\n"
				+ "    -fx-text-fill: #463E3F;\n");
		refuse.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				Stage newStage = new Stage();
				Label lb = new Label("Are you sure you want to remove this event?");
				lb.setStyle("-fx-font-size: 18 pt; -fx-text-fill: black;");
				Button btn1 = new Button("Remove");

				btn1.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {

						List<Item> items = EventServiceDelegate.listItems(1);

						for (Item i : items) {
							if (i.getEvent().getId() == ev.getId()) {

								float formule = ((100 * i.getPrice()) / (100 - i.getDiscount()));
								i.setPrice(formule);
								EventServiceDelegate.updateItem(i);
								Event siwar = new Event();
								siwar.setId(8);
								i.setEvent(siwar);
								i.setDiscount(0);
								EventServiceDelegate.updateItem(i);
							}
						}
						ev.setShopOwner(null);
						EventServiceDelegate.updateEvent(ev);
						EventServiceDelegate.deleteEvent(ev);
						primaryStage.hide();

					}
				});

				Button btn2 = new Button("Cancel");
				// vb.getChildren().add(btn2);
				btn2.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						newStage.hide();
					}
				});
				VBox vb = new VBox(10, lb, btn1, btn2);
				vb.setAlignment(Pos.CENTER);

				StackPane layout = new StackPane(vb);
				layout.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5);");
				layout.setPrefSize(400, 200);

				Scene scene1 = new Scene(layout);
				scene1.setFill(Color.TRANSPARENT);
				newStage.setScene(scene1);
				newStage.show();
			}
		});
		/****************************************************************************************/

		p.getChildren().addAll(shop, refuse, imageV, date1, date2, subject, title, activate);

		Scene scene = new Scene(p, 820, 620);
		primaryStage.setTitle("show event");
		primaryStage.setScene(scene);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.show();

	}

}
