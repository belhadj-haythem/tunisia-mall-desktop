package tunisia.mall.GUI;

import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

import java.util.Random;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import tunisia.mall.businessDelegate.ShopServiceDelegate;
import tunisia.mall.persistance.Shop;

public class MapTunisiaMall {

	private ImageView imageView;
	private ScrollPane scrollPane;
	private DoubleProperty zoom;

	
	public ScrollPane start() throws Exception {
		zoom = new SimpleDoubleProperty(200);
		InvalidationListener listener = new InvalidationListener() {
			@Override
			public void invalidated(Observable arg0) {
				imageView.setFitWidth(zoom.get() * 5);
				imageView.setFitHeight(zoom.get() * 4);
				scrollPane.requestLayout();
			}
		};
		Label newShop = new Label() ;
		Rectangle rect = new Rectangle(0, 0, 100, 100);
		Tooltip t = new Tooltip("A Square");
		Tooltip.install(rect, t);

		zoom.addListener(listener);
		Label w = new Label("ZARA");
		Tooltip ww = new Tooltip();
		
		//newShop.setText(ee.getNameShop());
		
		Tooltip ww1 = new Tooltip();
		ww.setText("ZARA SHOP");
		w.setTooltip(ww);
		ww1.setText("CARREFOUR MARKET SHOP");
		//newS.setStyle(
			//	"-fx-text-fill : #000000 ; -fx-background-radius: 7 7 7 7;-fx-background-color: linear-gradient(#e2ecfe, #99bcfd);");
		ww1.setStyle(
				"-fx-text-fill : #000000 ; -fx-background-radius: 7 7 7 7;-fx-background-color: linear-gradient(#e2ecfe, #99bcfd);");
		ww.setStyle(
				"-fx-text-fill : #000000 ; -fx-background-radius: 7 7 7 7;-fx-background-color: linear-gradient(#e2ecfe, #99bcfd);");
		Label w1 = new Label("CARREFOUR MARKET");
		//newShop.setTooltip(newS);
		newShop.setTranslateX(new Random().nextInt(510+1+80)); //(max - min) + 1+min
		newShop.setTranslateY(- 450);
		w1.setTooltip(ww1);
		w1.setTranslateX(80);
		w1.setTranslateY(-350);
		w.setTranslateX(750);
		w.setTranslateY(-350);
		scrollPane = new ScrollPane() {
			@Override
			protected void layoutChildren() {
				super.layoutChildren();
				System.out.println("Zoom=" + zoom.doubleValue() + " Bounds=" + getViewportBounds());
			}
		};
		scrollPane.setPannable(true);
		scrollPane.addEventFilter(ScrollEvent.ANY, new EventHandler<ScrollEvent>() {
			@Override
			public void handle(ScrollEvent event) {
				if (event.getDeltaY() > 0) {
					zoom.set(zoom.get() * 1.1);
				} else if (event.getDeltaY() < 0) {
					zoom.set(zoom.get() / 1.1);
				}
				event.consume();
			}
		});
		Image img = new Image("file:///C:/workspace/tunisia.mall.client/Images/mall.jpg");
		imageView = new ImageView(img) {
			{
				setPreserveRatio(true);
			}
		};
		scrollPane.setContent(imageView);

		Button reset = new Button("Reset");

		reset.setOnAction(e -> reset(imageView, zoom.get() * 4, zoom.get() * 3));
		VBox v = new VBox();
		reset.setTranslateX(400);
		v.getChildren().addAll(reset, imageView);

		scrollPane.setContent(v);

		BackgroundSize bgS = new BackgroundSize(780, 460, false, false, false, false);
		Background bg = new Background(new BackgroundImage(
				new Image("file:///C:/workspace/tunisia.mall.client/Images/b.png"), NO_REPEAT, NO_REPEAT, null, bgS));
		scrollPane.setBackground(bg);

		imageView.setFitWidth(zoom.get() * 4.5);
		imageView.setFitHeight(zoom.get() * 3.5);
		scrollPane.setHvalue(scrollPane.getHmax() - 100);
		scrollPane.setVvalue(scrollPane.getVmax() - 140);
		Scene scene = new Scene(scrollPane, 900, 500);
	
		 return scrollPane;
	}

	private void reset(ImageView imageView, double width, double height) {
		imageView.setViewport(new Rectangle2D(0, 0, width, height));
	}


}