package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.control.TextField;
import model.ExitDB;
import model.InventoryDB;
import model.MessageDB;
import model.MonsterDB;
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
	private int index;
	private boolean continueGame;
	private boolean visited;
	private boolean isAnExit;
	private boolean isAValidInput;
	private boolean playerIsAlive;
	private boolean playerIsAttacking;
	private int currentRoom;
	private int currentValidInputID;
	private int battleIndex;
	private int currentMessageID;
	private int puzzleIndex;
	private int vineMonsterStartingHitpoints;
	private int playerHitpoints;
	private boolean mustShutDoor;
	private boolean isCompleted;
	private boolean playerInBuggy;
	private boolean puzzleFail;

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
		index = 0;
		visited = false;
		isAnExit = false;
		isAValidInput = false;
		currentRoom = 0;
		puzzleIndex = 0;
		currentValidInputID = 0;
		currentMessageID = 0;
		mustShutDoor = false;
		puzzleFail = false;
		playerInBuggy = false;
		playerIsAttacking = false;




		if (pDB.getPlayer(playerID).getLocationID() == 1)
			startSpaceship(playerID);
		else if (pDB.getPlayer(playerID).getLocationID() == 2)
			startOutside(playerID);
		else if (pDB.getPlayer(playerID).getLocationID() == 3)
			startJungle(playerID);
		else 
			System.out.println("Invalid location id");

	}

	public void startSpaceship(int currentPlayerID) throws SQLException
	{
		playerIsAlive = true;
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
									System.out.println("Does the player have the suit? " + doesPlayerHaveSuit);
									if (doesPlayerHaveSuit == false || mustShutDoor == false)
									{
										RunningGameScreen.playerDied();
										isAValidInput = false;
										isAnExit = false;
										isCompleted = true;
									}
									else
									{
										//isCompleted = true;
										RunningGameScreen.displayToUser(("You have completed the spaceship!"));
										pDB.setLocationCompleted(currentPlayerID, location);
										startOutside(currentPlayerID);
									}
								}
							}
						}
					}
					if (isAValidInput == false && isAnExit == false && isCompleted == false)
					{
						RunningGameScreen.displayToUser(("Invalid User Input"));

					}
					else if (isAValidInput == true && isCompleted == false)
					{		
						//We didn't change rooms
						currentMessageID = mDB.getNextMessageID(currentMessageID, currentValidInputID);
						RunningGameScreen.displayToUser((mDB.getMessage(currentMessageID).getMessage()));
						if (currentMessageID == 11)
						{
							//Add crowbar to inventory
							RunningGameScreen.displayToUser((iDB.addWeapon(currentPlayerID, 1)));
						}

						if (currentMessageID == 9)
						{
							//Add Spacesuit to inventory
							RunningGameScreen.displayToUser((iDB.addSuitPart(currentPlayerID, 6)));
						}

					}
					else
					{
						if (isCompleted == false)
						{
							currentMessageID = mDB.getStartingMessageForRoom(currentRoom).getMessageID();
							//We changed rooms
							RunningGameScreen.displayToUser((mDB.getStartingMessageForRoom(currentRoom).getMessage()));
						}

					}
				} catch (SQLException sqle) 
				{
					sqle.printStackTrace();
				}
			}
		});



	}

	public void startOutside(int currentPlayerID) throws SQLException
	{
		int location = 2;
		MessageDB message = new MessageDB();
		ValidInputDB validInput = new ValidInputDB();

		currentRoom = rDB.getStartingRoomForLocation(location);
		currentMessageID = mDB.getStartingMessageForRoom(rDB.getStartingRoomForLocation(location)).getMessageID();

		//System.out.println(mDB.getStartingMessageForRoom(currentRoom).getMessage());
		RunningGameScreen.displayToUser((mDB.getStartingMessageForRoom(currentRoom).getMessage()));


		//Set default values
		isAnExit = false;
		isAValidInput = false;
		isCompleted = false;
		userInput = null;

		userInputTF.setOnAction(e -> {

			userInput = RunningGameScreen.getUserInput();
			try
			{



				//Get the exits for current room
				ArrayList<ExitCtrl> exits = eDB.getExitsForSpecificRoom(currentRoom);
				for (ExitCtrl exit : exits)
				{
					//	System.out.println(rDB.getRoom(e.getEndingRoomID()).getRoomName());
					//Check user input to see if it matches
					String s = "GO TO " + rDB.getRoom(exit.getEndingRoomID()).getRoomName();
					if (userInput.equalsIgnoreCase(s.trim()))
					{
						//Set current room to the next room
						currentRoom = exit.getEndingRoomID();
						isAnExit = true;
						//isCompleted = true;

						if (currentRoom == 46)
						{
							if (playerInBuggy == true)
								startJungle(currentPlayerID);
							else
								playerDied();
						}

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

				if (isAValidInput == false && isAnExit == false && isCompleted == false)
				{
					RunningGameScreen.displayToUser("Invalid User Input");

				}
				else if (isAValidInput == true && isCompleted == false)
				{		
					//We didn't change rooms
					currentMessageID = mDB.getNextMessageID(currentMessageID, currentValidInputID);
					RunningGameScreen.displayToUser(mDB.getMessage(currentMessageID).getMessage());

					if (currentMessageID == 18)
					{
						playerInBuggy = true;
					}

				}
				else
				{
					if (isCompleted == false)
					{
						currentMessageID = mDB.getStartingMessageForRoom(currentRoom).getMessageID();
						//We changed rooms
						RunningGameScreen.displayToUser(mDB.getStartingMessageForRoom(currentRoom).getMessage());
					}
				}
			} catch (SQLException sqle)
			{
				sqle.printStackTrace();
			}
		});

	}

	public void startJungle(int currentPlayerID) throws SQLException
	{
		int location = 3;
		isCompleted = false;
		MonsterDB monDB = new MonsterDB();
		MessageDB message = new MessageDB();
		ValidInputDB validInput = new ValidInputDB();
		ArrayList<MessageCtrl> messageAL = message.getMessagesForLocation(location);

		currentRoom = rDB.getStartingRoomForLocation(location);
		currentMessageID = mDB.getStartingMessageForRoom(rDB.getStartingRoomForLocation(location)).getMessageID();
		RunningGameScreen.displayToUser((mDB.getStartingMessageForRoom(currentRoom).getMessage()));

		puzzleIndex = 0;
		battleIndex = 1;
		vineMonsterStartingHitpoints = monDB.getMonster(1).getMonsterHitpoints();
		playerHitpoints = pDB.getPlayer(currentPlayerID).getPlayerHitpoints();


		//Set default values
		isAnExit = false;
		isAValidInput = false;
		isCompleted = false;
		userInput = null;

		puzzleFail = true;
		currentPuzzleID = 2;
		userInputTF.setOnAction(e -> {


			userInput = RunningGameScreen.getUserInput();	

			if (isCompleted == true)
			{
				RunningGameScreen.displayToUser("You completed the jungle!");
			}

			else if (currentRoom == 12 && currentValidInputID == 26 && puzzleFail == true)
			{
				//	RunningGameScreen.displayToUser("Has player completed? " + pDB.hasPlayerCompletedPuzzle(currentPlayerID, currentPuzzleID));
				try
				{


					String[] correctColorOrder = { "TURN ON RED", "TURN ON BLUE", "TURN ON PURPLE", "TURN ON BLUE", "TURN ON GREEN", "TURN ON YELLOW" };

					if (puzzleIndex == 0)
					{
						if (mDB.getMessage(mDB.getNextMessageID(currentMessageID, currentValidInputID)).getMessage() != null)
							RunningGameScreen.displayToUser(mDB.getMessage(mDB.getNextMessageID(currentMessageID, currentValidInputID)).getMessage());
					}

					if (userInput.equalsIgnoreCase(correctColorOrder[puzzleIndex]))
					{
						if (puzzleIndex == correctColorOrder.length - 1)
						{
							RunningGameScreen.displayToUser("The door opens! You have completed the puzzle!!");
							pDB.setPuzzleCompleted(currentPlayerID, currentPuzzleID);
							puzzleIndex = 0;
							//	currentMessageID = mDB.getNextMessageID(currentMessageID, currentValidInputID);
							currentMessageID = 37;
							RunningGameScreen.displayToUser((mDB.getMessage(currentMessageID).getMessage()));
							puzzleFail = false;
						}
						else
						{
							RunningGameScreen.displayToUser("The door opens slightly");
							puzzleIndex++;
						}

					}
					else
					{
						RunningGameScreen.displayToUser("The door has slammed shut, please try again");
						puzzleIndex = 0;
					}
				}
				catch(SQLException sqle)
				{
					sqle.printStackTrace();
				}

			}
			//Vine Monster Battle
			else if (currentRoom == 10 && pDB.hasPlayerDefeatedMonster(currentPlayerID, 1) == false)
			{
				if (battleIndex % 2 == 0)
				{
					playerIsAttacking = true;
				}
				else
				{
					playerIsAttacking = false;
				}

				BattleCtrl bc = new BattleCtrl(currentPlayerID, 1, userInput, playerHitpoints, vineMonsterStartingHitpoints, battleIndex, playerIsAttacking);
				try {
					bc.startBattle();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				playerHitpoints = bc.getPlayerHitpoints();
				vineMonsterStartingHitpoints = bc.getMonsterHitpoints();
				battleIndex++;
			}
			else 
			{
				try 
				{
					isAnExit = false;
					isAValidInput = false;



					//					RunningGameScreen.displayToUser(currentRoom + "");

					ArrayList<ExitCtrl> exits = eDB.getExitsForSpecificRoom(currentRoom);
					for (ExitCtrl exit : exits)
					{
						String s = "GO TO " + rDB.getRoom(exit.getEndingRoomID()).getRoomName();
						if (userInput.equalsIgnoreCase(s.trim()))
						{
							currentRoom = exit.getEndingRoomID();
							isAnExit = true;
						}
					}
					if (isAnExit == false)
					{
						for (ValidInputCtrl vAL : vDB.getValidInputsForMessage(currentMessageID))
						{
							if (userInput.equalsIgnoreCase(vAL.getCommand().trim()))
							{
								currentValidInputID = vAL.getValidInputID();
								isAValidInput = true;
							}

							//Add Stone Sword
							if (currentMessageID == 37 && currentValidInputID == 9)
							{
								RunningGameScreen.displayToUser("Weapon Added");
								iDB.addWeapon(currentPlayerID, 2);
							}
						}
					}
					if (isAValidInput == false && isAnExit == false && isCompleted == false)
					{
						RunningGameScreen.displayToUser("Invalid User Input");
					}
					else if (isAValidInput == true && isCompleted == false)
					{
						currentMessageID = mDB.getNextMessageID(currentMessageID, currentValidInputID);
						RunningGameScreen.displayToUser((mDB.getMessage(currentMessageID).getMessage()));

					}
					else
					{
						if (isCompleted == false)
						{
							currentMessageID = mDB.getStartingMessageForRoom(currentRoom).getMessageID();
							RunningGameScreen.displayToUser((mDB.getStartingMessageForRoom(currentRoom).getMessage()));
						}
					}
				}
				catch (SQLException sqle)
				{
					sqle.printStackTrace();
				}

			}
		});
	}


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



