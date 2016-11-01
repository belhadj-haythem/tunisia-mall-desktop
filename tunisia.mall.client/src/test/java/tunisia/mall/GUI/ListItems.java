package tunisia.mall.GUI;

import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.stage.Stage;
import javafx.util.Callback;
import tunisia.mall.businessDelegate.ItemServiceDelegate;
import tunisia.mall.persistance.Item;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.cell.TextFieldTableCell;

@SuppressWarnings("restriction")
public class ListItems {
	static Item item;

	public VBox start() throws Exception {

		TableView<Item> table = new TableView<>();
		Scene scene;
		table.setEditable(true);

		TableColumn<Item, String> name = new TableColumn<>("Name");
		name.setEditable(true);
		name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Item, String> param) {

				return new SimpleStringProperty(param.getValue().getName());
			}
		});

		TableColumn<Item, String> description = new TableColumn<>("Description");
		description.setEditable(true);
		description.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Item, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Item, String> param) {

						return new SimpleStringProperty(param.getValue().getDescription());
					}
				});

		TableColumn<Item, String> reference = new TableColumn<>("Reference");
		reference.setEditable(true);
		reference.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Item, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Item, String> param) {

						return new SimpleStringProperty(param.getValue().getReference());
					}
				});
		TableColumn<Item, String> price = new TableColumn<>("Price");
		price.setEditable(true);
		price.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Item, String> param) {

				return new SimpleStringProperty(param.getValue().getPrice() + "");
			}
		});
		TableColumn<Item, Button> stock = new TableColumn<>("Stock");
		stock.setEditable(true);
		stock.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, Button>, ObservableValue<Button>>() {

			@Override
			public ObservableValue<Button> call(CellDataFeatures<Item, Button> param) {

				Button btStock = new Button("Show Stock");
				btStock.setStyle("-fx-font-weight : bold;" + "-fx-font-family : Lato;" + "-fx-background-color: \n"
						+ "        #c3c4c4,\n" + "        linear-gradient(#d6d6d6 50%, white 100%),\n"
						+ "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);\n"
						+ "    -fx-background-radius: 30;\n" + "    -fx-background-insets: 0,1,1;\n"
						+ "    -fx-text-fill: #463E3F;\n");
				btStock.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						item = param.getValue();
						if (item.getDisponibility()) {
							SingleItemStock sis = new SingleItemStock();

							try {
								DashbordShopOwner.p.setCenter(sis.start());
							} catch (Exception e) {

								e.printStackTrace();
							}
						}

						else {
							ItemStock is = new ItemStock();
							try {
								DashbordShopOwner.p.setCenter(is.start());
							} catch (Exception e) {

								e.printStackTrace();
							}
						}
					}
				});
				return new SimpleObjectProperty<Button>(btStock);
			}
		});
		name.setCellFactory(TextFieldTableCell.<Item> forTableColumn());
		name.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Item, String>>() {

			@Override
			public void handle(CellEditEvent<Item, String> event) {
				Item item = event.getTableView().getItems().get(event.getTablePosition().getRow());
				item.setName(event.getNewValue());
				ItemServiceDelegate.updateItem(item);

			}
		});

		description.setCellFactory(TextFieldTableCell.<Item> forTableColumn());
		description.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Item, String>>() {

			@Override
			public void handle(CellEditEvent<Item, String> event) {
				Item item = event.getTableView().getItems().get(event.getTablePosition().getRow());
				item.setDescription(event.getNewValue());
				ItemServiceDelegate.updateItem(item);

			}
		});

		reference.setCellFactory(TextFieldTableCell.<Item> forTableColumn());
		reference.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Item, String>>() {

			@Override
			public void handle(CellEditEvent<Item, String> event) {
				Item item = event.getTableView().getItems().get(event.getTablePosition().getRow());
				item.setReference(event.getNewValue());
				ItemServiceDelegate.updateItem(item);

			}
		});
		price.setCellFactory(TextFieldTableCell.<Item> forTableColumn());
		price.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Item, String>>() {

			@Override
			public void handle(CellEditEvent<Item, String> event) {
				Item item = event.getTableView().getItems().get(event.getTablePosition().getRow());
				item.setPrice(Float.parseFloat(event.getNewValue()));
				ItemServiceDelegate.updateItem(item);

			}
		});

		List<Item> l = ItemServiceDelegate.listItems(Connexion.shopKriaa.getId_account());

		List<Item> l1 = new ArrayList<Item>();
		l1.add(l.get(0));
		int j = 0;
		for (int i = 1; i < l.size(); i++) {
			if (l.get(i).getReference().equals(l1.get(i - 1 - j).getReference())) {

				j++;
			} else {
				l1.add(l.get(i));
			}
		}

		AutoCompleteTextField nSearch = new AutoCompleteTextField();
		nSearch.setMaxWidth(150);
		// nSearch.setTranslateX(300);
		Button bSearch = new Button("Search");
		bSearch.setStyle("-fx-font-weight : bold;" + "-fx-font-family : Lato;" + "-fx-background-color: \n"
				+ "        #c3c4c4,\n" + "        linear-gradient(#d6d6d6 50%, white 100%),\n"
				+ "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);\n"
				+ "    -fx-background-radius: 30;\n" + "    -fx-background-insets: 0,1,1;\n"
				+ "    -fx-text-fill: #463E3F;\n");
		bSearch.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ObservableList<Item> liste1 = FXCollections.observableArrayList();

				List<Item> lis = LuceneSearchItem.lucene(nSearch.getText().toLowerCase());
				List<Item> l2 = new ArrayList<Item>();
				if(lis.size()!=0){
				l2.add(lis.get(0));
				int k = 0;
				for (int i = 1; i < lis.size(); i++) {
					if (lis.get(i).getReference().equals(l2.get(i - 1 - k).getReference())) {

						k++;
					} else {
						l2.add(l.get(i));
					}
				}
				liste1.addAll(l2);
				table.setItems(liste1);
			}
			}
		});
		Button bAll = new Button("All Items");
		bAll.setStyle("-fx-font-weight : bold;" + "-fx-font-family : Lato;" + "-fx-background-color: \n"
				+ "        #c3c4c4,\n" + "        linear-gradient(#d6d6d6 50%, white 100%),\n"
				+ "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);\n"
				+ "    -fx-background-radius: 30;\n" + "    -fx-background-insets: 0,1,1;\n"
				+ "    -fx-text-fill: #463E3F;\n");

		ObservableList<Item> liste = FXCollections.observableArrayList();
		liste.addAll(l1);
		table.setItems(liste);
		table.getColumns().addAll(name, description, reference, price, stock);
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		bAll.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				table.setItems(liste);
			}
		});
		VBox v = new VBox();
		HBox h = new HBox();
		h.setAlignment(Pos.CENTER);
		v.setAlignment(Pos.CENTER);
		v.setSpacing(10);
		h.setSpacing(20);
		v.setStyle("-fx-background-color:WHITE;");
		h.getChildren().addAll(nSearch, bSearch, bAll);
		v.getChildren().addAll(table, h);
		v.setMaxWidth(960);
		v.setMaxHeight(465);
		return v;
	}

}
