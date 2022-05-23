import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SaunaMain extends Application{

	// properties
	private Stage stage;
	private Scene scene;
	private BorderPane root;
	
	
	// buttons
	private Button powerButton;
	private Label powerLabel;
	
	// temp indicators
	private Button actualTemp;
	private Button targetTemp;
	
	// timers
	private Button timer10;
	private Button timer20;
	private Button timer30;
	
	// temp controls
	private Button tempUp;
	private Button tempDown;
	private Button tempSet;
	
	@Override
	public void start(Stage primaryStage)
	{
		this.stage = primaryStage;
		stage.setTitle("SotanSauna");
		this.root = new BorderPane();
		


		// create buttons and event handlers
		
		this.scene = new Scene(root, 700,500);
		
		// power button
		this.powerButton = new Button("Power");
		
		// future addition: when power button is clicked actual temp raises to setTemp
		this.powerButton.setOnAction(new EventHandler<ActionEvent>()
				{

					@Override
					public void handle(ActionEvent arg0) {
						powerLabel.setText("Heating");
						
					}
					
				});
		
		
		// temp indicators
		this.actualTemp = new Button("ACTUAL");
		
		// event handler
		// indicator will show current temp (default temp is room temp 68F)
		
		
		this.targetTemp = new Button("TARGET");
		
		// event handler
		// indicator will show target temp
		
		// timers
		this.timer10 = new Button("10");
		
		//event handler
		// timer will start after actual temp = target temp
		// timer will be set for 10 seconds
		
		this.timer20 = new Button("20");
		
		//event handler
		// timer will start after actual temp = target temp
		// timer will be set for 20 seconds
		
		this.timer30 = new Button("30");
		
		//event handler
		// timer will start after actual temp = target temp
		// timer will be set for 30 seconds
		
		
		// temp controls
		this.tempUp = new Button();
		this.tempUp.setText("+");
		TextField targetTextField = new TextField(); // create new TextField object
		// event handler
		// when user clicks up button target temp increases by 1F
		
		this.tempUp.setOnAction(new EventHandler <ActionEvent>()
				{

					@Override
					public void handle(ActionEvent event) {
						int num = Integer.parseInt(targetTextField.getText()); // getting value from textfield
						num += 1; // increment value from textfield by 1
						targetTextField.setText("" + num); // display result
						
					}

			
				});
		

		this.tempDown = new Button();
		tempDown.setText("-");
		// event handler
		// when user clicks down button target temp decreases by 1F
		this.tempDown.setOnAction(new EventHandler <ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event) {
				int num = Integer.parseInt(targetTextField.getText()); // getting value from textfield
				num -= 1; // decrement value from textfield by 1
				targetTextField.setText("" + num); // display result
				
			}

	
		});
		
		
		this.tempSet = new Button("SET");
		
		// event handler
		// when user clicks set button, target temp is set as setpoint
		
		
		// mapping of buttons on scene 
		
		// create VBox for temp indcators
		VBox tempIndicatorVbox = new VBox(50,actualTemp,targetTemp);
		tempIndicatorVbox.setAlignment(Pos.CENTER);
		
		// create HBox for timers 
		HBox tempTimersHbox = new HBox(20,timer10,timer20,timer30);
		tempTimersHbox.setAlignment(Pos.CENTER_LEFT);
		
		// create HBox for temp controls
		HBox tempControlsHbox = new HBox(20,tempUp,targetTextField,tempDown,tempSet);
		tempControlsHbox.setAlignment(Pos.CENTER_LEFT);
		
		// create VBox to contain HBox timers and temp controls
		VBox saunaControlsVbox = new VBox(50,tempTimersHbox,tempControlsHbox);
		saunaControlsVbox.setAlignment(Pos.CENTER_LEFT);
		
		
		this.powerLabel = new Label();
		this.root.setTop(powerButton);
		this.root.setBottom(powerLabel);
		this.root.setCenter(tempIndicatorVbox);
		this.root.setRight(saunaControlsVbox);
		this.stage.setScene(scene);
		this.stage.show(); 
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
