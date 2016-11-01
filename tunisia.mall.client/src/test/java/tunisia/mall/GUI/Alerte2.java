package tunisia.mall.GUI;

import javafx.application.*;
import javafx.stage.Stage;
import javafx.*;
import javafx.application.*;
import javafx.stage.Stage;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;

import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

import java.awt.Dialog.ModalityType;
import java.nio.file.attribute.PosixFilePermission;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.stage.StageStyle;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.geometry.Pos;

public class Alerte2 {
	static int test = 0;
	static int testupdate = 0;
	public static void alerte(String ch) {
		Stage a = new Stage();
		//a.initStyle(StageStyle.TRANSPARENT);
		BorderPane pane = new BorderPane();
		a.setTitle("Boite d'erreur");
		Label header = new Label();
		header.setText("ERROR");
		header.setStyle("-fx-font-family: \"Cursive\";\n" + "    -fx-font-weight: bold;\n"
				+ " -fx-text-fill : #FF0000;"
				+ "-fx-font-size :17px;" + "-fx-text-align: center;");
		Button c = new Button("ok");
		Button c1 = new Button("annuler");
		c1.setTranslateX(390);
		c1.setTranslateY(62);
		c1.setMaxWidth(80);
		c1.setMinHeight(20);
		c1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				test=1;
				a.hide();
			}
		});
		c.setTranslateX(300);
		c.setTranslateY(87);
		c.setMaxWidth(80);
		c.setMinHeight(20);
		c.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				test=2;
				a.hide();
			}
		});
		Label sujet = new Label();
		sujet.setText(ch);
		sujet.setStyle("-fx-font-size :15px;");
		sujet.setTranslateX(100);
		sujet.setTranslateY(90);
		VBox x = new VBox();
		header.setTranslateX(40);
		header.setTranslateY(10);
		x.getChildren().addAll(sujet,header,c,c1);
		//pane.setTop(header);
		
		
		BackgroundSize bgS = new BackgroundSize(475, 185, false, false, false, false);
		Background bg = new Background(
				new BackgroundImage(new Image("file:///C:/workspace/tunisia.mall.client/Images/alert.PNG"), NO_REPEAT,
						NO_REPEAT, null, bgS));
		pane.setBackground(bg);
	
		pane.setCenter(x);
		// pane.getChildren().addAll(header,sujet);

		Scene scene = new Scene(pane, 475, 185);

		a.setScene(scene);
		a.showAndWait();

	}

}
