package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.MessageCtrl;
import controller.RoomCtrl;
import controller.ValidInputCtrl;

/**
 * 
 * @author Robert
 *
 */
public class MessageDB 
{
	private DBConnection dbc;
	
	public MessageDB()
	{
		dbc = new DBConnection();
	}
	
	/**
	 * 
	 * @param incomingMessageID
	 * @return
	 * @throws SQLException
	 */
	public MessageCtrl getMessage(int incomingMessageID) throws SQLException
	{
		ResultSet rs = dbc.query(dbc, "SELECT * FROM message WHERE messageID = " + incomingMessageID);
		int messageID = 0, roomID = 0, isStartingMessage = 0, locationID = 0;
		String message = null;
		while (rs.next())
		{
			messageID = rs.getInt("messageID");
			roomID = rs.getInt("roomID");
			message = rs.getString("message");
			isStartingMessage = rs.getInt("isStartingMessage");
			locationID = rs.getInt("locationID");
		}
		return new MessageCtrl(messageID, roomID, message, isStartingMessage, locationID);
	}
	
	public ArrayList<MessageCtrl> getMessagesForRoom(int roomID) throws SQLException 
	{
		ResultSet rs = dbc.query(dbc, "SELECT * FROM message WHERE roomID = " + roomID);
		ArrayList<MessageCtrl> roomIDs = new ArrayList<MessageCtrl>();
		while (rs.next())
		{
			roomIDs.add(new MessageCtrl(rs.getInt("messageID"), rs.getInt("roomID"), rs.getString("message"), rs.getInt("isStartingMessage"), rs.getInt("locationID")));
		}
		return roomIDs;
	}
	
	public MessageCtrl getStartingMessageForRoom(int incomingRoomID) throws SQLException
	{
		ResultSet rs = dbc.query(dbc, "SELECT messageID FROM message WHERE roomID = " + incomingRoomID + " AND isStartingMessage = 1");
		int messageID = 0;
		while (rs.next())
		{
			messageID = rs.getInt("messageID");
		}
		return getMessage(messageID);
	}
	
	public String getMessageWithID(int messageID) throws SQLException
	{
		ResultSet rs = dbc.query(dbc, "SELECT message FROM message WHERE messageID = " + messageID);
		String message = null;
		while (rs.next())
		{
			message = rs.getString("message");
		}
		return message;
	}
	
	public ArrayList<MessageCtrl> getMessagesForLocation(int locationID) throws SQLException
	{
		ResultSet rs = dbc.query(dbc, "SELECT * FROM message WHERE locationID = " + locationID);
		ArrayList<MessageCtrl> roomIDs = new ArrayList<MessageCtrl>();
		while (rs.next())
		{
			roomIDs.add(new MessageCtrl(rs.getInt("messageID"), rs.getInt("roomID"), rs.getString("message"), rs.getInt("isStartingMessage"), rs.getInt("locationID")));
		}
		return roomIDs;	
	}
	
	public int getNextMessageID(int messageID, int validInputID) throws SQLException
	{
		ResultSet rs = dbc.query(dbc, "SELECT nextMessageID FROM MessageValidInput WHERE messageID = " + messageID + " AND validInputID = " + validInputID);
		int nextMessageID = 0;
		while (rs.next())
		{
			nextMessageID = rs.getInt("nextMessageID");
		}
		return nextMessageID;
	}

	

}
