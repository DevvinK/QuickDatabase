package data.view;

import javax.swing.JFrame;

import data.controller.AppController;



public class DataFrame extends JFrame
{
	private AppController baseController;

	private DataPanel myDatabasePanel;
	
	public DataFrame(AppController baseController)
	{
		myDatabasePanel = new DataPanel(baseController);
		
		setupFrame();
	}

	
	private void setupFrame()
	{
		this.setContentPane(myDatabasePanel);
		this.setSize(500, 500);
		this.setVisible(true);
		
	}
}
