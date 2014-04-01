package data.view;

import javax.swing.JFrame;

import data.controller.AppController;



public class DataFrame extends JFrame
{
	/**
	 * 
	 */
	private AppController baseController;
	/**
	 * 
	 */
	private DataPanel myDatabasePanel;
	
	/**
	 * Builds a DataFrame with a reference to the AppController.
	 * @param baseController Reference to the AppController for MVC.
	 */
	public DataFrame(AppController baseController)
	{
		myDatabasePanel = new DataPanel(baseController);
		
		setupFrame();
	}

	/**
	 * Sets the size and visibility of the Java Frame.
	 */
	private void setupFrame()
	{
		this.setContentPane(myDatabasePanel);
		this.setSize(500, 500);
		this.setVisible(true);
		
	}
}
