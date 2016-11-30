package view;

import java.sql.SQLException;

import controller.NewGameCtrl;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NewGameScreen
{
	private GridPane newGameContainer;
	private TextField userInput;
	private Button bReturn;
	private Button bEnter;
	private HBox userInputHBox;
	private NewGameCtrl newGameCtrl;
	
	public void createNewGameNodes()
	{
		bEnter = new Button("Enter");
	    userInputHBox.getChildren().addAll(new Label("Enter Name:"), userInput);
		newGameContainer.add(userInputHBox, 0, 0);
		newGameContainer.add(bEnter, 1, 0);
		newGameContainer.setVgap(10);
		newGameContainer.setHgap(10);
		newGameContainer.setAlignment(Pos.CENTER);
	}
	
	public NewGameScreen(StartScreen parent)
	{
		userInput = new TextField();
		userInputHBox = new HBox(8);
		newGameContainer = new GridPane();
		createNewGameNodes();
		
		bEnter.setOnAction((event) -> {
			String userName = userInput.getText();
			Stage stage = (Stage) bEnter.getScene().getWindow();
			stage.close();
			
			newGameCtrl = new NewGameCtrl(userName);
			try 
			{
				
				RunningGameScreen rgs = new RunningGameScreen(newGameCtrl.addPlayer().getPlayerID());
				rgs.launchScreen();
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
			
		});
	}
	
	public GridPane getNewGamePane()
	{
		return newGameContainer;
	}
}
