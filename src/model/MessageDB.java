package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.MessageCtrl;
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
		ResultSet rs = dbc.query(dbc, "SELECT * FROM message");
		int messageID = 0, roomID = 0, isStartingMessage = 0;
		String message = null;
		while (rs.next())
		{
			messageID = rs.getInt("messageID");
			roomID = rs.getInt("roomID");
			message = rs.getString("message");
			isStartingMessage = rs.getInt("isStartingMessage");
		}
		return new MessageCtrl(messageID, roomID, message, isStartingMessage);
	}
	
	public ArrayList<MessageCtrl> getMessagesForRoom(int roomID) throws SQLException 
	{
		ResultSet rs = dbc.query(dbc, "SELECT * FROM message WHERE roomID = " + roomID);
		ArrayList<MessageCtrl> roomIDs = new ArrayList<MessageCtrl>();
		while (rs.next())
		{
			roomIDs.add(new MessageCtrl(rs.getInt("messageID"), rs.getInt("roomID"), rs.getString("message"), rs.getInt("isStartingMessage")));
		}
		return roomIDs;
	}
	
	
	
	

}
