package controller;

public class WeaponCtrl 
{

	private int weaponID;
	private String weaponName;
	private int weaponDamage;
	
	public WeaponCtrl(int weaponID, String weaponName, int weaponDamage) 
	{
		this.weaponID = weaponID;
		this.weaponName = weaponName;
		this.weaponDamage = weaponDamage;
	}

	public int getWeaponID() 
	{
		return weaponID;
	}

	public void setWeaponID(int weaponID)
	{
		this.weaponID = weaponID;
	}

	public String getWeaponName() 
	{
		return weaponName;
	}

	public void setWeaponName(String weaponName) 
	{
		this.weaponName = weaponName;
	}

	public int getWeaponDamage() 
	{
		return weaponDamage;
	}

	public void setWeaponDamage(int weaponDamage) 
	{
		this.weaponDamage = weaponDamage;
	}

	@Override
	public String toString() 
	{
		return "WeaponCtrl [weaponID=" + weaponID + ", weaponName=" + weaponName + ", weaponDamage=" + weaponDamage
				+ "]";
	}
	
	
	
	
	

}
