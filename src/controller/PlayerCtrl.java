package controller;
import java.sql.SQLException;

import model.PlayerDB;

/**
 * @author Robert
 *
 */
public class PlayerCtrl {


private int playerID;
private int locationID;
private int inventoryID;
private int playerHitpoints;
private String playerName;

/**
 * @param playerID
 * @param locationID
 * @param inventoryID
 * @param playerHitpoints
 * @param playerName
 * @throws SQLException 
 */
public PlayerCtrl(String playerName) throws SQLException 
{
	PlayerDB playerDB = new PlayerDB();
	this.playerID = playerDB.getMaxIDOfPlayers()+1;
	this.inventoryID = this.playerID;
	this.locationID = 1;
	new InventoryCtrl(this.inventoryID);
	this.playerHitpoints = 100;
	this.playerName = playerName;

}

public PlayerCtrl(int playerID, int locationID, int inventoryID, int playerHitpoints, String playerName) {
	this.playerID = playerID;
	this.locationID = locationID;
	this.inventoryID = inventoryID;
	this.playerHitpoints = playerHitpoints;
	this.playerName = playerName;
}
/**
 * @return the playerID
 */
public int getPlayerID() {
	return playerID;
}
/**
 * @param playerID the playerID to set
 */
public void setPlayerID(int playerID) {
	this.playerID = playerID;
}
/**
 * @return the locationID
 */
public int getLocationID() {
	return locationID;
}
/**
 * @param locationID the locationID to set
 */
public void setLocationID(int locationID) {
	this.locationID = locationID;
}
/**
 * @return the inventoryID
 */
public int getInventoryID() {
	return inventoryID;
}
/**
 * @param inventoryID the inventoryID to set
 */
public void setInventoryID(int inventoryID) {
	this.inventoryID = inventoryID;
}
/**
 * @return the playerHitpoints
 */
public int getPlayerHitpoints() {
	return playerHitpoints;
}
/**
 * @param playerHitpoints the playerHitpoints to set
 */
public void setPlayerHitpoints(int playerHitpoints) {
	this.playerHitpoints = playerHitpoints;
}
/**
 * @return the playerName
 */
public String getPlayerName() {
	return playerName;
}
/**
 * @param playerName the playerName to set
 */
public void setPlayerName(String playerName) {
	this.playerName = playerName;
}

/**
 * Subtracts damage from player health
 * @returns - The new player health
 */
public int takeDamage(int incomingPlayerHitpoints, int incomingDamage) 
{		
	return incomingPlayerHitpoints - incomingDamage;
}


/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "Player [playerID=" + playerID + ", locationID=" + locationID + ", inventoryID=" + inventoryID
			+ ", playerHitpoints=" + playerHitpoints + ", playerName=" + playerName + "]";
}







	
	
}
