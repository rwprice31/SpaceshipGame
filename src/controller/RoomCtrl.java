package controller;

public class RoomCtrl 
{
	private int roomID;
	private int monsterID;
	private int locationID;
	private int puzzleID;
	private int isStartingRoom;
	private String roomName;
	
	public RoomCtrl(int roomID, int monsterID, int locationID, int puzzleID, int isStartingRoom, String roomName)
	{
		this.roomID = roomID;
		this.monsterID = monsterID;
		this.locationID = locationID;
		this.puzzleID = puzzleID;
		this.isStartingRoom = isStartingRoom;
		this.roomName = roomName;
	}

	public int getRoomID() 
	{
		return roomID;
	}

	public void setRoomID(int roomID) 
	{
		this.roomID = roomID;
	}

	public int getMonsterID()
	{
		return monsterID;
	}

	public void setMonsterID(int monsterID) 
	{
		this.monsterID = monsterID;
	}

	public int getLocationID() 
	{
		return locationID;
	}

	public void setLocationID(int locationID) 
	{
		this.locationID = locationID;
	}

	public int getPuzzleID() {
		return puzzleID;
	}

	public void setPuzzleID(int puzzleID) 
	{
		this.puzzleID = puzzleID;
	}

	public int getIsStartingRoom() 
	{
		return isStartingRoom;
	}

	public void setIsStartingRoom(int isStartingRoom) 
	{
		this.isStartingRoom = isStartingRoom;
	}

	public String getRoomName() 
	{
		return roomName;
	}

	public void setRoomName(String roomName) 
	{
		this.roomName = roomName;
	}

	@Override
	public String toString() 
	{
		return "RoomCtrl [roomID=" + roomID + ", monsterID=" + monsterID + ", locationID=" + locationID + ", puzzleID="
				+ puzzleID + ", isStartingRoom=" + isStartingRoom + ", roomName=" + roomName + "]";
	}
	
	
	
	

}
