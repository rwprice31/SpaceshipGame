package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import model.ExitDB;
import model.MessageDB;
import model.PlayerDB;
import model.RoomDB;
import model.ValidInputDB;

public class GameCtrl 
{
	private PlayerCtrl player;
	private PlayerDB pDB;
	private MessageDB mDB;
	private RoomDB rDB;
	private ExitDB eDB;
	private Scanner sc;
	private String userInput;
	private int locationID;
	private ValidInputDB vDB;

	public GameCtrl(int playerID) 
	{
		pDB = new PlayerDB();
		this.player = pDB.getPlayer(playerID);
		this.locationID = player.getLocationID();
	}

	public GameCtrl() {
		// TODO Auto-generated constructor stub
		mDB = new MessageDB();
		rDB = new RoomDB();
		sc = new Scanner(System.in);
		eDB = new ExitDB();
		vDB = new ValidInputDB();
	}

	public void startJungle()
	{

	}

	public void startGlacier() throws SQLException
	{

	}

	public void startSpaceship() throws SQLException
	{
		int location = 1;
		MessageDB message = new MessageDB();
		ValidInputDB validInput = new ValidInputDB();
		ArrayList<MessageCtrl> messageAL = message.getMessagesForLocation(location);
		ArrayList<ArrayList<ValidInputCtrl>> validInputAL = new ArrayList<>();

		for (MessageCtrl m : messageAL)
		{
			validInputAL.add(validInput.getValidInputsForMessage(m.getMessageID()));
		}

		boolean isCompleted = false;
		int index = 0;
		boolean isAnExit = false;
		boolean isAValidInput = false;

		int currentRoom = rDB.getStartingRoomForLocation(location);
		int currentMessageID = mDB.getStartingMessageForRoom(rDB.getStartingRoomForLocation(location)).getMessageID();
		int messageIntCounter = 0;
		boolean visited = false;
		
		while (isCompleted == false)
		{
			//If the first room and first message in the location
			if (currentRoom == rDB.getStartingRoomForLocation(location) && currentMessageID == mDB.getStartingMessageForRoom(rDB.getStartingRoomForLocation(location)).getMessageID() && visited == false)
			{
				System.out.println(mDB.getStartingMessageForRoom(currentRoom).getMessage());
				//System.out.println("The current room is " + currentRoom + " the currentMessageID is " + currentMessageID);
				visited = true;
			}
			userInput = sc.nextLine(); 
			
			//Get messages for current room
			ArrayList<MessageCtrl> messagesForRoom = mDB.getMessagesForRoom(currentRoom);
			
			//Set default values
			isAnExit = false;
			isAValidInput = false;
			
			//Get the exits for current room
			ArrayList<ExitCtrl> exits = eDB.getExitsForSpecificRoom(currentRoom);
			for (ExitCtrl e : exits)
			{
				//Check user input to see if it matches
				String s = "GO TO " + rDB.getRoom(e.getEndingRoomID()).getRoomName();
				if (userInput.equalsIgnoreCase(s.trim()))
				{
					//Set current room to the next room
					currentRoom = e.getEndingRoomID();
					isAnExit = true;
					
					//Reset message int counter (entered a new room)
					messageIntCounter = 0;
				}
			}
			//If it's not an exit... check to see if it's a valid input
			if (isAnExit == false)
			{
				//Get the valid inputs for the current message
				for (ValidInputCtrl vAL: vDB.getValidInputsForMessage(currentMessageID))
				{
					if (userInput.equalsIgnoreCase(vAL.getCommand().trim()))
					{
						
						//Increment message int counter
						messageIntCounter++;
						isAValidInput = true;
					}
				}
			}
			if (isAValidInput == false && isAnExit == false)
			{
				System.out.println("Invalid User Input");
				System.out.println("User input is not valid, plrease enter in another string.");
				System.out.println("Hello World");;
			
			}
			else if (isAValidInput == true)
			{
				//We didn't change rooms
				System.out.println(messagesForRoom.get(messageIntCounter).getMessage());
				currentMessageID = messagesForRoom.get(messageIntCounter).getMessageID();
			}
			else
			{
				//	index = 0;
				index = mDB.getStartingMessageForRoom(currentRoom).getMessageID();
				currentMessageID = mDB.getStartingMessageForRoom(currentRoom).getMessageID();
				//We changed rooms
				System.out.println(mDB.getStartingMessageForRoom(currentRoom).getMessage());
			}

			//System.out.println("Current room now equals = " + currentRoom);
		}
	}

	public void startVolcano()
	{

	}

	public void startSpaceshipEscape()
	{

	}

	public void startCave()
	{

	}

	public void startDesert() 
	{

	}

	public static void main(String[] args)
	{
		GameCtrl gc = new GameCtrl();
		try 
		{
			gc.startSpaceship();
		} catch (SQLException e) 
		{	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}



