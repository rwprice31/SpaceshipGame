package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import controller.InventoryCtrl;
import controller.ShipPartCtrl;

public class ShipPartDB 
{

	private DBConnection dbc;

	public ShipPartDB() 
	{
		dbc = new DBConnection();
	}

	public ShipPartCtrl getShipPart(int incomingShipPartID)
	{
		ResultSet rs = dbc.query(dbc, "SELECT * FROM ShipPart WHERE shipPartID = " + incomingShipPartID);
		int shipPartID = 0;

		String shipPartName = "";
		try
		{
			while(rs.next())
			{
				shipPartID = rs.getInt("shipPartID");
				shipPartName =  rs.getString("shipPartName");

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
		return new ShipPartCtrl(shipPartID, shipPartName);

	}

	public String getShipPartName(int shipPartID) 
	{
		ResultSet rs = dbc.query(dbc, "SELECT shipPartName FROM ShipPartName WHERE shipPartID = " + shipPartID);

		String shipPartName = "";
		try
		{
			while(rs.next())
			{
				shipPartName =  rs.getString("shipPartName");
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
		return shipPartName;

	}

}
