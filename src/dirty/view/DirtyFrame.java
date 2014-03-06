package dirty.view;

import javax.swing.JFrame;

import dirty.controller.AppController;



public class DirtyFrame extends JFrame
{
	private AppController baseController;

	private DirtyPanel myDatabasePanel;
	
	public DirtyFrame(AppController baseController)
	{
		myDatabasePanel = new DirtyPanel(baseController);
		
		setupFrame();
	}

	
	private void setupFrame()
	{
		this.setContentPane(myDatabasePanel);
		this.setSize(500, 500);
		this.setVisible(true);
		
	}
}
