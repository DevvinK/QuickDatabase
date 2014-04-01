package data.controller;

import java.sql.*;
import java.util.Vector;

import javax.swing.JOptionPane;

import data.model.Person;

public class DataController
{
	/**
	 * The current ConnectionString
	 */
	private String connectionString;
	/**
	 * The current 
	 */
	private Connection dataConnection;
	/**
	 * 
	 */
	private AppController baseController;

	
	/**
	 * Creates a DatabaseController with a reference to he AppController for message passing.
	 * @param baseController
	 */
	public DataController(AppController baseController)
	{
		connectionString = "jdbc:mysql://localhost/cutomer_pm?user=root";
		this.baseController = baseController;

		checkDriver();
		setupConnection();

	}

	/**
	 * Checks that the driver 
	 */
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

	/**
	 * 
	 */
	private void resetDatabaseConnection()
	{
		closeConnection();
		setupConnection();
	}

	/**
	 * 
	 */
	private void closeConnection()
	{
		try
		{
			dataConnection.close();
		}

		catch (SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}
	}
	
/**
 	* Builds a Java connectionString for a MySQL database with the supplied fields for server path, database, username, and password to access the database.
 * @param serverPath The path to the server. This can be an IP address or a URL string.
 * @param database The name of the database you are connecting to. If left blamk, the connectionString is for the server itself. Remember that it is case sensitive.
 * @param userName The username for the database access. If that user does not have permission the connection will fail.
 * @param password The password in cleartext for the connection. If it does not hash to match the password for the user, the connection will fail.
 */
	public void buildConnectionString(String serverPath, String database, String userName, String password)
	{
		connectionString = "jdbc:mysql://" + serverPath + "/" + database + "?user=" + userName + "&password=" + password;
	}

	/**
	 * 
	 */
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

	/**
	 * Creates a database on the existing SQL server connection.
	 * Can use stuff in "" instead of creating a seprate line.
	 */
	public void createDatabase()
	{
		resetDatabaseConnection();

		try
		{
			Statement createDatabaseStatement = dataConnection.createStatement();

			int result = createDatabaseStatement.executeUpdate("CREATE DATABASE IF NOT EXISTS devvin;");

		}

		catch (SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}
	}

	/*
	 * Creates a database on the connected server with the specified databaseName.
	 * Can use the String createSQL and put it into the .exicuteUpdate or put the stuff in "" instead.
	 */
	public void createDatabase(String databaseName)
	{
		try
		{
			Statement createDatabaseStatement = dataConnection.createStatement();
			
			String createSQL = "CREATE DATABASE IF NOT EXISTS `" +databaseName + "`;`";

			int result = createDatabaseStatement.executeUpdate(createSQL);

		}

		catch (SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}
	}
	
	/**
	 * 
	 */
	public void deleteDatabase()

	{
		try
		{
			Statement deleteDatabaseStatement = dataConnection.createStatement();

			int result = deleteDatabaseStatement.executeUpdate("DROP DATABASE ;");

		}

		catch (SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}
	}

	/**
	 * 
	 * @param database
	 * @param tableName
	 */
	public void createTable(String database, String tableName)
	{
		try
		{
			Statement createDatabaseTableStatement = dataConnection.createStatement();

			String createTable = "CREATE TABLE" + " `" + database + "`.`" + tableName + "';" + "(" + "`grave_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY ," + "`grave_name` VARCHAR (20) NOT NULL" + ")" + "ENGINE = INNODB;";

			int result = createDatabaseTableStatement.executeUpdate("CREATE DATABASE devvin;");

		}
		catch (SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}
	}

	/**
	 * Creates a people table on the supplied database using Person data members.
	 * Will not override an existing table. Calls the displaySQLErrors method if
	 * there are SQL problems.
	 * 
	 * @param database
	 *            The suplied database.
	 */
	public void createPeopleTable(String database)
	{
		try
		{
			Statement createPersonTableStatement = dataConnection.createStatement();
			String createPersonTable = "CREATE TABLE IF NOT EXISTS `" + database + "` . `people`" + 
			"(" + 
					"`person_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY ," + 
					"`person_name` VARCHAR (50)," +
					"`death_date` VARCHAR (30)," + 
					"`birth_date` VARCHAR (30),"+ 
					"`is_married` TINYINT (1)," + 
					"`has_children` TINYINT (1)," + 
					"`age` INT" + ")" +
			")" +
			 "ENGINE = INNODB;";

			int result = createPersonTableStatement.executeUpdate(createPersonTable);
			createPersonTableStatement.close();
		}
		catch (SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}
	}

	/**
	 * 
	 * @param database
	 * @param tableName
	 */
	public void deleteTable(String database, String tableName)
	{
		try
		{
			Statement deleteDatabaseTableStatement = dataConnection.createStatement();

			String createTable = "` DROP `" + database + "`.`" + tableName + "`;`";

			int result = deleteDatabaseTableStatement.executeUpdate("DROP DATABASE devvin;");

		}

		catch (SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}
	}

	/**
	 * 
	 * @param current
	 */
	public void displaySQLErrors(SQLException current)
	{
		JOptionPane.showMessageDialog(baseController.getMyAppFrame(), "SQL Mesaage is:" + current.getMessage());
		JOptionPane.showMessageDialog(baseController.getMyAppFrame(), "SQL State is:" + current.getSQLState());
		JOptionPane.showMessageDialog(baseController.getMyAppFrame(), "Java error code is:" + current.getErrorCode());
	}

	/**
	 * Insert a Person object into the database, parsing fields as needed.
	 * Converts the boolean values to 1/0 for SQL support. 
	 * If there are errors it will call the displaySQLErrors method.
	 * @param currentPerson The current Person object being added to the database.
	 */
	public void insertPersonIntoTable(Person currentPerson)
	{
		try
		{
			Statement insertPersonStatement = dataConnection.createStatement();

			int databaseIsMarried, databaseHasChildren;

			if (currentPerson.isHasChildren())
			{
				databaseHasChildren = 1;
			}
			else
			{
				databaseHasChildren = 0;
			}

			if (currentPerson.isMarried())
			{
				databaseIsMarried = 1;
			}
			else
			{
				databaseIsMarried = 0;
			}

			String insertPersonString = "INSERT INTO `devvin`.`people`" + "(`person_name`,`death_date`,`birth_date`,`is_married`,`has_children`,`age`) " + "VALUES " + 
			"('" +
				currentPerson.getName() +
				"' , '" + 
				currentPerson.getDeathDate() +
				"' , '" + 
				currentPerson.getBirthDate() +
				"' , '" +
				databaseIsMarried +
				"' , '" + 
				databaseHasChildren +
				"' , '" + 
				currentPerson.getAge() + 
					"'" + 
			");";
			int result = insertPersonStatement.executeUpdate(insertPersonString);
			insertPersonStatement.close();
			
			JOptionPane.showMessageDialog(baseController.getMyAppFrame(), "Successfully inserted " + result + " rows into the table.");
		}
		catch (SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}
	}
	
	/**
	 * 
	 * @param oldName
	 * @param newName
	 */
	public void updatePersonName(String oldName, String newName)

	{
		try 
		{
			Statement updateStatement = dataConnection.createStatement();
			
			String updateString = "UPDATE `devvin`.`people`" +
									"SET `person_name` = '" + newName + "'" +
									"WHERE `person_name` = '" + oldName + "'";
			
			int result = updateStatement.executeUpdate(updateString);
			
			JOptionPane.showMessageDialog(baseController.getMyAppFrame(), "Successfully updated" + result + "row(s).");
			
			updateStatement.close();
		}
		catch(SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}
	}
	
	/**
	 * Creates a connection to the specified external server for the database.
	 */
	public void connectToExternalServer()
	{
		buildConnectionString("10.228.6.204", "graveyard", "ctec", "student");
		setupConnection();
		createDatabase();
	}

	/**
	 * 
	 * @param tableName
	 * @return
	 */
	public Vector<Person> selectDataFromTable(String tableName)
	{
		Vector<Person> personVector = new Vector<Person>();
		ResultSet seeDeadPeopleResults;
		String selectQuery = "SELECT person_age, person_name, person_has_kids, person_is_married, person_birth_date, person_death_date FROM " + tableName + ";";
		
		try
		{
			PreparedStatement selectStatement = dataConnection.prepareStatement(selectQuery);
			seeDeadPeopleResults = selectStatement.executeQuery();
			
			while(seeDeadPeopleResults.next())
			{
				Person tempPerson = new Person();
				
				int tempAge = seeDeadPeopleResults.getInt(1);
				String tempName = seeDeadPeopleResults.getString(2);
				boolean tempKids = seeDeadPeopleResults.getBoolean(3);
				boolean tempMarried = seeDeadPeopleResults.getBoolean(4);
				String tempBirth = seeDeadPeopleResults.getString(5);
				String tempDeath = seeDeadPeopleResults.getString(6);
				
				tempPerson.setAge(tempAge);
				tempPerson.setBirthDate(tempBirth);
				tempPerson.setDeathDate(tempDeath);
				tempPerson.setHasChildren(tempKids);
				tempPerson.setMarried(tempMarried);
				tempPerson.setName(tempName);
				
				personVector.add(tempPerson);
			}
			
			seeDeadPeopleResults.close();
			selectStatement.close();
		}
		
		catch (SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}
		
		return personVector;
	}
}
