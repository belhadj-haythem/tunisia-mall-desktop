package tunisia.mall.GUI;
import javafx.application.*;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.Properties;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.stage.StageStyle;
import tunisia.mall.businessDelegate.UserServiceDelegate;
import tunisia.mall.persistance.User;




@SuppressWarnings("restriction")
public class RecupererPwd extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {

		
        GridPane grid = new GridPane();        
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(20);
        
        
        

        TextField titre = new TextField("Forgot your password ?");
        titre.setEditable(false);
        titre.setFocusTraversable(false);
        GridPane.setConstraints(titre, 1, 0,8,1);
        //titre.setTranslateX(-70);
        titre.setPrefSize(250, 20);
        titre.setStyle("-fx-font-family: \"Cursive\";\n"
                + "    -fx-font-weight: bold;\n"
                + "    -fx-background-color: linear-gradient(#FEFFFF, #FEFFFF);"
                + " -fx-text-fill : #3b5998;" + "-fx-font-size :15px;" + "-fx-text-align: center;");
        
        Label text = new Label("To recover your password, type the full address email or username");
        GridPane.setConstraints(text, 0, 1, 13, 1);

        Label emailLabel = new Label("Email/Username :");
        GridPane.setConstraints(emailLabel, 0, 4,3,1);

        final TextField email = new TextField();
        email.setPromptText("Enter your Email or Username");
        GridPane.setConstraints(email, 2, 4,9,1);
        

        primaryStage.initStyle(StageStyle.UNDECORATED);



        Button envoyer = new Button("Envoyer");
        GridPane.setConstraints(envoyer, 1, 8);
        envoyer.setPrefSize(60, 20);
        envoyer.setTranslateX(180);

        
		
        envoyer.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				User user=UserServiceDelegate.recuppererMotDePasse(email.getText());
				
				if (user!=null){
					String Pwd = "Votre mot de passe est :\n    "+user.getPassword();
					
					try {
						SimpleMail.envoyerMail(user.getEmail(), "aa", Pwd,"Password Recovery");
						Confirm confirm = new Confirm();
						confirm.Confirmer("Your password has been send ");
					} catch (Exception e) {
						Alerte alerte = new Alerte();
						alerte.alerte("Please check your connection");
					}
				}else{
					Alerte alerte = new Alerte();
					alerte.alerte("Please verify your email address or username ");
				}
								
			}
		});
		
        Button annuler = new Button("Annuler");
        GridPane.setConstraints(annuler, 1, 8);
        annuler.setTranslateY(0);
        annuler.setTranslateX(250);
        annuler.setPrefSize(60, 20);
        
        annuler.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	primaryStage.hide();
            }
        });
        
        
        Hyperlink question = new Hyperlink("More question ?");
        GridPane.setConstraints(question, 2, 10, 6, 1);
        
        question.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				primaryStage.hide();
				MailAdmin mailAdmin = new MailAdmin();
				try {
					mailAdmin.start(new Stage());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
        

        grid.getChildren().addAll(titre,text, emailLabel, email, envoyer, annuler, question);

        //BorderPane p = new BorderPane();
       // BackgroundSize bgS = new BackgroundSize(376, 280, false, false, false, false);
       // Background bg = new Background(new BackgroundImage(new Image(""), NO_REPEAT, NO_REPEAT, null, bgS));
        //p.setBackground(bg);
        //p.setLeft(grid);

        Scene scene = new Scene(grid, 376, 280);
        primaryStage.setScene(scene);
        primaryStage.show();
		
		
		
		
		
		
		
	}
	public static void main(String[] args){
		launch(args);
	}

}
