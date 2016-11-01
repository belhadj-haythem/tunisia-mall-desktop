package tunisia.mall.GUI;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.stage.Stage;
import tunisia.mall.persistance.Customer;
import tunisia.mall.persistance.Event;
import tunisia.mall.persistance.Item;
import tunisia.mall.utils.JavaDropbox;

import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import org.junit.Before;
import org.junit.Test;

import com.dropbox.core.DbxException;

import tunisia.mall.businessDelegate.EventServiceDelegate;
import tunisia.mall.businessDelegate.ShopServiceDelegate;
import tunisia.mall.interfaces.EventServicesRemote;
import tunisia.mall.persistance.Event;

@SuppressWarnings({ "restriction", "unused" })
public class AdminAddEvent {

	Event ev = new Event();
	
	JavaDropbox dropBox = new JavaDropbox();
	File filedropBox;

	public Pane start() throws Exception {

		final Pane p = new Pane();
		//p.setStyle("-fx-background-color: white;");

		/************ title **************/
		Text titre = new Text("Event title: ");
		titre.setFont(Font.font("Arial", 18));
		titre.setTranslateX(50);
		titre.setTranslateY(70);

		final TextField titre1 = new TextField();
		titre1.setTranslateX(200);
		titre1.setTranslateY(50);

		/*********** date debut ***********/
		Text date = new Text("Start date: ");
		date.setFont(Font.font("Arial", 18));
		date.setTranslateX(50);
		date.setTranslateY(100);

		final DatePicker date1 = new DatePicker();
		date1.setTranslateX(200);
		date1.setTranslateY(80);

		/********* date fin ***********/
		Text date2 = new Text("End date: ");
		date2.setFont(Font.font("Arial", 18));
		date2.setTranslateX(50);
		date2.setTranslateY(130);

		final DatePicker date3 = new DatePicker();
		date3.setTranslateX(200);
		date3.setTranslateY(110);

		/************** lieu ************/
		Text lieu = new Text("Event place: ");
		lieu.setFont(Font.font("Arial", 18));
		lieu.setTranslateX(50);
		lieu.setTranslateY(160);

		final TextField lieu1 = new TextField();
		lieu1.setTranslateX(200);
		lieu1.setTranslateY(140);

		/************** sujet ************/
		Text Sujet = new Text("Event subject: ");
		Sujet.setFont(Font.font("Arial", 18));
		Sujet.setTranslateX(50);
		Sujet.setTranslateY(190);

		final TextField Sujet1 = new TextField();
		Sujet1.setTranslateX(200);
		Sujet1.setTranslateY(170);// 400,70

		/**************************** Image *************************/

		Text im = new Text("pick your picture :");
		im.setFont(Font.font("Arial", 18));
		im.setTranslateX(50);
		im.setTranslateY(240);

		final ImageView myImageView = new ImageView();
		myImageView.setTranslateX(200);
		myImageView.setTranslateY(300);
		myImageView.setFitHeight(280);
		myImageView.setFitWidth(280);

		Button buttonLoad = new Button("Click to choose");
		buttonLoad.setTranslateX(200);
		buttonLoad.setTranslateY(222);
		buttonLoad.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				FileChooser fileChooser = new FileChooser();
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg",
						"*.gif");
				fileChooser.getExtensionFilters().add(extFilter);
				File file = fileChooser.showOpenDialog(null);
				file = file.getAbsoluteFile();
				filedropBox=file;
				try {
					BufferedImage bufferedImage;
					bufferedImage = ImageIO.read(file);
					Image image = SwingFXUtils.toFXImage(bufferedImage, null);
					myImageView.setImage(image);
					ByteArrayOutputStream baos = null;
					byte[] imageInByte = null;
					baos = new ByteArrayOutputStream();
					ImageIO.write(bufferedImage, "jpg", baos);
					baos.flush();
					imageInByte = baos.toByteArray();
					ev.setPicture(file.getName());
					baos.close();
				} catch (IOException ex) {

				}

			}
		});
		/*********************************
		 * bonton enregistrer
		 ************************************************/

		Button save = new Button("Save new Event");
		save.setTranslateX(500);
		save.setTranslateY(222);
		save.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				String chTitre = titre1.getText();
				ev.setTitle(chTitre);
				String chSujet = Sujet1.getText();
				ev.setSubject(chSujet);
				String chLieu = lieu1.getText();
				ev.setPlace(chLieu);

				ev.setShopOwner(ShopServiceDelegate.findShopById(52));// recupereerr
																		// depuis
																		// authentification

				LocalDate da = date1.getValue();
				Instant instant = Instant.from(da.atStartOfDay(ZoneId.systemDefault()));
				Date da1 = Date.from(instant);

				LocalDate da2 = date3.getValue();
				Instant instant2 = Instant.from(da2.atStartOfDay(ZoneId.systemDefault()));
				Date da3 = Date.from(instant2);

				if (date1.getValue().isAfter(LocalDate.now()) && date1.getValue().isBefore(da2)) {

					ev.setDateDebut(da1);
					ev.setDateFin(da3);
					ev.setStatutEv(true);
					EventServiceDelegate.addEvent(ev);
					Text ajoutReussi = new Text("Saving completed successfully");
					ajoutReussi.setFont(Font.font("Arial", 15));
					ajoutReussi.setTranslateX(500);
					ajoutReussi.setTranslateY(300);
					p.getChildren().add(ajoutReussi);
				} else {
					Text ajoutReussi = new Text("             !!!ERROR!!!" + "\n" + "Please choose a suitable date");
					ajoutReussi.setFont(Font.font("Arial", 15));
					ajoutReussi.setTranslateX(500);
					ajoutReussi.setTranslateY(300);
					p.getChildren().add(ajoutReussi);
				}
				
				try {
					dropBox.authDropbox(JavaDropbox.DROP_BOX_APP_KEY, JavaDropbox.DROP_BOX_APP_SECRET);
					dropBox.uploadToDropbox(filedropBox,"admin");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DbxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}

		});

		/************************************************************************************************************/

		p.getChildren().addAll(titre, titre1, Sujet, Sujet1, date, date1, date2, date3, lieu, lieu1, im, buttonLoad,
				myImageView, save);
		BackgroundSize bgS = new BackgroundSize(1355, 660, false, false, false, false);
		Background bg = new Background(
				new BackgroundImage(new Image("file:///C:/workspace/tunisia.mall.client/Images/b.png"), NO_REPEAT,
						NO_REPEAT, null, bgS));
		p.setBackground(bg);
		p.setMaxWidth(960);
		p.setMaxHeight(465);
		return p;
		// Scene scene = new Scene(p, 820, 620);

	}

}
