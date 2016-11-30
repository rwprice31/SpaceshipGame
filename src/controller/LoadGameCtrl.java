package controller;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.control.Button;
import model.PlayerDB;

public class LoadGameCtrl 
{
	private PlayerDB pDB;
	private HashMap<Integer, Button> bHM;
	private ArrayList<PlayerCtrl> playerAL;
	
	public LoadGameCtrl()
	{
		pDB = new PlayerDB();
		bHM = new HashMap<>();
		playerAL = new ArrayList<>();
		for (Integer playerID: pDB.getAllPlayerIDs())
		{
			playerAL.add(pDB.getPlayer(playerID));
		}
	}
	
	public HashMap<Integer, Button> getButtons()
	{
		for (int index = 0; index < playerAL.size(); index++)
		{
			bHM.put(playerAL.get(index).getPlayerID(), new Button(playerAL.get(index).getPlayerName()));
		}
		return bHM;
	}
	
	public PlayerCtrl getPlayer(int playerID) 
	{
		return pDB.getPlayer(playerID);
	}
	
	
}
