package view;

import java.sql.SQLException;

import controller.GameCtrl;
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
	private Text tMessages = new Text();
	private Button bInventory = new Button("Inventory");
	private String playerName;

	/*public RunningGameScreen(NewGameScreen newGame, String user)
	{
		GameCtrl game = new GameCtrl(user);
		playerName = user;
		buildGamePane();	
	}
	 */

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
		mainGamePane.setBottom(userInput);

		buildGlobalCommandsPane();
		mainGamePane.setRight(globalCommandsPane);

		//build descriptionPane();
		mainGamePane.setCenter(descriptionPane);

		buildInventoryPane();
		mainGamePane.setLeft(inventoryPane);

		mainGamePane.setTop(tMessages);	
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
