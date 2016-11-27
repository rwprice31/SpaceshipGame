package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import model.MessageDB;
import model.PlayerDB;
import model.ValidInputDB;

public class GameCtrl 
{
	private PlayerCtrl player;
	private PlayerDB pDB;
	private MessageDB mDB;
	private Scanner sc;
	private String userInput;

	public GameCtrl(int playerID) 
	{
		pDB = new PlayerDB();
		this.player = pDB.getPlayer(playerID);
	}

	public GameCtrl() {
		// TODO Auto-generated constructor stub
		mDB = new MessageDB();
		sc = new Scanner(System.in);
	}

	public void startJungle()
	{

	}

	public void startGlacier()
	{

	}

	public void startSpaceship() throws SQLException
	{
		MessageDB message = new MessageDB();
		Boolean boo = false;
		Boolean isCompleted = false;
		ValidInputDB validInput = new ValidInputDB();
		ArrayList<MessageCtrl> messageAL = message.getMessagesForLocation(1);
		ArrayList<ArrayList<ValidInputCtrl>> validInputAL = new ArrayList<>();

		for (MessageCtrl m : messageAL)
		{
			validInputAL.add(validInput.getValidInputsForMessage(m.getMessageID()));
		}

		int currentRoom = 1;
		while (isCompleted == false)
		{
			System.out.println(messageAL.get(currentRoom).getMessage());
			userInput = sc.nextLine();
			for (ValidInputCtrl vc : validInputAL.get(currentRoom))
			{
				if (userInput.equals(vc.getCommand())) 
				{
					boo = true;
				}
				else
				{
					System.out.println("Invalid input");
				}
			}
		}


			/*MessageDB message = new MessageDB();
		Boolean boo = false;
		ValidInputDB validInput = new ValidInputDB();
		ArrayList<MessageCtrl> messageAL = message.getMessagesForLocation(1);
		ArrayList<ArrayList<ValidInputCtrl>> validInputAL = new ArrayList<>();

		for (MessageCtrl m : messageAL)
		{
			validInputAL.add(validInput.getValidInputsForMessage(m.getMessageID()));
		}


		do 
		{
			System.out.println(messageAL.get(0).getMessage());
			userInput = sc.nextLine();
			for (ValidInputCtrl vc : validInputAL.get(0))
			{
				if (userInput.equals(vc.getCommand())) 
				{
					boo = true;
				}
				else
				{
					System.out.println("Invalid input");
				}
			}
		}while(boo == false);
		boo = false;

		do 
		{
			System.out.println(messageAL.get(1).getMessage());
			userInput = sc.nextLine();
			for (ValidInputCtrl vc : validInputAL.get(1))
			{
				if (userInput.equals(vc.getCommand())) 
				{
					boo = true;
				}
				else
				{
					System.out.println("Invalid input");
				}
			}
		}while(boo == false);
		boo = false;

		do 
		{
			System.out.println(messageAL.get(2).getMessage());
			userInput = sc.nextLine();
			for (ValidInputCtrl vc : validInputAL.get(2))
			{
				if (userInput.equals(vc.getCommand())) 
				{
					boo = true;
				}
				else
				{
					System.out.println("Invalid input");
				}
			}
		}while(boo == false);
		boo = false;

		do 
		{
			System.out.println(messageAL.get(4).getMessage());
			userInput = sc.nextLine();
			for (ValidInputCtrl vc : validInputAL.get(4))
			{
				if (userInput.equals(vc.getCommand())) 
				{
					boo = true;
				}
				else
				{
					System.out.println("Invalid input");
				}
			}
		}while(boo == false);
		boo = false;

		do 
		{
			System.out.println(messageAL.get(5).getMessage());
			userInput = sc.nextLine();
			for (ValidInputCtrl vc : validInputAL.get(5))
			{
				if (userInput.equals(vc.getCommand())) 
				{
					boo = true;
				}
			}
			if (boo == false)
				System.out.println("Invalid input");
		}while(boo == false);
		boo = false;

		if (userInput.equalsIgnoreCase("GO TO ENGINE ROOM"))
		{
			do 
			{
				System.out.println(messageAL.get(9).getMessage());
				userInput = sc.nextLine();
				for (ValidInputCtrl vc : validInputAL.get(9))
				{
					if (userInput.equals(vc.getCommand())) 
					{
						boo = true;
					}
				}
				if (boo == false)
					System.out.println("Invalid input");
			}while(boo == false);
			boo = false;

		}
		else if (userInput.equalsIgnoreCase("GO TO DECOMPRESSION ROOM"))
		{
			do 
			{
				System.out.println(messageAL.get(11).getMessage());
				userInput = sc.nextLine();
				for (ValidInputCtrl vc : validInputAL.get(11))
				{
					if (userInput.equals(vc.getCommand())) 
					{
						boo = true;
					}
				}
				if (boo == false)
					System.out.println("Invalid input");
			}while(boo == false);
			boo = false;

		}
		else
		{
			do 
			{
				System.out.println(messageAL.get(6).getMessage());
				userInput = sc.nextLine();
				for (ValidInputCtrl vc : validInputAL.get(6))
				{
					if (userInput.equals(vc.getCommand())) 
					{
						boo = true;
					}
				}
				if (boo == false)
					System.out.println("Invalid input");
			}while(boo == false);
			boo = false;
		}

		 1 You wake up laying on the floor, very confused, and it is pitch black. Maybe you should STAND UP?
		2	You are standing up. You see a lamp, maybe you should TURN ON LAMP.
		3	The lamp is turned on, you see a bedroom, with a bed, a lamp, a dresser, and an exit door. Maybe you should SEARCH THE DRESSER?
		4	You find a key and a flashlight. Maybe you should USE THE KEY TO UNLOCK THE DOOR?
		5	The door is open, you should EXIT the room.
		6	You are now in the central room of the spaceship, you see signs for the Engine Room, Decompression Room, and Cockpit, as well as a small window to the outside. Which room do you want to GO TO?
		7	You are now standing in the cockpit. You see flight controls and a spacesuit that you can PICK UP.
		8	You pick up the space suit, you need to PUT it ON for it to be useful.
		9	You are now wearing the spacesuit. There's nothing else for you to do here, except to EXIT.
		10	You are now in the engine room. You see that the engine for the spaceship is destryoed, you must find 6 parts to fix it. Also you see a crowbar that you should PICK UP.
		11	You picked up the crowbar. There's nothing left for you to do in this room right now, you should EXIT.
		12	You are now in the decompression room and you see that the exit door is jammed. However, the main door needs to be closed. Do you want to CLOSE the main DOOR, or do you want to USE the CROWBAR on the exit door?
		13	The door is now closed, that should be safer. You see an exit door that looks like it could be opened with a crowbar. Do you want to USE the CROWBAR ON the exit door?
		14	The door is now open to you wish to EXIT the spaceship, or LOOK OUT WINDOW?
		15	You see a barren wasteland of a planet. You will need equipment to survive. Do you wish to EXIT the SPACESHIP?
		16	Oh dear, you have died.
			 */
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
