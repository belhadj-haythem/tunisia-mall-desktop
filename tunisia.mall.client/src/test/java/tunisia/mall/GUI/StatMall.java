package tunisia.mall.GUI;

import javafx.application.Application;
import javafx.stage.Stage; 
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
//import tunisia.mall.tests.TestVendor;
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
public class StatMall {
	
public static String str;


	
	public Pane start() throws Exception {
		
		Scene scene;
		GridPane grid = new GridPane();
		
		
		Label lidshop = new Label("Enter id Shop  ");
		lidshop.setTranslateX(130);
		lidshop.setTranslateY(200);
		GridPane.setConstraints(lidshop, 0, 5);
		
		
		TextField tidshop = new TextField();
		tidshop.setMaxSize(60, 5);
		tidshop.setTranslateX(130);
		tidshop.setTranslateY(200);
		
		 Button show = new Button(" Show statistic");
		 show.setMaxSize(100, 20);
		 show.setTranslateX(160);
		 show.setTranslateY(250);
		 GridPane.setConstraints(show, 1, 14);
	        
	        show.setStyle("-fx-font-weight : bold;" + "-fx-font-family : Lato;" + "-fx-background-color: \n"
	                + "        #c3c4c4,\n"
	                + "        linear-gradient(#d6d6d6 50%, white 100%),\n"
	                + "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);\n"
	                + "    -fx-background-radius: 30;\n"
	                + "    -fx-background-insets: 0,1,1;\n"
	                + "    -fx-text-fill: #463E3F;\n"
	        );
	        show.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					 str = tidshop.getText();
					TestStatistique stat = new TestStatistique();
					try {
						stat.start(new Stage());
						//primaryStage.hide();
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
		 
		
		
		
		
		
		grid.addRow(1, lidshop,tidshop);
		grid.addRow(2, show);
		grid.setMaxWidth(960);
		grid.setMaxHeight(465);
		return grid;
	    
	     
		
		
		
	}
	
	public String str(){
		return str;
	}

	

}
