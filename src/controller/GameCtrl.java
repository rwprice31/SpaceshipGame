package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import model.ExitDB;
import model.InventoryDB;
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
	private InventoryDB iDB;

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
		iDB = new InventoryDB();
		pDB = new PlayerDB();
	}

	public void startJungle()
	{

	}

	public void startGlacier() throws SQLException
	{

	}

	public void startSpaceship(int currentPlayerID) throws SQLException
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
		boolean mustShutDoor = false;
		

		int currentRoom = rDB.getStartingRoomForLocation(location);
		int currentMessageID = mDB.getStartingMessageForRoom(rDB.getStartingRoomForLocation(location)).getMessageID();
		int messageIntCounter = 0;
		boolean visited = false;
		int currentValidInputID = 0;

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


						currentValidInputID = vAL.getValidInputID();
						//Increment message int counter
						//messageIntCounter++;
						isAValidInput = true;
						
						if (currentMessageID == 12 && currentValidInputID == 11)
						{
							mustShutDoor = true;
						}
						
						if ((currentMessageID == 15 && currentValidInputID == 5) || (currentMessageID == 14 && currentValidInputID == 5))
						{
							ArrayList<SuitPartCtrl> spAL = iDB.getSuitParts(currentPlayerID);
							boolean doesPlayerHaveSuit = false;
							for (SuitPartCtrl s : spAL)
							{
								if (s.getSuitPartName().equalsIgnoreCase("Spacesuit"))
								{
									doesPlayerHaveSuit = true;
								}
							}
							if (doesPlayerHaveSuit == false || mustShutDoor == false)
							{
								playerDied();
								System.exit(0);
							}
							else
							{
								isCompleted = true;
								System.out.println("You have completed the spaceship!");
								pDB.setLocationCompleted(1, location);

							}
						}
					}
				}
			}
			if (isAValidInput == false && isAnExit == false && isCompleted == false)
			{
				System.out.println("Invalid User Input");

			}
			else if (isAValidInput == true && isCompleted == false)
			{		
				//We didn't change rooms
				currentMessageID = mDB.getNextMessageID(currentMessageID, currentValidInputID);
				System.out.println(mDB.getMessage(currentMessageID).getMessage());
				if (currentMessageID == 11)
				{
					//Add crowbar to inventory
					System.out.println(iDB.addWeapon(currentPlayerID, 1));
				}

				if (currentMessageID == 9)
				{
					//Add Spacesuit to inventory
					System.out.println(iDB.addSuitPart(currentPlayerID, 6));
				}

			}
			else
			{
				if (isCompleted == false)
				{
					index = mDB.getStartingMessageForRoom(currentRoom).getMessageID();
					currentMessageID = mDB.getStartingMessageForRoom(currentRoom).getMessageID();
					//We changed rooms
					System.out.println(mDB.getStartingMessageForRoom(currentRoom).getMessage());
				}
			}
		}

	}

	public void playerDied()
	{
		System.out.println("Oh dear, you have died");
		sc.close();
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

	public static void main(String[] args) throws SQLException
	{
		PlayerDB pDB = new PlayerDB();
		pDB.addPlayer(new PlayerCtrl("Robert"));
		pDB.addIncompleteLocations(1);
		GameCtrl gc = new GameCtrl();
		try 
		{
			gc.startSpaceship(1);
		} catch (SQLException e) 
		{	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}



