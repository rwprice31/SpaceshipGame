package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import model.MessageDB;
import model.ValidInputDB;

public class MessageCtrl 
{
	private int messageID;
	private int roomID;
	private String message;
	private int isStartingMessage;
	private int locationID;
	private ArrayList<ValidInputCtrl> validInputs;
	private ValidInputDB vDB;

	public MessageCtrl(int messageID, int roomID, String message, int isStartingMessage, int locationID) throws SQLException 
	{
		vDB = new ValidInputDB();
		this.messageID = messageID;
		this.roomID = roomID;
		this.message = message;
		this.isStartingMessage = isStartingMessage;
		this.locationID = locationID;
		this.validInputs = vDB.getValidInputsForMessage(this.messageID);
	}
	
	public ArrayList<ValidInputCtrl> getValidInputs() 
	{
		return validInputs;
	}

	public void setValidInputs(ArrayList<ValidInputCtrl> validInputs) 
	{
		this.validInputs = validInputs;
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
