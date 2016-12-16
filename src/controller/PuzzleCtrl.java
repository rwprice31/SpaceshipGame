package controller;

/**
 * 
 * @author Robert
 *
 */
public class PuzzleCtrl
{
	private int puzzleID;
	private String puzzleName;
	private String hint;
	private String solution;
	
	public PuzzleCtrl(int puzzleID, String puzzleName, String hint, String solution) 
	{
		this.puzzleID = puzzleID;
		this.puzzleName = puzzleName;
		this.hint = hint;
		this.solution = solution;
	}

	public int getPuzzleID()
	{
		return puzzleID;
	}

	public void setPuzzleID(int puzzleID) 
	{
		this.puzzleID = puzzleID;
	}

	public String getPuzzleName() 
	{
		return puzzleName;
	}

	public void setPuzzleName(String puzzleName)
	{
		this.puzzleName = puzzleName;
	}

	public String getHint()
	{
		return hint;
	}

	public void setHint(String hint) 
	{
		this.hint = hint;
	}

	public String getSolution() 
	{
		return solution;
	}

	public void setSolution(String solution)
	{
		this.solution = solution;
	}

	@Override
	public String toString() 
	{
		return "PuzzleCtrl [puzzleID=" + puzzleID + ", puzzleName=" + puzzleName + ", hint=" + hint + ", solution="
				+ solution + "]";
	}
}
