package tunisia.mall.GUI;

import java.io.FileOutputStream;

import java.io.IOException;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.application.*;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tunisia.mall.businessDelegate.ItemServiceDelegate;
import tunisia.mall.persistance.Item;

@SuppressWarnings("restriction")
public class itextpdf extends Application {

	String defaultFileName = "Catalogue.pdf";

	public static void main(String[] args) {
		launch(args);
	}

	public void createPdf(String dest) throws IOException, DocumentException {

		String reference = null;

		List<Item> items = ItemServiceDelegate.listItems(AfficherShop.idChams);
		Document document = new Document(PageSize.A4);
		// Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));

		for (Item i : items) {
			if (!i.getReference().equals(reference)) {

				if (reference != null) {
					document.newPage();

				}

				byte[] IMAGE = i.getPhoto();
				document.open();
				document.add(new Paragraph(i.getName(),new Font(FontFamily.TIMES_ROMAN,30)));
				document.add(new Paragraph(i.getPrice() + " Dt",new Font(FontFamily.TIMES_ROMAN,30)));
				document.add(new Paragraph(i.getDescription(),new Font(FontFamily.TIMES_ROMAN,30)));

				PdfContentByte canvas = writer.getDirectContentUnder();

				Image image = Image.getInstance(IMAGE);
				image.scaleAbsolute(PageSize.A4.getWidth(), PageSize.A4.getHeight());
				image.setAbsolutePosition(0, 0);
				canvas.addImage(image);
				reference = i.getReference();

			}

		}
		document.close();

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Button catalogue = new Button("Catalogue");
		catalogue.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Save file");
				fileChooser.setInitialFileName(defaultFileName);
				File savedFile = fileChooser.showSaveDialog(null);

				if (savedFile != null) {

					try {
						File file = new File(savedFile.getAbsolutePath());
						file.getParentFile().mkdirs();
						new itextpdf().createPdf(savedFile.getAbsolutePath());
						primaryStage.close();
					} catch (IOException e) {

						e.printStackTrace();

					} catch (DocumentException e) {
						//
						e.printStackTrace();
					}
				}

			}
		});

		GridPane grid = new GridPane();

		grid.getChildren().add(catalogue);
		Scene scene = new Scene(grid, 200, 200);
		primaryStage.setScene(scene);
		primaryStage.show();

	}
}