package model;

public class Monster {

	//Attributes (match the Monster table's columns)
	private int monsterID;
	private String monsterName;
	private int hitpoints;
	private int monsterType;
	private int monsterDamage;
	
	//Connection object for database (use this to query the SQL)
	private DatabaseConnection dbc;
	
	
	//Constructor that takes in a monster primary key
	public Monster(int monsterID) {
		
		//Maybe we take the monsterID and check that it exists before using it?
		
		//Take it a monsterID and use it to assign the other values
		this.monsterID = monsterID;
		this.monsterName = getMonsterName(monsterID);
		this.hitpoints = getHitpoints(monsterID);
		this.monsterType = getMonsterType(monsterID);
		this.monsterDamage = getMonsterDamage(monsterID);
	}

	public int getMonsterID(String monsterName) {
		//SQL code goes here
		return 0;
	}
	
	public int getMonsterID() {
		return this.monsterID;
	}

	/*Commented out methods, I don't think we need (we don't want to 
	*change them (set them))
	*/
	
	
//	public void setMonsterID(int monsterID) {
//		this.monsterID = monsterID;
//	}

	public String getMonsterName(int monsterID) {
		//SQL code goes here
		return monsterName;
	}
	
	public String getMonsterName() {
		return this.monsterName;
	}

//	public void setMonsterName(String monsterName) {
//		this.monsterName = monsterName;
//	}

	public int getHitpoints(int monsterID) {
		//SQL Code using monsterID primary key
		return hitpoints;
	}
	
	public int getHitpoints() {
		return this.hitpoints;
	}

	/*Shouldn't update the table's hit points column
	*Returns updated hitpoints for storage in battle.java?
	*/
	
//	public int setHitpoints(int hitpoints) {
//		return 0;
//	}

	public int getMonsterType(int monsterID) {
		return monsterType;
	}

	public int getMonsterDamage(int monsterID) {
		//SQL Code using monsterID primary key
		return monsterDamage;
	}
	
	public int getMonsterDamage() {
		return this.monsterDamage;
	}

	public void setMonsterDamage(int monsterDamage) {
		this.monsterDamage = monsterDamage;
	}

	
	//public DatabaseConnection getDbc() {
	//	return dbc;
	//}
	
}
