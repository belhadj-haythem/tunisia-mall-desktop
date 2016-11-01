package tunisia.mall.GUI;

import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import tunisia.mall.businessDelegate.ItemServiceDelegate;
import tunisia.mall.persistance.Stock;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.Scene;

@SuppressWarnings("restriction")
public class ItemStock {

	
	public GridPane start() throws Exception {
		Scene scene;
		GridPane p = new GridPane();
		p.setHgap(10);
		p.setVgap(5);
		p.setPadding(new Insets(10, 10, 10, 10));
		Label lStock = new Label("Stock :");
		lStock.setStyle("-fx-font-size: 12pt;-fx-font-family: \"Segoe UI Semibold\";-fx-text-fill: Black;");
		Label lStockValue = new Label();
		
		lStockValue.setStyle("-fx-font-size: 12pt;-fx-font-family: \"Segoe UI Semibold\";-fx-text-fill: Black");
		List<Stock> l = ItemServiceDelegate.listStock(ListItems.item.getId());
		for(Stock s:l){
			lStockValue.setText(s.getQuantity()+"");
		}
		p.addRow(2, lStock,lStockValue);
		p.setStyle("-fx-background-color: WHITE;");
		p.setMaxWidth(960);
		p.setMaxHeight(465);
		return p;
		
	}

	
	
}
