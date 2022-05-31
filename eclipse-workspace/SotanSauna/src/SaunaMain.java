
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
		actualTemp = new Button("ACTUAL");
		actualTemp.setStyle("-fx-background-color: gainsboro;");
		TextField actualTempTextField = new TextField("68"); // create uneditable texfield for displaying actual temp 
		// default temp is 68F
		actualTempTextField.setEditable(false);
		
		// textfield for timer buttons
		TextField timerTextField = new TextField(); 
		
		// timers
		timer10 = new Button("10");
		timer10.setStyle("-fx-background-color: gainsboro");
		timer10.setStyle("-fx-border-color: black");
		
		//event handler
		// timer will start after actual temp = target temp
		timer10.setOnAction(new EventHandler <ActionEvent>() 
		{

			@Override
			public void handle(ActionEvent event) {
				timerTextField.setText("10");
				
			}

	
		});
		
		
		
		timer20 = new Button("20");
		timer20.setStyle("-fx-background-color: gainsboro");
		timer20.setStyle("-fx-border-color: black");
		//event handler
		// timer will start after actual temp = target temp
		timer20.setOnAction(new EventHandler <ActionEvent>() 
		{

			@Override
			public void handle(ActionEvent event) {
				timerTextField.setText("20");
				
			}

	
		});
		
		
		
		
		timer30 = new Button("30");
		timer30.setStyle("-fx-background-color: gainsboro");
		timer30.setStyle("-fx-border-color: black");
		
		//event handler
		// timer will start after actual temp = target temp
				this.timer30.setOnAction(new EventHandler <ActionEvent>() 
				{

					@Override
					public void handle(ActionEvent event) {
						timerTextField.setText("30");
						
					}

			
				});
				
		// temp controls
		tempUp = new Button();
		tempUp.setText("+");
		tempUp.setStyle("-fx-background-color: gainsboro");
		tempUp.setStyle("-fx-border-color: black");
		TextField targetTextField = new TextField(); // create new TextField object
		targetTextField.setText("68");
		targetTextField.setEditable(false);
		// event handler
		// when user clicks up button target temp increases by 1F up to max of 194
		
		tempUp.setOnAction(new EventHandler <ActionEvent>()
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
		

		tempDown = new Button();
		tempDown.setText("-");
		tempDown.setStyle("-fx-border-color: black");
		// event handler
		// when user clicks down button target temp decreases by 1F up to minimum 68
		tempDown.setOnAction(new EventHandler <ActionEvent>()
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
		
		
		tempSet = new Button("SET");
		tempSet.setStyle("-fx-border-color: black");
		// event handler
		// when user clicks set button, target temp is set as setpoint and become uneditable
		// when user clicks set again, the target textfield becomes editable again
		
		tempSet.setOnMouseClicked(new EventHandler <MouseEvent>()
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
		powerButton = new Button("Power");
		powerButton.setStyle("-fx-background-color: gainsboro");
		powerButton.setStyle("-fx-border-color: black");
		
		// conditional: if target temp is set (set variable is set to true) and timer variable > 0 
		// execute: actual temp increasing to target temp 
		
		{
		// future addition: when power button is clicked actual temp raises to setTemp
			powerButton.setOnAction(new EventHandler<ActionEvent>()
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
								//change power button color to green
								powerButton.setStyle("-fx-background-color: lightgreen");
								
								// set actual temp equal to target temp
								actualTempTextField.setText(targetTemp);
								
								// create Timer Object
								Timer timerObj = new Timer();
								
								// start timer, timer length based on which timer button was selected
								// after timer, set actual temp back to 68
								new Timeline(new KeyFrame(
								        Duration.millis(timer * 1000),
								        ae -> { 
								          actualTempTextField.setText("68");
								          powerButton.setStyle("fx-background-color: gainsboro");
								          powerButton.setStyle("-fx-border-color: black");
								        }))
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
