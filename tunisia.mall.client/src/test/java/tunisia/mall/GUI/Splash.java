package tunisia.mall.GUI;


import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.concurrent.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.util.Duration;

@SuppressWarnings("restriction")
public class Splash extends Application {

    public static final String APPLICATION_ICON
            = "file:///C:/workspace/tunisia.mall.client/Images/tm.jpg";
    public static final String SPLASH_IMAGE
            = "file:///C:/workspace/tunisia.mall.client/Images/3.png";

    private Pane splashLayout;
    private ProgressBar loadProgress;
    private Label progressText;
    private Stage mainStage;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void init() {
        ImageView splash = new ImageView(new Image(
                SPLASH_IMAGE
        ));
        loadProgress = new ProgressBar();
        loadProgress.setPrefWidth(802);
        progressText = new Label();
        splashLayout = new VBox();
        splashLayout.getChildren().addAll(splash, loadProgress, progressText);
        progressText.setAlignment(Pos.CENTER);
        splashLayout.setStyle(
                "-fx-padding: 3; "
                + "-fx-background-color: #FFFFFF; "
                + "-fx-border-width:3; "
                + "-fx-border-color: #E5E4E2"
        );
        splashLayout.setEffect(new DropShadow());
    }

    @Override
    public void start(final Stage initStage) throws Exception {
        final Task<ObservableList<String>> charity = new Task<ObservableList<String>>() {
            @Override
            protected ObservableList<String> call() throws InterruptedException {
                ObservableList<String> compteur
                        = FXCollections.observableArrayList(
                                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
                        );
                updateTitle("Charity Land");
                for (int i = 0; i < compteur.size() + 3; i++) {
                    Thread.sleep(400);
                    updateProgress(i + 1, compteur.size() - 1);

                    updateMessage("                                                                                                                 Soyez prêts..");
                }
                updateMessage("                                                                                                                 Lancez vous !");
                Thread.sleep(400);

                return compteur;
            }
        };

        showSplash(initStage, charity, new InitCompletionHandler() {

            public void complete() {

                showMainStage(charity.valueProperty());
            }
        });
        new Thread(charity).start();
    }

    private void showMainStage(
            ReadOnlyObjectProperty<ObservableList<String>> monObjet
    ) {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException ex) {
//            System.out.println("Erreur");
//        }
        mainStage = new Stage(StageStyle.DECORATED);
        mainStage.getIcons().add(new Image(
                APPLICATION_ICON
        ));
        Connexion c = new Connexion();
        try {
            c.start(mainStage);
        } catch (Exception ex) {
            System.out.println("Erreur");
        }
       
    }

    private void showSplash(
            final Stage initStage,
            Task<?> task,
            final InitCompletionHandler initCompletionHandler
    ) {
        progressText.textProperty().bind(task.messageProperty());
        loadProgress.progressProperty().bind(task.progressProperty());
        task.stateProperty().addListener(new ChangeListener<Worker.State>() {

            public void changed(ObservableValue<? extends Worker.State> ov, Worker.State t, Worker.State t1) {

                if (t1 == Worker.State.SUCCEEDED) {
                    loadProgress.progressProperty().unbind();
                    loadProgress.setProgress(1);
                    initStage.toFront();
                    FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1.0), splashLayout);
                    fadeSplash.setFromValue(4.0);
                    fadeSplash.setToValue(0.0);
                    fadeSplash.setOnFinished(new EventHandler<ActionEvent>() {

                        public void handle(ActionEvent event) {
                            
                            initStage.hide();
                            
                        }
                    });
                    fadeSplash.play();

                    initCompletionHandler.complete();
                }
            }
        });

        Scene splashScene = new Scene(splashLayout);
        initStage.initStyle(StageStyle.UNDECORATED);
        //final Rectangle2D bounds = Screen.getPrimary().getBounds();
        initStage.setScene(splashScene);
        initStage.setX(230);
        initStage.setY(120);
        initStage.show();
    }

    public interface InitCompletionHandler {

        public void complete();
    }
}