package data.controller;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
	
	/**
	 * Designed for use with a JTable in the DataPanel.
	 * @return A TableModel composed of the header and data from the people table.
	 */
	public TableModel createTableModel()
	{
		Vector<String> headers = myDataController.getTableHeaders("graveyard", "people");
		Vector<Person> base = myDataController.selectDataFromTable("people");
		Vector<Vector <String>> tableBase = parsePersonInformation(base);
		TableModel sampleModel = new DefaultTableModel(tableBase, headers);
		
		return sampleModel;
	}
	
	/**
	 * Creates a two dimensional Vector to store rows of information about Person objects to be Placed into a JTable.
	 * @param peopleData The Vector<Person> information that will be parsed into String objects to be shown in the JTable.
	 * @return The 2D Vector to be used in the TableModel.
	 */
	private Vector<Vector<String>> parsePersonInformation(Vector<Person> peopleData)
	{
		Vector<Vector <String>> parsedData = new Vector<Vector <String>>();
		int currentRowCount = 1;
		
		for(Person current : peopleData)
		{
			Vector<String> currentRow = new Vector<String>();
			
			currentRow.add(Integer.toString(currentRowCount));
			currentRow.add(current.getName());
			currentRow.add(current.getBirthDate());
			currentRow.add(current.getDeathDate());
			currentRow.add(Boolean.toString(current.isMarried()));
			currentRow.add(Boolean.toString(current.isHasChildren()));
			currentRow.add(Integer.toString(current.getAge()));
			
			parsedData.add(currentRow);
			currentRowCount++;
		}
		
		return parsedData;
	}
}
