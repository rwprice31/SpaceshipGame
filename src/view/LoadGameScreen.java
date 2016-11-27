package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoadGameScreen
{
	private GridPane loadPane = null;
	private VBox paneForUserInput = new VBox(20);
	private Button bOk = new Button("Ok");
	
	public LoadGameScreen(StartScreen parent)
	{	
		loadPane = new GridPane();
		loadPane.setAlignment(Pos.CENTER);
		loadPane.setPadding(new Insets(1,1,1,1));
		
		TextField tfUserName = new TextField();
		tfUserName.setPromptText("Enter your user name");
		paneForUserInput.getChildren().addAll(tfUserName, bOk);
		
		loadPane.add(paneForUserInput, 0, 1);
		
		bOk.setOnAction((event) -> {
			String userName = tfUserName.getText();
			//getUser(userName);
//			RunningGameScreen runningGame = new RunningGameScreen(this, userName);
			System.out.println(userName);
			Stage stage = (Stage) bOk.getScene().getWindow();
			stage.close();
			
			RunningGameScreen rgs = new RunningGameScreen(this, userName);
			rgs.launchScreen();
		});
	}
	
	public GridPane getLoadPane()
	{
		return loadPane;
	}
}
