package tunisia.mall.GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;
import javafx.scene.control.DatePicker;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.Subject;

import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import tunisia.mall.businessDelegate.NewsServiceDelegate;
import tunisia.mall.interfaces.NewsServiceRemote;
import tunisia.mall.persistance.News;
import tunisia.mall.services.NewsService;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;


@SuppressWarnings("restriction")
public class NewsAjout {
	String path ;
	
	public Pane start() {
		

		  GridPane grid = new GridPane();
		  grid.setVgap(10);
		  grid.setHgap(20);
		  
		  
		  Button addNewsButton = new Button("Add");
		  Label title = new Label("Title:  ");
		  Label datede = new Label("Start Date   :");
		  Label subject = new Label("Subject  :");
		  title.setStyle("-fx-font-weight: bold;-fx-font-size: 15px;");
		  datede.setStyle("-fx-font-weight: bold;-fx-font-size: 15px;");
		  subject.setStyle("-fx-font-weight: bold;-fx-font-size: 15px;");
		  
	        final Button openButton = new Button("Open a Picture...");
	        openButton.setStyle("-fx-text-fill: white; -fx-font-weight: bold;-fx-background-color: linear-gradient(#990012,#990012); -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 )");
	        GridPane.setConstraints(openButton, 16, 28);
	        final ImageView myImageView = new ImageView();
	        
	        //add picture 
	        final FileChooser fileChooser = new FileChooser();
	        openButton.setOnAction(
	                new EventHandler<ActionEvent>() {
	                  
	                    public void handle(ActionEvent event) {
	                        File file = fileChooser.showOpenDialog(null);
	                     path =file.getPath();
	                     System.out.println(path);
	                     
	                     //affichage photo séléctionnée
	                     try {
	                         BufferedImage bufferedImage;
	                         bufferedImage = ImageIO.read(file);
	                         Image image = SwingFXUtils.toFXImage(bufferedImage, null);
	                         myImageView.setImage(image);
	                         //myImageView.setPreserveRatio(true);
	                         myImageView.setFitHeight(170);
	                         myImageView.setFitWidth(250);
	                         myImageView.setTranslateX(700);
	                         myImageView.setTranslateY(250);
	                         
	                     } catch (IOException ex) {
	                     }
	                        
	                    }
	                });
     
		  
		  
		  
		  final DatePicker tfdatede = new DatePicker(LocalDate.now());
	        tfdatede.setTranslateX(0);
	        tfdatede.setTranslateY(0);
	       
	        final Label reponse = new Label();
	        grid.add(reponse, 1, 17, 2, 1);
	        final TextField tftitle = new TextField();
	        final TextField tfsubject= new TextField();
	        final TextField tfpic = new TextField();
	        GridPane.setConstraints(title, 2, 15);
	        GridPane.setConstraints(tftitle, 3,15);
	        GridPane.setConstraints(tfdatede,3,16);
	        GridPane.setConstraints(datede,2, 16);

	        GridPane.setConstraints(subject,2, 17);
	        GridPane.setConstraints(tfsubject, 3,17);
	        GridPane.setConstraints(openButton , 3, 18);
	        GridPane.setConstraints(tfpic, 3,18);
	        GridPane.setConstraints(addNewsButton,3,19);

	        grid.getChildren().addAll(openButton ,addNewsButton,title,datede,subject,tftitle,tfdatede,tfsubject,myImageView);
	        addNewsButton.setStyle("-fx-text-fill: white; -fx-font-weight: bold;-fx-background-color: linear-gradient(#990012,#990012); -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 )");
	        addNewsButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					
					
					News n  = new News();
					n.setTitle(tftitle.getText());
					LocalDate da = tfdatede.getValue();
					
					
					Instant instant = Instant.from(da.atStartOfDay(ZoneId.systemDefault()));
				Date date = Date.from(instant);
					n.setDateDebut(date);
					try {
						NewsService service = new NewsService();
						n.setPicture(service.extractBytes(path));
					} catch (IOException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}
					n.setSubject(tfsubject.getText());
					
					
						// TODO Auto-generated catch block
					
					if(NewsServiceDelegate.addNews(n)){
						Confirm.Confirmer("News successfuly added");
					}
					

                    				
				}
	        	
	        	
	        });
	        BackgroundSize bgS = new BackgroundSize(900,900, false, false, false, false);
	        Background bg = new Background(new BackgroundImage(new Image("file:///C:/workspace/tunisia.mall.client/Images/titre.png"), NO_REPEAT, NO_REPEAT, null, bgS));
	        grid.setBackground(bg);
	        
	        return grid;
	        
	       /*Scene scene = new Scene(grid, 900, 600);
	        primaryStage.setScene(scene);
	        primaryStage.show();*/
	        
	        

	}
	

	
	

}
