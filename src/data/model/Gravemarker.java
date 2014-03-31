package data.model;

import java.util.ArrayList;

public class Gravemarker
{
	private ArrayList<Person> gravePersonList;
	private boolean isReadable;
	
	
	public String toString()
	{
		String graveInfo = "";
		
		for(Person current : gravePersonList)
		{
			graveInfo += current + " is buried here.\n";
		}
		
		if(isReadable)
		{
			graveInfo += "This grave is readable";
		}
		
		else
		{
			graveInfo += "This grave is NOT readable";
		}
		
		
		return graveInfo;
	}
}
