package controller;

public class WeaponCtrl 
{

	private int weaponID;
	private String weaponName;
	private int weaponDamage;
	private String weaponType;
	
	public WeaponCtrl(int weaponID, String weaponName, int weaponDamage, String weaponType) 
	{
		this.weaponID = weaponID;
		this.weaponName = weaponName;
		this.weaponDamage = weaponDamage;
		this.weaponType = weaponType;
	}

	public String getWeaponType() 
	{
		return weaponType;
	}

	public void setWeaponType(String weaponType)
	{
		this.weaponType = weaponType;
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
