package shapes;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import popup.componants.ImageViewer;

public abstract class Drawing{
	
	
	
	protected static final double circleRadius = 4;
	protected static final double lineWidth = 3;
	
	protected static final Color circleFill = Color.RED;
	protected static final Color lineColor = Color.LIME;
	protected static final Color polyFill = new Color(Color.LIME.getRed(), Color.LIME.getGreen(), Color.LIME.getBlue(), .2);
	
	
	protected String drawingTitle;
	protected Pane drawingPane;
	protected VBox optionsPane;
	
	public Drawing() {
		drawingTitle = getName();
		createDrawingPane();
		createOptionsPane();
	}
	
	protected abstract void rotateShape();
	protected abstract void updateValues();
	protected abstract void createOptionsPane();
	protected abstract void createDrawingPane();
	protected abstract void createShape(double x, double y);
	protected abstract void move(double x, double y);
	protected abstract String getName();

	public Pane getDrawingPane() { return drawingPane; }
	public VBox getOptionsPane() { return optionsPane; }
	
	protected boolean shapeWillBeInXBounds(double left, double right) { return (left >= 0 && right <= ImageViewer.imageWidth); }
	protected boolean shapeWillBeInYBounds(double top, double bottom) { return (top >= 0 && bottom <= ImageViewer.imageHeight); }
	
	protected static HBox createOption(String labelName, Node optionControl) {
		HBox option = new HBox();
		option.setPadding(new Insets(5));
		
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		
		option.getChildren().addAll(new Label(labelName), spacer, optionControl);
		
		return option;
	}
	
	protected static Spinner<Double> createSpinner(double min, double max, double initial) {
		Spinner<Double> s = new Spinner<Double>(min, max, initial, 1.0);
		s.setPrefWidth(80);
		return s;
	}
	
	protected static Circle createPoint(double x, double y) {
		Circle point = new Circle(x, y, 5);
		point.setFill(circleFill);
		return point;
	}
	
	@Override
	public String toString() { return drawingTitle; }
	
}
