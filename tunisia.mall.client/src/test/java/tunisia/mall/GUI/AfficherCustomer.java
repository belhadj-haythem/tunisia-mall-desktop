package tunisia.mall.GUI;

import javafx.application.*;
import javafx.stage.Stage;
import javafx.scene.control.cell.TextFieldTableCell;
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

import org.hibernate.Session;
import org.hibernate.engine.HibernateIterator;
import org.hibernate.metamodel.source.hbm.HibernateMappingProcessor;

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
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import tunisia.mall.businessDelegate.UserServiceDelegate;
import tunisia.mall.persistance.Customer;
import tunisia.mall.persistance.User;

@SuppressWarnings("restriction")
public class AfficherCustomer {

	// --------------------------

	TableView<Customer> table;
	Scene scene;
	Customer a = new Customer();
	Stage window;

	
	public Pane start() throws Exception {
		table = new TableView<>();
		table.setEditable(true);
		table.setPrefSize(620, 300);
		//window = primaryStage;

		// CustomPasswordField a = new CustomPasswordField();

		// colonne nom
		TableColumn<Customer, String> nom = new TableColumn<>("Last Name");
		nom.setEditable(true);
		nom.setMinWidth(120);
		nom.setCellValueFactory(new Callback<CellDataFeatures<Customer, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Customer, String> p) {

				return new SimpleStringProperty(p.getValue().getLast_name());
			}
		});

		// colonne prenom
		TableColumn<Customer, String> prenom = new TableColumn<>("First Name");
		prenom.setEditable(true);
		prenom.setMinWidth(120);
		prenom.setCellValueFactory(new Callback<CellDataFeatures<Customer, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Customer, String> p) {

				return new SimpleStringProperty(p.getValue().getFisrt_name());
			}
		});

		// colonne Email
		TableColumn<Customer, String> mail = new TableColumn<>("Email");
		mail.setEditable(true);
		mail.setMinWidth(140);
		mail.setCellValueFactory(new Callback<CellDataFeatures<Customer, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Customer, String> p) {

				return new SimpleStringProperty(p.getValue().getEmail());
			}
		});
		// colonne usename
		TableColumn<Customer, String> Customername = new TableColumn<>("Username");
		Customername.setEditable(true);
		Customername.setMinWidth(120);
		Customername.setCellValueFactory(new Callback<CellDataFeatures<Customer, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Customer, String> p) {

				return new SimpleStringProperty(p.getValue().getUsername());
			}
		});

		// colonne statut
		TableColumn<Customer, String> statut = new TableColumn<>("Statut");
		statut.setEditable(true);
		statut.setMinWidth(120);
		statut.setCellValueFactory(new Callback<CellDataFeatures<Customer, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Customer, String> p) {

				if (p.getValue().getEnabled()) {
					return new SimpleStringProperty("Enabled");
				} else {
					return new SimpleStringProperty("Disabled");
				}
			}
		});

		// modifier nom
		nom.setCellFactory(TextFieldTableCell.<Customer> forTableColumn());

		nom.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Customer, String>>() {

			public void handle(TableColumn.CellEditEvent<Customer, String> event) {
				Customer a = event.getTableView().getItems().get(event.getTablePosition().getRow());
				a.setLast_name(event.getNewValue());
				UserServiceDelegate.updateCustomer(a);
			}
		});

		// modifier prenom
		prenom.setCellFactory(TextFieldTableCell.<Customer> forTableColumn());

		prenom.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Customer, String>>() {

			public void handle(TableColumn.CellEditEvent<Customer, String> event) {
				Customer a = event.getTableView().getItems().get(event.getTablePosition().getRow());
				a.setFisrt_name(event.getNewValue());
				UserServiceDelegate.updateCustomer(a);
			}
		});

		

		// bouton supprimer
		
		Button supprimer = new Button("Supprimer");
		supprimer.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				supprimerCustomer();

			}

		});

		// bouton bloquer
		Button bloquer = new Button("Bloquer/Debloquer");
		bloquer.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				bloquerCustomer();

			}

		});
		bloquer.setTranslateY(320);
		bloquer.setTranslateX(20);
		supprimer.setTranslateY(320);
		HBox box = new HBox();
		box.setPadding(new Insets(10, 10, 10, 200));
		box.getChildren().addAll( supprimer, bloquer);
		table.setItems(listCustomer());
		table.getColumns().addAll(prenom, nom, mail, Customername, statut);
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		Pane v = new Pane();
		v.setPrefSize(300, 100);
		v.getChildren().addAll(table, box);
		v.setMaxWidth(960);
		v.setMaxHeight(465);
		//scene = new Scene(v, 740, 420);
		return v;	
		
	}


	public void supprimerCustomer() {
		ObservableList<Customer> customers = table.getSelectionModel().getSelectedItems();
		System.out.println(customers);
		for (Customer a : customers) {
			UserServiceDelegate.deleteCustomer(a);
		}

		table.setItems(listCustomer());

	}

	public void bloquerCustomer() {
		ObservableList<Customer> customers = table.getSelectionModel().getSelectedItems();
		for (Customer a : customers) {
			if (a.getEnabled()) {
				a.setEnabled(false);
			} else {
				a.setEnabled(true);
			}
			UserServiceDelegate.updateCustomer(a);
		}

		table.setItems(listCustomer());

	}

	public ObservableList<Customer> listCustomer() {

		ObservableList<Customer> liste = FXCollections.observableArrayList();

		List<Customer> chams = UserServiceDelegate.listCustomer();

		liste.addAll(chams);

		return liste;
	}

}
