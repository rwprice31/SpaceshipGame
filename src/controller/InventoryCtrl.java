package controller;
import java.util.ArrayList;

import model.DBConnection;
import model.InventoryDB;

public class InventoryCtrl {

	private int inventoryID;
	private int playerID;
	private ArrayList<WeaponCtrl> weaponAL;
	private ArrayList<SuitPartCtrl> suitPartAL;
	private ArrayList<ShipPartCtrl> shipPartAL;
	private InventoryDB inventoryDB;
	
	
	/**
	 * @param playerID
	 */
	public InventoryCtrl(int playerID) 
	{
		inventoryDB = new InventoryDB();
		this.playerID = playerID;
		this.inventoryID = playerID;
		inventoryDB.addInventory(this);
		
	}

    // add elements to the array list
    //weaponAL.add("C");
    
	public InventoryCtrl(int inventoryID, int playerID) 
	{
		this.inventoryID = inventoryID;
		this.playerID = playerID;
	}

	public void printWeaponAL()
	{
		
	}
	
	public void printSuitPartAL()
	{
		
	}
	
	public void printShipPartAL()
	{
		
	}
	
	/**
	 * @return the inventoryID
	 */
	public int getInventoryID()
	{
		return inventoryID;
	}


	/**
	 * @return the playerID
	 */
	public int getPlayerID()
	{
		return playerID;
	}


	/**
	 * @return the weaponAL
	 */
	public ArrayList<WeaponCtrl> getWeaponAL()
	{
		return weaponAL;
	}
	
	public ArrayList<SuitPartCtrl> getSuitPartAL()
	{
		return suitPartAL;
	}
	
	public ArrayList<ShipPartCtrl> getShipPartAL()
	{
		return shipPartAL;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() 
	{
		return "Inventory [inventoryID=" + inventoryID + ", playerID=" + playerID + 
				", weaponAL=" + weaponAL + "]";
	}
   
	
  
	
}
