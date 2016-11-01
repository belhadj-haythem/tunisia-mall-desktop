package tunisia.mall.GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tunisia.mall.businessDelegate.UserServiceDelegate;
import tunisia.mall.persistance.Customer;


@SuppressWarnings("restriction")
public class ModifierCustomer extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Customer customer=UserServiceDelegate.findCustomer(6);

		primaryStage.initStyle(StageStyle.UNDECORATED);
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(10);

		Label firstName = new Label("First name: ");
		TextField tffname = new TextField();
		tffname.setText(customer.getFisrt_name());

		Label lastName = new Label("Last name: ");
		TextField tflname = new TextField();
		tflname.setText(customer.getLast_name());

		Label username = new Label("Username: ");
		TextField tfusername = new TextField();
		tfusername.setText(customer.getUsername());

		Label password = new Label("Password: ");
		TextField tfpwd = new TextField();
		tfpwd.setText(customer.getPassword());

		Label email = new Label("Email: ");
		TextField tfemail = new TextField();
		tfemail.setText(customer.getEmail());

		Label address = new Label("Adress: ");
		TextField tfaddress = new TextField();
		tfaddress.setText(customer.getAdress());

		Label phone = new Label("Phone: ");
		TextField tfphone = new TextField();
		tfphone.setText(customer.getPhone()+"");

		Label cardNumber = new Label("Card Number: ");
		TextField tfcardNumber = new TextField();
		tfcardNumber.setText(customer.getCard_number()+"");
		
		Label a = new Label();

		Button modifier = new Button("Ajouter");
		modifier.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				Long telephone=Long.parseLong(tfcardNumber.getText());
				Long carte=Long.parseLong(tfcardNumber.getText());
				Customer customer2 = new Customer();
				
				customer2.setId_account(customer.getId_account());
				customer2.setAdress(tfaddress.getText());
				customer2.setCard_number(carte);
				customer2.setFisrt_name(tffname.getText());
				customer2.setLast_name(tflname.getText());
				customer2.setUsername(tfusername.getText());
				customer2.setPassword(tfpwd.getText());
				customer2.setPhone(telephone);
				
				boolean aa =UserServiceDelegate.updateCustomer(customer2);
				
				
				a.setText(aa+"");
				

			}
		});

		Button cancel = new Button("Cancel");
		cancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

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
		GridPane.setConstraints(email, 1, 7);
		GridPane.setConstraints(tfemail, 2, 7);
		GridPane.setConstraints(address, 1, 8);
		GridPane.setConstraints(tfaddress, 2, 8);
		GridPane.setConstraints(phone, 1, 9);
		GridPane.setConstraints(tfphone, 2, 9);
		GridPane.setConstraints(cardNumber, 1, 10);
		GridPane.setConstraints(tfcardNumber, 2, 10);
		GridPane.setConstraints(modifier, 2, 12);
		GridPane.setConstraints(cancel, 2, 12);
		GridPane.setConstraints(a, 2, 13);


		cancel.setTranslateX(70);

		grid.getChildren().addAll(firstName, tffname, lastName, tflname, username, tfusername, password, tfpwd, email,
				tfemail, address, tfaddress, phone, tfphone, cardNumber, tfcardNumber, modifier, cancel, exit,a);

		Scene scene = new Scene(grid, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}
}