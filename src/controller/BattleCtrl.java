package controller;

import java.sql.SQLException;
import java.util.Scanner;

import model.InventoryDB;
import model.MonsterDB;
import model.PlayerDB;
import model.WeaponDB;

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
	private Scanner sc;

	public BattleCtrl(int incomingPlayerID, int incomingMonsterID)
	{
		mDB = new MonsterDB();
		pDB = new PlayerDB();
		iDB = new InventoryDB();
		wDB = new WeaponDB();
		player = pDB.getPlayer(incomingPlayerID);
		monster = mDB.getMonster(incomingMonsterID);
		monsterHitpoints = monster.getMonsterHitpoints();
		playerHitpoints = player.getPlayerHitpoints();
		sc = new Scanner(System.in);
		monsterIsAlive = true;
		playerIsAlive = true;
	}

	public int startBattle() throws SQLException
	{
		System.out.println("\n\tPlayer vs " + monster.getMonsterName());

		while (monsterIsAlive == true && playerIsAlive == true)
		{
			System.out.println(monster.getMonsterName() + " attacks, damaging you for " + monster.getMonsterDamage() 
			+ "\nYour current hitpoints are: " + (playerHitpoints - monster.getMonsterDamage()));
			
			playerHitpoints -= monster.getMonsterDamage();
			
			System.out.println("\nSelect a weapon to attack " + monster.getMonsterName());
			
			for (WeaponCtrl w: iDB.getWeapons(player.getInventoryID()))
			{
				System.out.println("\t" + w.getWeaponID() + ". " + w.getWeaponName());
			}
			
			String weaponSelection = sc.nextLine();
			int weaponID = Integer.parseInt(weaponSelection);
			
			System.out.println("You used your " + wDB.getWeaponName(Integer.parseInt(weaponSelection)) + " to damage " + monster.getMonsterName()
			+ " for " + getDamage(weaponID) + " hitpoints.");
			monsterHitpoints -=  getDamage(weaponID);
			System.out.println(monster.getMonsterName() + " currently has " + monsterHitpoints);

			if (playerHitpoints <= 0)
			{
				playerIsAlive = false;
			}

			if (monsterHitpoints <= 0)
			{
				monsterIsAlive = false;
			}
		}

		if (playerIsAlive == true)
		{
			pDB.setMonsterDefeated(player.getPlayerID(), monster.getMonsterID());
			return 1;
		}
		else
		{
			return 0;
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
