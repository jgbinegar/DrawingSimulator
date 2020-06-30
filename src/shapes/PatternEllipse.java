package shapes;

import javafx.scene.control.Spinner;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;

public class PatternEllipse extends Drawing {
	
	static final int initialX = 60;
	static final int initialY = 30;
	
	Circle centerPoint;
	Circle xRadiusPoint;
	Circle yRadiusPoint;
	Circle rotatePoint;
	
	Ellipse ellipse;
	
	Spinner<Integer> centerX;
	Spinner<Integer> centerY;
	Spinner<Integer> radiusX;
	Spinner<Integer> radiusY;
	
	public PatternEllipse() { super(); }
	
	private void changeWidth(double width) {
		xRadiusPoint.setCenterX(width);
		ellipse.setRadiusX(width - ellipse.getCenterX());
		
		if(ellipse.getRadiusX() < 15) {
			xRadiusPoint.setCenterX(ellipse.getCenterX() + 15);
			ellipse.setRadiusX(15);
		}
		
		updateValues();
	}
	
	private void changeHeight(double height) {
		yRadiusPoint.setCenterY(height);
		ellipse.setRadiusY(height - ellipse.getCenterY());
		
		if(ellipse.getRadiusY() < 15) {
			yRadiusPoint.setCenterY(ellipse.getCenterY() + 15);
			ellipse.setRadiusY(15);
		}
		
		updateValues();
	}
	
	@Override
	protected void rotateShape() {} // TODO: Setup rotation 

	@Override
	protected void updateValues() {
		centerX.getValueFactory().setValue((int)Math.round(ellipse.getCenterX()));
		centerY.getValueFactory().setValue((int)Math.round(ellipse.getCenterY()));
		
		radiusX.getValueFactory().setValue((int)Math.round(ellipse.getRadiusX()));
		radiusY.getValueFactory().setValue((int)Math.round(ellipse.getRadiusY()));
	}

	@Override
	protected void createOptionsPane() {
		
		centerX = createSpinner(0, 1000, 0);
		centerX.pressedProperty().addListener((observable, oldValue, newValue) -> { move(centerX.getValue(), centerY.getValue()); });
		
		centerY = createSpinner(0, 1000, 0);
		centerY.pressedProperty().addListener((observable, oldValue, newValue) -> { move(centerX.getValue(), centerY.getValue()); });
		
		radiusX = createSpinner(0, 1000, 0);
		radiusX.pressedProperty().addListener((observable, oldValue, newValue) -> { changeWidth(ellipse.getCenterX() + radiusX.getValue()); });
		
		radiusY = createSpinner(0, 1000, 0);
		radiusY.pressedProperty().addListener((observable, oldValue, newValue) -> { changeWidth(ellipse.getCenterY() + radiusY.getValue()); });
		
		optionsPane = new VBox();
		optionsPane.getChildren().addAll(createOption("Center X:", centerX),
										createOption("Center Y:", centerY),
										createOption("Radius X:", radiusX),
										createOption("Radius Y:", radiusY));
		
	}

	@Override
	protected void createDrawingPane() {
		drawingPane = new Pane();
		
		drawingPane.setOnMouseClicked(click -> {
			if(ellipse == null)
				createShape(click.getX(), click.getY());
		});
		
	}

	@Override
	protected void createShape(double x, double y) {
		ellipse = new Ellipse(x, y, initialX, initialY);
		ellipse.setStrokeWidth(lineWidth);
		ellipse.setStroke(lineColor);
		ellipse.setFill(polyFill);
		
		centerPoint = createPoint(x, y);
		centerPoint.setOnMouseDragged(drag -> { move(drag.getX(), drag.getY()); });
		
		xRadiusPoint = createPoint(x + initialX, y);
		xRadiusPoint.setOnMouseDragged(drag -> { changeWidth(drag.getX()); });
		
		yRadiusPoint = createPoint(x, y + initialY);
		yRadiusPoint.setOnMouseDragged(drag -> { changeHeight(drag.getY()); });
		
		drawingPane.getChildren().addAll(ellipse, centerPoint, xRadiusPoint, yRadiusPoint);
		updateValues();
	}

	@Override
	protected void move(double x, double y) {
		ellipse.setCenterX(x);
		ellipse.setCenterY(y);
		
		centerPoint.setCenterX(x);
		centerPoint.setCenterY(y);
		
		xRadiusPoint.setCenterX(x + ellipse.getRadiusX());
		xRadiusPoint.setCenterY(y);
		
		yRadiusPoint.setCenterX(x);
		yRadiusPoint.setCenterY(y + ellipse.getRadiusY());
		
		updateValues();
	}

	@Override
	protected String getName() { return "Ellipse"; }





}
