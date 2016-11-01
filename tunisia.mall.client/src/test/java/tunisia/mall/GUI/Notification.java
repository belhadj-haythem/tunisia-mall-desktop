package tunisia.mall.GUI;

import javafx.event.EventHandler;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author PC-ASUS
 */
@SuppressWarnings("restriction")
public class Notification extends Application {
	public static int aaa=5000;
    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception {
       
    	window = primaryStage;
        GridPane grid = new GridPane();
        window.setTitle("!!!!!!New Notification!!!!!");
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(20);

        TextField notif = new TextField("    you have new notification");
        notif.setEditable(false);
        notif.setFocusTraversable(false);
        GridPane.setConstraints(notif, 1, 0);
        notif.setTranslateX(-180);
        notif.setPrefSize(200, 20);
        notif.setStyle("-fx-font-family: \"Cursive\";\n"
                + "    -fx-font-weight: bold;\n"
                + "    -fx-background-color: linear-gradient(#FEFFFF, #FEFFFF);"
                + " -fx-text-fill : #3b5998;" + "-fx-font-size :12px;" + "-fx-text-align: center;");

        primaryStage.initStyle(StageStyle.UNDECORATED);


        Button Plutard = new Button("Check requests now");
        Plutard.setPrefSize(160, 20);
        GridPane.setConstraints(Plutard, 0, 9);
        Plutard.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	AdminCheckListRequests ad = new AdminCheckListRequests();
            	
            	aaa = 1;
            	Dashbord a = new Dashbord();
            	try {
            		
					a.start(new Stage());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	try {
					Connexion.aaa.window.hide();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				window.hide();
            	//System.exit(0);
            	
            }
        });

        Button Repondre = new Button("Check requests later");
        Repondre.setPrefSize(160, 20);
        GridPane.setConstraints(Repondre, 1, 9);
        Repondre.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	aaa = 0;
            	Dashbord a = new Dashbord();
            	try {
					a.start(new Stage());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	try {
					Connexion.aaa.window.hide();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	 primaryStage.hide();
            }
        });

        grid.getChildren().addAll(notif,Plutard,Repondre);

        BorderPane p = new BorderPane();
        BackgroundSize bgS = new BackgroundSize(376, 280, false, false, false, false);
        Background bg = new Background(new BackgroundImage(new Image("file:///C:/workspace/tunisia.mall.client/Images/tm.jpg"), NO_REPEAT, NO_REPEAT, null, bgS));
        p.setBackground(bg);
        p.setLeft(grid);
        Scene scene = new Scene(p, 376, 280);

        window.setScene(scene);
        window.setX(990);
        window.setY(448);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
