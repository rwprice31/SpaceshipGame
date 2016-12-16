package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.MessageCtrl;
import controller.ValidInputCtrl;

public class ValidInputDB 
{
	private DBConnection dbc;
	
	public ValidInputDB() 
	{
		dbc = new DBConnection();
	}
	
	public ValidInputCtrl getValidInput(int incomingValidInputID) throws SQLException
	{
		ResultSet rs = dbc.query(dbc, "SELECT * FROM validinput");
		int validInputID = 0; 
		String command = null;
		while (rs.next())
		{
			validInputID = rs.getInt("validInputID");
			command = rs.getString("command");
		}
		return new ValidInputCtrl(validInputID, command);
	}
	
	public ArrayList<ValidInputCtrl> getValidInputsForMessage(int incomingMessageID) throws SQLException
	{
		ResultSet rs = dbc.query(dbc, "SELECT * FROM ValidInput WHERE validInputID IN (SELECT validInputID FROM MessageValidInput WHERE messageID = " + incomingMessageID +")");
		ArrayList<ValidInputCtrl> validInputsAL = new ArrayList<ValidInputCtrl>();
		int validInputID = 0; 
		String command = null;
		while (rs.next())
		{
			validInputID = rs.getInt("validInputID");
			command = rs.getString("command");
			validInputsAL.add(new ValidInputCtrl(validInputID, command));
		}
		return validInputsAL;
	}
}
