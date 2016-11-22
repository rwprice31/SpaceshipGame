package controller;

public class MonsterCtrl {
	
	private int monsterID;
	private String monsterName;
	private int monsterHitpoints;
	private String monsterType;
	private int monsterDamage;
	
	public MonsterCtrl(int monsterID, String monsterName, int monsterHitpoints, String monsterType, int monsterDamage)
	{
		this.monsterID = monsterID;
		this.monsterName = monsterName;
		this.monsterHitpoints = monsterHitpoints;
		this.monsterType = monsterType;
		this.monsterDamage = monsterDamage;
	}

	public int getMonsterID()
	{
		return monsterID;
	}

	public void setMonsterID(int monsterID)
	{
		this.monsterID = monsterID;
	}

	public String getMonsterName() 
	{
		return monsterName;
	}

	public void setMonsterName(String monsterName) 
	{
		this.monsterName = monsterName;
	}

	public int getMonsterHitpoints()
	{
		return monsterHitpoints;
	}

	public void setMonsterHitpoints(int monsterHitpoints)
	{
		this.monsterHitpoints = monsterHitpoints;
	}

	public String getMonsterType() 
	{
		return monsterType;
	}

	public void setMonsterType(String monsterType)
	{
		this.monsterType = monsterType;
	}

	public int getMonsterDamage() 
	{
		return monsterDamage;
	}

	public void setMonsterDamage(int monsterDamage) 
	{
		this.monsterDamage = monsterDamage;
	}

	@Override
	public String toString() 
	{
		return "MonsterCtrl [monsterID=" + monsterID + ", monsterName=" + monsterName + ", monsterHitpoints="
				+ monsterHitpoints + ", monsterType=" + monsterType + ", monsterDamage=" + monsterDamage + "]";
	}
}
