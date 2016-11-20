package model;

import controller.InventoryCtrl;

public class InventoryDB {

	private DBConnection dbc;
	
	public InventoryDB() {
		dbc = new DBConnection();
	}
	
	public void addInventory(InventoryCtrl i) {
		dbc.modData(dbc, "INSERT INTO inventory(inventoryID, playerID) VALUES(" + i.getInventoryID() + ", " + i.getPlayerID() + ")");
	}

}
