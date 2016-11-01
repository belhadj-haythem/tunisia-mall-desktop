package tunisia.mall.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

public class TableauDeFichiers extends Application {

	// --------------------------
	DbxEntry.WithChildren listing;
	TableView<DbxEntry> table;
	Scene scene;
	Stage window;
	JavaDropbox a;

	@SuppressWarnings("restriction")
	@Override
	public void start(Stage primaryStage) throws Exception {
		a = new JavaDropbox();
		a.authDropbox(a.DROP_BOX_APP_KEY, a.DROP_BOX_APP_SECRET);
		table = new TableView<DbxEntry>();
		table.setEditable(true);
		table.setPrefSize(620, 300);
		// window = primaryStage;

		// colonne nom
		TableColumn<DbxEntry, String> nom = new TableColumn<DbxEntry, String>("File Name");
		nom.setEditable(true);
		nom.setMinWidth(120);
		nom.setCellValueFactory(new Callback<CellDataFeatures<DbxEntry, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<DbxEntry, String> p) {

				return new SimpleStringProperty(p.getValue().name);
			}

		});

		TableColumn<DbxEntry, Hyperlink> prenom = new TableColumn<DbxEntry, Hyperlink>("Catalogue");
		prenom.setEditable(true);
		prenom.setMinWidth(120);
		prenom.setCellValueFactory(new Callback<CellDataFeatures<DbxEntry, Hyperlink>, ObservableValue<Hyperlink>>() {
			public ObservableValue<Hyperlink> call(CellDataFeatures<DbxEntry, Hyperlink> p) {
				Hyperlink aaa = new Hyperlink("Download");
				final String nomFichier = p.getValue().name;
				aaa.setOnAction(new EventHandler<ActionEvent>() {

					public void handle(ActionEvent event) {

						FileChooser fileChooser = new FileChooser();
						fileChooser.setTitle("Save file");
						fileChooser.setInitialFileName(nomFichier);
						File savedFile = fileChooser.showSaveDialog(null);

						if (savedFile != null)

							try {
								a.downloadFromDropbox(nomFichier, "siwar", savedFile.getPath());
							} catch (DbxException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
				});
				;
				return new SimpleObjectProperty<Hyperlink>(aaa);
			}
		});

		HBox box = new HBox();
		box.setPadding(new Insets(10, 10, 10, 200));
		table.setItems(listFichier("/siwar"));
		table.getColumns().addAll(nom, prenom);
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		Pane v = new Pane();
		v.setPrefSize(300, 100);
		v.getChildren().addAll(table, box);
		v.setMaxWidth(960);
		v.setMaxHeight(465);
		scene = new Scene(v, 740, 420);
		primaryStage.setScene(scene);
		primaryStage.show();
		// return v;

	}

	public static void main(String[] args) {
		launch(args);
	}

	public ObservableList<DbxEntry> listFichier(String username) throws DbxException {

		ObservableList<DbxEntry> liste = FXCollections.observableArrayList();

		listing = a.listDropboxFolders(username);
		List<DbxEntry> chams = new ArrayList<DbxEntry>();

		for (DbxEntry child : listing.children) {
			chams.add(child);
			System.out.println("	" + child.name + ": " + child.toString());
		}

		liste.addAll(chams);

		return liste;
	}

}