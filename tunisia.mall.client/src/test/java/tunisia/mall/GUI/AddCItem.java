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
import tunisia.mall.businessDelegate.ItemServiceDelegate;
import tunisia.mall.persistance.Item;
import tunisia.mall.persistance.Shop;
import javafx.stage.*;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;


@SuppressWarnings("restriction")
public class AddCItem {
	
	Scene scene;
	static Item item = new Item();
	int i=0;
	List<String> lColor=new ArrayList<String>() ;
	TextField a;
	boolean test = false;
	
	public GridPane start() throws Exception {
		GridPane gp = new GridPane();
		
		gp.setHgap(10);
		gp.setVgap(10);
		gp.setPadding(new Insets(10, 10, 10, 10));

		Label laName = new Label("Name");
		TextField name = new TextField();
		name.setStyle("");
		
		Label laColor = new Label("Color");
		TextField color = new TextField();
		name.setStyle("");
		
		Label laDescription = new Label("Description");
		TextField description = new TextField();

		Label laReference = new Label("Reference");
		TextField reference = new TextField();

		Label laPrice = new Label("Price");
		final NumberSpinner price = new NumberSpinner(BigDecimal.ZERO, new BigDecimal("0.1"),
				new DecimalFormat("#,##0.00"));
		
		Label oblig = new Label();
		oblig.setStyle("-fx-font-size: 8pt;-fx-font-family: \"Segoe UI Semibold\";-fx-text-fill: red");
		Label oblig1 = new Label();
		oblig1.setStyle("-fx-font-size: 8pt;-fx-font-family: \"Segoe UI Semibold\";-fx-text-fill: red");
		Label oblig2 = new Label();
		oblig2.setStyle("-fx-font-size: 8pt;-fx-font-family: \"Segoe UI Semibold\";-fx-text-fill: red");
		Label oblig3 = new Label();
		oblig3.setStyle("-fx-font-size: 8pt;-fx-font-family: \"Segoe UI Semibold\";-fx-text-fill: red");
		Label oblig4 = new Label();
		oblig4.setStyle("-fx-font-size: 8pt;-fx-font-family: \"Segoe UI Semibold\";-fx-text-fill: red");
		
		
		Label laPhoto = new Label("Photo");
		Button buttonLoad = new Button("Add Photo...");
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

		Button add = new Button("Add item");
		add.setStyle("-fx-font-weight : bold;" + "-fx-font-family : Lato;" + "-fx-background-color: \n"
				+ "        #c3c4c4,\n" + "        linear-gradient(#d6d6d6 50%, white 100%),\n"
				+ "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);\n"
				+ "    -fx-background-radius: 30;\n" + "    -fx-background-insets: 0,1,1;\n"
				+ "    -fx-text-fill: #463E3F;\n");
		
		Button btaddcolor = new Button("Add More Colors");
		btaddcolor.setStyle("-fx-font-weight : bold;" + "-fx-font-family : Lato;" + "-fx-background-color: \n"
				+ "        #c3c4c4,\n" + "        linear-gradient(#d6d6d6 50%, white 100%),\n"
				+ "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);\n"
				+ "    -fx-background-radius: 30;\n" + "    -fx-background-insets: 0,1,1;\n"
				+ "    -fx-text-fill: #463E3F;\n");
		
		
		gp.addRow(1, laName, name, oblig);
		gp.addRow(2, laDescription, description, oblig1);
		gp.addRow(3, laReference, reference, oblig2);
		gp.addRow(4, laColor, color,oblig3,btaddcolor);
		gp.addRow(5, laPrice, price);
		gp.addRow(6, laPhoto, buttonLoad, oblig4);
		gp.add(add, 2, 7);
		
		
		btaddcolor.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				if(test){
					if(!a.getText().equals(""))
					lColor.add(a.getText());
				}
				 a = new TextField();
				
				Label aa = new Label("Color");
				i++;
				GridPane.setRowIndex(laPrice, 5+i);
				GridPane.setRowIndex(price, 5+i);
				GridPane.setRowIndex(laPhoto, 6+i);
				GridPane.setRowIndex(buttonLoad, 6+i);
				GridPane.setRowIndex(oblig4, 6+i);
				GridPane.setRowIndex(add, 7+i);
				gp.addRow(i+4, aa,a);
				GridPane.setRowIndex(btaddcolor, i+4);
				
				test=true;

				
			}
		});
	
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
					oblig4.setText("");
				} else if (reference.getText().equals("")) {
					oblig2.setText("*Required Field");
					oblig1.setText("");
					oblig.setText("");
					oblig3.setText("");
					oblig4.setText("");
				} else if ( color.getText().equals("")) {
					oblig3.setText("*Required Field");
					oblig1.setText("");
					oblig2.setText("");
					oblig.setText("");
					oblig4.setText("");
					
				} else if (item.getPhoto() == null){
					oblig4.setText("*Required Field");
					oblig1.setText("");
					oblig2.setText("");
					oblig.setText("");
					oblig3.setText("");
					
				}

				else {
					if(ItemServiceDelegate.testExistingReference(reference.getText(), Connexion.shopKriaa.getId_account())) {
						Alerte.alerte("Existing Reference");
					}
					else{
					item.setName(name.getText());
					item.setDescription(description.getText());
					item.setReference(reference.getText());
					item.setPrice(price.getNumber().floatValue());
					item.setShop(Connexion.shopKriaa);
					lColor.add(color.getText());
					if(test){
					lColor.add(a.getText());
					}
					item.setDisponibility(true);
					oblig1.setText("");
					oblig2.setText("");
					oblig.setText("");
					oblig3.setText("");
					oblig4.setText("");
					for(String c : lColor){
						
						item.setColor(c);
						ItemServiceDelegate.addItem(item);
						if(CItemMenu.inter==1){
							TopItemStock tis = new TopItemStock();
							try {
								DashbordShopOwner.p.setCenter(tis.start());
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						else if(CItemMenu.inter==2){
							BottomItemStock bis= new BottomItemStock();
							try {
								DashbordShopOwner.p.setCenter(bis.start());
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						else{
							ShoesItemStock sis=new ShoesItemStock();
							try {
								DashbordShopOwner.p.setCenter(sis.start());
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
					}
					}
				}
			}
		});
		
		
		


	
		gp.setStyle("-fx-background-color:WHITE;");
		gp.setMaxWidth(960);
		gp.setMaxHeight(465);
		return gp;
		
	}
	
}
