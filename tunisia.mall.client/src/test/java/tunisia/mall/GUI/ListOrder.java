package tunisia.mall.GUI;



import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Font;

import javax.swing.table.TableModel;
import java.util.List;
import java.util.Observable;
import javafx.scene.control.CheckBox;
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
import tunisia.mall.businessDelegate.CartServiceDelegate;
import tunisia.mall.businessDelegate.VendorServiceDelegate;
import tunisia.mall.persistance.Cart;
import tunisia.mall.persistance.Order;
import tunisia.mall.persistance.Vendor;
import javafx.scene.text.Text;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.beans.property.SimpleObjectProperty;

@SuppressWarnings("restriction")
public class ListOrder  {
TableModel listo;
	TableView<Order> table;
	Scene scene;
	CartServiceDelegate cartservice = new CartServiceDelegate();
	List<Order> l = CartServiceDelegate.listOrder();
	ObservableList<Long> checkedMessages = FXCollections
            .observableArrayList(new Long(1));
	
	ObservableList<Order> liste = FXCollections.observableArrayList();

	
	
	public Pane start() throws Exception {
		  //primaryStage.initStyle(StageStyle.UNDECORATED);
		table = new TableView<>();
		table.setEditable(true);
		
//title
	
		 TextField titre = new TextField("List of Orders");
	        titre.setEditable(false);
	        titre.setFocusTraversable(false);
	        GridPane.setConstraints(titre, 0, 0, 11, 1);
	        
	        titre.setPrefSize(320, 20);
	        titre.setStyle("-fx-font-family: \"Cursive\";\n"
	                + "    -fx-font-weight: bold;\n"
	                + "    -fx-background-color: linear-gradient(#FEFFFF, #FEFFFF);"
	                + " -fx-text-fill : #3b5998;" + "-fx-font-size :35px;" + "-fx-text-align: center;");

		
		
		
//adress
		TableColumn<Order, String> adress = new TableColumn<>("adress");
		adress.setEditable(true);
		adress.setMinWidth(120);
		adress.setCellValueFactory((new Callback<CellDataFeatures<Order, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Order, String> p) {

                return new SimpleStringProperty(p.getValue().getAdress());
            }
        }));
				
	
	//Totalcost
	TableColumn<Order, String> cost = new TableColumn<>("total cost");
	cost.setEditable(true);
	cost.setMinWidth(120);
	cost.setCellValueFactory((new Callback<CellDataFeatures<Order, String>, ObservableValue<String>>() {
        public ObservableValue<String> call(CellDataFeatures<Order, String> p) {

            return new SimpleStringProperty(p.getValue().getTotalCost()+"");
        }
    }));
	
	
		
	//Delivery Date
				  TableColumn<Order, String> Date = new TableColumn<>("Date");
			        Date.setEditable(true);
			        Date.setMinWidth(120);
			        Date.setCellValueFactory(new Callback<CellDataFeatures<Order, String>, ObservableValue<String>>() {
			            public ObservableValue<String> call(CellDataFeatures<Order, String> p) {

			                return new SimpleStringProperty(p.getValue().getDate()+"");
			            }
			        });
	
	
			        
	
 //search field
					final TextField searchorder = new TextField();
					searchorder.setPromptText("Enter adress");
					 searchorder.setMaxSize(100, 50);
					 searchorder.setTranslateX(320);
					 searchorder.setTranslateY(-25);
					 
//Button search
					Button search = new Button("search");
					 
					search.setTranslateX(250);
				    search.setTranslateY(0);
				    GridPane.setConstraints(search, 1, 14);
			        search.setStyle("-fx-font-weight : bold;" + "-fx-font-family : Lato;" + "-fx-background-color: \n"
			                + "        #c3c4c4,\n"
			                + "        linear-gradient(#d6d6d6 50%, white 100%),\n"
			                + "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);\n"
			                + "    -fx-background-radius: 30;\n"
			                + "    -fx-background-insets: 0,1,1;\n"
			                + "    -fx-text-fill: #463E3F;\n"
			        );
					
					search.setOnAction(new EventHandler<ActionEvent>(){

			//function search
						@Override
						public void handle(ActionEvent event) {
							 ObservableList<Order> liste = FXCollections.observableArrayList();

						       List<Order> lis= cartservice.findOrderByadress(searchorder.getText());

						        liste.addAll(lis);
							table.setItems(liste);
							
							
						}
						
							
				});
//button Back
			        
					  Button back= new Button("Back");
				        GridPane.setConstraints(back, 1, 14);
				        back.setTranslateX(400);
				        back.setStyle("-fx-font-weight : bold;" + "-fx-font-family : Lato;" + "-fx-background-color: \n"
				                + "        #c3c4c4,\n"
				                + "        linear-gradient(#d6d6d6 50%, white 100%),\n"
				                + "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);\n"
				                + "    -fx-background-radius: 30;\n"
				                + "    -fx-background-insets: 0,1,1;\n"
				                + "    -fx-text-fill: #463E3F;\n"
				        );

				       back.setOnAction(new EventHandler<ActionEvent>() {

				            @Override
				            public void handle(ActionEvent event) {

				            }
				        });
				       
				       
////Modify Salary				       
//				       
//	        salary.setCellFactory(TextFieldTableCell.<Vendor>forTableColumn());
//					salary.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Vendor,String>>() {
//						
//						@Override
//						public void handle(CellEditEvent<Vendor, String> event) {
//							Vendor vendor = event.getTableView().getItems().get(event.getTablePosition().getRow());
//							vendor.setSalary(Integer.parseInt(event.getNewValue()));
//							VendorServiceDelegate.updateVendor(vendor);
//							
//						}
//					});
			        
			        
			//checkbox
				       @SuppressWarnings({ "unchecked", "rawtypes" })
				       TableColumn<Order, CheckBox> chCol = new TableColumn<>("Delivery");
				           
				            chCol.setCellValueFactory( new Callback<CellDataFeatures<Order, CheckBox>, ObservableValue<CheckBox>>() {

				  

								@Override
								public ObservableValue<CheckBox> call(
				                        CellDataFeatures<Order, CheckBox> arg0) {
				                    Order order = arg0.getValue();
				                    CheckBox checkBox = new CheckBox();
				                    if(order.getDelivery()){
				                    	checkBox.selectedProperty().setValue(Boolean.TRUE);
				                    }
				                    else{
				                    	checkBox.selectedProperty().setValue(Boolean.FALSE);
				                    }
				                   // ObservableList<Order> orders = table.getSelectionModel().getSelectedItems();
				                   //for (Order v : orders) {
				                    
				                          // checkBox.selectedProperty().setValue(Boolean.TRUE);
				                          // System.out.println("aaaaaaaaaaaaaaaaaa");
				                        //  v.setDelivery(true); //ici a verifier
				                          // cartservice.update(v);
				                           
				                  //  checkBox.selectedProperty().addListener(listener);
				                    checkBox.setOnAction(new EventHandler<ActionEvent>() {
										
										@Override
										public void handle(ActionEvent event) {
											if(checkBox.isSelected()){
											order.setDelivery(true);
											cartservice.update(order);
											
										}
											else{
												order.setDelivery(false);
												cartservice.update(order);
											}
										}
									});
				                       
				                // }
				                    
				                    
				                    return new SimpleObjectProperty<CheckBox>(checkBox);
				                
				                }
				            
				            
				            });
				            
			        
	
				    table.setPrefSize(800, 300);
					table.setItems(listOrder());
					table.getColumns().addAll(adress,cost,Date,chCol);
					table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);	
					
					back.setTranslateX(340);
					back.setTranslateY(320);
					search.setTranslateX(390);
					search.setTranslateY(320);
					
					searchorder.setTranslateX(460);
					searchorder.setTranslateY(320);
	    Pane v = new Pane();
	   
	   v.getChildren().addAll(titre,table,search,searchorder);
	  // scene = new Scene(v,800,600);
	   		     return v;
	     
			
}
	
	

	
	//list function
	  public ObservableList<Order> listOrder() {
	        ObservableList<Order> liste = FXCollections.observableArrayList();

	       List<Order> lista= cartservice.listOrder();

	                liste.addAll(lista);

	        return liste;
	    }
	  

}