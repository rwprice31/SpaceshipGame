package controller;

public class ExitCtrl 
{
	private int exitID;
	private int startingRoomID;
	private int endingRoomID;
	
	public ExitCtrl(int exitID, int startingRoomID, int endingRoomID)
	{
		this.exitID = exitID;
		this.startingRoomID = startingRoomID;
		this.endingRoomID = endingRoomID;
	}

	public int getExitID() 
	{
		return exitID;
	}

	public void setExitID(int exitID) 
	{
		this.exitID = exitID;
	}

	public int getStartingRoomID() 
	{
		return startingRoomID;
	}

	public void setStartingRoomID(int startingRoomID) 
	{
		this.startingRoomID = startingRoomID;
	}

	public int getEndingRoomID() 
	{
		return endingRoomID;
	}

	public void setEndingRoomID(int endingRoomID)
	{
		this.endingRoomID = endingRoomID;
	}

	@Override
	public String toString() 
	{
		return "ExitCtrl [exitID=" + exitID + ", startingRoomID=" + startingRoomID + ", endingRoomID=" + endingRoomID
				+ "]";
	}
	
	
	
	
}
