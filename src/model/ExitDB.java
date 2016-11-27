package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.ExitCtrl;

/**
 * 
 * @author Robert
 *
 */
public class ExitDB 
{
	private DBConnection dbc;
	
	public ExitDB()
	{
		dbc = new DBConnection();
	}
	
	public ExitCtrl getExit(int incomingExitID) throws SQLException 
	{
		ResultSet rs = dbc.query(dbc, "SELECT * FROM exit WHERE exitID = " + incomingExitID);
		int exitID = 0, startingRoomID = 0, endingRoomID = 0;
		while (rs.next())
		{
			exitID = rs.getInt("exitID");
			startingRoomID = rs.getInt("startingRoomID");
			endingRoomID = rs.getInt("endingRoomID");
		}
		return new ExitCtrl(exitID, startingRoomID, endingRoomID);
	}
	
	public ArrayList<ExitCtrl> getExitsForSpecificRoom(int incomingRoomID) throws SQLException
	{
		ResultSet rs = dbc.query(dbc, "SELECT * FROM exit WHERE startingRoomID = " + incomingRoomID);
		ArrayList<ExitCtrl> exitAL = new ArrayList<>();
		while (rs.next())
		{
			exitAL.add(new ExitCtrl(rs.getInt("exitID"), rs.getInt("startingRoomID"), rs.getInt("endingRoomID")));
		}
		return exitAL;
		
	}
}
