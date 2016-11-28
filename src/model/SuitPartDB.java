package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import controller.SuitPartCtrl;



public class SuitPartDB 
{

	private DBConnection dbc;

	public SuitPartDB() 
	{
		dbc = new DBConnection();
	}

	public SuitPartCtrl getsuitPart(int incomingsuitPartID)
	{
		ResultSet rs = dbc.query(dbc, "SELECT * FROM suitPart WHERE suitPartID = " + incomingsuitPartID);
		int suitPartID = 0;

		String suitPartName = "";
		try
		{
			while(rs.next())
			{
				suitPartID = rs.getInt("suitPartID");
				suitPartName =  rs.getString("suitPartName");

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
		return new SuitPartCtrl(suitPartID, suitPartName);

	}

	public String getsuitPartName(int suitPartID) 
	{
		ResultSet rs = dbc.query(dbc, "SELECT suitPartName FROM suitPartName WHERE suitPartID = " + suitPartID);

		String suitPartName = "";
		try
		{
			while(rs.next())
			{
				suitPartName =  rs.getString("suitPartName");
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
		return suitPartName;

	}

}


