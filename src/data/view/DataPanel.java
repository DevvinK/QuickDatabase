package data.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.TableModel;

import data.controller.AppController;
import data.model.Person;

public class DataPanel extends JPanel
{
	/**
	 * 
	 */
	private AppController baseController;
	/**
	 * 
	 */
	private JButton createDatabaseButton;
	/**
	 * 
	 */
	private JButton createPeopleTableButton;
	/**
	 * 
	 */
	private JButton insertPersonIntoTableButton;
	/**
	 * 
	 */
	private JButton updatePersonButton;
	/**
	 * 
	 */
	private JButton selectButton;
	/**
	 * 
	 */
	private JButton connectToExternalDatabaseButton;
	/**
	 * 
	 */
	private SpringLayout baseLayout;
	

	/**
	 * 
	 */
	private JTextField ageField;
	/**
	 * 
	 */
	private JTextField nameField;
	/**
	 * 
	 */
	private JTextField deathDateField;
	/**
	 * 
	 */
	private JTextArea resultsArea;
	/**
	 * 
	 */
	private JScrollPane resultsPane;
	
	private JTable dataTable;

	/**
	 * 
	 * @param baseController
	 */
	public DataPanel(AppController baseController)
	{
		this.baseController = baseController;
		createDatabaseButton = new JButton("Click to create a Database");
		createPeopleTableButton = new JButton("Click to create table people");
		insertPersonIntoTableButton = new JButton("Insert the supplied person into the database");
		selectButton = new JButton("Select");
		updatePersonButton = new JButton("update a Person");
		connectToExternalDatabaseButton = new JButton ("Connect to a Database");
		ageField = new JTextField("age", 5);
		nameField = new JTextField("person name", 20);
		deathDateField = new JTextField("death date", 10);
		resultsPane = new JScrollPane();
		

		baseLayout = new SpringLayout();
		
		
		setupTable();
		setupPanel();
		setupLayout();
		setupListeners();

	}

	/**
	 * 
	 */
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
		this.add(resultsPane);
		this.add(selectButton);
		this.add(connectToExternalDatabaseButton);
		resultsArea = new JTextArea(5, 20);
		baseLayout.putConstraint(SpringLayout.WEST, resultsArea, 27, SpringLayout.EAST, resultsPane);
		baseLayout.putConstraint(SpringLayout.SOUTH, resultsArea, -6, SpringLayout.NORTH, connectToExternalDatabaseButton);
		add(resultsArea);
		
				resultsArea.setWrapStyleWord(true);
				resultsArea.setLineWrap(true);

	}

	/**
	 * 
	 */
	private void clearFields()
	{
		deathDateField.setText("");
		ageField.setText("");
		resultsArea.setText("");
		nameField.setText("");
	}

	/**
	 * 
	 */
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
				baseController.getMyDataController().connectToExternalServer();
				Vector<Person> temp = baseController.getMyDataController().selectDataFromTable("people");
				readFromVector(temp);
			}
		});
		
		selectButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				Vector<Person> temp = baseController.getMyDataController().selectDataFromTable("people");
				readFromVector(temp);
			}
		});
		
		connectToExternalDatabaseButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				baseController.getMyDataController().connectToExternalServer();
			}
		});

		insertPersonIntoTableButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				Person currentPerson = new Person();
				currentPerson.setName(nameField.getText());
				currentPerson.setDeathDate(deathDateField.getText());
				if (checkParseInteger(ageField.getText()))
				{
					currentPerson.setAge(Integer.parseInt(ageField.getText()));
				}

				baseController.getGraveyardPersons().add(currentPerson);
				baseController.getMyDataController().insertPersonIntoTable(currentPerson);

			}
		});
	}

	/**
	 * 
	 * @param current
	 * @return
	 */
	private boolean checkParseInteger(String current)
	{
		boolean isParseable = false;

		try
		{
			Integer.parseInt(current);
			isParseable = true;
		}
		catch (NumberFormatException currtentError)
		{
			JOptionPane.showMessageDialog(this, "Try typing in an interger for the age");
		}

		return isParseable;
	}

	/**
	 * 
	 * @return
	 */
	private Person createPersonFromValues()
	{
		Person tempDeadPerson = null;

		return tempDeadPerson;
	}
	
	/**
	 * 
	 * @param currentPeople
	 */
	private void readFromVector(Vector<Person> currentPeople)
	{
		clearFields();
		
		for (Person currentPerson : currentPeople)
		{
			resultsArea.append(currentPerson.toString()+ "\n");
		}
	}

	/**
	 * 
	 */
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.NORTH, deathDateField, 0, SpringLayout.NORTH, ageField);
		baseLayout.putConstraint(SpringLayout.WEST, deathDateField, 1045, SpringLayout.EAST, resultsPane);
		baseLayout.putConstraint(SpringLayout.EAST, deathDateField, -29, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, nameField, 0, SpringLayout.NORTH, ageField);
		baseLayout.putConstraint(SpringLayout.WEST, nameField, 41, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, ageField, 51, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.EAST, ageField, 0, SpringLayout.EAST, connectToExternalDatabaseButton);
		baseLayout.putConstraint(SpringLayout.SOUTH, selectButton, -76, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.NORTH, updatePersonButton, 6, SpringLayout.SOUTH, selectButton);
		baseLayout.putConstraint(SpringLayout.NORTH, createPeopleTableButton, 0, SpringLayout.NORTH, updatePersonButton);
		baseLayout.putConstraint(SpringLayout.WEST, createPeopleTableButton, 5, SpringLayout.EAST, updatePersonButton);
		baseLayout.putConstraint(SpringLayout.NORTH, connectToExternalDatabaseButton, 0, SpringLayout.NORTH, selectButton);
		baseLayout.putConstraint(SpringLayout.WEST, connectToExternalDatabaseButton, 62, SpringLayout.EAST, selectButton);
		baseLayout.putConstraint(SpringLayout.WEST, selectButton, 0, SpringLayout.WEST, updatePersonButton);
		baseLayout.putConstraint(SpringLayout.NORTH, resultsPane, 10, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, updatePersonButton, 30, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, createDatabaseButton, 0, SpringLayout.NORTH, insertPersonIntoTableButton);
		baseLayout.putConstraint(SpringLayout.WEST, createDatabaseButton, 0, SpringLayout.EAST, insertPersonIntoTableButton);
		baseLayout.putConstraint(SpringLayout.WEST, insertPersonIntoTableButton, 0, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, insertPersonIntoTableButton, -10, SpringLayout.SOUTH, this);
	}

	/**
	 * Helper method to land and the create a JTable based on the components in the database and them place it in the JScrollPane.
	 */
	private void setupTable()
	{
		TableModel sampleModel = baseController.createTableModel();
		dataTable = new JTable(sampleModel);
		resultsPane = new JScrollPane(dataTable);
	}
}
