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
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tunisia.mall.persistance.Administrator;
import tunisia.mall.businessDelegate.UserServiceDelegate;

@SuppressWarnings("restriction")
public class ModifierAdmin extends Application {
	
	
	Administrator admin=UserServiceDelegate.findAdmin(4); 
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		

		primaryStage.initStyle(StageStyle.UNDECORATED);
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(10);

		Label name = new Label("Name: ");
		TextField tfnom = new TextField();
		tfnom.setText(admin.getName_admin());

		Label username = new Label("Username: ");
		TextField tfusername = new TextField();
		tfusername.setText(admin.getUsername());

		Label password = new Label("Password: ");
		TextField tfpwd = new TextField();
		tfpwd.setText(admin.getPassword());

		Label email = new Label("Email: ");
		TextField tfemail = new TextField();
		tfemail.setText(admin.getEmail());
		
		Label a = new Label();

		Button modifier = new Button("Modifier");
		modifier.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Administrator admin2 = new Administrator();
				admin2.setId_account(admin.getId_account());
				admin2.setEmail(tfemail.getText());
				admin2.setEnabled(true);
				admin2.setName_admin(tfnom.getText());
				admin2.setPassword(tfpwd.getText());
				admin2.setUsername(tfusername.getText());				
				
				boolean aa =UserServiceDelegate.updateAdmin(admin2);
				
				
					a.setText(aa+"");

			}
		});

		Button cancel = new Button("Cancel");
		cancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				tfemail.setText(admin.getEmail());
				tfnom.setText(admin.getName_admin());
				tfpwd.setText(admin.getPassword());
				tfusername.setText(admin.getUsername());
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
		GridPane.setConstraints(username, 0, 6);
		GridPane.setConstraints(tfusername, 1, 6);
		GridPane.setConstraints(password, 0, 8);
		GridPane.setConstraints(tfpwd, 1, 8);
		GridPane.setConstraints(email, 0, 10);
		GridPane.setConstraints(tfemail, 1, 10);
		GridPane.setConstraints(modifier, 1, 15);
		GridPane.setConstraints(cancel, 1, 15);
		GridPane.setConstraints(a, 1, 16);
		cancel.setTranslateX(70);

		grid.getChildren().addAll(name, tfnom, username, tfusername, password, tfpwd, email, tfemail, modifier, cancel,exit,a);
		Scene scene = new Scene(grid, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
