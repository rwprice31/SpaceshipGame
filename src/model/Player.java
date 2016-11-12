package model;

public class Player {

	private int playerID;
	private String playerName;
	private int locationID;
	private int hitpoints;
	private int inventoryID;
	private DatabaseConnection dbc;
	
	public Player(int playerID, String playerName, int locationID, int hitpoints, int inventoryID) {
		this.playerID = playerID;
		this.playerName = playerName;
		this.locationID = locationID;
		this.hitpoints = hitpoints;
		this.inventoryID = inventoryID;
		dbc = new DatabaseConnection();
	}

	public int getPlayerID() {
		return playerID;
	}

	
//	public void setPlayerID(int playerID) {
//		this.playerID = playerID;
//	}

	public String getPlayerName() {
		return playerName;
	}

	//Don't think we need this
//	public void setPlayerName(String playerName) {
//		this.playerName = playerName;
//	}

	public int getLocationID() {
		return locationID;
	}

	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}

	public int getHitpoints() {
		return hitpoints;
	}

	public void setHitpoints(int hitpoints) {
		this.hitpoints = hitpoints;
	}

	public int getInventoryID() {
		return inventoryID;
	}

	public void setInventoryID(int inventoryID) {
		this.inventoryID = inventoryID;
	}

	public DatabaseConnection getDatabaseConnection() {
		return dbc;
	}
	
	@Override
	public String toString() {
		return "Player [playerID=" + playerID + ", playerName=" + playerName + ", locationID=" + locationID
				+ ", hitpoints=" + hitpoints + ", inventoryID=" + inventoryID + "]";
	}
	
	
	
}
