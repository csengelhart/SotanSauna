import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class SaunaMain extends Application{

	private Stage stage;
	private Scene scene;
	private BorderPane root;
	
	// buttons
	
	// power
	private Button powerButton;
	private Label powerLabel;
	
	// temp display
	private Button actualTemp;
	private Button targetTemp;
	
	// temp manipulation
	private Button tempUp;
	private Button tempDown;
	private Button tempSet;
	
	// timers
	private Button timer10;
	private Button timer20;
	private Button timer30;
	
	@Override
	public void start(Stage primaryStage)
	{
		this.stage = primaryStage;
		stage.setTitle("SotanSauna");
		this.root = new BorderPane();
		this.scene = new Scene(root,700,500);
		this.powerButton = new Button("POWER");
		
		// event handler
		// when powerButton is pushed, Heating! is printed in label
		this.powerButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				powerLabel.setText("Heating!");
			}
		
		
		});
		
		// actual temp button
		this.actualTemp = new Button("ACTUAL");
		
		// event handler
		
			
		
		// target temp button
		this.targetTemp = new Button("TARGET");
		
		// event handler
		
		
		// temp up button
		this.tempUp = new Button("UP");
		
		// event handler
		// increase target temp by 1F each time clicked
		
		
		// temp down button
		this.tempDown = new Button("DOWN");
		
		// event handler
		// decrease target temp by 1F each time clicked
		
		// set button
		this.tempSet = new Button("SET");
		
		// event handler
		// set target temp when clicked
		
		// 10 min timer
		this.timer10 = new Button("10");
		
		// event handler
		// after setpoint temp is reached, timer starts
		
		// 20 min timer
		this.timer20 = new Button("20");
		
		// event handler
		
		
		// 30 min timer
		this.timer30 = new Button("30");
		
		// event handler
		
		
		// map buttons on scene
		this.powerLabel = new Label();
		this.root.setLeft(powerButton);
		this.root.setBottom(powerLabel);
		
		// create VBox for temp indicators
		VBox tempVbox = new VBox(50); // 50 is the spacing between elements in the VBox
		tempVbox.getChildren().addAll(actualTemp, targetTemp);
		
		// set temp VBox in center of scene
		tempVbox.setAlignment(Pos.CENTER);
		this.root.setCenter(tempVbox);
		
		// create HBox for timers
		HBox timersHbox = new HBox(10);
		timersHbox.getChildren().addAll(timer10,timer20,timer30);
		
		// create HBox for temp controls
		HBox tempControlsHbox = new HBox(10);
		tempControlsHbox.getChildren().addAll(tempUp,tempDown,tempSet);
		
		// create VBox that will contain both HBox for timers and temp controls
		VBox tempControlsVbox = new VBox(50); // 50 is the spacing between elements in the VBox
		tempControlsVbox.getChildren().addAll(timersHbox,tempControlsHbox);
		tempControlsVbox.setAlignment(Pos.CENTER_LEFT);
		this.root.setRight(tempControlsVbox);
		
		
		this.stage.setScene(scene);
		this.stage.show();
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}


}

