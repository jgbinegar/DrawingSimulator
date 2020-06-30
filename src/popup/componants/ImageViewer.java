package popup.componants;

import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class ImageViewer extends ScrollPane {
	
	static StackPane stackPane;
	public static double imageWidth;
	public static double imageHeight;
	
	public ImageViewer(Image image) {
		
		ImageView imageHolder = new ImageView(image);
		
		imageWidth = image.getWidth();
		imageHeight = image.getHeight();
		
		stackPane = new StackPane();
		stackPane.getChildren().add(imageHolder);
		stackPane.getChildren().add(new Pane());
		
		this.setPrefSize(600, 600);
		this.setMinSize(600, 600);
		this.setContent(stackPane);
	}
	
	public void changeDrawing(Pane drawing) {
		stackPane.getChildren().remove(1);
		stackPane.getChildren().add(drawing);
	}

}
