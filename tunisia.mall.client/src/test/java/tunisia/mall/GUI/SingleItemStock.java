package tunisia.mall.GUI;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.VBox;
import javafx.beans.property.SimpleStringProperty;
import javafx.stage.Stage;
import javafx.util.Callback;
import tunisia.mall.businessDelegate.ItemServiceDelegate;
import tunisia.mall.persistance.Item;
import tunisia.mall.persistance.Stock;

@SuppressWarnings("restriction")
public class SingleItemStock {
	TableColumn<String[], String> size;
	int i = 0;

	int k;
	TableColumn<String[], String>s;

	
	public VBox start() throws Exception {
		
		TableView<String[]> table = new TableView<>();
		
		List<Item> lItem = ItemServiceDelegate.searchItemByReference(ListItems.item.getReference(), Connexion.shopKriaa.getId_account());
		List<Stock> lStock = ItemServiceDelegate.listStock(ListItems.item.getId());

		TableColumn<String[], String> color = new TableColumn<>("Color");
		
		
		color.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<String[], String> param) {
						
						return new SimpleStringProperty(param.getValue()[0]);
					
		 
					}
				});
		
		table.getColumns().add(color);

		for (Stock stock : lStock) {
			size = new TableColumn<>(stock.getSize());
			i++;
			table.getColumns().add(size);
		}

		ObservableList<String[]> liste = FXCollections.observableArrayList();
		
	
		
		
		
		
		for (Item it : lItem) {

			List<Stock> lst = ItemServiceDelegate.listStock(it.getId());
			String[] aa = new String[500];
			aa[0] = it.getColor();
			k=1;
			do {
				
				s = (TableColumn<String[], String>) table.getColumns().get(k);
				
				for (Stock stock : lst) {
					
					if (s.getText().equals(stock.getSize())) {

						aa[k] = stock.getQuantity() + "";
						
						k++;
					
						s.setCellValueFactory(
								new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {

									@Override
									public ObservableValue<String> call(CellDataFeatures<String[], String> param) {
										for(int w =0; w<table.getColumns().size()-1;w++){
											
										s = (TableColumn<String[], String>) table.getColumns().get(w+1);
										if (s.getText().equals(stock.getSize())){
											
										return new SimpleStringProperty(param.getValue()[w+1]);
										
									}
										
									}
										return new SimpleStringProperty(param.getValue()[2]);
									}
								
								});
						
					}
				}
				
			} while (k < (table.getColumns().size() ));
			
			
			
			liste.add(aa);
			
		}
		
		
		
		ObservableList<String[]> listeInv = FXCollections.observableArrayList();

		table.setItems(liste);

		VBox v = new VBox();
		v.setStyle("-fx-background-color:WHITE;");
		v.getChildren().addAll(table);
		v.setMaxWidth(960);
		v.setMaxHeight(465);
		return v;
	}

	
}
