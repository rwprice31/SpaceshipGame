package controller;
import java.util.ArrayList;

import model.DBConnection;
import model.InventoryDB;

public class InventoryCtrl {

	private int inventoryID;
	private int playerID;
	private ArrayList<Integer> weaponAL;
	private ArrayList<Integer> suitPartAL;
	private ArrayList<Integer> shipPartAL;
	//private DBConnection dbc;
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
    
	public void addWeapon(int weaponID)
	{
		
	}
	
	public void addSuitPart(int suitPartID)
	{
		
	}
	
	public void addShipPart(int shipPartID)
	{
		
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
	public int getInventoryID() {
		return inventoryID;
	}


	/**
	 * @return the playerID
	 */
	public int getPlayerID() {
		return playerID;
	}


	/**
	 * @return the weaponAL
	 */
	public ArrayList<Integer> getWeaponAL() {
		return weaponAL;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Inventory [inventoryID=" + inventoryID + ", playerID=" + playerID + 
				", weaponAL=" + weaponAL + "]";
	}
   
	
  
	
}
