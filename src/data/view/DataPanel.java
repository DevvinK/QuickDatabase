package data.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import data.controller.AppController;
import data.model.Person;

           
public class DataPanel extends JPanel
{
	private AppController baseController;
	
	private JButton createDatabaseButton;
	private JButton createPeopleTableButton;
	private JButton insertPersonIntoTableButton;
	private JButton updatePersonButton;
	private SpringLayout baseLayout;
	
	private JTextField ageField;
	private JTextField nameField;
	private JTextField deathDateField;

	public DataPanel(AppController baseController)
	{
		this.baseController = baseController;
		createDatabaseButton = new JButton("Click to create a Database");
		createPeopleTableButton = new JButton("Click to create table people");
		insertPersonIntoTableButton = new JButton("Insert the supplied person into the database");
		updatePersonButton = new JButton("update a Person");
		ageField = new JTextField("age", 5);
		nameField = new JTextField("person name", 20);
		deathDateField = new JTextField("death date", 10);
		
		baseLayout = new SpringLayout();
		
		setupPanel();
		setupLayout();
		setupListeners();
		
	}
	


	private void setupPanel()
	{
		this.setLayout(baseLayout);
		this.setBackground(Color.GRAY);
		this.add(createPeopleTableButton);
		this.add(deathDateField);
		this.add(createDatabaseButton);
		this.add(insertPersonIntoTableButton);
		this.add(ageField);
		this.add(nameField);
		this.add(deathDateField);
		this.add(updatePersonButton);
		
		
	}
	

	private void setupListeners()
	{
		createDatabaseButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				baseController.getMyDataController().connectToExternalServer();
			}
		});
		
		createPeopleTableButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent click)
			{
				baseController.getMyDataController().createPeopleTable("devvin");
			}
		});
		
		updatePersonButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				baseController.getMyDataController().updatePersonName(nameField.getText(), deathDateField.getText());
			}
		});
		
		insertPersonIntoTableButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				Person currentPerson = new Person();
				currentPerson.setName(nameField.getText());
				currentPerson.setDeathDate(deathDateField.getText());
				if(checkParseInteger(ageField.getText()))
				{
					currentPerson.setAge(Integer.parseInt(ageField.getText()));
				}
				
				baseController.getGraveyardPersons().add(currentPerson);
				baseController.getMyDataController().insertPersonIntoTable(currentPerson);
				
			}	
		});
	}
	
	private boolean checkParseInteger(String current)
	{
		boolean isParseable = false;
		
		try
		{
			Integer.parseInt(current);
			isParseable = true;
		}
		catch(NumberFormatException currtentError)
		{
			JOptionPane.showMessageDialog(this, "Try typing in an interger for the age");
		}
		
		
		return isParseable;
	}
	
	private Person createPersonFromValues()
	{
		Person tempDeadPerson = null;
		
		
		
		return tempDeadPerson;
	}

	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.NORTH, ageField, 129, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, ageField, 39, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.WEST, nameField, 40, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, nameField, -36, SpringLayout.NORTH, ageField);
		baseLayout.putConstraint(SpringLayout.NORTH, createDatabaseButton, 0, SpringLayout.NORTH, insertPersonIntoTableButton);
		baseLayout.putConstraint(SpringLayout.WEST, createDatabaseButton, 0, SpringLayout.EAST, insertPersonIntoTableButton);
		baseLayout.putConstraint(SpringLayout.NORTH, createPeopleTableButton, 10, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, createPeopleTableButton, 10, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.WEST, insertPersonIntoTableButton, 0, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, insertPersonIntoTableButton, -10, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.NORTH, deathDateField, 42, SpringLayout.SOUTH, ageField);
		baseLayout.putConstraint(SpringLayout.WEST, deathDateField, 0, SpringLayout.WEST, ageField);
		baseLayout.putConstraint(SpringLayout.EAST, deathDateField, -325, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, updatePersonButton, -14, SpringLayout.NORTH, insertPersonIntoTableButton);
		baseLayout.putConstraint(SpringLayout.EAST, updatePersonButton, 0, SpringLayout.EAST, deathDateField);
	}
}
