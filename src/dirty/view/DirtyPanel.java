package dirty.view;

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

import dirty.controller.AppController;
import dirty.model.Person;

           
public class DirtyPanel extends JPanel
{
	private AppController baseController;
	
	private SpringLayout baseLayout;
	private JButton testButton;
	private JTextField userTextField;
	private JTextArea chatArea;
	private JScrollPane chatPane;
	private int clickCount;

	public DirtyPanel(AppController baseController)
	{
		this.baseController = baseController;
		testButton = new JButton("Click here to type to the chatbot!");
		baseLayout = new SpringLayout();
		userTextField = new JTextField(30);
		chatArea = new JTextArea(10, 30);
		chatPane = new JScrollPane(chatArea);
		
		clickCount = 0;


		
		
		setupPanel();
		setupLayout();
		setupListeners();
		
	}
	

	private void setupChat()
	{
		
	}
	

	private void setupPanel()
	{
		this.setLayout(baseLayout);
		this.setBackground(Color.GRAY);
		this.add(testButton);
		this.add(userTextField);
		this.add(chatPane);
		
		
		
		
		chatArea.setWrapStyleWord(true);
		chatArea.setLineWrap(true);
		
	}
	

	private void setupListeners()
	{
		testButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				baseController.getMyDataController().createDatabase();
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
		
	}
}
