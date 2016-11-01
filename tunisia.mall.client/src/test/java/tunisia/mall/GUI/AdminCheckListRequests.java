package tunisia.mall.GUI;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;
import javax.imageio.ImageIO;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tunisia.mall.businessDelegate.EventServiceDelegate;
import tunisia.mall.persistance.Event;
import tunisia.mall.persistance.Shop;
import tunisia.mall.utils.JavaDropbox;
import javafx.scene.control.Label;

@SuppressWarnings("restriction")
public class AdminCheckListRequests {

	public static int idEvCourantAdmin;
	public static int idSearch;
	
	JavaDropbox dropBox = new JavaDropbox();
	File filedropBox;


	public ScrollPane start() throws Exception {

		List<Event> listev = EventServiceDelegate.ListEvent();

		int ligne = 0;
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(0, 10, 0, 10));
		ScrollPane sp = new ScrollPane();
		grid.getChildren().clear();


        
		for (Event ev : listev) {

			if (!ev.isStatutEv()) {
				
		        /******************************Search**************************************/

				final ComboBox hComboBox = new ComboBox();
				hComboBox.setMaxWidth(120);
				System.out.println(ev.getShopOwner().getNameShop());
		        hComboBox.getItems().add(ev.getShopOwner().getNameShop());
		        grid.add(hComboBox,6,1); 
		        
		        Button search = new Button("    Search Shop    ");
		        search.setStyle("-fx-font-weight : bold;" + "-fx-font-family : Lato;" + "-fx-background-color: \n"
		                + "        #c3c4c4,\n"
		                + "        linear-gradient(#d6d6d6 50%, white 100%),\n"
		                + "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);\n"
		                + "    -fx-background-radius: 30;\n"
		                + "    -fx-background-insets: 0,1,1;\n"
		                + "    -fx-text-fill: #463E3F;\n"
		        );
		        search.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						Shop shop = new Shop();
						String name=hComboBox.getValue().toString();
						shop=EventServiceDelegate.searchShopByName(name);
						idSearch=shop.getId_account();
						System.out.println(idSearch);
						AdminSearchEventByShop aseb = new AdminSearchEventByShop();
						try {
							aseb.start(new Stage());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
		        grid.add(search,7,1); 
		        
		/**************************************************************************/
		        
				//byte[] b = ev.getPicture();
				//ByteArrayInputStream in = new ByteArrayInputStream(b);
//		        File file = null;
//				try {
//					dropBox.authDropbox(JavaDropbox.DROP_BOX_APP_KEY, JavaDropbox.DROP_BOX_APP_SECRET);
//					 file= dropBox.tempFileFromDropbox(ev.getPicture(), "admin", "");
//				} catch (Exception e) {
//					// TODO: handle exception
//				}finally{
//					file.delete();
//				}
//				BufferedImage read = ImageIO.read(file);
//				Image image = SwingFXUtils.toFXImage(read, null);
//				ImageView imageV = new ImageView(image);
//				imageV.setFitHeight(122);
//				imageV.setFitWidth(122);
//				imageV.setEffect(new DropShadow(20, Color.BLACK));
//
//				grid.add(imageV,2, ligne + 3);

				Text evenement = new Text("Shop name: "+ev.getShopOwner().getNameShop()+"\n"+"Title: " + ev.getTitle() + "\n" + "Start date: " + ev.getDateDebut() + "\n"
						+ "End date: " + ev.getDateFin() + "\n" + "Place: " + ev.getPlace());
				evenement.setFont(Font.font("Arial", 20));
				grid.add(evenement,4, ligne + 3);

				Label lblA = new Label("show more details...");
				lblA.setStyle("-fx-font-size: 15 pt; -fx-text-fill: blue;");
				lblA.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {

						AdminShowDetailsEvent se = new AdminShowDetailsEvent();

						idEvCourantAdmin = ev.getId();
						try {
							se.start(new Stage());
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}
				});
				grid.add(lblA,6, ligne + 3);
				ligne++;
			}
		}

		sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		sp.setContent(grid);
		sp.setMaxWidth(960);
		sp.setMaxHeight(465);
		return sp;

	}

}
