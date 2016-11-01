package tunisia.mall.GUI;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.StageStyle;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class Confirm {
	public static void Confirmer(String ch) {
		Stage a = new Stage();
		//a.initStyle(StageStyle.TRANSPARENT);
		BorderPane pane = new BorderPane();
		a.setTitle("Boite d'erreur");
		Label header = new Label();
		header.setText("Information");
		header.setStyle("-fx-font-family: \"Cursive\";\n" + "    -fx-font-weight: bold;\n"
				+ " -fx-text-fill : #356FF6;"
				+ "-fx-font-size :16px;" + "-fx-text-align: center;");
		Button c = new Button("ok");
		c.setTranslateX(300);
		c.setTranslateY(87);
		c.setMaxWidth(80);
		c.setMinHeight(20);
		c.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
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
		header.setTranslateY(7);
		x.getChildren().addAll(sujet,header,c);
		//pane.setTop(header);
		
		
		BackgroundSize bgS = new BackgroundSize(475, 185, false, false, false, false);
		Background bg = new Background(
				new BackgroundImage(new Image("file:///C:/workspace/tunisia.mall.client/Images/confirm.jpg"), NO_REPEAT,
						NO_REPEAT, null, bgS));
		pane.setBackground(bg);
	
		pane.setCenter(x);
		// pane.getChildren().addAll(header,sujet);

		Scene scene = new Scene(pane, 475, 185);

		a.setScene(scene);
		a.showAndWait();

	}

}

