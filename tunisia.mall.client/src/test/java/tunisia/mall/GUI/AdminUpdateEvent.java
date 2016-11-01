package tunisia.mall.GUI;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.imageio.ImageIO;

import com.dropbox.core.DbxException;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tunisia.mall.businessDelegate.EventServiceDelegate;
import tunisia.mall.persistance.Event;
import tunisia.mall.utils.JavaDropbox;

@SuppressWarnings("restriction")
public class AdminUpdateEvent extends Application{

	public static void main(String [] args){	
		launch(args);	
		}
	
	JavaDropbox dropBox = new JavaDropbox();
	File filedropBox;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		final Pane p = new Pane();
		p.setStyle("-fx-background-color: white;");
		Event ev = EventServiceDelegate.findEvent(AdminManageEvents.idEvCourant);
		
		Event chamsEv=ev;
	
		//title
		Text titre = new Text("Event title: ");
        titre.setFont(Font.font("Arial", 18));
        titre.setTranslateX(100);
        titre.setTranslateY(70);

        final TextField titre1 = new TextField();
        titre1.setTranslateX(310);
        titre1.setTranslateY(50);
        titre1.setText(ev.getTitle());
        
        //Start Date
        Text date = new Text("Start date: ");
        date.setFont(Font.font("Arial", 18));
        date.setTranslateX(100);
        date.setTranslateY(100);

        final DatePicker date1 = new DatePicker();
        date1.setTranslateX(310);
        date1.setTranslateY(80);
        date1.setValue(LocalDate.parse(ev.getDateDebut()+""));
        
        
        //End date
        Text date2 = new Text("End date: ");
        date2.setFont(Font.font("Arial", 18));
        date2.setTranslateX(100);
        date2.setTranslateY(130);

        final DatePicker date3 = new DatePicker();
        date3.setTranslateX(310);
        date3.setTranslateY(110);
        date3.setValue(LocalDate.parse(ev.getDateFin()+""));
        
        
        
        //place
        Text lieu = new Text("Event place: ");
        lieu.setFont(Font.font("Arial", 18));
        lieu.setTranslateX(100);
        lieu.setTranslateY(160);

        final TextField lieu1 = new TextField();
        lieu1.setTranslateX(310);
        lieu1.setTranslateY(140);
        lieu1.setText(ev.getPlace());
        
        //subject
        Text Sujet = new Text("Event subject: ");
        Sujet.setFont(Font.font("Arial", 18));
        Sujet.setTranslateX(100);
        Sujet.setTranslateY(190);

        final TextField Sujet1 = new TextField();
        Sujet1.setTranslateX(310);
        Sujet1.setTranslateY(170);
        Sujet1.setText(ev.getSubject());
        
        //image
        Text im = new Text("pick your picture :");
        im.setFont(Font.font("Arial", 18));
        im.setTranslateX(100);
        im.setTranslateY(240);

        final ImageView myImageView = new ImageView();
        myImageView.setTranslateX(200);
		myImageView.setTranslateY(300);
		myImageView.setFitHeight(280);
		myImageView.setFitWidth(280);
		BufferedImage bufferedImage;
		dropBox.authDropbox(JavaDropbox.DROP_BOX_APP_KEY, JavaDropbox.DROP_BOX_APP_SECRET);
		File file= dropBox.tempFileFromDropbox(ev.getPicture(), "admin", "");
        bufferedImage = ImageIO.read(file);
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        myImageView.setImage(image);

        Button buttonLoad = new Button("Download picture:");
        buttonLoad.setTranslateX(300);
        buttonLoad.setTranslateY(220);
        buttonLoad.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent arg0) {
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif");
                fileChooser.getExtensionFilters().add(extFilter);
                File file = fileChooser.showOpenDialog(null);
                file=file.getAbsoluteFile();
                filedropBox=file;
                try {
                    BufferedImage bufferedImage;
                    bufferedImage = ImageIO.read(file);
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    myImageView.setImage(image);
                    ByteArrayOutputStream baos = null;
                	byte[] imageInByte=null;
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
		/******************button update*************/
		Button save = new Button("   update   ");
		save.setTranslateX(500);
		save.setTranslateY(222);
        save.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {	
		
				String chTitre = titre1.getText();
		        ev.setTitle(chTitre);
		        String chSujet = Sujet1.getText();
		        ev.setSubject(chSujet);
		        LocalDate da = date1.getValue();
		        Instant instant = Instant.from(da.atStartOfDay(ZoneId.systemDefault()));
				Date da1 = Date.from(instant);
				ev.setDateDebut(da1);
				LocalDate da2 = date3.getValue();
		        Instant instant2 = Instant.from(da2.atStartOfDay(ZoneId.systemDefault()));
				Date da3 = Date.from(instant2);
				ev.setDateFin(da3);
				String chLieu = lieu1.getText();
		        ev.setPlace(chLieu);
				EventServiceDelegate.updateEvent(ev);
				Text ajoutReussi = new Text("Saving completed successfully");
                ajoutReussi.setFont(Font.font("Arial", 15));
                ajoutReussi.setTranslateX(500);
				ajoutReussi.setTranslateY(300);
                p.getChildren().add(ajoutReussi);
                
                
                
                try {
					dropBox.authDropbox(JavaDropbox.DROP_BOX_APP_KEY, JavaDropbox.DROP_BOX_APP_SECRET);
					dropBox.uploadToDropbox(filedropBox,"admin");
					dropBox.deleteFileFromDropbox(chamsEv.getPicture(), "admin");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DbxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
                
                
			}
        });
        
	        p.getChildren().addAll(titre,titre1,Sujet,Sujet1,date,date1,date2,date3,lieu,lieu1,im,buttonLoad,myImageView,save);
			
	        Scene scene = new Scene(p,820,620);
	        primaryStage.setTitle("Update Event");
	        primaryStage.setScene(scene);
	        primaryStage.initStyle(StageStyle.UNDECORATED);
	        primaryStage.show();

        }
        }
