package tunisia.mall.GUI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.SimpleStringProperty;
import javafx.stage.Stage;
import javafx.util.Callback;
import tunisia.mall.businessDelegate.ItemServiceDelegate;
import tunisia.mall.persistance.Item;
import tunisia.mall.persistance.Stock;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.cell.TextFieldTableCell;

@SuppressWarnings("restriction")
public class ShoesItemStock {
	int i=0;
	
	TableColumn<String,String> color;
	Stock stock = new Stock();
	
	public VBox start() throws Exception {
		
		
		ObservableList<String> liste = FXCollections.observableArrayList();
		List<Item> lItem = ItemServiceDelegate.searchItemByReference(ListItems.item.getReference(),Connexion.shopKriaa.getId_account());

		
		liste.addAll("35","36","37","38","39","40","41","42","43","44","45","46");
		TableView<String> table = new TableView<>();
		
		table.setEditable(true);
		TableColumn<String,String> size= new TableColumn<>("Size");
		size.setEditable(true);
		size.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<String, String> param) {
				
				return new SimpleStringProperty(param.getValue());
			}
		});
		table.getColumns().add(size);
		for(Item it : lItem){
		 color= new TableColumn<>(it.getColor());
		
		 i++;
		 table.getColumns().add(color);
		}
		
		for(int j=1;j<table.getColumns().size();j++){
			TableColumn<String,String> s= (TableColumn<String, String>) table.getColumns().get(j);
			s.setEditable(true);
			s.setCellFactory(TextFieldTableCell.<String>forTableColumn());
			s.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<String,String>>() {
				
				@Override
				public void handle(CellEditEvent<String, String> event) {
					String quantity = event.getNewValue();
					stock.setQuantity(Integer.parseInt(quantity));
					String size=event.getTableView().getItems().get(event.getTablePosition().getRow());
					System.out.println(size);
					stock.setSize(event.getTableView().getItems().get(event.getTablePosition().getRow()));
					boolean test=false;
					while(!test){
						
					for(Item i :lItem){
					if(i.getColor().equals(s.getText()))	{
						stock.setItem(i);
						//System.out.println(i);
						test=true;
					}
					}
					}	
					ItemServiceDelegate.addStock(stock);
					
				}
			});
		}
	
		table.setItems(liste);
		
		VBox v = new VBox();
		v.setStyle("-fx-background-color:WHITE;");
		v.getChildren().addAll(table);
		
		return v;
		
		
	}
	

}
