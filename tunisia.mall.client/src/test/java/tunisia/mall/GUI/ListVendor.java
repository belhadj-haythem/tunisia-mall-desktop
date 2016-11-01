package tunisia.mall.GUI;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Font;

import java.util.List;
import java.util.Observable;

import javafx.stage.StageStyle;
import javafx.scene.control.TableColumn.CellEditEvent;
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
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import tunisia.mall.businessDelegate.VendorServiceDelegate;
import tunisia.mall.persistance.Vendor;
import javafx.stage.Stage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.text.Text;

@SuppressWarnings("restriction")
public class ListVendor {

	TableView<Vendor> table;

	VendorServiceDelegate vendorservice = new VendorServiceDelegate();
	// a modifier
	List<Vendor> l = VendorServiceDelegate.listVendor(Connexion.shopKriaa.getId_account());

	ObservableList<Vendor> liste = FXCollections.observableArrayList();

	public Pane start() throws Exception {

		table = new TableView<>();
		table.setEditable(true);
		

		// title

		TextField titre = new TextField("List of Vendors");
		titre.setEditable(false);
		titre.setFocusTraversable(false);
		GridPane.setConstraints(titre, 0, 0, 11, 1);

		titre.setPrefSize(320, 20);
		titre.setStyle("-fx-font-family: \"Cursive\";\n" + "    -fx-font-weight: bold;\n"
				+ "    -fx-background-color: linear-gradient(#FEFFFF, #FEFFFF);" + " -fx-text-fill : #3b5998;"
				+ "-fx-font-size :35px;" + "-fx-text-align: center;");

		// firstname
		TableColumn<Vendor, String> firstname = new TableColumn<>("first name");
		firstname.setEditable(true);
		firstname.setMinWidth(120);
		firstname.setCellValueFactory((new Callback<CellDataFeatures<Vendor, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Vendor, String> p) {

				return new SimpleStringProperty(p.getValue().getFirst_name());
			}
		}));

		// lastname
		TableColumn<Vendor, String> lastname = new TableColumn<>("last name");
		lastname.setEditable(true);
		lastname.setMinWidth(120);
		lastname.setCellValueFactory((new Callback<CellDataFeatures<Vendor, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Vendor, String> p) {

				return new SimpleStringProperty(p.getValue().getLast_name());
			}
		}));

		// salary
		TableColumn<Vendor, String> salary = new TableColumn<>("Salary");
		salary.setEditable(true);
		salary.setMinWidth(120);
		salary.setCellValueFactory((new Callback<CellDataFeatures<Vendor, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Vendor, String> p) {

				return new SimpleStringProperty(p.getValue().getSalary() + "");
			}
		}));

		// idCard
		TableColumn<Vendor, String> idCard = new TableColumn<>("id Card");
		idCard.setEditable(true);
		idCard.setMinWidth(120);
		idCard.setCellValueFactory((new Callback<CellDataFeatures<Vendor, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Vendor, String> p) {

				return new SimpleStringProperty(p.getValue().getIdCard() + "");
			}
		}));
		// Hiring Date
		TableColumn<Vendor, String> Date = new TableColumn<>("Date");
		Date.setEditable(true);
		Date.setMinWidth(120);
		Date.setCellValueFactory(new Callback<CellDataFeatures<Vendor, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Vendor, String> p) {

				return new SimpleStringProperty(p.getValue().getDateEmbauche() + "");
			}
		});

		// button remove
		Button delete = new Button("Remove");
		GridPane.setConstraints(delete, 1, 14);
		delete.setTranslateX(47);
		delete.setStyle("-fx-font-weight : bold;" + "-fx-font-family : Lato;" + "-fx-background-color: \n"
				+ "        #c3c4c4,\n" + "        linear-gradient(#d6d6d6 50%, white 100%),\n"
				+ "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);\n"
				+ "    -fx-background-radius: 30;\n" + "    -fx-background-insets: 0,1,1;\n"
				+ "    -fx-text-fill: #463E3F;\n");
		delete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ObservableList<Vendor> vendor = table.getSelectionModel().getSelectedItems();
				for (Vendor a : vendor) {
					vendorservice.removeVendor(a);
				}

				table.setItems(listVendor());

			}

		});
		// search field
		final TextField searchvendor = new TextField();
		searchvendor.setPromptText("Enter first name");
		searchvendor.setMaxSize(100, 50);
		searchvendor.setTranslateX(320);
		searchvendor.setTranslateY(-25);



		// Button search
		Button search = new Button("search");
		searchvendor.setMaxSize(100, 25);
		search.setTranslateX(250);
		search.setTranslateY(0);
		GridPane.setConstraints(search, 1, 14);
		search.setStyle("-fx-font-weight : bold;" + "-fx-font-family : Lato;" + "-fx-background-color: \n"
				+ "        #c3c4c4,\n" + "        linear-gradient(#d6d6d6 50%, white 100%),\n"
				+ "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);\n"
				+ "    -fx-background-radius: 30;\n" + "    -fx-background-insets: 0,1,1;\n"
				+ "    -fx-text-fill: #463E3F;\n");

		search.setOnAction(new EventHandler<ActionEvent>() {

			// function search
			@Override
			public void handle(ActionEvent event) {
				ObservableList<Vendor> liste = FXCollections.observableArrayList();
				// a modifier
				//List<Vendor> lis = vendorservice.findVendorByFname(searchvendor.getText(), Connexion.shopKriaa.getId_account());
				List<Vendor> lis = LuceneSearchVendor.lucene(searchvendor.getText().toLowerCase());
				if(lis.size()!=0){
				liste.addAll(lis);
				table.setItems(liste);

			}
			}
		});
		// button Back

		Button back = new Button("Back");
		GridPane.setConstraints(back, 1, 14);
		back.setTranslateX(400);
		back.setStyle("-fx-font-weight : bold;" + "-fx-font-family : Lato;" + "-fx-background-color: \n"
				+ "        #c3c4c4,\n" + "        linear-gradient(#d6d6d6 50%, white 100%),\n"
				+ "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);\n"
				+ "    -fx-background-radius: 30;\n" + "    -fx-background-insets: 0,1,1;\n"
				+ "    -fx-text-fill: #463E3F;\n");

		back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				MenuVendor m = new MenuVendor();
				try {
					TestVendor.p.setCenter(m.start());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		// Modify Salary

		salary.setCellFactory(TextFieldTableCell.<Vendor> forTableColumn());
		salary.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Vendor, String>>() {

			@Override
			public void handle(CellEditEvent<Vendor, String> event) {
				Vendor vendor = event.getTableView().getItems().get(event.getTablePosition().getRow());
				vendor.setSalary(Integer.parseInt(event.getNewValue()));
				VendorServiceDelegate.updateVendor(vendor);

			}
		});

		table.setItems(listVendor());
		table.setPrefSize(800, 300);
		table.getColumns().addAll(firstname, lastname, salary, idCard, Date);
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		back.setTranslateX(340);
		back.setTranslateY(320);
		search.setTranslateX(390);
		search.setTranslateY(320);
		delete.setTranslateX(260);
		delete.setTranslateY(320);
		searchvendor.setTranslateX(460);
		searchvendor.setTranslateY(320);
		Pane v = new Pane();

		v.getChildren().addAll(titre, table, delete, search, searchvendor, back);
		return v;

	}

	// list function
	public ObservableList<Vendor> listVendor() {
		ObservableList<Vendor> liste = FXCollections.observableArrayList();

		List<Vendor> lista = vendorservice.listVendor(Connexion.shopKriaa.getId_account());

		liste.addAll(lista);

		return liste;
	}

}
