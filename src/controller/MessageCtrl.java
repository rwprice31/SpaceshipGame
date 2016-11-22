package controller;

public class MessageCtrl 
{
	private int messageID;
	private int roomID;
	private String message;
	private int isStartingMessage;

	public MessageCtrl(int messageID, int roomID, String message, int isStartingMessage) 
	{
		this.messageID = messageID;
		this.roomID = roomID;
		this.message = message;
		this.isStartingMessage = isStartingMessage;
	}

	public int getMessageID() 
	{
		return messageID;
	}

	public void setMessageID(int messageID) 
	{
		this.messageID = messageID;
	}

	public int getRoomID() 
	{
		return roomID;
	}

	public void setRoomID(int roomID)
	{
		this.roomID = roomID;
	}

	public String getMessage() 
	{
		return message;
	}

	public void setMessage(String message) 
	{
		this.message = message;
	}

	public int getIsStartingMessage() 
	{
		return isStartingMessage;
	}

	public void setIsStartingMessage(int isStartingMessage) 
	{
		this.isStartingMessage = isStartingMessage;
	}

	@Override
	public String toString() 
	{
		return "MessageCtrl [messageID=" + messageID + ", roomID=" + roomID + ", message=" + message
				+ ", isStartingMessage=" + isStartingMessage + "]";
	}
}
