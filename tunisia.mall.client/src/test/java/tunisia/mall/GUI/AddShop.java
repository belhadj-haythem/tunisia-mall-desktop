package tunisia.mall.GUI;

import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import javafx.application.*;
import javafx.stage.*;
import tunisia.mall.businessDelegate.CategorieServiceDelegate;
import tunisia.mall.businessDelegate.ShopServiceDelegate;
import tunisia.mall.persistance.ProductCategorie;
import tunisia.mall.persistance.Shop;
import tunisia.mall.services.ShopService;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.event.*;

import org.controlsfx.dialog.*;
  




@SuppressWarnings("restriction")
public class AddShop {
	String path;
	Stage primaryStage;
	public static String variable="";
	public Pane start11() throws Exception {
		
		//primaryStage = window;
		GridPane grid = new GridPane();
		//primaryStage.initStyle(StageStyle.TRANSPARENT);
		grid.setVgap(20);
		grid.setHgap(10);
		Label shop = new Label("Shop");
		Label shopo = new Label("Shop Owner");
		shop.setStyle("-fx-text-fill: #4A4C46;-fx-font-weight: bold;" + "-fx-font-size: 30px;");
		shopo.setStyle("-fx-text-fill: #4A4C46;-fx-font-weight: bold;" + "-fx-font-size: 30px;");
		shop.setTranslateX(730);
		shop.setTranslateY(80);
		shopo.setTranslateX(225);
		shopo.setTranslateY(80);
		Button exit1 = new Button("x");

		exit1.setStyle("-fx-background-color:#C3FF53;"
				+ "-fx-background-radius: 30;"
				// + "-fx-background-insets: 0,1,2,3,0;"
				+ "-fx-text-fill: #4A4C46;-fx-font-weight: bold;" + "-fx-font-size: 10px;-fx-padding: 5 10 5 10;");

		
		Tooltip tta = new Tooltip();
		tta.setFont(new Font("Times New Roman", 22));
		tta.setText("Log out");
		exit1.setTooltip(tta);
		exit1.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				System.exit(0);

			}
		});

		final TextField autres = new TextField();
		final TextField desc = new TextField();
		final TextField tfsname = new TextField();
		final TextField tfusername = new TextField();
		final PasswordField tfmdp = new PasswordField();
		final TextField tmail = new TextField();
		final TextField tfnom = new TextField();
		final TextField tfprenom = new TextField();
		final TextField tfphone = new TextField();
		final TextField tfwebsite = new TextField();
		final ComboBox<String> choix = new ComboBox<>();
		final FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);
        
        //File file =fileChooser.showOpenDialog(null);
		final Button sign = new Button("sign in");
		sign.setStyle("-fx-background-color: #C3FF53;" 
				+ "-fx-background-radius: 30;"
				// + "-fx-background-insets: 0,1,2,3,0;"
				+ "-fx-text-fill: #4A4C46;-fx-font-weight: bold;" + "-fx-font-size: 12px;-fx-padding: 10 20 10 20;");

		final Button openButton = new Button("Add a Picture...");
		
		List<ProductCategorie> maliste = CategorieServiceDelegate.listShop();
		
		for(ProductCategorie p : maliste){
			choix.getItems().add(p.getName());
		}
		choix.getItems().add("Autres");
		choix.setPrefSize(170, 10);

		/*
		 * GridPane.setConstraints(nom, 1, 2); GridPane.setConstraints(prenom,
		 * 1, 3); GridPane.setConstraints(mail, 1, 4);
		 * GridPane.setConstraints(username, 1, 5); GridPane.setConstraints(mdp,
		 * 1, 6); GridPane.setConstraints(sname, 1, 7);
		 * GridPane.setConstraints(website, 1, 8);
		 * GridPane.setConstraints(phone, 1, 9);
		 * GridPane.setConstraints(catLabel, 1, 10);
		 * GridPane.setConstraints(pic, 1, 11);
		 */

		//GridPane.setConstraints(tfnom, 1, 7);
		//GridPane.setConstraints(tfprenom, 1, 8);
		GridPane.setConstraints(tmail, 1, 7);
		GridPane.setConstraints(tfusername, 1, 8);
		GridPane.setConstraints(tfmdp, 1, 9);
		GridPane.setConstraints(desc, 21, 6);
		GridPane.setConstraints(tfsname, 21, 7);
		GridPane.setConstraints(tfwebsite, 21, 8);
		GridPane.setConstraints(tfphone, 21, 9);
		GridPane.setConstraints(choix, 21, 10);
		GridPane.setConstraints(openButton, 21, 11);
		GridPane.setConstraints(sign, 12, 10);
		GridPane.setConstraints(autres, 22, 10);
		//GridPane.setConstraints(exit1, 25, 0);
		exit1.setTranslateX(1020);
		exit1.setTranslateY(5);
		
		autres.setTranslateX(120);
		autres.setVisible(false);
		//autres.setTranslateY(50);
		
		tfnom.setTranslateX(90);
		tmail.setTranslateX(90);
		tfusername.setTranslateX(90);
		tfmdp.setTranslateX(90);
		Tooltip tt = new Tooltip();
		tt.setFont(new Font("Times New Roman", 22));
		tt.setText("Your password must be \n at least 8 characters");
		tfmdp.setTooltip(tt);
		tfsname.setTranslateX(90);
		desc.setTranslateX(90);
		tfphone.setTranslateX(90);
		tfwebsite.setTranslateX(90);
		tfprenom.setTranslateX(90);
		choix.setTranslateX(90);
		sign.setTranslateX(80);
		openButton.setTranslateX(90);

		openButton.setStyle("-fx-text-fill: #4A4C46;-fx-font-weight: bold;"
						+ "-fx-font-size: 10px;-fx-padding: 7 14 7 14;");

		openButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				File file = fileChooser.showOpenDialog(null);
				path = file.getPath();

			}
		});
		
		choix.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (choix.getValue().equals("Autres")) {
                    autres.setVisible(true);
                } else {
                	autres.setVisible(false);
                }
            }
        });
		
		sign.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Shop shop = new Shop();
				ShopService s = new ShopService();
				shop.setEmail(tmail.getText());
				shop.setEnabled(true);
				variable = tfsname.getText();
				shop.setNameShop(tfsname.getText());
				shop.setPassword(tfmdp.getText());
				shop.setPhone(Integer.parseInt(tfphone.getText()));
				shop.setUsername(tfusername.getText());
				shop.setWebsite(tfwebsite.getText());
				shop.setDescription(desc.getText());
				try {
					shop.setPicture(s.extractBytes(path));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
				
				String ch = choix.getValue();
				if(!ch.equals("Autres")){
					ProductCategorie pc = CategorieServiceDelegate.findCategorieByName(ch);
					System.out.println(ch);
					shop.setCategorie(pc);
				}
				else{
					
					ch=autres.getText();
					ProductCategorie c = new ProductCategorie();
					c.setName(ch);
					CategorieServiceDelegate.addCategorie(c);
					ProductCategorie pc = CategorieServiceDelegate.findCategorieByName(ch);
					shop.setCategorie(pc);
				}
				
				if(ShopServiceDelegate.addShop(shop))
				{
					Confirm.Confirmer("Shop successfuly added !");
					tfnom.setText("");
					tmail.setText("");
					tfusername.setText("");
					tfmdp.setText("");
					tfsname.setText("");
					tfphone.setText("");
					tfwebsite.setText("");
					tfprenom.setText("");
					choix.setValue("");
					desc.setText("");
					
				}
				
			}
		});
		
		desc.setPromptText("Enter a description");
		autres.setPromptText("Add another categorie");
		tfnom.setPromptText("Enter your first name");
		tmail.setPromptText("Enter your E-mail");
		tfusername.setPromptText("Enter your username");
		tfmdp.setPromptText("Enter your password");
		tfsname.setPromptText("Enter your shop name");
		tfphone.setPromptText("Enter your phone");
		tfwebsite.setPromptText("Enter your website");
		tfprenom.setPromptText("Enter your last name");
		desc.setStyle("-fx-background-color: rgba(0, 0, 0, 0.71);-fx-text-fill: #FFFFFF;");
		tfmdp.setStyle("-fx-background-color: rgba(0, 0, 0, 0.71);-fx-text-fill: #FFFFFF;");
		tfusername.setStyle("-fx-background-color: rgba(0, 0, 0, 0.71);-fx-text-fill: #FFFFFF;");
		tfsname.setStyle("-fx-background-color: rgba(0, 0, 0, 0.71);-fx-text-fill: #FFFFFF;");
		tfphone.setStyle("-fx-background-color: rgba(0, 0, 0, 0.71);-fx-text-fill: #FFFFFF;");
		tfphone.setStyle("-fx-background-color: rgba(0, 0, 0, 0.71);-fx-text-fill: #FFFFFF;");
		tfwebsite.setStyle("-fx-background-color: rgba(0, 0, 0, 0.71);-fx-text-fill: #FFFFFF;");
		tmail.setStyle("-fx-background-color: rgba(0, 0, 0, 0.71);-fx-text-fill: #FFFFFF;");
		autres.setStyle("-fx-background-color: rgba(0, 0, 0, 0.71);-fx-text-fill: #FFFFFF;");
		
		
		BackgroundSize bgS = new BackgroundSize(1145, 365, false, false, false, false);
		Background bg = new Background(
				new BackgroundImage(new Image("file:///C:/workspace/tunisia.mall.client/Images/b.png"), NO_REPEAT,
						NO_REPEAT, null, bgS));
		grid.setBackground(bg);
		grid.getChildren().addAll( tmail, tfusername, tfmdp, tfsname, tfphone, tfwebsite, choix,
				openButton, sign, shop, shopo,autres,desc);
		//grid.setMaxHeight(grid.getHeight()+150);
		return grid;
		/*Scene scene = new Scene(grid, 1145, 485);
		primaryStage.setScene(scene);
		primaryStage.show();*/

	}

	
}
