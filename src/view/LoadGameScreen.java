package view;

import java.util.ArrayList;
import java.util.HashMap;

import controller.LoadGameCtrl;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class LoadGameScreen
{
	private GridPane loadPane = null;
	private VBox paneForUserInput = new VBox(20);
	private HashMap<Integer, Button> bHM;
	
	private LoadGameCtrl loadGameCtrl;
	
	public void createLoadGameScreenNodes()
	{
		loadPane.setAlignment(Pos.CENTER);
		loadPane.setPadding(new Insets(1,1,1,1));
		
		bHM = loadGameCtrl.getButtons();
		int rowIndex = 0;
		
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
			});
		}
	}
	
	public LoadGameScreen(StartScreen parent)
	{	
		bHM = new HashMap<>();
		loadGameCtrl = new LoadGameCtrl();
		loadPane = new GridPane();
		createLoadGameScreenNodes();
	}
	
	public GridPane getLoadPane()
	{
		return loadPane;
	}
}
