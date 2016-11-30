package view;

import java.util.ArrayList;
import java.util.HashMap;

import controller.LoadGameCtrl;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoadGameScreen
{
	private GridPane loadPane = null;
	private VBox paneForUserInput = new VBox(20);
	private HashMap<Integer, Button> bHM;
	private Button returnToMenu;
	private Text loadTitle;
	private BorderPane loadGameContainer;
	
	private LoadGameCtrl loadGameCtrl;

	public LoadGameScreen(StartScreen parent)
	{	
		returnToMenu = new Button("Return to starting menu");
		bHM = new HashMap<>();
		loadGameCtrl = new LoadGameCtrl();
		loadPane = new GridPane();
		loadTitle = new Text("Select your save");
		loadGameContainer = new BorderPane();
		createLoadGameScreenNodes(parent);
	}

	public void createLoadGameScreenNodes(StartScreen parent)
	{
		loadPane.setAlignment(Pos.CENTER);
		loadPane.setPadding(new Insets(1,1,1,1));

		bHM = loadGameCtrl.getButtons();
		loadPane.add(loadTitle, 0, 0);
		int rowIndex = 1;

		for (Integer i: bHM.keySet())
		{
			loadPane.add(bHM.get(i), 0, rowIndex);
			rowIndex++;
		}
		
		loadPane.setVgap(5);

		for (Integer i: bHM.keySet())
		{
			bHM.get(i).setOnAction(e -> {
				
				System.out.println(loadGameCtrl.getPlayer(i).getPlayerName());
			/*	Stage stage = (Stage) bHM.get(i).getScene().getWindow();
				stage.close();
				
				RunningGameScreen rgs = new RunningGameScreen(this, loadGameCtrl.getPlayer(i).getPlayerID());
				rgs.launchScreen();
				*/
			});
		}
		
		//TO DO
		returnToMenu.setOnAction(e -> {
			
			
			
		});
		
		loadGameContainer.setCenter(loadPane);
		loadGameContainer.setBottom(returnToMenu);
	}



	public BorderPane getLoadPane()
	{
		return loadGameContainer;
	}
}
