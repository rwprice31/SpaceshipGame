package controller;

import model.MonsterDB;
import model.PlayerDB;

public class BattleCtrl 
{
	
	private MonsterDB mDB;
	private PlayerDB pDB;
	private PlayerCtrl player;
	private MonsterCtrl monster;
	private int monsterHitpoints;
	private int playerHitpoints;
	
	public BattleCtrl(int incomingPlayerID, int incomingMonsterID)
	{
		mDB = new MonsterDB();
		pDB = new PlayerDB();
		player = pDB.getPlayer(incomingPlayerID);
		monster = mDB.getMonster(incomingMonsterID);
		monsterHitpoints = monster.getMonsterHitpoints();
		playerHitpoints = player.getPlayerHitpoints();
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
