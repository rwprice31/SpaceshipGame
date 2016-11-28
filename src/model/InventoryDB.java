package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.InventoryCtrl;
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

	public void addWeapon(int inventoryID, int weaponID) 
	{
		dbc.modData(dbc, "INSERT INTO InventoryWeapon(inventoryID, weaponID) "
				+ " VALUES(" + inventoryID +", " + weaponID);
	}

	public void addSuitPart(int inventoryID, int suitPartID) 
	{
		dbc.modData(dbc, "INSERT INTO InventorySuitPart(inventoryID, suitPartID) "
				+ " VALUES(" + inventoryID +", " + suitPartID);
	}

	public void addShipPart(int inventoryID, int shipPartID) 
	{
		dbc.modData(dbc, "INSERT INTO InventoryShipPart(inventoryID, shipPartID) "
				+ " VALUES(" + inventoryID +", " + shipPartID);
	}

	public ArrayList<Integer> getWeapons(int inventoryID) 
	{
		ResultSet rs = dbc.query(dbc, "SELECT weaponID FROM InventoryWeapon WHERE inventoryID = " + inventoryID);
		ArrayList<Integer> weaponAL = new ArrayList<Integer>();
		int weaponID = 0;
		try 
		{
			while (rs.next())
			{
				weaponID = rs.getInt("weaponID");
				weaponAL.add(weaponID);
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return weaponAL;
	}

	public ArrayList<Integer> getSuitParts(int inventoryID)
	{
		ResultSet rs = dbc.query(dbc, "SELECT suitPartID FROM InventorySuitPart WHERE inventoryID = " + inventoryID);
		ArrayList<Integer> suitPartAL = new ArrayList<Integer>();
		int suitPartID = 0;
		try 
		{
			while (rs.next())
			{
				suitPartID = rs.getInt("suitPartID");
				suitPartAL.add(suitPartID);
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
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
