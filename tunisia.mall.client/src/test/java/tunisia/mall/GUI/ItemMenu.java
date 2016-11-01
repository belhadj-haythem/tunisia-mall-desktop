package tunisia.mall.GUI;



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;

@SuppressWarnings("restriction")
public class ItemMenu {

	
	public Pane start() throws Exception {
		
		
		Scene scene;
		Pane p = new Pane();
		
		Button btadd = new Button("Add New Item");
		btadd.setStyle("-fx-background-color:linear-gradient(#f2f2f2, #d6d6d6),linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%),linear-gradient(#dddddd 0%, #f6f6f6 50%);-fx-background-radius: 8,7,6; -fx-background-insets: 0,1,2; -fx-text-fill: black;-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
	
		btadd.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				IAddItem iai = new IAddItem();
				try {
					DashbordShopOwner.p.setCenter(iai.start());
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
			}
		});
		btadd.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				btadd.setTranslateY(btadd.getTranslateY()+1);
				
			}
		});
		
		btadd.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				btadd.setTranslateY(btadd.getTranslateY()-1);
				
			}
		});
		Button btlist = new Button("Show All Items");
		btlist.setStyle("-fx-background-color:linear-gradient(#f2f2f2, #d6d6d6),linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%),linear-gradient(#dddddd 0%, #f6f6f6 50%);-fx-background-radius: 8,7,6; -fx-background-insets: 0,1,2; -fx-text-fill: black;-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
		btlist.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				btlist.setTranslateY(btlist.getTranslateY()+1);
				
			}
		});
		
		btlist.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				btlist.setTranslateY(btlist.getTranslateY()-1);
				
			}
		});
		
		
		btlist.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				ListItems li = new ListItems();
				try {
					DashbordShopOwner.p.setCenter(li.start());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
		
		
		VBox vb = new VBox();
		vb.getChildren().addAll(btadd,btlist);
		vb.setAlignment(Pos.CENTER);
		vb.setSpacing(40);
		vb.setTranslateX(175);
		vb.setTranslateY(175);
		
		
		
		
		Button btsearch= new Button("Search");
		btsearch.setStyle("-fx-background-color:linear-gradient(#f2f2f2, #d6d6d6),linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%),linear-gradient(#dddddd 0%, #f6f6f6 50%);-fx-background-radius: 8,7,6; -fx-background-insets: 0,1,2; -fx-text-fill: black;-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
		btsearch.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				btsearch.setTranslateY(btsearch.getTranslateY()+1);
				
			}
		});
		
		btsearch.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				btsearch.setTranslateY(btsearch.getTranslateY()-1);
				
			}
		});
		btsearch.setTranslateX(330);
		btsearch.setTranslateY(305);
		p.getChildren().addAll(vb);
		
		
		//p.setStyle("-fx-background-color: linear-gradient(from 15% 20% to 100% 100%, #006633, #180000 );");
		return p;
		
	}

	
}
