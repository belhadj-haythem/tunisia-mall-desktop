package tunisia.mall.GUI;

import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
//import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

import java.io.File;

import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tunisia.mall.businessDelegate.UserServiceDelegate;
import tunisia.mall.persistance.Administrator;

@SuppressWarnings("restriction")
public class AjoutAdmin extends Application {

	int i = 0;

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.initStyle(StageStyle.UNDECORATED);
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(10);

		Label name = new Label("Name: ");
		TextField tfnom = new TextField();
		tfnom.setPromptText("enter your name");
		/*Button openButton = new Button();
		openButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				File file = fileChooser.showOpenDialog(null);
				path = file.getPath();

			}
		});*/
		
		
		
		Label username = new Label("Username: ");
		TextField tfusername = new TextField();
		tfusername.setPromptText("enter your username");

		Label password = new Label("Password: ");
		PasswordField tfpwd = new PasswordField();

		Label cpassword = new Label("Confirm Password: ");
		PasswordField tfcpwd = new PasswordField();

		Label email = new Label("Email: ");
		TextField tfemail = new TextField();
		tfemail.setPromptText("enter your email");
		Label a = new Label();

		Button ajouter = new Button("Ajouter");
		ajouter.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				Administrator admin = new Administrator();
				admin.setEmail(tfemail.getText());
				admin.setEnabled(true);
				admin.setName_admin(tfnom.getText());
				admin.setPassword(tfpwd.getText());
				admin.setUsername(tfusername.getText());

				if (isValid(tfemail.getText()) && (!isInteger(tfnom.getText()))) {

					if (tfpwd.getText().equals(tfcpwd.getText())) {

						if (!UserServiceDelegate.chercherUserByUsername(tfusername.getText())) {

							boolean aa = UserServiceDelegate.addAdmin(admin);

							a.setText(aa + "");

						} else {
							a.setText("username déjà utilisé");

						}

					} else {
						a.setText("Les mots de passes doivent être identiques");
					}

				} else {
					a.setText("Vérifiez les champs NOM,PRENOM,EMAIL");
				}

			}
		});

		Button cancel = new Button("Cancel");
		cancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				tfemail.setText(null);
				tfnom.setText(null);
				tfpwd.setText(null);
				tfusername.setText(null);
				a.setText(null);
			}
		});

		Button exit = new Button("X");
		exit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.exit(0);
			}
		});

		exit.setTranslateX(476);

		GridPane.setConstraints(name, 0, 3);
		GridPane.setConstraints(tfnom, 1, 3);
		GridPane.setConstraints(username, 0, 4);
		GridPane.setConstraints(tfusername, 1, 4);
		GridPane.setConstraints(password, 0, 5);
		GridPane.setConstraints(tfpwd, 1, 5);
		GridPane.setConstraints(cpassword, 0, 6);
		GridPane.setConstraints(tfcpwd, 1, 6);
		GridPane.setConstraints(email, 0, 7);
		GridPane.setConstraints(tfemail, 1, 7);
		GridPane.setConstraints(ajouter, 1, 9);
		GridPane.setConstraints(cancel, 1, 9);
		GridPane.setConstraints(a, 1, 12);

		cancel.setTranslateX(70);

		grid.getChildren().addAll(name, tfnom, username, tfusername, password, tfpwd, cpassword, tfcpwd, email, tfemail,
				ajouter, cancel, exit, a);
		Scene scene = new Scene(grid, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static boolean isValid(String email) {
		if (email != null && email.trim().length() > 0) {
			return email.matches("(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)*\\@(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)+");
		}
		return false;
	}

	public static boolean isInteger(String s) {

		boolean v = false;
		try {
			Integer.parseInt(s);
			v = true;
		} catch (Exception e) {
			v = false;
		}
		return v;
	}

	public static void main(String[] args) {

		launch(args);
	}

}
