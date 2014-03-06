package dirty.controller;

import java.sql.*;

import javax.swing.JOptionPane;

public class DirtyController
{
	private String connectionString;
	private Connection dataConnection;
	
	public DirtyController()
	{
		connectionString = "jdbc:mysql://localhost/cutomer_pm?user=root";
		
		checkDriver();
		setupConnection();
		
	}

	public void checkDriver()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (Exception currentException)
		{
			System.err.println("Unable to load the driver");
			System.err.println("Check that the ConnectorJ .jar file is loaded as an external JAR in the build path");
			System.err.println("The original .far should be in the ~/MySQL/ folder");
			System.exit(1);
		}
	}
	
	private void resetDatabaseConnection()
	{
		closeConnection();
		connectionString = "jdbc:mysql://localhost/?user=root";
		setupConnection();
	}
	
	private void closeConnection()
	{
		try
		{
			dataConnection.close();
		}
		
		catch(SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}
	}

	public void setupConnection()
	{
		try
		{
			dataConnection = DriverManager.getConnection(connectionString);
		}
		
		catch (SQLException currentException)
		{
			displaySQLErrors(currentException);
		}
	}
	
	public void createDatabase()
	{
		resetDatabaseConnection();
		
		try
		{
			Statement createDatabaseStatement = dataConnection.createStatement();
			
			int result = createDatabaseStatement.executeUpdate("CREATE DATABASE graveyard;");
			
		}
		
		catch(SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}
	}
	
	public void deleteDatabase()

	{
		try
		{
			Statement deleteDatabaseStatement = dataConnection.createStatement();
			
			int result = deleteDatabaseStatement.executeUpdate("DROP DATABASE graveyard;");
			
		}
		
		catch(SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}
	}
	
	public void createTable(String database, String tableName)
	{
		try
		{
			Statement createDatabaseTableStatement = dataConnection.createStatement();
			
			String createTable = "CREATE TABLE" +
					" '"+database + "'.'"+ tableName +"';";
			
			int result = createDatabaseTableStatement.executeUpdate("CREATE DATABASE graveyard;");
			
		}
		
		catch(SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}
	}
	
	public void deleteTable(String database, String tableName)
	{
		try
		{
			Statement deleteDatabaseTableStatement = dataConnection.createStatement();
			
			String createTable = "DROP '"+database + "'.'"+ tableName +"';";
			
			int result = deleteDatabaseTableStatement.executeUpdate("DROP DATABASE graveyard;");
			
		}
		
		catch(SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}
	}
	
	public void displaySQLErrors(SQLException current)
	{
		JOptionPane.showMessageDialog(null, "SQL Mesaage is:" + current.getMessage());
		JOptionPane.showMessageDialog(null, "SQL State is:" + current.getSQLState());
		JOptionPane.showMessageDialog(null, "Java error code is:" + current.getErrorCode());
	}
}
