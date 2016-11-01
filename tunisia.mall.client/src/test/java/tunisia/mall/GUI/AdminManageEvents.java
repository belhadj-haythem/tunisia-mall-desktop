package tunisia.mall.GUI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import tunisia.mall.businessDelegate.EventServiceDelegate;
import tunisia.mall.interfaces.EventServicesRemote;
import tunisia.mall.persistance.Event;
import tunisia.mall.utils.JavaDropbox;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.management.PlatformLoggingMXBean;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javax.imageio.ImageIO;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

@SuppressWarnings({ "unused", "restriction" })
public class AdminManageEvents {

	public static int idEvCourant;

	JavaDropbox dropBox = new JavaDropbox();
	File filedropBox;
	
	public ScrollPane start() throws Exception {

		int ligne = 0;
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(0, 10, 0, 10));
		ScrollPane sp = new ScrollPane();
		grid.getChildren().clear();

		List<Event> listev = EventServiceDelegate.searchListEventById(52);

		for (Event ev : listev) {

			if (ev.isStatutEv()) {
				//byte[] b = ev.getPicture();
				//ByteArrayInputStream in = new ByteArrayInputStream(b);
				dropBox.authDropbox(JavaDropbox.DROP_BOX_APP_KEY, JavaDropbox.DROP_BOX_APP_SECRET);
				File file= dropBox.tempFileFromDropbox(ev.getPicture(), "admin", "");
				
				BufferedImage read = ImageIO.read(file);

				Image image = SwingFXUtils.toFXImage(read, null);
				ImageView imageV = new ImageView(image);
				imageV.setFitHeight(122);
				imageV.setFitWidth(122);
				imageV.setEffect(new DropShadow(20, Color.BLACK));

				grid.add(imageV, 1, ligne + 1);

				Text evenement = new Text("Title: " + ev.getTitle() + "\n" + "Subject: " + ev.getSubject() + "\n"
						+ "Start date: " + ev.getDateDebut() + "\n" + "End date: " + ev.getDateFin() + "\n" + "Place: "
						+ ev.getPlace());
				evenement.setFont(Font.font("Arial", 20));
				grid.add(evenement, 3, ligne + 1);

				
				
				//Button addEv = new Button("+");
				
				
				
				Label lblA = new Label("Update");
				lblA.setStyle("-fx-font-size: 15 pt; -fx-text-fill: blue;");
				lblA.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						AdminUpdateEvent ue = new AdminUpdateEvent();
						try {
							idEvCourant = ev.getId();
							ue.start(new Stage());
							//primaryStage.hide();
						} catch (Exception e) {

							e.printStackTrace();
						}

					}
				});

				Label lblB = new Label("Delete");
				lblB.setStyle("-fx-font-size: 15 pt; -fx-text-fill: blue;");
				lblB.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						Stage newStage = new Stage();

						Label lb = new Label("Are you sure you want to remove this event?");
						lb.setStyle("-fx-font-size: 18 pt; -fx-text-fill: black;");

						Button btn1 = new Button("Remove");
						btn1.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {
								EventServiceDelegate.deleteEvent(ev);
								dropBox.deleteFileFromDropbox(ev.getPicture(), "admin");
								//primaryStage.hide();
								AdminManageEvents sle = new AdminManageEvents();
								try {
									//sle.start(new Stage());
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});

						Button btn2 = new Button("Cancel");
						btn2.setOnAction(new EventHandler<ActionEvent>() {
							public void handle(ActionEvent event) {
								newStage.hide();
							}
						});
						VBox vb = new VBox(10, lb, btn1, btn2);
						vb.setAlignment(Pos.CENTER);

						StackPane layout = new StackPane(vb);
						layout.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5);");
						layout.setPrefSize(450, 200);

						Scene scene1 = new Scene(layout);
						scene1.setFill(Color.TRANSPARENT);
						newStage.setScene(scene1);
						newStage.show();
					}
				});

				grid.add(lblA,6, ligne + 1);
				grid.add(lblB,11, ligne + 1);

				ligne++;
			}
		}
		
		sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		sp.setContent(grid);
		sp.setMaxWidth(960);
		sp.setMaxHeight(465);
		return sp;

		//Scene scene = new Scene(sp, 820, 620);
		/*primaryStage.setTitle("update Event");
		primaryStage.setScene(scene);
		primaryStage.initStyle(StageStyle.UNDECORATED);

		primaryStage.show();*/
	}


}
