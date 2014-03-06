package dirty.controller;

import java.util.ArrayList;

import dirty.model.Gravemarker;
import dirty.model.Person;
import dirty.view.DirtyFrame;

public class AppController
{
	private DirtyController myDataController;
	private DirtyFrame myAppFrame;
	private ArrayList<Gravemarker> graveyardMarkerList;
	private ArrayList<Person> graveyardPersons;
	
	public AppController()
	{
		myDataController = new DirtyController();
		
		graveyardPersons = new ArrayList <Person>();
		graveyardMarkerList = new ArrayList <Gravemarker>();
	}
	
	public void start()
	{
		myAppFrame = new DirtyFrame(this);
		
		
	}
	
	public void addDeadPerson(Person currentDeadPerson)
	{
		graveyardPersons.add(currentDeadPerson);
	}
}
