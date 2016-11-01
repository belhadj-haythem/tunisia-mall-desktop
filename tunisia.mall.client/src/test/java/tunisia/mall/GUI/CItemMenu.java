package tunisia.mall.GUI;

import javafx.scene.layout.Pane;

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
public class CItemMenu {
	static int inter;
	
	public Pane start() throws Exception {
		
		
		
		Pane p = new Pane();
		
		Button btadd = new Button("Add New Top Item");
		btadd.setStyle("-fx-background-color:linear-gradient(#f2f2f2, #d6d6d6),linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%),linear-gradient(#dddddd 0%, #f6f6f6 50%);-fx-background-radius: 8,7,6; -fx-background-insets: 0,1,2; -fx-text-fill: black;-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
	
		btadd.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				inter=1;
				AddCItem aci = new AddCItem();
				try {
					DashbordShopOwner.p.setCenter(aci.start());
				} catch (Exception e) {
					// TODO Auto-generated catch block
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
		
		Button btadd1 = new Button("Add New Button Item");
		btadd1.setStyle("-fx-background-color:linear-gradient(#f2f2f2, #d6d6d6),linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%),linear-gradient(#dddddd 0%, #f6f6f6 50%);-fx-background-radius: 8,7,6; -fx-background-insets: 0,1,2; -fx-text-fill: black;-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
	
		btadd1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				inter=2;
				AddCItem aci = new AddCItem();
				try {
					DashbordShopOwner.p.setCenter(aci.start());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btadd1.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				btadd1.setTranslateY(btadd1.getTranslateY()+1);
				
			}
		});
		
		btadd1.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				btadd1.setTranslateY(btadd1.getTranslateY()-1);
				
			}
		});
		
		Button btadd2 = new Button("Add New Shoes");
		btadd2.setStyle("-fx-background-color:linear-gradient(#f2f2f2, #d6d6d6),linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%),linear-gradient(#dddddd 0%, #f6f6f6 50%);-fx-background-radius: 8,7,6; -fx-background-insets: 0,1,2; -fx-text-fill: black;-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
	
		btadd2.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				inter=3;
				AddCItem aci = new AddCItem();
				try {
					DashbordShopOwner.p.setCenter(aci.start());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btadd2.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				btadd2.setTranslateY(btadd2.getTranslateY()+1);
				
			}
		});
		
		btadd2.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				btadd2.setTranslateY(btadd2.getTranslateY()-1);
				
			}
		});
		
		Button btadd3 = new Button("Add Other Item");
		btadd3.setStyle("-fx-background-color:linear-gradient(#f2f2f2, #d6d6d6),linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%),linear-gradient(#dddddd 0%, #f6f6f6 50%);-fx-background-radius: 8,7,6; -fx-background-insets: 0,1,2; -fx-text-fill: black;-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
	
		btadd3.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				inter=4;
				IAddItem aci = new IAddItem();
				try {
					DashbordShopOwner.p.setCenter(aci.start());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btadd3.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				btadd3.setTranslateY(btadd3.getTranslateY()+1);
				
			}
		});
		
		btadd3.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				btadd3.setTranslateY(btadd3.getTranslateY()-1);
				
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
		vb.getChildren().addAll(btadd,btadd1,btadd2,btadd3,btlist);
		vb.setAlignment(Pos.CENTER);
		vb.setSpacing(40);
		vb.setTranslateX(180);
		vb.setTranslateY(100);
		
		
		
		
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
		//p.setStyle("-fx-background-color: linear-gradient(from 15% 20% to 100% 100%, #006633, #180000 );");
		p.getChildren().addAll(vb);
		p.setMaxWidth(960);
		p.setMaxHeight(465);
		
		
		
		return p;
		
	}

	
}
