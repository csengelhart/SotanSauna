
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

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
	int timer = 0;
	
	// temp controls
	private Button tempUp;
	private Button tempDown;
	private Button tempSet;
	boolean set = false; // flag to let power button check if the target temp is set
	
	
	@Override
	public void start(Stage primaryStage)
	{
		this.stage = primaryStage;
		stage.setTitle("SotanSauna");
		this.root = new BorderPane();
		
		
//*****************************************************************************************************************************		

		// create buttons and event handlers
		
		this.scene = new Scene(root, 600,500);
		
	
		
		
		// temp indicators
		this.actualTemp = new Button("ACTUAL");
		TextField actualTempTextField = new TextField("68"); // create uneditable texfield for displaying actual temp
		actualTempTextField.setEditable(false);
		// event handler
		// indicator will show current temp (default temp is room temp 68F)
		
		
		this.targetTemp = new Button("TARGET");
		
		// event handler
		// indicator will show target temp
		
		// textfield for timer buttons
		TextField timerTextField = new TextField(); 
		
		// timers
		this.timer10 = new Button("10");
		
		//event handler
		// timer will start after actual temp = target temp
		// when "10" is clicked, the timer variable is set to 10
		this.timer10.setOnAction(new EventHandler <ActionEvent>() 
		{

			@Override
			public void handle(ActionEvent event) {
				timerTextField.setText("10");
				
			}

	
		});
		
		
		
		this.timer20 = new Button("20");
		
		//event handler
		// timer will start after actual temp = target temp
		// when "20" is clicked, the timer variable is set to 20
		this.timer20.setOnAction(new EventHandler <ActionEvent>() 
		{

			@Override
			public void handle(ActionEvent event) {
				timerTextField.setText("20");
				
			}

	
		});
		
		
		
		
		this.timer30 = new Button("30");
		
		//event handler
		// timer will start after actual temp = target temp
		// when "30" is clicked, the timer variable is set to 30
				this.timer30.setOnAction(new EventHandler <ActionEvent>() 
				{

					@Override
					public void handle(ActionEvent event) {
						timerTextField.setText("30");
						
					}

			
				});
				
		// temp controls
		this.tempUp = new Button();
		this.tempUp.setText("+");
		TextField targetTextField = new TextField(); // create new TextField object
		targetTextField.setText("68");
		targetTextField.setEditable(false);
		// event handler
		// when user clicks up button target temp increases by 1F up to max of 194
		
		this.tempUp.setOnAction(new EventHandler <ActionEvent>()
				{

					@Override
					public void handle(ActionEvent event) {
						// if target temp is not equal to 194, increase it by 1
						if(Integer.parseInt(targetTextField.getText()) != 194)
						{int num = Integer.parseInt(targetTextField.getText()); // getting value from textfield
						num += 1; // increment value from textfield by 1
						targetTextField.setText("" + num); // display result
						}
						
							
					}

			
				});
		

		this.tempDown = new Button();
		tempDown.setText("-");
		// event handler
		// when user clicks down button target temp decreases by 1F up to minimum 68
		this.tempDown.setOnAction(new EventHandler <ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event) {
				if(Integer.parseInt(targetTextField.getText()) != 68)	
				{int num = Integer.parseInt(targetTextField.getText()); // getting value from textfield
				num -= 1; // decrement value from textfield by 1
				targetTextField.setText("" + num); // display result
				}
			}

	
		});
		
		
		this.tempSet = new Button("SET");
		// event handler
		// when user clicks set button, target temp is set as setpoint and become uneditable
		// when user clicks set again, the target textfield becomes editable again
		
		this.tempSet.setOnMouseClicked(new EventHandler <MouseEvent>()
		{
			
			@Override
			public void handle(MouseEvent mouseEvent) {
				// if single click registered, enable editing of target textfield, and enable +,- buttons
				if(mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
					if(mouseEvent.getClickCount() == 1)
					{
						targetTextField.setEditable(true);
						tempUp.setDisable(false);
						tempDown.setDisable(false);
						set = false;
						
					}
					
					// if double click registered, disable target textfield,and +, - buttons
					else if(mouseEvent.getClickCount() == 2) 
					{
						targetTextField.setEditable(false);
						tempUp.setDisable(true);
						tempDown.setDisable(true);
						set = true;
					}
				}
				
				
			}

		
	
		});
		
		
		
		// power button
		this.powerButton = new Button("Power");
		
		// conditional: if target temp is set (set variable is set to true) and timer variable > 0 
		// execute: actual temp increasing to target temp 
		
		{
		// future addition: when power button is clicked actual temp raises to setTemp
			this.powerButton.setOnAction(new EventHandler<ActionEvent>()
					{

					@Override
						public void handle(ActionEvent arg0) {
						
							// if timerTextField is not empty, get value from textfield
							if(!(timerTextField.getText().isEmpty()))
							{timer = Integer.parseInt(timerTextField.getText()); // getting value from textfield
							powerLabel.setText("" + timer);
							}	
							
							// if timer != 0 and set flag variable is true
							// set actual temp equal to target temp and start timer
							
							if(timer != 0 && set == true)
							{
								
								// get target temp from textfield
								String targetTemp = targetTextField.getText();
								// set actual temp equal to target temp
								actualTempTextField.setText(targetTemp);
								
								// create Timer Object
								Timer timerObj = new Timer();
								
								// start timer, timer length based on which timer button was selected
								// after timer, set actual temp back to 68
								new Timeline(new KeyFrame(
								        Duration.millis(timer * 1000),
								        ae -> actualTempTextField.setText("68")))
								    .play();
								
								
							
							}
							
							else 
								powerLabel.setText("Make sure temperature is set and timer selected");
							
							
					}
					
				});
		}
		
		
// *******************************************************************************************************		
		// mapping of buttons on scene 
		
		// create HBox for temp controls
		HBox tempControlsHbox = new HBox(10,tempUp,targetTextField,tempDown,tempSet);
		tempControlsHbox.setAlignment(Pos.CENTER);
		
		// create HBox for actualTemp indicator and actual temp textfield
		HBox actualTempHbox = new HBox(20,actualTemp,actualTempTextField);
		actualTempHbox.setAlignment(Pos.CENTER);
		
		// create VBox for temp indcators
		VBox tempIndicatorVbox = new VBox(50,actualTempHbox,tempControlsHbox);
		tempIndicatorVbox.setAlignment(Pos.CENTER);
		
		
		// create HBox for timers 
		HBox tempTimersHbox = new HBox(20,timer10,timer20,timer30);
		tempTimersHbox.setAlignment(Pos.CENTER_LEFT);
		
		// create VBox to contain HBox timers and temp controls
		VBox saunaControlsVbox = new VBox(50,tempTimersHbox,timerTextField);
		saunaControlsVbox.setAlignment(Pos.CENTER_LEFT);
		
		
		this.powerLabel = new Label();
		this.root.setTop(powerButton);
		this.root.setBottom(powerLabel);
		this.root.setCenter(tempIndicatorVbox);
		//this.root.setCenter(actualTempHbox);
		this.root.setRight(saunaControlsVbox);
		this.stage.setScene(scene);
		this.stage.show(); 
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
