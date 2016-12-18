package view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StartScreen extends Application
{
	private NewGameScreen newGameScreen = new NewGameScreen(this);
	private LoadGameScreen loadGameScreen = new LoadGameScreen(this);
	private Button bNewGame;
	private Button bLoadGame;
	private Button bExitGame;
	private Stage stageForGame;
	private static GridPane startScreenContainer;

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		stageForGame = primaryStage;
		stageForGame.setTitle("Spaceship Game");
		createMenuNodes();

		bNewGame.setOnAction(e -> {

			try
			{
				Stage newGameStage = new Stage();
				newGameStage.setTitle("Start a new game");

				Scene newGameScene = new Scene(newGameScreen.getNewGamePane(), 400, 300);
				primaryStage.close();
				newGameStage.setScene(newGameScene);
				newGameStage.show();
			} catch (Exception ex)
			{
				System.out.println("New stage not working");
			}

		});

		bLoadGame.setOnAction(e -> {
			try
			{
				Stage loadStage = new Stage();
				loadStage.setTitle("Load your game");

				Scene loadScene = new Scene(loadGameScreen.getLoadPane(), 300, 300);
				primaryStage.close();
				loadStage.setScene(loadScene);
				loadStage.show();
			} catch (Exception ex)
			{
				System.out.println("Load Stage not working");
			}
		});

		bExitGame.setOnAction((event) -> {
			primaryStage.close();
			Platform.exit();
		});
		
		Scene scene = new Scene(startScreenContainer, 300, 300);
		stageForGame.setScene(scene);
		stageForGame.show();
	}

	private void createMenuNodes()
	{
		bNewGame = new Button("New Game");
		bLoadGame = new Button("Load Game");
		bExitGame = new Button("Exit");
		
		startScreenContainer = new GridPane();
		startScreenContainer.setAlignment(Pos.CENTER);
		startScreenContainer.add(bNewGame, 0, 1);
		startScreenContainer.add(bLoadGame, 1, 1);
		startScreenContainer.add(bExitGame, 0, 2);
		startScreenContainer.setHgap(10);
		startScreenContainer.setVgap(10);
		
		GridPane.setHalignment(bExitGame, HPos.CENTER);
		GridPane.setColumnSpan(bExitGame, 2);
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
}
