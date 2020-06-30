package keep;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import popup.componants.ImageViewer;
import popup.componants.SidePanel;

public class DrawingSimulator extends Application {
	
	public static void main(String args[]) { launch(args); }

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Image fieldImage = new Image("/Example FingerPrint.jpg");
		
		ImageViewer imageViewer = new ImageViewer(fieldImage);
		SidePanel sidePanel = new SidePanel(imageViewer);
		
		BorderPane mainPane = new BorderPane();
		mainPane.setCenter(imageViewer);
		mainPane.setRight(sidePanel);
		
		Scene scene = new Scene(mainPane);
		
		primaryStage.setScene(scene);
		
		primaryStage.show();
	}
	
	
}