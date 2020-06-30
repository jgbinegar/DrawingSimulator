package shapes;

import javafx.scene.control.Spinner;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class PatternCircle extends Drawing {
	
	Circle centerPoint;
	Circle radiusPoint;
	Circle circle;
	
	Spinner<Double> xSpinner; // Spinner that controls the x position of the shape
	Spinner<Double> ySpinner; // Spinner that controls the y position of the shape
	Spinner<Double> rSpinner; // Spinner that controls the radius size of the shape
	
	static final int initialRadius = 30;
	
	public PatternCircle() { super(); }
	
	private void changeRadius(double x) {
		radiusPoint.setCenterX(x);
		circle.setRadius(x - circle.getCenterX());
		updateValues();
		
		if(circle.getRadius() < 15) {
			radiusPoint.setCenterX(circle.getCenterX() + 15);
			circle.setRadius(15);
			updateValues();
		}
	}
	
	@Override
	protected void updateValues() {
		xSpinner.getValueFactory().setValue(circle.getCenterX());
		ySpinner.getValueFactory().setValue(circle.getCenterY());
		rSpinner.getValueFactory().setValue(circle.getRadius());
	}

	@Override
	protected void createOptionsPane() {
		xSpinner = createSpinner(0, 1000, 0);
		xSpinner.pressedProperty().addListener((observable, oldValue, newValue) -> { move(xSpinner.getValue(), ySpinner.getValue()); });
		
		ySpinner = createSpinner(0, 1000, 0);
		ySpinner.pressedProperty().addListener((observable, oldValue, newValue) -> { move(xSpinner.getValue(), ySpinner.getValue()); });
		
		rSpinner = createSpinner(15, 1000, 0);
		rSpinner.pressedProperty().addListener((observable, oldValue, newValue) -> { changeRadius(circle.getCenterX() + rSpinner.getValue()); });
		
		optionsPane = new VBox();
		optionsPane.setPrefWidth(180);
		optionsPane.getChildren().addAll(createOption("Center X:", xSpinner), createOption("Center Y:", ySpinner), createOption("Radius:", rSpinner));	
	}
	
	@Override
	protected void createDrawingPane() {
		drawingPane = new Pane();
		
		drawingPane.setOnMouseClicked(click -> {
			if(circle == null)
				createShape(click.getX(), click.getY());
		});
	}

	@Override
	protected void move(double x, double y) {
		
		centerPoint.setCenterX(x);
		centerPoint.setCenterY(y);
		
		radiusPoint.setCenterX(x + circle.getRadius());
		radiusPoint.setCenterY(y);
		
		circle.setCenterX(x);
		circle.setCenterY(y);
		
		updateValues();
	}

	@Override
	protected void createShape(double x, double y) {
		circle = new Circle(x, y, initialRadius);
		circle.setStrokeWidth(lineWidth);
		circle.setStroke(lineColor);
		circle.setFill(polyFill);
		
		centerPoint = createPoint(x, y);
		centerPoint.setOnMouseDragged(drag -> { move(drag.getX(), drag.getY()); });
		
		radiusPoint = createPoint(x + initialRadius, y);
		radiusPoint.setOnMouseDragged(drag -> { changeRadius(drag.getX()); });
		
		drawingPane.getChildren().addAll(circle, centerPoint, radiusPoint);
		
		updateValues();
		
	}

	@Override
	protected String getName() { return "Circle"; }

	@Override
	protected void rotateShape() {}

}
