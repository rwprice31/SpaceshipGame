package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import controller.PlayerCtrl;

/**
 * @author Robert
 *
 */
public class PlayerDB {

	//DB Connector dbc;
	private DBConnection dbc;

	/**
	 * 
	 */
	public PlayerDB() {
		dbc = new DBConnection();
	}

	public PlayerCtrl getPlayer(int incomingPlayerID)
	{
		ResultSet rs = dbc.query(dbc, "SELECT * FROM player WHERE playerID = " + incomingPlayerID);
		int playerID = 0, locationID = 0, inventoryID = 0, playerHitpoints = 0;
		String playerName = null;
		try
		{
			while(rs.next())
			{
				playerID = rs.getInt("playerID");
				locationID =  rs.getInt("locationID");
				inventoryID = rs.getInt("inventoryID");
				playerHitpoints = rs.getInt("playerHitpoints");
				playerName = rs.getString("playerName");	
			}


		}

		catch(SQLException sqe)
		{
			System.out.println(sqe.getMessage());
		}
		return new PlayerCtrl(playerID, locationID, inventoryID, playerHitpoints, playerName);



	}

	//playerID, locationID, inventoryID, playerHitpoints, playerName
	public void addPlayer(PlayerCtrl p)
	{
		int playerID = p.getPlayerID();
		int locationID = p.getLocationID();
		int inventoryID = p.getInventoryID();
		int playerHitpoints = p.getPlayerHitpoints();
		String playerName = p.getPlayerName();
		//	System.out.println("Name = " + playerName + " pId = " + playerID + " lID = " + locationID + " iID = " + inventoryID + " hp = " + playerHitpoints);
		dbc.modData(dbc, "INSERT INTO PLAYER(playerID, locationID, inventoryID, playerHitpoints, playerName) "
				+ " VALUES(" + playerID +", " + locationID +", " + inventoryID +", " + playerHitpoints + ", '" + playerName +"')");

		//	dbc.modData(dbc, "INSERT INTO PLAYER(playerID, locationID, inventoryID, playerHitpoints, playerName) VALUES(playerID, locationID, inventoryID, playerHitpoints, 'playerName')");
		//dbc.modData(dbc, Integer.parseInt("INSERT INTO PLAYER (playerID, locationID, inventoryID, playerHitpoints)"
		//							+ "VALUES (playerID, locationID, inventoryID, playerHitpoints)");		
	}

	/*
	 * INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY)
VALUES (1, 'Paul', 32, 'California', 20000.00 );
	 */

	public void setPuzzleCompleted(int playerID)
	{

	}

	public void setMonsterDefeated(int playerID)
	{

	}

	public void setLoactionCompleated(int playerID)
	{

	}

	public int countNumberOfPlayers() throws SQLException {
		//System.out.println(" hi");
		ResultSet rs = dbc.query(dbc, "SELECT playerID FROM player WHERE playerID = (SELECT MAX(playerID) FROM player)");

		//	return rs.getRow();
		int totalPlayers = 0;
		try
		{
			while(rs.next())
			{

				totalPlayers = rs.getInt("playerID");

			}
		}
		catch (SQLException sqle) {
			sqle.getMessage();
		}


		return totalPlayers;
	}

	//	} catch (NullPointerException npe) {
	//	return 0;
	//}




}
