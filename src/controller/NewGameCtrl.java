package controller;

import java.sql.SQLException;

import model.PlayerDB;

public class NewGameCtrl 
{
	private PlayerDB pDB;
	private String playerName;
	
	public NewGameCtrl(String playerName)
	{
		pDB = new PlayerDB();
		this.playerName = playerName;
	}
	
	public void addPlayer() throws SQLException
	{
		pDB.addPlayer(new PlayerCtrl(playerName));
	}
}
