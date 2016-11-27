package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.omg.Messaging.SyncScopeHelper;

import model.DBConnection;
import model.LocationDB;
import model.MonsterDB;
import model.PlayerDB;
import model.RoomDB;
import model.ValidInputDB;

public class GameCtrl 
{

	private PlayerCtrl player;
	private PlayerDB pDB;
	
	public GameCtrl(int playerID) 
	{
		pDB = new PlayerDB();
		this.player = pDB.getPlayer(playerID);
	}
	
	public void startJungle()
	{
		
	}
	
	public void startGlacier()
	{
		
	}
	
	public void startSpaceship()
	{
		
	}
	
	public void startVolcano()
	{
		
	}
	
	public void startSpaceshipEscape()
	{
		
	}
	
	public void startCave()
	{
		
	}
	
	public void startDesert() 
	{
		
	}
	
	public static void main(String[] args) throws SQLException 
	{
		
	}
}
