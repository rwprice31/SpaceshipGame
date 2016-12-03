package controller;

import java.sql.SQLException;

import model.PlayerDB;

public class NewGameCtrl 
{
	private PlayerDB pDB;
	private PlayerCtrl player;
	private String playerName;
	
	public NewGameCtrl(String playerName)
	{
		pDB = new PlayerDB();
		this.playerName = playerName;
	}
	
	public PlayerCtrl addPlayer() throws SQLException
	{
		player = new PlayerCtrl(playerName);
		pDB.addPlayer(player);
		pDB.addIncompletedPuzzles(player.getPlayerID());
		pDB.addIncompleteLocations(player.getPlayerID());
		pDB.addUndefeatedMonsters(player.getPlayerID());
		return player;
	}
}
