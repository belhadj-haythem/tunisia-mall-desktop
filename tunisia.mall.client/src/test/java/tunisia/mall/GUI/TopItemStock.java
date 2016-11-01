package tunisia.mall.GUI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


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
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.cell.TextFieldTableCell;

@SuppressWarnings("restriction")
public class TopItemStock {
	int i=0;
	public int q ;
	public String ss; 
	public int ii;
	TableColumn<String,String> color;
	Stock stock = new Stock();
	
	public VBox start() throws Exception {
		
		
		ObservableList<String> liste = FXCollections.observableArrayList();
		
		List<Item> lItem = ItemServiceDelegate.searchItemByReference(AddCItem.item.getReference(),Connexion.shopKriaa.getId_account());

		
		liste.addAll("XXXS","XXS","XS","S","M","L","XL","XXL");
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
					q = Integer.parseInt(event.getNewValue());
					ss = event.getTableView().getItems().get(event.getTablePosition().getRow());
					
					String quantity = event.getNewValue();
					System.out.println(quantity);
					stock.setQuantity(Integer.parseInt(quantity));
					String size=event.getTableView().getItems().get(event.getTablePosition().getRow());
					System.out.println(size);
					stock.setSize(size);
					boolean test=false;
					Stock cc = new Stock();
					while(!test){
						
					for(Item i :lItem){
					if(i.getColor().equals(s.getText()))	{
						stock.setItem(i);
						System.out.println(i);
						test=true;
						cc.setItem(i);
						
						
					}
					}
					}	
					
					cc.setQuantity(q);
					cc.setSize(ss);
					ItemServiceDelegate.addStock(cc);
					
				}
			});
		}
	
		table.setItems(liste);
		
		VBox v = new VBox();
		v.setStyle("-fx-background-color:WHITE;");
		v.getChildren().addAll(table);
		v.setMaxWidth(960);
		v.setMaxHeight(465);
		return v;
		
		
	}
	

}
