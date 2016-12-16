package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import controller.RoomCtrl;

/**
 * 
 * @author Robert
 *
 */
public class RoomDB 
{
	private DBConnection dbc;
	
	public RoomDB()
	{
		dbc = new DBConnection();
	}
	
	/** Name: getRoom
	 * returns a RoomCtrl object with information retrieved from the Room database table
	 * @param incomingRoomID
	 * @return
	 * @throws SQLException
	 */
	public RoomCtrl getRoom(int incomingRoomID) throws SQLException 
	{
		ResultSet rs = dbc.query(dbc, "SELECT * FROM Room WHERE roomID = " + incomingRoomID);
		int roomID = 0, monsterID = 0, locationID = 0, puzzleID = 0, isStartingRoom = 0;
		String roomName = null;
		while (rs.next())
		{
			roomID = rs.getInt("roomID");
			monsterID = rs.getInt("monsterID");
			locationID = rs.getInt("locationID");
			puzzleID = rs.getInt("puzzleID");
			isStartingRoom = rs.getInt("isStartingRoom");
			roomName = rs.getString("roomName");
		}
		return new RoomCtrl(roomID, monsterID, locationID, puzzleID, isStartingRoom, roomName);
	}
	
	/**Name: getNumberOfRoomIDs
	 * Returns the number of rooms (used when creating an array of all rooms)
	 * @return numberOfIDs
	 * @throws SQLException
	 */
	public int getNumberOfRoomIDs() throws SQLException
	{
		ResultSet rs = dbc.query(dbc, "SELECT COUNT(roomID) FROM room");
		int numberOfIDs = 0;
		while (rs.next())
		{
			numberOfIDs = rs.getInt("COUNT(roomID)");
		}
		return numberOfIDs;
	}
	
	/**Name: getRoomIDs
	 * Returns an integer array with all the room ids
	 * @return
	 * @throws SQLException 
	 */
	public int[] getRoomIDs() throws SQLException 
	{
		int[] roomIDs = new int[getNumberOfRoomIDs()];
		int index = 0;
		ResultSet rs = dbc.query(dbc, "SELECT roomID FROM room");
		while (rs.next())
		{
			roomIDs[index++] = rs.getInt("roomID");
		}
		return roomIDs;
	}
	
	/**Name: getStartingRoomForLocation
	 * Returns the starting room for a specfied location
	 * @param locationID
	 * @return
	 * @throws SQLException 
	 */
	public int getStartingRoomForLocation(int locationID) throws SQLException
	{
		ResultSet rs = dbc.query(dbc, "SELECT roomID FROM room WHERE locationID = " + locationID + " AND isStartingRoom = 1");
		int startingRoomID = 0;
		while (rs.next())
		{
			startingRoomID = rs.getInt("roomID");
		}
		return startingRoomID;
	}
	
	/**Name: getRoomsThatContainMonsters
	 * Returns a hashmap that contains the monsterID as the key and roomID as the value
	 * Serves to detail which rooms each monster lives in
	 * @return
	 * @throws SQLException 
	 */
	public HashMap<Integer, Integer> getRoomsThatContainMonsters() throws SQLException
	{
		ResultSet rs = dbc.query(dbc, "SELECT monsterID, roomID, isStartingRoom  FROM room WHERE isStartingRoom != 0");
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		while (rs.next())
		{
			map.put(rs.getInt("monsterID"), rs.getInt("roomID"));
		}
		return map;
	}
	
	/**Name: getRoomsThatContainPuzzles
	 * Returns a hashmap that contains the puzzleID as the key and roomID as the value
	 * Serves to detail which room each puzzle lives in
	 * @return
	 * @throws SQLException 
	 */
	public HashMap<Integer, Integer> getRoomsThatContainPuzzles() throws SQLException
	{
		ResultSet rs = dbc.query(dbc, "SELECT puzzleID, roomID FROM room WHERE isStartingRoom != 0");
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		while (rs.next())
		{
			map.put(rs.getInt("puzzleID"), rs.getInt("roomID"));
		}
		return map;

	}
	
	/**Name: getStartingRoomsForAllLocations
	 * Returns a hashmap that contains the locationID as the key and roomID as the value
	 * Serves to detail the rooms that make up each location
	 * @return
	 * @throws SQLException 
	 */
	public HashMap<Integer, Integer> getStartingRoomsForAllLocations() throws SQLException
	{
		ResultSet rs = dbc.query(dbc, "SELECT locationID, roomID FROM room WHERE isStartingRoom != 0");
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		while (rs.next())
		{
			map.put(rs.getInt("locationID"), rs.getInt("roomID"));
		}
		return map;
	}
	
	/**Name: getRoomsThatContainSpecficMonster
	 * 
	 * @param monsterID
	 * @return
	 * @throws SQLException 
	 */
	public ArrayList<Integer> getRoomsThatContainSpecificMonster(int monsterID) throws SQLException
	{
		ResultSet rs = dbc.query(dbc, "SELECT roomID FROM room WHERE monsterID = " + monsterID);
		ArrayList<Integer> roomMonsterAL = new ArrayList<>();
		while (rs.next())
		{
			roomMonsterAL.add(rs.getInt("roomID"));
		}
		return roomMonsterAL;
	}
	
	/**Name: getRoomsThatBelongToSpecificLocation
	 * 
	 * @param locationID
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Integer> getRoomsThatBelongToSpecficLocation(int locationID) throws SQLException
	{
		ResultSet rs = dbc.query(dbc, "SELECT roomID FROM room WHERE locationID = " + locationID);
		ArrayList<Integer> roomLocationAL = new ArrayList<>();
		while (rs.next())
		{
			roomLocationAL.add(rs.getInt("roomID"));
		}
		return roomLocationAL;
	}
	
	/**Name: getRoomsThatContainSpecificPuzzle
	 * 
	 * @param puzzleID
	 * @return
	 * @throws SQLException 
	 */
	public ArrayList<Integer> getRoomsThatContainSpecificPuzzle(int puzzleID) throws SQLException
	{
		ResultSet rs = dbc.query(dbc, "SELECT roomID FROM room WHERE puzzleID = " + puzzleID);
		ArrayList<Integer> roomLocationAL = new ArrayList<>();
		while (rs.next())
		{
			roomLocationAL.add(rs.getInt("roomID"));
		}
		return roomLocationAL;
	}
}
