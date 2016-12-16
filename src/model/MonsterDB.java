package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import controller.MonsterCtrl;
import controller.PlayerCtrl;

public class MonsterDB 
{
	private DBConnection dbc;

	public MonsterDB() 
	{
		dbc = new DBConnection();
	}

	public MonsterCtrl getMonster(int incomingMonsterID) 
	{
		ResultSet rs = dbc.query(dbc, "SELECT * FROM monster WHERE monsterID = " + incomingMonsterID);
		int monsterID = 0, monsterHitpoints = 0, monsterDamage = 0;
		String monsterName = null, monsterType = null;
		try
		{
			while(rs.next())
			{
				monsterID = rs.getInt("monsterID");
				monsterName =  rs.getString("monsterName");
				monsterHitpoints = rs.getInt("monsterHitpoints");
				monsterType = rs.getString("monsterType");
				monsterDamage = rs.getInt("monsterDamage");	
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
		return new MonsterCtrl(monsterID, monsterName, monsterHitpoints, monsterType, monsterDamage);
	}
	
	/** Name: getAllMonsterIDs
	 * Returns an integer array containing each monsterID in the monster table
	 */
	public int[] getAllMonsterIDs() 
	{
		int[] monsterIDs = new int[countMonsters()];
		int index = 0;
		
		ResultSet rs = dbc.query(dbc, "SELECT monsterID FROM monster");	
		try
		{
			while(rs.next())
			{
				monsterIDs[index++] = rs.getInt("monsterID");
			}
		}
		catch (SQLException sqle) 
		{
			sqle.getMessage();
		}
		return monsterIDs;
	}
	
	public int countMonsters() 
	{
				ResultSet rs = dbc.query(dbc, "SELECT COUNT(monsterID) FROM monster");	
				int totalMonsters = 0;
				try
				{
					while(rs.next())
					{
						totalMonsters = rs.getInt("COUNT(monsterID)");
					}
				}
				catch (SQLException sqle) {
					sqle.getMessage();
				}


				return totalMonsters;

	}
	
	public int damageMonster(int incomingMonsterID, int damageToInflict) 
	{	
		ResultSet rs = dbc.query(dbc, "SELECT monsterHitpoints FROM monster WHERE monsterID = " + incomingMonsterID);
		int monsterHitpoints = 0;
		try
		{
			while(rs.next())
			{
				monsterHitpoints = rs.getInt("monsterHitpoints");
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
		return monsterHitpoints - damageToInflict;
	}
}
