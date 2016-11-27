package view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartScreen extends Application
{
	private NewGameScreen newGameScreen = new NewGameScreen(this);
	private LoadGameScreen loadGameScreen = new LoadGameScreen(this);
	private Button bNewGame = new Button("New Game");
	private Button bLoadGame = new Button("Load Game");
	private Button bExitGame = new Button("Exit Game"); 
	private VBox paneForButtons = new VBox(20);
	private GridPane startPane = new GridPane();
	private Scene scene = null;
	private Stage stageForGame = null;
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		stageForGame = primaryStage;
		
		primaryStage.setTitle("Spaceship Game");
		paneForButtons.getChildren().addAll(bNewGame, bLoadGame, bExitGame);
		startPane.setAlignment(Pos.CENTER);
		startPane.add(paneForButtons, 0, 1);
		
		bNewGame.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				try
				{
					Stage newGameStage = new Stage();
					newGameStage.setTitle("Start a new game");
					
					Scene newGameScene = new Scene(newGameScreen.getNewGamePane(), 300, 300);
					primaryStage.close();
					newGameStage.setScene(newGameScene);
					newGameStage.show();
				}catch(Exception e)
				{
					System.out.println("New stage not working");
				}
			}	
		});
		
		bLoadGame.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				try
				{
					Stage loadStage = new Stage();
					loadStage.setTitle("Load your game");
					
					Scene loadScene = new Scene(loadGameScreen.getLoadPane(), 300, 300);
					primaryStage.close();
					loadStage.setScene(loadScene);
					loadStage.show();
				}catch (Exception e)
				{
					System.out.println("Load Stage not working");
				}
			}
			});
		
		bExitGame.setOnAction((event) -> {
			primaryStage.close();
			Platform.exit();
		});
		
		scene = new Scene(startPane, 300, 300);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	
//	public static void startRunningGameStage() {
//		//replace stage and whatnot 
//	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
}
