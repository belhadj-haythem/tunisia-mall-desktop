package tunisia.mall.GUI;

import javafx.application.Application;
import javafx.stage.Stage;
import tunisia.mall.businessDelegate.NewsServiceDelegate;
import tunisia.mall.persistance.News;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javax.imageio.ImageIO;

import javafx.scene.Scene;
import javafx.scene.control.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Locale;
import javafx.stage.StageStyle;

@SuppressWarnings("restriction")
public class RemoveNews {
	List<News> newListe = new ArrayList<>();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ScrollPane start() throws Exception {

		Scene scene;

		GridPane grid = new GridPane();
		grid.setStyle("-fx-background-color: white;");

		ScrollPane sp = new ScrollPane();
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(0, 10, 0, 10));
		final DatePicker tfdatede = new DatePicker(LocalDate.now());
		tfdatede.setStyle(
				"-fx-text-fill: white; -fx-font-weight: bold;-fx-background-color: linear-gradient(#990012,#990012); -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 )");
	
		//tfdatede.setValue(null);
		tfdatede.setPromptText("search by Date");
		tfdatede.setTranslateX(0);
		tfdatede.setTranslateY(0);
		ComboBox cb = new ComboBox();
		cb.getItems().add("Date");
		cb.getItems().add("Title");
		cb.setStyle(
				"-fx-text-fill: white; -fx-font-weight: bold;-fx-background-color: linear-gradient(#990012,#990012); -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 )");
	
		
		
		
		
		final TextField tftitle = new TextField();
		
		tfdatede.setValue(null);
		Button search = new Button("search");
		search.setStyle(
				"-fx-text-fill: white; -fx-font-weight: bold;-fx-background-color: linear-gradient(#990012,#990012); -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 )");
	
		//	BackgroundSize bgS = new BackgroundSize(45, 15, false, false, false, false);
		//Background bg = new Background(new BackgroundImage(new Image("file:///C:/workspace/tunisia.mall.client/Images/alert.PNG"), NO_REPEAT,NO_REPEAT, null, bgS));
		//update.setBackground(bg);
		tfdatede.setVisible(false);
		tftitle.setVisible(false);
		cb.setOnAction(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if(cb.getValue().equals("Date")){
					tftitle.setText(" ");
					tftitle.setVisible(false);
					tfdatede.setVisible(true);
					//newListe = NewsServiceDelegate.searchNewsByDate(date);
				}
				if(cb.getValue().equals("Title")){
					tfdatede.setValue(null);
					tftitle.setVisible(true);
					tfdatede.setVisible(false);
					
				}
			}
		});

	
		tftitle.setText(" ");
		GridPane.setConstraints(tftitle, 2, 40);
		GridPane.setConstraints(tfdatede, 3, 10);
		GridPane.setConstraints(tftitle, 4, 40);
		tftitle.setPromptText("Search by title");
		
		
		search.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				newListe = new ArrayList<>();
				
				if (tftitle.getText().equals(" ")) {
					LocalDate aaa = tfdatede.getValue();
					//System.out.println(tfdatede.getValue().toString());
					Instant instant = Instant.from(aaa.atStartOfDay(ZoneId.systemDefault()));
					Date date = Date.from(instant);
					newListe = NewsServiceDelegate.searchNewsByDate(date);
					
				} 
				if(tftitle.getText().equals("")){
					newListe = NewsServiceDelegate.list();
				}
				if(tfdatede.getValue()== null){
					News news = NewsServiceDelegate.searchNewsByName(tftitle.getText());
					newListe.add(news);
				}
				
				System.out.println(newListe.size());
				int ligne = 0;
				bouclefor(grid, newListe, ligne, search, tftitle, tfdatede,cb);

			}
		});

		List<News> liste = NewsServiceDelegate.list();

		int ligne = 0;

		bouclefor(grid, liste, ligne, search, tftitle, tfdatede,cb);

		sp.setContent(grid);
		sp.setMaxWidth(960);
		sp.setMaxHeight(465);
		return sp;
		/*scene = new Scene(sp, 960, 365);

		primaryStage.setScene(scene);
		primaryStage.show();*/

	}


	@SuppressWarnings("unchecked")
	public void bouclefor(GridPane grid, List<News> liste, int ligne, Button search,
			TextField tftitle, DatePicker tfdatede,ComboBox cb) {
		// grid.getChildren().clear();
		
		grid.getChildren().clear();
		cb.setPromptText("search by..");
		grid.add(tfdatede, 3, 1);
		tfdatede.setTranslateY(-80);
		grid.add(search, 4, 1);
		search.setTranslateY(-80);
		grid.add(tftitle, 2, 1);
		tftitle.setTranslateY(-80);
		grid.setTranslateY(40);
		grid.add(cb, 1, 1);
		cb.setTranslateY(-80);
		
		
		
		
		for (News n : liste) {
			try {
				// i=1;

				TextField evenement = new TextField(n.getTitle());
				evenement.setTranslateY(-20);
				TextField evenement1 = new TextField("" + n.getDateDebut());
				evenement1.setTranslateY(-40);
				evenement.setStyle("-fx-background-color: transparent;");
				evenement1.setStyle("-fx-background-color: transparent;");
				grid.setVgap(0);
				grid.setHgap(38);

				evenement.setFont(Font.font("Arial", 18));
				evenement1.setFont(Font.font("Arial", 18));
				evenement.setFocusTraversable(false);
				evenement1.setFocusTraversable(false);
				grid.add(evenement, 3, ligne + 1);
				grid.add(evenement1, 3, ligne + 2);
				
				//affichage chaque photo 
				byte[] b = n.getPicture();
				ByteArrayInputStream in = new ByteArrayInputStream(b);
				BufferedImage read = ImageIO.read(in);

				Image image = SwingFXUtils.toFXImage(read, null);
				ImageView imageV = new ImageView(image);
				imageV.setFitHeight(122);
				imageV.setFitWidth(122);
				imageV.setEffect(new DropShadow(20, Color.BLACK));
				imageV.setTranslateY(20);
				
				grid.add(imageV, 1, ligne + 1);

				Button fermer = new Button("X");

				fermer.setStyle(
						"-fx-text-fill: white; -fx-font-weight: bold;-fx-background-color: linear-gradient(#990012,#990012); -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 )");

				fermer.setTranslateX(-140);
				fermer.setTranslateY(10);

				grid.add(fermer, 10, ligne + 1);

				ligne++;
				
			//Bouton supprimer news action
				
				fermer.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						Alerte2.alerte("Would you really delete the news ?");
						if(Alerte2.test==2){
							NewsServiceDelegate.removeNews(n);
							Alerte2.test=0;
							
						}
						//primaryStage.hide();
						RemoveNews rn = new RemoveNews();
						try {
							rn.start();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				});

				Button update = new Button("U");
			update.setStyle(
						"-fx-text-fill: white; -fx-font-weight: bold;-fx-background-color: linear-gradient(#990012,#990012); -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 )");
				update.setTranslateX(-100);
				update.setTranslateY(-67);
			//	BackgroundSize bgS = new BackgroundSize(45, 15, false, false, false, false);
				//Background bg = new Background(new BackgroundImage(new Image("file:///C:/workspace/tunisia.mall.client/Images/alert.PNG"), NO_REPEAT,NO_REPEAT, null, bgS));
				//update.setBackground(bg);
			

				grid.add(update, 10, ligne + 1);

				ligne++;
				// GridPane.setConstraints(fermer, 7,1);
				// GridPane.setConstraints(update, 8,1);

				
				//Bouton update news action
				
				
				update.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						String chTitre = evenement.getText();
						n.setTitle(chTitre);

						Date d;
						String ds = evenement1.getText();
						DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
						Date dateNew;
						try {
							dateNew = format.parse(ds);
							n.setDateDebut(dateNew);

						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Alerte2.alerte("Would you really update ?");
						if(Alerte2.test==2)
						NewsServiceDelegate.updateNews(n);
						Alerte2.test=0;
						;
						Text ajoutReussi = new Text("Saving completed successfully");
						ajoutReussi.setFont(Font.font("Arial", 15));

					}

				});

			} catch (IOException ex) {
				ex.printStackTrace();

			}

		}
	}

}