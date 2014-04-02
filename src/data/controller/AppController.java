package data.controller;

import java.util.ArrayList;

import data.model.Gravemarker;
import data.model.Person;
import data.view.DataFrame;

public class AppController
{
	/**
	 * Creates an object for the DataController.
	 */
	private DataController myDataController;
	/**
	 * Creates an object for the DataFrame.
	 */
	private DataFrame myAppFrame;
	/**
	 * Creates an ArrayList for Gravemarkers.
	 */
	private ArrayList<Gravemarker> graveyardMarkerList;
	/**
	 * Creates an ArrayList for Persons.
	 */
	private ArrayList<Person> graveyardPersons;
	
	
	public DataController getMyDataController()
	{
		return myDataController;
	}
	public void setMyDataController(DataController myDataController)
	{
		this.myDataController = myDataController;
	}
	public DataFrame getMyAppFrame()
	{
		return myAppFrame;
	}
	public void setMyAppFrame(DataFrame myAppFrame)
	{
		this.myAppFrame = myAppFrame;
	}
	public ArrayList<Gravemarker> getGraveyardMarkerList()
	{
		return graveyardMarkerList;
	}
	public void setGraveyardMarkerList(ArrayList<Gravemarker> graveyardMarkerList)
	{
		this.graveyardMarkerList = graveyardMarkerList;
	}
	public ArrayList<Person> getGraveyardPersons()
	{
		return graveyardPersons;
	}
	public void setGraveyardPersons(ArrayList<Person> graveyardPersons)
	{
		this.graveyardPersons = graveyardPersons;
	}
	
	/**
	 * 
	 */
	public AppController()
	{
		myDataController = new DataController(this);
		
		graveyardPersons = new ArrayList <Person>();
		graveyardMarkerList = new ArrayList <Gravemarker>();
	}
	
	/**
	 * 
	 */
	public void start()
	{
		myAppFrame = new DataFrame(this);
		
		
	}
	
	/**
	 * 
	 * @param currentDeadPerson
	 */
	public void addDeadPerson(Person currentDeadPerson)
	{
		graveyardPersons.add(currentDeadPerson);
	}
}
