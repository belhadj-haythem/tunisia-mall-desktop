package tunisia.mall.GUI;
import java.util.Date;

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
import javafx.geometry.Insets;
import javafx.stage.Stage;
import java.util.logging.Level;
import java.util.logging.Logger;



@SuppressWarnings("restriction")
public class AddVendor{

	
	
	public GridPane start() throws Exception {
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10,10,10,10));
		Vendor vendor = new Vendor();
		TestVendor testvendor = new TestVendor();
		Text problem = new Text();
		Text problem1 = new Text();
		
		 
		
		 
		
		Label firstName = new Label("first name");
		firstName.setTranslateX(200);
		firstName.setTranslateY(150);
		GridPane.setConstraints(firstName, 0, 5);
		
		final TextField tfirstName = new TextField();
		tfirstName.setTranslateX(200);
		tfirstName.setTranslateY(150);
		tfirstName.setPromptText("Enter first Name");
        GridPane.setConstraints(tfirstName, 1, 5);
		
		Label lastName = new Label("last name");
		lastName.setTranslateX(200);
		lastName.setTranslateY(160);
		GridPane.setConstraints(lastName, 0, 5);
		
		final TextField tlastName = new TextField();
		tlastName.setTranslateX(200);
		tlastName.setTranslateY(160);
		tlastName.setPromptText("Enter last Name");
        GridPane.setConstraints(tlastName, 1, 5);
		
		Label dateLabel = new Label("Hiring Date");
		dateLabel.setTranslateX(200);
		dateLabel.setTranslateY(170);
		GridPane.setConstraints(dateLabel, 0, 6);
		
		final DatePicker date = new DatePicker();
		date.setTranslateX(200);
		date.setTranslateY(170);
		GridPane.setConstraints(date, 1, 6);
		
		Label salaryLabel = new Label("Salary");
		salaryLabel.setTranslateX(200);
		salaryLabel.setTranslateY(180);
		GridPane.setConstraints(salaryLabel, 0, 5);
		
		final TextField tsalary = new TextField();
		tsalary.setTranslateX(200);
		tsalary.setTranslateY(180);
		tsalary.setPromptText("Enter salary");
        GridPane.setConstraints(tsalary, 1, 5);
        
		Label cardLabel = new Label("iden Card");
		cardLabel.setTranslateX(200);
		cardLabel.setTranslateY(190);
		GridPane.setConstraints(cardLabel, 0, 5);
		
		final TextField card = new TextField();
		card.setTranslateX(200);
        card.setTranslateY(190);
		card.setPromptText("Enter salary");
        GridPane.setConstraints(card, 1, 5);
	   
		Button save = new Button("Save");
		 save.setMaxSize(75, 100);
		save.setTranslateX(500);
		save.setTranslateY(210);
		   GridPane.setConstraints(save, 1, 14);
	        
	        save.setStyle("-fx-font-weight : bold;" + "-fx-font-family : Lato;" + "-fx-background-color: \n"
	                + "        #c3c4c4,\n"
	                + "        linear-gradient(#d6d6d6 50%, white 100%),\n"
	                + "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);\n"
	                + "    -fx-background-radius: 30;\n"
	                + "    -fx-background-insets: 0,1,1;\n"
	                + "    -fx-text-fill: #463E3F;\n"
	        );
	        

		save.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
			vendor.setFirst_name(tfirstName.getText());
			vendor.setLast_name(tlastName.getText());
			vendor.setShop(Connexion.shopKriaa);
			 Date a = new Date(50000000);
             a = java.sql.Date.valueOf(date.getValue());
             vendor.setDateEmbauche(a);
             
             String ch= tsalary.getText();
             String ch1 = card.getText();
             
             if(!VendorServiceDelegate.isFloat(ch)){
            	
            	 problem.setText("must be a number");
            	 problem1.setText("");
            	
             }else if(!VendorServiceDelegate.isFloat(ch1)) {
            	 problem1.setText("must be a number");
            	 problem.setText("");
            	 	 
             }else{
             float c = Float.parseFloat(ch);
        	 vendor.setSalary(c);
        	 int c1 = Integer.parseInt(ch1);
        	 vendor.setIdCard(c1);
        		VendorServiceDelegate.addVendor(vendor);
        		Confirm.Confirmer("Vendor added successfuly");
        		 problem.setText("");
        		 problem1.setText("");
             
             }
           
             
             
		
				
			}
		});
		
	//Back button 
		Button back = new Button("Back");
		  GridPane.setConstraints(save, 1, 14);
		  back.setMaxSize(75, 100);
		  back.setTranslateX(200);
			back.setTranslateY(210);

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
			MenuVendor m = new MenuVendor();
			try {
				TestVendor.p.setCenter(m.start());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		});
		
		
		
		
		grid.addRow(1,firstName,tfirstName);
		grid.addRow(2, lastName,tlastName);
		grid.addRow(3, dateLabel,date);
		grid.addRow(4, salaryLabel,tsalary,problem);
		grid.addRow(5, cardLabel,card,problem1);
		grid.addRow(6,save);
		grid.setStyle("-fx-backgroud-color:WHITE");
		grid.setMaxWidth(960);
		grid.setMaxHeight(465);
	return grid;

	
	
	}
	 
	

}
