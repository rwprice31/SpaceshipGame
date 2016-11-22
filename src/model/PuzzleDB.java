package model;
import java.sql.ResultSet;
import java.sql.SQLException;
import controller.PuzzleCtrl;

/**
 * 
 * @author Robert
 *
 */
public class PuzzleDB 
{
	private DBConnection dbc;
	
	public PuzzleDB() 
	{
		dbc = new DBConnection();
	}
	
	/** Name: getPuzzle
	 * returns a puzzlectrl object with information retrieved from the puzzle 
	 * table
	 * @param incomingPuzzleID
	 * @return
	 * @throws SQLException
	 */
	public PuzzleCtrl getPuzzle(int incomingPuzzleID) throws SQLException
	{
		ResultSet rs = dbc.query(dbc, "SELECT * FROM Puzzle");
		int puzzleID = 0;
		String puzzleName = null, solution = null, hint = null;
		while (rs.next())
		{
			puzzleID = rs.getInt("puzzleID");
			puzzleName = rs.getString("puzzleName");
			hint = rs.getString("hint");
			solution = rs.getString("solution");
		}
		return new PuzzleCtrl(puzzleID, puzzleName, hint, solution);
	}
	
	/** Name: getNumberOfPuzzleIDs
	 * 
	 * @return
	 * @throws SQLException
	 */
	public int getNumberOfPuzzleIDs() throws SQLException
	{
		ResultSet rs = dbc.query(dbc, "SELECT COUNT(puzzleID) FROM puzzle");
		int numberOfIDs = 0;
		while (rs.next())
		{
			numberOfIDs = rs.getInt("COUNT(puzzleID)");
		}
		return numberOfIDs;
	}
	
	/** Name: getAllLocationIDs
	 * 
	 * @return
	 * @throws SQLException
	 */
	public int[] getAllLocationIDs() throws SQLException 
	{
		int[] puzzleIDs = new int[getNumberOfPuzzleIDs()];
		int index = 0;
		ResultSet rs = dbc.query(dbc, "SELECT puzzleID FROM puzzle");
		while (rs.next()) 
		{
			puzzleIDs[index++] = rs.getInt("puzzleID");
		}
		return puzzleIDs;
	}
	
	
}
