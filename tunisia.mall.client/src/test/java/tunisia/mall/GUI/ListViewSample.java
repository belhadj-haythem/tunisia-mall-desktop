package tunisia.mall.GUI;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class ListViewSample extends Application {
    
    public static final ObservableList names = 
        FXCollections.observableArrayList();
    public static final ObservableList data = 
        FXCollections.observableArrayList();
       
  
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("List View Sample");        
        
        final ListView listView = new ListView(data);
        listView.setPrefSize(20, 20);
        listView.setEditable(true);
        
        names.addAll(
             "Remove","Update" );
         
        
            data.add("<");
       
          
        listView.setItems(data);
        listView.setCellFactory(ComboBoxListCell.forListView(names));              
               
        StackPane root = new StackPane();
        root.getChildren().add(listView);
        primaryStage.setScene(new Scene(root, 200, 250));
        primaryStage.show();
    }



public static void main(String[] args) {
    launch(args);
}
}