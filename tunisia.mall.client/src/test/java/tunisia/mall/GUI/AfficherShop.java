package tunisia.mall.GUI;

import javafx.application.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;

import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

import java.io.File;
import java.io.IOException;
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

import com.itextpdf.text.DocumentException;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import tunisia.mall.businessDelegate.CategorieServiceDelegate;
import tunisia.mall.businessDelegate.ScategorieServiceDelegate;
import tunisia.mall.businessDelegate.ShopServiceDelegate;
import tunisia.mall.interfaces.ShopServiceRemote;
import tunisia.mall.persistance.Customer;
import tunisia.mall.persistance.Shop;

@SuppressWarnings("restriction")
public class AfficherShop  {
	String defaultFileName = "Catalogue.pdf";
	public static int idChams;

	ShopServiceRemote proxy = null;

	TableView<Shop> table;
	Scene scene;
	Customer a = new Customer();
	Stage window;

	@SuppressWarnings("unchecked")
	public Pane start1() throws Exception {

		Context context;
		String jndiName = "tunisia.mall.ejb/ShopService!tunisia.mall.interfaces.ShopServiceRemote";

		try {
			context = new InitialContext();
			proxy = (ShopServiceRemote) context.lookup(jndiName);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		table = new TableView<>();
		table.setEditable(true);
		table.setPrefSize(800, 300);
		// colonne nom
		TableColumn<Shop, String> nom = new TableColumn<>("Name Shop");
		nom.setEditable(true);
		nom.setMinWidth(120);
		nom.setCellValueFactory(new Callback<CellDataFeatures<Shop, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Shop, String> p) {

				return new SimpleStringProperty(p.getValue().getNameShop());
			}
		});

		// colonne website
		TableColumn<Shop, String> website = new TableColumn<>("Categorie");
		website.setEditable(true);
		website.setMinWidth(160);
		website.setCellValueFactory(new Callback<CellDataFeatures<Shop, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Shop, String> p) {
				
				return new SimpleStringProperty(p.getValue().getCategorie().getName());
			}
		});

		// colonne Phone
		TableColumn<Shop, String> phone = new TableColumn<>("Phone");
		phone.setEditable(true);
		phone.setMinWidth(140);
		phone.setCellValueFactory(new Callback<CellDataFeatures<Shop, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Shop, String> p) {

				return new SimpleStringProperty(p.getValue().getPhone() + "");
			}
		});
		
		// colonne status
				TableColumn<Shop, String> status = new TableColumn<>("Status");
				status.setEditable(true);
				status.setMinWidth(140);
				status.setCellValueFactory(new Callback<CellDataFeatures<Shop, String>, ObservableValue<String>>() {
					public ObservableValue<String> call(CellDataFeatures<Shop, String> p) {
						if(p.getValue().getEnabled()){
							return new SimpleStringProperty("Enabled");
						}
						else
							return new SimpleStringProperty("Disabled");
					}
				});
				
		// colonne username
		TableColumn<Shop, String> username = new TableColumn<>("Shop Owner");
		username.setEditable(true);
		username.setMinWidth(120);
		username.setCellValueFactory(new Callback<CellDataFeatures<Shop, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Shop, String> p) {

				return new SimpleStringProperty(p.getValue().getUsername());
			}
		});
		TableColumn<Shop, Hyperlink> nomCat = new TableColumn<>("Catalogue");
		nomCat.setEditable(true);
		nomCat.setMinWidth(120);
		nomCat.setCellValueFactory(new Callback<CellDataFeatures<Shop, Hyperlink>, ObservableValue<Hyperlink>>() {
			public ObservableValue<Hyperlink> call(CellDataFeatures<Shop, Hyperlink> p) {
				Hyperlink aaa = new Hyperlink("Catalogue");
				
				aaa.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
												
						idChams=p.getValue().getId_account();
						FileChooser fileChooser = new FileChooser();
						fileChooser.setTitle("Save file");
						fileChooser.setInitialFileName(defaultFileName);
						File savedFile = fileChooser.showSaveDialog(null);

						if (savedFile != null) {

							try {
								File file = new File(savedFile.getAbsolutePath());
								file.getParentFile().mkdirs();
								new itextpdf().createPdf(savedFile.getAbsolutePath());
								//primaryStage.close();
							} catch (IOException e) {

								e.printStackTrace();

							} catch (DocumentException e) {
								//
								e.printStackTrace();
							}
						}
					}
				});
				return new SimpleObjectProperty<Hyperlink>(aaa);
			}
		});

		// modifier nom
		nom.setCellFactory(TextFieldTableCell.<Shop> forTableColumn());

		nom.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Shop, String>>() {

			public void handle(TableColumn.CellEditEvent<Shop, String> event) {
				Shop a = event.getTableView().getItems().get(event.getTablePosition().getRow());
				a.setNameShop(event.getNewValue());
				ShopServiceDelegate.updateShop(a);

			}
		});

		// modifier website
		website.setCellFactory(TextFieldTableCell.<Shop> forTableColumn());

		website.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Shop, String>>() {

			public void handle(TableColumn.CellEditEvent<Shop, String> event) {
				Shop a = event.getTableView().getItems().get(event.getTablePosition().getRow());
				a.setWebsite(event.getNewValue());
				ShopServiceDelegate.updateShop(a);

			}
		});

		// modifier phone
		phone.setCellFactory(TextFieldTableCell.<Shop> forTableColumn());

		phone.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Shop, String>>() {

			public void handle(TableColumn.CellEditEvent<Shop, String> event) {
				Shop a = event.getTableView().getItems().get(event.getTablePosition().getRow());
				a.setPhone(Long.parseLong(event.getNewValue()));
				ShopServiceDelegate.updateShop(a);

			}
		});

		// modifier username
		username.setCellFactory(TextFieldTableCell.<Shop> forTableColumn());

		username.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Shop, String>>() {

			public void handle(TableColumn.CellEditEvent<Shop, String> event) {
				Shop a = event.getTableView().getItems().get(event.getTablePosition().getRow());
				a.setUsername(event.getNewValue());
				ShopServiceDelegate.updateShop(a);
			}
		});		
		
		/*Button retour = new Button("Retour");
		
		retour.setTranslateX(10);
		retour.setStyle("-fx-font-weight : bold;" + "-fx-font-family : Lato;" + "-fx-background-color: \n"
				+ "        #c3c4c4,\n" + "        linear-gradient(#d6d6d6 50%, white 100%),\n"
				+ "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);\n"
				+ "    -fx-background-radius: 30;\n" + "    -fx-background-insets: 0,1,1;\n"
				+ "    -fx-text-fill: #463E3F;\n");

		retour.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
			}
		});*/
		
		
		Button aa = new Button();
		aa.setPrefSize(100, 96);
		aa.setTranslateX(820);
		aa.setTranslateY(100);
		aa.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(PDFTunisiaMallMagazine.main())
				Confirm.Confirmer("Magazine successfuly added");
				
			}
		});
		BackgroundSize bgS = new BackgroundSize(100, 96, false, false, false, false);
		Background bg = new Background(
				new BackgroundImage(new Image("file:///C:/workspace/tunisia.mall.client/Images/aaa.jpg"), NO_REPEAT,
						NO_REPEAT, null, bgS));
		aa.setBackground(bg);
		
		
		Button bloquer = new Button("Bloquer/Debloquer");
		bloquer.setTranslateX(150);
		bloquer.setTranslateY(320);
		bloquer.setStyle("-fx-font-weight : bold;" + "-fx-font-family : Lato;" + "-fx-background-color: \n"
				+ "        #c3c4c4,\n" + "        linear-gradient(#d6d6d6 50%, white 100%),\n"
				+ "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);\n"
				+ "    -fx-background-radius: 30;\n" + "    -fx-background-insets: 0,1,1;\n"
				+ "    -fx-text-fill: #463E3F;\n");
		bloquer.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				bloquerShop();
			}

		});
		
		Button supprimer = new Button("Supprimer");
		supprimer.setTranslateX(310);
		supprimer.setTranslateY(320);
		supprimer.setStyle("-fx-font-weight : bold;" + "-fx-font-family : Lato;" + "-fx-background-color: \n"
				+ "        #c3c4c4,\n" + "        linear-gradient(#d6d6d6 50%, white 100%),\n"
				+ "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);\n"
				+ "    -fx-background-radius: 30;\n" + "    -fx-background-insets: 0,1,1;\n"
				+ "    -fx-text-fill: #463E3F;\n");
		supprimer.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				supprimerShop();
			}

		});
		
		HBox box = new HBox();
		box.setPadding(new Insets(10, 10, 10, 200));
		//box.getChildren().add(retour);
		//box.getChildren().add(nombre);
		table.setItems(listShop());
		table.getColumns().addAll(nom, website, phone, username,status,nomCat);
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		Pane v = new Pane();
		v.setPrefSize(300, 100);
		AutoCompleteShop sh = new AutoCompleteShop();
		
		sh.setStyle("-fx-background-color: rgb(137, 137, 137);-fx-text-fill:#FFFFFF");
		sh.setPromptText("Find shop by name");
		sh.setTranslateX(200);
		sh.setTranslateY(-100);
		sh.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				String ch = sh.getText();
				if(ch.equals("")){
					table.setItems(listShop());
				}
				else {
					Shop e = ShopServiceDelegate.searchShopByName(ch);
					ObservableList<Shop> liste = FXCollections.observableArrayList();
					liste.add(e);
					table.setItems(liste);
				}
				
			}
		});
		v.getChildren().addAll(table, box,supprimer,bloquer,sh,aa);
		//scene = new Scene(v, 500, 400);

		return v;
	}

	

	public ObservableList<Shop> listShop() {
		ObservableList<Shop> liste = FXCollections.observableArrayList();
		List<Shop> maliste = ShopServiceDelegate.listShop();
		liste.addAll(maliste);

		return liste;
	}
	
	public void supprimerShop() {
        ObservableList<Shop> shops = table.getSelectionModel().getSelectedItems();
        System.out.println(shops);
        for (Shop a : shops) {
            ShopServiceDelegate.deleteShop(a);
        }
        table.setItems(listShop());
        
    }
	
	public void bloquerShop() {
        ObservableList<Shop> shops = table.getSelectionModel().getSelectedItems();
        for (Shop a : shops) {
        	if(a.getEnabled()){
        	a.setEnabled(false);
        	}else{
        		a.setEnabled(true);
        	}
        	ShopServiceDelegate.updateShop(a);
        }

        
        table.setItems(listShop());
        
    }

}
