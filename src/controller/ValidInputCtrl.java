package controller;

public class ValidInputCtrl 
{
	private int validInputID;
	private String command;
	
	public ValidInputCtrl(int validInputID, String command)
	{
		this.validInputID = validInputID;
		this.command = command;
	}
	
	public int getValidInputID() 
	{
		return validInputID;
	}
	
	public void setValidInputID(int validInputID)
	{
		this.validInputID = validInputID;
	}
	
	public String getCommand() 
	{
		return command;
	}
	
	public void setCommand(String command) 
	{
		this.command = command;
	}

	@Override
	public String toString() 
	{
		return "ValidInputCtrl [validInputID=" + validInputID + ", command=" + command + "]";
	}
}
