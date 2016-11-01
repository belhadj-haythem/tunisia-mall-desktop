package tunisia.mall.GUI;
import javafx.application.Application;
import javafx.stage.Stage; 
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import tunisia.mall.businessDelegate.VendorServiceDelegate;
import tunisia.mall.persistance.Vendor;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import javafx.stage.Stage;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("restriction")
public class MenuVendor  {

	
	public GridPane start() throws Exception {
		
		GridPane grid = new GridPane();
		
		Button addvendor = new Button("Add Vendor");
		 GridPane.setConstraints(addvendor, 1, 14);
		 addvendor.setMaxSize(120, 50);
		 addvendor.setTranslateX(200);
		 addvendor.setTranslateY(200);
	     
	        addvendor.setStyle("-fx-font-weight : bold;" + "-fx-font-family : Lato;" + "-fx-background-color: \n"
	                + "        #c3c4c4,\n"
	                + "        linear-gradient(#d6d6d6 50%, white 100%),\n"
	                + "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);\n"
	                + "    -fx-background-radius: 30;\n"
	                + "    -fx-background-insets: 0,1,1;\n"
	                + "    -fx-text-fill: #463E3F;\n"
	        );
	    
		addvendor.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				AddVendor a = new AddVendor();
				try {
					DashbordShopOwner.p.setCenter(a.start());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Button listvendor = new Button("List of Vendors");
		 listvendor.setMaxSize(120, 50);
		 listvendor.setTranslateX(200);
		 listvendor.setTranslateY(210);
		 GridPane.setConstraints(listvendor, 1, 14);
	      
	        listvendor.setStyle("-fx-font-weight : bold;" + "-fx-font-family : Lato;" + "-fx-background-color: \n"
	                + "        #c3c4c4,\n"
	                + "        linear-gradient(#d6d6d6 50%, white 100%),\n"
	                + "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);\n"
	                + "    -fx-background-radius: 30;\n"
	                + "    -fx-background-insets: 0,1,1;\n"
	                + "    -fx-text-fill: #463E3F;\n"
	        );
	       
		listvendor.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				ListVendor l = new ListVendor();
				try {
					DashbordShopOwner.p.setCenter(l.start());
					DashbordShopOwner.p.getCenter().setTranslateY(160);
					DashbordShopOwner.p.getCenter().setTranslateX(80);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			
				
				
			
		});
		
		
		
	
		
		
		grid.addRow(1, addvendor);
		grid.addRow(2, listvendor);
		//grid.addRow(3, exit);
		grid.setStyle("-fx-backgroud-color:WHITE");
		grid.setMaxWidth(960);
		grid.setMaxHeight(465);
		return grid;
	}
	
		
}
