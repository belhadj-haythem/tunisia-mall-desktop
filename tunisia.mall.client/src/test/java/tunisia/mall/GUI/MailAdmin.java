package tunisia.mall.GUI;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;



@SuppressWarnings("restriction")
public class MailAdmin extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
        primaryStage.initStyle(StageStyle.UNDECORATED);
		
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(10);
		grid.setHgap(20);

		Label toLabel = new Label("From");
		GridPane.setConstraints(toLabel, 0, 7,2,1);

		final TextField tfto = new TextField();
		GridPane.setConstraints(tfto, 2, 7);

		Label subjectLabel = new Label("Subject");
		GridPane.setConstraints(subjectLabel, 0, 8,2,1);

		final TextField tfsubject = new TextField();
		GridPane.setConstraints(tfsubject, 2, 8);

		Label messageLabel = new Label("Message");
		GridPane.setConstraints(messageLabel, 0, 9,2,1);

		final TextArea tfmessage = new TextArea();
		GridPane.setConstraints(tfmessage, 2, 9);

		Button envoyer = new Button("Envoyer");
		GridPane.setConstraints(envoyer, 2, 11);

		envoyer.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				
				try {
					SimpleMail.envoyerMail("tunisiamalladm@gmail.com", tfto.getText(), tfmessage.getText(), tfto.getText()+" : "+ tfsubject.getText());
					//System.exit(0);
					Confirm.Confirmer("Mail send successfuly");
					primaryStage.hide();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		envoyer.setTranslateX(270);
        envoyer.setPrefSize(100, 20);


		Button annuler = new Button("Annuler");
		GridPane.setConstraints(annuler, 2, 11);

		annuler.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				primaryStage.hide();
			}
		});

		annuler.setTranslateX(380);
        annuler.setPrefSize(100, 20);
		
		grid.getChildren().clear();
		grid.getChildren().addAll(toLabel, tfto, subjectLabel, tfsubject, messageLabel, tfmessage, envoyer, annuler);

		Scene scene = new Scene(grid, 600, 350);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
