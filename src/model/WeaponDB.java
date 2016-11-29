package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import controller.WeaponCtrl;

/**
 * 
 * @author Robert
 *
 */
public class WeaponDB 
{

	public DBConnection dbc;
	
	public WeaponDB() 
	{
		dbc = new DBConnection();
	}
	
	/**
	 * 
	 * @param incomingWeaponID
	 * @return
	 * @throws SQLException
	 */
	public WeaponCtrl getWeapon(int incomingWeaponID) throws SQLException
	{
		ResultSet rs = dbc.query(dbc, "SELECT * FROM weapon WHERE weaponID = " + incomingWeaponID);
		int weaponID = 0, weaponDamage = 0;
		String weaponName = null, weaponType = null;
		while (rs.next())
		{
			weaponID = rs.getInt("weaponID");
			weaponName = rs.getString("weaponName");
			weaponDamage = rs.getInt("weaponDamage");
			weaponType = rs.getString("weaponType");
		}
		return new WeaponCtrl(weaponID, weaponName, weaponDamage, weaponType);
	}
	
	/**
	 * 
	 * @param weaponID
	 * @return
	 * @throws SQLException
	 */
	public int getWeaponDamage(int weaponID) throws SQLException 
	{
		ResultSet rs = dbc.query(dbc, "SELECT weaponDamage FROM weapon WHERE weaponID = " + weaponID);
		int weaponDamage = 0;
		while (rs.next())
		{
			weaponDamage = rs.getInt("weaponDamage");
		}
		return weaponDamage;
	}
	
	/**
	 * 
	 * @param weaponID
	 * @return
	 * @throws SQLException
	 */
	public String getWeaponName(int weaponID) throws SQLException 
	{
		ResultSet rs = dbc.query(dbc, "SELECT weaponName FROM weapon WHERE weaponID = " + weaponID);
		String weaponName = null;
		while (rs.next())
		{
			weaponName = rs.getString("weaponName");
		}
		return weaponName;
	}
	
	
	

}
