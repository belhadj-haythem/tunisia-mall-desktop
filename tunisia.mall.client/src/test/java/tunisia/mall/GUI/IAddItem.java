package tunisia.mall.GUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.embed.swing.SwingFXUtils;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import tunisia.mall.businessDelegate.ItemServiceDelegate;
import tunisia.mall.persistance.Item;
import tunisia.mall.persistance.Stock;
import javafx.stage.*;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

@SuppressWarnings("restriction")
public class IAddItem  {
	
	Scene scene;
	Item item = new Item();

	
	public GridPane start() throws Exception {
		GridPane gp = new GridPane();
		gp.setHgap(10);
		gp.setVgap(10);
		gp.setPadding(new Insets(10, 10, 10, 10));

		Label laName = new Label("Name");
		TextField name = new TextField();
		name.setStyle("");
		
		Label laDescription = new Label("Description");
		TextField description = new TextField();

		Label laReference = new Label("Reference");
		TextField reference = new TextField();

		Label laPrice = new Label("Price");
		final NumberSpinner price = new NumberSpinner(BigDecimal.ZERO, new BigDecimal("0.1"),
				new DecimalFormat("#,##0.00"));

		Label laStock = new Label("Stock");
		laStock.setVisible(false);
		final NumberSpinner stock = new NumberSpinner();
		stock.setVisible(false);
		Label oblig = new Label();
		oblig.setStyle("-fx-font-size: 10pt;-fx-font-family: \"Segoe UI Semibold\";-fx-text-fill: red");
		Label oblig1 = new Label();
		oblig1.setStyle("-fx-font-size: 10pt;-fx-font-family: \"Segoe UI Semibold\";-fx-text-fill: red");
		Label oblig2 = new Label();
		oblig2.setStyle("-fx-font-size: 10pt;-fx-font-family: \"Segoe UI Semibold\";-fx-text-fill: red");
		Label oblig3 = new Label();
		oblig3.setStyle("-fx-font-size: 10pt;-fx-font-family: \"Segoe UI Semibold\";-fx-text-fill: red");

		Label laPhoto = new Label("Photo");
		Button buttonLoad = new Button("Add photo...");
		buttonLoad.setStyle("-fx-font-weight : bold;" + "-fx-font-family : Lato;" + "-fx-background-color: \n"
				+ "        #c3c4c4,\n" + "        linear-gradient(#d6d6d6 50%, white 100%),\n"
				+ "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);\n"
				+ "    -fx-background-radius: 30;\n" + "    -fx-background-insets: 0,1,1;\n"
				+ "    -fx-text-fill: #463E3F;\n");
		buttonLoad.setMaxSize(100, 20);
		buttonLoad.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				FileChooser fileChooser = new FileChooser();
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg",
						"*.gif");
				fileChooser.getExtensionFilters().add(extFilter);
				File file = fileChooser.showOpenDialog(null);
				buttonLoad.setText(file.getName());
				try {
					byte[] data = Files.readAllBytes(file.toPath());
					item.setPhoto(data);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});
		Button addStock = new Button("Add Stock");
		Button add = new Button("Add item");
		add.setStyle("-fx-font-weight : bold;" + "-fx-font-family : Lato;" + "-fx-background-color: \n"
				+ "        #c3c4c4,\n" + "        linear-gradient(#d6d6d6 50%, white 100%),\n"
				+ "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);\n"
				+ "    -fx-background-radius: 30;\n" + "    -fx-background-insets: 0,1,1;\n"
				+ "    -fx-text-fill: #463E3F;\n");
		add.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (name.getText().equals("")) {
					oblig.setText("*Required Field");
					oblig1.setText("");
					oblig2.setText("");
					oblig3.setText("");

				} else if (description.getText().equals("")) {

					oblig1.setText("*Required Field");
					oblig.setText("");
					oblig2.setText("");
					oblig3.setText("");
				} else if (reference.getText().equals("")) {
					oblig2.setText("*Required Field");
					oblig1.setText("");
					oblig.setText("");
					oblig3.setText("");
				} else if (item.getPhoto() == null) {
					oblig3.setText("*Required Field");
					oblig1.setText("");
					oblig2.setText("");
					oblig.setText("");
				}
				else {
					if(ItemServiceDelegate.testExistingReference(reference.getText(), 1)) {
						Alerte.alerte("Existing Reference");
						
					}
				else {
					item.setName(name.getText());
					item.setDescription(description.getText());
					item.setReference(reference.getText());
					item.setPrice(price.getNumber().floatValue());
					item.setDisponibility(false);
					item.setShop(Connexion.shopKriaa);
					item.setColor("x");
					oblig1.setText("");
					oblig2.setText("");
					oblig.setText("");
					oblig3.setText("");
					ItemServiceDelegate.addItem(item);
						
					laStock.setVisible(true);
					stock.setVisible(true);
				
				}
			}
			}
		});
		
		addStock.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Stock st = new Stock();
				List<Item> i = ItemServiceDelegate.searchItemByReference(item.getReference(), Connexion.shopKriaa.getId_account());
				for(Item it : i){
				st.setItem(it);
				}
				st.setQuantity(stock.getNumber().intValue());
			
				ItemServiceDelegate.addStock(st);
			}
		});
		
		gp.addRow(1, laName, name, oblig);
		gp.addRow(2, laDescription, description, oblig1);
		gp.addRow(3, laReference, reference, oblig2);
		gp.addRow(4, laPrice, price);
		gp.addRow(5, laPhoto, buttonLoad, oblig3);
		gp.add(add, 2, 6);
		gp.addRow(7, laStock, stock);
		gp.addRow(8, addStock);

		gp.setStyle("-fx-background-color:WHITE;");
		gp.setMaxWidth(960);
		gp.setMaxHeight(465);
		return gp;
	}

	
}
