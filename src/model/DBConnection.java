package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**Class:
 * @author Robert (code provided by Richard Price)
 * @version: 1.0
 * Course: ITEC 3860 Fall 2016
 * Written: November 11th, 2016
 * 
 * This class - uses the SQLite JDBC driver to access a SQLite database
 * 
 * Purpose: Creates a means of connecting to the database.
 */

public class DBConnection {

	private Connection conn;
	private Statement stmt;
	private int timeOut;
	
	public DBConnection()
	{
		//register the driver name
		String sDriver = "org.sqlite.JDBC";
		try
		{
			Class.forName(sDriver);
		}
		catch (ClassNotFoundException cfne)
		{
			System.out.println(cfne.getMessage());
		}
		
		//Build the URL for SQLite DB
		String tempDB = "Game.db";
		String jdbc = "jdbc:sqlite";
		String dbURL = jdbc + ":" + tempDB;
		
		//Set db timeout
		timeOut = 30;
		
		try
		{
			//Establish a connection to the database
			conn = DriverManager.getConnection(dbURL);
			//Create a container for the SQL statement
			stmt = conn.createStatement();
			//Set timeout on the statement
			stmt.setQueryTimeout(timeOut);
		}
		catch (SQLException sqe)
		{
			System.out.println(sqe.getMessage());			
		}
	}
	
	public ResultSet query(DBConnection dbc, String sql)
	{
		ResultSet rs = null;
		try
		{
			rs = dbc.stmt.executeQuery(sql);
		} 
		catch (SQLException sqe)
		{
			sqe.printStackTrace();
		}
		return rs;
	}
	
	public boolean modData(DBConnection dbc, String sql)
	{
		boolean success = true;
		try
		{
			dbc.stmt.executeUpdate(sql);
		} 
		catch (SQLException sqe)
		{
			success = false;
		}
		return success;
	}
	
}
