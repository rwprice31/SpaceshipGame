package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import controller.LocationCtrl;

public class LocationDB {

	private DBConnection dbc;

	public LocationDB() 
	{
		dbc = new DBConnection();
	}

	/**
	 * 
	 * @param incomingLocationID
	 * @return
	 * @throws SQLException
	 */
	public LocationCtrl getLocation(int incomingLocationID) throws SQLException 
	{
		ResultSet rs = dbc.query(dbc, "SELECT * FROM Location");
		int locationID = 0;
		String locationName = null;
		while (rs.next())
		{
			locationID = rs.getInt("locationID");
			locationName = rs.getString("locationName");
		}
		return new LocationCtrl(locationID, locationName);
	}

	/** Name: getNumberOfLocationIDs
	 * 
	 * @return
	 * @throws SQLException
	 */
	public int getNumberOfLocationIDs() throws SQLException 
	{
		ResultSet rs = dbc.query(dbc, "SELECT COUNT(locationID) FROM Location");
		int numberOfIDs = 0;
		while (rs.next())
		{
			numberOfIDs = rs.getInt("COUNT(locationID)");
		}
		return numberOfIDs;
	}
	
	/** Name: getAllLocationIDs
	 * 
	 * @return
	 * @throws SQLException
	 */
	public int[] getAllLocationIDs() throws SQLException 
	{
		int[] locationIDs = new int[getNumberOfLocationIDs()];
		int index = 0;
		ResultSet rs = dbc.query(dbc, "SELECT locationID FROM location");
		while (rs.next()) 
		{
			locationIDs[index++] = rs.getInt("locationID");
		}
		return locationIDs;
	}

}
