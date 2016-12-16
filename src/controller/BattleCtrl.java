package controller;

import java.sql.SQLException;
import java.util.Scanner;

import javafx.scene.control.TextField;
import model.InventoryDB;
import model.MonsterDB;
import model.PlayerDB;
import model.WeaponDB;
import view.RunningGameScreen;

public class BattleCtrl 
{

	private MonsterDB mDB;
	private PlayerDB pDB;
	private PlayerCtrl player;
	private MonsterCtrl monster;
	private WeaponCtrl weapon;
	private WeaponDB wDB;
	private InventoryDB iDB;
	private int monsterHitpoints;
	private boolean monsterIsAlive;
	private boolean playerIsAlive;
	private int playerHitpoints;
	private String userInput;
	//private TextField userInputTF;
	private int index;
	private int weaponID;
	private boolean playerIsAttacking;

	public BattleCtrl(int incomingPlayerID, int incomingMonsterID, String userInput, int playerHitpoints, int monsterHitpoints, int index, boolean playerIsAttacking)
	{
		mDB = new MonsterDB();
		pDB = new PlayerDB();
		iDB = new InventoryDB();
		wDB = new WeaponDB();
		player = pDB.getPlayer(incomingPlayerID);
		monster = mDB.getMonster(incomingMonsterID);
		this.monsterHitpoints = monsterHitpoints;
		this.playerHitpoints = playerHitpoints;
		monsterIsAlive = true;
		playerIsAlive = true;
		//this.userInputTF = userInputTF;	
		this.userInput = userInput;
		this.index = index;
		this.playerIsAttacking = playerIsAttacking;
	}

	public void startBattle() throws SQLException
	{
		System.out.println();
		System.out.println("Index = " + index);
		System.out.println("Player is attacking = " + playerIsAttacking);
		System.out.println("Monster is alive = " + monsterIsAlive);
		System.out.println("Player is alive = " + playerIsAlive);
		System.out.println("PlayerHP = " + playerHitpoints);
		System.out.println("MonsterHP = " + monsterHitpoints);

		try {
			weaponID = Integer.parseInt(userInput);
		} catch(NumberFormatException nfe)
		{
			weaponID = 1;
		}

		if (index == 1)
		{
			RunningGameScreen.displayToUser("\n\tPlayer vs " + monster.getMonsterName());

			playerHitpoints -= monster.getMonsterDamage();
			RunningGameScreen.displayToUser(monster.getMonsterName() + " attacks, damaging you for " + monster.getMonsterDamage() 
			+ "\nYour current hitpoints are: " + playerHitpoints);
			RunningGameScreen.displayToUser(monster.getMonsterName() + " currently has " + monsterHitpoints + " hitpoints");	
			RunningGameScreen.displayToUser("Select a weapon to attack " + monster.getMonsterName());
			for (WeaponCtrl w: iDB.getWeapons(player.getInventoryID()))
			{
				RunningGameScreen.displayToUser("\t" + w.getWeaponID() + ". " + w.getWeaponName());
			}
		}

		if (monsterIsAlive == true && playerIsAlive == true && index != 1)
		{
			RunningGameScreen.displayToUser("\nYou used your " + wDB.getWeaponName(weaponID) + " to damage " + monster.getMonsterName()
			+ " for " + getDamage(weaponID) + " hitpoints.");
			monsterHitpoints -=  getDamage(weaponID);
			RunningGameScreen.displayToUser(monster.getMonsterName() + " currently has " + monsterHitpoints + " hitpoints");	
			playerHitpoints -= monster.getMonsterDamage();
			RunningGameScreen.displayToUser(monster.getMonsterName() + " attacks, damaging you for " + monster.getMonsterDamage() 
			+ "\nYour current hitpoints are: " + playerHitpoints);
			RunningGameScreen.displayToUser("Select a weapon to attack " + monster.getMonsterName());
			for (WeaponCtrl w: iDB.getWeapons(player.getInventoryID()))
			{
				RunningGameScreen.displayToUser("\t" + w.getWeaponID() + ". " + w.getWeaponName());
			}

			if (playerHitpoints <= 0)
			{
				playerIsAlive = false;
			}

			if (monsterHitpoints <= 0)
			{
				monsterIsAlive = false;
			}

		}				
		if (monsterIsAlive == false)
		{
			RunningGameScreen.displayToUser("You have defeated " + monster.getMonsterName() + "!");
			pDB.setMonsterDefeated(player.getPlayerID(), monster.getMonsterID());
		}
		if (playerIsAlive == false)
		{
			RunningGameScreen.playerDied();
		}
	}






	public int getDamage(int incomingWeaponID) throws SQLException
	{
		WeaponCtrl w = wDB.getWeapon(incomingWeaponID);
		try 
		{
			if (w.getWeaponType().equalsIgnoreCase(monster.getMonsterType()))
			{
				return w.getWeaponDamage()/2;
			}
			else
			{
				return w.getWeaponDamage();
			}
		}
		catch(NullPointerException npe)
		{
			return w.getWeaponDamage();
		}
	}

	public MonsterDB getmDB() 
	{
		return mDB;
	}

	public void setmDB(MonsterDB mDB) 
	{
		this.mDB = mDB;
	}

	public PlayerDB getpDB() 
	{
		return pDB;
	}

	public void setpDB(PlayerDB pDB) 
	{
		this.pDB = pDB;
	}

	public PlayerCtrl getPlayer() 
	{
		return player;
	}

	public void setPlayer(PlayerCtrl player) 
	{
		this.player = player;
	}

	public MonsterCtrl getMonster() 
	{
		return monster;
	}

	public void setMonster(MonsterCtrl monster) 
	{
		this.monster = monster;
	}

	public int getMonsterHitpoints()
	{
		return monsterHitpoints;
	}

	public boolean isMonsterIsAlive() {
		return monsterIsAlive;
	}

	public void setMonsterIsAlive(boolean monsterIsAlive) {
		this.monsterIsAlive = monsterIsAlive;
	}

	public boolean isPlayerIsAlive() {
		return playerIsAlive;
	}

	public void setPlayerIsAlive(boolean playerIsAlive) {
		this.playerIsAlive = playerIsAlive;
	}

	public void setMonsterHitpoints(int monsterHitpoints)
	{
		this.monsterHitpoints = monsterHitpoints;
	}

	public int getPlayerHitpoints()
	{
		return playerHitpoints;
	}

	public void setPlayerHitpoints(int playerHitpoints)
	{
		this.playerHitpoints = playerHitpoints;
	}	
}
