package view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StartScreen extends Application
{
	private NewGameScreen newGameScreen = new NewGameScreen(this);
	private LoadGameScreen loadGameScreen = new LoadGameScreen(this);
	private HBox hbox;
	private Button bNewGame;
	private Button bLoadGame;
	private Button bExitGame;
	private Button returnButton;
	private Text title;
	private GridPane startScreenContainer;
	private Scene scene = null;
	private Stage stageForGame = null;
	private Pane startPane;
	//private Pane startPane;
	
	private void createMenuNodes() {
		bNewGame = new Button("New Game");
		bLoadGame = new Button("Load Game");
		bExitGame = new Button("Exit");
		title = new Text("Spaceship Game");
		hbox = new HBox(8);
		startScreenContainer = new GridPane();
		startScreenContainer.setAlignment(Pos.CENTER);
		startScreenContainer.add(title, 0, 0);
		startScreenContainer.add(bNewGame, 0, 1);
		startScreenContainer.add(bLoadGame, 1, 1);
		startScreenContainer.add(bExitGame, 0, 2);
		GridPane.setHalignment(bExitGame, HPos.CENTER);
		GridPane.setColumnSpan(bExitGame, 2);
		GridPane.setHalignment(title, HPos.CENTER);
		GridPane.setColumnSpan(title, 2);
		startScreenContainer.setHgap(10);
		startScreenContainer.setVgap(10);
		startPane.getChildren().add(startScreenContainer);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		stageForGame = primaryStage;
		createMenuNodes();
		primaryStage.setTitle("Spaceship Game");
		
		
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
