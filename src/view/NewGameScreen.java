package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NewGameScreen
{
	private GridPane newGamePane = null;
	private VBox paneForUserInput = new VBox(20);
	private Button bOk = new Button("Ok");
	
	public NewGameScreen(StartScreen parent)
	{
		newGamePane = new GridPane();
		newGamePane.setAlignment(Pos.CENTER);
		newGamePane.setPadding(new Insets(1,1,1,1));
		
		TextField tfUserName = new TextField();
		tfUserName.setPromptText("Enter your new user name");
		paneForUserInput.getChildren().addAll(tfUserName, bOk);
		
		newGamePane.add(paneForUserInput, 0, 1);
		
		bOk.setOnAction((event) -> {
			String userName = tfUserName.getText();
			//setUser(userName)
//			RunningGameScreen runningGame = new RunningGameScreen(this, userName);
//			runningGame.buildGamePane();
			Stage stage = (Stage) bOk.getScene().getWindow();
			stage.close();
			
			RunningGameScreen rgs = new RunningGameScreen(this, userName);
			rgs.launchScreen();
		});
	}
	
	public GridPane getNewGamePane()
	{
		return newGamePane;
	}
}
