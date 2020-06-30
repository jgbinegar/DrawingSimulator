package popup.componants;

import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import shapes.Drawing;
import shapes.PatternBoundingBox;
import shapes.PatternCircle;
import shapes.PatternEllipse;
import shapes.PatternPolygon;

public class SidePanel extends VBox {
	
	// New Design
	
	ComboBox<String> modeSelector;
	Button addButton;
	
	ListView<Drawing> shapesList;
	ObservableList<Drawing> list;
	
	ScrollPane optionsPane;
	
	ImageViewer iv;
	
	public SidePanel(ImageViewer iv) {
		
		this.iv = iv;
		
		HBox modeBox = createModeSelector();
		
		shapesList = createDrawingList();
		
		optionsPane = new ScrollPane();
		
		VBox.setVgrow(optionsPane, Priority.ALWAYS);
		
		this.getChildren().addAll(modeBox, shapesList, optionsPane);
		this.setPadding(new Insets(5));
		this.setPrefSize(200, 500);
	}
	
	public static void changeOptionsPane() {
		
	}
	
	public HBox createModeSelector() {
		ComboBox<String> modeSelector = new ComboBox<String>();
		modeSelector.getItems().addAll("Bounding Box", "Circle", "Ellipse", "Polygon");
		modeSelector.getSelectionModel().select(0);
		modeSelector.setPrefWidth(145);
		
		Region spacer = new Region();
		spacer.setPrefWidth(5);
		
		addButton = new Button("Add");
		addButton.setOnAction(action -> {
			
			Drawing shape = getDrawing(modeSelector.getSelectionModel().getSelectedItem());
			
			iv.changeDrawing(shape.getDrawingPane());
			optionsPane.setContent(shape.getOptionsPane());
			
			list.add(shape);
		});
		
		HBox modeBox = new HBox();
		modeBox.getChildren().addAll(modeSelector, spacer, addButton);
		modeBox.setPadding(new Insets(0,0,5,0));
		
		return modeBox;
	}
	
	public ListView<Drawing> createDrawingList() {
		
		list = FXCollections.observableList(new ArrayList<Drawing>());
		ListView<Drawing> shapesList = new ListView<Drawing>(list);
		shapesList.setPadding(new Insets(0,0,5,0));
		shapesList.setPrefHeight(150);
		
		shapesList.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
			System.out.println("Change");
			Drawing drawing = shapesList.getSelectionModel().getSelectedItem();
			optionsPane.setContent(drawing.getOptionsPane());
			
			iv.changeDrawing(drawing.getDrawingPane());
		});
		
		return shapesList;
	}
	
	private static Drawing getDrawing(String drawing) {
		switch(drawing) {
			case "Bounding Box": return new PatternBoundingBox();
			case "Circle": return new PatternCircle();
			case "Ellipse": return new PatternEllipse();
			case "Polygon": return new PatternPolygon();
			default: return null;
		}
	}
}