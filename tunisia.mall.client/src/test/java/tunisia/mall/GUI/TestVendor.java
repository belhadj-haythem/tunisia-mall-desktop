package tunisia.mall.GUI;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.Scene;

@SuppressWarnings("restriction")
public class TestVendor extends Application{
	 static  BorderPane p = new BorderPane();
	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene;
		
		MenuVendor mv = new MenuVendor();
		
		
		p.setCenter(mv.start());
		
		scene= new Scene(p,800,600);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
		
		
		
	}
public static void main(String[] args) {
	launch(args);
}
}
