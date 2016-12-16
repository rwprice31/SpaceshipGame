package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.InventoryCtrl;
import controller.SuitPartCtrl;
import controller.WeaponCtrl;

public class InventoryDB 
{
	private DBConnection dbc;

	public InventoryDB() 
	{
		dbc = new DBConnection();
	}

	public void addInventory(InventoryCtrl i) 
	{
		dbc.modData(dbc, "INSERT INTO inventory(inventoryID, playerID)"+
				" VALUES(" + i.getInventoryID() + ", " + i.getPlayerID() + ")");
	}

	public InventoryCtrl getInventory(int incomingInventoryID)
	{
		ResultSet rs = dbc.query(dbc, "SELECT * FROM inventory WHERE inventoryID = " + incomingInventoryID);
		int inventoryID = 0 , playerID = 0;

		try
		{
			while(rs.next())
			{
				inventoryID = rs.getInt("inventoryID");
				playerID =  rs.getInt("playerID");

			}

		}
		catch (SQLException sqle)
		{
			System.out.println(sqle.getMessage());
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return new InventoryCtrl(inventoryID, playerID);

	}

	public String addWeapon(int inventoryID, int weaponID) 
	{
		dbc.modData(dbc, "INSERT INTO InventoryWeapon(inventoryID, weaponID) "
				+ " VALUES(" + inventoryID +", " + weaponID + ")");
		return "Weapon Added to Inventory";
	}

	public String addSuitPart(int inventoryID, int suitPartID) 
	{
		dbc.modData(dbc, "INSERT INTO InventorySuitPart(inventoryID, suitPartID) "
				+ " VALUES(" + inventoryID +", " + suitPartID + ")");
		return "Suitpart Added";
	}

	public void addShipPart(int inventoryID, int shipPartID) 
	{
		dbc.modData(dbc, "INSERT INTO InventoryShipPart(inventoryID, shipPartID) "
				+ " VALUES(" + inventoryID +", " + shipPartID + ")");
	}

	public ArrayList<WeaponCtrl> getWeapons(int inventoryID) throws SQLException 
	{
		ResultSet rs = dbc.query(dbc, "SELECT * FROM weapon WHERE weaponID = (SELECT weaponID FROM InventoryWeapon WHERE inventoryID = " + inventoryID + ")");
		ArrayList<WeaponCtrl> weaponAL = new ArrayList<WeaponCtrl>();	
		while (rs.next())
		{
			weaponAL.add(new WeaponCtrl(rs.getInt("weaponID"), rs.getString("weaponName"), rs.getInt("weaponDamage"), rs.getString("weaponType")));
		}

		return weaponAL;
	}

	public ArrayList<SuitPartCtrl> getSuitParts(int inventoryID) throws SQLException
	{
		ResultSet rs = dbc.query(dbc, "SELECT * FROM SuitPart WHERE suitPartID = (SELECT suitPartID FROM InventorySuitPart WHERE inventoryID = " + inventoryID + ")");
		int suitPartID = 0;
		String suitPartName = null;
		ArrayList<SuitPartCtrl> suitPartAL = new ArrayList<SuitPartCtrl>();
		while (rs.next())
		{
			suitPartAL.add(new SuitPartCtrl(rs.getInt("suitPartID"), rs.getString("suitPartName")));
		}

		return suitPartAL;
	}

	public ArrayList<Integer> getShipParts(int inventoryID) 
	{
		ResultSet rs = dbc.query(dbc, "SELECT shipPartID FROM InventoryWeapon WHERE inventoryID = " + inventoryID);
		ArrayList<Integer> shipPartAL = new ArrayList<Integer>();
		int shipPartID = 0;
		try 
		{
			while (rs.next())
			{
				shipPartID = rs.getInt("shipPartID");
				shipPartAL.add(shipPartID);
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return shipPartAL;

	}


}
