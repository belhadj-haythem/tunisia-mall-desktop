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
import tunisia.mall.businessDelegate.UserServiceDelegate;
import tunisia.mall.persistance.Customer;
import tunisia.mall.persistance.User;

@SuppressWarnings("restriction")
public class AjoutCustomer extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.initStyle(StageStyle.UNDECORATED);
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(10);

		Label firstName = new Label("First name: ");
		TextField tffname = new TextField();
		tffname.setPromptText("enter your first name");

		Label lastName = new Label("Last name: ");
		TextField tflname = new TextField();
		tflname.setPromptText("enter your last name");

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

		Label address = new Label("Adress: ");
		TextField tfaddress = new TextField();
		tfaddress.setPromptText("enter your adress");

		Label phone = new Label("Phone: ");
		TextField tfphone = new TextField();
		tfphone.setPromptText("enter your Pnone number");

		Label cardNumber = new Label("Card Number: ");
		TextField tfcardNumber = new TextField();
		tfcardNumber.setPromptText("enter your card number");

		Label a = new Label();
		a.setStyle("-fx-text-fill: #FF0000;" + "-fx-font-size: 14");

		Button ajouter = new Button("Ajouter");
		ajouter.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if (isValid(tfemail.getText()) && (!isInteger(tffname.getText())) && (!isInteger(tflname.getText()))) {

					if (isInteger(tfphone.getText()) && (isLong(tfcardNumber.getText()))) {
						Long telephone = Long.parseLong(tfphone.getText());
						Long carte = Long.parseLong(tfcardNumber.getText());
						if (tfphone.getText().length() == 8) {
							if (tfcardNumber.getText().length() == 16) {
								if (tfpwd.getText().equals(tfcpwd.getText())) {

									if (!UserServiceDelegate.chercherCustomerByUsername(tfusername.getText())) {

										Customer customer = new Customer();

										customer.setAdress(tfaddress.getText());
										customer.setCard_number(carte);
										customer.setEmail(tfemail.getText());
										customer.setEnabled(true);
										customer.setLast_name(tflname.getText());
										customer.setFisrt_name(tffname.getText());
										customer.setPassword(tfpwd.getText());
										customer.setUsername(tfusername.getText());
										customer.setPhone(telephone);

										boolean aa = UserServiceDelegate.addCustomer(customer);

										a.setText(aa + "");

									} else {
										a.setText("username déjà utilisé");
									}
								} else {
									a.setText("Les mots de passes doivent être identiques");
								}

							} else {
								a.setText("la carte doit contenir 16 chiffres");
							}

						} else {
							a.setText("le telephone doit être egal à 8 chiffres");
						}
					} else {
						a.setText("Le telephone et la carte ne doivent pas contenir des lettres");
					}

				} else {
					a.setText("Vérifiez les champs First Name,Last Name,Email");
				}
			}

		});

		ajouter.setStyle("-fx-background-color:linear-gradient(#FF7F50, #FF7F50),"
				+ "radial-gradient(center 50% -40%, radius 200%, #FF7F50 45%, #FF7F50 50%);"
				+ "-fx-background-radius: 6, 5;" + "-fx-background-insets: 0, 1;"
				+ "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );"
				+ "-fx-text-fill: #FFFFFF;");

		Button cancel = new Button("Cancel");
		cancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				tfaddress.setText(null);
				tfcardNumber.setText(null);
				tfemail.setText(null);
				tffname.setText(null);
				tflname.setText(null);
				tfphone.setText(null);
				tfpwd.setText(null);
				tfcpwd.setText(null);
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

		GridPane.setConstraints(firstName, 1, 3);
		GridPane.setConstraints(tffname, 2, 3);
		GridPane.setConstraints(lastName, 1, 4);
		GridPane.setConstraints(tflname, 2, 4);
		GridPane.setConstraints(username, 1, 5);
		GridPane.setConstraints(tfusername, 2, 5);
		GridPane.setConstraints(password, 1, 6);
		GridPane.setConstraints(tfpwd, 2, 6);
		GridPane.setConstraints(cpassword, 1, 7);
		GridPane.setConstraints(tfcpwd, 2, 7);
		GridPane.setConstraints(email, 1, 8);
		GridPane.setConstraints(tfemail, 2, 8);
		GridPane.setConstraints(address, 1, 9);
		GridPane.setConstraints(tfaddress, 2, 9);
		GridPane.setConstraints(phone, 1, 10);
		GridPane.setConstraints(tfphone, 2, 10);
		GridPane.setConstraints(cardNumber, 1, 11);
		GridPane.setConstraints(tfcardNumber, 2, 11);
		GridPane.setConstraints(ajouter, 2, 13);
		GridPane.setConstraints(cancel, 2, 13);
		GridPane.setConstraints(a, 2, 14, 2, 1);

		cancel.setTranslateX(70);

		grid.getChildren().addAll(firstName, tffname, lastName, tflname, username, tfusername, password, tfpwd,
				cpassword, tfcpwd, email, tfemail, address, tfaddress, phone, tfphone, cardNumber, tfcardNumber,
				ajouter, cancel, exit, a);

		Scene scene = new Scene(grid, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public boolean isValid(String email) {
		if (email != null && email.trim().length() > 0) {
			return email.matches("(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)*\\@(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)+");
		}
		return false;
	}

	public boolean isInteger(String s) {

		boolean v = false;
		try {
			Integer.parseInt(s);
			v = true;
		} catch (Exception e) {
			v = false;
		}
		return v;
	}

	public boolean isLong(String s) {

		boolean v = false;
		try {
			Long.parseLong(s);
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
