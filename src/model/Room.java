package model;

public class Room {

	private int roomID;
	private String roomName;
	private int monsterID;
	private int puzzleID;
	
	public Room(int roomID, String roomName, int monsterID, int puzzleID) {
		this.roomID = roomID;
		this.roomName = getroomName(roomID);
		this.monsterID = monsterID;
		this.puzzleID = puzzleID;
	}

	public int getRoomID(roomID) {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public String getRoomName(roomID) {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public int getMonsterID(monsterID) {
		return monsterID;
	}

	public void setMonsterID(int monsterID) {
		this.monsterID = monsterID;
	}

	public int getPuzzleID(puzzleID) {
		return puzzleID;
	}

	public void setPuzzleID(int puzzleID) {
		this.puzzleID = puzzleID;
	}
	
	
	
	
	
}
