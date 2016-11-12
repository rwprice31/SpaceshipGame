package model;

public class Monster {

	private int monsterID;
	private String monsterName;
	private int hitpoints;
	private int monsterType;
	private int monsterDamage;
	private DatabaseConnection dbc;
	
	public Monster(int monsterID, String monsterName, int hitpoints, int monsterType, int monsterDamage) {
		this.monsterID = monsterID;
		this.monsterName = monsterName;
		this.hitpoints = hitpoints;
		this.monsterType = monsterType;
		this.monsterDamage = monsterDamage;
	}

	public int getMonsterID() {
		return monsterID;
	}

	public void setMonsterID(int monsterID) {
		this.monsterID = monsterID;
	}

	public String getMonsterName() {
		return monsterName;
	}

	public void setMonsterName(String monsterName) {
		this.monsterName = monsterName;
	}

	public int getHitpoints() {
		return hitpoints;
	}

	//Shouldn't update the table 
	public void setHitpoints(int hitpoints) {
		this.hitpoints = hitpoints;
	}

	public int getMonsterType() {
		return monsterType;
	}

	public int getMonsterDamage() {
		return monsterDamage;
	}

	public void setMonsterDamage(int monsterDamage) {
		this.monsterDamage = monsterDamage;
	}

	public DatabaseConnection getDbc() {
		return dbc;
	}
	
	
	
	
	
	
	
	
}
