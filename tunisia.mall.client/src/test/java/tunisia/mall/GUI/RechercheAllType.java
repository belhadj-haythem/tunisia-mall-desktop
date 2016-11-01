package tunisia.mall.GUI;

import java.util.List;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import tunisia.mall.businessDelegate.UserServiceDelegate;
import tunisia.mall.persistance.Customer;
import tunisia.mall.persistance.User;

@SuppressWarnings("restriction")
public class RechercheAllType {

	
	// --------------------------

	TableView<User> table;
	Scene scene;
	User a = new User();
	Stage window;

	
	public Pane start() throws Exception {
		table = new TableView<>();
		table.setEditable(true);
		table.setPrefSize(620, 300);
		//window = primaryStage;


		// colonne Email
		TableColumn<User, String> mail = new TableColumn<>("Email");
		mail.setEditable(true);
		mail.setMinWidth(140);
		mail.setCellValueFactory(new Callback<CellDataFeatures<User, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<User, String> p) {

				return new SimpleStringProperty(p.getValue().getEmail());
			}
		});
		
		// colonne type
				TableColumn<User, String> type = new TableColumn<>("Type");
				type.setEditable(true);
				type.setMinWidth(140);
				type.setCellValueFactory(new Callback<CellDataFeatures<User, String>, ObservableValue<String>>() {
					public ObservableValue<String> call(CellDataFeatures<User, String> p) {

						if (p.getValue() instanceof Customer){
							return new SimpleStringProperty("Customer");
						}else{
							return new SimpleStringProperty("Shop");
						}
						
						
					}
				});
		
		// colonne usename
		TableColumn<User, String> username = new TableColumn<>("Username");
		username.setEditable(true);
		username.setMinWidth(120);
		username.setCellValueFactory(new Callback<CellDataFeatures<User, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<User, String> p) {

				return new SimpleStringProperty(p.getValue().getUsername());
			}
		});

		// colonne statut
		TableColumn<User, String> statut = new TableColumn<>("Statut");
		statut.setEditable(true);
		statut.setMinWidth(120);
		statut.setCellValueFactory(new Callback<CellDataFeatures<User, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<User, String> p) {

				if (p.getValue().getEnabled()) {
					return new SimpleStringProperty("Enabled");
				} else {
					return new SimpleStringProperty("Disabled");
				}
			}
		});

		

		// bouton supprimer
		Button supprimer = new Button("Supprimer");
		supprimer.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				supprimerUser();

			}

		});

		// bouton bloquer
		Button bloquer = new Button("Bloquer/Debloquer");
		bloquer.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				bloquerUser();

			}

		});
		
		bloquer.setTranslateY(320);
		bloquer.setTranslateX(20);
		supprimer.setTranslateY(320);

		HBox box = new HBox();
		box.setPadding(new Insets(10, 10, 10, 200));
		box.getChildren().addAll( supprimer, bloquer);
		table.setItems(rechercheAll());
		table.getColumns().addAll( mail, username, statut,type);
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		Pane v = new Pane();

		v.getChildren().addAll(table, box);
		v.setPrefSize(300, 100);
		//scene = new Scene(v, 740, 420);
		
		return v;
	}


	public void supprimerUser() {
		ObservableList<User> customers = table.getSelectionModel().getSelectedItems();
		System.out.println(customers);
		for (User a : customers) {
			UserServiceDelegate.deleteUser(a);
		}

		table.setItems(rechercheAll());

	}

	public void bloquerUser() {
		ObservableList<User> customers = table.getSelectionModel().getSelectedItems();
		for (User a : customers) {
			if (a.getEnabled()) {
				a.setEnabled(false);
			} else {
				a.setEnabled(true);
			}
			UserServiceDelegate.updateUser(a);
		}

		table.setItems(rechercheAll());

	}

	public ObservableList<User> rechercheAll() {

		ObservableList<User> liste = FXCollections.observableArrayList();

		List<User> chams = UserServiceDelegate.findByAll(Dashbord.rechercheChams);

		liste.addAll(chams);

		return liste;
	}
	
	
	

}
