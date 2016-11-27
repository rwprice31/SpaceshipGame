package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RunningGameScreen
{
	private BorderPane mainGamePane = null;
	private TextField userInput = new TextField();
	private ScrollPane descriptionPane = new ScrollPane();
	private GridPane globalCommandsPane = new GridPane();
	private GridPane inventoryPane = new GridPane();
	private Text tMessages = new Text();
	private Button bInventory = new Button("Inventory");
	
	public RunningGameScreen(NewGameScreen newGame, String user)
	{
		//Player p = new Player(user);
		//PlayerController pc = new PlayerController();
		//pc.addNewPlayer(user)
		buildGamePane();
	}
	
	public RunningGameScreen(LoadGameScreen loadGame, String user)
	{
		buildGamePane();
		//getSavedGame(playerID);
	}
	
	public void buildGamePane()
	{
		mainGamePane = new BorderPane();
		mainGamePane.setTop(userInput);
		
		buildGlobalCommandsPane();
		mainGamePane.setRight(globalCommandsPane);
		
		//build descriptionPane();
		mainGamePane.setCenter(descriptionPane);
		
		buildInventoryPane();
		mainGamePane.setLeft(inventoryPane);
		
		mainGamePane.setBottom(tMessages);	
	}
	
	public void buildGlobalCommandsPane()
	{		
		Button bSave = new Button("Save");
		Button bQuit = new Button("Quit");
		
		VBox buttonsPane = new VBox(5);
		buttonsPane.getChildren().addAll(bSave, bQuit);
		
		globalCommandsPane.setAlignment(Pos.CENTER);
		globalCommandsPane.add(buttonsPane, 0, 1);
	}
	
	public void buildInventoryPane()
	{	
		VBox buttonPane = new VBox(5);
		buttonPane.getChildren().add(bInventory);
		
		inventoryPane.setAlignment(Pos.CENTER);
		inventoryPane.add(buttonPane, 0, 1);
	}
	
	public void launchScreen()
	{
		try
		{
			Stage runningGameStage = new Stage();
			Scene runningGameScene = new Scene(mainGamePane, 500, 500);
			runningGameStage.setScene(runningGameScene);
			runningGameStage.show();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
