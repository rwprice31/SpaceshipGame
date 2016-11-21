package controller;

import java.sql.SQLException;

import org.omg.Messaging.SyncScopeHelper;

import model.DBConnection;
import model.MonsterDB;
import model.PlayerDB;

public class GameCtrl {

	public GameCtrl() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) throws SQLException {
		
			//PlayerCtrl p = new PlayerCtrl("Robsta");
			
			
			//PlayerCtrl p2 = new PlayerCtrl("Jimmy");
		//	PlayerDB pDB = new PlayerDB();
			
		
		MonsterDB monsterDB = new MonsterDB();
		MonsterCtrl m1 = monsterDB.getMonster(1);
		//System.out.println(m1.getMonsterHitpoints());
		int testDamage = monsterDB.damageMonster(m1.getMonsterID(), 13);
		
		
		int[] intArray = monsterDB.getAllMonsterIDs();
		
				
		PlayerDB playerDB = new PlayerDB();
	//	playerDB.addUndefeatedMonsters(1);
		playerDB.setMonsterDefeated(1, 1);
		
	//	System.out.println(testDamage);
		
		//	PlayerCtrl p3 = pDB.getPlayer(1);
		//	System.out.println(p3.getPlayerHitpoints()-10);
		//	System.out.println(p3.toString());
		//	System.out.println(p.getPlayerID() + " " + p.getInventoryID() + " " + p.getLocationID() + " " + p.getPlayerHitpoints() + " " + p.getPlayerName());
		//	pDB.addPlayer(p2);
		//	pDB.addPlayer(p2);
		//	DBConnection dbc = new DBConnection();
		//	dbc.modData(dbc, "INSERT INTO PLAYER(playerID, locationID, inventoryID, playerHitpoints, playerName) VALUES(1, 1, 1, 100, 'Timmy')");
			
	
		
	}

}
