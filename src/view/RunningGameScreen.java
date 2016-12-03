package view;

import java.sql.SQLException;

import controller.GameCtrl;
import controller.PlayerCtrl;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.PlayerDB;

public class RunningGameScreen
{
	private BorderPane mainGamePane = null;
	private static TextField userInput = new TextField();
	private ScrollPane descriptionScrollPane = new ScrollPane();
	private static TextArea descriptionPane = new TextArea();
	private GridPane globalCommandsPane = new GridPane();
	private GridPane inventoryPane = new GridPane();
	//private Text tMessages = new Text();
	private Button bInventory = new Button("Inventory");
	private String playerName;
	private PlayerCtrl player;
	private Button bQuit;
//	private Button bSave;
	private Text gameTitle = new Text("Spaceship Game");

	public RunningGameScreen(int playerID) throws SQLException
	{
		PlayerDB pDB = new PlayerDB();
		playerName = pDB.getPlayer(playerID).getPlayerName();
		buildGamePane();
		new GameCtrl(playerID, userInput);
	}

	public void buildGamePane()
	{
		descriptionPane.clear();
		descriptionScrollPane = new ScrollPane(descriptionPane);
		descriptionPane.setEditable(false);
		descriptionPane.insertText(0, "Welcome to the Spaceship Game, " + playerName + "!");

		mainGamePane = new BorderPane();
		mainGamePane.setTop(userInput);

		buildGlobalCommandsPane();
		mainGamePane.setRight(globalCommandsPane);

		//build descriptionPane();
		mainGamePane.setCenter(descriptionPane);

		buildInventoryPane();
		mainGamePane.setLeft(inventoryPane);

		BorderPane.setAlignment(gameTitle, Pos.CENTER);
		BorderPane.setMargin(gameTitle, new Insets(5, 5, 5, 5));
		
		bQuit.setOnAction(e -> {
			System.exit(0);
		});
		
		bInventory.setOnAction(e -> {
			
		});
	}

	public void buildGlobalCommandsPane()
	{		
		bQuit = new Button("Quit");

		VBox buttonsPane = new VBox(5);
		buttonsPane.getChildren().addAll(bQuit);

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

	public static String getUserInput()
	{
		String s = userInput.getText();
		clearTextField();
		return s;
	}

	public static void clearTextField()
	{
		userInput.clear();
	}

	public static void displayToUser(String messageToDisplay)
	{
		descriptionPane.appendText("\n" + messageToDisplay);
	}

	public void clearText()
	{
		descriptionPane.clear();
	}
	
	public void launchScreen()
	{
		try
		{
			Stage runningGameStage = new Stage();
			Scene runningGameScene = new Scene(mainGamePane, 500, 500);
			runningGameStage.setTitle("Spaceship Game!");
			runningGameStage.setScene(runningGameScene);
			runningGameStage.show();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void playerDied()
	{
		RunningGameScreen.displayToUser("You have died");
		//System.exit(0);
		//userInput.disableProperty();
	}
}
