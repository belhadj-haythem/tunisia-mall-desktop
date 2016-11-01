package tunisia.mall.GUI;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;
import javax.imageio.ImageIO;


import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tunisia.mall.businessDelegate.EventServiceDelegate;
import tunisia.mall.persistance.Event;
import tunisia.mall.utils.JavaDropbox;
import javafx.scene.control.Label;

@SuppressWarnings("restriction")
public class AdminSearchEventByShop extends Application {

	public static int idEvCourantAdmin;
	
	JavaDropbox dropBox = new JavaDropbox();
	File filedropBox;
	

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		List<Event> listev = EventServiceDelegate.searchListEventById(AdminCheckListRequests.idSearch);

		int ligne = 0;
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(0, 10, 0, 10));
		ScrollPane sp = new ScrollPane();
		grid.getChildren().clear();


        
		for (Event ev : listev) {

			if (!ev.isStatutEv()) {

				//byte[] b = ev.getPicture();
				//ByteArrayInputStream in = new ByteArrayInputStream(b);
				File file = null;
				try {
					dropBox.authDropbox(JavaDropbox.DROP_BOX_APP_KEY, JavaDropbox.DROP_BOX_APP_SECRET);
					 file= dropBox.tempFileFromDropbox(ev.getPicture(), "admin", "");
				} catch (Exception e) {
					// TODO: handle exception
				}finally{
					file.deleteOnExit();
				}
				BufferedImage read = ImageIO.read(file);

				Image image = SwingFXUtils.toFXImage(read, null);
				ImageView imageV = new ImageView(image);
				imageV.setFitHeight(122);
				imageV.setFitWidth(122);
				imageV.setEffect(new DropShadow(20, Color.BLACK));

				grid.add(imageV,2, ligne + 1);

				Text evenement = new Text("Shop name: "+ev.getShopOwner().getNameShop()+"\n"+"Title: " + ev.getTitle() + "\n" + "Start date: " + ev.getDateDebut() + "\n"
						+ "End date: " + ev.getDateFin() + "\n" + "Place: " + ev.getPlace());
				evenement.setFont(Font.font("Arial", 20));
				grid.add(evenement,4, ligne + 1);

				Label lblA = new Label("show more details...");
				lblA.setStyle("-fx-font-size: 15 pt; -fx-text-fill: blue;");
				lblA.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {

						AdminShowDetailsEvent se = new AdminShowDetailsEvent();

						idEvCourantAdmin = ev.getId();
						try {
							se.start(new Stage());
						} catch (Exception e) {
							e.printStackTrace();
						}
						primaryStage.hide();
					}
				});
				grid.add(lblA,6, ligne + 1);
				ligne++;
			}
		}

		sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		sp.setContent(grid);

		Scene scene = new Scene(sp, 820, 620, Color.WHITE);
		primaryStage.setTitle("update Event");
		primaryStage.setScene(scene);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.show();

	}

}
