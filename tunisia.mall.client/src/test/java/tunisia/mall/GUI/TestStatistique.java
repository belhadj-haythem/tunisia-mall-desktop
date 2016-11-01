package tunisia.mall.GUI;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



@SuppressWarnings("restriction")
public class TestStatistique extends Application{

    Stage window;
    @Override
    public void start( Stage primaryStage) throws Exception {

        window=primaryStage;
        Statistique s = new Statistique();
        StatMall ss= new StatMall();
        
        Pane p = new Pane();
        
        int i = Integer.parseInt(ss.str);
        
                 PieChart chart = new PieChart(s.stat());
                   chart.setTitle("Productivity");
                   chart.setTranslateX(100);
                   
                Button back = new Button("X");
                back.setMaxSize(100, 20);
       		 back.setTranslateX(670);
       		 back.setTranslateY(1);
       		 GridPane.setConstraints(back, 1, 14);
       	        
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
                
                   
       	     back.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					primaryStage.hide();
				}
			});
       	     
                   
                   
         primaryStage.initStyle(StageStyle.UNDECORATED);
        p.getChildren().add(chart);
        p.getChildren().add(back);
        Scene scene = new Scene(p,700,700);
        primaryStage.setScene(scene);
        primaryStage.show();
       
    }
    public static void main(String[] args) {
	       launch(args);
	   }
		

}
