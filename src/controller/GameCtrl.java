package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.control.TextField;
import model.ExitDB;
import model.InventoryDB;
import model.MessageDB;
import model.PlayerDB;
import model.RoomDB;
import model.ValidInputDB;
import view.RunningGameScreen;

public class GameCtrl 
{
	private PlayerCtrl player;
	private PlayerDB pDB;
	private MessageDB mDB;
	private RoomDB rDB;
	private ExitDB eDB;
	private int locationID;
	//private Scanner sc;
	private String userInput;
	private ValidInputDB vDB;
	private RunningGameScreen game;
	private int currentPuzzleID;
	private InventoryDB iDB;
	private TextField userInputTF;
	private boolean continueGame;
	private boolean visited;
	private boolean isAnExit;
	private boolean isAValidInput;
	private int currentRoom;
	private int currentValidInputID;
	private int currentMessageID;
	private boolean mustShutDoor;
	private boolean isCompleted;

	public GameCtrl(int playerID, TextField userInputTF) throws SQLException 
	{
		this.userInputTF = userInputTF;
		mDB = new MessageDB();
		rDB = new RoomDB();
		//	sc = new Scanner(System.in);
		eDB = new ExitDB();
		vDB = new ValidInputDB();
		iDB = new InventoryDB();
		pDB = new PlayerDB();
		pDB = new PlayerDB();
//		game = new RunningGameScreen();
		this.player = pDB.getPlayer(playerID);
		this.locationID = player.getLocationID();
		userInput = null;
		continueGame = false;
		visited = false;
		isAnExit = false;
		isAValidInput = false;
		currentRoom = 0;
		currentValidInputID = 0;
		currentMessageID = 0;
		mustShutDoor = false;
		startSpaceship(playerID);

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

		//System.out.println(userInput);

		System.out.println("Hello");
		
		currentRoom = rDB.getStartingRoomForLocation(location);
		currentMessageID = mDB.getStartingMessageForRoom(rDB.getStartingRoomForLocation(location)).getMessageID();
		RunningGameScreen.displayToUser((mDB.getStartingMessageForRoom(currentRoom).getMessage()));
		//If the first room and first message in the location
		/*if (currentRoom == rDB.getStartingRoomForLocation(location) && currentMessageID == mDB.getStartingMessageForRoom(rDB.getStartingRoomForLocation(location)).getMessageID())
		{
			RunningGameScreen.displayToUser((mDB.getStartingMessageForRoom(currentRoom).getMessage()));
			//System.out.println("The current room is " + currentRoom + " the currentMessageID is " + currentMessageID);
		}
		*/
		
		userInputTF.setOnAction(e -> {
		
			userInput = RunningGameScreen.getUserInput();

			if (isCompleted == true)
			{
				RunningGameScreen.displayToUser("You completed the first location!");
			}
			else 
			{

				try 
				{

					
					//userInput = sc.nextLine(); 
					boolean boo = false;

					//Get messages for current room
					ArrayList<MessageCtrl> messagesForRoom = mDB.getMessagesForRoom(currentRoom);

					//Set default values
					isAnExit = false;
					isAValidInput = false;

					//Get the exits for current room
					ArrayList<ExitCtrl> exits = eDB.getExitsForSpecificRoom(currentRoom);
					for (ExitCtrl exit : exits)
					{
						//Check user input to see if it matches
						String s = "GO TO " + rDB.getRoom(exit.getEndingRoomID()).getRoomName();
						//System.out.println(userInput);
						if (userInput.equalsIgnoreCase(s.trim()))
						{
							//Set current room to the next room
							currentRoom = exit.getEndingRoomID();
							isAnExit = true;
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
										game.displayToUser(("You have completed the spaceship!"));
										pDB.setLocationCompleted(currentPlayerID, location);

									}
								}
							}
						}
					}
					if (isAValidInput == false && isAnExit == false && isCompleted == false)
					{
						game.displayToUser(("Invalid User Input"));

					}
					else if (isAValidInput == true && isCompleted == false)
					{		
						//We didn't change rooms
						currentMessageID = mDB.getNextMessageID(currentMessageID, currentValidInputID);
						game.displayToUser((mDB.getMessage(currentMessageID).getMessage()));
						if (currentMessageID == 11)
						{
							//Add crowbar to inventory
							game.displayToUser((iDB.addWeapon(currentPlayerID, 1)));
						}

						if (currentMessageID == 9)
						{
							//Add Spacesuit to inventory
							game.displayToUser((iDB.addSuitPart(currentPlayerID, 6)));
						}

					}
					else
					{
						if (isCompleted == false)
						{
							currentMessageID = mDB.getStartingMessageForRoom(currentRoom).getMessageID();
							//We changed rooms
							game.displayToUser((mDB.getStartingMessageForRoom(currentRoom).getMessage()));
						}
					}
				} catch (SQLException sqle) 
				{
					sqle.printStackTrace();
				}
			}
		});



	}

	/*	public void startOutside(int currentPlayerID) throws SQLException
	{
		int location = 2;
		MessageDB message = new MessageDB();
		ValidInputDB validInput = new ValidInputDB();
		ArrayList<MessageCtrl> messageAL = message.getMessagesForLocation(location);
		ArrayList<ArrayList<ValidInputCtrl>> validInputAL = new ArrayList<>();

		for (MessageCtrl m : messageAL)
		{
			validInputAL.add(validInput.getValidInputsForMessage(m.getMessageID()));
		}

		boolean isCompleted = false;
		boolean isAnExit = false;
		boolean isAValidInput = false;

		int currentRoom = rDB.getStartingRoomForLocation(location);
		int currentMessageID = mDB.getStartingMessageForRoom(rDB.getStartingRoomForLocation(location)).getMessageID();
		boolean visited = false;
		int currentValidInputID = 0;
		boolean playerInBuggy = false;

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
				//	System.out.println(rDB.getRoom(e.getEndingRoomID()).getRoomName());
				//Check user input to see if it matches
				String s = "GO TO " + rDB.getRoom(e.getEndingRoomID()).getRoomName();
				if (userInput.equalsIgnoreCase(s.trim()))
				{
					//Set current room to the next room
					currentRoom = e.getEndingRoomID();
					System.out.println(currentRoom);
					isAnExit = true;
					isCompleted = true;
				}
			}
			//If it's not an exit... check to see if it's a valid input
			if (isAnExit == false)
			{
				//Get the valid inputs for the current message
				for (ValidInputCtrl vAL: vDB.getValidInputsForMessage(currentMessageID))
				{
					//System.out.println(vAL.getCommand());
					if (userInput.equalsIgnoreCase(vAL.getCommand().trim()))
					{
						currentValidInputID = vAL.getValidInputID();
						//messageIntCounter++;
						isAValidInput = true;
					}
				}
			}

			if (currentMessageID == 18)
			{
				playerInBuggy = true;
			}

			if (currentRoom == 46)
			{
				if (playerInBuggy == true)
					startJungle(currentPlayerID);
				else
					playerDied();
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
			}
			else
			{
				if (isCompleted == false)
				{
					currentMessageID = mDB.getStartingMessageForRoom(currentRoom).getMessageID();
					//We changed rooms
					System.out.println(mDB.getStartingMessageForRoom(currentRoom).getMessage());
				}
			}
		}
	}


	public void startJungle(int currentPlayerID) throws SQLException
	{
		int location = 3;
		MessageDB message = new MessageDB();
		ValidInputDB validInput = new ValidInputDB();
		ArrayList<MessageCtrl> messageAL = message.getMessagesForLocation(location);
		ArrayList<ArrayList<ValidInputCtrl>> validInputAL = new ArrayList<>();

		for (MessageCtrl m : messageAL)
		{
			validInputAL.add(validInput.getValidInputsForMessage(m.getMessageID()));
		}

		boolean isCompleted = false;
		boolean isAnExit = false;
		boolean isAValidInput = false;
		boolean mustShutDoor = false;


		int currentRoom = rDB.getStartingRoomForLocation(location);
		int currentMessageID = mDB.getStartingMessageForRoom(rDB.getStartingRoomForLocation(location)).getMessageID();
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
			//userInput = sc.nextLine(); 

			//Get messages for current room
			ArrayList<MessageCtrl> messagesForRoom = mDB.getMessagesForRoom(currentRoom);

			//Set default values
			isAnExit = false;
			isAValidInput = false;

			//Add Stone Sword
			if (currentMessageID == 38)
			{
				System.out.println("Stone Sword Added");
				iDB.addWeapon(currentPlayerID, 2);
			}

			//Add Ship Part 1
			if (currentMessageID == 45)
			{
				System.out.println("Ship Part 1 Added");
				iDB.addShipPart(currentPlayerID, 1);
			}

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



						//Light Puzzle
						if (currentRoom == 12 && currentValidInputID == 26)
						{	
							currentPuzzleID = 2;
							String[] correctColorOrder = { "TURN ON RED", "TURN ON BLUE", "TURN ON PURPLE", "TURN ON BLUE", "TURN ON GREEN", "TURN ON YELLOW" }; 
							boolean fail = true;

							while (fail == true)
							{
								System.out.println(mDB.getMessage(mDB.getNextMessageID(currentMessageID, currentValidInputID)).getMessage());

								for (int i = 0; i < correctColorOrder.length; i++)
								{
									String userInput = sc.nextLine();
									if (userInput.equalsIgnoreCase(correctColorOrder[i]))
									{
										if (i == correctColorOrder.length - 1)
										{
											System.out.println("The door has opened!");
										}
										else
										{
											System.out.println("The door opens slightly");
										}
										fail = false;
									}
									else
									{
										System.out.println("The door has slammed shut, please try again.");
										fail = true;
										break;
									}
								}

							}
							pDB.setPuzzleCompleted(currentPlayerID, currentPuzzleID);
							System.out.println("You finished the puzzle!");
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

				if (currentRoom == 12 && currentValidInputID == 26 && pDB.hasPlayerCompletedPuzzle(currentPlayerID, 2) == true)
				{
					currentMessageID = 37;
					System.out.println(mDB.getMessage(currentMessageID).getMessage());
				}
				else
				{
					System.out.println(mDB.getMessage(currentMessageID).getMessage());
				}
			}
			else
			{
				if (isCompleted == false)
				{
					currentMessageID = mDB.getStartingMessageForRoom(currentRoom).getMessageID();

					//We changed rooms
					System.out.println(mDB.getStartingMessageForRoom(currentRoom).getMessage());

					//Vine Monster Battle
					if (currentRoom == 10 && pDB.hasPlayerDefeatedMonster(currentPlayerID, 1) == false)
					{
						BattleCtrl bc = new BattleCtrl(currentPlayerID, 1);
						bc.startBattle();
					}


				}
			}
		}

	}
	 */

	public void startGlacier() throws SQLException
	{

	}



	public void playerDied()
	{
		RunningGameScreen.displayToUser("Oh dear, you have died");
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

	/*public static void main(String[] args) throws SQLException
	{
		PlayerDB pDB = new PlayerDB();
		pDB.addIncompleteLocations(1);
		pDB.addUndefeatedMonsters(1);
		pDB.addIncompletedPuzzles(1);
	//	pDB.setPuzzleCompleted(1, 4);
	//	pDB.setLocationCompleted(1, 4);
		GameCtrl gc = new GameCtrl();
		try 
		{
			gc.startJungle(1);
		} catch (SQLException e) 
		{	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 */
}



