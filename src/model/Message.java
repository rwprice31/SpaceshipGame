package model;

public class Message {

	private int messageID;
	private int roomID;
	private String messageString;
	
	public Message(int messageID, int roomID, String messageString) {
		this.messageID = messageID;
		this.roomID = roomID;
		this.messageString = messageString;
	}

	public int getMessageID() {
		return messageID;
	}

	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public String getMessageString() {
		return messageString;
	}

	public void setMessageString(String messageString) {
		this.messageString = messageString;
	}
	
	
	
	
	
}
