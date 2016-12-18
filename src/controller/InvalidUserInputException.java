package controller;

public class InvalidUserInputException extends RuntimeException
{
	public InvalidUserInputException()
	{
		super("Invalid input. Try again.");
	}
}
