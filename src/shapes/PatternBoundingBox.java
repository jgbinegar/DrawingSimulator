package shapes;

import javafx.scene.control.Spinner;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import popup.componants.ImageViewer;

public class PatternBoundingBox extends Drawing {
	
	static final int initialX = 60;
	static final int initialY = 30;
	
	Circle cPoint;
	Circle xPoint;
	Circle yPoint;
	Circle rPoint;
	
	Rectangle r;
	
	Spinner<Double> centerX;
	Spinner<Double> centerY;
	Spinner<Double> widthX;
	Spinner<Double> heightY;
	
	public PatternBoundingBox() { super(); }
	
	public void changeWidth(double width) {
		double halfWidth = width / 2;
		
		if(shapeWillBeInXBounds(cPoint.getCenterX() - halfWidth, cPoint.getCenterX() + halfWidth)) {
			r.setWidth(width);
			r.setX(cPoint.getCenterX() - halfWidth);
			xPoint.setCenterX(cPoint.getCenterX() + halfWidth);
			
			if(r.getWidth() < 30) {
				r.setWidth(30);
				r.setX(cPoint.getCenterX() - 15);
				xPoint.setCenterX(cPoint.getCenterX() + 15);
			}
		}
		
		updateValues();
	}
	
	public void changeHeight(double height) {
		double halfHeight = height / 2;
		
		if(shapeWillBeInXBounds(cPoint.getCenterY() - halfHeight, cPoint.getCenterY() + halfHeight)) {
			r.setHeight(height);
			r.setY(cPoint.getCenterY() - halfHeight);
			yPoint.setCenterY(cPoint.getCenterY() + halfHeight);
			
			if(r.getHeight() < 30) {
				r.setHeight(15);
				r.setX(cPoint.getCenterY() - 15);
				yPoint.setCenterY(cPoint.getCenterY() + 15);
			}
		}
		
		updateValues();
	}

	@Override
	protected void rotateShape() {}// TODO: Create rotation

	@Override
	protected void updateValues() {
		centerX.getValueFactory().setValue(cPoint.getCenterX());
		centerY.getValueFactory().setValue(cPoint.getCenterY());
		widthX.getValueFactory().setValue(r.getWidth());
		heightY.getValueFactory().setValue(r.getHeight());
	}

	@Override
	protected void createOptionsPane() {

		centerX = createSpinner(0, 1000, 0);
		
		centerY = createSpinner(0, 1000, 0);
		
		widthX = createSpinner(0, 1000, 0);
		widthX.pressedProperty().addListener((observable, oldValue, newValue) -> {
			if(oldValue)
				changeWidth(widthX.getValue());
			});
		
		heightY = createSpinner(0, 1000, 0);
		heightY.pressedProperty().addListener((observable, oldValue, newValue) -> {
			if(oldValue)
				changeHeight(heightY.getValue());
			});
		optionsPane = new VBox();
		optionsPane.getChildren().addAll(createOption("Center X:", centerX),
										createOption("Center Y:", centerY),
										createOption("Width:", widthX),
										createOption("Height:", heightY));
		
	}

	@Override
	protected void createDrawingPane() {		
		drawingPane = new Pane();
		
		drawingPane.setOnMouseClicked(click -> { if(r == null) createShape(click.getX(), click.getY()); });
	}

	@Override
	protected void createShape(double x, double y) {		
		
		r = new Rectangle(x - (initialX / 2), y - (initialY / 2), initialX, initialY);
		r.setStrokeWidth(lineWidth);
		r.setStroke(lineColor);
		r.setFill(polyFill);
		
		cPoint = createPoint(x, y);
		cPoint.setOnMouseDragged(drag -> { move(drag.getX(), drag.getY()); });
		
		xPoint = createPoint(x + (initialX / 2), y);
		xPoint.setOnMouseDragged(drag -> { changeWidth((drag.getX() - cPoint.getCenterX()) * 2); });
		
		yPoint = createPoint(x, y + (initialY / 2));
		yPoint.setOnMouseDragged(drag -> { changeHeight((drag.getY() - cPoint.getCenterY()) * 2); });
		
		drawingPane.getChildren().addAll(r, cPoint, xPoint, yPoint);
		
		updateValues();
	}

	@Override
	protected void move(double x, double y) {
		double halfWidth = r.getWidth() / 2;
		double halfHeight = r.getHeight() / 2;
		
		if(shapeWillBeInXBounds(x - halfWidth, x + halfWidth)) {
			r.setX(x - halfWidth);
			cPoint.setCenterX(x);
			xPoint.setCenterX(x + halfWidth);
			yPoint.setCenterX(x);
		}
		
		if(shapeWillBeInYBounds(y - halfHeight, y + halfHeight)) {
			r.setY(y - halfHeight);
			cPoint.setCenterY(y);
			xPoint.setCenterY(y);
			yPoint.setCenterY(y + halfHeight);
		}
	}

	@Override
	protected String getName() { return "Bounding Box"; }


}
