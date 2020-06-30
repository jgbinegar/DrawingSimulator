package popup;

import java.util.ArrayList;

import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import popup.componants.SidePanel;
import popup.componants.ImageViewer;

public class PolygonPopup extends Dialog<String> {
	
	public PolygonPopup(Image fieldImage, ArrayList<String> drawingModes) {
		
		ImageViewer imageViewer = new ImageViewer(fieldImage);
		SidePanel sidePanel = new SidePanel(imageViewer);
		
		BorderPane mainPane = new BorderPane();
		mainPane.setCenter(imageViewer);
		mainPane.setRight(sidePanel);
		
		
		this.getDialogPane().setMinSize(850, 650);
		this.setOnCloseRequest(value -> { setResult(null); } );
		this.getDialogPane().setContent(mainPane);
		this.setTitle("Polygon Popup");
	}
}