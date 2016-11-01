package tunisia.mall.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxAuthFinish;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuthNoRedirect;

@SuppressWarnings("restriction")
public class TestDropBoxJavaFx extends Application {

	String authorizeUrl;

	@Override
	public void start(Stage primaryStage) throws Exception, IOException, DbxException {

		Pane p = new Pane();

		Label nom = new Label("NOM: ");
		final Label ListeFichier = new Label();

		ListeFichier.setTranslateY(150);
		ListeFichier.setLayoutX(150);
		ListeFichier.setLayoutY(150);

		final TextField tfnom = new TextField();
		tfnom.setTranslateX(50);
		Button ajouterCloud = new Button("Ajouter au cloud");
		Button telechargerCloud = new Button("download depuis cloud");
		Button listerCloud = new Button("Lister Le contenu du dossier");
		Button afficherPhoto = new Button("Afficher photo");
		
		
		
		listerCloud.setTranslateY(110);
		ajouterCloud.setTranslateY(50);
		telechargerCloud.setTranslateY(80);
		afficherPhoto.setTranslateY(140);

		DbxClient dbxClient;

		final ImageView myImageView = new ImageView();
		myImageView.setTranslateX(200);
		myImageView.setTranslateY(400);
		myImageView.setFitHeight(280);
		myImageView.setFitWidth(280);

		final JavaDropbox a = new JavaDropbox();
		a.authDropbox(a.DROP_BOX_APP_KEY, a.DROP_BOX_APP_SECRET);

		ajouterCloud.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				FileChooser fileChooser = new FileChooser();
				File file = fileChooser.showOpenDialog(null);
				file = file.getAbsoluteFile();
				System.out.println(file.getPath());
				try {
					a.createFolder(tfnom.getText());
					a.uploadToDropbox(file, tfnom.getText());
				} catch (DbxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		telechargerCloud.setOnAction(new EventHandler<ActionEvent>() {

			@SuppressWarnings("null")
			public void handle(ActionEvent event) {

				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Save file");
				fileChooser.setInitialFileName("simwar.jpg");
				File savedFile = fileChooser.showSaveDialog(null);

				if (savedFile != null)

					try {
						a.downloadFromDropbox("simwar.jpg", "siwar", savedFile.getPath());
					} catch (DbxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				BufferedImage bufferedImage;
				try {
					bufferedImage = ImageIO.read(savedFile.getCanonicalFile());
					Image image = SwingFXUtils.toFXImage(bufferedImage, null);
					myImageView.setImage(image);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		listerCloud.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				try {
					DbxEntry.WithChildren listing = a.listDropboxFolders("/siwar");
					for (DbxEntry child : listing.children) {
						ListeFichier.setText(ListeFichier.getText() + "	" + child.name);
					}
				} catch (DbxException e) {
					e.printStackTrace();
				}

			}
		});
		
		afficherPhoto.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {

				File file = null;
					try {
						file = a.tempFileFromDropbox("simwar.jpg", "siwar", "");
						BufferedImage bufferedImage;

							bufferedImage = ImageIO.read(file);
							Image image = SwingFXUtils.toFXImage(bufferedImage, null);
							myImageView.setImage(image);
					} catch (Exception e) {
						// TODO: handle exception
					}finally{
						file.deleteOnExit();
					}				
			}
		});
		
		
		

		p.getChildren().addAll(nom, tfnom, ajouterCloud, telechargerCloud, listerCloud,afficherPhoto, ListeFichier, myImageView);
		Scene scene = new Scene(p, 900, 900);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public static File byteArrayToFile(byte[] bytearray) throws IOException {
		File file = new File("test.txt");
		file.createNewFile();
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(bytearray);
		fos.close();
		return file;
	}

}
